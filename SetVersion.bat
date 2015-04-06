@ECHO OFF
@ECHO This script updates the pom version in every module.
@ECHO Additionally you can provide the vcs build number as argument
@ECHO   ex. %0 %%build.vcs.number%%

:readVersion
SET /p version= <version.txt

IF %1.==. GOTO doIt
SET version=%version%-%1

:doIt
@ECHO Setting all module versions to %version%
mvn versions:set -DnewVersion=%version% versions:commit
