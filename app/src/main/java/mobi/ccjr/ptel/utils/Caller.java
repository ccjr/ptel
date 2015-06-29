package mobi.ccjr.ptel.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import mobi.ccjr.ptel.service.BalanceRequestService;

public class Caller {
    private static final String ADD_CREDIT_CALL_URI = "tel:233";
    private static final String CUSTOMER_SERVICE_CALL_URI = "tel:611";

    public static void callAddCredit(Context context) {
        call(context, ADD_CREDIT_CALL_URI);
    }

    public static void callCustomerService(Context context) {
        call(context, CUSTOMER_SERVICE_CALL_URI);
    }

    public static void checkBalance(Context context) {
        Intent intent = new Intent(Intent.ACTION_SYNC, null, context, BalanceRequestService.class);
        context.startService(intent);
    }

    private static void call(Context context, String callUri) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(callUri));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
