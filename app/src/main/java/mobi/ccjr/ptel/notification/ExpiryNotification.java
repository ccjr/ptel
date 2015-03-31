package mobi.ccjr.ptel.notification;

import android.content.Context;

import mobi.ccjr.ptel.R;
import mobi.ccjr.ptel.model.Balance;

public class ExpiryNotification
        extends BalanceNotification {

    public ExpiryNotification(Balance balance) {
        super(balance);
    }

    public void notify(Context context) {
        notify(context,
               context.getString(R.string.notification_expiry_title),
               context.getString(R.string.notification_expiry_body));
    }
}
