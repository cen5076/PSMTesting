package testing.logicSubsystemTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import Logic.appController;

import stubs.DBConnection;
import stubs.Messages;
import testUtil.DBUtil;


public class appControllerLogin_SS {

	private appController app1;
	
	@Rule
	public final ExpectedSystemExit exit = ExpectedSystemExit.none();
	
	@Before
	public void setUp() throws Exception {
		this.app1 = new appController();
	}

	@After
	public void tearDown() throws Exception {
		app1 = null;
	}

	/**
	 * Test ID: PSM_008-Message_PopUp-SubSystemTest-A01
	 * Purpose: To test the login functionality for a valid username and password
	 * Date Created: 09/18/12
	 * Author: Jose Borja
	 * Stubs Needed: 
	 */
	@Test
	public void testLogIn() {
		
		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		
		app1.LogIn();
		assertTrue("Log In", app1.isLoggedin());

	}
	
	/**
	 * Test ID: PSM_008-Message_PopUp-SubSystemTest-A02
	 * Purpose: To test the login functionality for an  invalid username and valid password
	 * Date Created: 09/18/12
	 * Author: Jose Borja
	 * Stubs Needed: 
	 */
	@Test
	public void testLogIn_BadUser() {
		
		app1.setUsername("badUser");
		app1.setPassword(DBUtil.PASSWORD);

		app1.LogIn();
		assertFalse("Log In", app1.isLoggedin());
	}
	
	/**
	 * Test ID: PSM_008-Message_PopUp-SubSystemTest-A03
	 * Purpose: To test the login functionality for a valid username and invalid password
	 * Date Created: 09/18/12
	 * Author: Jose Borja
	 * Stubs Needed: 
	 */
	@Test
	public void testLogIn_BadPass() {

		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword("badPassword");
		
		app1.LogIn();
		assertFalse("Log In", app1.isLoggedin());

	}
	
	/**
	 * Test ID: PSM_008-Message_PopUp-SubSystemTest-A04
	 * Purpose: To test the login functionality for an invalid username and invalid 
	 * Date Created: 09/18/12
	 * Author: Jose Borja
	 * Stubs Needed: 
	 */
	@Test
	public void testLogIn_BadUser_Pass() {

		app1.setUsername("badUser");
		app1.setPassword("badPassword");
		
		app1.LogIn();
		assertFalse("Log In",app1.isLoggedin());

	}
	
	// tests that the DBConnection object created for this instance of appController is the one that is fetched
	// and properly updated given valid authentication
	@Test
	public void testConnection() {
		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		app1.LogIn();
		
		DBConnection dbc = app1.getCon();
		assertEquals("Gets username", DBUtil.USERNAME, dbc.getUsername());
		assertEquals("Gets password", DBUtil.PASSWORD, dbc.getPassword());
		assertTrue(dbc.isConnected());
	}
	
	// tests that the DBConnection object created for this instance of appController is the one that is fetched
	// and properly updated given invalid authentication
	@Test
	public void testConnection2() {
		app1.setUsername("badUser");
		app1.setPassword("badPassword");
		app1.LogIn();
		
		DBConnection dbc = app1.getCon();
		assertEquals("Gets username", "badUser", dbc.getUsername());
		assertEquals("Gets password", "badPassword", dbc.getPassword());
		assertFalse(dbc.isConnected());
	}
	
	@Test
	public void testLoginState() {
		app1.setLoggedIn(false);
		app1.getIc().log.setLoginStateFlag(true);
		app1.loginState();
		assertEquals("Gets username", DBUtil.USERNAME, app1.getUsername());
		assertEquals("Gets password", DBUtil.PASSWORD, app1.getPassword());
		assertFalse(app1.isDataReceived());
	}
	
	/*
	@Test
	public void testLoginState2() {
		app1.loginState();
	} */
	
	@Test
	public void testAuthenticate() {
		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		
		app1.authenticate();
		assertTrue(app1.getAuth().validate_Login());
		assertTrue(app1.isLoggedin());
		assertTrue(app1.getDb().isConnected());
	}
	
	
	@Test
	public void testAuthenticate_Multi() {
		app1.setUsername("badUser");
		app1.setPassword("badPassword");
		
		app1.authenticate();
		assertFalse(app1.getAuth().validate_Login());
		app1.authenticate();
		assertFalse(app1.getAuth().validate_Login());
		exit.expectSystemExitWithStatus(0);
		app1.authenticate();

		assertFalse(app1.getAuth().validate_Login());
		assertTrue(Messages.isLockedOut());
		assertFalse(app1.isDataReceived());
	}
	
	@Test
	public void testLogOut() {
		app1.logOut();
		
		assertTrue(Messages.getLogOut());
	}
}
