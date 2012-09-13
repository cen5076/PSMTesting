package DataStore;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBConnectionTest {

	private DBConnection dbc;
	private final static String USERNAME = "cen5076";
	private final static String PASSWORD = "cen5076";
	private final static String DB = "jdbc:mysql://dgarc012.homeip.net:3306/mydb";
	
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
		assertEquals("successful_connect", 0, dbc.connect(DB, USERNAME, PASSWORD));
	}

	@Test
	public void testConnect_fail() {
		assertEquals("unsuccessful_connect", -1, dbc.connect("", USERNAME, PASSWORD));
	}
	
	@Test
	public void testConnectDefaultDB_success() {
		assertEquals("successful_connect_with_default_db", 0, dbc.connect(USERNAME, PASSWORD));
	}
	
	@Test
	public void testConnectDefaultDB_fail() {
		assertEquals("unsuccessful_connect_with_default_db", -1, dbc.connect(USERNAME, "c3n5076"));
		assertEquals("unsuccessful_connect_with_default_db", -1, dbc.connect("asdf", "c3n5076"));
		assertEquals("unsuccessful_connect_with_default_db", -1, dbc.connect("asdfg", "c3n5076"));
	}

	@Test
	public void testDisconnect() {
		dbc.connect(USERNAME, PASSWORD);
		assertEquals("disconnect", 0, dbc.disconnect());
	}

	@Test
	public void testDisconnect_fail() {
		assertEquals("disconnect", -1, dbc.disconnect());
	}
	
	@Test
	public void testFetchCourseID() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEndDates() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCourses() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchCourses() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchCourseSubj() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchCourseName() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchCourseSemester() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchCourseStart() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchCourseEnd() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchStartMon() {
		fail("Not yet implemented");
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

}
