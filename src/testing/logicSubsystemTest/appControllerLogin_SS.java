package testing.logicSubsystemTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import Logic.appController;

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

	
	@Test
	/**
	 * Test ID: PSM_008-Message_PopUp-SubSystemTest-A01
	 * Purpose: To test the login functionality for a valid username and password
	 * Date Created: 09/18/12
	 * Author: Jose Borja
	 * Stubs Needed: 
	 */
	//valid login test
	public void testLogIn() {
		
		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		
		app1.LogIn();
		assertTrue("Log In",app1.isLoggedin());

	}
	
	@Test
	/**
	 * Test ID: PSM_008-Message_PopUp-SubSystemTest-A02
	 * Purpose: To test the login functionality for an  invalid username and valid password
	 * Date Created: 09/18/12
	 * Author: Jose Borja
	 * Stubs Needed: 
	 */
	//invalid login username test
	public void testLogIn_BadUser() {
		
		app1.setUsername("badUser");
		app1.setPassword(DBUtil.PASSWORD);

		app1.LogIn();
		assertFalse("Log In",app1.isLoggedin());
	}
	
	@Test
	/**
	 * Test ID: PSM_008-Message_PopUp-SubSystemTest-A03
	 * Purpose: To test the login functionality for a valid username and invalid password
	 * Date Created: 09/18/12
	 * Author: Jose Borja
	 * Stubs Needed: 
	 */
	//invalid login password test
	public void testLogIn_BadPass() {

		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword("badPassword");
		
		app1.LogIn();
		assertFalse("Log In",app1.isLoggedin());

	}
	
	@Test
	/**
	 * Test ID: PSM_008-Message_PopUp-SubSystemTest-A04
	 * Purpose: To test the login functionality for an invalid username and invalid 
	 * Date Created: 09/18/12
	 * Author: Jose Borja
	 * Stubs Needed: 
	 */
	//invalid login user and password test
	public void testLogIn_BadUser_Pass() {

		app1.setUsername("badUser");
		app1.setPassword("badPassword");
		
		app1.LogIn();
		assertFalse("Log In",app1.isLoggedin());

	}
	
	@Test
	public void testLoginState() {
		app1.loginState();
		// add assert(s)
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
		// add assert(s)
	}
	
	
	@Test
	public void testAuthenticate_Multi() {
		app1.setUsername("badUser");
		app1.setPassword("badPassword");
		
		app1.authenticate();
		app1.authenticate();
		exit.expectSystemExitWithStatus(0);
		app1.authenticate();
		// add assert(s)
	}
	
}
