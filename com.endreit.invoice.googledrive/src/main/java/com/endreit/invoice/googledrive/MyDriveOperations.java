package com.endreit.invoice.googledrive;

import com.google.api.client.googleapis.media.MediaHttpDownloader;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MyDriveOperations
{

    public void downloadTemplate(String toDirectory) throws IOException
    {
        Drive drive = MyDrive.getDrive();
        HttpTransport httpTransport = MyDrive.getHttpTransport();

        java.io.File parentDir = new java.io.File(toDirectory);
        if (!parentDir.exists() && !parentDir.mkdirs())
        {
            throw new IOException("Unable to create parent directory");
        }

        File uploadedFile = null;
        OutputStream out = new FileOutputStream(new java.io.File(parentDir, uploadedFile.getTitle()));

        MediaHttpDownloader downloader = new MediaHttpDownloader(httpTransport, drive.getRequestFactory().getInitializer());
//        downloader.setDirectDownloadEnabled(true);
//        downloader.setProgressListener(new FileDownloadProgressListener());
        downloader.download(new GenericUrl(uploadedFile.getDownloadUrl()), out);
    }

    public void downloadProperties(String toDirectory)
    {

    }
}
