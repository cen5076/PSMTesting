package testUtil;

// TODO Make static as with most utility classes

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
	public Connection conn;
	public ArrayList<Course> crsList;
	public static final String[] defaultDates = {"02/01/13","02/02/13","03/01/13","03/02/13","04/01/13","04/02/13"
		,"05/01/13","05/02/13","06/01/13","06/02/13","07/01/13","07/02/13"};
	
	public static final String USERNAME = "cen5076";
	public static final String PASSWORD = "cen5076";
	public static final String DB = "jdbc:mysql://dgarc012.homeip.net:3306/mydb";
	
	
	
	public DBUtil(){
		
		/* TODO instantiate connection */
		this.userName = "cen5076";
		this.password = "cen5076";
		this.db = "jdbc:mysql://dgarc012.homeip.net:3306/mydb";
		crsList = new ArrayList<Course>();
		
	}
	
	public String getUserName() {
		return userName;
		
	}
	
	public String getPassword() {
		return password;
		
	}
	
	public String getDB() {
		return db;
		
	}
	
	public int connect() {
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = java.sql.DriverManager.getConnection(this.db,this.userName,this.password);
		
		}
		catch(Exception e){
			System.out.println(e.toString());
			return -1;
		}
		
		return 0;
		
	}
	
	public void close(){
		try{
			this.conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private String quote(String field){
		
		return "'" + field + "'";
	}
	
	private String quote(int field){
		
		return "'" + String.valueOf(field) + "'";
	}
	
	/* comma separated and '' quoted fields */
	private String fieldsSQL() {
		
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
	
	private String valuesSQL(Course c){

		
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
	
	public int insertCourse(int crsid, String crssub, String crsnam,String semester,String start,String end){
		
		Course c = new Course(crsid,crssub,crsnam,semester,start,end);
		
		return this.insertCourse(c);
	}
	
	public int insertCourse(Course c) {
		
		int result;
		this.connect();
		
		try{
			
			Statement sql = this.conn.createStatement();
			String text = this.insertSQL(c);
//			System.out.println(text);
			result = sql.executeUpdate(text);
		}
		catch(SQLException e){
			System.out.println(e.toString());
			return -1;
		}
		
		this.close();
		
		return result;
	}
	
	private String insertSQL(Course c){ // (int crsid, String crssub, String crsnam,String semester,String start,String end){
		
		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO class100 ("); //course_id,course_subject,course_name,semester,start_date,end_date) values (";
		
		sql.append(this.fieldsSQL());
		sql.append(") VALUES (");
		sql.append(this.valuesSQL(c));
		sql.append(")");
		
		return sql.toString();
		
	}
	
	/*private String insertSQL(int crsid, String crssub, String crsnam,String semester,String start,String end,String mons,String mone,String tues,String tuee,String weds,String wede,String thus,String thue,String fris,String frie,String sats,String sate){
			
		String insert = "INSERT INTO class100 (course_id,course_subject,course_name,semester,start_date,end_date,start_mon,end_mon,start_tue,end_tue,) values (";

		StringBuilder sql = new StringBuilder();
	} */
		
	public boolean addCourse(Course c) {
		
		return this.crsList.add(c);
	}
	
	public int insertCourses(){
		
		int count = 0;
		int i =0;
		
		Iterator<Course> list = this.crsList.listIterator();

		while(list.hasNext()){
			
			if( this.insertCourse(list.next()) == 1)
				i++;
			else
				return -1;
			
			
		}
		
		return count;
	}
	
	/* remove all from table */
	public boolean deleteAll(){
		
		this.connect();
		
		try{
			Statement stat = this.conn.createStatement();
			
			stat.executeUpdate("DELETE FROM class100");
		}
		catch(SQLException e){
			System.err.println("Caught Exception Deleting- " + e.toString());
			return false;
		}
		this.close();
		return true;
	}
	
	public ArrayList<Course> getCourseList(){
		
		return this.crsList;
	}
	
	public ArrayList<Integer> getCourseIds(){
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for (Course c : this.crsList ){
			list.add(new Integer(c.crseid));
		}
		
		return list;
	}
	
	public ArrayList<String> getCourseSubjects(){
		
		ArrayList<String> list = new ArrayList<String>();
		
		for(Course c : this.crsList){
			list.add(c.getCrseSub());
		}
		
		return list;
	}
	
	public long get15Milli(){
		
		return 15 * 60000;
	}
	
	public long get5Milli(){
		
		return 5 * 60000;
	}
	
	public long secInMilli(int seconds){
				
		return seconds * 1000; 
		
	}
	
	public long minInMilli(int minutes){
		
		return this.secInMilli(minutes)*60;
	}
	
	public long hrsInMilli(int hours){
		
		return this.minInMilli(hours)*60;
	}
	
	public void initializeStub(DataStore.DBConnection dbConnection,ArrayList<Course> arrlist){
	
		
		
	
	}
	}
