package testing.dataStoreUnitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DataStore.DBConnection;
import testUtil.Course;
import testUtil.DBUtil;

public class DBConnectionTest4 {
	
	private DBConnection dbc;
	private Connection myCon;
	private Course c1;
	
	private final String USERNAME = DBUtil.USERNAME;
	private final String PASSWORD = DBUtil.PASSWORD;
	private final String UNDERPRIV_USERNAME = "selectOnly";
	private final String UNDERPRIV_PASSWORD = "selectOnly";
	
	
	@Before
	public void setUp() throws Exception {
		dbc = new DBConnection();
		dbc.connect(USERNAME, PASSWORD);
		dbc.createClassTable();
		myCon = dbc.getMyCon();
		
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
	}
	
	@Test // PSM001_Login-UnitTest-G10
	public void testCreateClassTable3() {
		dbc.connect(UNDERPRIV_USERNAME, UNDERPRIV_PASSWORD);
        int result = dbc.createClassTable();
		assertEquals("Create New Class100 Table", -1, result);
		dbc.disconnect();
	}
	
	@Test // PSM001_Login-UnitTest-H07
	public void testStoreClassSched3() {
		try {
			Statement s = myCon.createStatement();
			s.executeUpdate("DROP TABLE IF EXISTS Class100");
			s.close();
		}
		catch(Exception e) {
        	e.printStackTrace();
        	fail("Exception thrown");
        }
		int result = dbc.storeClassSched(c1.getCrseid(), c1.getStartdt(), c1.getEnddt(), c1.getMonStart(), c1.getMonEnd(),
				c1.getTueStart(), c1.getTueEnd(), c1.getWedStart(), c1.getWedEnd(), c1.getThuStart(), c1.getThuEnd(),
				c1.getFriStart(), c1.getFriEnd(), c1.getSatStart(), c1.getSatEnd());
		assertEquals("Store Class Schedule", -1, result);
	}
	
	@Test // PSM001_Login-UnitTest-I07
	public void testGetEndDates3() {
		try {
			Statement s = myCon.createStatement();
			s.executeUpdate("DROP TABLE IF EXISTS Class100");
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception thrown");
		}
		
		ArrayList<String> endDates = dbc.getEndDates();
		
		assertTrue("End Date ArrayList", endDates.isEmpty());
	}
	
	@Test // PSM001_Login-UnitTest-I10
	public void testGetCourses3() {
		try {
			Statement s = myCon.createStatement();
			s.executeUpdate("DROP TABLE IF EXISTS Class100");
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception thrown");
		}
		
		ArrayList<Integer> actuals = dbc.getCourses();
		
		assertTrue("CourseID ArrayList", actuals.isEmpty());
	}
	
	@Test // PSM001_Login-UnitTest-I13
	public void testFetchCourses3() {
		try {
			Statement s = myCon.createStatement();
			s.executeUpdate("DROP TABLE IF EXISTS Class100");
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception thrown");
		}
		
		String courses = dbc.fetchCourses();
		
		assertEquals("Fetch Course IDs", "", courses);
	}
	
	@Test // PSM001_Login-UnitTest-I14
	public void testFetchCourses4() {
		
		dbc.fetchCourses();
		
		try {
			Statement s = myCon.createStatement();
			s.executeUpdate("DROP TABLE IF EXISTS Class100");
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception thrown");
		}
		
		String courses = dbc.fetchCourses();
		
		assertEquals("Fetch Course IDs", "", courses);
	}

}
