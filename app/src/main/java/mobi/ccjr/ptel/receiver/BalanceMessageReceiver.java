package mobi.ccjr.ptel.receiver;

import mobi.ccjr.ptel.parser.BalanceMessageParser;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
				String messageReceived = msgs[i].getMessageBody().toString();

				BalanceMessageParser parser = new BalanceMessageParser(
						messageReceived);
				// TODO update balance somewhere
				Toast toast = Toast.makeText(context,
						"message: " + parser.extractBalance(),
						Toast.LENGTH_LONG);
				toast.show();
			}
		}
	}
}
