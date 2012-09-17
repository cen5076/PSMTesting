package Logic;

import static org.junit.Assert.*;

import java.util.ArrayList;
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
	//@SuppressWarnings("unused")
	//private DBUtil util = new DBUtil();
	public ArrayList<Course> courseList;
	public Course c1;
	public Course c2; 
	public Course c3; 

	
	@Before
	public void setUp() throws Exception {
		
		this.app1 = new appController();
		Date date = app1.getDate();
		this.courseList = new ArrayList<Course>();
		c1= new Course(1111,"Sub","Name1","Semester","010112","123112");
		c2= new Course(2222,"Sub","Name2","Semester","010112","123112");
		c3= new Course(3333,"Sub","Name3","Semester","010112","123112");
		
		c1.fillDates(DBUtil.defaultDates);
		c2.fillDates(DBUtil.defaultDates);
		c3.fillDates(DBUtil.defaultDates);
		
		courseList.add(c1);
		courseList.add(c2);
		courseList.add(c3);
		
		testCal = new GregorianCalendar();
		testCal.setTime(date);
		
		this.courseList.add(c1);
		this.courseList.add(c2);
		this.courseList.add(c3);
		
		this.app1.getDb().initializeStub(courseList);
		
		// DEBUG 
		//System.out.println("today is " + testCal.getTime().toGMTString());
		//System.exit(0);
		
	}

	@After
	public void tearDown() throws Exception {
		this.c1.clear();
		this.c2.clear();
		this.c3.clear();
		this.courseList = null;
		app1 = null;
	}
/*
	@Test
	// Constructor 
	public void testAppController() {
		
		System.out.println("testAppController");
		// TODO verify if need to check others 
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
		
		System.out.println("END testAppController");
	}

	@Test
	// Program Entry point 
	public void testMainLogIn() {
		System.out.println();

		fail("Not yet implemented");
		System.out.println();

	}
	
	@Test
	// Program entry point 
	public void testMainClearDb() {
		System.out.println();

		fail("Not yet implemented");
		System.out.println();

	}
	
	@Test
	// Program entry point 
	public void testMainLogout() {
		System.out.println();

		fail("Not yet implemented");
		System.out.println();

	}

	@Test
	// if there is an end date that is equal to the current date 
	public void testCheckClear() {
		System.out.println("testCheckClear");
		
		// TODO add an end date equal to now 

		assertTrue("CheckClear- Date Match",app1.checkClear());
		System.out.println("END testCheckClear");

	}

	@Test
	// Determines the end time and sets timers for various intervals
	public void testCheckTimes() {
		fail("Not yet implemented");
	}
*/
	
	@Test
	// test setting of values from a course 
	public void testGetData() {
		
		System.out.println("testGetData");
		//this.c1.fillDates(DBUtil.defaultDates);
		//this.util.insertCourse(c1);
		app1.getData(this.c1.getCrseid());
		System.out.println(this.c1.getMonEnd());
		
		assertEquals("Subject",this.c1.getCrseSub(),app1.getDefSub());
		assertEquals("Semester",this.c1.getSemester(),app1.getDefSemester());
        assertEquals("CourseName",this.c1.getCrseNam(),app1.getDefCourseName());
        assertEquals("CourseStart",this.c1.getStartdt(),app1.getDefCourseStart());
        assertEquals("CourseEnd",this.c1.getEnddt(),app1.getDefCourseEnd());
        assertEquals("MonStart",this.c1.getMonStart(),app1.getDefMonStart());
        //defMonStart = db.fetchStartMon(course);
        assertEquals("MonEnd",this.c1.getMonEnd(),app1.getDefMonEnd());
        //defMonEnd = db.fetchEndMon(course);
        assertEquals("TueStart",this.c1.getTueStart(),app1.getDefTueStart());
        //defTueStart = db.fetchStartTue(course);
        assertEquals("TueEnd",this.c1.getTueEnd(),app1.getDefTueEnd());
        //defTueEnd = db.fetchEndTue(course);
        assertEquals("WedStart",this.c1.getWedStart(),app1.getDefWedStart());
        //defWedStart = db.fetchStartWed(course);
        assertEquals("WedEnd",this.c1.getWedEnd(),app1.getDefWedEnd());
        //defWedEnd = db.fetchEndWed(course);
        assertEquals("ThuStart",this.c1.getThuStart(),app1.getDefThuStart());
        //defThuStart = db.fetchStartThu(course);
        assertEquals("ThuEnd",this.c1.getThuEnd(),app1.getDefThuEnd());
        //defThuEnd = db.fetchEndThu(course);
        assertEquals("FriStart",this.c1.getFriStart(),app1.getDefFriStart());
        //defFriStart = db.fetchStartFri(course);
        assertEquals("FriEnd",this.c1.getFriEnd(),app1.getDefFriEnd());
        //defFriEnd = db.fetchEndFri(course);
        assertEquals("SatStart",this.c1.getSatStart(),app1.getDefSatStart());
        //defSatStart = db.fetchStartSat(course);
        assertEquals("SatEnd",this.c1.getSatEnd(),app1.getDefSatEnd());
        //defSatEnd = db.fetchEndSat(course);   
     
		System.out.println("END testGetData");

	}
		 
/*
	@Test
	// Make sure sleep takes longer than 625 milliseconds 
	public void testSleep() {
		System.out.println("testSleep");

		// TODO 
		//get current time
		
		//Calendar start = new GregorianCalendar();
		long start = System.currentTimeMillis();
		app1.sleep(600);
		//get current time after
		//Calendar end = new GregorianCalendar();
		long end = System.currentTimeMillis();
		//may take a bit longer;
		assertTrue("Sleep",end-start<625);
		
		System.out.println("END testSleep");

	}

	@Test
	public void testLogIn() {
		System.out.println("testLogIn");

		// TODO Setup username and password 
		
		app1.LogIn();
		assertTrue("Log In",app1.isLoggedin());
		System.out.println("END testLogIn");

	}

	@Test
	public void testGetCon() {
		System.out.println("testGetCon");
		
		// TODO depends on previous test Success 
		assertNotNull("DB Connection",app1.getDb());
		System.out.println("END testGetCon");

	}

	@Test
	public void testSetTime() {

		System.out.println("testSetTime");

		app1.setTime(this.testCal.YEAR,this.testCal.MONTH,this.testCal.DATE,this.testCal.HOUR_OF_DAY, this.testCal.MINUTE);
		assertEquals("Year",this.testCal.YEAR,app1.getSetRun().YEAR);
		assertEquals("Month",this.testCal.MONTH,app1.getSetRun().MONTH);
		assertEquals("Date",this.testCal.DATE,app1.getSetRun().DATE);
		assertEquals("Hours",this.testCal.HOUR_OF_DAY,app1.getSetRun().HOUR_OF_DAY);
		assertEquals("Minutes",this.testCal.MINUTE,app1.getSetRun().MINUTE);
		
		System.out.println("END testSetTime");
		
	}

	@Test
	public void testGetTime() {

		System.out.println("testGetTime");

		// TODO initialize with a Date object or get date object from calendar
		// and use for comparison to stored values.
		 
		assertEquals("Time",testCal.getTimeInMillis(),app1.getSetRun().getTimeInMillis());
		System.out.println("END testGetTime");

	}

	@Test
	public void testGetTimeMillis() {

		System.out.println("testGetTimeMillis");

		assertEquals("TimeMilli",testCal.getTimeInMillis(),app1.getSetRun().getTimeInMillis());
		
		System.out.println("END testGetTimeMillis");

	}

	@Test
	public void testTimerParser() {
		System.out.println("testTimerParser");

		assertEquals("HR", testCal.HOUR,app1.getHr());
		assertEquals("Min",testCal.MINUTE,app1.getMin());
		System.out.println("END testTimerParser");

	}

	@Test
	public void testDateParser() {
		System.out.println("testDateParser");

		assertEquals("Month",testCal.MONTH,app1.getSetRun().MONTH);
		assertEquals("Date",testCal.DATE,app1.getSetRun().DATE);
		assertEquals("Year",testCal.YEAR,app1.getSetRun().YEAR);
		System.out.println("END testDateParser");

	}

	@Test
	public void testReturnHr() {
		System.out.println("testReturnHr");

		assertEquals("HR",testCal.HOUR,app1.getHr());
		System.out.println("END testReturnHr");

	}

	@Test
	public void testReturnMin() {
		System.out.println("testReturnMin");

		assertEquals("Min",testCal.MINUTE,app1.getMin());
		System.out.println("END testReturnMin");

	}

	@Test
	public void testGetEndTime() {
		System.out.println("testGetEndTime");
		
		Date test = app1.getEndTime(testCal.HOUR, testCal.MINUTE);
		assertEquals("Date",testCal.getTime(),test);
		System.out.println("END testGetEndTime");

	}

	
	@Test
	public void testSetSemesterClear() {
		System.out.println("testSetSemesterClear");

		// TODO pass a year, month, day 
		app1.setSemesterClear(testCal.YEAR, testCal.MONTH,testCal.DATE, testCal.HOUR, testCal.MINUTE);
		
		assertEquals("Year",testCal.YEAR,app1.getAutoClear().YEAR);
		assertEquals("Month",testCal.MONTH,app1.getAutoClear().MONTH);
		assertEquals("Date",testCal.DATE,app1.getAutoClear().DATE);
		assertEquals("Hour",testCal.HOUR,app1.getAutoClear().HOUR);
		assertEquals("Minute",testCal.MINUTE,app1.getAutoClear().MINUTE);
		System.out.println("END testSetSemesterClear");

	}

	@Test
	public void testGetSemesterClear() {
		System.out.println("testGetSemesterClear");

		fail("Not yet implemented");
		System.out.println("END testGetSemesterClear");

	}

	@Test
	public void testGet15BeforeEnd() {
		System.out.println("testGet15BeforeEnd");

		fail("Not yet implemented");
		System.out.println("END testGet15BeforeEnd");

	}

	@Test
	public void testGet5BeforeEnd() {
		System.out.println();

		fail("Not yet implemented");
		System.out.println();

	}

	@Test
	public void testAutoExit() {
		System.out.println();

		fail("Not yet implemented");
		System.out.println();

	}

	@Test
	public void testAutoClear() {
		System.out.println();

		fail("Not yet implemented");
		System.out.println();

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
