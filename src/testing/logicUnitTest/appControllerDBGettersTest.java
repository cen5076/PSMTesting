package testing.logicUnitTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Logic.appController;

import testUtil.Course;
import testUtil.DBUtil;
import stubs.DBConnection;

public class appControllerDBGettersTest {

	appController app1;
	Course c1;
	DBConnection db;
	DBUtil dbUtil;
	
	@Before
	public void setUp() throws Exception {
		
		app1 = new appController();
		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		app1.LogIn();
		db = app1.getDb();
		c1= new Course(1234, "Subject", "Name", "Semester", Course.STARTDATE, Course.ENDDATE);
		c1.fillTimes(Course.DEFAULT_TIMES);
		
	}

	@After
	public void tearDown() throws Exception {
		
		app1 = null;
		c1 = null;
		db.clearDatabase();
		db = null;
		
	}

	/** Test Case ID: PSM001_Login-SubsystemTest-B01
	 * Purpose: To test the getData functionality that returns necessary setup data after successful login for a Course Id in the database.
	 * Date Created: 09/08/12
	 * Author: David Garcia
	 * Stubs needed: DBConnection
	 */
	@Test
	public void testGetData_1() {
		
		db.addCourse(c1);
		app1.getData(c1.getCrseid());
		
		assertEquals("Subject", c1.getCrseSub(), app1.getDefSub());
		assertEquals("Semester", c1.getSemester(), app1.getDefSemester());
        assertEquals("CourseName", c1.getCrseNam(), app1.getDefCourseName());
        assertEquals("CourseStart", c1.getStartdt(), app1.getDefCourseStart());
        assertEquals("CourseEnd", c1.getEnddt(), app1.getDefCourseEnd());
        assertEquals("MonStart", c1.getMonStart(), app1.getDefMonStart());
        assertEquals("MonEnd", c1.getMonEnd(), app1.getDefMonEnd());
        assertEquals("TueStart", c1.getTueStart(), app1.getDefTueStart());
        assertEquals("TueEnd", c1.getTueEnd(), app1.getDefTueEnd());
        assertEquals("WedStart", c1.getWedStart(), app1.getDefWedStart());
        assertEquals("WedEnd", c1.getWedEnd(), app1.getDefWedEnd());
        assertEquals("ThuStart", c1.getThuStart(), app1.getDefThuStart());
        assertEquals("ThuEnd", c1.getThuEnd(), app1.getDefThuEnd());
        assertEquals("FriStart", c1.getFriStart(), app1.getDefFriStart());
        assertEquals("FriEnd", c1.getFriEnd(), app1.getDefFriEnd());
        assertEquals("SatStart", c1.getSatStart(), app1.getDefSatStart());
        assertEquals("SatEnd", c1.getSatEnd(), app1.getDefSatEnd());
		
	}
	
	/** Test Case ID: PSM001_Login-SubsystemTest-B02
	 * Purpose: To test the getData functionality that returns necessary setup data after successful login for a Course Id NOT in the database.
	 * Date Created: 09/08/12
	 * Author: David Garcia
	 * Stubs needed: DBConnection
	 */
	@Test
	public void testGetData_2() {

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
