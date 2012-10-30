package stubs;

import stubs.Messages;
import stubs.courseSelect;
import stubs.LogOutConfirm;
import stubs.LoginForm;
import stubs.MainMenu;
import stubs.PrefilledScheduleForm;
import stubs.ScheduleForm;


public class InterfaceController {

	 	public Messages msg = new Messages();
	    
	    public LoginForm log = new LoginForm();
	    public ScheduleForm sched = new ScheduleForm();
	    public PrefilledScheduleForm edSched = new PrefilledScheduleForm();
	    public MainMenu mm = new MainMenu();
	    public LogOutConfirm logout = new LogOutConfirm();
	    public courseSelect cs;
	    
	    public InterfaceController()
	    {
	    	/* TODO Instantiate all parameters */
	        
	    }
	    
	    public void passwordLock()
	    {
	        //msg.lockedOut();
	    }
	    public void Initiate_Logout() 
	    {
	        
	         msg.logoutConfirmation();
	     
	    }

	    public void Initiate_IncorrectLogin()
	    {
	        
	        //msg.incorrectLogin();
	        
	    }
	    
	    public void Initiate_Schedule_Form() 
	    {
	       
	       //sched.launchInitial();
	    }

	    public void Pre_Filled_Form(int courseID, String courseSubj, String courseName, String semester,
	            String startDate, String endDate, String startMon, String endMon,
	            String startTue, String endTue, String startWed, String endWed, String startThu, String endThu, 
	            String startFri, String endFri, String startSat, String endSat) 
	    {

	    
	        
	          edSched.launchEdit(courseID, courseSubj, courseName, semester,
	            startDate, endDate, startMon, endMon, startTue, endTue, 
	            startWed, endWed, startThu, endThu, startFri, endFri, startSat, endSat);
	            
	    }
	    
	    public void Course_Select_Form(){
	        cs = new courseSelect();
	        cs.launchCourse();
	    }
	    public void Initiate_Login_Form() {

	       //log.launchForm();

	    }

	    public void Initiate_Message() {

	    }

	    public void Initiate_MainMenu() {
	        //mm.launchForm();

	    }
	}

