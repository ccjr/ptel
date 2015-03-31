package mobi.ccjr.ptel.notification;

import android.content.Context;

import mobi.ccjr.ptel.R;
import mobi.ccjr.ptel.model.Balance;

public class LowBalanceNotification
        extends BalanceNotification {

    public LowBalanceNotification(Balance balance) {
        super(balance);
    }

    public void notify(Context context) {
        notify(context,
               context.getString(R.string.notification_balance_title),
               context.getString(R.string.notification_balance_body));
    }
}
