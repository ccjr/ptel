package mobi.ccjr.ptel.service;

import android.app.IntentService;
import android.content.Intent;
import android.telephony.SmsManager;

import mobi.ccjr.ptel.utils.Constants;

public class BalanceRequestService
        extends IntentService {

    /**
     * A constructor is required, and must call the super IntentService(String)
     * constructor with a name for the worker thread.
     */
    public BalanceRequestService() {
        super(BalanceRequestService.class.getName());
    }

    /**
     * The IntentService calls this method from the default worker thread with
     * the intent that started the service. When this method returns, IntentService
     * stops the service, as appropriate.
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(Constants.SMS_BALANCE_REQUEST_NUMBER,
                                   null,
                                   Constants.SMS_BALANCE_REQUEST_COMMAND,
                                   null,
                                   null);
    }

}
