package testing.logicSubsystemTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Logic.appController;

import stubs.DBConnection;
import testUtil.Course;
import testUtil.DBUtil;

public class appControllerGetData_SS {

	ArrayList<Course> courseList;
	appController app1;
	Course c1, c2;
	DBConnection dbc;

	
	@Before
	public void setUp() throws Exception {
		app1 = new appController();
		dbc = app1.getDb();
		dbc.connect("cen5076", "cen5076");
		c1 = new Course(1234,"Sub", "Nam", "Semester", Course.STARTDATE, Course.ENDDATE);
		c1.fillDates(DBUtil.defaultDates);
		c2 = new Course(2345, "Sub2", "Nam2", "Semester", Course.STARTDATE, Course.ENDDATE);
		c2.fillDates(DBUtil.defaultDates);
		courseList = new ArrayList<Course>();
	}

	@After
	public void tearDown() throws Exception {
		
		app1 = null;
		c1 = null;
		courseList.clear();
		dbc.clearDatabase();
	}
	
	
	@Test //PSM001_Login-SubSystemTest-B01
	/**
	 * Test ID: PSM001_Login-SubSystemTest-B01
	 * Purpose: To test the getData functionality that returns necessary setup data after successful login for a Course Id in the database.
	 * Date Created: 09/18/12
	 * Author: Jose Borja
	 * Stubs Needed: 
	 */
	/* Test a course id in database */
	public void testGetData2_SS() {
		
		// DEBUG message
		String messg = new Object(){}.getClass().getEnclosingMethod().getName(); 
		System.out.println("---" + messg + "---");
		
		dbc.addCourse(c1);
		
		System.out.println(c1.getCrseid());
		app1.getData(this.c1.getCrseid());
		
		assertEquals("Subject", this.c1.getCrseSub(), app1.getDefSub());
		assertEquals("Semester", this.c1.getSemester(), app1.getDefSemester());
        assertEquals("CourseName", this.c1.getCrseNam(), app1.getDefCourseName());
        assertEquals("CourseStart", this.c1.getStartdt(), app1.getDefCourseStart());
        assertEquals("CourseEnd", this.c1.getEnddt(), app1.getDefCourseEnd());
        assertEquals("MonStart", this.c1.getMonStart(), app1.getDefMonStart());
        assertEquals("MonEnd", this.c1.getMonEnd(), app1.getDefMonEnd());
        assertEquals("TueStart", this.c1.getTueStart(), app1.getDefTueStart());
        assertEquals("TueEnd", this.c1.getTueEnd(), app1.getDefTueEnd());
        assertEquals("WedStart", this.c1.getWedStart(), app1.getDefWedStart());
        assertEquals("WedEnd", this.c1.getWedEnd(), app1.getDefWedEnd());
        assertEquals("ThuStart", this.c1.getThuStart(), app1.getDefThuStart());
        assertEquals("ThuEnd", this.c1.getThuEnd(), app1.getDefThuEnd());
        assertEquals("FriStart", this.c1.getFriStart(), app1.getDefFriStart());
        assertEquals("FriEnd", this.c1.getFriEnd(), app1.getDefFriEnd());
        assertEquals("SatStart", this.c1.getSatStart(), app1.getDefSatStart());
        assertEquals("SatEnd", this.c1.getSatEnd(), app1.getDefSatEnd());
     
		System.out.println("END testGetData");

	}

	@Test //PSM001_Login-SubSystemTest-B02
	/**
	 * Test ID: PSM001_Login-SubSystemTest-B01
	 * Purpose: To test the getData functionality that returns necessary setup data after successful login for a Course Id NOT in the database.
	 * Date Created: 09/18/12
	 * Author: Jose Borja
	 * Stubs Needed: 
	 */
	/* Test a course id not in database */
	public void testGetData1_SS() {
		String messg = new Object(){}.getClass().getEnclosingMethod().getName(); 
		System.out.println("---" + messg + "---");
		
		//get Course 1
		app1.getData(c1.getCrseid());
	
		assertNull("Subject", app1.getDefSub());
		assertNull("Semester", app1.getDefSemester());
		assertNull("CourseName", app1.getDefCourseName());
		assertNull("CourseStart", app1.getDefCourseStart());
		assertNull("CourseEnd", app1.getDefCourseEnd());
		assertNull("MonStart", app1.getDefMonStart());
		assertNull("MonEnd", app1.getDefMonEnd());
		assertNull("TueStart", app1.getDefTueStart());
		assertNull("TueEnd", app1.getDefTueEnd());
		assertNull("WedStart", app1.getDefWedStart());
		assertNull("WedEnd", app1.getDefWedEnd());
		assertNull("ThuStart", app1.getDefThuStart());
		assertNull("ThuEnd", app1.getDefThuEnd());
		assertNull("FriStart", app1.getDefFriStart());
		assertNull("FriEnd", app1.getDefFriEnd());
		assertNull("SatStart", app1.getDefSatStart());
		assertNull("SatEnd", app1.getDefSatEnd());
	
		}
	}

/* System.out.println("TuesdayStart=" + app1.getDefTueStart());
if( app1.getDefTueStart() == null){
	System.out.println("NULL!!!");
}
else if(app1.getDefTueStart() == " "){
	System.out.println("Space!!!!");
}
else if (app1.getDefTueStart() == ""){
	System.out.println("Empty!!!!");
}
else
	System.out.println("WTF!!??"); */
