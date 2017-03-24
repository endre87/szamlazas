package com.endreit.invoice.model;

import com.endreit.invoice.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InvoiceModel extends BaseModel
{
    private final Integer invoiceNumber;
    private final Date invoiceDate;
    private final Date serviceDate;
    private final String invoiceDateFormat;
    private Integer invoiceYear;
    private String invoiceStringNumber;
    private String serviceStringDate;
    private String expenseStringNumber;
    private String expenseStringDate;

    public InvoiceModel(Date executionDate, Date invoiceDate, Date serviceDate, String invoiceDateFormat, String expenseDateFormat) {
        this.invoiceYear = DateUtils.getYear(executionDate);
        this.invoiceNumber = DateUtils.getMonth(executionDate) + 1; // public static final int APRIL = 3; if executed in april we need to display 004
        this.invoiceDate = invoiceDate;
        this.serviceDate = serviceDate;
        this.invoiceDateFormat = invoiceDateFormat;
        setInvoiceStringNumber();
        setServiceStringDate();
        setExpenseStringNumber();
        setExpenseStringDate(expenseDateFormat);
    }

    private void setExpenseStringDate(String expenseDateFormat)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(expenseDateFormat, new Locale("ro"));
        expenseStringDate = dateFormat.format(serviceDate);
    }

    private void setExpenseStringNumber()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(String.format("%02d/yyyy", invoiceNumber));
        expenseStringNumber = dateFormat.format(invoiceDate);
    }

    private void setServiceStringDate()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(invoiceDateFormat);
        serviceStringDate = dateFormat.format(serviceDate);
    }

    private void setInvoiceStringNumber()
    {
        invoiceStringNumber = String.format("%03d", invoiceNumber);
    }

    public Date getInvoiceDate()
    {
        return invoiceDate;
    }

    public String getInvoiceStringNumber()
    {
        return invoiceStringNumber;
    }

    public String getServiceStringDate()
    {
        return serviceStringDate;
    }

    public String getExpenseStringNumber()
    {
        return expenseStringNumber;
    }

    public String getExpenseStringDate()
    {
        return expenseStringDate;
    }

    public Integer getInvoiceYear() {
        return invoiceYear;
    }
}
