@echo off
java -cp lib\cobertura.jar;lib\mysql-connector-java-5.1.21-bin.jar;lib\swing-layout-1.0.4.jar;lib\system-rules-1.2.0.jar;"C:\Program Files\Eclipse\plugins\org.junit_4.10.0.v4_10_0_v20120426-0900\junit.jar";"C:\Program Files\Eclipse\plugins\org.hamcrest.core_1.1.0.v20090501071000.jar";instrumented org.junit.runner.JUnitCore testing.dataStoreUnitTest.DBConnectionTestAllOld
