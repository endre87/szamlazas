package com.endreit.invoice.main;

import com.endreit.invoice.inputparameters.ISalaryParams;
import com.endreit.invoice.model.SalaryModel;

import java.util.logging.Logger;

public class SalaryCalculator
{
    private static final Logger LOGGER = Logger.getLogger(SalaryCalculator.class.getName());

    private final float euroExchangeValue;
    private final int accountingFeeRON;
    private final int cmSalaryRON;
    private final int totalSalaryEUR;
    private final int profitTaxPercent;
    private final int smeTaxPercent;

    private float startValueEUR;
    private float totalValueRON;

    public SalaryCalculator(ISalaryParams salaryParams, float euroExchangeRate)
    {
        this.euroExchangeValue = euroExchangeRate;
        this.accountingFeeRON = salaryParams.getAccountingFeeRON();
        this.cmSalaryRON = salaryParams.getCMSalaryRON();
        this.totalSalaryEUR = salaryParams.getTotalSalaryEUR();
        this.profitTaxPercent = salaryParams.getProfitTaxPercent();
        this.smeTaxPercent = salaryParams.getSMETaxPercent();
    }

    private void calculateSalary()
    {
        LOGGER.info("Calculating salary ...");

        startValueEUR = totalSalaryEUR - cmSalaryRON / euroExchangeValue;
        float accountingEUR = accountingFeeRON / euroExchangeValue;
        float profitEUR = startValueEUR / ((float) (100 - profitTaxPercent) / 100);
        float totalEUR = (accountingEUR + profitEUR) / ((float) (100 - smeTaxPercent) / 100);
        totalValueRON = totalEUR * euroExchangeValue;

        LOGGER.info(String.format("Start value = EUR %.2f", startValueEUR));
        LOGGER.info(String.format("Total value = RON %.2f", totalValueRON));
    }

    public SalaryModel buildModel()
    {
        calculateSalary();

        SalaryModel salaryModel = new SalaryModel();
        salaryModel.setStartValueEUR(startValueEUR);
        salaryModel.setValueRON(totalValueRON);
        salaryModel.setAccountingFeeRON(accountingFeeRON);
        salaryModel.setEuroExchangeValue(euroExchangeValue);
        salaryModel.setTotalSalaryEUR(totalSalaryEUR);
        salaryModel.setCMSalaryRON(cmSalaryRON);
        return salaryModel;
    }
}
