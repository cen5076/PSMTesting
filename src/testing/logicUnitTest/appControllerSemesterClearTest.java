package testing.logicUnitTest;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Logic.appController;

import testUtil.DBUtil;

public class appControllerSemesterClearTest {

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

	@Test // PSM014-End_Semester_Clear-UnitTest-E01
	// Test the setting of the set semester clear
	// relies on autoclear() stub and 
	public void testSetSemesterClear_1() {
		
		Date d = new Date(System.currentTimeMillis());
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		
		
		
		app1.setSemesterClear(c.get(Calendar.YEAR), c.get(Calendar.MONTH),c.get(Calendar.DATE), c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));
		
		System.out.println("Calendar:" + c.getTime().toString());
		System.out.println("date2   :" + app1.getDate2().toString());

		assertEquals("Year",c.get(Calendar.YEAR),app1.getAutoClear().get(Calendar.YEAR));
		assertEquals("Month",c.get(Calendar.MONTH),app1.getAutoClear().get(Calendar.MONTH));
		assertEquals("Date",c.get(Calendar.DATE),app1.getAutoClear().get(Calendar.DATE));
		assertEquals("Hour",c.get(Calendar.HOUR),app1.getAutoClear().get(Calendar.HOUR));
		assertEquals("Minute",c.get(Calendar.MINUTE),app1.getAutoClear().get(Calendar.MINUTE));
		/* Removed to ignore the millisecond difference that will cause a failure. */
		//assertEquals("Date2",0,app1.getDate2().compareTo(c.getTime()));
		
	}
	
	@Test // PSM014-End_Semester_Clear-UnitTest-E02
	// Test the setting of the set semester clear
	// relies on autoclear() stub and 
	public void testSetSemesterClear_2() {
		
	    Date d = new Date(System.currentTimeMillis());
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		
		app1.setSemesterClear(c.get(Calendar.YEAR), c.get(Calendar.MONTH),c.get(Calendar.DATE), c.get(Calendar.HOUR), -1);
		
		System.out.println(app1.getAutoClear().getTime().toString());
		//Assert
		assertEquals("Year",c.get(Calendar.YEAR),app1.getAutoClear().get(Calendar.YEAR));
		assertEquals("Month",c.get(Calendar.MONTH),app1.getAutoClear().get(Calendar.MONTH));
		assertEquals("Date",c.get(Calendar.DATE),app1.getAutoClear().get(Calendar.DATE));
		assertEquals("Hour",c.get(Calendar.HOUR)-1,app1.getAutoClear().get(Calendar.HOUR));
		assertEquals("Minute",59,app1.getAutoClear().get(Calendar.MINUTE));
		
		assertEquals("Date2",-1,app1.getDate2().compareTo(c.getTime()));
	}

}
