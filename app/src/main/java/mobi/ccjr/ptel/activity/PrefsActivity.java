package mobi.ccjr.ptel.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mobi.ccjr.ptel.R;
import mobi.ccjr.ptel.utils.Constants;

public class PrefsActivity
        extends Activity
        implements View.OnClickListener {

    TextView mTxtDays, mTxtDollars;
    RelativeLayout mRlDays, mRlDollars;
    private SharedPreferences mPrefs;
    private EditText input;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferences);

        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mPrefs = this.getSharedPreferences(Constants.PREFS_FILE_NAME, MODE_PRIVATE);

        bindIds();

        setOnClickListener();

        setDays(mPrefs.getInt(Constants.PREFS_EXP_DAYS_ALARM,
                              Constants.EXPIRY_ALARM_DEFAULT_THRESHOLD_IN_DAYS));

        setDollars(mPrefs.getString(Constants.PREFS_LOW_BALANCE_ALARM,
                                    Constants.BALANCE_ALARM_DEFAULT_THRESHOLD_IN_DOLLARS.toString()));
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

    @Override
    protected void onPause() {
        super.onPause();
        if (input != null) {
            showKeyboard(input, false);
        }
    }

    private void setOnClickListener() {
        mRlDays.setOnClickListener(this);
        mRlDollars.setOnClickListener(this);
    }

    private void bindIds() {
        mTxtDays = (TextView) findViewById(R.id.prefs_txtDays);
        mTxtDollars = (TextView) findViewById(R.id.prefs_txtDollars);
        mRlDays = (RelativeLayout) findViewById(R.id.prefs_linExpDate);
        mRlDollars = (RelativeLayout) findViewById(R.id.prefs_linLowCredit);
    }

    private void setDays(int days) {
        mPrefs.edit()
              .putInt(Constants.PREFS_EXP_DAYS_ALARM, days)
              .apply();
        mTxtDays.setText(getResources().getQuantityString(R.plurals.days, days, days));
    }

    private void setDays(String value) {
        try {
            setDays(Integer.valueOf(value));
        }
        catch (Exception e) {
            //TODO Warn for invalid number!
        }
    }

    private void setDollars(String value) {
        if (validateStringToBigDecimal(value)) {
            mPrefs.edit()
                  .putString(Constants.PREFS_LOW_BALANCE_ALARM, value)
                  .apply();
        }

        mTxtDollars.setText(convertToCurrency(mPrefs.getString(Constants.PREFS_LOW_BALANCE_ALARM,
                                                               Constants.BALANCE_ALARM_DEFAULT_THRESHOLD_IN_DOLLARS.toString())));

        BigDecimal bdValue = new BigDecimal(mPrefs.getString(Constants.PREFS_LOW_BALANCE_ALARM,
                                                             Constants.BALANCE_ALARM_DEFAULT_THRESHOLD_IN_DOLLARS.toString()));
        setTxtColor(bdValue);
    }

    private void setTxtColor(BigDecimal bdValue) {
        if (bdValue.compareTo(new BigDecimal("0")) < 0) {
            mTxtDollars.setTextColor(getResources().getColor(R.color.red_alarm));
        }
        else {
            mTxtDollars.setTextColor(getResources().getColor(R.color.normal_blue));
        }
    }

    private String convertToCurrency(String value) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        return formatter.format(new BigDecimal(value));
    }

    private boolean validateStringToBigDecimal(String value) {
        try {
            new BigDecimal(value);

            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    private void create_dialog(String title, String message, int type) {

        input = new EditText(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        input.setGravity(Gravity.CENTER);

        builder.setTitle(title)
               .setMessage(message)
               .setView(input)
               .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int whichButton) {
                       showKeyboard(input, false);
                       dialog.cancel();
                   }
               });

        switch (type) {
        case R.id.prefs_linExpDate:
            input.setInputType(InputType.TYPE_CLASS_NUMBER);//TODO Negative Number????
            input.setHint("X days");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    setDays(input.getText()
                                 .toString());
                    showKeyboard(input, false);
                    dialog.cancel();
                }
            });
            break;

        case R.id.prefs_linLowCredit:
            input.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
            //TODO how to choose between "." or "," LOCALIZATION???
            input.setKeyListener(DigitsKeyListener.getInstance("-0123456789."));
            input.setHint("X Dollars");
            setTextWatcher(input);

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    setDollars(input.getText()
                                    .toString());
                    showKeyboard(input, false);
                    dialog.cancel();
                }
            });
            break;
        }

        builder.create();
        builder.show();

        showKeyboard(input, true);
        input.requestFocus();
    }

    private void showKeyboard(EditText edt, boolean show) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (show) {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }
        else {
            imm.hideSoftInputFromWindow(edt.getWindowToken(), 0);
        }
    }

    private void setTextWatcher(final EditText edt) {
        TextWatcher textWatcher = new TextWatcher() {
            private boolean repeating = false;
            private String priorString;

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (repeating) {
                    repeating = false;
                    return;
                }

                Matcher m = Pattern.compile(Constants.REGEX_CURRENCY)
                                   .matcher(s.toString());

                if (!m.matches()) {
                    repeating = true;
                    edt.setText(priorString);
                }
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                priorString = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                edt.setSelection(edt.getText()
                                    .length());
            }
        };

        edt.addTextChangedListener(textWatcher);
    }
}
