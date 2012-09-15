package testUtil;

public class TestDBUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DBUtil d = new DBUtil();
		
		Course c = new Course(1234,"Sub","Name","Semes","090112","093012");
		
		System.out.println(d.insertCourse(c));
		

	}

}
