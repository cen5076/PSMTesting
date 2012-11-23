package testing.logicSubsystemTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({
	appController_SS.class,
	appControllerDBGetters_SS.class,
	appControllerParser_SS.class,
	appControllerTimers_SS.class,
	appControllerSemesterClear_SS.class,
	appControllerSupplementary_SS.class,
	appControllerBegin_SS.class,
	appControllerTimerTask_SS.class})
public class AllLogicSubsystemTests {

}
