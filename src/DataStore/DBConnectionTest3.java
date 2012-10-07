package DataStore;

import static org.junit.Assert.assertEquals;
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
	
	
	@Before
	public void setUp() throws Exception {
		dbc = new DBConnection();
		dbc.connect(USERNAME, PASSWORD);
		dbc.createClassTable();
		
		// Check start/end dates
		dbu = new DBUtil();
		c1 = new Course(1234, "Subject", "Name", "Semester", "091012", "092012");
		dbu.insertCourse(c1);
	}

	@After
	public void tearDown() throws Exception {
		dbc.disconnect();
		dbc = null;
		dbu = null;
	}
	
	@Test
	public void testStoreClassSched() {
		int cId = c1.getCrseid();
		String stDt = c1.getStartdt();
		String enDt = c1.getEnddt();
		String stMo = c1.getMonStart();
		String enMo = c1.getMonEnd();
		String stTu = c1.getTueStart();
		String enTu = c1.getTueEnd();
		String stWe = c1.getWedStart();
		String enWe = c1.getWedEnd();
		String stTh = c1.getThuStart();
		String enTh = c1.getThuEnd();
		String stFr = c1.getFriStart();
		String enFr = c1.getFriEnd();
		String stSa = c1.getSatStart();
		String enSa = c1.getSatEnd();
		int result = dbc.storeClassSched(cId, stDt, enDt, stMo, enMo, stTu, enTu, stWe, enWe,
				stTh, enTh, stFr, enFr, stSa, enSa);
		assertEquals("Store Class Schedule", 0, result);
		
		Connection myCon = dbc.getMyCon();
		try {
            Statement s = myCon.createStatement();
            ResultSet res = s.executeQuery("SELECT * FROM Class100 WHERE course_id = " + cId +";");
            res.next();
            assertEquals("Store Class Schedule ID", res.getInt("course_id"), cId);
            assertEquals("Store Class Start Date", res.getString("start_date"), stDt);
            assertEquals("Store Class End Date", res.getString("end_date"), enDt);
            assertEquals("Store Class Start Monday", res.getString("start_mon"), stMo);
            assertEquals("Store Class End Monday", res.getString("end_mon"), enMo);
            assertEquals("Store Class Start Tuesday", res.getString("start_tue"), stTu);
            assertEquals("Store Class End Tuesday", res.getString("end_tue"), enTu);
            assertEquals("Store Class Start Wednesday", res.getString("start_wed"), stWe);
            assertEquals("Store Class End Wednesday", res.getString("end_wed"), enWe);
            assertEquals("Store Class Start Thursday", res.getString("start_thu"), stTh);
            assertEquals("Store Class End Thursday", res.getString("end_thu"), enTh);
            assertEquals("Store Class Start Friday", res.getString("start_fri"), stFr);
            assertEquals("Store Class End Friday", res.getString("end_fri"), enFr);
            assertEquals("Store Class End Saturday", res.getString("start_sat"), stSa);
            assertEquals("Store Class End Saturday", res.getString("end_sat"), enSa);
            s.close();
        }
        catch(Exception e) {
        	e.printStackTrace();
        	fail("Exception thrown");
        }
	}
	
	@Test
	public void testFetchCourseID() {
		int result = dbc.fetchCourseID(c1.getCrseid());
		assertEquals("Course ID", c1.getCrseid(), result);
	}

	@Test
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

	@Test
	public void testGetCourses() {
		Course c2 = new Course(2345, "Subject2", "Name2", "Semester", "101012", "121012");
		dbc.storeClassInfo(c2.getCrseid(), c2.getCrseSub(), c2.getCrseNam(), c2.getSemester());
//		dbu.clearCourseList();
		dbu.addCourse(c1);
		dbu.addCourse(c2);
		
		ArrayList<Integer> actuals = dbc.getCourses();
		ArrayList<Integer> expected = dbu.getCourseIds();
		Collections.sort(actuals);
		Collections.sort(expected);
		
		assertEquals("CourseID ArrayList", expected, actuals);
	}

	@Test
	public void testFetchCourses() {
		Course c2 = new Course(2345, "Subject2", "Name2", "Semester", "101012", "121012");
		dbc.storeClassInfo(c2.getCrseid(), c2.getCrseSub(), c2.getCrseNam(), c2.getSemester());
//		dbu.clearCourseList();
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

	@Test
	public void testFetchCourseSubj() {
		String result = dbc.fetchCourseSubj(c1.crseid);
		assertEquals("Fetch Subject", c1.getCrseSub(), result);
	}

	@Test
	public void testFetchCourseName() {
		String result = dbc.fetchCourseName(c1.getCrseid());
		assertEquals("Fetch Name", c1.getCrseNam(), result);
	}

	@Test
	public void testFetchCourseSemester() {
		String result = dbc.fetchCourseSemester(c1.getCrseid());
		assertEquals("Fetch Semester", c1.getSemester(), result);
	}

	@Test
	public void testFetchCourseStart() {
		String result = dbc.fetchCourseStart(c1.getCrseid());
		assertEquals("Fetch Course Start", c1.getStartdt(), result);
	}

	@Test
	public void testFetchCourseEnd() {
		String result = dbc.fetchCourseEnd(c1.getCrseid());
		assertEquals("Fetch Course End", c1.getEnddt(), result);
	}

	@Test
	public void testFetchStartMon() {
		String result = dbc.fetchStartMon(c1.getCrseid());
		assertEquals("Fetch Start Monday", c1.getMonStart(), result);
	}

	@Test
	public void testFetchEndMon() {
		String result = dbc.fetchEndMon(c1.getCrseid());
		assertEquals("Fetch End Monday", c1.getMonEnd(), result);
	}

	@Test
	public void testFetchStartTue() {
		String result = dbc.fetchStartTue(c1.getCrseid());
		assertEquals("Fetch Start Tuesday", c1.getTueStart(), result);
	}

	@Test
	public void testFetchEndTue() {
		String result = dbc.fetchEndTue(c1.getCrseid());
		assertEquals("Fetch End Tuesday", c1.getTueEnd(), result);
	}

	@Test
	public void testFetchStartWed() {
		String result = dbc.fetchStartWed(c1.getCrseid());
		assertEquals("Fetch Start Wednesday", c1.getWedStart(), result);
	}

	@Test
	public void testFetchEndWed() {
		String result = dbc.fetchEndWed(c1.getCrseid());
		assertEquals("Fetch End Wednesday", c1.getWedEnd(), result);
	}

	@Test
	public void testFetchStartThu() {
		String result = dbc.fetchStartThu(c1.getCrseid());
		assertEquals("Fetch Start Thursday", c1.getThuStart(), result);
	}

	@Test
	public void testFetchEndThu() {
		String result = dbc.fetchEndThu(c1.getCrseid());
		assertEquals("Fetch End Thursday", c1.getThuEnd(), result);
	}

	@Test
	public void testFetchStartFri() {
		String result = dbc.fetchStartFri(c1.getCrseid());
		assertEquals("Fetch Start Friday", c1.getFriStart(), result);
	}

	@Test
	public void testFetchEndFri() {
		String result = dbc.fetchEndFri(c1.getCrseid());
		assertEquals("Fetch End Friday", c1.getFriEnd(), result);
	}

	@Test
	public void testFetchStartSat() {
		String result = dbc.fetchStartSat(c1.getCrseid());
		assertEquals("Fetch Start Saturday", c1.getSatStart(), result);
	}

	@Test
	public void testFetchEndSat() {
		String result = dbc.fetchEndSat(c1.getCrseid());
		assertEquals("Fetch End Saturday", c1.getSatEnd(), result);
	}
}
