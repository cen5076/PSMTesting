package testing.dataStoreUnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import DataStore.DBConnection;
import testUtil.Course;
import testUtil.DBUtil;

public class DBConnectionTest3 {

	private static DBConnection dbc;
	private static Connection myCon;
	private static DBUtil dbu;
	private static Course c1;
	
	private static final String USERNAME = DBUtil.USERNAME;
	private static final String PASSWORD = DBUtil.PASSWORD;
	private static final int BADCRSEID = 9999;
	
	
	@BeforeClass
	public static void setUpOnce() throws Exception {
		dbc = new DBConnection();
		dbc.connect(USERNAME, PASSWORD);
		myCon = dbc.getMyCon();
		
		c1 = new Course(1234, "Subject", "Name", "Semester", Course.STARTDATE, Course.ENDDATE);
	}
	
	@Before
	public void setUp() throws Exception {
		dbu = new DBUtil();
		dbc.createClassTable();
		dbc.storeClassInfo(c1.getCrseid(), c1.getCrseSub(), c1.getCrseNam(), c1.getSemester());
		dbc.storeClassSched(c1.getCrseid(), c1.getStartdt(), c1.getEnddt(), c1.getMonStart(), c1.getMonEnd(),
				c1.getTueStart(), c1.getTueEnd(), c1.getWedStart(), c1.getWedEnd(), c1.getThuStart(), c1.getThuEnd(),
				c1.getFriStart(), c1.getFriEnd(), c1.getSatStart(), c1.getSatEnd());
	}
	
	@After
	public void tearDown() throws Exception {
		dbu = null;
	}

	@AfterClass
	public static void tearDownOnce() throws Exception {
		dbc.disconnect();
		dbc = null;
	}
	
	/** Test Case ID: PSM001_Login-UnitTest-I01
	 * Purpose: Test the clearing of the Class100 table
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testClearDatabase() {
		dbc.clearDatabase();
		try {
			Statement s = myCon.createStatement();
			ResultSet res = s.executeQuery("SELECT * FROM Class100;");
	        assertFalse("Get no rows", res.next());
	        res.close();
	        s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Test Case ID: PSM001_Login-UnitTest-I02
	 * Purpose: Test the clearing of the Class100 table when it doesn’t exist
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testClearDatabase2() {
		try {
			Statement s = myCon.createStatement();
			s.executeUpdate("DROP TABLE IF EXISTS Class100");
			dbc.clearDatabase();
	        s.close();
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception thrown");
		}
	}
	
	/** Test Case ID: PSM001_Login-UnitTest-I03
	 * Purpose: Test fetching a course ID from the database
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchCourseID() {
		int result = dbc.fetchCourseID(c1.getCrseid());
		
		assertEquals("Course ID", c1.getCrseid(), result);
	}
	
	/** Test Case ID: PSM001_Login-UnitTest-I04
	 * Purpose: Test fetching a course ID from the database without that course
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchCourseID2() {
		int result = dbc.fetchCourseID(BADCRSEID);
		assertEquals("Course ID", -1, result);
	}

	/** Test Case ID: PSM001_Login-UnitTest-I05
	 * Purpose: Test that all end dates in DB are returned
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testGetEndDates() {
		Course c2 = new Course(2345, "Subject2", "Name2", "Semester", "10/10/12", "12/10/12");
		dbu.insertCourse(c2);
		
		ArrayList<String> endDates = dbc.getEndDates();
		ArrayList<String> allDates = new ArrayList<String>();
		allDates.add(c1.getEnddt());
		allDates.add(c2.getEnddt());
		
		Collections.sort(endDates);
		Collections.sort(allDates);
		
		assertEquals("End Date ArrayList", allDates, endDates);
	}
	
	/** Test Case ID: PSM001_Login-UnitTest-I06
	 * Purpose: Test that no end dates are returned for empty Class 100 table
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testGetEndDates2() {
		dbc.clearDatabase();
		
		ArrayList<String> endDates = dbc.getEndDates();
		
		assertTrue("End Date ArrayList", endDates.isEmpty());
	}
	
	/** Test Case ID: PSM001_Login-UnitTest-I07
	 * Purpose: Test that no course IDs are returned for empty Class 100 table
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testGetCourses() {
		Course c2 = new Course(2345, "Subject2", "Name2", "Semester", "10/10/12", "12/10/12");
		dbc.storeClassInfo(c2.getCrseid(), c2.getCrseSub(), c2.getCrseNam(), c2.getSemester());
		dbu.addCourse(c1);
		dbu.addCourse(c2);
		
		ArrayList<Integer> actuals = dbc.getCourses();
		ArrayList<Integer> expected = dbu.getCourseIds();
		Collections.sort(actuals);
		Collections.sort(expected);
		
		assertEquals("CourseID ArrayList", expected, actuals);
	}
	
	/** Test Case ID: PSM001_Login-UnitTest-I08
	 * Purpose: Test that all course IDs in DB are returned
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testGetCourses2() {
		dbc.clearDatabase();
		
		ArrayList<Integer> actuals = dbc.getCourses();
		
		assertTrue("CourseID ArrayList", actuals.isEmpty());
	}
	
	/** Test Case ID: PSM001_Login-UnitTest-I09
	 * Purpose: Test that the correct course subject is fetched
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchCourses() {
		Course c2 = new Course(2345, "Subject2", "Name2", "Semester", "10/10/12", "12/10/12");
		dbc.storeClassInfo(c2.getCrseid(), c2.getCrseSub(), c2.getCrseNam(), c2.getSemester());
		dbu.addCourse(c1);
		dbu.addCourse(c2);
		
		String courses = dbc.fetchCourses();
		
		StringTokenizer p = new StringTokenizer(courses, ",");
		SortedSet<String> orderedCourses = new TreeSet<String>();
		while(p.hasMoreElements()){
			orderedCourses.add(p.nextToken());
		}
		String ordered = new ArrayList<String>(orderedCourses).toString();
		
		ArrayList<Integer> expected = dbu.getCourseIds();
		Collections.sort(expected);
		
		assertEquals("Fetch Course IDs", expected.toString(), ordered);
	}
	
	/** Test Case ID: PSM001_Login-UnitTest-I10
	 * Purpose: Test fetching the course subject for a course that does not exist
	 * Date Created: 09/15/12
	 * Author: Matthew Brown 
	 */
	@Test
	public void testFetchCourses2() {
		dbc.clearDatabase();
		
		String courses = dbc.fetchCourses();
		
		assertEquals("Fetch Course IDs", "", courses);
	}

	/** Test Case ID: PSM001_Login-UnitTest-I11
	 * Purpose: Test that the correct semester is fetched
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchCourseSubj() {
		String result = dbc.fetchCourseSubj(c1.crseid);
		assertEquals("Fetch Subject", c1.getCrseSub(), result);
	}
	
	/** Test Case ID: PSM001_Login-UnitTest-I12
	 * Purpose: Test fetching the semester for a course that does not exist
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchCourseSubj2() {
		String result = dbc.fetchCourseSubj(BADCRSEID);
		assertNull("Fetch Subject", result);
	}

	/** Test Case ID: PSM001_Login-UnitTest-I13
	 * Purpose: Test that the correct start date is fetched
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchCourseName() {
		String result = dbc.fetchCourseName(c1.getCrseid());
		assertEquals("Fetch Name", c1.getCrseNam(), result);
	}
	
	/** Test Case ID: PSM001_Login-UnitTest-I14
	 * Purpose: Test fetching the start date for a course that does not exist
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchCourseName2() {
		String result = dbc.fetchCourseName(BADCRSEID);
		assertNull("Fetch Name", result);
	}

	/** Test Case ID: PSM001_Login-UnitTest-I15
	 * Purpose: Test that the correct end date is fetched
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchCourseSemester() {
		String result = dbc.fetchCourseSemester(c1.getCrseid());
		assertEquals("Fetch Semester", c1.getSemester(), result);
	}

	/** Test Case ID: PSM001_Login-UnitTest-I16
	 * Purpose: Test fetching the end date for a course that does not exist
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchCourseSemester2() {
		String result = dbc.fetchCourseSemester(BADCRSEID);
		assertNull("Fetch Semester", result);
	}
	
	/** Test Case ID: PSM001_Login-UnitTest-I17
	 * Purpose: Test that the correct time is fetched
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchCourseStart() {
		String result = dbc.fetchCourseStart(c1.getCrseid());
		assertEquals("Fetch Course Start", c1.getStartdt(), result);
	}
	
	/** Test Case ID: PSM001_Login-UnitTest-I18
	 * Purpose: Test fetching a time for a course that does not exist
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchCourseStart2() {
		String result = dbc.fetchCourseStart(BADCRSEID);
		assertNull("Fetch Course Start", result);
	}

	/** Test Case ID: PSM001_Login-UnitTest-I19
	 * Purpose: Test that the correct time is fetched
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchCourseEnd() {
		String result = dbc.fetchCourseEnd(c1.getCrseid());
		System.out.println("result: " + result);
		System.out.println("Enddt: " + c1.getEnddt());
		assertEquals("Fetch Course End", c1.getEnddt(), result);
	}
	
	/** Test Case ID: PSM001_Login-UnitTest-I20
	 * Purpose: Test fetching a time for a course that does not exist
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchCourseEnd2() {
		String result = dbc.fetchCourseEnd(BADCRSEID);
		assertNull("Fetch Course End", result);
	}

	/** Test Case ID: PSM001_Login-UnitTest-I21
	 * Purpose: Test that the correct time is fetched
	 * Date Created: 09/15/12
	 * Author: Matthew Brown 
	 */
	@Test
	public void testFetchStartMon() {
		String result = dbc.fetchStartMon(c1.getCrseid());
		assertEquals("Fetch Start Monday", c1.getMonStart(), result);
	}
	
	/** Test Case ID: PSM001_Login-UnitTest-I22
	 * Purpose: Test fetching a time for a course that does not exist
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchStartMon2() {
		String result = dbc.fetchStartMon(BADCRSEID);
		assertNull("Fetch Start Monday", result);
	}

	/** Test Case ID: PSM001_Login-UnitTest-I23
	 * Purpose: Test that the correct time is fetched
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchEndMon() {
		String result = dbc.fetchEndMon(c1.getCrseid());
		assertEquals("Fetch End Monday", c1.getMonEnd(), result);
	}
	
	/** Test Case ID: PSM001_Login-UnitTest-I24
	 * Purpose: Test fetching a time for a course that does not exist
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchEndMon2() {
		String result = dbc.fetchEndMon(BADCRSEID);
		assertNull("Fetch End Monday", result);
	}

	/** Test Case ID: PSM001_Login-UnitTest-I25
	 * Purpose: Test that the correct time is fetched
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchStartTue() {
		String result = dbc.fetchStartTue(c1.getCrseid());
		assertEquals("Fetch Start Tuesday", c1.getTueStart(), result);
	}
	
	/** Test Case ID: PSM001_Login-UnitTest-I26
	 * Purpose: Test fetching a time for a course that does not exist
	 * Date Created: 09/18/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchStartTue2() {
		String result = dbc.fetchStartTue(BADCRSEID);
		assertNull("Fetch Start Tuesday", result);
	}

	/** Test Case ID: PSM001_Login-UnitTest-I27
	 * Purpose: Test that the correct time is fetched
	 * Date Created: 09/18/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchEndTue() {
		String result = dbc.fetchEndTue(c1.getCrseid());
		assertEquals("Fetch End Tuesday", c1.getTueEnd(), result);
	}
	
	/** Test Case ID: PSM001_Login-UnitTest-I28
	 * Purpose: Test fetching a time for a course that does not exist
	 * Date Created: 09/18/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchEndTue2() {
		String result = dbc.fetchEndTue(BADCRSEID);
		assertNull("Fetch End Tuesday", result);
	}

	/** Test Case ID: PSM001_Login-UnitTest-I29
	 * Purpose: Test that the correct time is fetched
	 * Date Created: 09/18/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchStartWed() {
		String result = dbc.fetchStartWed(c1.getCrseid());
		assertEquals("Fetch Start Wednesday", c1.getWedStart(), result);
	}
	
	/** Test Case ID: PSM001_Login-UnitTest-I30
	 * Purpose: Test fetching a time for a course that does not exist
	 * Date Created: 09/18/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchStartWed2() {
		String result = dbc.fetchStartWed(BADCRSEID);
		assertNull("Fetch Start Wednesday", result);
	}

	/** Test Case ID: PSM001_Login-UnitTest-I31
	 * Purpose: Test that the correct time is fetched
	 * Date Created: 09/18/12
	 * Author: Matthew Brown 
	 */
	@Test
	public void testFetchEndWed() {
		String result = dbc.fetchEndWed(c1.getCrseid());
		assertEquals("Fetch End Wednesday", c1.getWedEnd(), result);
	}
	
	/** Test Case ID: PSM001_Login-UnitTest-I32
	 * Purpose: Test fetching a time for a course that does not exist
	 * Date Created: 09/18/12
	 * Author: Matthew Brown 
	 */
	@Test
	public void testFetchEndWed2() {
		String result = dbc.fetchEndWed(BADCRSEID);
		assertNull("Fetch End Wednesday", result);
	}

	/** Test Case ID: PSM001_Login-UnitTest-I33
	 * Purpose: Test that the correct time is fetched
	 * Date Created: 09/18/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchStartThu() {
		String result = dbc.fetchStartThu(c1.getCrseid());
		assertEquals("Fetch Start Thursday", c1.getThuStart(), result);
	}
	
	/** Test Case ID: PSM001_Login-UnitTest-I34
	 * Purpose: Test fetching a time for a course that does not exist
	 * Date Created: 09/18/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchStartThu2() {
		String result = dbc.fetchStartThu(BADCRSEID);
		assertNull("Fetch Start Thursday", result);
	}

	/** Test Case ID: PSM001_Login-UnitTest-I35
	 * Purpose: Test that the correct time is fetched
	 * Date Created: 09/18/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchEndThu() {
		String result = dbc.fetchEndThu(c1.getCrseid());
		assertEquals("Fetch End Thursday", c1.getThuEnd(), result);
	}
	
	/** Test Case ID: PSM001_Login-UnitTest-I36
	 * Purpose: Test fetching a time for a course that does not exist
	 * Date Created: 09/18/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchEndThu2() {
		String result = dbc.fetchEndThu(BADCRSEID);
		assertNull("Fetch End Thursday", result);
	}

	/** Test Case ID: PSM001_Login-UnitTest-I37
	 * Purpose: Test that the correct time is fetched
	 * Date Created: 09/18/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchStartFri() {
		String result = dbc.fetchStartFri(c1.getCrseid());
		assertEquals("Fetch Start Friday", c1.getFriStart(), result);
	}
	
	/** Test Case ID: PSM001_Login-UnitTest-I38
	 * Purpose: Test fetching a time for a course that does not exist
	 * Date Created: 09/18/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchStartFri2() {
		String result = dbc.fetchStartFri(BADCRSEID);
		assertNull("Fetch Start Friday", result);
	}

	/** Test Case ID: PSM001_Login-UnitTest-I39
	 * Purpose: Test that the correct time is fetched
	 * Date Created: 09/18/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchEndFri() {
		String result = dbc.fetchEndFri(c1.getCrseid());
		assertEquals("Fetch End Friday", c1.getFriEnd(), result);
	}
	
	/** Test Case ID: PSM001_Login-UnitTest-I40
	 * Purpose: Test fetching a time for a course that does not exist
	 * Date Created: 09/18/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchEndFri2() {
		String result = dbc.fetchEndFri(BADCRSEID);
		assertNull("Fetch End Friday", result);
	}

	/** Test Case ID: PSM001_Login-UnitTest-I41
	 * Purpose: Test that the correct time is fetched
	 * Date Created: 09/18/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchStartSat() {
		String result = dbc.fetchStartSat(c1.getCrseid());
		assertEquals("Fetch Start Saturday", c1.getSatStart(), result);
	}
	
	/** Test Case ID: PSM001_Login-UnitTest-I42
	 * Purpose: Test fetching a time for a course that does not exist
	 * Date Created: 09/18/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchStartSat2() {
		String result = dbc.fetchStartSat(BADCRSEID);
		assertNull("Fetch Start Saturday", result);
	}

	/** Test Case ID: PSM001_Login-UnitTest-I43
	 * Purpose: Test that the correct time is fetched
	 * Date Created: 09/18/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchEndSat() {
		String result = dbc.fetchEndSat(c1.getCrseid());
		assertEquals("Fetch End Saturday", c1.getSatEnd(), result);
	}
	
	/** Test Case ID: PSM001_Login-UnitTest-I44
	 * Purpose: Test fetching a time for a course that does not exist
	 * Date Created: 09/18/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testFetchEndSat2() {
		String result = dbc.fetchEndSat(BADCRSEID);
		assertNull("Fetch End Saturday", result);
	}
}
