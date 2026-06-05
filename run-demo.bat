@echo off

echo [FastEmojis] Running Demo...
cd examples
call mvn clean compile exec:java -Dexec.mainClass="fastemojis.Demo"
if %ERRORLEVEL% NEQ 0 ( pause & exit /b )
cd ..
pause
