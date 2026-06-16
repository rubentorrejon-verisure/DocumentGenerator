@echo off
if not exist bin (
  echo No se ha compilado el proyecto.
  echo Ejecuta build.bat primero.
  exit /b 1
)
java -cp bin com.example.docgenerator.App
