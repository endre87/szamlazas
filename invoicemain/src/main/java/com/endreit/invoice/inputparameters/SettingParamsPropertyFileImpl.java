package com.endreit.invoice.inputparameters;

import java.io.File;

public class SettingParamsPropertyFileImpl implements ISettingParams
{
    private static final String PROPERTY_INVOICE_DAY = "InvoiceDay";

    private static final String PROPERTY_INVOICE_SERVICE_DAY = "InvoiceServiceDay";

    private static final String PROPERTY_EXPENSE_DATE_FORMAT = "ExpenseDateFormat";

    private final PropertiesReader reader;

    public SettingParamsPropertyFileImpl(File directory, String propFileName)
    {
        this.reader = new FilePropertiesReader(directory, propFileName);
    }

    public String getInvoiceDay()
    {
        return reader.getAsString(PROPERTY_INVOICE_DAY);
    }

    public String getInvoiceServiceDay()
    {
        return reader.getAsString(PROPERTY_INVOICE_SERVICE_DAY);
    }

    public String getExpenseDateFormat()
    {
        return reader.getAsString(PROPERTY_EXPENSE_DATE_FORMAT);
    }
}
