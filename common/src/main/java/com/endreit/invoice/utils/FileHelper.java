package com.endreit.invoice.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public final class FileHelper
{
    private static final Logger LOGGER = Logger.getLogger(FileHelper.class.getName());

    public static File createFolderInsideTemporaryDir(String folder, boolean deleteFirstIfAlreadyExists)
    {
        File tempDirectory = FileUtils.getTempDirectory();
        return createFolder(tempDirectory.getAbsolutePath(), folder, deleteFirstIfAlreadyExists);
    }

    public static File createFolder(String parentFolder, String folder, boolean deleteFirstIfAlreadyExists)
    {
        File parent = new File(parentFolder);
        if (!parent.exists())
        {
            throw new RuntimeException("Parent folder doesn't exists!");
        }
        File folderToCreate = new File(parent, folder);
        if (!folderToCreate.exists())
        {
            createUnExistingFolder(folderToCreate);
        } else if (deleteFirstIfAlreadyExists)
        {
            deleteExistingFolder(folderToCreate);
            createUnExistingFolder(folderToCreate);
        }
        return folderToCreate;
    }

    private static void deleteExistingFolder(File folder)
    {
        try
        {
            FileUtils.deleteDirectory(folder);
        } catch (IOException e)
        {
            throw new RuntimeException("Cannot delete folder: " + folder.getAbsolutePath(), e);
        }
    }

    private static File createUnExistingFolder(File folder)
    {
        if (!folder.mkdir())
        {
            throw new RuntimeException("Cannot create folder: " + folder.getAbsolutePath());
        }
        return folder;
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
