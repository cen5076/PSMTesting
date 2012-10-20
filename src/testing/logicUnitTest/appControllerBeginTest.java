package testing.logicUnitTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Logic.appController;
import testUtil.DBUtil;
import testUtil.Course;

/* 
 * This class tests the Begin method which has system functionality and is called within main.
 * 
 */
@SuppressWarnings("unused")
public class appControllerBeginTest {
	
	appController app1;

	@Before
	/*
	 * Create a new appController, call begin
	 * 
	 */
	public void setUp() throws Exception {
		
		app1 = new appController();
		app1.begin();
		
	}

	@After
	public void tearDown() throws Exception {
		
		app1 = null;
	}
	

	/* Test States of Begin method */
	@Test
	//TODO test cases
	public void testBegin() {
		fail("Not yet implemented");
	}

}
