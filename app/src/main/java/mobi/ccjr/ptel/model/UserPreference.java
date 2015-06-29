package mobi.ccjr.ptel.model;

import android.content.Context;
import android.content.SharedPreferences;

import java.math.BigDecimal;

import mobi.ccjr.ptel.utils.Constants;

public class UserPreference {
    public static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(Constants.PREFS_FILE_NAME,
                                            Context.MODE_PRIVATE);
    }

    public static boolean firstRunComplete(Context context) {
        SharedPreferences prefs = getPrefs(context);
        return prefs.getBoolean(Constants.PREFS_FIRST_RUN_COMPLETE, false);
    }

    public static void markFirstRunComplete(Context context) {
        SharedPreferences prefs = getPrefs(context);
        prefs.edit().putBoolean(Constants.PREFS_FIRST_RUN_COMPLETE, true).apply();
    }

    public static int expiryThresholdInDays(Context context) {
        SharedPreferences prefs = getPrefs(context);
        return prefs.getInt(Constants.PREFS_EXP_DAYS_ALARM,
                            Constants.EXPIRY_ALARM_DEFAULT_THRESHOLD_IN_DAYS);
    }

    public static BigDecimal balanceThresholdInDollars(Context context) {
        SharedPreferences prefs = getPrefs(context);
        String balance = prefs.getString(Constants.PREFS_LOW_BALANCE_ALARM,
                                         Constants.BALANCE_ALARM_DEFAULT_THRESHOLD_IN_DOLLARS.toString());
        return new BigDecimal(balance);
    }
}
