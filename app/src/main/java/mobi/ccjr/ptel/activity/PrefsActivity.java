package mobi.ccjr.ptel.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.NumberFormat;

import mobi.ccjr.ptel.R;
import mobi.ccjr.ptel.utils.Constants;

public class PrefsActivity
        extends Activity
        implements View.OnClickListener {

    TextView mTxtDays;
    TextView mTxtDollars;
    private SharedPreferences mPrefs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        mTxtDays = (TextView) findViewById(R.id.prefs_txtDays);
        mTxtDollars = (TextView) findViewById(R.id.prefs_txtDollars);
        RelativeLayout rlDays = (RelativeLayout) findViewById(R.id.prefs_linExpDate);
        RelativeLayout rlDollars = (RelativeLayout) findViewById(R.id.prefs_linLowCredit);
        rlDays.setOnClickListener(this);
        rlDollars.setOnClickListener(this);

        mPrefs = this.getSharedPreferences(Constants.PREFS_FILE_NAME, MODE_PRIVATE);
        setDays(mPrefs.getInt(Constants.PREFS_EXP_DAYS_ALARM,
                              Constants.EXPIRY_ALARM_DEFAULT_THRESHOLD_IN_DAYS));

        mTxtDollars.setText(convertToCurrency(mPrefs.getFloat(Constants.PREFS_LOW_BALANCE_ALARM,
                                                              Constants.BALANCE_ALARM_DEFAULT_THRESHOLD_IN_DOLLARS)));
    }

    private void setDays(int days) {
        mPrefs.edit()
              .putInt(Constants.PREFS_EXP_DAYS_ALARM, days)
              .apply();

        mTxtDays.setText(String.valueOf(days == 1 ? days + " day" : days + " days"));
    }

    private void setDollars(String value) {
        float dollars;

        try {
            dollars = Float.valueOf(value);
        }
        catch (Exception e) {
            dollars = mPrefs.getFloat(Constants.PREFS_LOW_BALANCE_ALARM,
                                      Constants.BALANCE_ALARM_DEFAULT_THRESHOLD_IN_DOLLARS);
        }
        mPrefs.edit()
              .putFloat(Constants.PREFS_LOW_BALANCE_ALARM, dollars)
              .apply();

        mTxtDollars.setText(convertToCurrency(mPrefs.getFloat(Constants.PREFS_LOW_BALANCE_ALARM,
                                                              Constants.BALANCE_ALARM_DEFAULT_THRESHOLD_IN_DOLLARS)));
    }

    private void setDays(String value) {
        int days;

        try {
            days = Integer.valueOf(value);
        }
        catch (Exception e) {
            days = mPrefs.getInt(Constants.PREFS_EXP_DAYS_ALARM,
                                 Constants.EXPIRY_ALARM_DEFAULT_THRESHOLD_IN_DAYS);
        }

        mPrefs.edit()
              .putInt(Constants.PREFS_EXP_DAYS_ALARM, days)
              .apply();

        mTxtDays.setText(String.valueOf(
                days == 1 ? days + " day" : days + " days"));//TODO improve singular / plural
    }

    private String convertToCurrency(float value) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        return formatter.format(value);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.prefs_linExpDate:
            create_dialog(getResources().getString(R.string.expiration_days_alarm),
                          getResources().getString(R.string.warn_credit_will_expire),
                          R.id.prefs_linExpDate);
            break;

        case R.id.prefs_linLowCredit:
            create_dialog(getResources().getString(R.string.low_credit_alarm),
                          getResources().getString(R.string.warn_low_credit),
                          R.id.prefs_linLowCredit);
            break;
        }
    }

    private void create_dialog(String title, String message, int type) {
        //TODO improve dialog UI
        final EditText input = new EditText(this);
        AlertDialog.Builder ad = new AlertDialog.Builder(this);

        ad.setTitle(title)
          .setMessage(message)
          .setView(input)
          .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int whichButton) {
                  // Do nothing.
              }
          });
        switch (type) {
        case R.id.prefs_linExpDate:
            input.setInputType(InputType.TYPE_CLASS_NUMBER);//TODO Negative Number????
            input.setHint("X days");
            ad.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    setDays(input.getText()
                                 .toString());
                }
            });
            break;

        case R.id.prefs_linLowCredit:
            input.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
            input.setHint("X Dollars");
            ad.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    setDollars(input.getText()
                                    .toString());
                }
            });
            break;
        }

        ad.show();
    }
}
