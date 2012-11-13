package testing.logicUnitTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Logic.appController;

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
	
	/** Test Case ID: PSM014_End_Semester_Clear-UnitTest-C01
	 * Purpose: To test parsing of a valid formatted date string. Should conform to DateFormat in C01 Test Identification and Setup.
	 * Date Created: 09/11/12
	 * Author: David Garcia
	 */
	@Test
	public void testDateParser_1() {

		String date = "01/02/12";
		app1.dateParser(date);
		
		assertEquals("Clear Month", 1, app1.getClearMonth());
		assertEquals("Clear Date",2,app1.getClearDate());
		assertEquals("Clear Year",12,app1.getClearYear());

	}
	
	/** Test Case ID: PSM014_End_Semester_Clear-UnitTest-C02
	 * Purpose: To test parsing of an invalid formatted date string. Should not conform to DateFormat in C01 Test 
	 * 			Identification and Setup. Specifically the month is incorrect.
	 * Date Created: 09/11/12
	 * Author: David Garcia
	 */
	@Test
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
	
	/** Test Case ID: PSM014_End_Semester_Clear-UnitTest-C03
	 * Purpose: To test parsing of an invalid formatted date string. Should not conform to DateFormat in C01 Test 
	 * 			Identification and Setup. Specifically the date is incorrect.
	 * Date Created: 09/11/12
	 * Author: David Garcia
	 */
	@Test
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
	
	/** Test Case ID: PSM014_End_Semester_Clear-UnitTest-C04
	 * Purpose: To test parsing of an invalid formatted date string. Should not conform to DateFormat in C01 Test 
	 * 			Identification and Setup. Specifically the year is incorrect.
	 * Date Created: 09/11/12
	 * Author: David Garcia
	 */
	@Test
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
	
	/** Test Case ID: PSM014_End_Semester_Clear-UnitTest-C05
	 * Purpose: To test parsing of an invalid formatted date string. Should not conform to DateFormat in C01 Test 
	 * 			Identification and Setup. Specifically the wildcard + is more than 1 character.(2 spaces)
	 * Date Created: 09/11/12
	 * Author: David Garcia
	 */
	@Test
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
	
	/** Test Case ID: PSM014_End_Semester_Clear-UnitTest-C06
	 * Purpose: To test parsing of an invalid formatted date string. Should not conform to DateFormat in C01 Test 
	 * 			Identification and Setup. Specifically the wildcard + is too short.
	 * Date Created: 09/11/12
	 * Author: David Garcia 
	 */
	@Test
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

	/** Test Case ID: PSM001_Login-UnitTest-07
	 * Purpose: To test parsing of a valid time string. Should conform to TimeFormat in C07 Test Identification and Objective.
	 * Date Created: 09/13/12
	 * Author: David Garcia
	 */
	@Test
	public void testTimerParser_1() {
		
		String hour = "11:56";
		app1.timerParser(hour);
		
		assertEquals("Hour", 11, app1.getHr());
		assertEquals("Minute",56,app1.getMin());
		

	}
	
	/** Test Case ID: PSM001_Login-UnitTest-08
	 * Purpose: To test parsing of an invalid time string. Should not conform to TimeFormat in C07 Test Identification 
	 * 			and Objective.Specifically the ‘hh’ is not a valid value.
	 * Date Created: 09/13/12
	 * Author: David Garcia
	 */
	@Test
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
	
	/** Test Case ID: PSM001_Login-UnitTest-09
	 * Purpose: To test parsing of an invalid time string. Should not conform to TimeFormat in C07 Test Identification 
	 * 			and Objective.Specifically the ‘mm’ is not a valid value;
	 * Date Created: 09/13/12
	 * Author: David Garcia
	 */
	@Test
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
	
	/** Test Case ID: PSM001_Login-UnitTest-10
	 * Purpose: To test parsing of an invalid time string. Should not conform to TimeFormat in C07 Test Identification 
	 * 			and Objective.Specifically the ‘:’ is not a valid length, too long;
	 * Date Created: 09/13/12
	 * Author: David Garcia
	 */
	@Test
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
	
	/** Test Case ID: PSM001_Login-UnitTest-11
	 * Purpose: To test parsing of an invalid time string. Should not conform to TimeFormat in C07 Test Identification 
	 * 			and Objective.Specifically the ‘:’ is not a valid length, too short;
	 * Date Created: 09/13/12
	 * Author: David Garcia
	 */
	@Test
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
	
	/** Test Case ID: PSM001_Login-UnitTest-12
	 * Purpose: To test parsing of an invalid time string. Should not conform to TimeFormat in C07 Test Identification 
	 * 			and Objective.Specifically the string is not a valid length, too short;
	 * Date Created: 09/13/12
	 * Author: David Garcia
	 */
	@Test
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
