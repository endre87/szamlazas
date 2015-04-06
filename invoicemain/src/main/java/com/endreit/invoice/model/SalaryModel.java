package com.endreit.invoice.model;

import java.util.Date;

public class SalaryModel extends BaseModel
{
    private float startValueEUR;
    private float valueRON;
    private float accountingFeeRON;
    private float euroExchangeValue;
    private int totalSalaryEUR;
    private int cMSalaryRON;
    private Date exchangeDate;

    public float getAccountingFeeRON()
    {
        return accountingFeeRON;
    }

    public void setAccountingFeeRON(float accountingFeeRON)
    {
        this.accountingFeeRON = accountingFeeRON;
    }

    public float getEuroExchangeValue()
    {
        return euroExchangeValue;
    }

    public void setEuroExchangeValue(float euroExchangeValue)
    {
        this.euroExchangeValue = euroExchangeValue;
    }

    public int getTotalSalaryEUR()
    {
        return totalSalaryEUR;
    }

    public void setTotalSalaryEUR(int totalSalaryEUR)
    {
        this.totalSalaryEUR = totalSalaryEUR;
    }

    public int getCMSalaryRON()
    {
        return cMSalaryRON;
    }

    public void setCMSalaryRON(int cMSalaryRON)
    {
        this.cMSalaryRON = cMSalaryRON;
    }

    public Date getExchangeDate()
    {
        return exchangeDate;
    }

    public void setExchangeDate(Date exchangeDate)
    {
        this.exchangeDate = exchangeDate;
    }

    public float getValueRON()
    {
        return valueRON;
    }

    public void setValueRON(float valueRON)
    {
        this.valueRON = valueRON;
    }

    public void setStartValueEUR(float startValueEUR)
    {
        this.startValueEUR = startValueEUR;
    }

    public float getStartValueEUR()
    {
        return startValueEUR;
    }
}
