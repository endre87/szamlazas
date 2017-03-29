#!/bin/sh
# echo -----------------------------------------------------------------------------------------------------
# echo Usage: GenerateInvoiceXls.sh [copyToBaseDir noUploadToGoogleDrive -D{date}]
# echo All parameters are optional and their order is arbitrary:
# echo   copyToBaseDir - flag to copy the xls to the directory where this script was executed
# echo   noUploadToGoogleDrive - flag to skip copying the file to google drive, default is false, it will upload
# echo   -D{date} - specifies the execution date, it must be in format: dd.MM.yyyy
# echo -----------------------------------------------------------------------------------------------------

java -jar target/cmdline-1.0.0-jar-with-dependencies.jar $*
