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
### Run once to get the client_secret.json (json file already included at: com.endreit.invoice.googledrive/src/main/resources)
    - follow instructions from https://developers.google.com/identity/sign-in/web/devconsole-project to create an OAuth client (type = other) download the json data into com.endreit.invoice.googledrive\src\main\resources\client_secret.json

### Building the application and running it for the first time
    - you might need to delete previously stored file from your windows user directory \.store\google_drive\StoredCredential
    - run: __mvn package -Dmaven.test.skip=true__
    - if build was successful, go to cmdline directory and execute __GenerateInvoiceXls.bat__ or __GenerateInvoiceXls.sh__
    - on the first execution google needs your permission to let the application manage file on google drive

## CI - Docker & Jenkins
### Prerequisites
    On the host machine where Jenkins will do the build we need Docker. The project will be built using maven on a ephemeral docker container.
    After a successful build, the executable will be copied into __/mnt/nfs/deploy__
    Create this directory in the host machine and assign it to owner and group __jenkins__
    __sudo mkdir -p -v /mnt/nfs/deploy/__
    __sudo chown jenkins /mnt/nfs/deploy/__
    __sudo chgrp jenkins /mnt/nfs/deploy/__

### Docker
    - install Docker
        https://www.voyalab.com/2017/04/01/installing-docker-community-edition-on-ubuntu/

    - install __mymaven__ image from Dockerfile using command:
        __docker build -t mymaven .__

    - optionally can push the image to the registry
        __docker push mymaven__

### Jenkins
    - install Jenkins

    - create a new Pipeline build
        For Definition choose __Pipeline script from SCM__ and set Script Path to __Jenkinsfile__

Ready to build with Jenkins!
