## Setup prerequisites:
 - JDK 1.7 or higher
 - Maven
 - Google account
 - The following config files in google drive:
    1. Invoice/input/salary.properties
      // ExchangeDay = day no. or literals: LAST_DAY
      ExchangeDay         =   LAST_DAY
      TotalSalaryEUR      =   xxxx
      CMSalaryRON         =   xxxx
      AccountingFeeRON    =   xxxx
      SMETaxPercent       =   xxxx
      ProfitTaxPercent    =   xxxx
    2. Invoice/input/settings.properties
      // invoice sheet
      InvoiceDay                  =   13
      InvoiceServiceDay           =   11
      InvoiceServiceDateFormat    =   dd.MM.yyyy

      // expense sheet
      ExpenseDateFormat           =   dd MMMM yyyy
    3. Invoice/input/template.xls
      This is the template for the generated invoice document.
  - The generated files will be uploaded into Invoice/generated folder on google drive

## Setup the application:
### Run once to get the client_secret.json
 - follow instructions from https://developers.google.com/identity/sign-in/web/devconsole-project to create an OAuth client (type = other) download the json data into com.endreit.invoice.googledrive\src\main\resources\client_secret.json

### Building the application and running it for the first time
 - you might need to delete previously stored file from your windows user directory \.store\google_drive\StoredCredential
 - run: mvn package -Dmaven.test.skip=true
 - if build was successful, go to cmdline directory and execute GenerateInvoiceXls.bat
 - on the first execution google needs your permission to let the application manage file on google drive
