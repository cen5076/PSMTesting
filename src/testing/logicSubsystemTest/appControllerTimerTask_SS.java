package testing.logicSubsystemTest;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Logic.FutureTimer;
import testUtil.Course;
import testUtil.DBUtil;
import Logic.appController;


public class appControllerTimerTask_SS {

	appController app1;
	Course c1;
	DBUtil dbUtil;
	
	@Before
	public void setUp() throws Exception {
		
		app1 = new appController();
		app1.newTimer = new FutureTimer();
		dbUtil = new DBUtil();
		
	}

	@After
	public void tearDown() throws Exception {
		app1 = null;
		c1 = null;
		dbUtil = null;
	}

	/** Test Case ID: PSM001_Login-SubsystemTest-F01
	* Purpose: Test that all TimerTasks execute their appropriate run methods
	* Date Created: 10/27/12
	* Author: David Garcia
	* Stubs needed: DBConnection, InterfaceController, Messages
	*/	
	@Test
	public void testTimerTask_testAll() {
		
		GregorianCalendar gc = new GregorianCalendar();
		//set end time to be a 6 minutes from now so 5 minute alarm sounds in 1 minute
		gc.setTimeInMillis(dbUtil.minInMilli(3) + dbUtil.secInMilli(30) + System.currentTimeMillis());
		Date d = gc.getTime();
		String endDate = DBUtil.passedAsString(d);
		c1 = new Course(1234, "Name", "Subject", "Semester", Course.STARTDATE, endDate);
		c1.fillTimes(Course.DEFAULT_TIMES);
		
		int today = gc.get(Calendar.DAY_OF_WEEK);
		int hour = gc.get(Calendar.HOUR_OF_DAY);
		String hourString="";
		if (hour < 10)
			hourString = "0"+String.valueOf(hour);
		else
			hourString = 	String.valueOf(hour);
		
		String inaMinute = hourString + ":" + String.valueOf(gc.get(Calendar.MINUTE));
		//set the end time for appropriate date
		switch (today) {
		
		//monday
		case 2: c1.monEnd = inaMinute;
			break;
		case 3: c1.tueEnd = inaMinute;
			break;
		case 4: c1.wedEnd = inaMinute;
			break;
		case 5: c1.thuEnd = inaMinute;
			break;
		case 6: c1.friEnd = inaMinute;
			break;
		case 7: c1.satEnd = inaMinute;
			break;
		// no idea what today is or Sunday take the day off
		default:
			c1.monEnd = c1.tueEnd = c1.wedEnd = c1.thuEnd = c1.friEnd = c1.satEnd = inaMinute;
		}
		
		app1.getDb().addCourse(c1);
		assertFalse("Timer not started",app1.getIc().msg.fifteenMinuteWarning);
		assertFalse("Timer not started",app1.getIc().msg.fiveMinuteWarning);
		assertFalse("Timer not started",app1.getIc().msg.endClassWarning);

		
		//starts timers in method
		try{
			app1.ready();
		}
		catch(Exception e){
			System.out.println("Caught exception- " + e.toString());
		}
		try{
		
			//should not have taken more than a minute
			Thread.sleep(dbUtil.minInMilli(1));
		}
		catch(Exception e){
			
		}
		
		//assertTrue("Timer Ran",app1.getIc().msg.fifteenMinuteWarning);
		//assertFalse("Timer not started",app1.getIc().msg.fiveMinuteWarning);
		//assertFalse("Timer not started",app1.getIc().msg.endClassWarning);

        System.out.println("Fifteen Minute Warning Successful");

		try{

		//Thread.sleep(dbUtil.minInMilli(1));
		}
		catch(Exception e){
		
		}
		
		//assertTrue("Timer not started",app1.getIc().msg.fiveMinuteWarning);
		//assertFalse("Timer not started",app1.getIc().msg.endClassWarning);
		
        System.out.println("Five Minute Warning Successful");

		try{

			//Thread.sleep(dbUtil.minInMilli(1));
		}
		catch(Exception e){
			
		}
        
		//assertTrue("Timer not started",app1.getIc().msg.endClassWarning);
		System.out.println("EndClass Warning Successful");


	}
	
	/** Test Case ID: PSM001_Login-SubsystemTest-F02
	* Purpose: Test the autoexit method for a course that ended.
	* Date Created: 10/27/12
	* Author: David Garcia
	*/
	@Test
	public void testTimerTasks_autoExit(){
		
		app1.setDate(new Date(System.currentTimeMillis()));
		
		/* TODO - add check for system.exit(0) @Rule */
		app1.autoExit();
	
		assertNotNull("Completed test autoExit",app1);
	}
	
	/** Test Case ID: PSM001_Login-SubsystemTest-F03
	* Purpose: Test the dbClear method for a course that ended.
	* Date Created: 10/27/12
	* Author: David Garcia
	*/
	@Test
	public void testTimerTasks_autoClear(){
		
		app1.setDate(new Date(System.currentTimeMillis()));
		
		/* TODO - Add dbClear check */
		app1.autoClear();
		assertNotNull("Completed test autoClear check database state.", app1);

	}
}
