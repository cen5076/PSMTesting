package testing.logicSubsystemTest;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Logic.appController;

public class appControllerSupplementary_SS {

	appController app1;
	
	@Before
	public void setUp() throws Exception {
		
		app1 = new appController();
	}

	@After
	public void tearDown() throws Exception {
		app1 = null;
	}

	/** Test Case ID: PSM001_Login-SubsystemTest-G01
	* Purpose: Test miscellaneous getter methods
	* Date Created: 10/27/12
	* Author: David Garcia
	* Stubs needed: DBConnection
	*/
	@Test
	public void testGetters() {
		
		try{
			app1.getCon();
			app1.getTime();
			app1.getTimeMillis();
			app1.returnHr();
			app1.returnMin();
			app1.getSemesterClear();
		}
		catch (Exception e){
			
			fail("Caught Exception Getting- " + e.toString());
		}
		
		assertNotNull("Completed Getters Successfully", app1);
	}
	
	/** Test Case ID: PSM001_Login-SubsystemTest-G02
	* Purpose: Test miscellaneous setter methods
	* Date Created: 10/27/12
	* Author: David Garcia
	*/
	@Test
	public void testSetters() {
		
		Calendar now = new GregorianCalendar();
		
		now.setTimeInMillis(System.currentTimeMillis());
		
		app1.setTime(now.get(Calendar.YEAR),now.get(Calendar.MONTH), now.get(Calendar.DATE), now.get(Calendar.HOUR), now.get(Calendar.MINUTE));
		
		assertEquals("Year", now.get(Calendar.YEAR), app1.getSetRun().get(Calendar.YEAR));
		assertEquals("Month", now.get(Calendar.MONTH), app1.getSetRun().get(Calendar.MONTH));
		assertEquals("Date", now.get(Calendar.DATE), app1.getSetRun().get(Calendar.DATE));
		assertEquals("Hour", now.get(Calendar.HOUR), app1.getSetRun().get(Calendar.HOUR));
		assertEquals("Minute", now.get(Calendar.MINUTE), app1.getSetRun().get(Calendar.MINUTE));
		
		
	}
	
	/** Test Case ID: PSM001_Login-SubsystemTest-G03
	* Purpose: Test miscellaneous system calls wrapped in methods
	* Date Created: 10/27/12
	* Author: David Garcia
	*/
	@Test
	public void testSystemCalls() {
		
		app1.sleep(0);
		assertNotNull("Completed sleep command", app1);
	}

}
