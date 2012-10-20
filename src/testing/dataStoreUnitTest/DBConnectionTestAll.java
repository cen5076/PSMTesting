package testing.dataStoreUnitTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DBConnectionTest.class, DBConnectionTest2.class,
		DBConnectionTest3.class })
public class DBConnectionTestAll {
	
}
