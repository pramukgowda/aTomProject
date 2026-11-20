@echo off
setlocal
cd /d "%~dp0"
if exist out rmdir /s /q out
mkdir out
(dir /s /b src\main\java\*.java src\test\java\*.java) > sources.txt
javac --release 17 -encoding UTF-8 -Xlint:all -d out @sources.txt
if errorlevel 1 goto :error
java -ea -cp out tests.AllTests
if errorlevel 1 goto :error
goto :end
:error
echo Tests failed.
:end
del sources.txt 2>nul
endlocal
