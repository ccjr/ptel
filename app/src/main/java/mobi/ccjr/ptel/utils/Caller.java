package mobi.ccjr.ptel.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class Caller {
    private static final String ADD_CREDIT_CALL_URI = "tel:233";
    private static final String CUSTOMER_SERVICE_CALL_URI = "tel:611";

    public static void callAddCredit(Context context) {
        call(context, ADD_CREDIT_CALL_URI);
    }

    public static void callCustomerService(Context context) {
        call(context, CUSTOMER_SERVICE_CALL_URI);
    }

    private static void call(Context context, String callUri) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(callUri));
        context.startActivity(callIntent);
    }
}
