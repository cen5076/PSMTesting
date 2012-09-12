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
	    
	    

}
