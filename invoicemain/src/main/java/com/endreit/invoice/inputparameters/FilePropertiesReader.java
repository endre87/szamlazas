package com.endreit.invoice.inputparameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FilePropertiesReader extends PropertiesReader
{
    private final File directory;

    public FilePropertiesReader(File directory, String fileName)
    {
        super(fileName);
        this.directory = directory;
    }

    @Override
    protected InputStream getInputStream()
    {
        File file = new File(directory, fileName);
        try
        {
            return new FileInputStream(file);
        } catch (FileNotFoundException e)
        {
            throw new RuntimeException("Cannot find input file: " + file.getAbsolutePath(), e);
        }
    }
}
