package mobi.ccjr.ptel.notification;

import android.content.Context;

public class ExpiryNotification
        extends BaseNotification {

    public void notify(Context context) {
        // TODO strings in strings.xml
        notify(context, "PTEL Mobile - Balance", "Your balance is getting low - $2.50.");
    }

}
