package com.endreit.invoice.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

public final class FileHelper
{
    private static final Logger LOGGER = Logger.getLogger(FileHelper.class.getName());

    private static final String INVOICE_TEMP_DIRECTORY_NAME = "Invoice_v1";

    private static final String OUTPUT_FILE_NAME = "%sEND_INVOICE.xls";

    private static final File INVOICE_TEMP_DIRECTORY;

    static
    {
        File tempDirectory = FileUtils.getTempDirectory();
        File invoiceTempDirectory = new File(tempDirectory, INVOICE_TEMP_DIRECTORY_NAME);
        invoiceTempDirectory.mkdir();
        INVOICE_TEMP_DIRECTORY = invoiceTempDirectory;
    }

    public static String extractTemplate(String templateFileName)
    {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        final URL template = loader.getResource(templateFileName);

        File destination = new File(INVOICE_TEMP_DIRECTORY, templateFileName);
        try
        {
            FileUtils.copyURLToFile(template, destination);
            return destination.getAbsolutePath();
        } catch (IOException e)
        {
            String msg = String.format("Cannot copy URL %s to destination %s", template.getPath(), destination.getAbsolutePath());
            LOGGER.severe(msg);
            throw new RuntimeException(msg, e);
        }
    }

    public static String setupDestinationFile(String destinationFolderName)
    {
        String outputFolderName = destinationFolderName.replace("/", "_");
        File destinationFolder = new File(INVOICE_TEMP_DIRECTORY, outputFolderName);
        destinationFolder.mkdir();
        String destinationFileName = String.format(OUTPUT_FILE_NAME, outputFolderName);
        return new File(destinationFolder, destinationFileName).getAbsolutePath();
    }

    public static void copyFileToBaseDirectory(String outputFilePath)
    {
        File baseDir = new File(".");
        try
        {
            FileUtils.copyFileToDirectory(new File(outputFilePath), baseDir);
        } catch (IOException e)
        {
            String msg = String.format("Cannot copy file %s top directory %s", outputFilePath, baseDir.getAbsolutePath());
            LOGGER.severe(msg);
            throw new RuntimeException(msg, e);
        }
    }
}
