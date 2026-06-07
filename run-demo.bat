@echo off

echo ⚡ Building Main Project...
call mvn clean package -DskipTests -q
if %ERRORLEVEL% NEQ 0 ( echo ❌ Benchmark failed. & pause & exit /b %ERRORLEVEL% )

echo [FastEmojis] Running Demo...
cd examples
call mvn clean compile exec:java -Dexec.mainClass="fastemojis.Demo"
if %ERRORLEVEL% NEQ 0 ( pause & exit /b )
cd ..
pause
