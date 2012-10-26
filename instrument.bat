cd %UserProfile%\workspace\PSMTesting
del cobertura.ser
copy cobertura.ser.bak cobertura.ser
del cobertura.ser.lock
rd /s /q coverage-report
cobertura-instrument.bat psm.jar