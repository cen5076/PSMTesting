package DataStore;

import static org.junit.Assert.*;

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

import java.sql.Connection;

import testUtil.Course;
import testUtil.DBUtil;

public class DBConnectionTest2 {

	private DBUtil util  = new DBUtil();
	private DBConnection dbc;
	private Course c1;
	private Course c2;
	
	private final String USERNAME = util.getUserName();
	private final String PASSWORD = util.getPassword();
	
	
	@Before
	public void setUp() throws Exception {
		dbc = new DBConnection();
		c1 = new Course(1234,"Subject", "Name", "Semester", "091012", "092012");
		c2 = new Course(2345,"Subject", "Name", "Semester", "101012", "121012");
		
		util.deleteAll();
		util.insertCourse(c1);
		util.addCourse(c1);
		util.insertCourse(c2);
		util.addCourse(c2);
		
		dbc.connect(USERNAME, PASSWORD);
	}

	@After
	public void tearDown() throws Exception {
		dbc.disconnect();
		dbc = null;
		util.deleteAll();
	}
	
	@Test
	public void testFetchCourseID() {
		int result = dbc.fetchCourseID(c1.getCrseid());
		assertEquals("Course ID", c1.getCrseid(), result);
	}

	@Test
	public void testGetEndDates() {
		ArrayList<String> endDates = dbc.getEndDates();
		ArrayList<String> allDates = new ArrayList<String>();
		allDates.add("092012");
		allDates.add("121012");
//		ArrayList<String> allDates = c1.getEndDates();
//		allDates.addAll(c2.getEndDates());
		
		Collections.sort(endDates);
		Collections.sort(allDates);
//		System.out.println("endDates: " + endDates + "\nallDates: " + allDates);
		assertEquals("End Date ArrayList", allDates, endDates);
		
		/*Iterator<String> eIt = endDates.iterator();
		
		
		while(eIt.hasNext()){
			
			
			String endDate = eIt.next();
			Iterator<String> vIt = allDates.iterator();
			while(vIt.hasNext()){
					
				//String verifyDate = vIt.next();
				if(allDates.remove(endDate))
					continue;
				else
					fail("EndDate Missing");
									
			}
			
		}
		
		if(allDates.size()> 0)
			fail("Extra date found");
		
		assertTrue("All Dates Accounted for",true);
			*/	
	}

	@Test
	public void testGetCourses() {
		ArrayList<Integer> actuals = dbc.getCourses();
		ArrayList<Integer> expected = util.getCourseIds();
		Collections.sort(actuals);
		Collections.sort(expected);
		
		assertEquals("CourseID ArrayList", expected, actuals);
		
	}

	@Test
	public void testFetchCourses() {
		String courses = dbc.fetchCourses();
		
		StringTokenizer p = new StringTokenizer(courses, ",");
		SortedSet<String> orderedCourses = new TreeSet<String>();
		while(p.hasMoreElements()){
			orderedCourses.add(p.nextToken());
		}
		String ordered = new ArrayList<String>(orderedCourses).toString();
		
		ArrayList<Integer> expected = util.getCourseIds();
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

	@Test
	public void testStoreClassInfo() {
		util.deleteAll();
		int cId = c1.getCrseid();
		String cSub = c1.getCrseSub();
		String cName = c1.getCrseNam();
		String cSem = c1.getSemester();
		int result = dbc.storeClassInfo(cId, cSub, cName, cSem);
		assertEquals("Store Class Info", 0, result);
		
		Connection myCon = dbc.getMyCon();
		Statement s = null;
		ResultSet res;
		try {
            s = myCon.createStatement();
            s.executeQuery("SELECT * FROM Class100 WHERE course_id = " + cId +";");
            res = s.getResultSet();
            res.next();
            assertEquals("Store Class Info ID", res.getInt("course_id"), cId);
            assertEquals("Store Class Subject", res.getString("course_subject"), cSub);
            assertEquals("Store Class Name", res.getString("course_name"), cName);
            assertEquals("Store Class Semseter", res.getString("semester"), cSem);
            
            res.close();
            s.close();
        }
        catch(Exception e) {
        	e.printStackTrace();
        	fail("Exception thrown");
        }
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
            s.executeQuery("SELECT * FROM Class100 WHERE course_id = " + cId +";");
            ResultSet res = s.getResultSet();
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
	public void testClearDatabase() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateClassTable() {
		
		util.deleteAll();
		int result = dbc.createClassTable();
		assertEquals("Create Class Table", 0, result);
	}	
}
