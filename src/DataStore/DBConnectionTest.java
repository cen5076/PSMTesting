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

public class DBConnectionTest {

	private DBUtil util  = new DBUtil();
	private DBConnection dbc;
	private Course c1;
	private Course c2;
	
	private final String USERNAME = util.getUserName();
	private final String PASSWORD = util.getPassword();
	private final String DB = util.getDB();
	
	
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
	}

	@After
	public void tearDown() throws Exception {
		dbc = null;
		util.deleteAll();
	}

	@Test
	public void testConnect_success() {
		
		int result = dbc.connect(DB,USERNAME,PASSWORD);
		assertEquals("DBAddr",DB,dbc.getDbAddr());
		assertEquals("Username",USERNAME,dbc.getUsername());
		assertEquals("Password",PASSWORD,dbc.getPassword());
		assertNotNull("MyCon",dbc.getMyCon());
		assertEquals("successful_connect", 0, result);
	}

	@Test
	public void testConnect_fail() {
		assertEquals("unsuccessful_connect", -1, dbc.connect("", USERNAME, PASSWORD));
	}
	
	@Test
	public void testConnectDefaultDB_success() {
		
		int result =  dbc.connect(USERNAME, PASSWORD);
		assertEquals("Username",USERNAME,dbc.getUsername());
		assertEquals("Password",PASSWORD,dbc.getPassword());
		assertNotNull("MyCon",dbc.getMyCon());
		assertEquals("successful_connect_with_default_db", 0,result);
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
		int result =  dbc.disconnect();
		assertEquals("disconnect", 0, result);
	}
	
	
}
