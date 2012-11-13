package stubs;

import testUtil.DBUtil;


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
	/*** CEN5076 ***/
	public boolean exit = false;
	
//	public boolean passLogoutRef;
	   
	/** Creates a new instance of Authenticate */
	public Authenticate(String user, String pw) {
		username = user;
		password = pw;
	}
	    
	public boolean validate_Login()
	{
		return (username == DBUtil.USERNAME) && (password == DBUtil.PASSWORD);
	}
	
	public boolean logout()
	{
		return true;
	}
/*
	    public boolean logout(appController app){
	    	
			if(passLogoutRef){
	    		app.setLogOutSel(true);
	    		app.dataReceived = true;
			}
	    	return this.logout();
	    }
*/
}
