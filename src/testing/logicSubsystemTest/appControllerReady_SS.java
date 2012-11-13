package testing.logicSubsystemTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import stubs.DBConnection;
import testUtil.Course;
import static org.junit.Assert.*;

import Logic.appController;

public class appControllerReady_SS {
	
	appController app1;
	DBConnection dbc;
	Course c1;
	
	@Before
	public void setUp() throws Exception {
		
		app1 = new appController();
		dbc = app1.getDb();
		dbc.connect("cen5076", "cen5076");
		c1 = new Course(1234,"Sub", "Nam", "Semester", Course.STARTDATE, Course.ENDDATE);
		c1.fillTimes(Course.defaultTimes);
	}

	@After
	public void tearDown() throws Exception {
		app1 = null;
		
	}
	
	@Test
	public void testReady() {
		dbc.addCourse(c1);
		app1.ready();
		//insert asserts
	}
}
