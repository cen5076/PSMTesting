package testing.logicUnitTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({  appControllerDBGettersTest.class,
		appControllerParserTest.class, appControllerSemesterClearTest.class,
		appControllerTest.class, appControllerTimersTest.class })
public class AllUnitTests {

}
