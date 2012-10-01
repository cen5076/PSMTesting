package testUtil;

public class TestDBUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DBUtil d = new DBUtil();
		
		Course c = new Course(1234,"Sub","Name","Semester","01/01/13","01/02/13");
		
		c.fillDates(DBUtil.defaultDates);
		
		System.out.println(d.insertCourse(c));
		

	}

}
