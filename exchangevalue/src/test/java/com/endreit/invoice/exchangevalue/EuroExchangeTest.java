package com.endreit.invoice.exchangevalue;

import com.endreit.invoice.exchangevalue.api.ExchangeValue;
import com.endreit.invoice.exchangevalue.impl.EuroExchangeRate;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class EuroExchangeTest
{
    @Test
    public void testEuroExchangeApi() throws Exception
    {
        final Date today = new Date();
        final float euroValue = ExchangeValue.getEuroValue(today);
        System.out.printf("Date: " + today + "\tEURO = " + euroValue);
    }

    @Test
    public void testEuroExchangeValue() throws Exception
    {
        Date today = Calendar.getInstance().getTime();
        float exchangeRate = EuroExchangeRate.getInstance().getExchangeRate(today);
        System.out.printf("Date: " + today + "\tEURO = " + exchangeRate);
    }
}
