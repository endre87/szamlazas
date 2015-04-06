package com.endreit.invoice.main;

import com.endreit.invoice.inputparameters.SalaryParamsPropertyFileImpl;
import com.endreit.invoice.model.SalaryModel;
import junit.framework.Assert;
import org.junit.Test;

public class SalaryCalculatorTest
{
    @Test
    public void testBuildModel() throws Exception
    {
        float euroExchangeRate = 4.4424f;
        SalaryCalculator salaryCalculator = new SalaryCalculator(new SalaryParamsPropertyFileImpl(), euroExchangeRate);
        SalaryModel salaryModel = salaryCalculator.buildModel();
        float serviceValue = salaryModel.getValueRON();
        Assert.assertEquals("3860.78", String.format("%.2f", serviceValue));
    }
}
