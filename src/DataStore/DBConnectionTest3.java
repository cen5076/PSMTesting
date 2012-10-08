package DataStore;

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
import org.junit.Before;
import org.junit.Test;

import testUtil.Course;
import testUtil.DBUtil;

public class DBConnectionTest3 {

	private DBConnection dbc;
	private DBUtil dbu;
	private Course c1;
	
	private final String USERNAME = DBUtil.USERNAME;
	private final String PASSWORD = DBUtil.PASSWORD;
	private final int BADCRSEID = 9999;
	
	
	@Before
	public void setUp() throws Exception {
		dbc = new DBConnection();
		dbc.connect(USERNAME, PASSWORD);
		dbc.createClassTable();
		
		dbu = new DBUtil();
		c1 = new Course(1234, "Subject", "Name", "Semester", Course.STARTDATE, Course.ENDDATE);
		dbc.storeClassInfo(c1.getCrseid(), c1.getCrseSub(), c1.getCrseNam(), c1.getSemester());
		dbc.storeClassSched(c1.getCrseid(), c1.getStartdt(), c1.getEnddt(), c1.getMonStart(), c1.getMonEnd(),
				c1.getTueStart(), c1.getTueEnd(), c1.getWedStart(), c1.getWedEnd(), c1.getThuStart(), c1.getThuEnd(),
				c1.getFriStart(), c1.getFriEnd(), c1.getSatStart(), c1.getSatEnd());
	}

	@After
	public void tearDown() throws Exception {
		dbc.disconnect();
		dbc = null;
		dbu = null;
	}

	
	@Test // PSM001_Login-UnitTest-I01
	public void testClearDatabase() {
		Connection myCon = dbc.getMyCon();
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
	
	@Test // PSM001_Login-UnitTest-I02
	public void testClearDatabase2() {
		Connection myCon = dbc.getMyCon();
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
	
	@Test // PSM001_Login-UnitTest-I03
	public void testFetchCourseID() {
		int result = dbc.fetchCourseID(c1.getCrseid());
		
		assertEquals("Course ID", c1.getCrseid(), result);
	}
	
	@Test // PSM001_Login-UnitTest-I04
	public void testFetchCourseID2() {
		int result = dbc.fetchCourseID(BADCRSEID);
		assertEquals("Course ID", -1, result);
	}

	@Test // PSM001_Login-UnitTest-I05
	public void testGetEndDates() {
		Course c2 = new Course(2345, "Subject2", "Name2", "Semester", "101012", "121012");
		dbu.insertCourse(c2);
		
		ArrayList<String> endDates = dbc.getEndDates();
		ArrayList<String> allDates = new ArrayList<String>();
		allDates.add(c1.getEnddt());
		allDates.add(c2.getEnddt());
		
		Collections.sort(endDates);
		Collections.sort(allDates);
		
		assertEquals("End Date ArrayList", allDates, endDates);
	}
	
	@Test // PSM001_Login-UnitTest-I06
	public void testGetEndDates2() {
		dbc.clearDatabase();
		
		ArrayList<String> endDates = dbc.getEndDates();
		
		assertTrue("End Date ArrayList", endDates.isEmpty());
	}

	@Test // PSM001_Login-UnitTest-I07
	public void testGetCourses() {
		Course c2 = new Course(2345, "Subject2", "Name2", "Semester", "101012", "121012");
		dbc.storeClassInfo(c2.getCrseid(), c2.getCrseSub(), c2.getCrseNam(), c2.getSemester());
		dbu.addCourse(c1);
		dbu.addCourse(c2);
		
		ArrayList<Integer> actuals = dbc.getCourses();
		ArrayList<Integer> expected = dbu.getCourseIds();
		Collections.sort(actuals);
		Collections.sort(expected);
		
		assertEquals("CourseID ArrayList", expected, actuals);
	}
	
	@Test // PSM001_Login-UnitTest-I08
	public void testGetCourses2() {
		dbc.clearDatabase();
		
		ArrayList<Integer> actuals = dbc.getCourses();
		
		assertTrue("CourseID ArrayList", actuals.isEmpty());
	}

	@Test // PSM001_Login-UnitTest-I09
	public void testFetchCourses() {
		Course c2 = new Course(2345, "Subject2", "Name2", "Semester", "101012", "121012");
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
	
	@Test // PSM001_Login-UnitTest-I10
	public void testFetchCourses2() {
		dbc.clearDatabase();
		
		String courses = dbc.fetchCourses();
		
		assertEquals("Fetch Course IDs", "", courses);
	}

	@Test // PSM001_Login-UnitTest-I11
	public void testFetchCourseSubj() {
		String result = dbc.fetchCourseSubj(c1.crseid);
		assertEquals("Fetch Subject", c1.getCrseSub(), result);
	}
	
	@Test // PSM001_Login-UnitTest-I12
	public void testFetchCourseSubj2() {
		String result = dbc.fetchCourseSubj(BADCRSEID);
		assertNull("Fetch Subject", result);
	}

	@Test // PSM001_Login-UnitTest-I13
	public void testFetchCourseName() {
		String result = dbc.fetchCourseName(c1.getCrseid());
		assertEquals("Fetch Name", c1.getCrseNam(), result);
	}
	
	@Test // PSM001_Login-UnitTest-I14
	public void testFetchCourseName2() {
		String result = dbc.fetchCourseName(BADCRSEID);
		assertNull("Fetch Name", result);
	}

	@Test // PSM001_Login-UnitTest-I15
	public void testFetchCourseSemester() {
		String result = dbc.fetchCourseSemester(c1.getCrseid());
		assertEquals("Fetch Semester", c1.getSemester(), result);
	}

	@Test // PSM001_Login-UnitTest-I16
	public void testFetchCourseSemester2() {
		String result = dbc.fetchCourseSemester(BADCRSEID);
		assertNull("Fetch Semester", result);
	}
	
	@Test // PSM001_Login-UnitTest-I17
	public void testFetchCourseStart() {
		String result = dbc.fetchCourseStart(c1.getCrseid());
		assertEquals("Fetch Course Start", c1.getStartdt(), result);
	}
	
	@Test // PSM001_Login-UnitTest-I18
	public void testFetchCourseStart2() {
		String result = dbc.fetchCourseStart(BADCRSEID);
		assertNull("Fetch Course Start", result);
	}

	@Test // PSM001_Login-UnitTest-I19
	public void testFetchCourseEnd() {
		String result = dbc.fetchCourseEnd(c1.getCrseid());
		assertEquals("Fetch Course End", c1.getEnddt(), result);
	}
	
	@Test // PSM001_Login-UnitTest-I20
	public void testFetchCourseEnd2() {
		String result = dbc.fetchCourseEnd(BADCRSEID);
		assertNull("Fetch Course End", result);
	}

	@Test // PSM001_Login-UnitTest-I21
	public void testFetchStartMon() {
		String result = dbc.fetchStartMon(c1.getCrseid());
		assertEquals("Fetch Start Monday", c1.getMonStart(), result);
	}
	
	@Test // PSM001_Login-UnitTest-I22
	public void testFetchStartMon2() {
		String result = dbc.fetchStartMon(BADCRSEID);
		assertNull("Fetch Start Monday", result);
	}

	@Test // PSM001_Login-UnitTest-I23
	public void testFetchEndMon() {
		String result = dbc.fetchEndMon(c1.getCrseid());
		assertEquals("Fetch End Monday", c1.getMonEnd(), result);
	}
	
	@Test // PSM001_Login-UnitTest-I24
	public void testFetchEndMon2() {
		String result = dbc.fetchEndMon(BADCRSEID);
		assertNull("Fetch End Monday", result);
	}

	@Test // PSM001_Login-UnitTest-I25
	public void testFetchStartTue() {
		String result = dbc.fetchStartTue(c1.getCrseid());
		assertEquals("Fetch Start Tuesday", c1.getTueStart(), result);
	}
	
	@Test // PSM001_Login-UnitTest-I26
	public void testFetchStartTue2() {
		String result = dbc.fetchStartTue(BADCRSEID);
		assertNull("Fetch Start Tuesday", result);
	}

	@Test // PSM001_Login-UnitTest-I27
	public void testFetchEndTue() {
		String result = dbc.fetchEndTue(c1.getCrseid());
		assertEquals("Fetch End Tuesday", c1.getTueEnd(), result);
	}
	
	@Test // PSM001_Login-UnitTest-I28
	public void testFetchEndTue2() {
		String result = dbc.fetchEndTue(BADCRSEID);
		assertNull("Fetch End Tuesday", result);
	}

	@Test // PSM001_Login-UnitTest-I29
	public void testFetchStartWed() {
		String result = dbc.fetchStartWed(c1.getCrseid());
		assertEquals("Fetch Start Wednesday", c1.getWedStart(), result);
	}
	
	@Test // PSM001_Login-UnitTest-I30
	public void testFetchStartWed2() {
		String result = dbc.fetchStartWed(BADCRSEID);
		assertNull("Fetch Start Wednesday", result);
	}

	@Test // PSM001_Login-UnitTest-I31
	public void testFetchEndWed() {
		String result = dbc.fetchEndWed(c1.getCrseid());
		assertEquals("Fetch End Wednesday", c1.getWedEnd(), result);
	}
	
	@Test // PSM001_Login-UnitTest-I32
	public void testFetchEndWed2() {
		String result = dbc.fetchEndWed(BADCRSEID);
		assertNull("Fetch End Wednesday", result);
	}

	@Test // PSM001_Login-UnitTest-I33
	public void testFetchStartThu() {
		String result = dbc.fetchStartThu(c1.getCrseid());
		assertEquals("Fetch Start Thursday", c1.getThuStart(), result);
	}
	
	@Test // PSM001_Login-UnitTest-I34
	public void testFetchStartThu2() {
		String result = dbc.fetchStartThu(BADCRSEID);
		assertNull("Fetch Start Thursday", result);
	}

	@Test // PSM001_Login-UnitTest-I35
	public void testFetchEndThu() {
		String result = dbc.fetchEndThu(c1.getCrseid());
		assertEquals("Fetch End Thursday", c1.getThuEnd(), result);
	}
	
	@Test // PSM001_Login-UnitTest-I36
	public void testFetchEndThu2() {
		String result = dbc.fetchEndThu(BADCRSEID);
		assertNull("Fetch End Thursday", result);
	}

	@Test // PSM001_Login-UnitTest-I37
	public void testFetchStartFri() {
		String result = dbc.fetchStartFri(c1.getCrseid());
		assertEquals("Fetch Start Friday", c1.getFriStart(), result);
	}
	
	@Test // PSM001_Login-UnitTest-I38
	public void testFetchStartFri2() {
		String result = dbc.fetchStartFri(BADCRSEID);
		assertNull("Fetch Start Friday", result);
	}

	@Test // PSM001_Login-UnitTest-I39
	public void testFetchEndFri() {
		String result = dbc.fetchEndFri(c1.getCrseid());
		assertEquals("Fetch End Friday", c1.getFriEnd(), result);
	}
	
	@Test // PSM001_Login-UnitTest-I40
	public void testFetchEndFri2() {
		String result = dbc.fetchEndFri(BADCRSEID);
		assertNull("Fetch End Friday", result);
	}

	@Test // PSM001_Login-UnitTest-I41
	public void testFetchStartSat() {
		String result = dbc.fetchStartSat(c1.getCrseid());
		assertEquals("Fetch Start Saturday", c1.getSatStart(), result);
	}
	
	@Test // PSM001_Login-UnitTest-I42
	public void testFetchStartSat2() {
		String result = dbc.fetchStartSat(BADCRSEID);
		assertNull("Fetch Start Saturday", result);
	}

	@Test // PSM001_Login-UnitTest-I43
	public void testFetchEndSat() {
		String result = dbc.fetchEndSat(c1.getCrseid());
		assertEquals("Fetch End Saturday", c1.getSatEnd(), result);
	}
	
	@Test // PSM001_Login-UnitTest-I44
	public void testFetchEndSat2() {
		String result = dbc.fetchEndSat(BADCRSEID);
		assertNull("Fetch End Saturday", result);
	}
}
