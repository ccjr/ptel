package mobi.ccjr.ptel.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import mobi.ccjr.ptel.model.Balance;
import mobi.ccjr.ptel.data.BalanceDAO;
import mobi.ccjr.ptel.parser.BalanceMessageParser;
import mobi.ccjr.ptel.utils.Constants;

public class BalanceMessageReceiver
        extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        if (bundle != null) {
            // ---retrieve the SMS message received---
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++) {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                if (msgs[i].getOriginatingAddress().equals(Constants.SMS_BALANCE_REQUEST_NUMBER)) {
                    String message = msgs[i].getMessageBody()
                                            .toString();
                    persistBalance(context, message);
                }
            }
        }
    }

    private void persistBalance(Context context, String message) {
        BalanceMessageParser parser = new BalanceMessageParser(message);
        BalanceDAO dao = new BalanceDAO(context);
        Balance balance = new Balance(parser.extractBalance(), parser.extractExpiry());
        dao.save(balance);

        // TODO: update the UI if application is running
        Toast toast = Toast.makeText(context,
                                     "balance: " + parser.extractBalance(),
                                     Toast.LENGTH_LONG);
        toast.show();
    }
}
