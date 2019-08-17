package com.huben.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author koishi
 */
public class DateUtil {
    private static long millisecondsOfOneDay = 1000 * 60 * 60 * 24;

    public static java.sql.Date util2sql(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static Date today() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        return c.getTime();
    }

    public static Date monthBegin() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.DATE, 1);

        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static Date monthEnd() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        c.set(Calendar.DATE, 1);
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DATE, -1);

        return c.getTime();
    }

    public static int thisMonthTotalDay() {
        long lastDayMilliSeconds = monthEnd().getTime();
        long firstDayMilliSeconds = monthBegin().getTime();

        return (int) ((lastDayMilliSeconds - firstDayMilliSeconds) / millisecondsOfOneDay) + 1;
    }

    public static int thisMonthLeftDay() {
        long lastDayMilliSeconds = monthEnd().getTime();
        long toDayMilliSeconds = today().getTime();

        return (int) ((lastDayMilliSeconds - toDayMilliSeconds) / millisecondsOfOneDay) + 1;
    }
}
