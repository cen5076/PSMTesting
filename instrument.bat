cd %UserProfile%\workspace\PSMTesting
del cobertura.ser
del cobertura.ser.lock
del cobertura.ser.bak
rd /s /q coverage-report
rd /s /q instrumented
cobertura-instrument.bat --destination instrumented bin