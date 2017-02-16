@ECHO OFF
@ECHO -----------------------------------------------------------------------------------------------------
@ECHO Usage: GenerateInvoiceXls.bat [copyToBaseDir noUploadToGoogleDrive -D{date}]
@ECHO All parameters are optional and their order is arbitrary:
@ECHO   copyToBaseDir - flag to copy the xls to the directory where this script was executed
@ECHO   noUploadToGoogleDrive - flag to skip copying the file to google drive, default is false, it will upload
@ECHO   -D{date} - specifies the execution date, it must be in format: dd.MM.yyyy
@ECHO -----------------------------------------------------------------------------------------------------

REM Clear params in case we execute it more then once from the same terminal
SET params=[]

IF [%1]==[] GOTO doIt
SET params=%1

IF [%2]==[] GOTO doIt
SET params=%params% %2

IF [%3]==[] GOTO doIt
SET params=%params% %3

:doIt
java -jar target/cmdline-1.0.0-jar-with-dependencies.jar %params%
