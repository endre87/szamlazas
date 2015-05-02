import com.endreit.invoice.googledrive.operations.FolderOperations;
import com.google.api.services.drive.model.File;
import org.junit.Test;

import java.util.List;

public class GoogleDriveTest
{
    @Test
    public void testName() throws Exception
    {
        File folder = FolderOperations.getFolder("Invoice");
        printFile(folder);
    }

//    @Test
//    public void testUpload() throws Exception
//    {
//        java.io.File file = new java.io.File("C:\\Users\\Endre\\AppData\\Local\\Temp\\InvoiceApp\\generated\\04_2015_invoice.xls");
//        GoogleDrive.getInstance().uploadFile(file);
//    }

    public static void printFiles(List<File> files)
    {
        for (File f : files)
        {
            printFile(f);
        }
    }

    private static void printFile(File f)
    {
        System.out.println(f.getTitle() + "\t type:" + f.getMimeType());
    }
}
