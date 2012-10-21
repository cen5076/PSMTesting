@echo off
cd %UserProfile%\workspace\PSMTesting
cobertura-report.bat --destination coverage-report src
