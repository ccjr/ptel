package mobi.ccjr.ptel.notification;

import android.content.Context;

public class LowBalanceNotification
        extends BaseNotification {

    public void notify(Context context) {
        // TODO strings in strings.xml
        notify(context,
               "PTEL Mobile - Credit Expiry",
               "Your credit expiry date is approaching soon - 3 days left.");
    }

}
