package com.endreit.invoice.main;

import com.endreit.invoice.exchangevalue.api.ExchangeValue;
import com.endreit.invoice.inputparameters.ISalaryParams;
import com.endreit.invoice.inputparameters.ISettingParams;
import com.endreit.invoice.model.InvoiceModel;
import com.endreit.invoice.model.SalaryModel;
import com.endreit.invoice.utils.DateUtils;
import com.endreit.invoice.utils.FileHelper;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Processor
{
    private static final Logger LOGGER = Logger.getLogger(Processor.class.getName());

    private static final String templateFileName = "templates/invoice.xls";

    private final ISettingParams settingParams;
    private final ISalaryParams salaryParams;

    public Processor(ISettingParams settings, ISalaryParams salaryParams)
    {
        this.settingParams = settings;
        this.salaryParams = salaryParams;
    }

    /**
     * Generates invoice for the previous month
     * Example of flow: today is 16 april 2015 (executionDate)
     * - we need to generate the invoice for the previous month (march)
     * - executionDate dependent input parameters:
     * 1. invoiceNumber = 003
     * 2. invoiceDate = xx/April/2015
     * 3. serviceDate = yy/April/2015
     * 4. serviceValue = calculating value based on previous months (march) exchange rate
     *
     * @param executionDate Default date should be today, but it is possible to execute it for another date.
     * @return Returns the output file path
     * @throws IOException
     * @throws InvalidFormatException
     */
    public String execute(Date executionDate) throws IOException, InvalidFormatException
    {
        Map beans = new HashMap();
        InvoiceModel invoice = buildInvoiceModel(executionDate);
        SalaryModel salary = buildSalaryModel(executionDate);
        beans.put("invoice", invoice);
        beans.put("salary", salary);

        String templatePath = FileHelper.extractTemplate(templateFileName);
        String destinationPath = FileHelper.setupDestinationFile(invoice.getExpenseStringNumber());

        LOGGER.info(String.format("Generating xls %s", destinationPath));
        new XLSTransformer().transformXLS(templatePath, beans, destinationPath);
        LOGGER.info("Done");
        return destinationPath;
    }


    private InvoiceModel buildInvoiceModel(Date executionDate)
    {
        int month = DateUtils.getMonth(executionDate);
        int invoiceNumber = month; // if executed in april -> month = 3 -> invoice number = 003
        Date invoiceDate = DateUtils.getDateFor(executionDate, settingParams.getInvoiceDay());
        Date serviceDate = DateUtils.getDateFor(executionDate, settingParams.getInvoiceServiceDay());
        String invoiceDateFormat = settingParams.getInvoiceServiceDateFormat();
        String expenseDateFormat = settingParams.getExpenseDateFormat();
        return new InvoiceModel(invoiceNumber, invoiceDate, serviceDate, invoiceDateFormat, expenseDateFormat);
    }

    private SalaryModel buildSalaryModel(Date executionDate)
    {
        String invoiceExchangeDay = salaryParams.getInvoiceExchangeDay();
        Date exchangeDate = null;
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
