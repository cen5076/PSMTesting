package testing.logicSubsystemTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	appControllerLogin_SS_Old.class,
	appControllerGetData_SS_Old.class,
	appControllerGetTimers_SS_Old.class
	})
public class AllLogicSubsystemTests_Old {

}
