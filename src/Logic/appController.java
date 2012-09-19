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


/**
 *
 * @author lrizo002
 */
public class appController {
    
    private static final long TENMIN = 600000;
    
    private static int hr;
    private static int min;
    private static Calendar autoClear = new GregorianCalendar();
    private static Calendar setRun = new GregorianCalendar();
    //@SuppressWarnings("unused")
	//private static Calendar calendar = new GregorianCalendar();
    private static Timer timer = new FutureTimer();
    private static Date date = new Date();
    private static Date date2 = new Date();
    
    public static String defSub = "";
    public static String defSemester = "";
    public static String defCourseName = "";
    public static String defCourseStart = "";
    public static String defCourseEnd = "";
    public static String defMonStart = "";
    public static String defMonEnd = "";
    public static String defTueStart = "";
    public static String defTueEnd = "";
    public static String defWedStart = "";
    public static String defWedEnd = "";
    public static String defThuStart = "";
    public static String defThuEnd = "";
    public static String defFriStart = "";
    public static String defFriEnd = "";
    public static String defSatStart = "";
    public static String defSatEnd = "";
    
    private static String username;
    private static String password;
    private static boolean loggedin;
    private static boolean dataReceived = false;
    private static boolean edSchedSel = false;
    private static boolean schedSetupSel = false;
    private static boolean logoutSel = false;
    
    private static int clearDate, clearMonth, clearYear;
    private static int counter = 0;
    
    public static DBConnection db = new DBConnection();
    private static InterfaceController ic = new InterfaceController();
    private static Authenticate auth;
    private static int courseSel;
    private static long classEnded = 0;
       
    
    /** Creates a new instance of ApplicationLogic */
    public appController() {
        hr = min = 0;
    }
    
    
    /**** BEGIN TEST GENERATED FIELDS ****/
    /**
     * @ return edSchedSel
     */
     public boolean getEdSchedSel(){
    	 return edSchedSel;
     }
     
     /**
      * 
      * @return schedSetupSel
      */
     public boolean getSchedSetupSel(){
    	 return schedSetupSel;
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
	public static Calendar getAutoClear() {
		return autoClear;
	}

	/**
	 * @return the setRun
	 */
	public static Calendar getSetRun() {
		return setRun;
	}

	
	/**
	 * @return the timer
	 */
	public static Timer getTimer() {
		return timer;
	}

	/**
	 * @return the date
	 */
	public static Date getDate() {
		return date;
	}

	/**
	 * @return the date2
	 */
	public static Date getDate2() {
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
	public static String getDefSemester() {
		return defSemester;
	}

	

	/**
	 * @return the defCourseName
	 */
	public static String getDefCourseName() {
		return defCourseName;
	}

	

	/**
	 * @return the defCourseStart
	 */
	public static String getDefCourseStart() {
		return defCourseStart;
	}

	

	/**
	 * @return the defCourseEnd
	 */
	public static String getDefCourseEnd() {
		return defCourseEnd;
	}

	

	/**
	 * @return the defMonStart
	 */
	public static String getDefMonStart() {
		return defMonStart;
	}

	

	/**
	 * @return the defMonEnd
	 */
	public static String getDefMonEnd() {
		return defMonEnd;
	}

	

	/**
	 * @return the defTueStart
	 */
	public static String getDefTueStart() {
		return defTueStart;
	}

	

	/**
	 * @return the defTueEnd
	 */
	public static String getDefTueEnd() {
		return defTueEnd;
	}

	

	/**
	 * @return the defWedStart
	 */
	public static String getDefWedStart() {
		return defWedStart;
	}

	

	/**
	 * @return the defWedEnd
	 */
	public static String getDefWedEnd() {
		return defWedEnd;
	}

	

	/**
	 * @return the defThuStart
	 */
	public static String getDefThuStart() {
		return defThuStart;
	}

	

	/**
	 * @return the defThuEnd
	 */
	public static String getDefThuEnd() {
		return defThuEnd;
	}

	

	/**
	 * @return the defFriStart
	 */
	public static String getDefFriStart() {
		return defFriStart;
	}

	

	/**
	 * @return the defFriEnd
	 */
	public static String getDefFriEnd() {
		return defFriEnd;
	}

	

	/**
	 * @return the defSatStart
	 */
	public static String getDefSatStart() {
		return defSatStart;
	}

	

	/**
	 * @return the defSatEnd
	 */
	public static String getDefSatEnd() {
		return defSatEnd;
	}

	

	/**
	 * @return the username
	 */
	public static String getUsername() {
		return username;
	}

	

	/**
	 * @return the password
	 */
	public static String getPassword() {
		return password;
	}

	

	/**
	 * @return the loggedin
	 */
	public static boolean isLoggedin() {
		return loggedin;
	}

	

	/**
	 * @return the dataReceived
	 */
	public static boolean isDataReceived() {
		return dataReceived;
	}

	

	/**
	 * @return the edSchedSel
	 */
	public static boolean isEdSchedSel() {
		return edSchedSel;
	}

	

	/**
	 * @return the schedSetupSel
	 */
	public static boolean isSchedSetupSel() {
		return schedSetupSel;
	}

	

	/**
	 * @return the logoutSel
	 */
	public static boolean isLogoutSel() {
		return logoutSel;
	}

	

	/**
	 * @return the clearDate
	 */
	public static int getClearDate() {
		return clearDate;
	}

	

	/**
	 * @return the clearMonth
	 */
	public static int getClearMonth() {
		return clearMonth;
	}

	

	/**
	 * @return the clearYear
	 */
	public static int getClearYear() {
		return clearYear;
	}

	

	/**
	 * @return the counter
	 */
	public int getCounter() {
		return counter;
	}

	

	/**
	 * @return the db
	 */
	public static DBConnection getDb() {
		return db;
	}

	

	/**
	 * @return the ic
	 */
	public static InterfaceController getIc() {
		return ic;
	}

	

	/**
	 * @return the auth
	 */
	public static Authenticate getAuth() {
		return auth;
	}

	

	/**
	 * @return the courseSel
	 */
	public static int getCourseSel() {
		return courseSel;
	}

	

	/**
	 * @return the classEnded
	 */
	public long getClassEnded() {
		return classEnded;
	}

	

	/**
	 * @return the dbClear
	 */
	public static TimerTask getDbClear() {
		return dbClear;
	}

	

	/**
	 * @return the popup15min
	 */
	public static TimerTask getPopup15min() {
		return popup15min;
	}

	

	/**
	 * @return the popup5min
	 */
	public static TimerTask getPopup5min() {
		return popup5min;
	}

	

	/**
	 * @return the endofclass
	 */
	public static TimerTask getEndofclass() {
		return endofclass;
	}

	

	/**
	 * @return the systemExit
	 */
	public static TimerTask getSystemExit() {
		return systemExit;
	}

	

	/**
	 * @return the tenmin
	 */
	public long getTenmin() {
		return TENMIN;
	}

    /**** END TEST GENERATED FIELDS ****/

	public static void main(String args[])
    {       
//       Calendar now = new GregorianCalendar();
//       now.set(2008, 3, 15, 13, 29);
//       date = now.getTime();
//      
    	
		appController appc = new appController();
		
		appc.begin();
       
       
    }
	
	public void begin(){
		
		while(!loggedin)
	       {    
	    	   
	    	   
	           ic.Initiate_Login_Form();            
	           
	            do
	            {
	            	dataReceived = ic.log.dataReceived();
	                sleep(300);
	                
	            }while(!dataReceived);
	            
	            ic.log.setDataRec(false);
	            dataReceived = false;
	            //CEN5076
	            System.out.println("User= " + ic.log.getUsername());
	            System.out.println("Password= " + ic.log.getPassword());
	            username = ic.log.getUsername();
	            password = ic.log.getPassword();

	            
	            auth = new Authenticate(username,password);
	            if(auth.validate_Login()){
	                loggedin = true;
	                auth.logout();
	                db.connect(username,password);
	            }
	            
	              
	            if(!loggedin){
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
	               
	                ic.passwordLock();
	                while(!dataReceived)
	                {
	                    dataReceived = Messages.isAck();
	                    
	                }
	                System.exit(0);
	             }
	       }
		   
	       
	       ic.Initiate_MainMenu();
	       if(checkClear())
	       {
	    	   
	    	   db.clearDatabase();
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
	        while(!logoutSel)
	        { 
	        	@SuppressWarnings("unused")
	    		long newCurrentTime;
	               while(!dataReceived)
	               {
	                   dataReceived = ic.mm.dataRec();
	                   edSchedSel = ic.mm.editSchedSelected();
	                   schedSetupSel = ic.mm.InitSetupSelected();
	                   logoutSel = ic.mm.logoutSelected();
	                   //System.out.println("Class end time: " +classEnded);
	                   //System.out.println("Current time: " +System.currentTimeMillis());
	                           
	                           
	                   if(classEnded != 0 && System.currentTimeMillis() - classEnded >= TENMIN)
	                   {
	                     //System.out.println("EXIT");
	                     System.exit(0);   
	                   }
	                   
	                   sleep(500);
	               }
	               newCurrentTime = 0;
	               ic.mm.setdataRec(false);
	               dataReceived = false;

	               if(logoutSel)
	               {
	                   // Logout 
	                   auth.logout();
	                   ic.Initiate_Logout();

	               }
	               else if(edSchedSel)
	               {
	                    //Edit Schedule
	                   ic.Course_Select_Form();

	                   while(!dataReceived)
	                   {
	                       dataReceived = ic.cs.courseSelected();
	                       sleep(300);
	                    //   System.out.println("test");
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
	                   }
	                   
	                   dataReceived = false;
	                   ic.edSched.setDataRec(false);

	                   //System.out.println("Save has been pressed" +ic.edSched.newMonStart);

	                   db.storeClassSched(PrefilledScheduleForm.getDefCourseID(), PrefilledScheduleForm.getNewCourseStart(), PrefilledScheduleForm.getNewCourseEnd(), 
	                           PrefilledScheduleForm.getNewMonStart(), PrefilledScheduleForm.getNewMonEnd(), PrefilledScheduleForm.getNewTueStart(), PrefilledScheduleForm.getNewTueEnd(), 
	                           PrefilledScheduleForm.getNewWedStart(), PrefilledScheduleForm.getNewWedEnd(), PrefilledScheduleForm.getNewThuStart(), PrefilledScheduleForm.getNewThuEnd(), 
	                           PrefilledScheduleForm.getNewFriStart(), PrefilledScheduleForm.getNewFriEnd(), PrefilledScheduleForm.getNewSatStart(), PrefilledScheduleForm.getNewSatEnd());


	               }
	               else if(schedSetupSel)
	               {
	                   ic.sched.launchInitial();
	                   //Initial Schedule Setup
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

	               dataReceived = false;
	               
	              // db.disconnect();
	        }
	        
	        
	}
	
    /* Created to parse out MAIN */
    public static boolean checkClear()
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
    
    /* Created to parse out MAIN */
    public static void checkTimes()
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
    
    
    public static void getData(int course)
    {
        defSub = db.fetchCourseSubj(course);
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
    
    
    public static void sleep(int milli)
    {
          try { 
           Thread.sleep(milli);
        } catch (InterruptedException ex) {
           Logger.getLogger(appController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
    public static void LogIn()
    {      
        if(db.connect(username,password) == 0)
            loggedin = true;
        else
            loggedin = false;        
    }
    
    public static DBConnection getCon()
    {
        return db;
    }
    
    
    private static TimerTask dbClear = new TimerTask()
    {
        public void run()
        {
           db.clearDatabase();
        }
    };
    
    private static TimerTask popup15min = new TimerTask()
    {
        public void run()
        {
            ic.msg.FifteenMinWarning();
        }
    };
	
    private static TimerTask popup5min = new TimerTask()
    {
        public void run()
        {
            ic.msg.FiveMinWarning();
        }
    };
    
    
    private static TimerTask endofclass = new TimerTask()
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
    
    public static long getTimeMillis()
    {
    	//setRun = Gregorian calendar
        return setRun.getTimeInMillis();
    }
    
    public static void timerParser(String timer)
    {
        hr = Integer.parseInt(timer.substring(0,2));
        min = Integer.parseInt(timer.substring(3,5));
    }
    public static void dateParser(String timer)
    {
        
        clearMonth = Integer.parseInt(timer.substring(0,2));
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
    
    public static Date getEndTime(int hrs, int mins)
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
        // System.out.println(dayWeek);
        return tempC.getTime();
    }
    
    public static void setSemesterClear(int year, int month, int date, int hours, int mins)
    {
        autoClear.set(year, month, date, hours, mins);
        date2 = autoClear.getTime();
    }
    
    public Date getSemesterClear()
    {
        return autoClear.getTime();
    }
    
    public static Date get15BeforeEnd(int hrs, int mins)
    {
        @SuppressWarnings("unused")
		int years, months, dates, dayOfWeek; 
        
        Calendar tempC = new GregorianCalendar();
        
        tempC.setTimeInMillis(System.currentTimeMillis());
        
        years = tempC.get(Calendar.YEAR);
        months = tempC.get(Calendar.MONTH);
        dates =  tempC.get(Calendar.DATE);
        dayOfWeek = tempC.get(Calendar.DAY_OF_WEEK);
        
        tempC.set(years, months, dates, hrs, mins - 15, 1);
        
        return tempC.getTime();
    }
    
    public static Date get5BeforeEnd(int hrs, int mins)
    {
        @SuppressWarnings("unused")
		int years, months, dates, dayOfWeek;
        
        Calendar tempC = new GregorianCalendar();
        
        tempC.setTimeInMillis(System.currentTimeMillis());
        
        years = tempC.get(Calendar.YEAR);
        months = tempC.get(Calendar.MONTH);
        dates = tempC.get(Calendar.DATE);
        dayOfWeek = tempC.get(Calendar.DAY_OF_WEEK);
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
