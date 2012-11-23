package testing.logicSubsystemTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import Logic.appController;
import stubs.DBConnection;
import Logic.FutureTimer;
import stubs.MainMenu;
import stubs.Messages;
import stubs.ScheduleForm;
import stubs.courseSelect;
import testUtil.DBUtil;
import testUtil.Course;

/**
 * This class tests the Begin method which has system functionality and is called within main.
 * Tests H04-H16 require admin rights to change system time
 */

public class appControllerBegin_SS {
	
	static int currMonth;
	static int currDate;
	static int currYear;
	appController app1;
	Course c1;
	DBConnection db;

	@Rule
	public final ExpectedSystemExit exit = ExpectedSystemExit.none();
	
	@BeforeClass
	public static void setUpOnce() throws Exception {
		Calendar cal = Calendar.getInstance();
		currMonth = cal.get(Calendar.MONTH) + 1;
		currDate = cal.get(Calendar.DATE);
		currYear = cal.get(Calendar.YEAR) - 2000;
	}
	
	@Before
	public void setUp() throws Exception {
		app1 = new appController();
		app1.newTimer = new FutureTimer();
		db = app1.getDb();
	}

	@After
	public void tearDown() throws Exception {
		app1.getPopup5min().cancel();
		app1.getPopup15min().cancel();
		app1.getEndofclass().cancel();
		c1 = null;
		app1 = null;
	}
	
	@AfterClass
	public static void tearDownOnce() throws Exception {
		try {
			Runtime.getRuntime().exec("cmd /C date " + currMonth + '-' + currDate + '-' + currYear);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** Test Case ID: PSM001_Login-SubsystemTest-F01
	* Purpose: Test Begin authenticate state transition to login with a branch to the lockout case.
	* Date Created: 10/27/12
	* Author: David Garcia
	* Stubs needed: DBConnection, InterfaceController, Messages
	*/
	@Test
	public void testBegin_AuthenticateToLoginTransition(){
		
		String messg = new Object(){}.getClass().getEnclosingMethod().getName(); 
		System.out.println("---" + messg + "---");
		
		app1.setLoggedIn(false);
		app1.setUsername("BadUser");
		app1.setPassword("badPassword");
		app1.setCounter(1);
		app1.setDataReceived(false);
		app1.authenticate();
		app1.setDataReceived(true);
		exit.expectSystemExitWithStatus(0);
		app1.authenticate();
		
		assertFalse("Logged in", app1.isLoggedin());
		assertFalse("DataReceived toggled", app1.getDataReceived());
	}
	
	/** Test Case ID: PSM001_Login-SubsystemTest-F02
	* Purpose: Tests the ready state of appController and calls the clearDB section since a course has expired
	* Date Created: 10/27/12
	* Author: David Garcia
	* Stubs needed: DBConnection, InterfaceController
	*/
	@Test
	public void testBegin_ReadyState_ClearDB(){
		
		String messg = new Object(){}.getClass().getEnclosingMethod().getName(); 
		System.out.println("---" + messg + "---");
		
		app1.setLoggedIn(true);
		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		app1.setDataReceived(true);
		
		GregorianCalendar g = new GregorianCalendar();
		Date d = new Date(System.currentTimeMillis());
		g.setTime(d);
		g.set(g.get(Calendar.YEAR)-1,g.get(Calendar.MONTH),g.get(Calendar.DATE));
		Date passedDate = g.getTime();
		
		String endDate = DBUtil.passedAsString(passedDate);
		
		Course c1 = new Course(1234,"Subject","Name","Semester","10/01/12",endDate);
		c1.fillTimes(Course.DEFAULT_TIMES);
		
		//System.out.println(endDate);
		db.addCourse(c1);
		
		try {
			app1.ready();
		}
		catch(Exception e) {
			
			fail("Exception caught-" + e.toString());
		}
		
		assertEquals("Timer 5 before",0,app1.getPopup5min().scheduledExecutionTime());
		assertEquals("Timer 15 before",0,app1.getPopup15min().scheduledExecutionTime());
		assertEquals("Timer End of Class",0,app1.getEndofclass().scheduledExecutionTime());
	}
	
	/** Test Case ID: PSM001_Login-SubsystemTest-F03
	* Purpose: Test All the states of the Begin method.
	* Date Created: 10/27/12
	* Author: David Garcia
	* Stubs needed: InterFaceController, LoginForm,  DBConnection, ScheduleForm, MainMenu, courseSelect
	*/
	@Test
	public void testBegin_AllStates(){
		
		String messg = new Object(){}.getClass().getEnclosingMethod().getName(); 
		System.out.println("---" + messg + "---");
		

		/** LOGIN **/
		app1.setLoggedIn(true);
		app1.setDataReceived(true);
		app1.getIc().log.setUsername(DBUtil.USERNAME);
		app1.getIc().log.setPassword(DBUtil.PASSWORD); 
		app1.loginState();
		
		/** Assert Logged In **/
		assertEquals("Logged in Username", app1.getUsername(), DBUtil.USERNAME);
		assertEquals("Logged in Password", app1.getPassword(), DBUtil.PASSWORD);
		assertTrue("Logged in State", app1.isLoggedin());
		
		/** SET Values for Authenticate ***/
		app1.setLoggedIn(false);
		app1.setDataReceived(true);
		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		int counter = 0;
		app1.setCounter(counter);
		
		app1.authenticate();
		
		assertTrue("Connected", db.isConnected());
		assertEquals("Counter unchanged", counter, app1.getCounter());
		
		
		/** Set Values to Be in Ready **/
		app1.setLoggedIn(true);
		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		app1.setDataReceived(true);
		
		GregorianCalendar g = new GregorianCalendar();
		Date d = new Date(System.currentTimeMillis());
		g.setTime(d);
		g.set(g.get(Calendar.YEAR)+1,g.get(Calendar.MONTH),g.get(Calendar.DATE));
		Date passedDate = g.getTime();
		
		String endDate = DBUtil.passedAsString(passedDate);
		
		
		c1 = new Course(1234,"Subject","Name","Semester","10/01/12",endDate);
		c1.fillTimes(Course.DEFAULT_TIMES);
		
		try{
			Thread.sleep(300);
		}
		catch(Exception e){
			
		}
		
		assertNotNull("Database Null", db);
		System.out.println("Course=" + c1.getCrseid());
		System.out.println(c1.toString());
		db.addCourse(c1);
		
		try{
			app1.ready();
		}
		catch(IllegalStateException e){
			
			
		}
		
		/*** Assert in ready and timers set ***/
		//assertTrue("Timer 5 before",app1.getPopup5min().scheduledExecutionTime()> 0);
		//assertTrue("Timer 15 before",app1.getPopup15min().scheduledExecutionTime() > 0);
		//assertTrue("Timer End of Class",app1.getEndofclass().scheduledExecutionTime() > 0);
		
		ScheduleForm.dataReceived=false;

		DBUtil.defaultCourseId = 1234;
		Course c2 = new Course(DBUtil.defaultCourseId,"Subject","Name","Semester",Course.STARTDATE,Course.ENDDATE);
		c1.fillTimes(Course.DEFAULT_TIMES);
		
		db.addCourse(c2);
		app1.getIc().sched.addCourse(c2);
		ScheduleForm.dataReceived=true;
		
		app1.setSchedule();
		
		/*** Assert in Set Schedule ***/
		assertFalse("DataReceived", app1.getDataReceived());
		app1.setDataReceived(false);
		
		//enter loop
		app1.setLogOutSel(false);
		app1.getIc().mm.setEditSched(true);
		app1.getIc().mm.setdataRec(true);
		
		DBUtil.defaultCourseId = 1234;
		Course c3 = new Course(DBUtil.defaultCourseId, "Subject", "Name", "Semester", Course.STARTDATE, Course.ENDDATE);
		c3.fillTimes(Course.DEFAULT_TIMES);
		
		db.addCourse(c3);
		
		//make sure class has not ended
		app1.setClassEnded(0);

//		app1.getDb().connected = true;
		app1.LogIn();
		app1.getIc().Course_Select_Form();
		app1.getIc().cs.setCourseSelected(true);
		courseSelect.setSelection(c3.crseid);
		System.out.println("IC.cs.getselection()=" + app1.getIc().cs.getSelection());
		System.out.println("IC.cs.selection1=" + courseSelect.selection);

		app1.getIc().edSched.setDataRec(true);
		System.out.println("IC.cs.selection2=" + courseSelect.selection);

		app1.getIc().edSched.addCourse(c3);
		System.out.println("IC.cs.selection3=" + courseSelect.selection);

		System.out.println("IC.cs.selection4=" + courseSelect.selection);


		try{
			app1.editSchedule();
		}
		catch(Exception e){
			
			fail("Exception Caught-" + e.toString());
		}
		assertFalse("Data Received", app1.getDataReceived());
		
		app1.setDataReceived(true);
		
		//enter loop
		app1.setLogOutSel(false);
		app1.getIc().mm.setLogout(true);
		app1.getIc().mm.setdataRec(true);
		
		//make sure class has not ended
		app1.setClassEnded(0);

//		app1.getDb().connected = true;
		app1.LogIn();
		app1.initAuthenticate(DBUtil.USERNAME, DBUtil.PASSWORD);

		app1.logOut();
		
		
		assertTrue("Logged out", Messages.getLogOut());
		///assertFalse("Not LoggedIn",app1.isLoggedin());
		
		
	}
	
	/** Test Case ID: PSM001_Login-SubsystemTest-F04
	* Purpose: Test the branch for a Monday class end date check
	* Date Created: 10/27/12
	* Author: David Garcia
	* Stubs needed: DBConnection, InterfaceController
	*/
	@Test
	public void testBegin_TestDaysBranchesMon(){
		
		//Set the date to a Monday
		try {
			Runtime.getRuntime().exec("cmd /C date " + "10-22-12");
		} catch (IOException e) {
			e.printStackTrace();
		} // dd-MM-yy
		
		/** Set Values to Be in Ready **/
		//app1.setLoggedIn(true);
		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		app1.LogIn();
		app1.setDataReceived(true);
		
		assertTrue("Connected", db.isConnected());
		
		GregorianCalendar g = new GregorianCalendar();
		Date d = new Date(System.currentTimeMillis());
		g.setTime(d);
		g.set(g.get(Calendar.YEAR)+1,g.get(Calendar.MONTH),g.get(Calendar.DATE));
		Date passedDate = g.getTime();
		
		String endDate = DBUtil.passedAsString(passedDate);
		
		
		c1 = new Course(1234,"Subject","Name","Semester","10/01/12",endDate);
		c1.fillTimes(Course.DEFAULT_TIMES);
		
		//System.out.println(endDate);
		try{
			Thread.sleep(1000);
		}
		catch(Exception e){
			
		}
		
		assertNotNull("Database Null", db);
//		System.out.println("Course=" + c1.getCrseid());
//		System.out.println(c1.toString());
		db.addCourse(c1);
		assertEquals("DB Size", 1, db.getCourseSet().size());
		
		try{
			app1.ready();
		}
		catch(IllegalStateException e){
			
			
		}
		
		assertTrue("Logged in", app1.isLoggedin());
		assertFalse("Data Received", app1.getDataReceived());
		assertEquals("Get End Hour", 13, app1.returnHr());
		assertEquals("Get End Minute", 0, app1.returnMin());
	}
	
	/** Test Case ID: PSM001_Login-SubsystemTest-F05
	* Purpose: Test the branch for a Monday class end check that fails validation of end time check
	* Date Created: 11/13/12
	* Author: Matthew Brown
	* Stubs needed: DBConnection, InterfaceController
	*/
	@Test
	public void testBegin_TestDaysBranchesMon2(){
		
		//Set the date to a Monday
		try {
			Runtime.getRuntime().exec("cmd /C date " + "10-22-12");
		} catch (IOException e) {
			e.printStackTrace();
		} // dd-MM-yy
		
		/** Set Values to Be in Ready **/
		//app1.setLoggedIn(true);
		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		app1.LogIn();
		app1.setDataReceived(true);
		
		assertTrue("Connected", db.isConnected());
		
		GregorianCalendar g = new GregorianCalendar();
		Date d = new Date(System.currentTimeMillis());
		g.setTime(d);
		g.set(g.get(Calendar.YEAR)+1,g.get(Calendar.MONTH),g.get(Calendar.DATE));
		Date passedDate = g.getTime();
		
		String endDate = DBUtil.passedAsString(passedDate);
		
		c1 = new Course(1234,"Subject","Name","Semester","10/01/12",endDate);
		c1.fillTimes(Course.NO_TIMES);
		
		//System.out.println(endDate);
		try{
			Thread.sleep(1000);
		}
		catch(Exception e){
			
		}
		
		assertNotNull("Database Null", db);
//		System.out.println("Course=" + c1.getCrseid());
//		System.out.println(c1.toString());
		db.addCourse(c1);
		assertEquals("DB Size", 1, db.getCourseSet().size());
		
		try{
			app1.ready();
		}
		catch(IllegalStateException e){
			
			
		}
		
		assertTrue("Logged in", app1.isLoggedin());
		assertFalse("Data Received", app1.getDataReceived());
		assertEquals("Get End Hour", 0, app1.returnHr());
		assertEquals("Get End Minute", 0, app1.returnMin());
	}
	
	/** Test Case ID: PSM001_Login-SubsystemTest-F06
	* Purpose: Test the branch for a Tuesday class end date check
	* Date Created: 10/27/12
	* Author: David Garcia
	* Stubs needed: DBConnection, InterfaceController
	*/
	@Test
	public void testBegin_TestDaysBranchesTue(){
		
		//Set the date to a Tuesday
		try {
			Runtime.getRuntime().exec("cmd /C date " + "10-23-12");
		} catch (IOException e) {
			e.printStackTrace();
		} // dd-MM-yy
		
		/** Set Values to Be in Ready **/
//		app1.setLoggedIn(true);
		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		app1.LogIn();
		app1.setDataReceived(true);
		
		assertTrue("Connected", db.isConnected());
		
		GregorianCalendar g = new GregorianCalendar();
		Date d = new Date(System.currentTimeMillis());
		g.setTime(d);
		g.set(g.get(Calendar.YEAR)+1,g.get(Calendar.MONTH),g.get(Calendar.DATE));
		Date passedDate = g.getTime();
		
		String endDate = DBUtil.passedAsString(passedDate);
		
		c1 = new Course(1234,"Subject","Name","Semester","10/01/12",endDate);
		c1.fillTimes(Course.DEFAULT_TIMES);
		
		//System.out.println(endDate);
		try{
			Thread.sleep(1000);
		}
		catch(Exception e){
			
		}
		
		assertNotNull("Database Null", db);
//		System.out.println("Course=" + c1.getCrseid());
//		System.out.println(c1.toString());
		db.addCourse(c1);
		assertEquals("DB Size", 1, db.getCourseSet().size());
		
		try{
			app1.ready();
		}
		catch(IllegalStateException e){
			
			
		}
		
		assertTrue("Logged in", app1.isLoggedin());
		assertFalse("Data Received", app1.getDataReceived());
		assertEquals("Get End Hour", 13, app1.returnHr());
		assertEquals("Get End Minute", 0, app1.returnMin());
	}
	
	/** Test Case ID: PSM001_Login-SubsystemTest-F07
	* Purpose: Test the branch for a Tuesday class end check that fails validation of end time check
	* Date Created: 11/13/12
	* Author: Matthew Brown
	* Stubs needed: DBConnection, InterfaceController
	*/
	@Test
	public void testBegin_TestDaysBranchesTue2(){
		
		//Set the date to a Tuesday
		try {
			Runtime.getRuntime().exec("cmd /C date " + "10-23-12");
		} catch (IOException e) {
			e.printStackTrace();
		} // dd-MM-yy
		
		/** Set Values to Be in Ready **/
//		app1.setLoggedIn(true);
		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		app1.LogIn();
		app1.setDataReceived(true);
		
		assertTrue("Connected", db.isConnected());
		
		GregorianCalendar g = new GregorianCalendar();
		Date d = new Date(System.currentTimeMillis());
		g.setTime(d);
		g.set(g.get(Calendar.YEAR)+1,g.get(Calendar.MONTH),g.get(Calendar.DATE));
		Date passedDate = g.getTime();
		
		String endDate = DBUtil.passedAsString(passedDate);
		
		
		c1 = new Course(1234,"Subject","Name","Semester","10/01/12",endDate);
		c1.fillTimes(Course.NO_TIMES);
		
		//System.out.println(endDate);
		try{
			Thread.sleep(1000);
		}
		catch(Exception e){
			
		}
		
		assertNotNull("Database Null", db);
//		System.out.println("Course=" + c1.getCrseid());
//		System.out.println(c1.toString());
		db.addCourse(c1);
		assertEquals("DB Size", 1, db.getCourseSet().size());
		
		try{
			app1.ready();
		}
		catch(IllegalStateException e){
			
			
		}
		
		assertTrue("Logged in", app1.isLoggedin());
		assertFalse("Data Received", app1.getDataReceived());
		assertEquals("Get End Hour", 0, app1.returnHr());
		assertEquals("Get End Minute", 0, app1.returnMin());
	}
	
	/** Test Case ID: PSM001_Login-SubsystemTest-F08
	* Purpose: Test the branch for a Wednesday class end date check
	* Date Created: 10/27/12
	* Author: David Garcia
	* Stubs needed: DBConnection, InterfaceController
	*/
	@Test
	public void testBegin_TestDaysBranchesWed(){
		
		//Set the date to a Wednesday
		try {
			Runtime.getRuntime().exec("cmd /C date " + "10-24-12");
		} catch (IOException e) {
			e.printStackTrace();
		} // dd-MM-yy
		
		/** Set Values to Be in Ready **/
//		app1.setLoggedIn(true);
		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		app1.LogIn();
		app1.setDataReceived(true);
		
		assertTrue("Connected", db.isConnected());
		
		GregorianCalendar g = new GregorianCalendar();
		Date d = new Date(System.currentTimeMillis());
		g.setTime(d);
		g.set(g.get(Calendar.YEAR)+1,g.get(Calendar.MONTH),g.get(Calendar.DATE));
		Date passedDate = g.getTime();
		
		String endDate = DBUtil.passedAsString(passedDate);
		
		
		c1 = new Course(1234,"Subject","Name","Semester","10/01/12",endDate);
		c1.fillTimes(Course.DEFAULT_TIMES);
		
		//System.out.println(endDate);
		try{
			Thread.sleep(1000);
		}
		catch(Exception e){
			
		}
		
		assertNotNull("Database Null", db);
//		System.out.println("Course=" + c1.getCrseid());
//		System.out.println(c1.toString());
		db.addCourse(c1);
		assertEquals("DB Size", 1, db.getCourseSet().size());
		
		try{
			app1.ready();
		}
		catch(IllegalStateException e){
			
			
		}
		
		assertTrue("Logged in", app1.isLoggedin());
		assertFalse("Data Received", app1.getDataReceived());
		assertEquals("Get End Hour", 13, app1.returnHr());
		assertEquals("Get End Minute", 0, app1.returnMin());
	}
	
	/** Test Case ID: PSM001_Login-SubsystemTest-F09
	* Purpose: Test the branch for a Wednesday class end check that fails validation of end time check
	* Date Created: 11/13/12
	* Author: Matthew Brown
	* Stubs needed: DBConnection, InterfaceController
	*/
	@Test
	public void testBegin_TestDaysBranchesWed2(){
		
		//Set the date to a Wednesday
		try {
			Runtime.getRuntime().exec("cmd /C date " + "10-24-12");
		} catch (IOException e) {
			e.printStackTrace();
		} // dd-MM-yy
		
		/** Set Values to Be in Ready **/
//		app1.setLoggedIn(true);
		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		app1.LogIn();
		app1.setDataReceived(true);
		
		assertTrue("Connected", db.isConnected());
		
		GregorianCalendar g = new GregorianCalendar();
		Date d = new Date(System.currentTimeMillis());
		g.setTime(d);
		g.set(g.get(Calendar.YEAR)+1,g.get(Calendar.MONTH),g.get(Calendar.DATE));
		Date passedDate = g.getTime();
		
		String endDate = DBUtil.passedAsString(passedDate);
		
		
		c1 = new Course(1234,"Subject","Name","Semester","10/01/12",endDate);
		c1.fillTimes(Course.NO_TIMES);
		
		//System.out.println(endDate);
		try{
			Thread.sleep(1000);
		}
		catch(Exception e){
			
		}
		
		assertNotNull("Database Null", db);
//		System.out.println("Course=" + c1.getCrseid());
//		System.out.println(c1.toString());
		db.addCourse(c1);
		assertEquals("DB Size", 1, db.getCourseSet().size());
		
		try{
			app1.ready();
		}
		catch(IllegalStateException e){
			
			
		}
		
		assertTrue("Logged in", app1.isLoggedin());
		assertFalse("Data Received", app1.getDataReceived());
		assertEquals("Get End Hour", 0, app1.returnHr());
		assertEquals("Get End Minute", 0, app1.returnMin());
	}
	
	
	/** Test Case ID: PSM001_Login-SubsystemTest-F10
	* Purpose: Test the branch for a Thursday class end date check
	* Date Created: 10/27/12
	* Author: David Garcia
	* Stubs needed: DBConnection, InterfaceController
	*/
	@Test
	public void testBegin_TestDaysBranchesThu(){
		
		//Set the date to a Thursday
		try {
			Runtime.getRuntime().exec("cmd /C date " + "10-25-12");
		} catch (IOException e) {
			e.printStackTrace();
		} // dd-MM-yy
		
		/** Set Values to Be in Ready **/
//		app1.setLoggedIn(true);
		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		app1.LogIn();
		app1.setDataReceived(true);
		
		assertTrue("Connected", db.isConnected());
		
		GregorianCalendar g = new GregorianCalendar();
		Date d = new Date(System.currentTimeMillis());
		g.setTime(d);
		g.set(g.get(Calendar.YEAR)+1,g.get(Calendar.MONTH),g.get(Calendar.DATE));
		Date passedDate = g.getTime();
		
		String endDate = DBUtil.passedAsString(passedDate);
		
		
		c1 = new Course(1234,"Subject","Name","Semester","10/01/12",endDate);
		c1.fillTimes(Course.DEFAULT_TIMES);
		
		//System.out.println(endDate);
		try{
			Thread.sleep(1000);
		}
		catch(Exception e){
			
		}
		
		assertNotNull("Database Null", db);
//		System.out.println("Course=" + c1.getCrseid());
//		System.out.println(c1.toString());
		db.addCourse(c1);
		assertEquals("DB Size", 1, db.getCourseSet().size());
		
		try{
			app1.ready();
		}
		catch(IllegalStateException e){
			
			
		}
		
		assertTrue("Logged in", app1.isLoggedin());
		assertFalse("Data Received", app1.getDataReceived());
		assertEquals("Get End Hour", 13, app1.returnHr());
		assertEquals("Get End Minute", 0, app1.returnMin());
	}
	
	/** Test Case ID: PSM001_Login-SubsystemTest-F11
	* Purpose: Test the branch for a Thursday class end check that fails validation of end time check
	* Date Created: 11/13/12
	* Author: Matthew Brown
	* Stubs needed: DBConnection, InterfaceController
	*/
	@Test
	public void testBegin_TestDaysBranchesThu2(){
		
		//Set the date to a Thursday
		try {
			Runtime.getRuntime().exec("cmd /C date " + "10-25-12");
		} catch (IOException e) {
			e.printStackTrace();
		} // dd-MM-yy
		
		/** Set Values to Be in Ready **/
//		app1.setLoggedIn(true);
		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		app1.LogIn();
		app1.setDataReceived(true);
		
		assertTrue("Connected", db.isConnected());
		
		GregorianCalendar g = new GregorianCalendar();
		Date d = new Date(System.currentTimeMillis());
		g.setTime(d);
		g.set(g.get(Calendar.YEAR)+1,g.get(Calendar.MONTH),g.get(Calendar.DATE));
		Date passedDate = g.getTime();
		
		String endDate = DBUtil.passedAsString(passedDate);
		
		
		c1 = new Course(1234,"Subject","Name","Semester","10/01/12",endDate);
		c1.fillTimes(Course.NO_TIMES);
		
		//System.out.println(endDate);
		try{
			Thread.sleep(1000);
		}
		catch(Exception e){
			
		}
		
		assertNotNull("Database Null", db);
//		System.out.println("Course=" + c1.getCrseid());
//		System.out.println(c1.toString());
		db.addCourse(c1);
		assertEquals("DB Size", 1, db.getCourseSet().size());
		
		try{
			app1.ready();
		}
		catch(IllegalStateException e){
			
			
		}
		
		assertTrue("Logged in", app1.isLoggedin());
		assertFalse("Data Received", app1.getDataReceived());
		assertEquals("Get End Hour", 0, app1.returnHr());
		assertEquals("Get End Minute", 0, app1.returnMin());
	}
	
	/** Test Case ID: PSM001_Login-SubsystemTest-F12
	* Purpose: Test the branch for a Friday class end date check
	* Date Created: 10/27/12
	* Author: David Garcia
	* Stubs needed: DBConnection, InterfaceController
	*/
	@Test
	public void testBegin_TestDaysBranchesFri(){
		
		//Set the date to a Friday
		try {
			Runtime.getRuntime().exec("cmd /C date " + "10-26-12");
		} catch (IOException e) {
			e.printStackTrace();
		} // dd-MM-yy
		
		/** Set Values to Be in Ready **/
//		app1.setLoggedIn(true);
		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		app1.LogIn();
		app1.setDataReceived(true);
		
		assertTrue("Connected", db.isConnected());
		
		GregorianCalendar g = new GregorianCalendar();
		Date d = new Date(System.currentTimeMillis());
		g.setTime(d);
		g.set(g.get(Calendar.YEAR)+1,g.get(Calendar.MONTH),g.get(Calendar.DATE));
		Date passedDate = g.getTime();
		
		String endDate = DBUtil.passedAsString(passedDate);
		
		
		c1 = new Course(1234,"Subject","Name","Semester","10/01/12",endDate);
		c1.fillTimes(Course.DEFAULT_TIMES);
		
		//System.out.println(endDate);
		try{
			Thread.sleep(1000);
		}
		catch(Exception e){
			
		}
		
		assertNotNull("Database Null", db);
//		System.out.println("Course=" + c1.getCrseid());
//		System.out.println(c1.toString());
		db.addCourse(c1);
		assertEquals("DB Size", 1, db.getCourseSet().size());
		
		try{
			app1.ready();
		}
		catch(IllegalStateException e){
			
			
		}
		
		assertTrue("Logged in", app1.isLoggedin());
		assertFalse("Data Received", app1.getDataReceived());
		assertEquals("Get End Hour", 13, app1.returnHr());
		assertEquals("Get End Minute", 0, app1.returnMin());
	}
	
	/** Test Case ID: PSM001_Login-SubsystemTest-F13
	* Purpose: Test the branch for a Friday class end check that fails validation of end time check
	* Date Created: 11/13/12
	* Author: Matthew Brown
	* Stubs needed: DBConnection, InterfaceController
	*/
	@Test
	public void testBegin_TestDaysBranchesFri2(){
		
		//Set the date to a Friday
		try {
			Runtime.getRuntime().exec("cmd /C date " + "10-26-12");
		} catch (IOException e) {
			e.printStackTrace();
		} // dd-MM-yy
		
		/** Set Values to Be in Ready **/
//		app1.setLoggedIn(true);
		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		app1.LogIn();
		app1.setDataReceived(true);
		
		assertTrue("Connected", db.isConnected());
		
		GregorianCalendar g = new GregorianCalendar();
		Date d = new Date(System.currentTimeMillis());
		g.setTime(d);
		g.set(g.get(Calendar.YEAR)+1,g.get(Calendar.MONTH),g.get(Calendar.DATE));
		Date passedDate = g.getTime();
		
		String endDate = DBUtil.passedAsString(passedDate);
		
		
		c1 = new Course(1234,"Subject","Name","Semester","10/01/12",endDate);
		c1.fillTimes(Course.NO_TIMES);
		
		//System.out.println(endDate);
		try{
			Thread.sleep(1000);
		}
		catch(Exception e){
			
		}
		
		assertNotNull("Database Null", db);
//		System.out.println("Course=" + c1.getCrseid());
//		System.out.println(c1.toString());
		db.addCourse(c1);
		assertEquals("DB Size", 1, db.getCourseSet().size());
		
		try{
			app1.ready();
		}
		catch(IllegalStateException e){
			
			
		}
		
		assertTrue("Logged in", app1.isLoggedin());
		assertFalse("Data Received", app1.getDataReceived());
		assertEquals("Get End Hour", 0, app1.returnHr());
		assertEquals("Get End Minute", 0, app1.returnMin());
	}
	
	/** Test Case ID: PSM001_Login-SubsystemTest-F14
	* Purpose: Test the branch for a Saturday class end date check
	* Date Created: 10/27/12
	* Author: David Garcia
	* Stubs needed: DBConnection, InterfaceController
	*/
	@Test
	public void testBegin_TestDaysBranchesSat(){
		
		//Set the date to a Saturday
		try {
			Runtime.getRuntime().exec("cmd /C date " + "10-27-12");
		} catch (IOException e) {
			e.printStackTrace();
		} // dd-MM-yy
		
		/** Set Values to Be in Ready **/
//		app1.setLoggedIn(true);
		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		app1.LogIn();
		app1.setDataReceived(true);
		
		assertTrue("Connected", db.isConnected());
		
		GregorianCalendar g = new GregorianCalendar();
		Date d = new Date(System.currentTimeMillis());
		g.setTime(d);
		g.set(g.get(Calendar.YEAR)+1,g.get(Calendar.MONTH),g.get(Calendar.DATE));
		Date passedDate = g.getTime();
		
		String endDate = DBUtil.passedAsString(passedDate);
		
		
		c1 = new Course(1234,"Subject","Name","Semester","10/01/12",endDate);
		c1.fillTimes(Course.DEFAULT_TIMES);
		
		//System.out.println(endDate);
		try{
			Thread.sleep(300);
		}
		catch(Exception e){
			
		}
		
		assertNotNull("Database Null", db);
//		System.out.println("Course=" + c1.getCrseid());
//		System.out.println(c1.toString());
		db.addCourse(c1);
		assertEquals("DB Size", 1, db.getCourseSet().size());
		
		try{
			app1.ready();
		}
		catch(IllegalStateException e){
			
			
		}
		
		assertTrue("Logged in", app1.isLoggedin());
		assertFalse("Data Received", app1.getDataReceived());
		assertEquals("Get End Hour", 13, app1.returnHr());
		assertEquals("Get End Minute", 0, app1.returnMin());
	}
	
	/** Test Case ID: PSM001_Login-SubsystemTest-F15
	* Purpose: Test the branch for a Saturday class end check that fails validation of end time check
	* Date Created: 11/13/12
	* Author: Matthew Brown
	* Stubs needed: DBConnection, InterfaceController
	*/
	@Test
	public void testBegin_TestDaysBranchesSat2(){
		
		//Set the date to a Saturday
		try {
			Runtime.getRuntime().exec("cmd /C date " + "10-27-12");
		} catch (IOException e) {
			e.printStackTrace();
		} // dd-MM-yy
		
		/** Set Values to Be in Ready **/
//		app1.setLoggedIn(true);
		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		app1.LogIn();
		app1.setDataReceived(true);
		
		assertTrue("Connected", db.isConnected());
		
		GregorianCalendar g = new GregorianCalendar();
		Date d = new Date(System.currentTimeMillis());
		g.setTime(d);
		g.set(g.get(Calendar.YEAR)+1,g.get(Calendar.MONTH),g.get(Calendar.DATE));
		Date passedDate = g.getTime();
		
		String endDate = DBUtil.passedAsString(passedDate);
		
		
		c1 = new Course(1234,"Subject","Name","Semester","10/01/12",endDate);
		c1.fillTimes(Course.NO_TIMES);
		
		//System.out.println(endDate);
		try{
			Thread.sleep(300);
		}
		catch(Exception e){
			
		}
		
		assertNotNull("Database Null", db);
//		System.out.println("Course=" + c1.getCrseid());
//		System.out.println(c1.toString());
		db.addCourse(c1);
		assertEquals("DB Size", 1, db.getCourseSet().size());
		
		try{
			app1.ready();
		}
		catch(IllegalStateException e){
			
			
		}
		
		assertTrue("Logged in", app1.isLoggedin());
		assertFalse("Data Received", app1.getDataReceived());
		assertEquals("Get End Hour", 0, app1.returnHr());
		assertEquals("Get End Minute", 0, app1.returnMin());
	}
	
	/** Test Case ID: PSM001_Login-SubsystemTest-F16
	* Purpose: Test the branch for a Sunday class end date check
	* Date Created: 10/27/12
	* Author: David Garcia
	* Stubs needed: DBConnection, InterfaceController
	*/
	@Test
	public void testBegin_TestDaysBranchesSun(){
		
		//Set the date to a Sunday
		try {
			Runtime.getRuntime().exec("cmd /C date " + "10-28-12");
		} catch (IOException e) {
			e.printStackTrace();
		} // dd-MM-yy
		
		/** Set Values to Be in Ready **/
//		app1.setLoggedIn(true);
		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		app1.LogIn();
		app1.setDataReceived(true);
		
		assertTrue("Connected", db.isConnected());
		
		GregorianCalendar g = new GregorianCalendar();
		Date d = new Date(System.currentTimeMillis());
		g.setTime(d);
		g.set(g.get(Calendar.YEAR)+1,g.get(Calendar.MONTH),g.get(Calendar.DATE));
		Date passedDate = g.getTime();
		
		String endDate = DBUtil.passedAsString(passedDate);
		
		
		c1 = new Course(1234,"Subject","Name","Semester","10/01/12",endDate);
		c1.fillTimes(Course.DEFAULT_TIMES);
		
		//System.out.println(endDate);
		try{
			Thread.sleep(1000);
		}
		catch(Exception e){
			
		}
		
		assertNotNull("Database Null", db);
//		System.out.println("Course=" + c1.getCrseid());
//		System.out.println(c1.toString());
		db.addCourse(c1);
		assertEquals("DB Size", 1, db.getCourseSet().size());
		
		try{
			app1.ready();
		}
		catch(IllegalStateException e){
			
			
		}
		
		assertTrue("Logged in", app1.isLoggedin());
		assertFalse("Data Received", app1.getDataReceived());
		assertEquals("Get End Hour", 0, app1.returnHr());
		assertEquals("Get End Minute", 0, app1.returnMin());
	}
	
	
	/** Test Case ID: PSM001_Login-SubsystemTest-F17
	* Purpose: Test the call to the actual begin method and not its parsed out pieces. We set the values so
	* 		   that it takes a path through LogIn, Authenticate, and Ready in the Begin State Chart, calling
	* 		   clearDatabase method within Ready.
	* Date Created: 10/27/12
	* Author: David Garcia
	* Stubs needed: DBConnection, InterfaceController, MainMenu, Messages
	*/
	@Test
	public void testBegin_Begin(){

		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		app1.LogIn();
		app1.setDataReceived(true);
		
		GregorianCalendar g = new GregorianCalendar();
		Date d = new Date(System.currentTimeMillis());
		g.setTime(d);
		
		//Use ClearDatabase branch
		g.set(g.get(Calendar.YEAR)-1,g.get(Calendar.MONTH),g.get(Calendar.DATE));
		Date passedDate = g.getTime();
		
		String endDate = DBUtil.passedAsString(passedDate);
		
		
		c1 = new Course(1234,"Subject","Name","Semester","10/01/12",endDate);
		c1.fillTimes(Course.DEFAULT_TIMES);
		
		db.addCourse(c1);
		//app1.setLoggedIn(false);
		//app1.setDataReceived(false);

		//app1.setLoggedIn(false);
		//app1.setDataReceived(true);
		//app1.setUsername(DBUtil.USERNAME);
		//app1.setPassword(DBUtil.PASSWORD);
		int counter = 0;
		app1.setCounter(counter);
		app1.setClassEnded(System.currentTimeMillis());
		app1.getIc().mm.setdataRec(true);
		//app1.getIc().mm.setLogout(true);
		//app1.setEdSchedSel(true);
		//app1.setSchedSetupSel(true);
		app1.getIc().mm.setEditSched(true);
		//app1.getIc().mm.setLogout(true);
		app1.getIc().mm.setInitSetup(true);
		app1.initAuthenticate(DBUtil.USERNAME, DBUtil.PASSWORD);
		
		app1.begin();
		
		assertTrue(app1.isLoggedin());
		assertFalse(app1.isDataReceived());
		
	}
	
	/** Test Case ID: PSM001_Login-SubsystemTest-F18
	* Purpose: Test the call to the actual begin method and not its parsed out pieces. We set the values so
	* 	       that it takes a path thru LogIn, Authenticate, Ready, Edit Schedule and Logout states in the
	* 		   Begin State Chart.
	* Date Created: 10/27/12
	* Author: David Garcia
	* Stubs needed: DBConnection, InterfaceController, MainMenu, PrefilledScheduleForm, Messages
	*/
	@Test
	public void testBegin_BeginEditSched(){
		
		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		app1.LogIn();
		app1.setDataReceived(true);
		
		GregorianCalendar g = new GregorianCalendar();
		Date d = new Date(System.currentTimeMillis());
		g.setTime(d);
		

		g.set(g.get(Calendar.YEAR)+1,g.get(Calendar.MONTH),g.get(Calendar.DATE));
		Date passedDate = g.getTime();
		
		String endDate = DBUtil.passedAsString(passedDate);
		
		c1 = new Course(1234,"Subject","Name","Semester","10/01/12",endDate);
		c1.fillTimes(Course.DEFAULT_TIMES);
		
		db.addCourse(c1);
		//app1.setLoggedIn(false);
		//app1.setDataReceived(false);

		//app1.setLoggedIn(false);
		//app1.setDataReceived(true);
		//app1.setUsername(DBUtil.USERNAME);
		//app1.setPassword(DBUtil.PASSWORD);
		int counter = 0;
		app1.setCounter(counter);
		app1.setClassEnded(System.currentTimeMillis());
		MainMenu.dataRec = true;
		app1.getIc().mm.alwaystrue = true;
		//app1.getIc().mm.setLogout(true);
		//app1.setEdSchedSel(true);
		//app1.setSchedSetupSel(true);
		app1.getIc().mm.setEditSched(true);
		app1.getIc().mm.setLogout(false);
		app1.getIc().mm.togglelogout = true;
		//app1.getIc().mm.setInitSetup(true);
		app1.initAuthenticate(DBUtil.USERNAME, DBUtil.PASSWORD);
		
		//requires Authenticate stub in appController
//		app1.getAuth().passLogoutRef = true;
		app1.getIc().edSched.setDataRec(true);
		//app1.getDb().addCourse(c1);
		app1.getIc().edSched.addCourse(c1);
		app1.getIc().msg.exitTest = true;
		exit.expectSystemExitWithStatus(0);
		app1.begin();


		//app1.logOut();

	}
}
