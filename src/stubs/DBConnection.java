package stubs;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;

import testUtil.Course;
import testUtil.DBUtil;

@SuppressWarnings("unused")
/**
 * Stub class for DBConnection, uses an ArrayList and a HashMap of courses to
 * simulate database actions and transactions. disconnect stores values in private
 * fields and resumes them to accessible fields on connect. Takes Course objects
 * as parameters.
 * 
 * @see testUtil.Course;
 * 
 * @author David
 *
 */
public class DBConnection {

	private boolean connected;
	private String username;
	private String password;
	private String dbAddr;
    //CEN5076
    //private String localDb = "jdbc:mysql://10.105.40.92:3306/mydb";

    //private String marcosDb = "jdbc:mysql://131.94.178.165:3306/mydb";
    private String localDb = "jdbc:mysql://dgarc012.homeip.net:3306/mydb";
    private String result;
    private String [] courses;
//    private ArrayList<Course> courseList;
    private HashMap<Integer, Course> courseMap;
    
    //two sets that are inaccessible to use for disconnect without nulling out
//    private ArrayList<Course> lockedList;
//    private HashMap<Integer,Course> lockedSet;
    
    /** Creates a new instance of DBConnection */
    public DBConnection() {
//    	courseList = new ArrayList<Course>();
    	courseMap = new HashMap<Integer, Course>();
    	connected = false;
    	/*
    	this.courseList = arrlist;
    	System.out.println("list size: " + arrlist.size());
    	
    	//set map for searching
    	for (Course c : arrlist) {
    		this.courseSet.put(new Integer(c.getCrseid()), c);
    	}
    	*/
    }
    
    
    /**
	 * @return the myCon
	 */
//	public Connection getMyCon() {
//		return myCon;
//	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the dbAddr
	 */
	public String getDbAddr() {
		return dbAddr;
	}

	/**
	 * @return the localDb
	 */
	public String getLocalDb() {
		return localDb;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}
	
	public boolean isConnected() {
		return connected;
	}

	/**
	 * @return the courseList
	 */
//	public ArrayList<Course> getCourseList() {
//		return courseList;
//	}

	/**
	 * @return the courseMap
	 */
	public HashMap<Integer, Course> getCourseSet() {
		return courseMap;
	}

	/*
	public void initializeStub(ArrayList<Course> arrlist) {
		this.courseList = arrlist;
    	System.out.println("list size: " + arrlist.size());
    	
    	//set map for searching
    	for (Course c : arrlist) {
    		this.courseSet.put(new Integer(c.getCrseid()), c);
    	}
    }
    */
	
	// shortcut method that calls internal methods
	public void addCourse(Course c) {
		storeClassInfo(c.getCrseid(), c.getCrseSub(), c.getCrseNam(), c.getSemester());
		storeClassSched(c.getCrseid(), c.getStartdt(), c.getEnddt(), c.getMonStart(), c.getMonEnd(),
				c.getTueStart(), c.getTueEnd(), c.getWedStart(), c.getWedEnd(), c.getThuStart(), c.getThuEnd(),
				c.getFriStart(), c.getFriEnd(), c.getSatStart(), c.getSatEnd());
	}
    
	/*
	 * Connect using known database address
	 * 
	 * @param String db - database
	 * @param String user - username
	 * @param String pw - password
	 */
    public int connect(String db, String user, String pw) {
    	dbAddr = db;
        username = user;
        password = pw;
        
    	if (db != DBUtil.DB || user != DBUtil.USERNAME || pw != DBUtil.PASSWORD) {
    		this.connected = false;
    		return -1;
    	}
        
        //reconnect the sets
    	// 	this.courseList = lockedList;
    	// 	this.courseSet = lockedSet;
      	this.connected = true;
        return 0;
    }
    
    /*
     * Connect using default host as database address
     * 
     * @param String user - username
     * @param String pw - password
     * @returns - 0 if successful
     * 
     */
    public int connect(String user, String pw) {
    	return connect(DBUtil.DB, user, pw);
    }
    
    //disconnect from database
    public int disconnect()
    {
        this.connected = false;
//    	this.courseList = this.lockedList;
//    	this.courseSet = this.lockedSet;
        
        return 0;
    }
     
    /*
     * checks if an id is in the set
     * 
     * @param int courseID - the id to search for
     * @return id if exists and -1 otherwise*
     * @see DBCOnnectionTest.fetchCourseID(-1);
     */
    public int fetchCourseID(int courseID)
    {
    	if (!connected || !courseMap.containsKey(courseID)) {
    		return -1;
    	}
    	Course c = this.courseMap.get(courseID);
    	return c.getCrseid();
    }
    
    //returns a list of end dates for all the courses in set
    public ArrayList<String> getEndDates() {
        ArrayList<String> endDates = new ArrayList<String>();

        for (Course c : courseMap.values()) {
        	endDates.add(c.getEnddt());
        }
        
        return endDates;
    }
    
    public ArrayList<Integer> getCourses()  {
        return new ArrayList<Integer>(courseMap.keySet());
    }
    
    // stub uses course set
    public String fetchCourses() {
    	if (!connected) {
    		return null;
    	}
    	StringBuilder result = new StringBuilder();
    	
    	for (Integer cid : courseMap.keySet()) {
    		result.append(cid);
    		result.append(",");
    	}
    	
        return result.toString();
        //String[] courses = result.split(",");
       //return courses;
    }
    
    // stub uses course set
    public String fetchCourseSubj(int courseID)
    {
    	if (!connected || !courseMap.containsKey(courseID)) {
    		return null;
    	}
    	Course c = this.courseMap.get(courseID);
    	return c.getCrseSub();
       
    }
    
    // stub uses course set
    public String fetchCourseName(int courseID)
    {
    	if (!connected || !courseMap.containsKey(courseID)) {
    		return null;
    	}
    	Course c = this.courseMap.get(courseID);
   		return c.getCrseNam();
    }
    
    // stub uses course set
    public String fetchCourseSemester(int courseID)
    {
    	if (!connected || !courseMap.containsKey(courseID)) {
    		return null;
    	}
    	Course c = this.courseMap.get(courseID);
   		return c.getSemester();
       
    }
    
    // stub uses course set
    public String fetchCourseStart(int courseID)
    {
    	if (!connected || !courseMap.containsKey(courseID)) {
    		return null;
    	}
    	Course c = this.courseMap.get(courseID);
   		return c.getStartdt();
       
    }
    
    // stub uses course set
    public String fetchCourseEnd(int courseID)
    {
    	if (!connected || !courseMap.containsKey(courseID)) {
    		return null;
    	}
    	
    	Course c = this.courseMap.get(courseID);
    	return c.getEnddt();
    }
    
    
    // stub uses course set
    public String fetchStartMon(int courseID)
    {
    	if (!connected || !courseMap.containsKey(courseID)) {
    		return null;
    	}
    	Course c = this.courseMap.get(courseID);
   		return c.getMonStart();
    }
    
    // stub uses course set
    public String fetchEndMon(int courseID)
    {
    	if (!connected || !courseMap.containsKey(courseID)) {
    		return null;
    	}
    	Course c = this.courseMap.get(new Integer(courseID));
    	return c.getMonEnd();
    }
   
    // stub uses course set
    public String fetchStartTue(int courseID)
    {
    	if (!connected || !courseMap.containsKey(courseID)) {
    		return null;
    	}
    	Course c = this.courseMap.get(new Integer(courseID));
    	return c.getTueStart();
    }
    
    // stub uses course set
    public String fetchEndTue(int courseID)
    {
    	if (!connected || !courseMap.containsKey(courseID)) {
    		return null;
    	}
    	Course c = this.courseMap.get(new Integer(courseID));
   		return c.getTueEnd();
    }
    
    // stub uses course set
    public String fetchStartWed(int courseID)
    {
    	if (!connected || !courseMap.containsKey(courseID)) {
    		return null;
    	}
    	Course c = this.courseMap.get(new Integer(courseID));
    	return c.getWedStart();
    }
    
    // stub uses course set
    public String fetchEndWed(int courseID)
    {
    	if (!connected || !courseMap.containsKey(courseID)) {
    		return null;
    	}
    	Course c = this.courseMap.get(new Integer(courseID));
    	return c.getWedEnd();
    }
    
    // stub uses course set
    public String fetchStartThu(int courseID)
    {
    	if (!connected || !courseMap.containsKey(courseID)) {
    		return null;
    	}
        Course c = this.courseMap.get(new Integer(courseID));
        return c.getThuStart();
    }
        // stub uses course set
    public String fetchEndThu(int courseID)
    {
    	if (!connected || !courseMap.containsKey(courseID)) {
    		return null;
    	}
    	Course c = this.courseMap.get(new Integer(courseID));
    	return c.getThuEnd();
    }
    
    // stub uses course set
    public String fetchStartFri(int courseID)
    {
    	if (!connected || !courseMap.containsKey(courseID)) {
    		return null;
    	}
        Course c = this.courseMap.get(new Integer(courseID));
        return c.getFriStart();
    }
    
    // stub uses course set
    public String fetchEndFri(int courseID)
    {
    	if (!connected || !courseMap.containsKey(courseID)) {
    		return null;
    	}
    	Course c = this.courseMap.get(new Integer(courseID));
    	return c.getFriEnd();
    }
    

    // stub uses course set
    public String fetchStartSat(int courseID)
    {
    	if (!connected || !courseMap.containsKey(courseID)) {
    		return null;
    	}
        Course c = this.courseMap.get(new Integer(courseID));
        return c.getSatStart();
    }
    
    // stub uses course set
    public String fetchEndSat(int courseID)
    {
    	if (!connected || !courseMap.containsKey(courseID)) {
    		return null;
    	}
    	Course c = this.courseMap.get(new Integer(courseID));
    	return c.getSatEnd();
    }
    
    //stub to insert into the set a course with default start and end date
    public int storeClassInfo(int courseID, String courseSubj, String courseName, String semester)
    {
        if (!connected || courseMap.containsKey(courseID)) {
        	return -1;
        }
    	Course c = new Course(courseID, courseSubj, courseName, semester, Course.STARTDATE, Course.ENDDATE);
    	courseMap.put(courseID, c);
    	
    	return 0;
    }
    
    //stub to add schedule data to a course in set
    public int storeClassSched(int courseID, String startDate, String endDate, String startMon, String endMon,
            String startTue, String endTue, String startWed, String endWed, String startThu, String endThu, 
            String startFri, String endFri, String startSat, String endSat)
    {
    	if (!connected || !courseMap.containsKey(courseID)) {
    		return -1;
    	}
    	
    	Course c = this.courseMap.get(courseID);

    	c.setStartdt(startDate);
    	c.setEnddt(endDate);
    	c.setMonStart(startMon);
    	c.setMonEnd(endMon);
    	c.setTueStart(startTue);
    	c.setTueEnd(endTue);
    	c.setWedStart(startWed);
    	c.setWedEnd(endWed);
    	c.setThuStart(startThu);
    	c.setThuEnd(endThu);
    	c.setFriStart(startFri);
    	c.setFriEnd(endFri);
    	c.setSatStart(startSat);
    	c.setSatEnd(endSat);
    	
    	return 0;
        
    }
    
    //clears course list and course set
    public void clearDatabase() {
//    	this.courseList.clear();
    	this.courseMap.clear();
    }
    
    // Stub returns 0
    public int createClassTable() {
    	clearDatabase();
    	return 0;                
    }
}
