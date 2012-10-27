cd %UserProfile%\workspace\PSMTesting
del cobertura.ser
del cobertura.ser.lock
del cobertura.ser.bak
rd /s /q coverage-report
cobertura-instrument.bat psm.jar