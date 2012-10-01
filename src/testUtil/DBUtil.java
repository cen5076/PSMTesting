package testUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import stubs.DBConnection;
import testUtil.Course;

@SuppressWarnings("unused")
public class DBUtil {
	
	private String userName;
	private String password;
	private String db;
	
	public static final String USERNAME = "cen5076";
	public static final String PASSWORD = "cen5076";
	public static final String DB = "jdbc:mysql://dgarc012.homeip.net:3306/mydb";
	public static Connection conn;
	public static ArrayList<Course> crsList = new ArrayList<Course>();
	public static final String[] defaultDates = {"01/01/12",
		"010212","020112","020212","030112","030212","040112","040212",
		"050112","050212","060112","060212"};
	
	public static int connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = java.sql.DriverManager.getConnection(DB, USERNAME, PASSWORD);
		}
		catch(Exception e){
			System.out.println(e.toString());
			return -1;
		}
		return 0;
		
	}
	
	public static void close() {
		try {
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static String quote(String field) {
		
		return "'" + field + "'";
	}
	
	private static String quote(int field){
		
		return "'" + field + "'";
	}
	
	/* comma separated and '' quoted fields */
	private static String fieldsSQL() {
		
		StringBuilder fields = new StringBuilder();
		
		Course c = new Course();
		
		ArrayList<String> fieldNames = c.getFieldNames();
		
		Iterator<String> iter = fieldNames.iterator();
		
		for (String field : fieldNames) {
			fields.append(field);
			fields.append(",");
		}
		
		return fields.substring(0, fields.length() - 1);
	}
	
	private static String valuesSQL(Course c){

		
		StringBuilder vals = new StringBuilder();
		
		Iterator<String> cvals = c.getValues().iterator();
		
		//first is an int
		if(cvals.hasNext())
			vals.append(cvals.next()+",");
		
		while(cvals.hasNext()){
			
			vals.append("'");
			vals.append(cvals.next() + "'");
			if(cvals.hasNext())
				vals.append(",");
		}
		
		return vals.toString();
	}
	
	public static int insertCourse(int crsid, String crssub, String crsnam,String semester,String start,String end){
		
		Course c = new Course(crsid,crssub,crsnam,semester,start,end);
		
		return insertCourse(c);
	}
	
	public static int insertCourse(Course c) {
		
		int result;
		connect();
		
		try{
			
			Statement sql = conn.createStatement();
			String text = insertSQL(c);
//			System.out.println(text);
			result = sql.executeUpdate(text);
		}
		catch(SQLException e){
			System.out.println(e.toString());
			return -1;
		}
		
		close();
		
		return result;
	}
	
	private static String insertSQL(Course c){ // (int crsid, String crssub, String crsnam,String semester,String start,String end){
		
		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO class100 ("); //course_id,course_subject,course_name,semester,start_date,end_date) values (";
		
		sql.append(fieldsSQL());
		sql.append(") VALUES (");
		sql.append(valuesSQL(c));
		sql.append(")");
		
		return sql.toString();
		
	}
	
	/*private String insertSQL(int crsid, String crssub, String crsnam,String semester,String start,String end,String mons,String mone,String tues,String tuee,String weds,String wede,String thus,String thue,String fris,String frie,String sats,String sate){
			
		String insert = "INSERT INTO class100 (course_id,course_subject,course_name,semester,start_date,end_date,start_mon,end_mon,start_tue,end_tue,) values (";

		StringBuilder sql = new StringBuilder();
	} */
		
	public static boolean addCourse(Course c) {
		
		return crsList.add(c);
	}
	
	public static int insertCourses(){
		
		int count = 0;
		int i =0;
		
		Iterator<Course> list = crsList.listIterator();

		while(list.hasNext()){
			
			if( insertCourse(list.next()) == 1)
				i++;
			else
				return -1;
			
			
		}
		
		return count;
	}
	
	/* remove all from table */
	public static boolean deleteAll() {
		
		connect();
		
		try{
			Statement stat = conn.createStatement();
			
			stat.executeUpdate("DELETE FROM class100");
		}
		catch(SQLException e){
			System.err.println("Caught Exception Deleting- " + e.toString());
			return false;
		}
		close();
		return true;
	}
	
	public static ArrayList<Course> getCourseList(){
		
		return crsList;
	}
	
	public static ArrayList<Integer> getCourseIds(){
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for (Course c : crsList ){
			list.add(new Integer(c.crseid));
		}
		
		return list;
	}
	
	public static ArrayList<String> getCourseSubjects(){
		
		ArrayList<String> list = new ArrayList<String>();
		
		for(Course c : crsList){
			list.add(c.getCrseSub());
		}
		
		return list;
	}
	
	public static long get15Milli() {
		return 15 * 60000;
	}

	public static long get5Milli() {
		return 5 * 60000;
	}	

	public static long secInMilli(int seconds) {	
		return seconds * 1000; 
	}

	public static long minInMilli(int minutes) {
		return secInMilli(minutes)*60;
	}

	public static long hrsInMilli(int hours) {
		return minInMilli(hours)*60;
	}
	
	public void initializeStub(DataStore.DBConnection dbConnection,ArrayList<Course> arrlist){
	
		
		
	
	}
	}
