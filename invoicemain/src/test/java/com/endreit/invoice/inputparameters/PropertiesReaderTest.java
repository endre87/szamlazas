package com.endreit.invoice.inputparameters;

import org.junit.Test;

import java.util.Set;

public class PropertiesReaderTest
{
    PropertiesReader settingsReader = new PropertiesReader("settings.properties");

    @Test
    public void testPropertyReader() throws Exception
    {
        final Set<String> allPropertyNames = settingsReader.getAllPropertyNames();
        for (String propertyName : allPropertyNames)
        {
            Object testValue = settingsReader.get(propertyName);
            System.out.println(String.format("%s= %s", propertyName, testValue));
        }
    }

}
