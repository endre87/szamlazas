package com.endreit.invoice.inputparameters;

import com.endreit.invoice.main.Processor;
import com.endreit.invoice.utils.DateUtils;

import org.junit.Test;

import java.util.Date;

public class PropertiesReaderTest
{
//    PropertiesReader settingsReader = new ResourcePropertiesReader("settings.properties");
//
//    @Test
//    public void testPropertyReader() throws Exception
//    {
//        final Set<String> allPropertyNames = settingsReader.getAllPropertyNames();
//        for (String propertyName : allPropertyNames)
//        {
//            Object testValue = settingsReader.get(propertyName);
//            System.out.println(String.format("%s= %s", propertyName, testValue));
//        }
//    }


    @Test public void testLastWorkingDayCalculator() throws Exception {
        String settingParam = "LAST_WORKING_DAY";
        Date date = DateUtils.getDate(2017, 3, 15); // 2017/Apr/15
        Date invoiceDate = Processor.getInvoiceDate(date, settingParam);
        assert DateUtils.getDayOfMonth(invoiceDate)==31;

        settingParam = "LAST_WORKING_DAY";
        date = DateUtils.getDate(2017, 2, 15); // 2017/March/15
        invoiceDate = Processor.getInvoiceDate(date, settingParam);
        assert DateUtils.getDayOfMonth(invoiceDate)==28;
    }

    @Test public void testLastWorkingDayWithOffsetCalculator() throws Exception {
        String settingParam = "LAST_WORKING_DAY-6";
        Date date = DateUtils.getDate(2017, 3, 15);
        Date invoiceDate = Processor.getInvoiceDate(date, settingParam);
        assert DateUtils.getDayOfMonth(invoiceDate)==23;

        settingParam = "LAST_WORKING_DAY-7";
        date = DateUtils.getDate(2017, 2, 15); // 2017/March/15
        invoiceDate = Processor.getInvoiceDate(date, settingParam);
        assert DateUtils.getDayOfMonth(invoiceDate)==17;
    }
}
