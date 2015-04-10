package mobi.ccjr.ptel.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import java.text.NumberFormat;

import mobi.ccjr.ptel.R;
import mobi.ccjr.ptel.model.Balance;

public class BalanceTextView
        extends BlinkableTextView {
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
        this.setText(getBalanceText());
        if (balance.inBalanceAlarmState(getContext())) {
            this.setTextColor(getResources().getColor(R.color.red_alarm));
            this.blink();
        }
        else {
            this.setTextColor(getResources().getColor(R.color.normal_blue));
        }
    }

    private String getBalanceText() {
        return NumberFormat.getCurrencyInstance().format(balance.getBalanceAsBigDecimal());
    }
}
