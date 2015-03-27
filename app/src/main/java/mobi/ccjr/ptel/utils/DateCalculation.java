package mobi.ccjr.ptel.utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateCalculation {

    /**
     * Get a diff between two dates
     * @param date1 the oldest date
     * @param date2 the newest date
     * @param timeUnit the unit in which you want the diff
     * @return the diff value, in the provided unit
     * Source: http://stackoverflow.com/questions/1555262/calculating-the-difference-between-two-java-date-instances
     */
    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillis = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillis, TimeUnit.MILLISECONDS);
    }

    /**
     * Number of days between 2 dates
     * @param date1
     * @param date2
     * @return
     */
    public static long daysBetweenDates(Date date1, Date date2) {
        return getDateDiff(date1, date2, TimeUnit.DAYS);
    }
}
