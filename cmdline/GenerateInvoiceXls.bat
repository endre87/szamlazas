IF %1.==. GOTO doIt
SET executionDate=%1

:doIt
java -jar target/cmdline-1.0.0-jar-with-dependencies.jar %executionDate%

