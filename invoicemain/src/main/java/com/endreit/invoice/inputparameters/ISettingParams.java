package com.endreit.invoice.inputparameters;

public interface ISettingParams
{
    String LAST_WORKING_DAY = "LAST_WORKING_DAY";

    /**
     * @return 1- 31 day of month or literal: LAST_WORKING_DAY , LAST_WORKING_DAY-x (x is integer)
     */
    String getInvoiceDay();

    /**
     * @return 1- 31 day of month or literal: LAST_WORKING_DAY , LAST_WORKING_DAY-x (x is integer)
     */
    String getInvoiceServiceDay();

    String getInvoiceServiceDateFormat();

    String getExpenseDateFormat();
}
