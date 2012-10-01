package stubs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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

@SuppressWarnings("unused")
/**
 * Stub class for DBConnection, uses an arraylist and a hashmap of courses to
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

	public Connection myCon;
    public String username;
    public String password;
    public String dbAddr;
    //CEN5076
    //private String localDb = "jdbc:mysql://10.105.40.92:3306/mydb";

    //private String marcosDb = "jdbc:mysql://131.94.178.165:3306/mydb";
    private String localDb = "jdbc:mysql://dgarc012.homeip.net:3306/mydb";
    private String result;
    private String [] courses;
    public ArrayList<Course> courseList = new ArrayList<Course>();
    public HashMap<Integer,Course> courseSet = new HashMap<Integer,Course>();
    
    //two sets that are inaccessible to use for disconnect without nulling out
    private ArrayList<Course> lockedList;
    private HashMap<Integer,Course> lockedSet;
    
    /** Creates a new instance of DBConnection */
    public DBConnection()
    {
      
    }
    
    

    /**
	 * @return the myCon
	 */
	public Connection getMyCon() {
		
		return myCon;
		
	}

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

	/**
	 * @return the courseList
	 */
	public ArrayList<Course> getCourseList() {
		return courseList;
	}

	/**
	 * @return the courseSet
	 */
	public HashMap<Integer, Course> getCourseSet() {
		return courseSet;
	}

	public void initializeStub(ArrayList<Course> arrlist){
		
		this.courseList = arrlist;
    	System.out.println("list size:"+arrlist.size());
    	Iterator<Course> it = arrlist.iterator();
    	
    	//set map for searching
    	while(it.hasNext()){
    		Course c = it.next();
    		this.courseSet.put(new Integer(c.crseid), c);
    	}
    	
    }
    
	/*
	 * Connect using known database address
	 * 
	 * @param String db - database
	 * @param String user - username
	 * @param String pw - password
	 */
    public int connect(String db, String user, String pw)
    {
    
        dbAddr = db;
        username = user;
        password = pw;
        
        //reconnect the sets
      	this.courseList = lockedList;
      	this.courseSet = lockedSet;
       
        
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
    public int connect(String user,String pw)
    {
        username = user;
        password = pw;
        
        //reconnect the sets
      	this.courseList = lockedList;
      	this.courseSet = lockedSet;
        
        return 0;
    }
    
    //disconnect from database
    public int disconnect()
    {
        this.myCon = null;
    	this.courseList = this.lockedList;
    	this.courseSet = this.lockedSet;
        
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
    	
    	Course c = this.courseSet.get(new Integer(courseID));
    	
    	if (c==null)
    			return -1;
    	else
    		return c.getCrseid();
    	
    	
    }
    
    //returns a list of end dates for all the courses in set
    public ArrayList<String> getEndDates()
    {
        ArrayList<String> endDates = new ArrayList<String>();
        
        
        
        Iterator<Course> it = this.courseList.iterator();
        
        while(it.hasNext()){
        	
        	Course c = it.next();
        	endDates.add(c.getEnddt());
        }
        
        return endDates;
    }
    
    // stub uses course set
    public ArrayList<Integer> getCourses()
    {
        ArrayList<Integer> courseList = new ArrayList<Integer>();
       
        
        Iterator<Course> it = this.courseList.iterator();
        
        while(it.hasNext()){
        	
        	Course c = it.next();
        	courseList.add(new Integer(c.getCrseid()));
        }
        
        return courseList;
    
    }
    
    // stub uses course set
    public String fetchCourses()
    {
      
    	StringBuilder result= new StringBuilder();
    	
    	Collections.sort(this.courseList, 
    			new Comparator<Course>()
    			{
    		
    				public int compare(Course c1,Course c2){ 
    					return (c1.getCrseid()-c2.getCrseid());
    					}
    			});
    	
    	Iterator<Course> it = this.courseList.iterator();

    	while (it.hasNext()){
    		
    		Course c = it.next();
    		result.append(c.crseid);
    		if(it.hasNext())
    			result.append(",");
    		
    	}
    	
        return result.toString();
        //String[] courses = result.split(",");
       //return courses;
    }
    
    // stub uses course set
    public String fetchCourseSubj(int courseID)
    {
       
    	Course c = this.courseSet.get(new Integer(courseID));
    	
    	if (c==null)
    		return null;
    	else
    		return c.getCrseSub();
       
    }
    
    // stub uses course set
    public String fetchCourseName(int courseID)
    {
       
    	Course c = this.courseSet.get(new Integer(courseID));
    	
    	if (c == null)
    		return null;
    	else
    		return c.getCrseNam();
    }
    
    // stub uses course set
    public String fetchCourseSemester(int courseID)
    {
    	
    	Course c = this.courseSet.get(courseID);
    	
    	if(c == null)
    		return null;
    	else
    		return c.getSemester();
       
    }
    
    // stub uses course set
    public String fetchCourseStart(int courseID)
    {
    	
    	Course c = this.courseSet.get(new Integer(courseID));
    	
    	if (c == null)
    		return null;
    	else
    		return c.getStartdt();
       
    }
    
    // stub uses course set
    public String fetchCourseEnd(int courseID)
    {
        
    	
    	Course c = this.courseSet.get(new Integer(courseID));
    	
    	if (c == null)
    		return null;
    	else
    		return c.getEnddt();
    }
    
    
    // stub uses course set
    public String fetchStartMon(int courseID)
    {
     
    	Course c = this.courseSet.get(new Integer(courseID));
    	
    	if (c == null)
    		return null;
    	else
    		return c.getMonStart();
    }
    
    // stub uses course set
    public String fetchEndMon(int courseID)
    {
Course c = this.courseSet.get(new Integer(courseID));
    	
    	if (c == null)
    		return null;
    	else
    		return c.getMonEnd();
    }
   
    // stub uses course set
    public String fetchStartTue(int courseID)
    {
    	Course c = this.courseSet.get(new Integer(courseID));
    	
    	if (c == null)
    		return null;
    	else
    		return c.getTueStart();
    }
    
    // stub uses course set
    public String fetchEndTue(int courseID)
    {
      
Course c = this.courseSet.get(new Integer(courseID));
    	
    	if (c == null)
    		return null;
    	else
    		return c.getTueEnd();
    }
    
    // stub uses course set
    public String fetchStartWed(int courseID)
    {
       
Course c = this.courseSet.get(new Integer(courseID));
    	
    	if (c == null)
    		return null;
    	else
    		return c.getWedStart();
    }
    
    // stub uses course set
    public String fetchEndWed(int courseID)
    {
       Course c = this.courseSet.get(new Integer(courseID));
    	
    	if (c == null)
    		return null;
    	else
    		return c.getWedEnd();
    }
    
    // stub uses course set
        public String fetchStartThu(int courseID)
    {
        
        	Course c = this.courseSet.get(new Integer(courseID));
        	
        	if (c == null)
        		return null;
        	else
        		return c.getThuStart();
    }
        // stub uses course set
    public String fetchEndThu(int courseID)
    {
        
    	
Course c = this.courseSet.get(new Integer(courseID));
    	
    	if (c == null)
    		return null;
    	else
    		return c.getThuEnd();
    }
    // stub uses course set
        public String fetchStartFri(int courseID)
    {

        	Course c = this.courseSet.get(new Integer(courseID));
        	
        	if (c == null)
        		return null;
        	else
        		return c.getFriStart();
    }
    
        // stub uses course set
    public String fetchEndFri(int courseID)
    {
      
Course c = this.courseSet.get(new Integer(courseID));
    	
    	if (c == null)
    		return null;
    	else
    		return c.getFriEnd();
    }
    

    // stub uses course set
        public String fetchStartSat(int courseID)
    {
        /*try{
            Statement s = myCon.createStatement();
            ResultSet res = s.executeQuery("SELECT start_sat FROM Class100 WHERE course_id = " +courseID +";");
            
            res.next();
            return res.getString("start_sat");
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        } */
        	Course c = this.courseSet.get(new Integer(courseID));
        	
        	if (c == null)
        		return null;
        	else
        		return c.getSatStart();
    }
    
    // stub uses course set
    public String fetchEndSat(int courseID)
    {
        
       Course c = this.courseSet.get(new Integer(courseID));
    	
    	if (c == null)
    		return null;
    	else
    		return c.getSatEnd();
    }
    
    //stub to insert into the set a course with default start and end date
    public int storeClassInfo(int courseID, String courseSubj, String courseName, String semester)
    {
       
    	Course c = new Course(courseID,courseSubj,courseName,semester,Course.STARTDATE,Course.ENDDATE);
    	
    	this.courseList.add(c);
    	return 0;
    }
    
    //stub to add schedule data to a course in set
    public int storeClassSched(int courseID, String startDate, String endDate, String startMon, String endMon,
            String startTue, String endTue, String startWed, String endWed, String startThu, String endThu, 
            String startFri, String endFri, String startSat, String endSat)
    {
    	Course c = this.courseSet.get(courseID);
    	
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
    public void clearDatabase()
    {
       
    	
    	this.courseList.clear();
    	this.courseSet.clear();
    }
    
    // Stub returns 0
    public int createClassTable()
    {
     
       return 0;
                       
    }
    
  
    

}
