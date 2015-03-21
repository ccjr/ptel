package mobi.ccjr.ptel.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class BalanceMessageReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		SmsMessage[] msgs = null;
		String messageReceived = "";
		if (bundle != null) {
			// ---retrieve the SMS message received---
			Object[] pdus = (Object[]) bundle.get("pdus");
			msgs = new SmsMessage[pdus.length];
			for (int i = 0; i < msgs.length; i++) {
				msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
				// TODO: parse and extract balance
				messageReceived += msgs[i].getMessageBody().toString();
			}
		}
	}
}
