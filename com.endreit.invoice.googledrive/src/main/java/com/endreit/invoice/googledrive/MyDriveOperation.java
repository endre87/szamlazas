package com.endreit.invoice.googledrive;

import com.google.api.client.http.HttpTransport;
import com.google.api.services.drive.Drive;

public interface MyDriveOperation<D>
{
    D execute(Drive drive, HttpTransport httpTransport) throws Exception;
}
