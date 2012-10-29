package stubs;

//import Stubs.Messages;

public class Messages {

	 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private static String m = "";
	    private static boolean logout;
	    private static boolean incorrectLogin;
	    private static boolean ack;
	    private static boolean lockedOut;
	    private javax.swing.JButton jButton1;
	    private javax.swing.JLabel jLabel1;
	    public boolean exitTest = false;


	    
	    /** Creates new form Messages */
	    public Messages() {
	    	
	    	Messages.m = "This is a stub Message";
	    	Messages.logout = false;
	    	Messages.incorrectLogin = false;
	    	Messages.ack = true;
	    	Messages.lockedOut= false;
	    }
	    
	    /** Added for Message desplay confirmation ***/
	    
	    public boolean fiveMinuteWarning= false;
	    public boolean fifteenMinuteWarning = false;
	    public boolean endClassWarning = false;
		//public boolean exitTest =false;
	    
	    /** Added CEN5076 
	     * 
	     * @return - the logout value
	     */
	    public boolean getLogOut(){
	    	
	    	return logout;

	    }
	   
	    /**
		 * @return the m
		 */
		public static String getM() {
			return m;
		}

		/**
		 * @param m the m to set
		 */
		public static void setM(String m) {
			Messages.m = m;
		}

		/**
		 * @return the logout
		 */
		public static boolean isLogout() {
			return logout;
		}

		/**
		 * @param logout the logout to set
		 */
		public static void setLogout(boolean logout) {
			Messages.logout = logout;
		}

		/**
		 * @return the incorrectLogin
		 */
		public static boolean isIncorrectLogin() {
			return incorrectLogin;
		}

		/**
		 * @param incorrectLogin the incorrectLogin to set
		 */
		public static void setIncorrectLogin(boolean incorrectLogin) {
			Messages.incorrectLogin = incorrectLogin;
		}

		/**
		 * @return the lockedOut
		 */
		public static boolean isLockedOut() {
			return lockedOut;
		}

		/**
		 * @param lockedOut the lockedOut to set
		 */
		public static void setLockedOut(boolean lockedOut) {
			Messages.lockedOut = lockedOut;
		}

		/**
		 * @return the jButton1
		 */
		public javax.swing.JButton getjButton1() {
			return jButton1;
		}

		/**
		 * @param jButton1 the jButton1 to set
		 */
		public void setjButton1(javax.swing.JButton jButton1) {
			this.jButton1 = jButton1;
		}

		/**
		 * @return the jLabel1
		 */
		public javax.swing.JLabel getjLabel1() {
			return jLabel1;
		}

		/**
		 * @param jLabel1 the jLabel1 to set
		 */
		public void setjLabel1(javax.swing.JLabel jLabel1) {
			this.jLabel1 = jLabel1;
		}

		/**
		 * @return the serialversionuid
		 */
		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public void lockedOut()
	    {
	        
	    }
	    public void incorrectLogin()
	    {
	        
	    }
	    public void FifteenMinWarning()
	    {
	    	System.out.println("FifteenMinute Warning Ran");

	      this.fifteenMinuteWarning = true;
	    }

	    public void FiveMinWarning()
	    {
	    	System.out.println("FiveMinute Warning Ran");
	        this.fiveMinuteWarning = true;
	    }
	    
	    public void endClassWarning()
	    {
	    	System.out.println("EndClass Warning Ran");

	        this.endClassWarning = true;
	        
	    }
	    
	    public void logoutConfirmation()
	    {
	    	if(exitTest)
	    	System.exit(0);
	    	else

	    	logout = true;
	      
	    }
	    
	    @SuppressWarnings("unused")
		private void initComponents() {

	        
	        
	    }

	   
	    
	    @SuppressWarnings("unused")
		private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
	        
	    }
	    
	    /**
	     * @param args the command line arguments
	     */
	    public void launchPopup() {
	        
	    }
	    
	    public static boolean isAck() {



			return ack;
		}

		public static void setAck(boolean ack) {

		}

		
		
	}

