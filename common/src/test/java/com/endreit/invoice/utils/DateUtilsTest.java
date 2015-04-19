package com.endreit.invoice.utils;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class DateUtilsTest
{
    @Test
    public void testGetPreviousMonthLastDay() throws Exception
    {
        doTestLastDay(2015, 1, 31);
        doTestLastDay(2015, 2, 28);
        doTestLastDay(2015, 3, 31);
        doTestLastDay(2015, 4, 30);
    }

    private void doTestLastDay(int year, int month, int expectedLastDay)
    {
        Date date = DateUtils.getDate(year, month, 1);
        Date previousMonthDate = DateUtils.getPreviousMonthLastDay(date);
        int previousMonthLastDay = getDay(previousMonthDate);
        Assert.assertEquals(expectedLastDay, previousMonthLastDay);
    }

    private int getDay(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }
}
