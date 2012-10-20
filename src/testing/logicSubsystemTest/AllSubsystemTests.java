package testing.logicSubsystemTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ appControllerGetData_SS.class, appControllerGetTimers_SS.class,
		appControllerLogin_SS.class })
public class AllSubsystemTests {

}
