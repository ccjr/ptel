package mobi.ccjr.ptel.model;

import android.content.Context;
import android.content.SharedPreferences;

import java.math.BigDecimal;

import mobi.ccjr.ptel.utils.Constants;

public class UserPreference {
    public static int expiryThresholdInDays(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.PREFS_FILE_NAME,
                                                               Context.MODE_PRIVATE);
        return prefs.getInt(Constants.PREFS_EXP_DAYS_ALARM,
                            Constants.EXPIRY_ALARM_DEFAULT_THRESHOLD_IN_DAYS);
    }

    public static BigDecimal balanceThresholdInDollars(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.PREFS_FILE_NAME,
                                                               Context.MODE_PRIVATE);
        String balance = prefs.getString(Constants.PREFS_LOW_BALANCE_ALARM,
                                         Constants.BALANCE_ALARM_DEFAULT_THRESHOLD_IN_DOLLARS.toString());
        return new BigDecimal(balance);
    }
}
