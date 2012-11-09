package testing.logicSubsystemTest;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Logic.appController;

import testUtil.DBUtil;

public class appControllerGetTimers_SS {
	
	appController app1;
	Calendar today;
	Calendar earlierToday;
	DBUtil dbUtil;
	
	
	@Before
	public void setUp() throws Exception {
		//Create new appController
		app1 = new appController();
		app1.setTestingTimers();
		dbUtil = new DBUtil();
		
	}

	@After
	public void tearDown() throws Exception {
		
		// delete appController */
		app1 = null;
		dbUtil = null;
	}
	

	@Test // PSM_008-Message_PopUp-SubSystemTest-C01
	/**
	 * Test ID: PSM_008-Message_PopUp-SubSystemTest-C01
	 * Purpose: Test offpoint/inpoint values for getEndTime.
	 * Date Created: 09/18/12
	 * Author: Jose Borja
	 * Stubs Needed: 
	 */
	// Test for return date that is same as current time with 1 minute difference
	public void testGetEndTime1_SS() {
		
		//set hour and minutes of day
		//h=0=0:00, h=1=1:00, h=-1=23:00
		//m=0=:00, m=1=:01, m=-1=:59
		int h=0;
		int m=0;
		
		//set offset for current time to match
		long hDiff = dbUtil.hrsInMilli(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) - dbUtil.hrsInMilli(h); 
		long mDiff = dbUtil.minInMilli(Calendar.getInstance().get(Calendar.MINUTE)) - dbUtil.minInMilli(m) + 60000; 
		
		//create new Date with time offset
		Date time = new Date(System.currentTimeMillis() - hDiff - mDiff);
		
		//create Calendars from each to compare
		Calendar expected = new GregorianCalendar();
		expected.setTimeInMillis(time.getTime());
		
		Calendar result = new GregorianCalendar();
		result.setTime(app1.getEndTime(h, m));
		
		
		
		String messg = new Object(){}.getClass().getEnclosingMethod().getName(); 
		System.out.println("---" + messg + "---");

		System.out.println("time  =" + time.toString());
		System.out.println("return=" + result.getTime().toString());

		//Assert
		assertEquals("Year",expected.get(Calendar.YEAR),result.get(Calendar.YEAR));
		assertEquals("Month",expected.get(Calendar.MONTH),result.get(Calendar.MONTH));
		assertEquals("Date",expected.get(Calendar.DATE),result.get(Calendar.DATE));
		assertEquals("Hour",expected.get(Calendar.HOUR),result.get(Calendar.HOUR));
		assertEquals("Minute",expected.get(Calendar.MINUTE),result.get(Calendar.MINUTE));
	}

	
	@Test // PSM_008-Message_PopUp-SubSystemTest-C02
	/**
	 * Test ID: PSM_008-Message_PopUp-SubSystemTest-C02
	 * Purpose: Test offpoint/inpoint values for get15BeforeEnd
	 * Date Created: 09/18/12
	 * Author: Jose Borja
	 * Stubs Needed: 
	 */
	public void testGetPopup15min1_SS() {
		
		//set hour and minutes of day 
		int m=16;
		
		//set offset for current time to match
		long mDiff = dbUtil.minInMilli(m); //+ dbUtil.get15Milli(); 
		
		//create new Date with time offset
		Date time = new Date(System.currentTimeMillis());// - sDiff);
		
		//create Calendars from each to compare
		Calendar expected = new GregorianCalendar();
		expected.setTimeInMillis(time.getTime() + mDiff);
		
		Calendar result = new GregorianCalendar();
		result.setTime(app1.get15BeforeEnd(expected.get(Calendar.HOUR_OF_DAY), expected.get(Calendar.MINUTE)));
		
		
		String messg = new Object(){}.getClass().getEnclosingMethod().getName(); 
		System.out.println("---" + messg + "---");

		System.out.println("Current time=" + time.toString());
		System.out.println("end time=" + expected.getTime().toString());
		System.out.println("pop at =" + result.getTime().toString());
		
		try{
			Thread.sleep(dbUtil.minInMilli(1) + dbUtil.secInMilli(20));
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
		//Assert
		assertTrue("15 before", app1.is15BeforeEnd);

}
	
	@Test // PSM_008-Message_PopUp-SubSystemTest-C03
	/**
	 * Test ID: PSM_008-Message_PopUp-SubSystemTest-C03
	 * Purpose: Test offpoint/inpoint values for get5BeforeEnd
	 * Date Created: 09/18/12
	 * Author: Jose Borja
	 * Stubs Needed: 
	 */
	public void testGetPopup5min1_SS() {
		
		//set hour and minutes of day 
		int m=6;
		
		//set offset for current time to match
		long mDiff = dbUtil.minInMilli(m); //+ dbUtil.get5Milli(); 
		
		//create new Date with time offset
		Date time = new Date(System.currentTimeMillis());// - sDiff);
		
		//create Calendars from each to compare
		Calendar expected = new GregorianCalendar();
		expected.setTimeInMillis(time.getTime()+mDiff);
		
		Calendar result = new GregorianCalendar();
		result.setTime(app1.get5BeforeEnd(expected.get(Calendar.HOUR_OF_DAY), expected.get(Calendar.MINUTE)));
		
		
		String messg = new Object(){}.getClass().getEnclosingMethod().getName(); 
		System.out.println("---" + messg + "---");

		System.out.println("Current time=" + time.toString());
		System.out.println("end time=" + expected.getTime().toString());
		System.out.println("pop at =" + result.getTime().toString());
		
		try{
			Thread.sleep(dbUtil.minInMilli(1) + dbUtil.secInMilli(20));
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
		//Assert
		assertTrue("5 before",app1.is5BeforeEnd);
	}
}

	
	