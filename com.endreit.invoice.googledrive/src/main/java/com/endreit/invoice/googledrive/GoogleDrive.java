package com.endreit.invoice.googledrive;

import com.endreit.invoice.googledrive.operations.DownloadOperations;
import com.endreit.invoice.googledrive.operations.FolderOperations;
import com.endreit.invoice.googledrive.operations.UploadOperations;
import com.google.api.services.drive.model.File;

import java.util.logging.Logger;

public final class GoogleDrive
{
    private static final Logger LOGGER = Logger.getLogger(GoogleDrive.class.getName());

    private static final String GOOGLE_DRIVE_APP_FOLDER_NAME = "Invoice";

    private static final String GOOGLE_DRIVE_INPUT_FOLDER_NAME = "input";

    private static final String GOOGLE_DRIVE_OUTPUT_FOLDER_NAME = "generated";

    private static final GoogleDrive INSTANCE = new GoogleDrive();


    private final File rootAppFolder;

    private final File inputFolder;

    private final File outputFolder;

    private GoogleDrive()
    {
        this.rootAppFolder = FolderOperations.getFolder(GOOGLE_DRIVE_APP_FOLDER_NAME);
        this.inputFolder = FolderOperations.getFolder(rootAppFolder, GOOGLE_DRIVE_INPUT_FOLDER_NAME);
        this.outputFolder = FolderOperations.getFolder(rootAppFolder, GOOGLE_DRIVE_OUTPUT_FOLDER_NAME);
    }

    public static GoogleDrive getInstance()
    {
        return INSTANCE;
    }

    public void downloadInputParameters(java.io.File destinationFolder)
    {
        LOGGER.info("Downloading application input parameters from Google Drive...");
        DownloadOperations.downloadFolderFirstLevelContent(inputFolder, destinationFolder);
        LOGGER.info("Done");
    }

    public void uploadFile(java.io.File file)
    {
        LOGGER.info("Uploading generated invoice excel to Google Drive...");
        UploadOperations.uploadFileToFolder(file, outputFolder);
        LOGGER.info("Done");
    }
}
