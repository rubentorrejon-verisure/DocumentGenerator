@echo off
if not exist bin mkdir bin
javac -d bin src/main/java/com/example/docgenerator/*.java src/main/java/com/example/docgenerator/generators/dni/*.java
if errorlevel 1 (
  echo Compilación fallida.
  exit /b 1
)
echo Compilación completada.
