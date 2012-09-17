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

import testUtil.Course;

@SuppressWarnings("unused")
public class DBUtil {
	
	public String userName;
	public String password;
	public String db;
	public Connection conn;
	public ArrayList<Course> crsList;
	public static final String[] defaultDates = {"010112"
		,"010212","020112","020212","030112","030212","040112","040212"
		,"050112","050212","060112","060212"};
	
	
	public DBUtil(){
		
		/* TODO instantiate connection */
		this.userName = "cen5076";
		this.password = "cen5076";
		this.db = "jdbc:mysql://dgarc012.homeip.net:3306/mydb";
		
	}
	
	public int connect(){
		
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
		catch(SQLException e){
			
		}
	}
	
	private String quote(String field){
		
		return "'" + field + "'";
	}
	
	private String quote(int field){
		
		return "'" + String.valueOf(field) + "'";
	}
	
	/* comma seperated and '' quoted fields */
	private String fieldsSQL(){
		
		StringBuilder flds = new StringBuilder();
		
		Course c = new Course();
		
		ArrayList<String> f = c.getFieldNames();
		
		Iterator<String> i = f.iterator();
		
		while( i.hasNext()){
			
			String n = i.next();
			//flds.append("'");
			flds.append(n);
			//flds.append("'");
			if(i.hasNext())
				flds.append(",");
			
		}
		
		return flds.toString();
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
	
	public int insertCourse( int crsid, String crssub, String crsnam,String semester,String start,String end){
		
		Course c = new Course(crsid,crssub,crsnam,semester,start,end);
		
		return this.insertCourse(c);
	}
	
	public int insertCourse(Course c){
		
		int result;
		this.connect();
		
		try{
			
			Statement sql = this.conn.createStatement();
			String text = this.insertSQL(c);
			System.out.println(text);
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
		
	public boolean addCourse(Course c){
		
		this.crsList.add(c);
		
		return true;
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
			System.out.println("Caught Exception Deleting- " + e.toString());
			return false;
		}
		System.out.println("Deleted All");
		this.close();
		return true;
	}
	
	public boolean compareArrayList(ArrayList<String> al1, ArrayList<String> al2){
		
		
		
		return true;
		
	}
	
	public ArrayList<Course> getCourseList(){
		
		return this.crsList;
	}
	
	public ArrayList<Integer> getCourseIds(){
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(Course c : this.crsList ){
			
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
		
	
	
		

}
