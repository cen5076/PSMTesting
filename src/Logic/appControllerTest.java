package Logic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class appControllerTest {

	private appController app1;
	
	@Before
	public void setUp() throws Exception {
		
		app1 = new appController();
		
	}

	@After
	public void tearDown() throws Exception {
		
		app1 = null;
	}

	@Test
	/* Assure that the values are initiated to zero */
	public void testAppController() {
		fail("Not yet implemented");
		//assertEquals("Zero Hours",0,app1.getHr());
		//assertEquals("Zero Minutes",0,app1.getMin());
		//TODO check time requirement for ten minutes
		//assertEquals("Ten Minutes",36000,app1.getTenmin());
		//assertEquals("Counter",0,app1.getCounter());
		//assertEquals("Data Received",false,app1.getEdSchedSel());
		//assertEquals("Schedule Setup Select",false,app1.getSchedSetupSel());
	}

	@Test
	public void testMain() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckClear() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckTimes() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetData() {
		/*
		defSub = db.fetchCourseSubj(course);
        defSemester = db.fetchCourseSemester(course);
        defCourseName = db.fetchCourseName(course);
        defCourseStart = db.fetchCourseStart(course);
        defCourseEnd = db.fetchCourseEnd(course);
        defMonStart = db.fetchStartMon(course);
        defMonEnd = db.fetchEndMon(course);
        defTueStart = db.fetchStartTue(course);
        defTueEnd = db.fetchEndTue(course);
        defWedStart = db.fetchStartWed(course);
        defWedEnd = db.fetchEndWed(course);
        defThuStart = db.fetchStartThu(course);
        defThuEnd = db.fetchEndThu(course);
        defFriStart = db.fetchStartFri(course);
        defFriEnd = db.fetchEndFri(course);
        defSatStart = db.fetchStartSat(course);
        defSatEnd = db.fetchEndSat(course);   
        */
		//assertEquals("Subject",,app1.getDefSub());
		//assertEquals("Semester",)
	}
		 

	@Test
	public void testSleep() {
		fail("Not yet implemented");
	}

	@Test
	public void testLogIn() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCon() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTime() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTime() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTimeMillis() {
		fail("Not yet implemented");
	}

	@Test
	public void testTimerParser() {
		fail("Not yet implemented");
	}

	@Test
	public void testDateParser() {
		fail("Not yet implemented");
	}

	@Test
	public void testReturnHr() {
		fail("Not yet implemented");
	}

	@Test
	public void testReturnMin() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEndTime() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetSemesterClear() {
		fail("Not yet implemented");
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

	@Test
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

}
