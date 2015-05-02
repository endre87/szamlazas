package com.endreit.invoice.googledrive.operations;

import com.endreit.invoice.googledrive.MyDrive;
import com.endreit.invoice.googledrive.MyDriveOperation;
import com.google.api.client.googleapis.media.MediaHttpDownloader;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.Preconditions;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public final class DownloadOperations
{
    public static void downloadFolderFirstLevelContent(final File sourceFolder, final java.io.File parentDir)
    {
        Preconditions.checkArgument(parentDir.exists(), String.format("Directory '%s' does not exist!", parentDir.getAbsolutePath()));

        MyDrive.execute(Void.class, new MyDriveOperation<Void>()
        {
            @Override
            public Void execute(Drive drive, HttpTransport httpTransport) throws Exception
            {
                FileList fileList = drive.files().list()
                        .setQ(String.format("trashed = false and '%s' in parents and mimeType != '%s'",
                                sourceFolder.getId(), FolderOperations.FOLDER_MIMETYPE))
                        .execute();

                for (File file : fileList.getItems())
                {
                    download(drive, httpTransport, file, parentDir);
                }
                return null;
            }
        });
    }

    public static void download(Drive drive, HttpTransport httpTransport, File fileToDownload, java.io.File parentDir) throws IOException
    {
        OutputStream out = new FileOutputStream(new java.io.File(parentDir, fileToDownload.getTitle()));

        MediaHttpDownloader downloader = new MediaHttpDownloader(httpTransport, drive.getRequestFactory().getInitializer());
//        downloader.setDirectDownloadEnabled(true);
//        downloader.setProgressListener(new FileDownloadProgressListener());
        downloader.download(new GenericUrl(fileToDownload.getDownloadUrl()), out);
    }

    public static void downloadFile(final File fileToDownload, final java.io.File parentDir)
    {
        Preconditions.checkArgument(parentDir.exists(), String.format("Directory '%s' does not exist!", parentDir.getAbsolutePath()));

        MyDrive.execute(Void.class, new MyDriveOperation<Void>()
        {
            @Override
            public Void execute(Drive drive, HttpTransport httpTransport) throws Exception
            {
                download(drive, httpTransport, fileToDownload, parentDir);
                return null;
            }
        });
    }
}
