package com.endreit.invoice.inputparameters;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class PropertiesReader
{
    private final String resourceName;
    private final Properties props = new Properties();
    private boolean loaded;

    public PropertiesReader(String resourceName)
    {
        this.resourceName = resourceName;
    }

    private void load()
    {
        try
        {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream stream = loader.getResourceAsStream(resourceName);
            props.load(stream);
            loaded = true;
        } catch (IOException e)
        {
            throw new RuntimeException("Cannot load resource property file: " + resourceName, e);
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
