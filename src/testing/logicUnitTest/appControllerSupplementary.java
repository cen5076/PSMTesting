package testing.logicUnitTest;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Logic.appController;

/** this is L package **/
public class appControllerSupplementary {

	appController app1;
	
	@Before
	public void setUp() throws Exception {
		
		app1 = new appController();
	}

	@After
	public void tearDown() throws Exception {
		app1 = null;
	}

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
		
		assertNotNull("Completed Getters Successfully",app1);
	}
	
	@Test
	public void testSetters() {
		
		GregorianCalendar now = new GregorianCalendar();
		
		now.setTimeInMillis(System.currentTimeMillis());
		
		app1.setTime(now.get(Calendar.YEAR),now.get(Calendar.MONTH), now.get(Calendar.DATE), now.get(Calendar.HOUR), now.get(Calendar.MINUTE));
		
		assertEquals("Year",now.get(Calendar.YEAR),app1.getSetRun().get(Calendar.YEAR));
		assertEquals("Year",now.get(Calendar.MONTH),app1.getSetRun().get(Calendar.MONTH));
		assertEquals("Year",now.get(Calendar.DATE),app1.getSetRun().get(Calendar.DATE));
		assertEquals("Year",now.get(Calendar.HOUR),app1.getSetRun().get(Calendar.HOUR));
		assertEquals("Year",now.get(Calendar.MINUTE),app1.getSetRun().get(Calendar.MINUTE));
		
		
	}
	
	@Test
	public void testSystemCalls() {
		
		app1.sleep(0);
	}

}
