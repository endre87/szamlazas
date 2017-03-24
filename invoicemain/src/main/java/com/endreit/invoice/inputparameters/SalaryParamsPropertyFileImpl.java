package com.endreit.invoice.inputparameters;

import java.io.File;

public class SalaryParamsPropertyFileImpl implements ISalaryParams
{
    private static final String PROPERTY_EXCHANGE_DAY = "ExchangeDay";

    private static final String PROPERTY_TOTAL_SALARY_EUR = "TotalSalaryEUR";

    private static final String PROPERTY_CM_SALARY_RON = "CMSalaryRON";

    private static final String PROPERTY_ACCOUNTING_FEE_RON = "AccountingFeeRON";

    private static final String PROPERTY_SME_TAX_PERCENT = "SMETaxPercent";

    private static final String PROPERTY_PROFIT_TAX_PERCENT = "ProfitTaxPercent";

    private final PropertiesReader reader;

    public SalaryParamsPropertyFileImpl(File directory, String propFileName)
    {
        this.reader = new FilePropertiesReader(directory, propFileName);
    }

    public String getInvoiceExchangeDay()
    {
        return reader.getAsString(PROPERTY_EXCHANGE_DAY);
    }

    public int getTotalSalaryEUR()
    {
        return Integer.valueOf(reader.getAsString(PROPERTY_TOTAL_SALARY_EUR));
    }

    public int getCMSalaryRON()
    {
        return Integer.valueOf(reader.getAsString(PROPERTY_CM_SALARY_RON));
    }

    public int getAccountingFeeRON()
    {
        return Integer.valueOf(reader.getAsString(PROPERTY_ACCOUNTING_FEE_RON));
    }

    public int getSMETaxPercent()
    {
        return Integer.valueOf(reader.getAsString(PROPERTY_SME_TAX_PERCENT));
    }

    public int getProfitTaxPercent()
    {
        return Integer.valueOf(reader.getAsString(PROPERTY_PROFIT_TAX_PERCENT));
    }

}
