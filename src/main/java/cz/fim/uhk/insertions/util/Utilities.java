package cz.fim.uhk.insertions.util;

import java.util.Calendar;
import java.util.Date;

public  class Utilities {

    /**
     * Get Date of today
     * @return Returns only Date without time
     */
    public  static Date getCurrentDate() {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return cal.getTime();
    }

    public static Date getExpiredDate(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.MONTH,1);
        return cal.getTime();
    }
}
