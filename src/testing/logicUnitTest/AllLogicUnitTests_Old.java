package testing.logicUnitTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	appControllerTest.class,
	appControllerDBGettersTest.class,
	appControllerParserTest.class,
	appControllerTimersTest.class,
	appControllerSemesterClearTest.class})
public class AllLogicUnitTests_Old {

}
