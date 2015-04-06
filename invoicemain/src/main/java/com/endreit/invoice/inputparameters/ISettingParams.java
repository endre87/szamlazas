package com.endreit.invoice.inputparameters;

public interface ISettingParams
{
    /**
     * @return 1- 31 day of month
     */
    public int getInvoiceDay();

    /**
     * @return 1- 31 day of month
     */
    public int getInvoiceServiceDay();

    public String getInvoiceServiceDateFormat();

    public String getExpenseDateFormat();
}
