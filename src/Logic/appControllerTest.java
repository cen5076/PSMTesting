package Logic;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
//import java.util.Timer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import testUtil.Course;
import testUtil.DBUtil;

//@SuppressWarnings("unused")
@SuppressWarnings("static-access")
public class appControllerTest {

	private appController app1;
	private Calendar testCal;
	private DBUtil util = new DBUtil();
	
	@Before
	public void setUp() throws Exception {
		
		app1 = new appController();
		//calendar used to initialize and compare.
		Date date = new Date();
		testCal = new GregorianCalendar();
		testCal.setTime(date);
		
		/* DEBUG 
		 * 
		 */
		//System.out.println("today is " + testCal.getTime().toGMTString());
		//System.exit(0);
		
	}

	@After
	public void tearDown() throws Exception {
		
		app1 = null;
	}

	@Test
	/* Constructor */
	public void testAppController() {
		
		/* TODO verify if need to check others */
		assertEquals("Zero Hours",0,app1.getHr());
		assertEquals("Zero Minutes",0,app1.getMin());
		//TODO check time requirement for ten minutes
		assertEquals("Ten Minutes",600000,app1.getTenmin());
		assertNotNull("Auto Clear", app1.getAutoClear());
		assertNotNull("Set Run",app1.getSetRun());
		assertNotNull("Timer",app1.getTimer());
		assertNotNull("Date",app1.getDate());
		assertNotNull("Date 2",app1.getDate2());
		assertFalse("Data Recieved",app1.isDataReceived());
		assertFalse("Ed Sched Select",app1.isEdSchedSel());
		assertFalse("Schedule Setup Select",app1.isSchedSetupSel());
		assertFalse("Logout Select",app1.isLoggedin());
		assertEquals("Counter",0,app1.getCounter());
		assertEquals("Class Ended",0,app1.getClassEnded());
		assertNotNull("DB",app1.getDb());
		assertNotNull("Ic",app1.getIc());
		
	}

	@Test
	/* Program Entry point */
	public void testMainLogIn() {
		fail("Not yet implemented");
	}
	
	@Test
	/* Program entry point */
	public void testMainClearDb() {
		fail("Not yet implemented");
	}
	
	@Test
	/* Program entry point */
	public void testMainLogout() {
		fail("Not yet implemented");
	}

	@Test
	/* if there is an end date that is equal to the current date */
	public void testCheckClear() {
		
		/* TODO add an end date equal to now */
		
		assertTrue("CheckClear- Date Match",app1.checkClear());
	}

	@Test
	/* Determines the end time and sets timers for various intervals */
	public void testCheckTimes() {
		fail("Not yet implemented");
	}

	@Test
	/* test setting of values from a course */
	public void testGetData() {
		
		Course c1 = new Course(1234,"Sub","Name","Semester","010112","123112");
		c1.fillDates(DBUtil.defaultDates);
		this.util.insertCourse(c1);
		
		app1.getData(c1.getCrseid());
		
		assertEquals("Subject",c1.getCrseSub(),app1.getDefSub());
		assertEquals("Semester",c1.getSemester(),app1.getDefSemester());
        assertEquals("CourseName",c1.getCrseNam(),app1.getDefCourseName());
        assertEquals("CourseStart",c1.getStartdt(),app1.getDefCourseStart());
        assertEquals("CourseEnd",c1.getEnddt(),app1.getDefCourseEnd());
        assertEquals("MonStart",c1.getMonStart(),app1.getDefMonStart());
        //defMonStart = db.fetchStartMon(course);
        assertEquals("MonEnd",c1.getMonEnd(),app1.getDefMonEnd());
        //defMonEnd = db.fetchEndMon(course);
        assertEquals("TueStart",c1.getTueStart(),app1.getDefTueStart());
        //defTueStart = db.fetchStartTue(course);
        assertEquals("TueEnd",c1.getTueEnd(),app1.getDefTueEnd());
        //defTueEnd = db.fetchEndTue(course);
        assertEquals("WedStart",c1.getWedStart(),app1.getDefWedStart());
        //defWedStart = db.fetchStartWed(course);
        assertEquals("WedEnd",c1.getWedEnd(),app1.getDefWedEnd());
        //defWedEnd = db.fetchEndWed(course);
        assertEquals("ThuStart",c1.getThuStart(),app1.getDefThuStart());
        //defThuStart = db.fetchStartThu(course);
        assertEquals("ThuEnd",c1.getThuEnd(),app1.getDefThuEnd());
        //defThuEnd = db.fetchEndThu(course);
        assertEquals("FriStart",c1.getFriStart(),app1.getDefFriStart());
        //defFriStart = db.fetchStartFri(course);
        assertEquals("FriEnd",c1.getFriEnd(),app1.getDefFriEnd());
        //defFriEnd = db.fetchEndFri(course);
        assertEquals("SatStart",c1.getSatStart(),app1.getDefSatStart());
        //defSatStart = db.fetchStartSat(course);
        assertEquals("SatEnd",c1.getSatEnd(),app1.getDefSatEnd());
        //defSatEnd = db.fetchEndSat(course);   
        
	}
		 

	@Test
	/* Make sure sleep takes longer than 600 milliseconds */
	public void testSleep() {
		/* TODO */
		//get current time
		
		//Calendar start = new GregorianCalendar();
		long start = System.currentTimeMillis();
		app1.sleep(600);
		//get current time after
		//Calendar end = new GregorianCalendar();
		long end = System.currentTimeMillis();
		//may take a bit longer;
		assertEquals("Sleep",600,end-start);
	}

	@Test
	public void testLogIn() {
		/* TODO Setup username and password */
		
		app1.LogIn();
		assertTrue("Log In",app1.isLoggedin());
	}

	@Test
	public void testGetCon() {
		
		/* TODO depends on previous test Success */
		assertNotNull("DB Connection",app1.getDb());
	}

	@Test
	public void testSetTime() {
		
		app1.setTime(this.testCal.YEAR,this.testCal.MONTH,this.testCal.DATE,this.testCal.HOUR_OF_DAY, this.testCal.MINUTE);
		assertEquals("Year",this.testCal.YEAR,app1.getSetRun().YEAR);
		assertEquals("Month",this.testCal.MONTH,app1.getSetRun().MONTH);
		assertEquals("Date",this.testCal.DATE,app1.getSetRun().DATE);
		assertEquals("Hours",this.testCal.HOUR_OF_DAY,app1.getSetRun().HOUR_OF_DAY);
		assertEquals("Minutes",this.testCal.MINUTE,app1.getSetRun().MINUTE);
		
		
	}

	@Test
	public void testGetTime() {
		
		/* TODO initialize with a Date object or get date object from calendar
		 * and use for comparison to stored values.
		 */
		assertEquals("Time",testCal.getTimeInMillis(),app1.getSetRun().getTimeInMillis());
	}

	@Test
	public void testGetTimeMillis() {

		assertEquals("TimeMilli",testCal.getTimeInMillis(),app1.getSetRun().getTimeInMillis());
	}

	@Test
	public void testTimerParser() {
		assertEquals("HR", testCal.HOUR,app1.getHr());
		assertEquals("Min",testCal.MINUTE,app1.getMin());
	}

	@Test
	public void testDateParser() {
		assertEquals("Month",testCal.MONTH,app1.getSetRun().MONTH);
		assertEquals("Date",testCal.DATE,app1.getSetRun().DATE);
		assertEquals("Year",testCal.YEAR,app1.getSetRun().YEAR);
	}

	@Test
	public void testReturnHr() {
		assertEquals("HR",testCal.HOUR,app1.getHr());
	}

	@Test
	public void testReturnMin() {
		assertEquals("Min",testCal.MINUTE,app1.getMin());
	}

	@Test
	public void testGetEndTime() {
		
		Date test = app1.getEndTime(testCal.HOUR, testCal.MINUTE);
		assertEquals("Date",testCal.getTime(),test);
	}

	
	@Test
	public void testSetSemesterClear() {
		/* TODO pass a year, month, day */
		app1.setSemesterClear(testCal.YEAR, testCal.MONTH,testCal.DATE, testCal.HOUR, testCal.MINUTE);
		
		assertEquals("Year",testCal.YEAR,app1.getAutoClear().YEAR);
		assertEquals("Month",testCal.MONTH,app1.getAutoClear().MONTH);
		assertEquals("Date",testCal.DATE,app1.getAutoClear().DATE);
		assertEquals("Hour",testCal.HOUR,app1.getAutoClear().HOUR);
		assertEquals("Minute",testCal.MINUTE,app1.getAutoClear().MINUTE);
	}

	@Test
	public void testGetSemesterClear() {
		fail("Not yet implemented");
	}

	@Test
	public void testGet15BeforeEnd() {
		fail("Not yet implemented");
	}

	@Test
	public void testGet5BeforeEnd() {
		fail("Not yet implemented");
	}

	@Test
	public void testAutoExit() {
		fail("Not yet implemented");
	}

	@Test
	public void testAutoClear() {
		fail("Not yet implemented");
	}

	/*@Test
	public void testObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetClass() {
		fail("Not yet implemented");
	}

	@Test
	public void testHashCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testEquals() {
		fail("Not yet implemented");
	}

	@Test
	public void testClone() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotify() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotifyAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testWaitLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testWaitLongInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testWait() {
		fail("Not yet implemented");
	}

	@Test
	public void testFinalize() {
		fail("Not yet implemented");
	}
*/
}
