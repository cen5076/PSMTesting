package testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import testing.logicSubsystemTest.appControllerGetData_SS_Old;
import testing.logicSubsystemTest.appControllerGetTimers_SS_Old;
import testing.logicSubsystemTest.appControllerLogin_SS_Old;
import testing.logicUnitTest.appControllerDBGettersTest;
import testing.logicUnitTest.appControllerParserTest;
import testing.logicUnitTest.appControllerSemesterClearTest;
import testing.logicUnitTest.appControllerTest;
import testing.logicUnitTest.appControllerTimersTest;

@RunWith(Suite.class)
@SuiteClasses({appControllerGetData_SS_Old.class, appControllerGetTimers_SS_Old.class,
	appControllerLogin_SS_Old.class,appControllerDBGettersTest.class,
	appControllerParserTest.class, appControllerSemesterClearTest.class,
	appControllerTest.class, appControllerTimersTest.class })
public class AllTests {

}
