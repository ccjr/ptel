package mobi.ccjr.ptel.ui;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Date;

import mobi.ccjr.ptel.R;
import mobi.ccjr.ptel.model.Balance;
import mobi.ccjr.ptel.utils.Constants;
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
        if (daysUntilExpiry() > Constants.EXPIRY_ALARM_DEFAULT_THRESHOLD_IN_DAYS) {
            this.setTextColor(getResources().getColor(R.color.normal_blue));
        } else {
            this.setTextColor(getResources().getColor(R.color.red_alarm));
        }
    }

    private Spanned getExpiryText() {
        String html = "<small>credit expires in</small><br>" + getExpiryInWords();
        return Html.fromHtml(html);
    }

    private long daysUntilExpiry() {
        Date date1 = new Date();
        Date date2 = balance.getExpiryAsDate();
        return DateCalculation.daysBetweenDates(date1, date2);
    }

    private String getExpiryInWords() {
        // TODO: support expiring today and in past
        long days = daysUntilExpiry();
        return days + " days";
    }
}
