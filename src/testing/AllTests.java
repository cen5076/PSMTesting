package testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import testing.logicSubsystemTest.appControllerGetData_SS;
import testing.logicSubsystemTest.appControllerGetTimers_SS;
import testing.logicSubsystemTest.appControllerLogin_SS;
import testing.logicUnitTest.appControllerDBGettersTest;
import testing.logicUnitTest.appControllerParserTest;
import testing.logicUnitTest.appControllerSemesterClearTest;
import testing.logicUnitTest.appControllerTest;
import testing.logicUnitTest.appControllerTimersTest;

@RunWith(Suite.class)
@SuiteClasses({appControllerGetData_SS.class, appControllerGetTimers_SS.class,
	appControllerLogin_SS.class,appControllerDBGettersTest.class,
	appControllerParserTest.class, appControllerSemesterClearTest.class,
	appControllerTest.class, appControllerTimersTest.class })
public class AllTests {

}
