@echo off
setlocal
cd /d "%~dp0"
if exist out rmdir /s /q out
if exist dist rmdir /s /q dist
mkdir out
mkdir dist
(dir /s /b src\main\java\*.java) > sources.txt
javac --release 17 -encoding UTF-8 -Xlint:all -d out @sources.txt
if errorlevel 1 goto :error
jar --create --file dist\HotelBookingSystem.jar --main-class app.Main -C out .
if errorlevel 1 goto :error
echo Created dist\HotelBookingSystem.jar
goto :end
:error
echo Packaging failed.
:end
del sources.txt 2>nul
endlocal
