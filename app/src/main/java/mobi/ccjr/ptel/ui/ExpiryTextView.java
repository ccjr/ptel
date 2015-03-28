package mobi.ccjr.ptel.ui;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import java.util.Date;

import mobi.ccjr.ptel.R;
import mobi.ccjr.ptel.model.Balance;
import mobi.ccjr.ptel.model.UserPreference;
import mobi.ccjr.ptel.utils.DateCalculation;

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
        if (inAlarmState()) {
            this.setTextColor(getResources().getColor(R.color.red_alarm));
            this.blink();
        }
        else {
            this.setTextColor(getResources().getColor(R.color.normal_blue));
        }
    }

    private boolean inAlarmState() {
        return balance.daysUntilExpiry() <= UserPreference.expiryThresholdInDays(getContext());
    }

    private Spanned getExpiryText() {
        String html = "<small>credit expires in</small><br>" + getExpiryInWords();
        return Html.fromHtml(html);
    }

    private void blink() {
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(250);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(4);
        startAnimation(anim);
    }

    private String getExpiryInWords() {
        // TODO: support expiring today and in past
        long days = balance.daysUntilExpiry();
        return days + " days";
    }
}
