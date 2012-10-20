package testing.logicUnitTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Logic.appController;

import testUtil.DBUtil;


public class appControllerTest {

	private appController app1;
	private DBUtil dbUtil;

	
	@Before
	public void setUp() throws Exception {
		this.app1 = new appController();
		this.dbUtil = new DBUtil();
	}

	@After
	public void tearDown() throws Exception {
		this.app1 = null;
		this.dbUtil = null;
	}

	@Test // PSM001_Login-UnitTest-A01
	/**
	 * Test id:
	 * Purpose:
	 * 
	 */
	// Constructor 
	public void testAppController() {
		
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
		
	}

	@Test // PSM001_Login-UnitTest-A02
	// valid login test
	public void testLogIn_1() {
		
		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		
		app1.LogIn();
		assertTrue("Log In",app1.isLoggedin());

	}
	
	@Test // PSM001_Login-UnitTest-A03
	//invalid login username test
	public void testLogIn_2() {

		app1.setUsername("badUser");
		app1.setPassword(DBUtil.PASSWORD);

		app1.LogIn();
		assertFalse("Log In",app1.isLoggedin());

	}
	
	@Test // PSM001_Login-UnitTest-A04
	//invalid login password test
	public void testLogIn_3() {

		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword("badPassword");
		
		app1.LogIn();
		assertFalse("Log In",app1.isLoggedin());

	}

}
