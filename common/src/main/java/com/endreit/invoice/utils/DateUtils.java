package com.endreit.invoice.utils;

import java.util.Calendar;
import java.util.Date;

public final class DateUtils
{
    private static Calendar getExecutionCalendar(Date executionDate)
    {
        final Calendar instance = Calendar.getInstance();
        instance.setTime(executionDate);
        return instance;
    }

    public static Date getDateFor(Date executionDate, int day)
    {
        Calendar calendar = getExecutionCalendar(executionDate);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    public static int getDayOfMonth(Date date)
    {
        Calendar calendar = getExecutionCalendar(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getMonth(Date date)
    {
        Calendar calendar = getExecutionCalendar(date);
        return calendar.get(Calendar.MONTH);
    }

    public static int getYear(Date date) {
        Calendar calendar = getExecutionCalendar(date);
        return calendar.get(Calendar.YEAR);
    }

    public static Date getPreviousMonthDate(Date executionDate, int day)
    {
        Calendar calendar = getExecutionCalendar(executionDate);
        calendar.roll(Calendar.MONTH, false);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    public static Date getLastWorkingDayOfMonth(Date executionDate, int rollWorkingDayOffset) {
        Calendar c = Calendar.getInstance();
        c.setTime(executionDate);
        // get previous month
        c.roll(Calendar.MONTH, false);
        // get last day of month
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        int currentOffset = rollWorkingDayOffset;
        while (isWeekend(c) || currentOffset > 0) {
            c.roll(Calendar.DAY_OF_MONTH, false);
            if (!isWeekend(c)) {
                currentOffset--;
            }
        }
        return c.getTime();
    }

    public static boolean isWeekend(Calendar c) {
        return c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    public static Date getPreviousMonthLastDay(Date executionDate)
    {
        Calendar c = getExecutionCalendar(executionDate);
        c.roll(Calendar.MONTH, false);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    public static Date getDate(int year, int month, int dayOfMonth)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        return calendar.getTime();
    }

//    public static Date today()
//    {
//        final Calendar today = Calendar.getInstance();
//        clearMilliseconds(today);
//        clearSeconds(today);
//        clearMinutes(today);
//        clearHours(today);
//        return today.getTime();
//    }
//
//    private static void clearHours(Calendar date)
//    {
//        date.set(Calendar.HOUR_OF_DAY, 0);
//    }
//
//    private static void clearMinutes(Calendar date)
//    {
//        date.set(Calendar.MINUTE, 0);
//    }
//
//    private static void clearSeconds(Calendar date)
//    {
//        date.set(Calendar.SECOND, 0);
//    }
//
//    private static void clearMilliseconds(Calendar date)
//    {
//        date.set(Calendar.MILLISECOND, 0);
//    }

//    public static Date minusDay(Date date, int i)
//    {
//        final Calendar result = Calendar.getInstance();
//        result.setTime(date);
//        final int dayOfYear = result.get(Calendar.DAY_OF_YEAR);
//        result.set(Calendar.DAY_OF_YEAR, dayOfYear - i);
//        return result.getTime();
//    }
}
