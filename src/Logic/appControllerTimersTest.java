package Logic;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.jmx.trace.Trace;

import testUtil.DBUtil;

@SuppressWarnings({ "unused", "deprecation" })
public class appControllerTimersTest {

	appController app1;
	Calendar today;
	Calendar earlierToday;
	DBUtil dbUtil;
	
	
	@Before
	public void setUp() throws Exception {
		//Create new appController
		app1 = new appController();
		dbUtil = new DBUtil();
		
	}

	@After
	public void tearDown() throws Exception {
		
		// delete appController */
		app1 = null;
		dbUtil = null;
	}

	@Test
	// Test Case 1 
	public void testGetPopup15min_1() {
		
		//set hour and minutes of day 
				//h=0=0:00, h=1=1:00, h=-1=23:00
				//m=0=:00, m=1=:01, m=-1=:59
				int h=0;
				int m=0;
				
				//set offset for current time to match
				long hDiff = dbUtil.hrsInMilli(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) - dbUtil.hrsInMilli(h); 
				long mDiff = dbUtil.minInMilli(Calendar.getInstance().get(Calendar.MINUTE)) -dbUtil.minInMilli(m) + dbUtil.get15Milli(); 
				
				//create new Date with time offset
				Date time = new Date(System.currentTimeMillis());//- hDiff - mDiff);
				
				//create Calendars from each to compare
				Calendar expected = new GregorianCalendar();
				expected.setTimeInMillis(time.getTime());
				
				Calendar result = new GregorianCalendar();
				result.setTime(app1.get15BeforeEnd(0, 0));
				//result.setTime(app1.get15BeforeEnd(expected.get(Calendar.HOUR_OF_DAY), expected.get(Calendar.MINUTE)));

				
				
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
	@Test
	// Test Case 1 
	public void testGetPopup15min_2() {
		
		//set hour and minutes of day 
				//h=0=0:00, h=1=1:00, h=-1=23:00
				//m=0=:00, m=1=:01, m=-1=:59
				int h=0;
				int m=1;
				
				//set offset for current time to match
				long hDiff = dbUtil.hrsInMilli(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) - dbUtil.hrsInMilli(h); 
				long mDiff = dbUtil.minInMilli(Calendar.getInstance().get(Calendar.MINUTE)) -dbUtil.minInMilli(m) + dbUtil.get15Milli(); 
				
				//create new Date with time offset
				Date time = new Date(System.currentTimeMillis()- hDiff - mDiff);
				
				//create Calendars from each to compare
				Calendar expected = new GregorianCalendar();
				expected.setTimeInMillis(time.getTime());
				
				Calendar result = new GregorianCalendar();
				result.setTime(app1.get15BeforeEnd(h, m));
				
				
				
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
	@Test
	// Test Case 1 
	public void testGetPopup15min_3() {
		
		//set hour and minutes of day 
				//h=0=0:00, h=1=1:00, h=-1=23:00
				//m=0=:00, m=1=:01, m=-1=:59
				int h=0;
				int m=-1;
				
				//set offset for current time to match
				long hDiff = dbUtil.hrsInMilli(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) - dbUtil.hrsInMilli(h); 
				long mDiff = dbUtil.minInMilli(Calendar.getInstance().get(Calendar.MINUTE)) -dbUtil.minInMilli(m) + dbUtil.get15Milli(); 
				
				//create new Date with time offset
				Date time = new Date(System.currentTimeMillis()- hDiff - mDiff);
				
				//create Calendars from each to compare
				Calendar expected = new GregorianCalendar();
				expected.setTimeInMillis(time.getTime());
				
				Calendar result = new GregorianCalendar();
				result.setTime(app1.get15BeforeEnd(h, m));
				
				
				
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
	@Test
	// Test Case 1 
	public void testGetPopup15min_4() {
		
		//set hour and minutes of day 
				//h=0=0:00, h=1=1:00, h=-1=23:00
				//m=0=:00, m=1=:01, m=-1=:59
				int h=1;
				int m=0;
				
				//set offset for current time to match
				long hDiff = dbUtil.hrsInMilli(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) - dbUtil.hrsInMilli(h); 
				long mDiff = dbUtil.minInMilli(Calendar.getInstance().get(Calendar.MINUTE)) -dbUtil.minInMilli(m) + dbUtil.get15Milli(); 
				
				//create new Date with time offset
				Date time = new Date(System.currentTimeMillis()- hDiff - mDiff);
				
				//create Calendars from each to compare
				Calendar expected = new GregorianCalendar();
				expected.setTimeInMillis(time.getTime());
				
				Calendar result = new GregorianCalendar();
				result.setTime(app1.get15BeforeEnd(h, m));
				
				
				
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
	@Test
	// Test Case 1 
	public void testGetPopup15min_5() {
		
		//set hour and minutes of day 
				//h=0=0:00, h=1=1:00, h=-1=23:00
				//m=0=:00, m=1=:01, m=-1=:59
				int h=1;
				int m=1;
				
				//set offset for current time to match
				long hDiff = dbUtil.hrsInMilli(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) - dbUtil.hrsInMilli(h); 
				long mDiff = dbUtil.minInMilli(Calendar.getInstance().get(Calendar.MINUTE)) -dbUtil.minInMilli(m) + dbUtil.get15Milli(); 
				
				//create new Date with time offset
				Date time = new Date(System.currentTimeMillis()- hDiff - mDiff);
				
				//create Calendars from each to compare
				Calendar expected = new GregorianCalendar();
				expected.setTimeInMillis(time.getTime());
				
				Calendar result = new GregorianCalendar();
				result.setTime(app1.get15BeforeEnd(h, m));
				
				
				
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
	@Test
	// Test Case 1 
	public void testGetPopup15min_6() {
		
		//set hour and minutes of day 
				//h=0=0:00, h=1=1:00, h=-1=23:00
				//m=0=:00, m=1=:01, m=-1=:59
				int h=1;
				int m=-1;
				
				//set offset for current time to match
				long hDiff = dbUtil.hrsInMilli(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) - dbUtil.hrsInMilli(h); 
				long mDiff = dbUtil.minInMilli(Calendar.getInstance().get(Calendar.MINUTE)) -dbUtil.minInMilli(m) + dbUtil.get15Milli(); 
				
				//create new Date with time offset
				Date time = new Date(System.currentTimeMillis()- hDiff - mDiff);
				
				//create Calendars from each to compare
				Calendar expected = new GregorianCalendar();
				expected.setTimeInMillis(time.getTime());
				
				Calendar result = new GregorianCalendar();
				result.setTime(app1.get15BeforeEnd(h, m));
				
				
				
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
	@Test
	// Test Case 1 
	public void testGetPopup15min_7() {
		
		//set hour and minutes of day 
				//h=0=0:00, h=1=1:00, h=-1=23:00
				//m=0=:00, m=1=:01, m=-1=:59
				int h=-1;
				int m=0;
				
				//set offset for current time to match
				long hDiff = dbUtil.hrsInMilli(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) - dbUtil.hrsInMilli(h); 
				long mDiff = dbUtil.minInMilli(Calendar.getInstance().get(Calendar.MINUTE)) -dbUtil.minInMilli(m) + dbUtil.get15Milli(); 
				
				//create new Date with time offset
				Date time = new Date(System.currentTimeMillis()- hDiff - mDiff);
				
				//create Calendars from each to compare
				Calendar expected = new GregorianCalendar();
				expected.setTimeInMillis(time.getTime());
				
				Calendar result = new GregorianCalendar();
				result.setTime(app1.get15BeforeEnd(h, m));
				
				
				
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
	@Test
	// Test Case 1 
	public void testGetPopup15min_8() {
		
		//set hour and minutes of day 
				//h=0=0:00, h=1=1:00, h=-1=23:00
				//m=0=:00, m=1=:01, m=-1=:59
				int h=-1;
				int m=1;
				
				//set offset for current time to match
				long hDiff = dbUtil.hrsInMilli(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) - dbUtil.hrsInMilli(h); 
				long mDiff = dbUtil.minInMilli(Calendar.getInstance().get(Calendar.MINUTE)) -dbUtil.minInMilli(m) + dbUtil.get15Milli(); 
				
				//create new Date with time offset
				Date time = new Date(System.currentTimeMillis()- hDiff - mDiff);
				
				//create Calendars from each to compare
				Calendar expected = new GregorianCalendar();
				expected.setTimeInMillis(time.getTime());
				
				Calendar result = new GregorianCalendar();
				result.setTime(app1.get15BeforeEnd(h, m));
				
				
				
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
	@Test
	// Test Case 1 
	public void testGetPopup15min_9() {
		
		//set hour and minutes of day 
				//h=0=0:00, h=1=1:00, h=-1=23:00
				//m=0=:00, m=1=:01, m=-1=:59
				int h=-1;
				int m=-1;
				
				//set offset for current time to match
				long hDiff = dbUtil.hrsInMilli(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) - dbUtil.hrsInMilli(h); 
				long mDiff = dbUtil.minInMilli(Calendar.getInstance().get(Calendar.MINUTE)) -dbUtil.minInMilli(m) + dbUtil.get15Milli(); 
				
				//create new Date with time offset
				Date time = new Date(System.currentTimeMillis()- hDiff - mDiff);
				
				//create Calendars from each to compare
				Calendar expected = new GregorianCalendar();
				expected.setTimeInMillis(time.getTime());
				
				Calendar result = new GregorianCalendar();
				result.setTime(app1.get15BeforeEnd(h, m));
				
				
				
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
	@Test
	 
	//  Test for a return date that is 5 minutes ahead.
	 
	public void testGetPopup5min_1() {
		
		//set hour and minutes of day 
		//h=0=0:00, h=1=1:00, h=-1=23:00
		//m=0=:00, m=1=:01, m=-1=:59
		int h=0;
		int m=0;
		
		//set offset for current time to match
		long hDiff = dbUtil.hrsInMilli(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) - dbUtil.hrsInMilli(h); // + dbUtil.hrsInMilli(h);
		long mDiff = dbUtil.minInMilli(Calendar.getInstance().get(Calendar.MINUTE))+ dbUtil.get5Milli()-dbUtil.minInMilli(m); // + dbUtil.minInMilli(m);
		
		//create new Date with time offset
		Date time = new Date(System.currentTimeMillis()- hDiff - mDiff);
		
		//create Calendars from each to compare
		Calendar expected = new GregorianCalendar();
		expected.setTimeInMillis(time.getTime());
		
		Calendar result = new GregorianCalendar();
		result.setTime(app1.get5BeforeEnd(h, m));
		
		//Assert
		assertEquals("Year",expected.get(Calendar.YEAR),result.get(Calendar.YEAR));
		assertEquals("Month",expected.get(Calendar.MONTH),result.get(Calendar.MONTH));
		assertEquals("Date",expected.get(Calendar.DATE),result.get(Calendar.DATE));
		assertEquals("Hour",expected.get(Calendar.HOUR),result.get(Calendar.HOUR));
		assertEquals("Minute",expected.get(Calendar.MINUTE),result.get(Calendar.MINUTE));

		String messg = new Object(){}.getClass().getEnclosingMethod().getName(); 
		System.out.println("---" + messg + "---");

		System.out.println("time  =" + time.toString());
		System.out.println("return=" + result.getTime().toString());
		
		

	}
	
	@Test
	 
	 // Test for a return date that is 5 minutes ahead.
	 
	public void testGetPopup5min_2() {
		
		//set hour and minutes of day 
		//h=0=0:00, h=1=1:00, h=-1=23:00
		//m=0=:00, m=1=:01, m=-1=:59
		int h=0;
		int m=1;
		
		//set offset for current time to match
		long hDiff = dbUtil.hrsInMilli(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) - dbUtil.hrsInMilli(h); // + dbUtil.hrsInMilli(h);
		long mDiff = dbUtil.minInMilli(Calendar.getInstance().get(Calendar.MINUTE))+ dbUtil.get5Milli()-dbUtil.minInMilli(m); // + dbUtil.minInMilli(m);
		
		//create new Date with time offset
		Date time = new Date(System.currentTimeMillis()- hDiff - mDiff);
		
		//create Calendars from each to compare
		Calendar expected = new GregorianCalendar();
		expected.setTimeInMillis(time.getTime());
		
		Calendar result = new GregorianCalendar();
		result.setTime(app1.get5BeforeEnd(h, m));
		
		//Assert
		assertEquals("Year",expected.get(Calendar.YEAR),result.get(Calendar.YEAR));
		assertEquals("Month",expected.get(Calendar.MONTH),result.get(Calendar.MONTH));
		assertEquals("Date",expected.get(Calendar.DATE),result.get(Calendar.DATE));
		assertEquals("Hour",expected.get(Calendar.HOUR),result.get(Calendar.HOUR));
		assertEquals("Minute",expected.get(Calendar.MINUTE),result.get(Calendar.MINUTE));

		String messg = new Object(){}.getClass().getEnclosingMethod().getName(); 
		System.out.println("---" + messg + "---");

		System.out.println("time  =" + time.toString());
		System.out.println("return=" + result.getTime().toString());
		
		

	}
	
	@Test
	
	 // Test for a return date that is 5 minutes ahead.
	 
	public void testGetPopup5min_3() {
		
		//set hour and minutes of day 
		//h=0=0:00, h=1=1:00, h=-1=23:00
		//m=0=:00, m=1=:01, m=-1=:59
		int h=0;
		int m=-1;
		
		//set offset for current time to match
		long hDiff = dbUtil.hrsInMilli(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) - dbUtil.hrsInMilli(h); // + dbUtil.hrsInMilli(h);
		long mDiff = dbUtil.minInMilli(Calendar.getInstance().get(Calendar.MINUTE))+ dbUtil.get5Milli()-dbUtil.minInMilli(m); // + dbUtil.minInMilli(m);
		
		//create new Date with time offset
		Date time = new Date(System.currentTimeMillis()- hDiff - mDiff);
		
		//create Calendars from each to compare
		Calendar expected = new GregorianCalendar();
		expected.setTimeInMillis(time.getTime());
		
		Calendar result = new GregorianCalendar();
		result.setTime(app1.get5BeforeEnd(h, m));
		
		//Assert
		assertEquals("Year",expected.get(Calendar.YEAR),result.get(Calendar.YEAR));
		assertEquals("Month",expected.get(Calendar.MONTH),result.get(Calendar.MONTH));
		assertEquals("Date",expected.get(Calendar.DATE),result.get(Calendar.DATE));
		assertEquals("Hour",expected.get(Calendar.HOUR),result.get(Calendar.HOUR));
		assertEquals("Minute",expected.get(Calendar.MINUTE),result.get(Calendar.MINUTE));

		String messg = new Object(){}.getClass().getEnclosingMethod().getName(); 
		System.out.println("---" + messg + "---");

		System.out.println("time  =" + time.toString());
		System.out.println("return=" + result.getTime().toString());
		
		

	}
	
	@Test
	 
	// Test for a return date that is 5 minutes ahead.
	 
	public void testGetPopup5min_4() {
		
		//set hour and minutes of day 
		//h=0=0:00, h=1=1:00, h=-1=23:00
		//m=0=:00, m=1=:01, m=-1=:59
		int h=1;
		int m=0;
		
		//set offset for current time to match
		long hDiff = dbUtil.hrsInMilli(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) - dbUtil.hrsInMilli(h); // + dbUtil.hrsInMilli(h);
		long mDiff = dbUtil.minInMilli(Calendar.getInstance().get(Calendar.MINUTE))+ dbUtil.get5Milli()-dbUtil.minInMilli(m); // + dbUtil.minInMilli(m);
		
		//create new Date with time offset
		Date time = new Date(System.currentTimeMillis()- hDiff - mDiff);
		
		//create Calendars from each to compare
		Calendar expected = new GregorianCalendar();
		expected.setTimeInMillis(time.getTime());
		
		Calendar result = new GregorianCalendar();
		result.setTime(app1.get5BeforeEnd(h, m));
		
		//Assert
		assertEquals("Year",expected.get(Calendar.YEAR),result.get(Calendar.YEAR));
		assertEquals("Month",expected.get(Calendar.MONTH),result.get(Calendar.MONTH));
		assertEquals("Date",expected.get(Calendar.DATE),result.get(Calendar.DATE));
		assertEquals("Hour",expected.get(Calendar.HOUR),result.get(Calendar.HOUR));
		assertEquals("Minute",expected.get(Calendar.MINUTE),result.get(Calendar.MINUTE));

		String messg = new Object(){}.getClass().getEnclosingMethod().getName(); 
		System.out.println("---" + messg + "---");

		System.out.println("time  =" + time.toString());
		System.out.println("return=" + result.getTime().toString());
		
		

	}
	
	
	@Test
	
	 // Test for a return date that is 5 minutes ahead.
	 
	public void testGetPopup5min_5() {
		
		//set hour and minutes of day 
		//h=0=0:00, h=1=1:00, h=-1=23:00
		//m=0=:00, m=1=:01, m=-1=:59
		int h=1;
		int m=1;
		
		//set offset for current time to match
		long hDiff = dbUtil.hrsInMilli(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) - dbUtil.hrsInMilli(h); // + dbUtil.hrsInMilli(h);
		long mDiff = dbUtil.minInMilli(Calendar.getInstance().get(Calendar.MINUTE))+ dbUtil.get5Milli()-dbUtil.minInMilli(m); // + dbUtil.minInMilli(m);
		
		//create new Date with time offset
		Date time = new Date(System.currentTimeMillis()- hDiff - mDiff);
		
		//create Calendars from each to compare
		Calendar expected = new GregorianCalendar();
		expected.setTimeInMillis(time.getTime());
		
		Calendar result = new GregorianCalendar();
		result.setTime(app1.get5BeforeEnd(h, m));
		
		//Assert
		assertEquals("Year",expected.get(Calendar.YEAR),result.get(Calendar.YEAR));
		assertEquals("Month",expected.get(Calendar.MONTH),result.get(Calendar.MONTH));
		assertEquals("Date",expected.get(Calendar.DATE),result.get(Calendar.DATE));
		assertEquals("Hour",expected.get(Calendar.HOUR),result.get(Calendar.HOUR));
		assertEquals("Minute",expected.get(Calendar.MINUTE),result.get(Calendar.MINUTE));

		String messg = new Object(){}.getClass().getEnclosingMethod().getName(); 
		System.out.println("---" + messg + "---");

		System.out.println("time  =" + time.toString());
		System.out.println("return=" + result.getTime().toString());
		
		

	}
	
	@Test
	 
	// Test for a return date that is 5 minutes ahead.
	 
	public void testGetPopup5min_6() {
		
		//set hour and minutes of day 
		//h=0=0:00, h=1=1:00, h=-1=23:00
		//m=0=:00, m=1=:01, m=-1=:59
		int h=1;
		int m=-1;
		
		//set offset for current time to match
		long hDiff = dbUtil.hrsInMilli(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) - dbUtil.hrsInMilli(h); // + dbUtil.hrsInMilli(h);
		long mDiff = dbUtil.minInMilli(Calendar.getInstance().get(Calendar.MINUTE))+ dbUtil.get5Milli()-dbUtil.minInMilli(m); // + dbUtil.minInMilli(m);
		
		//create new Date with time offset
		Date time = new Date(System.currentTimeMillis()- hDiff - mDiff);
		
		//create Calendars from each to compare
		Calendar expected = new GregorianCalendar();
		expected.setTimeInMillis(time.getTime());
		
		Calendar result = new GregorianCalendar();
		result.setTime(app1.get5BeforeEnd(h, m));
		
		//Assert
		assertEquals("Year",expected.get(Calendar.YEAR),result.get(Calendar.YEAR));
		assertEquals("Month",expected.get(Calendar.MONTH),result.get(Calendar.MONTH));
		assertEquals("Date",expected.get(Calendar.DATE),result.get(Calendar.DATE));
		assertEquals("Hour",expected.get(Calendar.HOUR),result.get(Calendar.HOUR));
		assertEquals("Minute",expected.get(Calendar.MINUTE),result.get(Calendar.MINUTE));

		String messg = new Object(){}.getClass().getEnclosingMethod().getName(); 
		System.out.println("---" + messg + "---");

		System.out.println("time  =" + time.toString());
		System.out.println("return=" + result.getTime().toString());
		
		

	}
	
	@Test
	
	// Test for a return date that is 5 minutes ahead.
	 
	public void testGetPopup5min_7() {
		
		//set hour and minutes of day 
		//h=0=0:00, h=1=1:00, h=-1=23:00
		//m=0=:00, m=1=:01, m=-1=:59
		int h=-1;
		int m=0;
		
		//set offset for current time to match
		long hDiff = dbUtil.hrsInMilli(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) - dbUtil.hrsInMilli(h); // + dbUtil.hrsInMilli(h);
		long mDiff = dbUtil.minInMilli(Calendar.getInstance().get(Calendar.MINUTE))+ dbUtil.get5Milli()-dbUtil.minInMilli(m); // + dbUtil.minInMilli(m);
		
		//create new Date with time offset
		Date time = new Date(System.currentTimeMillis()- hDiff - mDiff);
		
		//create Calendars from each to compare
		Calendar expected = new GregorianCalendar();
		expected.setTimeInMillis(time.getTime());
		
		Calendar result = new GregorianCalendar();
		result.setTime(app1.get5BeforeEnd(h, m));
		
		//Assert
		assertEquals("Year",expected.get(Calendar.YEAR),result.get(Calendar.YEAR));
		assertEquals("Month",expected.get(Calendar.MONTH),result.get(Calendar.MONTH));
		assertEquals("Date",expected.get(Calendar.DATE),result.get(Calendar.DATE));
		assertEquals("Hour",expected.get(Calendar.HOUR),result.get(Calendar.HOUR));
		assertEquals("Minute",expected.get(Calendar.MINUTE),result.get(Calendar.MINUTE));

		String messg = new Object(){}.getClass().getEnclosingMethod().getName(); 
		System.out.println("---" + messg + "---");

		System.out.println("time  =" + time.toString());
		System.out.println("return=" + result.getTime().toString());
		
		

	}
	
	@Test
	
	 // Test for a return date that is 5 minutes ahead.
	 
	public void testGetPopup5min_8() {
		
		//set hour and minutes of day 
		//h=0=0:00, h=1=1:00, h=-1=23:00
		//m=0=:00, m=1=:01, m=-1=:59
		int h=-1;
		int m=1;
		
		//set offset for current time to match
		long hDiff = dbUtil.hrsInMilli(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) - dbUtil.hrsInMilli(h); // + dbUtil.hrsInMilli(h);
		long mDiff = dbUtil.minInMilli(Calendar.getInstance().get(Calendar.MINUTE))+ dbUtil.get5Milli()-dbUtil.minInMilli(m); // + dbUtil.minInMilli(m);
		
		//create new Date with time offset
		Date time = new Date(System.currentTimeMillis()- hDiff - mDiff);
		
		//create Calendars from each to compare
		Calendar expected = new GregorianCalendar();
		expected.setTimeInMillis(time.getTime());
		
		Calendar result = new GregorianCalendar();
		result.setTime(app1.get5BeforeEnd(h, m));
		
//Assert
		assertEquals("Year",expected.get(Calendar.YEAR),result.get(Calendar.YEAR));
		assertEquals("Month",expected.get(Calendar.MONTH),result.get(Calendar.MONTH));
		assertEquals("Date",expected.get(Calendar.DATE),result.get(Calendar.DATE));
		assertEquals("Hour",expected.get(Calendar.HOUR),result.get(Calendar.HOUR));
		assertEquals("Minute",expected.get(Calendar.MINUTE),result.get(Calendar.MINUTE));

		String messg = new Object(){}.getClass().getEnclosingMethod().getName();
		System.out.println("---" + messg + "---");
		
		System.out.println("time  =" + time.toString());
		System.out.println("return=" + result.getTime().toString());
		
		

	}
	
	@Test
	
	 // Test for a return date that is 5 minutes ahead.
	 
	public void testGetPopup5min_9() {
		
		//set hour and minutes of day 
		//h=0=0:00, h=1=1:00, h=-1=23:00
		//m=0=:00, m=1=:01, m=-1=:59
		int h=-1;
		int m=-1;
		
		//set offset for current time to match
		long hDiff = dbUtil.hrsInMilli(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) - dbUtil.hrsInMilli(h); // + dbUtil.hrsInMilli(h);
		long mDiff = dbUtil.minInMilli(Calendar.getInstance().get(Calendar.MINUTE))+ dbUtil.get5Milli()-dbUtil.minInMilli(m); // + dbUtil.minInMilli(m);
		
		//create new Date with time offset
		Date time = new Date(System.currentTimeMillis()- hDiff - mDiff);
		
		//create Calendars from each to compare
		Calendar expected = new GregorianCalendar();
		expected.setTimeInMillis(time.getTime());
		
		Calendar result = new GregorianCalendar();
		result.setTime(app1.get5BeforeEnd(h, m));
		
		//Assert
		assertEquals("Year",expected.get(Calendar.YEAR),result.get(Calendar.YEAR));
		assertEquals("Month",expected.get(Calendar.MONTH),result.get(Calendar.MONTH));
		assertEquals("Date",expected.get(Calendar.DATE),result.get(Calendar.DATE));
		assertEquals("Hour",expected.get(Calendar.HOUR),result.get(Calendar.HOUR));
		assertEquals("Minute",expected.get(Calendar.MINUTE),result.get(Calendar.MINUTE));


		
		String messg = new Object(){}.getClass().getEnclosingMethod().getName(); 
		System.out.println("---" + messg + "---");

		System.out.println("time  =" + time.toString());
		System.out.println("return=" + result.getTime().toString());
		
		

	}
	
	
	@Test
	// Test for return date that is same as current time with 1 minute difference
	public void testGetEndTime_1() {
		
		//set hour and minutes of day 
				//h=0=0:00, h=1=1:00, h=-1=23:00
				//m=0=:00, m=1=:01, m=-1=:59
				int h=0;
				int m=0;
				
				//set offset for current time to match
				long hDiff = dbUtil.hrsInMilli(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) - dbUtil.hrsInMilli(h); 
				long mDiff = dbUtil.minInMilli(Calendar.getInstance().get(Calendar.MINUTE)) -dbUtil.minInMilli(m) +60000; 
				
				//create new Date with time offset
				Date time = new Date(System.currentTimeMillis()- hDiff - mDiff);
				
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

	@Test
	// Test for return date that is same as current time with 1 minute difference
	public void testGetEndTime_2() {
		
		//set hour and minutes of day 
				//h=0=0:00, h=1=1:00, h=-1=23:00
				//m=0=:00, m=1=:01, m=-1=:59
				int h=0;
				int m=1;
				
				//set offset for current time to match
				long hDiff = dbUtil.hrsInMilli(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) - dbUtil.hrsInMilli(h); 
				long mDiff = dbUtil.minInMilli(Calendar.getInstance().get(Calendar.MINUTE)) -dbUtil.minInMilli(m) +60000; 
				
				//create new Date with time offset
				Date time = new Date(System.currentTimeMillis()- hDiff - mDiff);
				
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
	
	@Test
	// Test for return date that is same as current time with 1 minute difference
	public void testGetEndTime_3() {
		
		//set hour and minutes of day 
				//h=0=0:00, h=1=1:00, h=-1=23:00
				//m=0=:00, m=1=:01, m=-1=:59
				int h=0;
				int m=-1;
				
				//set offset for current time to match
				long hDiff = dbUtil.hrsInMilli(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) - dbUtil.hrsInMilli(h); 
				long mDiff = dbUtil.minInMilli(Calendar.getInstance().get(Calendar.MINUTE)) -dbUtil.minInMilli(m) +60000; 
				
				//create new Date with time offset
				Date time = new Date(System.currentTimeMillis()- hDiff - mDiff);
				
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

	@Test
	// Test for return date that is same as current time with 1 minute difference
	public void testGetEndTime_4() {
		
		//set hour and minutes of day 
				//h=0=0:00, h=1=1:00, h=-1=23:00
				//m=0=:00, m=1=:01, m=-1=:59
				int h=1;
				int m=0;
				
				//set offset for current time to match
				long hDiff = dbUtil.hrsInMilli(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) - dbUtil.hrsInMilli(h); 
				long mDiff = dbUtil.minInMilli(Calendar.getInstance().get(Calendar.MINUTE)) -dbUtil.minInMilli(m) +60000; 
				
				//create new Date with time offset
				Date time = new Date(System.currentTimeMillis()- hDiff - mDiff);
				
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

	@Test
	// Test for return date that is same as current time with 1 minute difference
	public void testGetEndTime_5() {
		
		//set hour and minutes of day 
				//h=0=0:00, h=1=1:00, h=-1=23:00
				//m=0=:00, m=1=:01, m=-1=:59
				int h=1;
				int m=1;
				
				//set offset for current time to match
				long hDiff = dbUtil.hrsInMilli(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) - dbUtil.hrsInMilli(h); 
				long mDiff = dbUtil.minInMilli(Calendar.getInstance().get(Calendar.MINUTE)) -dbUtil.minInMilli(m) +60000; 
				
				//create new Date with time offset
				Date time = new Date(System.currentTimeMillis()- hDiff - mDiff);
				
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

	@Test
	// Test for return date that is same as current time with 1 minute difference
	public void testGetEndTime_6() {
		
		//set hour and minutes of day 
				//h=0=0:00, h=1=1:00, h=-1=23:00
				//m=0=:00, m=1=:01, m=-1=:59
				int h=1;
				int m=-1;
				
				//set offset for current time to match
				long hDiff = dbUtil.hrsInMilli(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) - dbUtil.hrsInMilli(h); 
				long mDiff = dbUtil.minInMilli(Calendar.getInstance().get(Calendar.MINUTE)) -dbUtil.minInMilli(m) +60000; 
				
				//create new Date with time offset
				Date time = new Date(System.currentTimeMillis()- hDiff - mDiff);
				
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

	@Test
	// Test for return date that is same as current time with 1 minute difference
	public void testGetEndTime_7() {
		
		//set hour and minutes of day 
				//h=0=0:00, h=1=1:00, h=-1=23:00
				//m=0=:00, m=1=:01, m=-1=:59
				int h=-1;
				int m=0;
				
				//set offset for current time to match
				long hDiff = dbUtil.hrsInMilli(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) - dbUtil.hrsInMilli(h); 
				long mDiff = dbUtil.minInMilli(Calendar.getInstance().get(Calendar.MINUTE)) -dbUtil.minInMilli(m) +60000; 
				
				//create new Date with time offset
				Date time = new Date(System.currentTimeMillis()- hDiff - mDiff);
				
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

	@Test
	// Test for return date that is same as current time with 1 minute difference
	public void testGetEndTime_8() {
		
		//set hour and minutes of day 
				//h=0=0:00, h=1=1:00, h=-1=23:00
				//m=0=:00, m=1=:01, m=-1=:59
				int h=-1;
				int m=1;
				
				//set offset for current time to match
				long hDiff = dbUtil.hrsInMilli(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) - dbUtil.hrsInMilli(h); 
				long mDiff = dbUtil.minInMilli(Calendar.getInstance().get(Calendar.MINUTE)) -dbUtil.minInMilli(m) +60000; 
				
				//create new Date with time offset
				Date time = new Date(System.currentTimeMillis()- hDiff - mDiff);
				
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

	@Test
	// Test for return date that is same as current time with 1 minute difference
	public void testGetEndTime_9() {
		
		//set hour and minutes of day 
				//h=0=0:00, h=1=1:00, h=-1=23:00
				//m=0=:00, m=1=:01, m=-1=:59
				int h=-1;
				int m=-1;
				
				//set offset for current time to match
				long hDiff = dbUtil.hrsInMilli(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) - dbUtil.hrsInMilli(h); 
				long mDiff = dbUtil.minInMilli(Calendar.getInstance().get(Calendar.MINUTE)) -dbUtil.minInMilli(m) +60000; 
				
				//create new Date with time offset
				Date time = new Date(System.currentTimeMillis()- hDiff - mDiff);
				
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

	






}

