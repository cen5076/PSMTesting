package DataStore;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import testUtil.DBUtil;

public class DBConnectionTest {

	private DBConnection dbc;
	
	private final String USERNAME = DBUtil.USERNAME;
	private final String PASSWORD = DBUtil.PASSWORD;
	private final String DB = DBUtil.DB;
	private DBUtil dbutil = new DBUtil();
	
	
	@Before
	public void setUp() throws Exception {
		dbc = new DBConnection();
	}

	@After
	public void tearDown() throws Exception {
		dbc = null;
		dbutil.deleteAll();
	}
	
	@Test
	public void testConnect_success() {
		int result = dbc.connect(DB, USERNAME, PASSWORD);
		assertEquals("DBAddr", DB, dbc.getDbAddr());
		assertEquals("Username", USERNAME, dbc.getUsername());
		assertEquals("Password", PASSWORD, dbc.getPassword());
		assertNotNull("MyCon", dbc.getMyCon());
		assertEquals("Successful connect", 0, result);
	}

	@Test
	public void testConnect_fail() {
		assertEquals("unsuccessful_connect", -1, dbc.connect("", USERNAME, PASSWORD));
	}
	
	@Test
	public void testConnectDefaultDB_success() {
		int result =  dbc.connect(USERNAME, PASSWORD);
		assertEquals("Username", USERNAME, dbc.getUsername());
		assertEquals("Password", PASSWORD, dbc.getPassword());
		assertNotNull("MyCon", dbc.getMyCon());
		assertEquals("successful_connect_with_default_db", 0, result);
	}
	
	@Test
	public void testConnectDefaultDB_fail() {
		assertEquals("unsuccessful_connect_with_default_db", -1, dbc.connect(USERNAME, "c3n5076"));
		assertEquals("unsuccessful_connect_with_default_db", -1, dbc.connect("asdf", PASSWORD));
		assertEquals("unsuccessful_connect_with_default_db", -1, dbc.connect("asdf", "c3n5076"));
	}

	@Test
	public void testDisconnect() {
		dbc.connect(USERNAME, PASSWORD);
		int result =  dbc.disconnect();
		assertEquals("disconnect", 0, result);
	}
}
