package Logic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
	//valid login test
	public void testLogIn() {
		
		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword(DBUtil.PASSWORD);
		
		app1.LogIn();
		assertTrue("Log In",app1.isLoggedin());

	}
	
	@Test //PSM001_Login-SubSystemTest-A02
	//invalid login username test
	public void testLogIn_BadUser() {
		
		app1.setUsername("badUser");
		app1.setPassword(DBUtil.PASSWORD);

		app1.LogIn();
		assertFalse("Log In",app1.isLoggedin());
	}
	
	@Test //PSM001_Login-SubSystemTest-A03
	//invalid login password test
	public void testLogIn_BadPass() {

		app1.setUsername(DBUtil.USERNAME);
		app1.setPassword("badPassword");
		
		app1.LogIn();
		assertFalse("Log In",app1.isLoggedin());

	}
	
	@Test //PSM001_Login-SubSystemTest-A04
	//invalid login user and password test
	public void testLogIn_BadUser_Pass() {

		app1.setUsername("badUser");
		app1.setPassword("badPassword");
		
		app1.LogIn();
		assertFalse("Log In",app1.isLoggedin());

	}

}
