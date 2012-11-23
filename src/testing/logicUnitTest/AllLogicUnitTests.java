package testing.logicUnitTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	appControllerSupplementary.class,
	appControllerTest.class,
	appControllerDBGettersTest.class,
	appControllerParserTest.class,
	appControllerTimersTest.class,
	appControllerSemesterClearTest.class,
	appControllerBeginTest.class,
	appControllerTimerTaskTest.class})
public class AllLogicUnitTests {

}
