package DataStore;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
//import java.util.HashSet;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;
//import java.util.List;
//import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import testUtil.Course;
import testUtil.DBUtil;

public class DBConnectionTest {

	private DBConnection dbc;
	private DBUtil util = new DBUtil();
	private Course c1 = new Course(1234,"Subject","Name","Semester","091012","092012");
	private Course c2 = new Course(2345,"Subject","Name","Semester","101012","121012");
	//private final static String USERNAME = "cen5076";
	//private final static String PASSWORD = "cen5076";
	//private final static String DB = "jdbc:mysql://dgarc012.homeip.net:3306/mydb";
	boolean emtpyDB = util.deleteAll();
	int result = util.insertCourse(c1);	
	int result2 = util.insertCourse(c2);
	
	
	
	@Before
	public void setUp() throws Exception {
		dbc = new DBConnection();
	}

	@After
	public void tearDown() throws Exception {
		dbc = null;
	}

	@Test
	public void testConnect_success() {
		
		int result = dbc.connect(util.db,util.userName,util.password);
		assertEquals("DBAddr",util.db,dbc.getDbAddr());
		assertEquals("Username",util.userName,dbc.getUsername());
		assertEquals("Password",util.password,dbc.getPassword());
		assertNotNull("MyCon",dbc.getMyCon());
		assertEquals("successful_connect", 0, result);
	}

	@Test
	public void testConnect_fail() {
		assertEquals("unsuccessful_connect", -1, dbc.connect("", util.userName, util.password));
	}
	
	@Test
	public void testConnectDefaultDB_success() {
		
		int result =  dbc.connect(util.userName, util.password);
		assertEquals("Username",util.userName,dbc.getUsername());
		assertEquals("Password",util.password,dbc.getPassword());
		assertNotNull("MyCon",dbc.getMyCon());
		assertEquals("successful_connect_with_default_db", 0,result);
	}
	
	@Test
	public void testConnectDefaultDB_fail() {
		assertEquals("unsuccessful_connect_with_default_db", -1, dbc.connect(util.userName, "c3n5076"));
		assertEquals("unsuccessful_connect_with_default_db", -1, dbc.connect("asdf", "c3n5076"));
		assertEquals("unsuccessful_connect_with_default_db", -1, dbc.connect("asdfg", "c3n5076"));
	}

	@Test
	public void testDisconnect() {
		
		dbc.connect(util.userName, util.password);
		int result =  dbc.disconnect();
		/* TODO make sure correct */
		assertNull(dbc.getMyCon()); //maybe
		assertEquals("disconnect", 0,result);
	}
	
	@Test
	public void testFetchCourseID() {
		
		int result = dbc.fetchCourseID(c1.getCrseid());
		assertEquals("Course ID",c1.getCrseid(),result);
	}

	@Test
	public void testGetEndDates() {
		
		ArrayList<String> endDates = dbc.getEndDates();
		
		 
		ArrayList<String> allDates = c1.getEndDates();
		allDates.addAll(c2.getEndDates());
		
		Collections.sort(endDates, String.CASE_INSENSITIVE_ORDER);
		Collections.sort(allDates,String.CASE_INSENSITIVE_ORDER);
		assertEquals("EndDate ArrayList",endDates,allDates);
		
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
		
		assertEquals("CourseID ArrayList",expected,actuals);
		
	}

	@Test
	public void testFetchCourses() {

		String courses = dbc.fetchCourses();
		
		StringTokenizer p = new StringTokenizer(courses,",");
		SortedSet<String> orderedCourses = new TreeSet<String>();
		while(p.hasMoreElements()){
			
			orderedCourses.add(p.nextToken());
			
		}
		String ordered = new ArrayList<String>(orderedCourses).toString();
		
		
		ArrayList<Integer> expected = util.getCourseIds();
		Collections.sort(expected);
		
		assertEquals("Fetch CourseIds",expected.toString(),ordered);
		
		
	}

	@Test
	public void testFetchCourseSubj() {
		
		String result = dbc.fetchCourseSubj(c1.crseid);
		assertEquals("Fetch Subject",c1.getCrseSub(),result);
	}

	@Test
	public void testFetchCourseName() {
		
		String result = dbc.fetchCourseName(c1.getCrseid());
		assertEquals("Fetch Name",c1.getCrseNam(),result);
	}

	@Test
	public void testFetchCourseSemester() {
		String result = dbc.fetchCourseSemester(c1.getCrseid());
		assertEquals("Fetch Name",c1.getSemester(),result);
	}

	@Test
	public void testFetchCourseStart() {
		String result = dbc.fetchCourseStart(c1.getCrseid());
		assertEquals("Fetch Name",c1.getStartdt(),result);
	}

	@Test
	public void testFetchCourseEnd() {
		String result = dbc.fetchCourseEnd(c1.getCrseid());
		assertEquals("Fetch Name",c1.getEnddt(),result);
	}

	@Test
	public void testFetchStartMon() {
		String result = dbc.fetchStartMon(c1.getCrseid());
		assertEquals("Fetch Name",c1.getMonStart(),result);
	}

	@Test
	public void testFetchEndMon() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchStartTue() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchEndTue() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchStartWed() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchEndWed() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchStartThu() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchEndThu() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchStartFri() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchEndFri() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchStartSat() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchEndSat() {
		fail("Not yet implemented");
	}

	@Test
	public void testStoreClassInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testStoreClassSched() {
		fail("Not yet implemented");
	}

	@Test
	public void testClearDatabase() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateClassTable() {
		fail("Not yet implemented");
	}
	
	@Test
	public void FINALIZETESTING(){
		util.deleteAll();
	}
	

}
