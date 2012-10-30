package stubs;

public class MainMenu {

		private static boolean dataRec;
	    private static boolean editSched;
	    private static boolean logout;
	    private static boolean initSetup;
	    public MainMenu() {
	    
	    }
	    public void launchForm() {
	    }
	    
	    public void setdataRec(boolean condition)
	    {
	        dataRec = condition;
	    }
	    public boolean dataRec()
	    {
	        return dataRec;
	    }
	    
	    public boolean logoutSelected()
	    {
	    	this.logout = (!this.logout);
	        return (!logout);
	    }
	    
	    public boolean InitSetupSelected()
	    {
	        return initSetup;
	    }
	    
	    public boolean editSchedSelected()
	    {
	        return editSched;
	    }
	    
	    /* Added for Testing CEN5076 */
	    
	    /**
	     * 
	     * @param d - set dataRec to simulate data Received
	     * 
	     */
	    public void setDataRec(Boolean d){
	    	
	    	this.dataRec = d;
	    }
	    
	    /**
	     * 
	     * 
	     * @param e - set the editSchedul to simulate edit schedule button
	     */
	    public void setEditSched(Boolean e){
	    	this.editSched = e;
	    }

	    /**
	     * 
	     * @param l - set the logout to simulate the logout schedule button
	     */
	    public void setLogout(Boolean l){
	    	this.logout = l;
	    }
	    
	    /**
	     * 
	     * @param i - set the initsetup to simulate the initsetup button
	     */
	    public void setInitSetup(Boolean i){
	    	this.initSetup = i;
	    }
	    

}
