package testing.dataStoreUnitTest;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import DataStore.DBConnection;
import testUtil.Course;
import testUtil.DBUtil;

public class DBConnectionTest2 {

	private static DBConnection dbc;
	private static Course c1;
	private static Connection myCon;
	
	private static final String USERNAME = DBUtil.USERNAME;
	private static final String PASSWORD = DBUtil.PASSWORD;
	
	@BeforeClass
	public static void setUpOnce() throws Exception {
		dbc = new DBConnection();
		dbc.connect(USERNAME, PASSWORD);
		myCon = dbc.getMyCon();
		
		c1 = new Course(1234, "Subject", "Name", "Semester", Course.STARTDATE, Course.ENDDATE);
	}
	
	@Before
	public void setUp() throws Exception {
		dbc.createClassTable();
	}

	@AfterClass
	public static void tearDownOnce() throws Exception {
		dbc.disconnect();
		dbc = null;
	}
	
	/** Test Case ID: PSM001_Login-UnitTest-H01
	 * Purpose: Test the inserting of a course into the database with Class100 table
	 * Date Created: 09/11/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testStoreClassInfo1() {
		int cId = c1.getCrseid();
		String cSub = c1.getCrseSub();
		String cName = c1.getCrseNam();
		String cSem = c1.getSemester();
		int result = dbc.storeClassInfo(cId, cSub, cName, cSem);
		assertEquals("Store Class Info", 0, result);
		
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
	
	/** Test Case ID: PSM001_Login-UnitTest-H02
	 * Purpose: Test the inserting of a course into the database without a Class100 table
	 * Date Created: 09/11/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testStoreClassInfo2() {
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
	
	/** Test Case ID: PSM001_Login-UnitTest-H03
	 * Purpose: Test the inserting of a course into the database with a Class100 table containing a course with the same course_id
	 * Date Created: 09/11/12
	 * Author: Matthew Brown
	 * Stubs needed: 
	 */
	@Test
	public void testStoreClassInfo3() {
		dbc.storeClassInfo(c1.getCrseid(), c1.getCrseSub(), 
				c1.getCrseNam(), c1.getSemester());
		int result = dbc.storeClassInfo(c1.getCrseid(), c1.getCrseSub(), 
				c1.getCrseNam(), c1.getSemester());
		assertEquals("Store Duplicate Class Info", -1, result);
	}
	
	/** Test Case ID: PSM001_Login-UnitTest-H04
	 * Purpose: Test the inserting of a course into the database with a Class100 table and a string parameter of more than 20 characters in length
	 * Date Created: 09/11/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testStoreClassInfo4() {
		int cId = c1.getCrseid();
		String cSub = c1.getCrseSub();
		String cName = "Supercalifragilisticexpialidocious";
		String cSem = c1.getSemester();
		int result = dbc.storeClassInfo(cId, cSub, cName, cSem);
		assertEquals("Store Class Info", -1, result);
		
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
	
	/** Test Case ID: PSM001_Login-UnitTest-H05
	 * Purpose: Test the updating of a course�s time in the database
	 * Date Created: 09/11/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testStoreClassSched() {
		int cId = c1.getCrseid();
		dbc.storeClassInfo(cId, c1.getCrseSub(), c1.getCrseNam(), c1.getSemester());
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
	
	/** Test Case ID: PSM001_Login-UnitTest-H06
	 * Purpose: Test the updating of a course�s times for a course not in the database
	 * Date Created: 09/11/12
	 * Author: Matthew Brown
	 * Stubs needed: 
	 */
	@Test
	public void testStoreClassSched2() {
		int result = dbc.storeClassSched(c1.getCrseid(), c1.getStartdt(), c1.getEnddt(), c1.getMonStart(), c1.getMonEnd(),
				c1.getTueStart(), c1.getTueEnd(), c1.getWedStart(), c1.getWedEnd(), c1.getThuStart(), c1.getThuEnd(),
				c1.getFriStart(), c1.getFriEnd(), c1.getSatStart(), c1.getSatEnd());
		assertEquals("Store Class Schedule", -1, result);
	}
}
