package com.endreit.invoice.googledrive.operations;

import com.endreit.invoice.googledrive.MyDrive;
import com.endreit.invoice.googledrive.MyDriveOperation;
import com.google.api.client.http.HttpTransport;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.ChildList;
import com.google.api.services.drive.model.ChildReference;
import com.google.api.services.drive.model.File;

import java.util.List;

public final class FolderOperations
{
    public static final String FOLDER_MIMETYPE = "application/vnd.google-apps.folder";

    public static File getFolder(final String folderName)
    {
        return MyDrive.execute(File.class, new MyDriveOperation<File>()
        {
            @Override
            public File execute(Drive drive, HttpTransport httpTransport) throws Exception
            {
                Drive.Files.List request = drive.files().list();
                String q = String.format("trashed = false and mimeType = '%s' and title = '%s'", FOLDER_MIMETYPE, folderName);
                request.setQ(q);
                List<File> items = request.execute().getItems();

                if (items.isEmpty())
                {
                    throw new RuntimeException(String.format("Folder '%s' was not found in google drive!", folderName));
                }
                return items.get(0);
            }
        });
    }

    public static File getFolder(final File parentFolder, final String childFolder)
    {
        return MyDrive.execute(File.class, new MyDriveOperation<File>()
        {
            @Override
            public File execute(Drive drive, HttpTransport httpTransport) throws Exception
            {
                Drive.Files.List request = drive.files().list();
                String q = String.format("trashed = false and '%s' in parents and mimeType = '%s' and title = '%s'", parentFolder.getId(), FOLDER_MIMETYPE, childFolder);
                request.setQ(q);
                List<File> items = request.execute().getItems();

                if (items.isEmpty())
                {
                    throw new RuntimeException(String.format("Folder '%s' was not found in google drive!", childFolder));
                }
                return items.get(0);
            }
        });
    }

    /**
     * @param folderId use "root" for top level
     * @return first level children of folder with folderId
     */
    @SuppressWarnings(value = "unchecked")
    public static List<ChildReference> getChildren(final String folderId)
    {
        return MyDrive.execute(List.class, new MyDriveOperation<List>()
        {
            @Override
            public List<ChildReference> execute(Drive drive, HttpTransport httpTransport) throws Exception
            {
                Drive.Children.List request = drive.children().list(folderId);

                ChildList children = request.execute();
                return children.getItems();
            }
        });
    }
}
