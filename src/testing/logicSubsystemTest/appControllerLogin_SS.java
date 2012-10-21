package testing.logicSubsystemTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Logic.appController;

import testUtil.DBUtil;


public class appControllerLogin_SS {

	private appController app1;
	
	@Before
	public void setUp() throws Exception {
	this.app1 = new appController();
	}

	@After
	public void tearDown() throws Exception {
	app1 = null;
	}	

	
	@Test //PSM001_Login-SubSystemTest-A01
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
	
	@Test //PSM001_Login-SubSystemTest-A02
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
	
	@Test //PSM001_Login-SubSystemTest-A03
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
	
	@Test //PSM001_Login-SubSystemTest-A04
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

}
