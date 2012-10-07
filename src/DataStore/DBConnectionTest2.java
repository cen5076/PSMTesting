package DataStore;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import testUtil.Course;
import testUtil.DBUtil;

public class DBConnectionTest2 {

	private DBConnection dbc;
	private Course c1;
	
	private final String USERNAME = DBUtil.USERNAME;
	private final String PASSWORD = DBUtil.PASSWORD;
	
	
	@Before
	public void setUp() throws Exception {
		dbc = new DBConnection();
		dbc.connect(USERNAME, PASSWORD);
		dbc.createClassTable();
		
		// check start/end dates
		c1 = new Course(1234, "Subject", "Name", "Semester", "091012", "092012");
	}

	@After
	public void tearDown() throws Exception {
		dbc.disconnect();
		dbc = null;
	}
	
	// Move this ?
	// PSM_DBConnection-UnitTest-B01
	@Test
	public void testClearDatabase() {
		Connection myCon = dbc.getMyCon();
		dbc.clearDatabase();
		try {
			Statement s = myCon.createStatement();
			ResultSet res = s.executeQuery("SELECT * FROM Class100;");
	        assertEquals("Get no rows", false, res.next());
	        res.close();
	        s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// PSM_DBConnection-UnitTest-B02
	@Test
	public void testStoreClassInfo1() {
		int cId = c1.getCrseid();
		String cSub = c1.getCrseSub();
		String cName = c1.getCrseNam();
		String cSem = c1.getSemester();
		int result = dbc.storeClassInfo(cId, cSub, cName, cSem);
		assertEquals("Store Class Info", 0, result);
		
		Connection myCon = dbc.getMyCon();
		try {
			Statement s = myCon.createStatement();
            ResultSet res = s.executeQuery("SELECT * FROM Class100 WHERE course_id = " + cId +";");
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
	
	// PSM_DBConnection-UnitTest-B03
	@Test
	public void testStoreClassInfo2() {
		Connection myCon = dbc.getMyCon();
		try {
			Statement s = myCon.createStatement();
			s.executeUpdate("DROP TABLE IF EXISTS Class100");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int result = dbc.storeClassInfo(c1.getCrseid(), c1.getCrseSub(), 
				c1.getCrseNam(), c1.getSemester());
		assertEquals("Store Class Info with no table", -1, result);
	}
	
	// PSM_DBConnection-UnitTest-B04
	@Test
	public void testStoreClassInfo3() {
		dbc.storeClassInfo(c1.getCrseid(), c1.getCrseSub(), 
				c1.getCrseNam(), c1.getSemester());
		int result = dbc.storeClassInfo(c1.getCrseid(), c1.getCrseSub(), 
				c1.getCrseNam(), c1.getSemester());
		assertEquals("Store Duplicate Class Info", -1, result);
	}
	
	// PSM_DBConnection-UnitTest-B05
	@Test
	public void testStoreClassInfo4() {
		dbc.clearDatabase();
		int cId = c1.getCrseid();
		String cSub = c1.getCrseSub();
		String cName = "Supercalifragilisticexpialidocious";
		String cSem = c1.getSemester();
		int result = dbc.storeClassInfo(cId, cSub, cName, cSem);
		assertEquals("Store Class Info", -1, result);
		
		Connection myCon = dbc.getMyCon();
		try {
			Statement s = myCon.createStatement();
			ResultSet res = s.executeQuery("SELECT * FROM Class100 WHERE course_id = " + cId +";");
	        assertFalse("Get no rows", res.next());
	        res.close();
	        s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
