package com.endreit.invoice.inputparameters;

public interface ISalaryParams
{
    String LAST_DAY_PROPERTY = "LAST_DAY";

    /**
     * @return 1- 31 day of month or literals: LAST_DAY
     */
    String getInvoiceExchangeDay();

    int getTotalSalaryEUR();

    int getCMSalaryRON();

    int getAccountingFeeRON();

    int getSMETaxPercent();

    int getProfitTaxPercent();
}
