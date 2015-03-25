package mobi.ccjr.ptel.receiver;

import mobi.ccjr.ptel.R;
import mobi.ccjr.ptel.parser.BalanceMessageParser;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class BalanceMessageReceiver extends BroadcastReceiver {

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
				String message = msgs[i].getMessageBody().toString();
                persistBalance(context, message);
			}
		}
	}

    private void persistBalance(Context context, String message) {
        BalanceMessageParser parser = new BalanceMessageParser(message);

        // TODO move this code to its own class
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(context.getString(R.string.saved_balance), parser.extractBalance());
        editor.commit();

        Toast toast = Toast.makeText(context, "message: " + parser.extractBalance(),
            Toast.LENGTH_LONG);
        toast.show();
    }
}
