package com.endreit.invoice.exchangevalue.api;

import com.endreit.invoice.exchangevalue.impl.EuroExchangeRate;

import java.util.Date;

public abstract class ExchangeValue
{
    public static float getEuroValue(Date date)
    {
        try
        {
            return EuroExchangeRate.getInstance().getExchangeRate(date);
        } catch (Exception e)
        {
            throw new ExchangeValueException("Could not get euro exchange value", e);
        }
    }
}
