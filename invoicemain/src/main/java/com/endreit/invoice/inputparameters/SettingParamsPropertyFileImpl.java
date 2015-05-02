package com.endreit.invoice.inputparameters;

import java.io.File;

public class SettingParamsPropertyFileImpl implements ISettingParams
{
    public static final String PROPERTY_INVOICE_DAY = "InvoiceDay";

    public static final String PROPERTY_INVOICE_SERVICE_DAY = "InvoiceServiceDay";

    public static final String PROPERTY_INVOICE_SERVICE_DATE_FORMAT = "InvoiceServiceDateFormat";

    public static final String PROPERTY_EXPENSE_DATE_FORMAT = "ExpenseDateFormat";

    private final PropertiesReader reader;

    public SettingParamsPropertyFileImpl(File directory, String propFileName)
    {
        this.reader = new FilePropertiesReader(directory, propFileName);
    }

    @Override
    public int getInvoiceDay()
    {
        return Integer.valueOf(reader.getAsString(PROPERTY_INVOICE_DAY));
    }

    @Override
    public int getInvoiceServiceDay()
    {
        return Integer.valueOf(reader.getAsString(PROPERTY_INVOICE_SERVICE_DAY));
    }

    @Override
    public String getInvoiceServiceDateFormat()
    {
        return reader.getAsString(PROPERTY_INVOICE_SERVICE_DATE_FORMAT);
    }

    @Override
    public String getExpenseDateFormat()
    {
        return reader.getAsString(PROPERTY_EXPENSE_DATE_FORMAT);
    }
}
