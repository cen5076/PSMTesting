cd %UserProfile%\workspace\PSMTesting
del cobertura.ser
del cobertura.ser.lock
rd /s /q coverage-report
copy cobertura.ser.bak cobertura.ser
