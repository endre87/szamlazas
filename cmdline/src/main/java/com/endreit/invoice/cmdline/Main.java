package com.endreit.invoice.cmdline;

import com.endreit.invoice.logger.MyLogger;
import com.endreit.invoice.main.Application;
import com.endreit.invoice.utils.FileHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main
{
    private static final String COPY_TO_BASE_DIR_FLAG = "copyToBaseDir";

    private static boolean copyOutputToBaseDir = false;
    private static Date executionDate = new Date();

    public static void main(String[] args) throws Exception
    {
        initParams(args);
        MyLogger.setup();

        String outputFilePath = Application.getInstance().execute(executionDate);

        if (copyOutputToBaseDir)
        {
            FileHelper.copyFileToBaseDirectory(outputFilePath);
        }
    }

    private static void initParams(String[] args)
    {
        if (args != null)
        {
            if (args.length > 0)
            {
                copyOutputToBaseDir = COPY_TO_BASE_DIR_FLAG.equals(args[0]);
            }
            if (args.length > 1)
            {
                executionDate = getExecutionDate(args[1]);
            }
        }
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
