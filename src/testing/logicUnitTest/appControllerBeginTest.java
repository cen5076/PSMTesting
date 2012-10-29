package testing.logicUnitTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import Logic.appController;
import stubs.FutureTimer;
import testUtil.DBUtil;
import testUtil.Course;

/* 
 * This class tests the Begin method which has system functionality and is called within main.
 * 
 */

/*** Added for additional Coverage ***/
/** These are F package **/
@SuppressWarnings("unused")
public class appControllerBeginTest {
	
	appController app1;
	Course c1;


	@Rule
	public final ExpectedSystemExit exit = ExpectedSystemExit.none();
	
	@Before
	/*
	 * Create a new appController, call begin
	 * 
	 */
	public void setUp() throws Exception {
		
		app1 = new appController();
		app1.newTimer = new FutureTimer();

		//app1.newTimers() = new FutureTimer();
		//app1.begin();
		
	}

	@After
	public void tearDown() throws Exception {
		
		app1.getPopup5min().cancel();
		app1.getPopup15min().cancel();
		app1.getEndofclass().cancel();
		//app1.newTimer.purge();
		c1  =null;

		
		//app1.nullTimers();
		app1 = null;
		
	}
	

	/* Test States of Begin method */
	//@Test
	public void atestBegin_LoginState() {
		
		String messg = new Object(){}.getClass().getEnclosingMethod().getName(); 
		System.out.println("---" + messg + "---");
		
		app1.setLoggedIn(true);
		app1.setDataReceived(true);
		app1.getIc().log.username = DBUtil.USERNAME;
		app1.getIc().log.password = DBUtil.PASSWORD; 
		app1.loginState();
		assertEquals("Logged in Username",app1.getUsername(),DBUtil.USERNAME);
		assertEquals("Logged in Password",app1.getPassword(),DBUtil.PASSWORD);
		assertTrue("Logged in State",app1.isLoggedin());
		
	}
	
	//@Test
	public void testBegin_AuthenticateState(){
		
		String messg = new Object(){}.getClass().getEnclosingMethod().getName(); 
		System.out.println("---" + messg + "---");
		
		app1.setLoggedIn(false);
		app1.setDataReceived(true);
		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		int counter = 0;
		app1.setCounter(counter);
		
		app1.authenticate();
		
		
		assertTrue("Connected",app1.getDb().connected);
		assertEquals("Counter unchanged", counter , app1.getCounter());
		
		
	}
	
	@Test  //Valid
	public void testBegin_AuthenticateToLoginTransition(){
		
		String messg = new Object(){}.getClass().getEnclosingMethod().getName(); 
		System.out.println("---" + messg + "---");
		
		app1.setLoggedIn(false);
		app1.setUsername("BadUser");
		app1.setPassword("badPassword");
		int counter = 2;
		app1.setCounter(counter);
		app1.setDataReceived(true);
		
		exit.expectSystemExitWithStatus(0);
		app1.authenticate();
		
		assertFalse("Logged in",app1.isLoggedin());
		assertFalse("DataReceived toggled", app1.getDataReceived());
		
		
		
		
	}
	
	/**
	 * 
	 *  Does not clear the DB
	 *  
	 */
	//@Test
	public void testBegin_ReadyState_NoClearDB(){
		
		String messg = new Object(){}.getClass().getEnclosingMethod().getName(); 
		System.out.println("---" + messg + "---");
		
		app1.setLoggedIn(true);
		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		app1.setDataReceived(true);
		
		GregorianCalendar g = new GregorianCalendar();
		Date d = new Date(System.currentTimeMillis());
		g.setTime(d);
		g.set(g.get(Calendar.YEAR)+1,g.get(Calendar.MONTH),g.get(Calendar.DATE));
		Date passedDate = g.getTime();
		
		String endDate = DBUtil.pastAsString(passedDate);
		
		//Course c1 = new Course(1234,"Sub","Nam","Semester","01/01/12",endDate);
		Course c2 = new Course(2345,"Sub2","Nam2","Semester","01/01/12",endDate);
		c2.fillDates(DBUtil.defaultDates);	
		
		
		//System.out.println(endDate);
		//app1.getDb().courseList.add(c1);

		app1.getDb().courseList.add(c2);
		app1.getDb().courseSet.put(c2.crseid, c2);
		
		app1.ready();
		
		assertNotNull("Timer 5 before",app1.getPopup5min());
		assertNotNull("Timer 15 before",app1.getPopup15min());
		assertNotNull("Timer End of Class",app1.getEndofclass());
		//System.out.println( app1.getPopup5min().scheduledExecutionTime());
		assertTrue("Timer5 set", app1.getPopup5min().scheduledExecutionTime()> 0);
		assertTrue("Timer15 set",app1.getPopup15min().scheduledExecutionTime()>0);
		assertTrue("Timer End",app1.getEndofclass().scheduledExecutionTime()>0);
		
		
	}
	
	/**
	 * 
	 * Has an enddate that has passed and clears the DB
	 * 
	 */
	@Test  //Valid
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
		
		String endDate = DBUtil.pastAsString(passedDate);
		
		Course c1 = new Course(1234,"Sub","Nam","Semester","10/01/12",endDate);
		c1.fillDates(DBUtil.defaultDates);
		
		//System.out.println(endDate);
		app1.getDb().addCourse(c1);
		
		try{
			app1.ready();
		}
		catch(Exception e){
			
			fail("Exception caught-" + e.toString());
		}
		
		assertEquals("Timer 5 before",0,app1.getPopup5min().scheduledExecutionTime());
		assertEquals("Timer 15 before",0,app1.getPopup15min().scheduledExecutionTime());
		assertEquals("Timer End of Class",0,app1.getEndofclass().scheduledExecutionTime());
	
		
		
	}
	
	
	/**
	 * 
	 * Test the Logout state
	 * 
	 */
	//@Test
	/*public void ftestBegin_LogoutState(){
		
		String messg = new Object(){}.getClass().getEnclosingMethod().getName(); 
		System.out.println("---" + messg + "---");
		
		app1.setLoggedIn(true);
		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		app1.setDataReceived(true);
		
		//enter loop
		app1.setLogOutSel(false);
		app1.getIc().mm.setLogout(true);
		app1.getIc().mm.setdataRec(true);
		
		//make sure class has not ended
		app1.setClassEnded(0);
		app1.getDb().connected = true;
		app1.initAuthenticate(DBUtil.USERNAME, DBUtil.PASSWORD);

		app1.logOut();
		
		
		assertTrue("Logged out",app1.getIc().msg.getLogOut());
		assertFalse("Not LoggedIn",app1.isLoggedin());
		
		
	}
	
	@SuppressWarnings("static-access")
	//@Test
	public void gtestBegin_EditSchedState(){
		String messg = new Object(){}.getClass().getEnclosingMethod().getName(); 
		System.out.println("---" + messg + "---");
		
		app1.setLoggedIn(true);
		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		app1.setDataReceived(false);
		
		//enter loop
		app1.setLogOutSel(false);
		app1.getIc().mm.setEditSched(true);
		app1.getIc().mm.setdataRec(true);
		
		DBUtil dbUtil = new DBUtil();
		dbUtil.defaultCourseId = 1234;
		Course c1 = new Course(DBUtil.defaultCourseId,"Sub","Nam","Semester",Course.STARTDATE,Course.ENDDATE);
		c1.fillDates(DBUtil.defaultDates);
		
		app1.db.addCourse(c1);
		
		//make sure class has not ended
		app1.setClassEnded(0);
		app1.getDb().connected = true;
		app1.getIc().Course_Select_Form();
		app1.getIc().cs.setCourseSelected(true);
		app1.getIc().cs.setSelection(c1.crseid);
		//app1.getIc().cs.selection = c1.crseid;
		System.out.println("IC.cs.getselection()=" + app1.getIc().cs.getSelection());
		System.out.println("IC.cs.selection1=" + app1.getIc().cs.selection);

		app1.getIc().edSched.setDataRec(true);
		System.out.println("IC.cs.selection2=" + app1.getIc().cs.selection);

		app1.getIc().edSched.addCourse(c1);
		System.out.println("IC.cs.selection3=" + app1.getIc().cs.selection);

		app1.initAuthenticate(DBUtil.USERNAME, DBUtil.PASSWORD);
		System.out.println("IC.cs.selection4=" + app1.getIc().cs.selection);


		try{
			app1.editSchedule();
		}
		catch(Exception e){
			
			fail("Exception Caught-" + e.toString());
		}
		//assertFalse("Data Received", app1.getDataReceived());
		
	}
	
	//@Test
	public void htestBegin_SetScheduleState(){
		
		String messg = new Object(){}.getClass().getEnclosingMethod().getName(); 
		System.out.println("---" + messg + "---");
		
		app1.setLoggedIn(true);
		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		app1.setDataReceived(false);
		
		app1.getIc().sched.setDataRec(false);
		
		DBUtil dbUtil = new DBUtil();
		dbUtil.defaultCourseId = 1234;
		Course c1 = new Course(DBUtil.defaultCourseId,"Sub","Nam","Semester",Course.STARTDATE,Course.ENDDATE);
		c1.fillDates(DBUtil.defaultDates);
		
		app1.db.addCourse(c1);
		app1.getIc().sched.addCourse(c1);
		
		assertFalse("DataReceived",app1.getDataReceived());
		
	}
	*/
	@Test  //Valid
	public void testBegin_AllStates(){
		
		String messg = new Object(){}.getClass().getEnclosingMethod().getName(); 
		System.out.println("---" + messg + "---");
		

		/** LOGIN **/
		app1.setLoggedIn(true);
		app1.setDataReceived(true);
		app1.getIc().log.username = DBUtil.USERNAME;
		app1.getIc().log.password = DBUtil.PASSWORD; 
		app1.testingTimers = true;
		app1.loginState();
		
		/** Assert Logged In **/
		assertEquals("Logged in Username",app1.getUsername(),DBUtil.USERNAME);
		assertEquals("Logged in Password",app1.getPassword(),DBUtil.PASSWORD);
		assertTrue("Logged in State",app1.isLoggedin());
		
		/** SET Values for Authenticate ***/
		app1.setLoggedIn(false);
		app1.setDataReceived(true);
		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		int counter = 0;
		app1.setCounter(counter);
		
		app1.authenticate();
		//156
		
		assertTrue("Connected",app1.getDb().connected);
		assertEquals("Counter unchanged", counter , app1.getCounter());
		
		
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
		
		String endDate = DBUtil.pastAsString(passedDate);
		
		
		c1 = new Course(1234,"Sub","Nam","Semester","10/01/12",endDate);
		c1.fillDates(DBUtil.defaultDates);
		
		//System.out.println(endDate);
		try{
			Thread.sleep(300);
		}
		catch(Exception e){
			
		}
		
		assertNotNull("Database Null",app1.getDb());
		System.out.println("Course=" + c1.getCrseid());
		System.out.println(c1.toString());
		app1.getDb().addCourse(c1);
		
		try{
			app1.ready();
		}
		catch(IllegalStateException e){
			
			
		}
		
		/*** Assert in ready and timers set ***/
		//assertTrue("Timer 5 before",app1.getPopup5min().scheduledExecutionTime()> 0);
		//assertTrue("Timer 15 before",app1.getPopup15min().scheduledExecutionTime() > 0);
		//assertTrue("Timer End of Class",app1.getEndofclass().scheduledExecutionTime() > 0);
		
		app1.getIc().sched.dataReceived=false;
		
		DBUtil dbUtil = new DBUtil();
		dbUtil.defaultCourseId = 1234;
		Course c2 = new Course(DBUtil.defaultCourseId,"Sub","Nam","Semester",Course.STARTDATE,Course.ENDDATE);
		c1.fillDates(DBUtil.defaultDates);
		
		app1.getDb().addCourse(c2);
		app1.getIc().sched.addCourse(c2);
		app1.getIc().sched.dataReceived=true;
		
		app1.setSchedule();
		
		/*** Assert in Set Schedule ***/
		assertFalse("DataReceived",app1.getDataReceived());
		
		//app1.setLoggedIn(true);
		//app1.setUsername(DBUtil.USERNAME);
		//app1.setPassword(DBUtil.PASSWORD);
		app1.setDataReceived(false);
		
		//enter loop
		app1.setLogOutSel(false);
		app1.getIc().mm.setEditSched(true);
		app1.getIc().mm.setdataRec(true);
		
		//DBUtil dbUtil = new DBUtil();
		dbUtil.defaultCourseId = 1234;
		Course c3 = new Course(DBUtil.defaultCourseId,"Sub","Nam","Semester",Course.STARTDATE,Course.ENDDATE);
		c3.fillDates(DBUtil.defaultDates);
		
		app1.getDb().addCourse(c3);
		
		//make sure class has not ended
		app1.setClassEnded(0);

		app1.getDb().connected = true;
		app1.getIc().Course_Select_Form();
		app1.getIc().cs.setCourseSelected(true);
		app1.getIc().cs.setSelection(c3.crseid);
		//app1.getIc().cs.selection = c1.crseid;
		System.out.println("IC.cs.getselection()=" + app1.getIc().cs.getSelection());
		System.out.println("IC.cs.selection1=" + app1.getIc().cs.selection);

		app1.getIc().edSched.setDataRec(true);
		System.out.println("IC.cs.selection2=" + app1.getIc().cs.selection);

		app1.getIc().edSched.addCourse(c3);
		System.out.println("IC.cs.selection3=" + app1.getIc().cs.selection);

		//app1.initAuthenticate(DBUtil.USERNAME, DBUtil.PASSWORD);
		System.out.println("IC.cs.selection4=" + app1.getIc().cs.selection);


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

		app1.getDb().connected = true;
		app1.initAuthenticate(DBUtil.USERNAME, DBUtil.PASSWORD);

		app1.logOut();
		
		
		assertTrue("Logged out",app1.getIc().msg.getLogOut());
		///assertFalse("Not LoggedIn",app1.isLoggedin());
		
		
	}
	
	@Test  //Valid
	public void testBegin_TestDaysBranchesMon(){
		
		//Set the date to a Monday
		try {
			Runtime.getRuntime().exec("cmd /C date " + "10-22-12");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // dd-MM-yy
		
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
		
		String endDate = DBUtil.pastAsString(passedDate);
		
		
		c1 = new Course(1234,"Sub","Nam","Semester","10/01/12",endDate);
		c1.fillDates(DBUtil.defaultDates);
		
		//System.out.println(endDate);
		try{
			Thread.sleep(300);
		}
		catch(Exception e){
			
		}
		
		assertNotNull("Database Null",app1.getDb());
		System.out.println("Course=" + c1.getCrseid());
		System.out.println(c1.toString());
		app1.getDb().addCourse(c1);
		
		try{
			app1.ready();
		}
		catch(IllegalStateException e){
			
			
		}
	}
	
	@Test  //Valid
	public void testBegin_TestDaysBranchesTue(){
		
		//Set the date to a Monday
		try {
			Runtime.getRuntime().exec("cmd /C date " + "10-23-12");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // dd-MM-yy
		
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
		
		String endDate = DBUtil.pastAsString(passedDate);
		
		
		c1 = new Course(1234,"Sub","Nam","Semester","10/01/12",endDate);
		c1.fillDates(DBUtil.defaultDates);
		
		//System.out.println(endDate);
		try{
			Thread.sleep(300);
		}
		catch(Exception e){
			
		}
		
		assertNotNull("Database Null",app1.getDb());
		System.out.println("Course=" + c1.getCrseid());
		System.out.println(c1.toString());
		app1.getDb().addCourse(c1);
		
		try{
			app1.ready();
		}
		catch(IllegalStateException e){
			
			
		}
	}
	@Test  //Valid
	public void testBegin_TestDaysBranchesWed(){
		
		//Set the date to a Monday
		try {
			Runtime.getRuntime().exec("cmd /C date " + "10-24-12");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // dd-MM-yy
		
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
		
		String endDate = DBUtil.pastAsString(passedDate);
		
		
		c1 = new Course(1234,"Sub","Nam","Semester","10/01/12",endDate);
		c1.fillDates(DBUtil.defaultDates);
		
		//System.out.println(endDate);
		try{
			Thread.sleep(300);
		}
		catch(Exception e){
			
		}
		
		assertNotNull("Database Null",app1.getDb());
		System.out.println("Course=" + c1.getCrseid());
		System.out.println(c1.toString());
		app1.getDb().addCourse(c1);
		
		try{
			app1.ready();
		}
		catch(IllegalStateException e){
			
			
		}
	}
	
	@Test  //Valid
	public void testBegin_TestDaysBranchesThu(){
		
		//Set the date to a Monday
		try {
			Runtime.getRuntime().exec("cmd /C date " + "10-25-12");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // dd-MM-yy
		
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
		
		String endDate = DBUtil.pastAsString(passedDate);
		
		
		c1 = new Course(1234,"Sub","Nam","Semester","10/01/12",endDate);
		c1.fillDates(DBUtil.defaultDates);
		
		//System.out.println(endDate);
		try{
			Thread.sleep(300);
		}
		catch(Exception e){
			
		}
		
		assertNotNull("Database Null",app1.getDb());
		System.out.println("Course=" + c1.getCrseid());
		System.out.println(c1.toString());
		app1.getDb().addCourse(c1);
		
		try{
			app1.ready();
		}
		catch(IllegalStateException e){
			
			
		}
	}
	@Test  //Valid
	public void testBegin_TestDaysBranchesFri(){
		
		//Set the date to a Monday
		try {
			Runtime.getRuntime().exec("cmd /C date " + "10-26-12");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // dd-MM-yy
		
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
		
		String endDate = DBUtil.pastAsString(passedDate);
		
		
		c1 = new Course(1234,"Sub","Nam","Semester","10/01/12",endDate);
		c1.fillDates(DBUtil.defaultDates);
		
		//System.out.println(endDate);
		try{
			Thread.sleep(300);
		}
		catch(Exception e){
			
		}
		
		assertNotNull("Database Null",app1.getDb());
		System.out.println("Course=" + c1.getCrseid());
		System.out.println(c1.toString());
		app1.getDb().addCourse(c1);
		
		try{
			app1.ready();
		}
		catch(IllegalStateException e){
			
			
		}
	}
	@Test  //Valid
	public void testBegin_TestDaysBranchesSat(){
		
		//Set the date to a Monday
		try {
			Runtime.getRuntime().exec("cmd /C date " + "10-27-12");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // dd-MM-yy
		
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
		
		String endDate = DBUtil.pastAsString(passedDate);
		
		
		c1 = new Course(1234,"Sub","Nam","Semester","10/01/12",endDate);
		c1.fillDates(DBUtil.defaultDates);
		
		//System.out.println(endDate);
		try{
			Thread.sleep(300);
		}
		catch(Exception e){
			
		}
		
		assertNotNull("Database Null",app1.getDb());
		System.out.println("Course=" + c1.getCrseid());
		System.out.println(c1.toString());
		app1.getDb().addCourse(c1);
		
		try{
			app1.ready();
		}
		catch(IllegalStateException e){
			
			
		}
	}
	@Test  //Valid
	public void testBegin_TestDaysBranchesSun(){
		
		//Set the date to a Monday
		try {
			Runtime.getRuntime().exec("cmd /C date " + "10-28-12");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // dd-MM-yy
		
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
		
		String endDate = DBUtil.pastAsString(passedDate);
		
		
		c1 = new Course(1234,"Sub","Nam","Semester","10/01/12",endDate);
		c1.fillDates(DBUtil.defaultDates);
		
		//System.out.println(endDate);
		try{
			Thread.sleep(300);
		}
		catch(Exception e){
			
		}
		
		assertNotNull("Database Null",app1.getDb());
		System.out.println("Course=" + c1.getCrseid());
		System.out.println(c1.toString());
		app1.getDb().addCourse(c1);
		
		try{
			app1.ready();
		}
		catch(IllegalStateException e){
			
			
		}
	}
	
	//@Test
	public void testBegin_Begin(){
		
		app1.setLoggedIn(true);
		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		app1.setDataReceived(true);
		
		GregorianCalendar g = new GregorianCalendar();
		Date d = new Date(System.currentTimeMillis());
		g.setTime(d);
		
		//Use ClearDatabase branch
		g.set(g.get(Calendar.YEAR)-1,g.get(Calendar.MONTH),g.get(Calendar.DATE));
		Date passedDate = g.getTime();
		
		String endDate = DBUtil.pastAsString(passedDate);
		
		
		c1 = new Course(1234,"Sub","Nam","Semester","10/01/12",endDate);
		c1.fillDates(DBUtil.defaultDates);
		
		app1.getDb().addCourse(c1);
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
		app1.initAuthenticate(DBUtil.USERNAME,DBUtil.PASSWORD);
		
		app1.begin();
		
	}
	
	@Test
	public void testBegin_BeginEditSched(){
		
		app1.setLoggedIn(true);
		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		app1.setDataReceived(true);
		
		GregorianCalendar g = new GregorianCalendar();
		Date d = new Date(System.currentTimeMillis());
		g.setTime(d);
		

		g.set(g.get(Calendar.YEAR)+1,g.get(Calendar.MONTH),g.get(Calendar.DATE));
		Date passedDate = g.getTime();
		
		String endDate = DBUtil.pastAsString(passedDate);
		
		
		c1 = new Course(1234,"Sub","Nam","Semester","10/01/12",endDate);
		c1.fillDates(DBUtil.defaultDates);
		
		app1.getDb().addCourse(c1);
		//app1.setLoggedIn(false);
		//app1.setDataReceived(false);

		//app1.setLoggedIn(false);
		//app1.setDataReceived(true);
		//app1.setUsername(DBUtil.USERNAME);
		//app1.setPassword(DBUtil.PASSWORD);
		int counter = 0;
		app1.setCounter(counter);	
		app1.setClassEnded(System.currentTimeMillis());
		app1.getIc().mm.dataRec = true;
		app1.getIc().mm.alwaystrue = true;
		//app1.getIc().mm.setLogout(true);
		//app1.setEdSchedSel(true);
		//app1.setSchedSetupSel(true);
		app1.getIc().mm.setEditSched(true);
		app1.getIc().mm.setLogout(false);
		app1.getIc().mm.togglelogout = true;
		//app1.getIc().mm.setInitSetup(true);
		app1.initAuthenticate(DBUtil.USERNAME,DBUtil.PASSWORD);
		app1.getAuth().passLogoutRef = true;
		app1.getIc().edSched.setDataRec(true);
		//app1.getDb().addCourse(c1);
		app1.getIc().edSched.addCourse(c1);
		app1.getIc().msg.exitTest = true;
		exit.expectSystemExitWithStatus(0);
		app1.begin();


		//app1.logOut();


	}

}
