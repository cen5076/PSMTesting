package stubs;

import testUtil.DBUtil;

@SuppressWarnings("unused")
public class LoginForm {

	private static final long serialVersionUID = 1L;

	public boolean loggedin;
    public boolean dataRec;
    public String username;
    public String password;
    

    
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
    
    public String getPassword()
    {
        return password;
    }
    
    
}
