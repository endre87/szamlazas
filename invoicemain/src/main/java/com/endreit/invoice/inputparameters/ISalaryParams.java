package com.endreit.invoice.inputparameters;

public interface ISalaryParams
{
    /**
     * @return 1- 31 day of month
     */
    public int getInvoiceExchangeDay();

    public int getTotalSalaryEUR();

    public int getCMSalaryRON();

    public int getAccountingFeeRON();

    public int getSMETaxPercent();

    public int getProfitTaxPercent();
}
