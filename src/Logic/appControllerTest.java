package Logic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
//import java.util.Timer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import testUtil.Course;
import testUtil.DBUtil;

//@SuppressWarnings("unused")
//@SuppressWarnings("static-access")
public class appControllerTest {

	public appController app1;
	public Calendar testCal;
	public DBUtil dbUtil;
	//@SuppressWarnings("unused")
	//private DBUtil util = new DBUtil();
	public ArrayList<Course> courseList;
	public Course c1;
	public Course c2; 
	public Course c3; 

	
	@Before
	public void setUp() throws Exception {

		dbUtil = new DBUtil();
		//Date date = app1.getDate();
		//testCal = new GregorianCalendar();
		//testCal.setTime(date);
		this.app1 = new appController();
		//Date date = app1.getDate();
		this.courseList = new ArrayList<Course>();
		c1= new Course(1234,"Sub","Name","Semester",Course.STARTDATE,Course.ENDDATE);
		c2= new Course(2345,"Sub2","Name2","Semester",Course.STARTDATE,Course.ENDDATE);
		c3= new Course(3456,"Sub3","Name3","Semester",Course.STARTDATE,Course.ENDDATE);
		
		c1.fillDates(DBUtil.defaultDates);
		c2.fillDates(DBUtil.defaultDates);
		c3.fillDates(DBUtil.defaultDates);
		
		courseList.add(c1);
		courseList.add(c2);
		courseList.add(c3);
		
		//testCal = new GregorianCalendar();
		//testCal.setTime(date);
		
		this.courseList.add(c1);
		this.courseList.add(c2);
		this.courseList.add(c3);
		
		//this.app1.getDb().initializeStub(courseList);
		
	
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
		this.c1= null;
		this.c2 = null;
		this.c3 = null;
	}

	@Test
	// Constructor 
	public void testAppController() {
		
		System.out.println("testAppController");
		// TODO verify if need to check others 
		assertEquals("Hours",0,app1.getHr());//
		assertEquals("Minutes",0,app1.getMin());//
		assertEquals("Ten Minutes",dbUtil.minInMilli(10),app1.getTenmin());//
		assertNotNull("Auto Clear", app1.getAutoClear());//
		assertNotNull("Set Run",app1.getSetRun());//
		assertNotNull("Timer",app1.getTimer());//
		assertNotNull("Date",app1.getDate());//
		assertNotNull("Date 2",app1.getDate2());//
		assertEquals("DefSub","",app1.getDefSub());//
		assertEquals("DefSemester","",app1.getDefSemester());
		assertEquals("DefName","",app1.getDefCourseName());
		assertEquals("DefStart","",app1.getDefCourseStart());
		assertEquals("DefEnd","",app1.getDefCourseEnd());
		assertEquals("DefMonStart","",app1.getDefMonStart());
		assertEquals("DefMonEnd","",app1.getDefMonEnd());
		assertEquals("DefTueStart","",app1.getDefTueStart());
		assertEquals("DefTueEnd","",app1.getDefTueEnd());
		assertEquals("DefWedStart","",app1.getDefWedStart());
		assertEquals("DefWedEnd","",app1.getDefWedEnd());
		assertEquals("DefThuStart","",app1.getDefThuStart());
		assertEquals("DefThuEnd","",app1.getDefThuEnd());
		assertEquals("DefFriStart","",app1.getDefFriStart());
		assertEquals("DefFriEnd","",app1.getDefFriEnd());
		assertEquals("DefSatStart","",app1.getDefSatStart());
		assertEquals("DefSatEnd","",app1.getDefSatEnd());
	    
		assertNull("Username",app1.getUsername());
		assertNull("Password",app1.getPassword());
		assertFalse("Logged In",app1.isLoggedin());
		
		assertFalse("Data Recieved",app1.isDataReceived());
		assertFalse("Ed Sched Select",app1.isEdSchedSel());
		assertFalse("Schedule Setup Select",app1.isSchedSetupSel());
		assertFalse("Logout Select",app1.isLogoutSel());
		
		assertEquals("ClearDate",0,app1.getClearDate());
		assertEquals("ClearMonth",0,app1.getClearMonth());
		assertEquals("ClearYear",0,app1.getClearYear());
		
		assertEquals("Counter",0,app1.getCounter());
		assertEquals("Class Ended",0,app1.getClassEnded());
		assertNotNull("DBConnection",app1.getDb());
		assertNotNull("InterfaceController",app1.getIc());
		
		assertNull("Authenticate",app1.getAuth());
		assertEquals("Course Sel",0,app1.getCourseSel());
		assertEquals("Class End",0,app1.getClassEnded());
		
		System.out.println("END testAppController");
	}

	
	@Test
	// valid login test
	public void testLogIn_1() {
		System.out.println("testLogIn");

		app1.getDb().connect("cen5076","cen5076");
		app1.LogIn();
		assertTrue("Log In",app1.isLoggedin());
		System.out.println("END testLogIn");

	}
	
	@Test
	//invalid login username test
	public void testLogIn_2() {
		System.out.println("testLogIn");

		app1.getDb().connect("badUser","cen5076");
		app1.LogIn();
		assertFalse("Log In",app1.isLoggedin());
		System.out.println("END testLogIn");

	}
	
	@Test
	//invalid login password test
	public void testLogIn_3() {
		System.out.println("testLogIn");

		app1.getDb().connect("cen5076","badPassword");
		app1.LogIn();
		assertFalse("Log In",app1.isLoggedin());
		System.out.println("END testLogIn");

	}

}
