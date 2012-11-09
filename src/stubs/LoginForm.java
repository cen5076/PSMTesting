package stubs;

import testUtil.DBUtil;

@SuppressWarnings("unused")
public class LoginForm {

	private static final long serialVersionUID = 1L;

	public boolean loggedin;
    private boolean dataRec;
    private String username;
    private String password;
    private boolean loginStateFlag = false;
    

    public void setLoginStateFlag(boolean b) {
    	loginStateFlag = b;
    }
    
    
    public LoginForm() {
    	/* TODO Initialize variables */
    	loggedin = false;
    	dataRec = true;
    	username = DBUtil.USERNAME;
    	password = DBUtil.PASSWORD;
    }
    
    private void initComponents() {


    }

    public boolean dataReceived()
    {
    	if (loginStateFlag) {
    		setLoginStateFlag(false);
    		return false;
    	}
        return dataRec;
    }
    
    public void setDataRec(boolean condition)
    {
        dataRec = condition;
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
    }
    
    public void launchForm() {
       
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public void setUsername(String usr)
    {
        username = usr;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String pass)
    {
        password = pass;
    }
}
