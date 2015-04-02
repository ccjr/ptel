package mobi.ccjr.ptel.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import mobi.ccjr.ptel.data.BalanceDAO;
import mobi.ccjr.ptel.model.Balance;
import mobi.ccjr.ptel.notification.BalanceNotification;
import mobi.ccjr.ptel.parser.BalanceMessageParser;
import mobi.ccjr.ptel.utils.Constants;

public class BalanceMessageReceiver
        extends BroadcastReceiver {
    private Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            // ---retrieve the SMS message received---
            Object[] pdus = (Object[]) bundle.get("pdus");
            SmsMessage[] msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++) {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                if (msgs[i].getOriginatingAddress()
                           .equals(Constants.SMS_BALANCE_REQUEST_NUMBER)) {
                    String message = msgs[i].getMessageBody();

                    Balance balance = persistBalance(message);
                    updateInterfaceIfAppropriate(balance);
                    notifyUserIfAppropriate(balance);
                }
            }
        }
    }

    private Balance persistBalance(String message) {
        BalanceMessageParser parser = new BalanceMessageParser(message);
        BalanceDAO dao = new BalanceDAO(context);
        Balance balance = new Balance(parser.extractBalance(), parser.extractExpiry());
        dao.save(balance);
        return balance;
    }

    private void updateInterfaceIfAppropriate(Balance balance) {
        // TODO: update the UI if application is running
        String text = "balance/expiry: " + balance.getBalance() + " - " + balance.getExpiry();
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        toast.show();
    }

    private void notifyUserIfAppropriate(Balance balance) {
        BalanceNotification notification = BalanceNotification.getNotification(context, balance);
        if (notification != null) {
            notification.notify(context);
        }
    }
}
