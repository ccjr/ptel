package mobi.ccjr.ptel.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.util.Date;

import mobi.ccjr.ptel.model.Balance;

public class BalanceDAO {
    private Context context;
    private BalanceDbHelper dbHelper;

    public BalanceDAO(Context context) {
        this.context = context;
    }

    public long save(Balance balance) {
        SQLiteDatabase db = getDbHelper(context).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BalanceContract.BalanceEntry.COLUMN_NAME_BALANCE, balance.getBalance());
        values.put(BalanceContract.BalanceEntry.COLUMN_NAME_EXPIRY, balance.getExpiry());

        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        Date date = new Date();
        values.put(BalanceContract.BalanceEntry.COLUMN_NAME_CREATED_AT, dateFormat.format(date));

        return db.insert(BalanceContract.BalanceEntry.TABLE_NAME, null, values);
    }

    public Balance findMostRecent() {
        Balance balance = null;
        SQLiteDatabase db = getDbHelper(context).getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT balance, expiry FROM balances ORDER BY _ID DESC LIMIT 1 ",
                null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            balance = new Balance(cursor.getString(0), cursor.getString(1));
        }
        cursor.close();

        return balance;
    }

    private BalanceDbHelper getDbHelper(Context context) {
        if (dbHelper == null) {
            dbHelper = new BalanceDbHelper(context);
        }
        return dbHelper;
    }

}
