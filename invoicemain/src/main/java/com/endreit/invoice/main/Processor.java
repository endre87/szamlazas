package com.endreit.invoice.main;

import com.endreit.invoice.exchangevalue.api.ExchangeValue;
import com.endreit.invoice.inputparameters.ISalaryParams;
import com.endreit.invoice.inputparameters.ISettingParams;
import com.endreit.invoice.model.InvoiceModel;
import com.endreit.invoice.model.SalaryModel;
import com.endreit.invoice.utils.DateUtils;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public abstract class Processor
{
    private static final Logger LOGGER = Logger.getLogger(Processor.class.getName());

    protected ISettingParams settingParams;
    protected ISalaryParams salaryParams;

    public Processor()
    {
        init();
    }

    protected abstract void init();

    protected abstract String getDestinationPath(String variable);

    protected abstract String getTemplatePath();

    /**
     * Generates invoice for the previous month
     * Example of flow: today is 16 april 2015 (executionDate)
     * - we need to generate the invoice for the previous month (march)
     * - executionDate dependent input parameters:
     * 0. invoiceYear = 2015
     * 1. invoiceNumber = 004
     * 2. invoiceDate = xx/April/2015
     * 3. serviceDate = yy/April/2015
     * 4. serviceValue = calculating value based on previous months (march) exchange rate
     *
     * @param executionDate Default date should be today, but it is possible to execute it for another date.
     * @return Returns the output file path
     * @throws IOException
     * @throws InvalidFormatException
     */
    protected String execute(Date executionDate) throws IOException, InvalidFormatException
    {
        Map beans = new HashMap();
        InvoiceModel invoice = buildInvoiceModel(executionDate);
        SalaryModel salary = buildSalaryModel(executionDate);
        beans.put("invoice", invoice);
        beans.put("salary", salary);

        String templatePath = getTemplatePath();
        String destinationPath = getDestinationPath(invoice.getExpenseStringNumber());

        LOGGER.info(String.format("Generating xls %s", destinationPath));
        new XLSTransformer().transformXLS(templatePath, beans, destinationPath);
        LOGGER.info("Done");
        return destinationPath;
    }


    private InvoiceModel buildInvoiceModel(Date executionDate)
    {
        return new InvoiceModel(executionDate, settingParams.getInvoiceDay(), settingParams.getInvoiceServiceDay(), settingParams.getInvoiceServiceDateFormat(),
                settingParams.getExpenseDateFormat());
    }

    private SalaryModel buildSalaryModel(Date executionDate)
    {
        String invoiceExchangeDay = salaryParams.getInvoiceExchangeDay();
        Date exchangeDate;
        if (ISalaryParams.LAST_DAY_PROPERTY.equals(invoiceExchangeDay))
        {
            exchangeDate = DateUtils.getPreviousMonthLastDay(executionDate);
        } else
        {
            int day = Integer.valueOf(invoiceExchangeDay);
            exchangeDate = DateUtils.getPreviousMonthDate(executionDate, day);
        }

        float euroExchangeRate = ExchangeValue.getEuroValue(exchangeDate);

        SalaryCalculator salaryCalculator = new SalaryCalculator(salaryParams, euroExchangeRate);
        SalaryModel salaryModel = salaryCalculator.buildModel();
        salaryModel.setExchangeDate(exchangeDate);
        return salaryModel;
    }
}
