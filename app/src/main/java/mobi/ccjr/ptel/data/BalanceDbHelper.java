package mobi.ccjr.ptel.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BalanceDbHelper
        extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "balances.db";
    private static final String SQL_CREATE_BALANCES =
            "CREATE TABLE " + BalanceContract.BalanceEntry.TABLE_NAME + " (" +
            BalanceContract.BalanceEntry._ID + " INTEGER PRIMARY KEY," +
            BalanceContract.BalanceEntry.COLUMN_NAME_BALANCE + " NUMERIC NOT NULL, " +
            BalanceContract.BalanceEntry.COLUMN_NAME_EXPIRY + " TEXT NOT NULL, " +
            BalanceContract.BalanceEntry.COLUMN_NAME_CREATED_AT + " TEXT NOT NULL" +
            " )";

    public BalanceDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_BALANCES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
