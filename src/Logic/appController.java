package Logic;
/*
 * ApplicationLogic.java
 *
 * Created on April 12, 2008, 11:03 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

//CEN5076
//package my.PSM;


import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.lang.Thread;
import java.util.logging.Level;
import java.util.logging.Logger;

//import DataStore.DBConnection;
import stubs.DBConnection;
//import Interface.InterfaceController;
import stubs.InterfaceController;
//import Interface.Messages;
import stubs.Messages;
//import Interface.PrefilledScheduleForm;
import stubs.PrefilledScheduleForm;
//import Interface.ScheduleForm;
import stubs.ScheduleForm;
//import stubs.FutureTimer;

//Don't use Authenticate stub for SS testing
//import stubs.Authenticate;


/**
 *
 * @author lrizo002
 */
public class appController {
    
    private static final long TENMIN = 600000;
    
    private int hr;
    private int min;
    private Calendar autoClear = new GregorianCalendar();
    private Calendar setRun = new GregorianCalendar();
    //@SuppressWarnings("unused")
	//private static Calendar calendar = new GregorianCalendar();
    // CEN5076 - commented and added public to debug
	private Timer timer = new FutureTimer();
	//public Timer timer = new FutureTimer();
	public boolean testingTimers = false;
    private Date date = new Date();
    private Date date2 = new Date();
    
    public String defSub = "";
    public String defSemester = "";
    public String defCourseName = "";
    public String defCourseStart = "";
    public String defCourseEnd = "";
    public String defMonStart = "";
    public String defMonEnd = "";
    public String defTueStart = "";
    public String defTueEnd = "";
    public String defWedStart = "";
    public String defWedEnd = "";
    public String defThuStart = "";
    public String defThuEnd = "";
    public String defFriStart = "";
    public String defFriEnd = "";
    public String defSatStart = "";
    public String defSatEnd = "";
    
    private String username;
    private String password;
    private boolean loggedin;
    //CEN5076 - commented made public for Debugging, also needed to be public for Authenticate stub to set a value
    //private boolean dataReceived = false;
    public boolean dataReceived = false;
    private boolean edSchedSel = false;
    private boolean schedSetupSel = false;
    private boolean logoutSel = false;
    
    private int clearDate, clearMonth, clearYear;
    private int counter = 0;
    
    //CEN5076 - commented made public for Debugging
    public static DBConnection db = new DBConnection();
    //private DBConnection db;
    private static InterfaceController ic = new InterfaceController();
    private Authenticate auth;
    private int courseSel;
    private static long classEnded = 0;
    
    /* CEN5076 */
    //added to help in Testing TimerTasks
    public boolean is15BeforeEnd = false;
    public boolean is5BeforeEnd = false;
    
    /*** Added Declaration of new FutureTimer() CEN5076 to help parse out main()***/
    public Timer newTimer = new FutureTimer();
    
    /** Creates a new instance of ApplicationLogic */
    public appController() {
        hr = min = 0;
        db = new DBConnection();
    }
    
    
    /**** BEGIN TEST GENERATED FIELDS ****/
    /**
     * All fields until END TEST GENERATED FIELDS are used to get validation values for tests and help set
     * necessary fields.
     */
    
    //@Unused
    /*
     public boolean getEdSchedSel(){
    	 return edSchedSel;
     }
     
     public void setEdSchedSel(boolean b){
    	 
    	 this.edSchedSel = b;
     }
 
     public boolean getSchedSetupSel(){
    	 return schedSetupSel;
     }
     
     
     public void setSchedSetupSel(boolean b){
    	 this.schedSetupSel = b;
     }
 
     */
    // End Unused
    
    public void setTestingTimers() {
    	testingTimers = true;
    }
    
    /**
	 * @return the hr
	 */
	public int getHr() {
		return hr;
	}

	/**
	 * @return the min
	 */
	public int getMin() {
		return min;
	}

	/**
	 * @return the autoClear
	 */
	public  Calendar getAutoClear() {
		return autoClear;
	}

	/**
	 * @return the setRun
	 */
	public  Calendar getSetRun() {
		return setRun;
	}
	
	/**
	 * @return the timer
	 */
	public  Timer getTimer() {
		return timer;
	}

	/**
	 * @return the date
	 */
	public  Date getDate() {
		return date;
	}

	/**
	 * @return the date2
	 */
	public  Date getDate2() {
		return date2;
	}

	/**
	 * @return the defSub
	 */
	public String getDefSub() {
		return defSub;
	}

	/**
	 * @return the defSemester
	 */
	public String getDefSemester() {
		return defSemester;
	}

	/**
	 * @return the defCourseName
	 */
	public  String getDefCourseName() {
		return defCourseName;
	}

	/**
	 * @return the defCourseStart
	 */
	public  String getDefCourseStart() {
		return defCourseStart;
	}

	/**
	 * @return the defCourseEnd
	 */
	public  String getDefCourseEnd() {
		return defCourseEnd;
	}

	/**
	 * @return the defMonStart
	 */
	public  String getDefMonStart() {
		return defMonStart;
	}

	/**
	 * @return the defMonEnd
	 */
	public  String getDefMonEnd() {
		return defMonEnd;
	}

	/**
	 * @return the defTueStart
	 */
	public  String getDefTueStart() {
		return defTueStart;
	}

	/**
	 * @return the defTueEnd
	 */
	public  String getDefTueEnd() {
		return defTueEnd;
	}

	/**
	 * @return the defWedStart
	 */
	public  String getDefWedStart() {
		return defWedStart;
	}

	/**
	 * @return the defWedEnd
	 */
	public  String getDefWedEnd() {
		return defWedEnd;
	}

	/**
	 * @return the defThuStart
	 */
	public  String getDefThuStart() {
		return defThuStart;
	}

	/**
	 * @return the defThuEnd
	 */
	public  String getDefThuEnd() {
		return defThuEnd;
	}

	/**
	 * @return the defFriStart
	 */
	public  String getDefFriStart() {
		return defFriStart;
	}

	/**
	 * @return the defFriEnd
	 */
	public  String getDefFriEnd() {
		return defFriEnd;
	}

	/**
	 * @return the defSatStart
	 */
	public  String getDefSatStart() {
		return defSatStart;
	}

	/**
	 * @return the defSatEnd
	 */
	public  String getDefSatEnd() {
		return defSatEnd;
	}

	/**
	 * @return the username
	 */
	public  String getUsername() {
		return username;
	}
	
	public void setUsername(String name) {
		this.username = name;
	}

	/**
	 * @return the password
	 */
	public  String getPassword() {
		return password;
	}
	
	
	public void setPassword(String pass) {
		this.password = pass;
	}

	/**
	 * @return the loggedin
	 */
	public boolean isLoggedin() {
		return loggedin;
	}
	
	/**
	 * 
	 * @param l - logged in state T or F
	 */
	public void setLoggedIn(Boolean l ){
		
		this.loggedin = l;
	}

	/**
	 * @return the dataReceived
	 */
	public  boolean isDataReceived() {
		return dataReceived;
	}

	/**
	 * @return the edSchedSel
	 */
	public  boolean isEdSchedSel() {
		return edSchedSel;
	}

	/**
	 * @return the schedSetupSel
	 */
	public  boolean isSchedSetupSel() {
		return schedSetupSel;
	}

	/**
	 * @return the logoutSel
	 */
	public  boolean isLogoutSel() {
		return logoutSel;
	}

	/**
	 * @return the clearDate
	 */
	public  int getClearDate() {
		return clearDate;
	}

	/**
	 * @return the clearMonth
	 */
	public  int getClearMonth() {
		return clearMonth;
	}

	/**
	 * @return the clearYear
	 */
	public  int getClearYear() {
		return clearYear;
	}

	/**
	 * @return the counter
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * 
	 * @param c - set the counter for login attempts
	 */
	public void setCounter(int c){
		this.counter = c;
	}

	/**
	 * @return the db
	 */
	public  DBConnection getDb() {
		return db;
	}

	

	/**
	 * @return the ic
	 */
	public  InterfaceController getIc() {
		return ic;
	}
	
	public void setDataReceived(Boolean d){
		
		//this.ic.log.dataRec = d;
		this.dataReceived = d;
	}
	
	/**
	 * @return the auth
	 */
	public  Authenticate getAuth() {
		return auth;
	}

	/**
	 * @return the courseSel
	 */
	public  int getCourseSel() {
		return courseSel;
	}

	/**
	 * @return the classEnded
	 */
	public long getClassEnded() {
		return classEnded;
	}
	
	/**
	 * 
	 * @param c - the classEnded long time in milliseconds
	 */
	public void setClassEnded(long c){
		
		classEnded = c;
	}

	/**
	 * 
	 * @return dataReceived private variable
	 */
	public boolean getDataReceived(){
		
		return this.dataReceived;
	}
	
	/**
	 * @return the dbClear
	 */
	/*@Unused
	public static TimerTask getDbClear() {
		return dbClear;
	}
	*/

	/**
	 * @return the popup15min
	 */
	public TimerTask getPopup15min() {
		return this.popup15min;
	}

	/**
	 * @return the popup5min
	 */
	public TimerTask getPopup5min() {
		return this.popup5min;
	}

	/**
	 * @return the endofclass
	 */
	public TimerTask getEndofclass() {
		return this.endofclass;
	}

	/**
	 * @return the systemExit
	 */
	/*@Unused
	public static TimerTask getSystemExit() {
		return systemExit;
	}
	*/

	/**
	 * @return the tenmin
	 */
	public long getTenmin() {
		return TENMIN;
	}
	
	/**
	 * 
	 * @return return the logOutSel value
	 */
	/*public boolean getLogOutSel(){
		
		return this.logoutSel;
	}
	*/
	/**
	 * 
	 *
	 * @param b set the logout selected to b
	 */
	public void setLogOutSel(Boolean b){
		
		this.logoutSel = b;
	}
	
	public void initAuthenticate(String u,String p){
		
		this.auth = new Authenticate(u,p);
		
	}
	
	/**
	 * 
	 * @param d - Date sets the date used in appcontroller for autoexit
	 */
	public void setDate(Date d){
		
		this.date = d;
	}
	
/*	public void nullTimers(){

		this.popup15min = null;
		this.popup5min = null;
		this.endofclass = null;
	}*/

	public void newTimers(){

	}

	/**
	 * Test Driver for entering authenticate
	 * 
	 * @param - username
	 * @param - password
	 * 
	 */
	/*@Unused
	public void testDriverLogin(String user,String pass){
		
		this.dataReceived = true;
	}
	*/

    /**** END TEST GENERATED FIELDS ****/

	/**
	 * 
	 * Main was parsed out into a non static and single call to a begin method as instructed by P. Clarke
	 * 
	 * @param args
	 */
	public static void main(String args[])
    {       
//       Calendar now = new GregorianCalendar();
//       now.set(2008, 3, 15, 13, 29);
//       date = now.getTime();
//      
    	
		appController appc = new appController();
		
		//DBConnection db = new DBConnection();
		
		//int result = db.connect("jdbc:mysql://dgarc012.homeip.net:3306/mydb", "cen5076", "cen5076");
		//System.out.println("Connected:" + result);
		appc.begin();
    }

	/**
	 * Loginstate 
	 * 
	 */
	public void loginState(){
		
		ic.Initiate_Login_Form();            
	    dataReceived = false;
	    do
	    {
	    	//data ! received
	    	dataReceived = ic.log.dataReceived();
	    	sleep(300);
	         
	    	//Waiting Login
	
	    } while(!dataReceived);
	     
	     
	    //data received
	    ic.log.setDataRec(false);
	    dataReceived = false;
	    //CEN5076
	    //System.out.println("User= " + ic.log.getUsername());
	    //System.out.println("Password= " + ic.log.getPassword());
	    username = ic.log.getUsername();
	    password = ic.log.getPassword();
	}
	
	/**
	 * In charge of authentication
	 * 
	 */
	public void authenticate(){
		
		 auth = new Authenticate(username,password);
	     if(auth.validate_Login()){
	         loggedin = true;
	         auth.logout();
	         db.connect(username,password);
	         
	         //logged in
	     }
	     
	       
	     if(!loggedin){
			 	//Waiting Login
	
	     //invalid not locked out
	         ic.Initiate_IncorrectLogin();
	         counter++;
	         while(!dataReceived)
	         {
	             dataReceived = Messages.isAck();
	             //System.out.println("in");
	         }
	         dataReceived = false;
	         Messages.setAck(false);
	         
	     }
	     
	     if(counter >= 3){
	        
	     	//terminated
	        
	         ic.passwordLock();
	         while(!dataReceived)
	         {
	             dataReceived = Messages.isAck();
	             
	         }
	         System.exit(0);
	      }
	}
	
	/**
	 * main work of the begin method
	 * 
	 */
	public void ready(){
		
		//Added by DG
		dataReceived = false;
	
	    //logged in
	    ic.Initiate_MainMenu();
	    try{
		    if(this.checkClear())
		    {
		 	   db.clearDatabase();
		    }
	    }
	    catch (Exception e){
	 	   System.out.println(e.toString());
	    }
	    
	    ArrayList<Integer> courseList = db.getCourses();
	     
	    Calendar tempC = new GregorianCalendar();
	    int currentDay = tempC.get(Calendar.DAY_OF_WEEK);
	    tempC.setTimeInMillis(System.currentTimeMillis());
	    //System.out.println("Curr Day: " +currentDay);
	    Date fifteenMin;
	    Date fiveMin;
	    Date endClass;
	     
	    for(int i = 0; i < courseList.size(); i++)
	    {
	    	System.out.println("Courses for day" + currentDay + ": " + courseList);
	    	 
	    	boolean isNull = true;
	    	getData(courseList.get(i).intValue());
	    	newTimer = new FutureTimer();
	         
	    	if(currentDay == 2 && defMonEnd.compareTo("") != 0)
	    	{
	    		timerParser(defMonEnd);
	    		isNull = false;
	    	}
	    	
	    	else if(currentDay == 3 && defTueEnd.compareTo("") != 0)
	    	{
	    		timerParser(defTueEnd);
	    		isNull = false;
	    	}
	    	
	    	else if(currentDay == 4 && defWedEnd.compareTo("") != 0)
	    	{
	    		timerParser(defWedEnd);
	    		isNull = false;
	    	}
	     
	    	else if(currentDay == 5 && defThuEnd.compareTo("") != 0)
	    	{
	    		timerParser(defThuEnd);
	    		isNull = false;
	    	}
	    	else if(currentDay == 6 && defFriEnd.compareTo("") != 0)
	    	{
	    		timerParser(defFriEnd);
	    		isNull = false;
	    	}
	         
	    	else if(currentDay == 7 && defSatEnd.compareTo("") != 0)
	    	{    
	    		timerParser(defSatEnd);
	    		isNull = false;
	    	}         
	         
	    	if(!isNull) {
	    		fiveMin = get5BeforeEnd(hr, min);
	    		System.out.println("Five Minute Warning-" + fiveMin.toString());
	    		newTimer.schedule(popup5min, fiveMin);
	    		//newTimer.schedule(popup5min, new Date(System.currentTimeMillis()+10));

	    		fifteenMin = get15BeforeEnd(hr, min);   
	    		System.out.println("Fifteen Minute Warning-" + fifteenMin.toString());
	    		newTimer.schedule(popup15min, fifteenMin);
	    		//newTimer.schedule(popup15min, new Date(System.currentTimeMillis()+10));
	
	    		endClass = getEndTime(hr, min);
	    		System.out.println("End Class Warning-" + endClass.toString());
	    		newTimer.schedule(endofclass, endClass);
	    		//newTimer.schedule(endofclass, new Date(System.currentTimeMillis()+10));
	    	}
	    }	
	}
	
	/**
	 * 
	 * Handles the logout actions if logout selected
	 * 
	 */
	public void logOut(){

		//terminated
	    // Logout 
		//CEN5076 Changed to have parameter this
	    //auth.logout(this);
	    ic.Initiate_Logout();

	}
	
	/**
	 * Handles the edit schedule button and changing a course schedule
	 * 
	 */
	@SuppressWarnings("static-access")
	public void editSchedule(){
	    //System.out.println("Selection=" + ic.cs.selection);
	
		//Editing Schedule
	    ic.Course_Select_Form();
	
	    while(!dataReceived)
	    {
	        dataReceived = ic.cs.courseSelected();
	        sleep(300);
	    }
	
	    ic.cs.setCourseSelected(false);
	    dataReceived = false;
	
	    courseSel = ic.cs.getSelection();
	    
	    getData(courseSel);
	
	    ic.Pre_Filled_Form(courseSel,defSub,defCourseName,defSemester,defCourseStart,
	            defCourseEnd,defMonStart,defMonEnd,defTueStart,defTueEnd,defWedStart,
	            defWedEnd,defThuStart,defThuEnd,defFriStart,defFriEnd,defSatStart,defSatEnd);
	
	    while(!dataReceived)
	    {
	        dataReceived = ic.edSched.dataRec(); 
	        sleep(300);
	    }    dataReceived = false;
	    
	    ic.edSched.setDataRec(false);
	
	    //System.out.println("Save has been pressed" +ic.edSched.newMonStart);
	    System.out.println("Store class");
	    if(db == null)
	    	System.out.println("Null db");
	    
	    System.out.println(PrefilledScheduleForm.getDefCourseID()); 
	    System.out.println(PrefilledScheduleForm.getNewCourseStart()); 
	    System.out.println(PrefilledScheduleForm.getNewCourseEnd()); 
	    System.out.println(PrefilledScheduleForm.getNewMonStart()); 
	    System.out.println(PrefilledScheduleForm.getNewMonEnd()); 
	    System.out.println(PrefilledScheduleForm.getNewTueStart()); 
	    System.out.println(PrefilledScheduleForm.getNewTueEnd()); 
	    System.out.println(PrefilledScheduleForm.getNewWedStart()); 
	    System.out.println(PrefilledScheduleForm.getNewWedEnd()); 
	    System.out.println(PrefilledScheduleForm.getNewThuStart());
	    System.out.println(PrefilledScheduleForm.getNewThuEnd()); 
	    System.out.println(PrefilledScheduleForm.getNewFriStart()); 
	    System.out.println(PrefilledScheduleForm.getNewFriEnd()); 
	    System.out.println(PrefilledScheduleForm.getNewSatStart()); 
	    System.out.println(PrefilledScheduleForm.getNewSatEnd());
	    db.storeClassSched(PrefilledScheduleForm.getDefCourseID(), PrefilledScheduleForm.getNewCourseStart(), PrefilledScheduleForm.getNewCourseEnd(), 
	            PrefilledScheduleForm.getNewMonStart(), PrefilledScheduleForm.getNewMonEnd(), PrefilledScheduleForm.getNewTueStart(), PrefilledScheduleForm.getNewTueEnd(), 
	            PrefilledScheduleForm.getNewWedStart(), PrefilledScheduleForm.getNewWedEnd(), PrefilledScheduleForm.getNewThuStart(), PrefilledScheduleForm.getNewThuEnd(), 
	            PrefilledScheduleForm.getNewFriStart(), PrefilledScheduleForm.getNewFriEnd(), PrefilledScheduleForm.getNewSatStart(), PrefilledScheduleForm.getNewSatEnd());
	    System.out.println("Class Stored");
	}
	
	/**
	 * Handles the adding of a new courses schedule
	 * 
	 */
	public void setSchedule(){
		
		 ic.sched.launchInitial();
	     //Initial Schedule Setup
		 //TODO Issue they did not use dataReceived so cannot enter this code
	     while(!ic.sched.dataRec())
	     {
	         dataReceived = ic.sched.dataRec();
	         sleep(300);
	     }
	     dataReceived = false;
	     ic.sched.setDataRec(false);
	     
	     db.storeClassInfo(ScheduleForm.getNewCourseID(), ScheduleForm.getNewSub(), ScheduleForm.getNewCourseName(),ScheduleForm.getNewSemester());
	     db.storeClassSched(ScheduleForm.getNewCourseID(), ScheduleForm.getNewCourseStart(), ScheduleForm.getNewCourseEnd(), 
	             ScheduleForm.getNewMonStart(), ScheduleForm.getNewMonEnd(), ScheduleForm.getNewTueStart(), ScheduleForm.getNewTueEnd(), 
	             ScheduleForm.getNewWedStart(), ScheduleForm.getNewWedEnd(), ScheduleForm.getNewThuStart(), ScheduleForm.getNewThuEnd(), 
	             ScheduleForm.getNewFriStart(), ScheduleForm.getNewFriEnd(), ScheduleForm.getNewSatStart(), ScheduleForm.getNewSatEnd());
	
	}
	
	/**
	 *  Created to parse out MAIN and handles clearing the database when a course has ended
	 */
	public boolean checkClear()
	{
	    ArrayList<String> endDates = db.getEndDates();
	    Calendar endCal = new GregorianCalendar();
	    Calendar now = Calendar.getInstance();
	    
	    for(int i = 0; i < endDates.size(); i++)
	    {
	        dateParser(endDates.get(i));
	        
	        //System.out.println("Day : " +clearDate);
	        //System.out.println("Month : " +clearMonth);
	        //System.out.println("Year : " +clearYear);
	
	        endCal.set(clearYear + 2000, clearMonth-1, clearDate);
	        if(now.compareTo(endCal) <= 0)
	           return false;
	            
	    }
	    return true;
	            
	}

/**
 * This is the main method parsed out into many methods which are inside begin.
 * 
 */
public void begin(){
		
		while(!loggedin)
	       {    
		   
			   this.loginState();
	            

	           this.authenticate(); 
	       }

           this.ready();
	        
		       //logged in

	        while(!logoutSel)
	        { 
	        	
			      //logged in

	        	@SuppressWarnings("unused")
	    		long newCurrentTime;
	               while(!dataReceived)
	               {
	                   dataReceived = ic.mm.dataRec();
	                   edSchedSel = ic.mm.editSchedSelected();
	                   //CEN5076
	                   //dataReceived = true;	                   //
	                   schedSetupSel = ic.mm.InitSetupSelected();
	                   //schedSetupSel = true;
//
	                   logoutSel = ic.mm.logoutSelected();
	                   //System.out.println("Class end time: " +classEnded);
	                   //System.out.println("Current time: " +System.currentTimeMillis());
	                           
	                           
	                   if(classEnded != 0 && System.currentTimeMillis() - classEnded >= TENMIN)
	                   {
	                	   //terminated
	                     //System.out.println("EXIT");
	                     System.exit(0);   
	                   }
	                   
	                   sleep(500);
	                   
				       //logged in

	               }
	               
	               
	               newCurrentTime = 0;
	               ic.mm.setdataRec(false);
	               dataReceived = false;

	               if(logoutSel)
	               {
	            	 this.logOut();
	               }
	               
	               else if(edSchedSel)
	               {
	                  this.editSchedule();
	                  
	               }
	               else if(schedSetupSel)
	               {
	            	   
	                  this.setSchedule();
	               }

	               dataReceived = false;
	               
	              // db.disconnect();
	        }
	        
	        
	}


    /*
     * * Created to parse out MAIN 
     * 
     *  @Unused
     */
/*
    public void checkTimes()
    {
        ArrayList<Integer> courseList = db.getCourses();
        
        Calendar tempC = new GregorianCalendar();
        int currentDay = tempC.get(Calendar.DAY_OF_WEEK);
        tempC.setTimeInMillis(System.currentTimeMillis());
        //System.out.println("Curr Day: " +currentDay);
        Date fifteenMin;
        Date fiveMin;
        Date endClass;
        
        for(int i = 0; i < courseList.size(); i++)
        {
            boolean isNull = true;
            getData(courseList.get(i).intValue());
            Timer newTimer = new FutureTimer();
            
            if(currentDay == 2 && defMonEnd.compareTo("") != 0)
            {
                timerParser(defMonEnd);
                isNull = false;
            }
            else if(currentDay == 3 && defTueEnd.compareTo("") != 0)
            {
                timerParser(defTueEnd);
                isNull = false;
            }
            else if(currentDay == 4 && defWedEnd.compareTo("") != 0)
            {
                timerParser(defWedEnd);
                isNull = false;
            }
            else if(currentDay == 5 && defThuEnd.compareTo("") != 0)
            {
                timerParser(defThuEnd);
                isNull = false;
            }
            else if(currentDay == 6 && defFriEnd.compareTo("") != 0)
            {
                timerParser(defFriEnd);
                isNull = false;
            }
            
            else if(currentDay == 7 && defSatEnd.compareTo("") != 0)
            {
                
                timerParser(defSatEnd);
                isNull = false;
            }
            
            
            if(!isNull){
                fiveMin = get5BeforeEnd(hr, min);
                newTimer.schedule(popup5min, fiveMin);
                fifteenMin = get15BeforeEnd(hr, min);              
                newTimer.schedule(popup15min, fifteenMin);
                endClass = getEndTime(hr, min);
                newTimer.schedule(endofclass, endClass);
            }
        }   
       
    }

    */
    
    public void getData(int course)
    {
        defSub = db.fetchCourseSubj(course);
        System.out.println("setting Course " + course + " sub to " + String.valueOf(db.fetchCourseSubj(course)));
        defSemester = db.fetchCourseSemester(course);
        defCourseName = db.fetchCourseName(course);
        defCourseStart = db.fetchCourseStart(course);
        defCourseEnd = db.fetchCourseEnd(course);
        defMonStart = db.fetchStartMon(course);
        defMonEnd = db.fetchEndMon(course);
        defTueStart = db.fetchStartTue(course);
        defTueEnd = db.fetchEndTue(course);
        defWedStart = db.fetchStartWed(course);
        defWedEnd = db.fetchEndWed(course);
        defThuStart = db.fetchStartThu(course);
        defThuEnd = db.fetchEndThu(course);
        defFriStart = db.fetchStartFri(course);
        defFriEnd = db.fetchEndFri(course);
        defSatStart = db.fetchStartSat(course);
        defSatEnd = db.fetchEndSat(course);           
    }
    
    
    public void sleep(int milli)
    {
          try { 
           Thread.sleep(milli);
        } catch (InterruptedException ex) {
           Logger.getLogger(appController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
    public void LogIn()
    {      
        if(db.connect(username,password) == 0)
            loggedin = true;
        else
            loggedin = false;        
    }
    
    public DBConnection getCon()
    {
        return db;
    }
    
    
    private static TimerTask dbClear = new TimerTask()
    {
        public void run()
        {
        	
          //db.clearDatabase();
        }
    };
    
    private TimerTask popup15min = new TimerTask()
    {
        public void run()
        {
            ic.msg.FifteenMinWarning();
        }
    };
	
    private TimerTask popup5min = new TimerTask()
    {
        public void run()
        {
            ic.msg.FiveMinWarning();
        }
    };
    
    
    private TimerTask endofclass = new TimerTask()
    {
        public void run()
        {
            ic.msg.endClassWarning();
            classEnded = System.currentTimeMillis();
        }
    };
    
    
    private static TimerTask systemExit = new TimerTask()
    {
        public void run()
        {
            System.exit(0);
        }
    };
    
       
    public void setTime(int year, int month, int date, int hours, int mins)
    {
        setRun.set(year, month, date, hours, mins);
    }
    
    public Date getTime()
    {
        return setRun.getTime();
    }
    
    public long getTimeMillis()
    {
    	//setRun = Gregorian calendar
        return setRun.getTimeInMillis();
    }
    
    public void timerParser(String timer)
    {
        hr = Integer.parseInt(timer.substring(0,2));
        min = Integer.parseInt(timer.substring(3,5));
    }
    
    public void dateParser(String timer)
    {
    	System.out.println("dateParser input:"+ timer);
        
        clearMonth = Integer.parseInt(timer.substring(0,2));
       // System.out.println(timer.substring(0,2));
        clearDate = Integer.parseInt(timer.substring(3,5));
        clearYear = Integer.parseInt(timer.substring(6,8));
    }
    
    public int returnHr()
    {
        return hr;
    }
    
    public int returnMin()
    {
        return min;
    }
    
    public Date getEndTime(int hrs, int mins)
    {
        @SuppressWarnings("unused")
		int years, months, dates, dayOfWeek;
        
        Calendar tempC = new GregorianCalendar();
        
        tempC.setTimeInMillis(System.currentTimeMillis());
        
        years = tempC.get(Calendar.YEAR);
        months = tempC.get(Calendar.MONTH);
        dates = tempC.get(Calendar.DATE);
        dayOfWeek = tempC.get(Calendar.DAY_OF_WEEK);
        tempC.set(years, months, dates, hrs, mins - 1, 1);
        System.out.println(tempC.getTime().toString());
        return tempC.getTime();
    }
    
    public void setSemesterClear(int year, int month, int date, int hours, int mins)
    {
        autoClear.set(year, month, date, hours, mins);
        date2 = autoClear.getTime();
    }
    
    public Date getSemesterClear()
    {
        return autoClear.getTime();
    }
    
    public Date get15BeforeEnd(int hrs, int mins)
    {
        @SuppressWarnings("unused")
		int years, months, dates, dayOfWeek; 
        
        Calendar tempC = new GregorianCalendar();
        
        tempC.setTimeInMillis(System.currentTimeMillis());
        
        years = tempC.get(Calendar.YEAR);
        months = tempC.get(Calendar.MONTH);
        dates =  tempC.get(Calendar.DATE);
        dayOfWeek = tempC.get(Calendar.DAY_OF_WEEK);
        
        /** CEN5076 - added for easier timer testing **/
        if (this.testingTimers)
        	tempC.set(years, months, dates, hrs, mins - 3, 1);
        else
        	tempC.set(years, months, dates, hrs, mins - 15, 1);
        
        return tempC.getTime();
    }
    
    public Date get5BeforeEnd(int hrs, int mins)
    {
        @SuppressWarnings("unused")
		int years, months, dates, dayOfWeek;
        
        Calendar tempC = new GregorianCalendar();
        
        tempC.setTimeInMillis(System.currentTimeMillis());
        
        years = tempC.get(Calendar.YEAR);
        months = tempC.get(Calendar.MONTH);
        dates = tempC.get(Calendar.DATE);
        dayOfWeek = tempC.get(Calendar.DAY_OF_WEEK);
        
        /** CEN5076 - added for easier timer testing **/
        if (this.testingTimers)
        	tempC.set(years, months, dates, hrs, mins - 2, 1);
        else
        	tempC.set(years, months, dates, hrs, mins - 5, 1);
        // System.out.println(dayWeek);
        return tempC.getTime();
    }
    
    public void autoExit()
    {
        timer.schedule(systemExit, date);
    }
    
    public void autoClear()
    {
        timer.schedule(dbClear, date2);
    }
}