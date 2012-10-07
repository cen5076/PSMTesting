package Logic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import testUtil.Course;
import testUtil.DBUtil;

public class appControllerParserTest {

	appController app1;
	DBUtil dbUtil;
	Course c1;
	
	@Before
	public void setUp() throws Exception {
		
		app1 = new appController();
		dbUtil = new DBUtil();
		c1 = new Course(1234,"Sub","Nam","Semester",Course.STARTDATE,Course.ENDDATE);

	}

	@After
	public void tearDown() throws Exception {
		
		app1 = null;
		dbUtil = null;
		c1 = null;
	}
	
	@Test //PSM014_End_Semester_Clear-UnitTest-C01
	/* Test a valid formatted string */
	public void testDateParser_1() {

		String date = "01/02/12";
		app1.dateParser(date);
		
		assertEquals("Clear Month", 1, app1.getClearMonth());
		assertEquals("Clear Date",2,app1.getClearDate());
		assertEquals("Clear Year",12,app1.getClearYear());

	}
	
	@Test //PSM014_End_Semester_Clear-UnitTest-C02
	/* Test an invalid formatted string for 'mm'*/
	public void testDateParser_2() {

		String date = "ss/the rest no matter";
		
		try{
			app1.dateParser(date);
		}
		catch(Exception NumberFormatException){
			
			assertEquals("Clear Month",0,app1.getClearMonth());
			assertEquals("Clear Date",0,app1.getClearDate());
			assertEquals("Clear Year",0,app1.getClearYear());
			return;
		}
		
		fail("Exception not Thrown");

		
	}
	



	@Test //PSM014_End_Semester_Clear-UnitTest-C03
	/* Test an invalid formatted string for 'dd'*/
	public void testDateParser_3() {
	
		String date = "12the rest no matter";
		
		try{
			app1.dateParser(date);
		}
		catch(Exception NumberFormatException){
			
			assertEquals("Clear Month",12,app1.getClearMonth());
			assertEquals("Clear Date",0,app1.getClearDate());
			assertEquals("Clear Year",0,app1.getClearYear());
			return;
		}
		
		fail("Exception not Thrown");
	
		
	}
	
	
	@Test //PSM014_End_Semester_Clear-UnitTest-C04
	/* Test an invalid formatted string for 'yy'*/
	public void testDateParser_4() {
	
		String date = "12-66the rest no matter";
		
		try{
			app1.dateParser(date);
		}
		catch(Exception NumberFormatException){
			
			assertEquals("Clear Month",12,app1.getClearMonth());
			assertEquals("Clear Date",66,app1.getClearDate());
			assertEquals("Clear Year",0,app1.getClearYear());
			return;
		}
		
		fail("Exception not Thrown");
	
		
	}
	
	@Test //PSM014_End_Semester_Clear-UnitTest-C05
	/* Test an invalid formatted string for '+' more than 1 char*/
	public void testDateParser_5() {
	
		String date = "12  2276the rest no matter";
		
		try{
			app1.dateParser(date);
		}
		catch(Exception NumberFormatException){
			
			assertEquals("Clear Month",12,app1.getClearMonth());
			assertEquals("Clear Date",0,app1.getClearDate());
			assertEquals("Clear Year",0,app1.getClearYear());
			return;
		}
		
		fail("Exception not Thrown");
	
		
	}
	
	@Test //PSM014_End_Semester_Clear-UnitTest-C06
	/* Test an invalid formatted string for '+' less one char*/
	public void testDateParser_6() {
	
		String date = "12";
		
		try{
			app1.dateParser(date);
			
		}
		catch(Exception e){
			assertEquals("Clear Month",12,app1.getClearMonth());
			assertEquals("Clear Date",0,app1.getClearDate());
			assertEquals("Clear Year",0,app1.getClearYear());
			return;
			
		}
		
		fail("Exception not thrown");
	
		
		
		
	}


	@Test //PSM001_Login-UnitTest-C07
	// Test a valid format string
	public void testTimerParser_1() {
		
		String hour = "11:56";
		app1.timerParser(hour);
		
		assertEquals("Hour", 11, app1.getHr());
		assertEquals("Minute",56,app1.getMin());
		

	}
	
	@Test //PSM001_Login-UnitTest-C08
	//Test invalid string format hh
	public void testTimerParser_2() {
		
		String hour = "ss:56";
		
		try{
			app1.timerParser(hour);
		}
		
		catch(NumberFormatException e){
			
			//DEBUG System.out.println(e.toString());
			assertEquals("Hour", 0, app1.getHr());
			assertEquals("Minute",0,app1.getMin());
			return;
		}
		
		fail("No Exception Thrown");
	}
	
	@Test //PSM001_Login-UnitTest-C09
	//Test invalid string format mm
	public void testTimerParser_3() {
		

		String hour = "09: !the rest dont matter";
		
		try{
			app1.timerParser(hour);
		}
		
		catch(NumberFormatException e){
			
			//DEBUG System.out.println(e.toString());
			assertEquals("Hour", 9, app1.getHr());
			assertEquals("Minute",0,app1.getMin());
			return;
		}
		
		fail("No Exception Thrown");
	}
	
	@Test //PSM001_Login-UnitTest-C10
	//Test invalid string format '+' too long
	public void testTimerParser_4() {
		
		String hour = "09::07the rest dont matter";
		
		try{
			app1.timerParser(hour);
		}
		
		catch(NumberFormatException e){
			
			//DEBUG System.out.println(e.toString());
			assertEquals("Hour", 9, app1.getHr());
			assertEquals("Minute",0,app1.getMin());
			return;
		}
		
		fail("No Exception Thrown");
	}
	
	@Test //PSM001_Login-UnitTest-C11
	//Test invalid string format '+' too short
	public void testTimerParser_5() {
		
		String hour = "0907the rest dont matter";
		
		try{
			app1.timerParser(hour);
		}
		
		catch(NumberFormatException e){
			
			//DEBUG System.out.println(e.toString());
			assertEquals("Hour", 9, app1.getHr());
			assertEquals("Minute",0,app1.getMin());
			return;
		}
		
		fail("No Exception Thrown");
	}
	
	@Test //PSM001_Login-UnitTest-C12
	//Test invalid string format
	public void testTimerParser_6() {
		
		String hour = "9";
		
		try {
			app1.timerParser(hour);
		}
		
		catch(Exception e){
			
			//DEBUG System.out.println(e.toString());
			assertEquals("Hour", 0, app1.getHr());
			assertEquals("Minute",0,app1.getMin());
			return;
		}
		
		fail("No Exception Thrown");
	}

}
