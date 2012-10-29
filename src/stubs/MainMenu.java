package stubs;

public class MainMenu {

		public static boolean dataRec;
	    private static boolean editSched;
	    private static boolean logout;
	    private static boolean initSetup;
		public boolean togglelogout=false;
		public boolean alwaystrue = false;
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
	    	if(alwaystrue)
	    		return true;
	    	else
	    		return dataRec;
	    }
	    
	    public boolean logoutSelected()
	    {
	    	if(this.togglelogout){
	    	this.logout = (!this.logout);
	        return (!logout);
	    	}
	    	return logout;
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
