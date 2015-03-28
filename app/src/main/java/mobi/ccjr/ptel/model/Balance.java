package mobi.ccjr.ptel.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import mobi.ccjr.ptel.utils.DateCalculation;

public class Balance {
    private String balance;
    private String expiry;

    public Balance(String balance, String expiry) {
        this.balance = balance;
        this.expiry = expiry;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getExpiry() {
        return expiry;
    }

    public Date getExpiryAsDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        try {
            return dateFormat.parse(expiry);
        }
        catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public long daysUntilExpiry() {
        Date date1 = new Date();
        Date date2 = getExpiryAsDate();
        return DateCalculation.daysBetweenDates(date1, date2);
    }
}
