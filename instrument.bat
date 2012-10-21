@echo off
cd %UserProfile%\workspace\PSMTesting
del cobertura.ser
rd /s /q instrumented
rd /s /q coverage-report
cobertura-instrument.bat --destination instrumented bin