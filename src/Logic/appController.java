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

import DataStore.DBConnection;
import Interface.InterfaceController;
import Interface.Messages;
import Interface.PrefilledScheduleForm;
import Interface.ScheduleForm;


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
	 * @param hr the hr to set
	 */
	public static void setHr(int hr) {
		appController.hr = hr;
	}

	/**
	 * @return the min
	 */
	public int getMin() {
		return min;
	}

	/**
	 * @param min the min to set
	 */
	public static void setMin(int min) {
		appController.min = min;
	}

	/**
	 * @return the autoClear
	 */
	public static Calendar getAutoClear() {
		return autoClear;
	}

	/**
	 * @param autoClear the autoClear to set
	 */
	public static void setAutoClear(Calendar autoClear) {
		appController.autoClear = autoClear;
	}

	/**
	 * @return the setRun
	 */
	public static Calendar getSetRun() {
		return setRun;
	}

	/**
	 * @param setRun the setRun to set
	 */
	public static void setSetRun(Calendar setRun) {
		appController.setRun = setRun;
	} 

	/**
	 * @return the timer
	 */
	public static Timer getTimer() {
		return timer;
	}

	/**
	 * @param timer the timer to set
	 */
	public static void setTimer(Timer timer) {
		appController.timer = timer;
	}

	/**
	 * @return the date
	 */
	public static Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public static void setDate(Date date) {
		appController.date = date;
	}

	/**
	 * @return the date2
	 */
	public static Date getDate2() {
		return date2;
	}

	/**
	 * @param date2 the date2 to set
	 */
	public static void setDate2(Date date2) {
		appController.date2 = date2;
	}

	/**
	 * @return the defSub
	 */
	public String getDefSub() {
		return defSub;
	}

	/**
	 * @param defSub the defSub to set
	 */
	public static void setDefSub(String defSub) {
		appController.defSub = defSub;
	}

	/**
	 * @return the defSemester
	 */
	public static String getDefSemester() {
		return defSemester;
	}

	/**
	 * @param defSemester the defSemester to set
	 */
	public static void setDefSemester(String defSemester) {
		appController.defSemester = defSemester;
	}

	/**
	 * @return the defCourseName
	 */
	public static String getDefCourseName() {
		return defCourseName;
	}

	/**
	 * @param defCourseName the defCourseName to set
	 */
	public static void setDefCourseName(String defCourseName) {
		appController.defCourseName = defCourseName;
	}

	/**
	 * @return the defCourseStart
	 */
	public static String getDefCourseStart() {
		return defCourseStart;
	}

	/**
	 * @param defCourseStart the defCourseStart to set
	 */
	public static void setDefCourseStart(String defCourseStart) {
		appController.defCourseStart = defCourseStart;
	}

	/**
	 * @return the defCourseEnd
	 */
	public static String getDefCourseEnd() {
		return defCourseEnd;
	}

	/**
	 * @param defCourseEnd the defCourseEnd to set
	 */
	public static void setDefCourseEnd(String defCourseEnd) {
		appController.defCourseEnd = defCourseEnd;
	}

	/**
	 * @return the defMonStart
	 */
	public static String getDefMonStart() {
		return defMonStart;
	}

	/**
	 * @param defMonStart the defMonStart to set
	 */
	public static void setDefMonStart(String defMonStart) {
		appController.defMonStart = defMonStart;
	}

	/**
	 * @return the defMonEnd
	 */
	public static String getDefMonEnd() {
		return defMonEnd;
	}

	/**
	 * @param defMonEnd the defMonEnd to set
	 */
	public static void setDefMonEnd(String defMonEnd) {
		appController.defMonEnd = defMonEnd;
	}

	/**
	 * @return the defTueStart
	 */
	public static String getDefTueStart() {
		return defTueStart;
	}

	/**
	 * @param defTueStart the defTueStart to set
	 */
	public static void setDefTueStart(String defTueStart) {
		appController.defTueStart = defTueStart;
	}

	/**
	 * @return the defTueEnd
	 */
	public static String getDefTueEnd() {
		return defTueEnd;
	}

	/**
	 * @param defTueEnd the defTueEnd to set
	 */
	public static void setDefTueEnd(String defTueEnd) {
		appController.defTueEnd = defTueEnd;
	}

	/**
	 * @return the defWedStart
	 */
	public static String getDefWedStart() {
		return defWedStart;
	}

	/**
	 * @param defWedStart the defWedStart to set
	 */
	public static void setDefWedStart(String defWedStart) {
		appController.defWedStart = defWedStart;
	}

	/**
	 * @return the defWedEnd
	 */
	public static String getDefWedEnd() {
		return defWedEnd;
	}

	/**
	 * @param defWedEnd the defWedEnd to set
	 */
	public static void setDefWedEnd(String defWedEnd) {
		appController.defWedEnd = defWedEnd;
	}

	/**
	 * @return the defThuStart
	 */
	public static String getDefThuStart() {
		return defThuStart;
	}

	/**
	 * @param defThuStart the defThuStart to set
	 */
	public static void setDefThuStart(String defThuStart) {
		appController.defThuStart = defThuStart;
	}

	/**
	 * @return the defThuEnd
	 */
	public static String getDefThuEnd() {
		return defThuEnd;
	}

	/**
	 * @param defThuEnd the defThuEnd to set
	 */
	public static void setDefThuEnd(String defThuEnd) {
		appController.defThuEnd = defThuEnd;
	}

	/**
	 * @return the defFriStart
	 */
	public static String getDefFriStart() {
		return defFriStart;
	}

	/**
	 * @param defFriStart the defFriStart to set
	 */
	public static void setDefFriStart(String defFriStart) {
		appController.defFriStart = defFriStart;
	}

	/**
	 * @return the defFriEnd
	 */
	public static String getDefFriEnd() {
		return defFriEnd;
	}

	/**
	 * @param defFriEnd the defFriEnd to set
	 */
	public static void setDefFriEnd(String defFriEnd) {
		appController.defFriEnd = defFriEnd;
	}

	/**
	 * @return the defSatStart
	 */
	public static String getDefSatStart() {
		return defSatStart;
	}

	/**
	 * @param defSatStart the defSatStart to set
	 */
	public static void setDefSatStart(String defSatStart) {
		appController.defSatStart = defSatStart;
	}

	/**
	 * @return the defSatEnd
	 */
	public static String getDefSatEnd() {
		return defSatEnd;
	}

	/**
	 * @param defSatEnd the defSatEnd to set
	 */
	public static void setDefSatEnd(String defSatEnd) {
		appController.defSatEnd = defSatEnd;
	}

	/**
	 * @return the username
	 */
	public static String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public static void setUsername(String username) {
		appController.username = username;
	}

	/**
	 * @return the password
	 */
	public static String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public static void setPassword(String password) {
		appController.password = password;
	}

	/**
	 * @return the loggedin
	 */
	public static boolean isLoggedin() {
		return loggedin;
	}

	/**
	 * @param loggedin the loggedin to set
	 */
	public static void setLoggedin(boolean loggedin) {
		appController.loggedin = loggedin;
	}

	/**
	 * @return the dataReceived
	 */
	public static boolean isDataReceived() {
		return dataReceived;
	}

	/**
	 * @param dataReceived the dataReceived to set
	 */
	public static void setDataReceived(boolean dataReceived) {
		appController.dataReceived = dataReceived;
	}

	/**
	 * @return the edSchedSel
	 */
	public static boolean isEdSchedSel() {
		return edSchedSel;
	}

	/**
	 * @param edSchedSel the edSchedSel to set
	 */
	public static void setEdSchedSel(boolean edSchedSel) {
		appController.edSchedSel = edSchedSel;
	}

	/**
	 * @return the schedSetupSel
	 */
	public static boolean isSchedSetupSel() {
		return schedSetupSel;
	}

	/**
	 * @param schedSetupSel the schedSetupSel to set
	 */
	public static void setSchedSetupSel(boolean schedSetupSel) {
		appController.schedSetupSel = schedSetupSel;
	}

	/**
	 * @return the logoutSel
	 */
	public static boolean isLogoutSel() {
		return logoutSel;
	}

	/**
	 * @param logoutSel the logoutSel to set
	 */
	public static void setLogoutSel(boolean logoutSel) {
		appController.logoutSel = logoutSel;
	}

	/**
	 * @return the clearDate
	 */
	public static int getClearDate() {
		return clearDate;
	}

	/**
	 * @param clearDate the clearDate to set
	 */
	public static void setClearDate(int clearDate) {
		appController.clearDate = clearDate;
	}

	/**
	 * @return the clearMonth
	 */
	public static int getClearMonth() {
		return clearMonth;
	}

	/**
	 * @param clearMonth the clearMonth to set
	 */
	public static void setClearMonth(int clearMonth) {
		appController.clearMonth = clearMonth;
	}

	/**
	 * @return the clearYear
	 */
	public static int getClearYear() {
		return clearYear;
	}

	/**
	 * @param clearYear the clearYear to set
	 */
	public static void setClearYear(int clearYear) {
		appController.clearYear = clearYear;
	}

	/**
	 * @return the counter
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * @param counter the counter to set
	 */
	public static void setCounter(int counter) {
		appController.counter = counter;
	}

	/**
	 * @return the db
	 */
	public static DBConnection getDb() {
		return db;
	}

	/**
	 * @param db the db to set
	 */
	public static void setDb(DBConnection db) {
		appController.db = db;
	}

	/**
	 * @return the ic
	 */
	public static InterfaceController getIc() {
		return ic;
	}

	/**
	 * @param ic the ic to set
	 */
	public static void setIc(InterfaceController ic) {
		appController.ic = ic;
	}

	/**
	 * @return the auth
	 */
	public static Authenticate getAuth() {
		return auth;
	}

	/**
	 * @param auth the auth to set
	 */
	public static void setAuth(Authenticate auth) {
		appController.auth = auth;
	}

	/**
	 * @return the courseSel
	 */
	public static int getCourseSel() {
		return courseSel;
	}

	/**
	 * @param courseSel the courseSel to set
	 */
	public static void setCourseSel(int courseSel) {
		appController.courseSel = courseSel;
	}

	/**
	 * @return the classEnded
	 */
	public static long getClassEnded() {
		return classEnded;
	}

	/**
	 * @param classEnded the classEnded to set
	 */
	public static void setClassEnded(long classEnded) {
		appController.classEnded = classEnded;
	}

	/**
	 * @return the dbClear
	 */
	public static TimerTask getDbClear() {
		return dbClear;
	}

	/**
	 * @param dbClear the dbClear to set
	 */
	public static void setDbClear(TimerTask dbClear) {
		appController.dbClear = dbClear;
	}

	/**
	 * @return the popup15min
	 */
	public static TimerTask getPopup15min() {
		return popup15min;
	}

	/**
	 * @param popup15min the popup15min to set
	 */
	public static void setPopup15min(TimerTask popup15min) {
		appController.popup15min = popup15min;
	}

	/**
	 * @return the popup5min
	 */
	public static TimerTask getPopup5min() {
		return popup5min;
	}

	/**
	 * @param popup5min the popup5min to set
	 */
	public static void setPopup5min(TimerTask popup5min) {
		appController.popup5min = popup5min;
	}

	/**
	 * @return the endofclass
	 */
	public static TimerTask getEndofclass() {
		return endofclass;
	}

	/**
	 * @param endofclass the endofclass to set
	 */
	public static void setEndofclass(TimerTask endofclass) {
		appController.endofclass = endofclass;
	}

	/**
	 * @return the systemExit
	 */
	public static TimerTask getSystemExit() {
		return systemExit;
	}

	/**
	 * @param systemExit the systemExit to set
	 */
	public static void setSystemExit(TimerTask systemExit) {
		appController.systemExit = systemExit;
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
    	
       
       while(!loggedin)
       {    
    	   
    	   
           ic.Initiate_Login_Form();            
           
            do
            {
                dataReceived = ic.log.dataReceived();
                sleep(300);
                
            }while(!dataReceived);
            
            tryLogin();
       }
	   
       
       ic.Initiate_MainMenu();
       
       if(checkClear())
       {
           db.clearDatabase();
       }
       
       checkTimes();
       while(!logoutSel)
       {
           
        /*   
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
           */
    	   checkSchedule();
          // db.disconnect();
       }
       //end
       
    }
    
    private static void checkSchedule(){
    	
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
    private static void tryLogin(){
    	
    	
    	
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
