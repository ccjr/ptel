package mobi.ccjr.ptel.utils;

import java.math.BigDecimal;

public class Constants {
    public static final int EXPIRY_ALARM_DEFAULT_THRESHOLD_IN_DAYS = 1;
    public static final BigDecimal BALANCE_ALARM_DEFAULT_THRESHOLD_IN_DOLLARS = new BigDecimal(
            "5.00");

    public static final String PREFS_FILE_NAME = "ptel_prefs";
    public static final String PREFS_EXP_DAYS_ALARM = "exp_days_alarm";
    public static final String PREFS_FIRST_RUN_COMPLETE = "first_run_complete";
    public static final String PREFS_LOW_BALANCE_ALARM = "exp_low_bal_alarm";

    public static final String SMS_BALANCE_REQUEST_NUMBER = "7801";
    public static final String SMS_BALANCE_REQUEST_COMMAND = "BAL";

    public static final String REGEX_CURRENCY = "^([+-]?\\d*[\\.,]?\\d{0,2})$";
}
