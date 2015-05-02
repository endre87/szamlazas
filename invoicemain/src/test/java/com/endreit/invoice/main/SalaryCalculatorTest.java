package com.endreit.invoice.main;

import com.endreit.invoice.inputparameters.ISalaryParams;
import com.endreit.invoice.model.SalaryModel;
import junit.framework.Assert;
import org.junit.Test;

public class SalaryCalculatorTest
{
    @Test
    public void testBuildModel() throws Exception
    {
        float euroExchangeRate = 4.4424f;
        SalaryCalculator salaryCalculator = new SalaryCalculator(new ISalaryParams()
        {
            @Override
            public String getInvoiceExchangeDay()
            {
                return "LAST_DAY";
            }

            @Override
            public int getTotalSalaryEUR()
            {
                return 500;
            }

            @Override
            public int getCMSalaryRON()
            {
                return 500;
            }

            @Override
            public int getAccountingFeeRON()
            {
                return 200;
            }

            @Override
            public int getSMETaxPercent()
            {
                return 3;
            }

            @Override
            public int getProfitTaxPercent()
            {
                return 16;
            }
        }, euroExchangeRate);
        SalaryModel salaryModel = salaryCalculator.buildModel();
        float serviceValue = salaryModel.getValueRON();
        Assert.assertEquals("2318.61", String.format("%.2f", serviceValue));
    }
}
