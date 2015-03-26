package mobi.ccjr.ptel.data;

import android.provider.BaseColumns;

public class BalanceContract {
    public BalanceContract() {}

    /* Inner class that defines the table contents */
    public static abstract class BalanceEntry
            implements BaseColumns {
        public static final String TABLE_NAME = "balances";
        public static final String COLUMN_NAME_BALANCE = "balance";
        public static final String COLUMN_NAME_EXPIRY = "expiry";
        public static final String COLUMN_NAME_CREATED_AT = "created_at";
    }

}
