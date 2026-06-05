@echo off
chcp 65001 >nul


echo [FastEmojis] Running Demo...
cd examples
cls
call mvn -q clean compile exec:java -Dexec.mainClass="fastemojis.Demo"
if %ERRORLEVEL% NEQ 0 ( pause & exit /b )
cd ..
pause
