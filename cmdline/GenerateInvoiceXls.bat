@ECHO OFF
@ECHO Usage: GenerateInvoiceXls.bat date
@ECHO date is optional, if provided it must be in format: dd.MM.yyyy

IF %1.==. GOTO doIt
SET executionDate=%1

:doIt
java -jar target/cmdline-1.0.0-jar-with-dependencies.jar copyToBaseDir %executionDate%

