package com.endreit.invoice.googledrive.operations;

import com.endreit.invoice.googledrive.MyDrive;
import com.endreit.invoice.googledrive.MyDriveOperation;
import com.google.api.client.http.HttpTransport;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class FileOperations
{

    @Nullable
    public static File getFile(final File parentFolder, final String fileTitle)
    {
        return MyDrive.execute(File.class, new MyDriveOperation<File>()
        {
            @Override
            public File execute(Drive drive, HttpTransport httpTransport) throws Exception
            {
                return getFile(drive, parentFolder, fileTitle);
            }
        });
    }

    @Nullable
    public static File getFile(Drive drive, File parentFolder, String fileTitle) throws IOException
    {
        Drive.Files.List request = drive.files().list();
        String q = String.format("trashed = false and '%s' in parents and title = '%s'", parentFolder.getId(), fileTitle);
        request.setQ(q);
        List<File> items = request.execute().getItems();

        if (items.isEmpty())
        {
            return null;
        }
        return items.get(0);
    }

    @SuppressWarnings(value = "unchecked")
    public static List<File> retrieveAllFiles()
    {
        return MyDrive.execute(List.class, new MyDriveOperation<List>()
        {
            @Override
            public List<File> execute(Drive drive, HttpTransport httpTransport) throws Exception
            {
                List<File> result = new ArrayList<File>();
                Drive.Files.List request = drive.files().list();
                request.setCorpus("domain");

                do
                {
                    FileList files = request.execute();
                    result.addAll(files.getItems());
                    request.setPageToken(files.getNextPageToken());
                } while (request.getPageToken() != null && request.getPageToken().length() > 0);

                return result;
            }
        });

    }
}
