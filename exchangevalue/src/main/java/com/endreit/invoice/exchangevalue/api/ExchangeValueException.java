package com.endreit.invoice.exchangevalue.api;

public class ExchangeValueException extends RuntimeException
{
    public ExchangeValueException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
