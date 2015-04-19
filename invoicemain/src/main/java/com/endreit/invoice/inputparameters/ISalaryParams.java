package com.endreit.invoice.inputparameters;

public interface ISalaryParams
{
    public static final String LAST_DAY_PROPERTY = "LAST_DAY";

    /**
     * @return 1- 31 day of month or literals: LAST_DAY
     */
    public String getInvoiceExchangeDay();

    public int getTotalSalaryEUR();

    public int getCMSalaryRON();

    public int getAccountingFeeRON();

    public int getSMETaxPercent();

    public int getProfitTaxPercent();
}
