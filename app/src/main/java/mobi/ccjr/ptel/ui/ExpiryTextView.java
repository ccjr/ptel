package mobi.ccjr.ptel.ui;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import mobi.ccjr.ptel.R;
import mobi.ccjr.ptel.model.Balance;

public class ExpiryTextView
        extends TextView {
    private Balance balance;

    public ExpiryTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ExpiryTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpiryTextView(Context context) {
        super(context);
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
        this.setText(getExpiryText());
        if (balance.inExpiryAlarmState(getContext())) {
            this.setTextColor(getResources().getColor(R.color.red_alarm));
            this.blink();
        }
        else {
            this.setTextColor(getResources().getColor(R.color.normal_blue));
        }
    }

    private Spanned getExpiryText() {
        long days = balance.daysUntilExpiry();
        String html;
        if (days > 0) {
            html = "<small>credit expires in</small><br>" + days + " days";
        } else if (days == 0) {
            html = "<small>credit expires</small><br>today";
        } else {
            html = "<small>credit expired</small><br>already";
        }
        return Html.fromHtml(html);
    }

    private void blink() {
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(250);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(4);
        startAnimation(anim);
    }
}
