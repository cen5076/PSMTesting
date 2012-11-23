package testing.logicSubsystemTest;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Logic.appController;

import testUtil.DBUtil;

public class appControllerSemesterClear_SS {

	appController app1;
	DBUtil dbUtil;
	
	@Before
	public void setUp() throws Exception {
		
		app1 = new appController();
		dbUtil = new DBUtil();
	}

	@After
	public void tearDown() throws Exception {
		
		app1 = null;
		dbUtil = null;
	}

	/** Test Case ID: PSM014_End_Semester_Clear-SubsystemTest-E01
	 * Purpose: To test the setSemesterClear function which sets a variable used when clearing the database on 
	 * 			a Timer action.
	 * Date Created: 09/18/12
	 * Author: David Garcia
	 */
	@Test
	public void testSetSemesterClear_1() {

		Calendar c = Calendar.getInstance();
		
		app1.setSemesterClear(2012, 11, 20, 10, 0);

		assertEquals("Year", 2012, app1.getAutoClear().get(Calendar.YEAR));
		assertEquals("Month", 11, app1.getAutoClear().get(Calendar.MONTH));
		assertEquals("Date", 20, app1.getAutoClear().get(Calendar.DATE));
		assertEquals("Hour", 10, app1.getAutoClear().get(Calendar.HOUR));
		assertEquals("Minute", 0, app1.getAutoClear().get(Calendar.MINUTE));
		
		assertEquals("Date2", 1, app1.getDate2().compareTo(c.getTime()));
		
	}
	
	/** Test Case ID: PSM014_End_Semester_Clear-SubsystemTest-E02
	 * Purpose: Test the setSemesterClear function which sets a variable used when clearing the database on a Timer action
	 * Date Created: 09/18/12
	 * Author: David Garcia
	 */
	@Test
	public void testSetSemesterClear_2() {

		Calendar c = Calendar.getInstance();
		
		app1.setSemesterClear(2012, 11, 20, 10, -1);

		//Assert
		assertEquals("Year", 2012, app1.getAutoClear().get(Calendar.YEAR));
		assertEquals("Month", 11, app1.getAutoClear().get(Calendar.MONTH));
		assertEquals("Date", 20, app1.getAutoClear().get(Calendar.DATE));
		assertEquals("Hour", 9, app1.getAutoClear().get(Calendar.HOUR));
		assertEquals("Minute", 59, app1.getAutoClear().get(Calendar.MINUTE));
		
		assertEquals("Date2", 1, app1.getDate2().compareTo(c.getTime()));
	}
}
