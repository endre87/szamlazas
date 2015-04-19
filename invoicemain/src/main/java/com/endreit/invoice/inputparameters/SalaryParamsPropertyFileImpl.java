package com.endreit.invoice.inputparameters;

public class SalaryParamsPropertyFileImpl implements ISalaryParams
{
    public static final String PROPERTY_EXCHANGE_DAY = "ExchangeDay";

    public static final String PROPERTY_TOTAL_SALARY_EUR = "TotalSalaryEUR";

    public static final String PROPERTY_CM_SALARY_RON = "CMSalaryRON";

    public static final String PROPERTY_ACCOUNTING_FEE_RON = "AccountingFeeRON";

    public static final String PROPERTY_SME_TAX_PERCENT = "SMETaxPercent";

    public static final String PROPERTY_PROFIT_TAX_PERCENT = "ProfitTaxPercent";

    private final PropertiesReader reader = new PropertiesReader("salary.properties");

    @Override
    public String getInvoiceExchangeDay()
    {
        return reader.getAsString(PROPERTY_EXCHANGE_DAY);
    }

    @Override
    public int getTotalSalaryEUR()
    {
        return Integer.valueOf(reader.getAsString(PROPERTY_TOTAL_SALARY_EUR));
    }

    @Override
    public int getCMSalaryRON()
    {
        return Integer.valueOf(reader.getAsString(PROPERTY_CM_SALARY_RON));
    }

    @Override
    public int getAccountingFeeRON()
    {
        return Integer.valueOf(reader.getAsString(PROPERTY_ACCOUNTING_FEE_RON));
    }

    @Override
    public int getSMETaxPercent()
    {
        return Integer.valueOf(reader.getAsString(PROPERTY_SME_TAX_PERCENT));
    }

    @Override
    public int getProfitTaxPercent()
    {
        return Integer.valueOf(reader.getAsString(PROPERTY_PROFIT_TAX_PERCENT));
    }

}
