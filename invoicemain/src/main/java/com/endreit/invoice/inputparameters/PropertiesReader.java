package com.endreit.invoice.inputparameters;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public abstract class PropertiesReader
{
    protected final String fileName;
    protected final Properties props = new Properties();
    protected boolean loaded;

    public PropertiesReader(String fileName)
    {
        this.fileName = fileName;
    }

    protected abstract InputStream getInputStream();

    protected void load()
    {
        try
        {
            InputStream stream = getInputStream();
            props.load(stream);
            loaded = true;
        } catch (IOException e)
        {
            throw new RuntimeException("Cannot load resource property file: " + fileName, e);
        }
    }

    public Object get(String propertyName)
    {
        if (!loaded)
        {
            load();
        }
        return props.get(propertyName);
    }

    public String getAsString(String propertyName)
    {
        final Object value = get(propertyName);
        return value == null ? "" : value.toString();
    }

    public Set<String> getAllPropertyNames()
    {
        if (!loaded)
        {
            load();
        }
        return props.stringPropertyNames();
    }
}
