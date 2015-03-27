package mobi.ccjr.ptel.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import mobi.ccjr.ptel.model.Balance;

public class BalanceTextView
        extends TextView {
    private Balance balance;

    public BalanceTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public BalanceTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BalanceTextView(Context context) {
        super(context);
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
        this.setText(balance.getBalance());
    }
}
