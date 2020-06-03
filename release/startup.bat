@echo off
@REM Copyright 2018-present, Yudong (Dom) Wang
@REM
@REM Licensed under the Apache License, Version 2.0 (the "License");
@REM you may not use this file except in compliance with the License.
@REM You may obtain a copy of the License at
@REM
@REM      http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing, software
@REM distributed under the License is distributed on an "AS IS" BASIS,
@REM WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM See the License for the specific language governing permissions and
@REM limitations under the License.

@REM -----------------------------------------------------------------------------
@REM Start script for the manager Server
@REM -----------------------------------------------------------------------------

setlocal enabledelayedexpansion
set errorlevel=
title webchat


set JAVA_OPT=-Xms1g -Xmx1g -Xmn512m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=320m 
set JAVA_OPT=%JAVA_OPT% -XX:-OmitStackTraceInFastThrow -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=logs\webchat_heapdump.hprof

@REM Java JVM tuning settings for dev environment
@REM set JAVA_OPT=%JAVA_OPT% -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps
@REM set JAVA_OPT=%JAVA_OPT% -Xloggc:logs\webchat-gc.log  

set SERVER_NAME=webchat.jar
set CONF=conf/application.yml

cd /d "%~dp0"
@REM Check if Java is correctly installed and set
java -version 1>nul 2>nul
if !errorlevel! neq 0 (
    @echo.
    @echo Please install Java 1.8 or higher and make sure the Java is set correctly.
    @echo.
    @echo You can execute command [ java -version ] to check if Java is correctly installed and set.
    @echo.

    pause
    goto:eof
)

@echo.
@echo Please do not close the current window.
@echo.
@echo %JAVA_OPT%
java %JAVA_OPT% -jar  %SERVER_NAME% --spring.config.location=%CONF% 

@echo Stopped ocr manager.
@echo.

pause
goto:eof
