package com.endreit.invoice.cmdline;

import com.endreit.invoice.logger.MyLogger;
import com.endreit.invoice.main.Application;
import com.endreit.invoice.utils.FileHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    private static final String COPY_TO_BASE_DIR_FLAG = "copyToBaseDir";

    private static final String NO_UPLOAD_TO_GOOGLE_DRIVE_TAG = "noUploadToGoogleDrive";

    private static boolean copyOutputToBaseDir = false;
    private static boolean noUploadToGoogleDrive = false;
    private static Date executionDate = new Date();

    public static void main(String[] args) throws Exception {
        initParams(args);
        MyLogger.setup();

        boolean uploadToDrive = !noUploadToGoogleDrive;
        String outputFilePath = Application.getInstance().run(executionDate, uploadToDrive);

        if (copyOutputToBaseDir) {
            FileHelper.copyFileToBaseDirectory(outputFilePath);
        }
    }

    private static void initParams(String[] args) {
        if (args != null) {
            if (args.length > 0) {
                for (String arg : args) {
                    if (arg.equals(NO_UPLOAD_TO_GOOGLE_DRIVE_TAG)) {
                        noUploadToGoogleDrive = true;
                    } else if (arg.equals(COPY_TO_BASE_DIR_FLAG)) {
                        copyOutputToBaseDir = true;
                    } else if (arg.startsWith("-D")) {
                        executionDate = getExecutionDate(arg.substring(2));
                    }
                }
            }
        }
    }

    private static Date getExecutionDate(String stringDate) {
        String pattern = "dd.MM.yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.parse(stringDate);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid pattern! Date pattern to use: " + pattern);
        }
    }

}
