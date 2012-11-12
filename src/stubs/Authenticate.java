package stubs;

import Logic.appController;


/*
 * *** STUB CLASS ***
 * Authenticate.java
 *
 * Created on April 8, 2008, 3:25 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 * 
 * @author David Garcia
 *
 */
public class Authenticate {


	    
	    String username;
	    String password;
	    DBConnection db;
	    /*** CEN5076 ***/
	    public boolean exit = false;

    	public boolean passLogoutRef;
	   
	    /** Creates a new instance of Authenticate */
	    public Authenticate(String user, String pw) {
	        username = user;
	        password = pw;
	        db = new DBConnection();
	        
	    }
	    
	    public boolean validate_Login()
	    {
	        int state; 
	        state = db.connect(username,password);          // connect to default database
	        if(state == 0)
	            return true;
	        else
	            return false;   
	    }
	    
	    public boolean logout()
	    {
	        int state;
	        state = db.disconnect();

	        if(state == 0)
	            return true;
	        else
	            return false;
	    }
	    
	    public boolean logout(appController app){
	    	
			if(passLogoutRef){
	    		app.setLogOutSel(true);
	    		app.dataReceived = true;
			}
	    	return this.logout();
	    }
	    
	}

