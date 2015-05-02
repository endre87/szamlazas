package com.endreit.invoice.googledrive.operations;

import com.endreit.invoice.googledrive.MyDrive;
import com.endreit.invoice.googledrive.MyDriveOperation;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.Preconditions;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.ParentReference;

import java.util.Arrays;

public final class UploadOperations
{
    /**
     * Uploads the file to a given folder in Google Drive. It will update the file if already exists.
     *
     * @param file              file to upload
     * @param destinationFolder Google Drive destination folder
     */
    public static void uploadFileToFolder(final java.io.File file, final File destinationFolder)
    {
        Preconditions.checkArgument(file.exists(), String.format("File '%s' does not exist!", file.getAbsolutePath()));

        MyDrive.execute(File.class, new MyDriveOperation<File>()
        {
            @Override
            public File execute(Drive drive, HttpTransport httpTransport) throws Exception
            {
                String parentId = destinationFolder.getId();
                String mimeType = "application/vnd.ms-excel";
                String title = file.getName();

                // File's metadata.
                File body = new File();
                body.setTitle(title);
                body.setDescription("Uploaded the generated invoice: " + title);
                body.setMimeType(mimeType);

                // Set the parent folder.
                if (parentId != null && parentId.length() > 0)
                {
                    body.setParents(Arrays.asList(new ParentReference().setId(parentId)));
                }

                // File's content.
                FileContent mediaContent = new FileContent(mimeType, file);

                // Insert or Update (if already exists) file.
                File uploadedFile;
                File existingFile = FileOperations.getFile(drive, destinationFolder, title);

                if (existingFile == null)
                {
                    uploadedFile = drive.files().insert(body, mediaContent).execute();
                } else
                {
                    uploadedFile = drive.files().update(existingFile.getId(), existingFile, mediaContent).execute();
                }

                return uploadedFile;
            }
        });
    }
}
