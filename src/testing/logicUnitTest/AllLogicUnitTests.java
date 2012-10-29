package testing.logicUnitTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	appControllerBeginTest.class, 
	appControllerDBGettersTest.class,
		appControllerParserTest.class, 
		appControllerSemesterClearTest.class,
		appControllerSupplementary.class, 
		appControllerTest.class,
		appControllerTimersTest.class, 
		appControllerTimerTaskTest.class })
public class AllLogicUnitTests {

}
