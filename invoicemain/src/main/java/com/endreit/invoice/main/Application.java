package com.endreit.invoice.main;

import com.endreit.invoice.googledrive.GoogleDrive;
import com.endreit.invoice.inputparameters.SalaryParamsPropertyFileImpl;
import com.endreit.invoice.inputparameters.SettingParamsPropertyFileImpl;
import com.endreit.invoice.utils.FileHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Application extends Processor
{
    public static final String WORKING_DIR_NAME = "InvoiceApp";

    public static final String INPUT_DIR_NAME = "input";

    public static final String GENERATED_DIR_NAME = "generated";

    public static final String TEMPLATE_FILE_NAME = "template.xls";

    public static final String SETTINGS_PROP_FILE_NAME = "settings.properties";

    public static final String SALARY_PROP_FILE_NAME = "salary.properties";

    public static final Application INSTANCE = new Application();

    private File workingDir;
    private File inputDir;

    @Override
    protected void init()
    {
        this.workingDir = FileHelper.createFolderInsideTemporaryDir(Application.WORKING_DIR_NAME, false);
        this.inputDir = FileHelper.createFolder(workingDir.getAbsolutePath(), Application.INPUT_DIR_NAME, true);
        GoogleDrive.getInstance().downloadInputParameters(inputDir);

        this.settingParams = new SettingParamsPropertyFileImpl(inputDir, SETTINGS_PROP_FILE_NAME);
        this.salaryParams = new SalaryParamsPropertyFileImpl(inputDir, SALARY_PROP_FILE_NAME);
    }

    public static Application getInstance()
    {
        return INSTANCE;
    }

    @Override
    protected String getTemplatePath()
    {
        File file = new File(inputDir, TEMPLATE_FILE_NAME);
        if (!file.exists())
        {
            throw new RuntimeException("Template file does not exist: " + file.getAbsolutePath());
        }
        return file.getAbsolutePath();
    }

    @Override
    protected String getDestinationPath(String variable)
    {
        File generatedDir = FileHelper.createFolder(workingDir.getAbsolutePath(), GENERATED_DIR_NAME, false);
        String outputName = variable.replace("/", "_").concat("_invoice.xls");
        return new File(generatedDir, outputName).getAbsolutePath();
    }

    @Override
    public String execute(Date executionDate) throws IOException, InvalidFormatException
    {
        String outputFile = super.execute(executionDate);
        GoogleDrive.getInstance().uploadFile(new File(outputFile));
        return outputFile;
    }
}
