package testing.dataStoreUnitTest;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DataStore.DBConnection;
import testUtil.DBUtil;

public class DBConnectionTest {

	private DBConnection dbc;
	private DBUtil dbu;
	
	private final String USERNAME = DBUtil.USERNAME;
	private final String PASSWORD = DBUtil.PASSWORD;
	private final String UNDERPRIV_USERNAME = "selectOnly";
	private final String UNDERPRIV_PASSWORD = "selectOnly";
	private final String DB = DBUtil.DB;
	
	
	@Before
	public void setUp() throws Exception {
		dbc = new DBConnection();
		dbu = new DBUtil();
	}

	@After
	public void tearDown() throws Exception {
		dbc = null;
		dbu.deleteAll();
	}
	
	
	@Test // PSM001_Login-UnitTest-G01
	public void testConnectDefaultDB_success() {
		int result =  dbc.connect(USERNAME, PASSWORD);
		assertEquals("Username", USERNAME, dbc.getUsername());
		assertEquals("Password", PASSWORD, dbc.getPassword());
		Connection myCon = dbc.getMyCon();
		assertNotNull("MyCon", myCon);
		try {
			assertFalse("Open connection", myCon.isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals("Successful connect with default Db", 0, result);
	}
	
	@Test // PSM001_Login-UnitTest-G02
	public void testConnectDefaultDB_fail() {
		int result =  dbc.connect(USERNAME, "CEN5076");
		assertEquals("Username", USERNAME, dbc.getUsername());
		assertEquals("Password", "CEN5076", dbc.getPassword());
		assertNull("MyCon", dbc.getMyCon());
		assertEquals("Unsuccessful connect with default Db", -1, result);
	}
	
	@Test // PSM001_Login-UnitTest-G03
	public void testConnect_success() {
		int result = dbc.connect(DB, USERNAME, PASSWORD);
		assertEquals("DB Address", DB, dbc.getDbAddr());
		assertEquals("Username", USERNAME, dbc.getUsername());
		assertEquals("Password", PASSWORD, dbc.getPassword());
		Connection myCon = dbc.getMyCon();
		assertNotNull("MyCon", myCon);
		try {
			assertFalse("Open connection", myCon.isClosed());
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals("Successful connect", 0, result);
	}
	
	@Test // PSM001_Login-UnitTest-G04
	public void testConnect_fail() {
		int result = dbc.connect("", USERNAME, PASSWORD);
		assertEquals("DB Address", "", dbc.getDbAddr());
		assertEquals("Username", USERNAME, dbc.getUsername());
		assertEquals("Password", PASSWORD, dbc.getPassword());
		assertNull("MyCon", dbc.getMyCon());
		assertEquals("Unsuccessful connect", -1, result);
	}

	@Test // PSM001_Login-UnitTest-G05
	public void testDisconnect() {
		dbc.connect(USERNAME, PASSWORD);
		int result =  dbc.disconnect();
		try {
			assertTrue("Closed connection", dbc.getMyCon().isClosed());
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals("Disconnect", 0, result);
	}
	
	@Test // PSM001_Login-UnitTest-G06
	public void testDisconnect2() {
		dbc.connect(USERNAME, PASSWORD);
		dbc.disconnect();
		int result =  dbc.disconnect();
		try {
			assertTrue("Closed connection", dbc.getMyCon().isClosed());
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals("Double disconnect", 0, result);
	}
	
	@Test // PSM001_Login-UnitTest-G07
	public void testDisconnect3() {
		int result =  dbc.disconnect();
		assertNull("Null connection", dbc.getMyCon());
		assertEquals("Null Disconnect", 0, result);
	}
	
	@Test // PSM001_Login-UnitTest-G08
	public void testCreateClassTable1() {
		dbc.connect(USERNAME, PASSWORD);
		try {
			Connection myCon = dbc.getMyCon();
            Statement s = myCon.createStatement();
            s.executeUpdate("DROP TABLE IF EXISTS Class100");
            int result = dbc.createClassTable();
    		assertEquals("Create New Class100 Table", 0, result);
        }
        catch(Exception e)
        {
            fail("Exception thrown");
        }
		finally {
			dbc.disconnect();
		}
	}
	
	@Test // PSM001_Login-UnitTest-G09
	public void testCreateClassTable2() {
		dbc.connect(USERNAME, PASSWORD);
		try {
			Connection myCon = dbc.getMyCon();
            Statement s = myCon.createStatement();
            s.executeUpdate("DROP TABLE IF EXISTS Class100");
            dbc.createClassTable();
            s.executeUpdate("INSERT INTO Class100 (course_id, course_subject, course_name, semester)" +
                    " VALUES ( '"+ 1234 +"', '" + "Subject" +"', '" + "Name" +"', '" + "Semester" +"')");
            int result = dbc.createClassTable();
    		assertEquals("Create Class100 Table Twice", 0, result);
    		
    		ResultSet res = s.executeQuery("SELECT * FROM Class100;");
	        assertFalse("Get no rows", res.next());
    		
        }
        catch(Exception e)
        {
            fail("Exception thrown");
        }
		finally {
			dbc.disconnect();
		}
	}
	
	@Test // PSM001_Login-UnitTest-G10
	public void testCreateClassTable3() {
		dbc.connect(UNDERPRIV_USERNAME, UNDERPRIV_PASSWORD);
        int result = dbc.createClassTable();
		assertEquals("Create New Class100 Table", -1, result);
		dbc.disconnect();
	}
}
