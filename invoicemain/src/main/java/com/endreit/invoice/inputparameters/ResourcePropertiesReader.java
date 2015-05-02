package com.endreit.invoice.inputparameters;

import java.io.InputStream;

public class ResourcePropertiesReader extends PropertiesReader
{
    public ResourcePropertiesReader(String fileName)
    {
        super(fileName);
    }

    @Override
    protected InputStream getInputStream()
    {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        return loader.getResourceAsStream(fileName);
    }
}
