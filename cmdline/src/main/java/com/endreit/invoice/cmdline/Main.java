package com.endreit.invoice.cmdline;

import com.endreit.invoice.inputparameters.ISalaryParams;
import com.endreit.invoice.inputparameters.ISettingParams;
import com.endreit.invoice.inputparameters.SalaryParamsPropertyFileImpl;
import com.endreit.invoice.inputparameters.SettingParamsPropertyFileImpl;
import com.endreit.invoice.logger.MyLogger;
import com.endreit.invoice.main.Processor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        MyLogger.setup();

        ISettingParams settings = new SettingParamsPropertyFileImpl();
        ISalaryParams salaryParams = new SalaryParamsPropertyFileImpl();
        Processor p = new Processor(settings, salaryParams);

        Date executionDate = null;
        if (args != null && args.length == 1)
        {
            executionDate = getExecutionDate(args[0]);
        } else
        {
            executionDate = new Date();
        }
        p.execute(executionDate);
    }

    private static Date getExecutionDate(String stringDate)
    {
        String pattern = "dd.MM.yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try
        {
            return dateFormat.parse(stringDate);
        } catch (ParseException e)
        {
            throw new RuntimeException("Invalid pattern! Date pattern to use: " + pattern);
        }
    }

}
