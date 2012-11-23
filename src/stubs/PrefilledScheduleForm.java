package stubs;

import testUtil.Course;

public class PrefilledScheduleForm {

//	private static final long serialVersionUID = 1L;
	private static int defCourseID;
	static  String defSub;
	static  String defSemester;
	static  String defCourseName;
	static  String defCourseStart;
	static  String defCourseEnd;
	static  String defMonStart;
	static  String defMonEnd;
	static  String defTueStart;
	static  String defTueEnd;
	static  String defWedStart;
	static  String defWedEnd;
	static  String defThuStart;
	static  String defThuEnd;
	static  String defFriStart;
	static  String defFriEnd;
	static  String defSatStart;
	static  String defSatEnd;
      
	static int newCourseID;
	static String newSub;
	static String newSemester;
	static String newCourseName;
	private static String newCourseStart;
	private static String newCourseEnd;
	private static String newMonStart;
	private static String newMonEnd;
	private static String newTueStart;
	private static String newTueEnd;
	private static String newWedStart;
	private static String newWedEnd;
	private static String newThuStart;
	private static String newThuEnd;
	private static String newFriStart;
	private static String newFriEnd;
	private static String newSatStart;
	private static String newSatEnd;
      
	static boolean dataReceived;
        
    /** Creates new form EditScheduleSetupUI2 */
    public PrefilledScheduleForm() {
       /* TODO Initialize Values */
    }
    public boolean dataRec()
    {
        return dataReceived;
    }
    public void setDataRec(boolean condition)
    {
       
        dataReceived = condition; 
        
    }
    
	/**
     * ***STUBS ***
     * Added method for Testing CEN5076
     */
    public void addCourse(Course c){
    	
    	defCourseID = c.crseid;
    	newCourseName = c.crseNam;
    	newSub = c.crseSub;
    	newSemester = c.crseSub;
    	newCourseStart = c.startdt;
    	newCourseEnd = c.enddt;
    	newMonStart = c.monStart;
    	newMonEnd = c.monEnd;
    	newTueEnd = c.tueEnd;
    	newTueStart = c.tueStart;
    	newWedStart = c.wedStart;
    	newWedEnd = c.wedEnd;
    	newThuStart = c.thuStart;
    	newThuEnd = c.thuEnd;
    	newFriStart = c.friStart;
    	newFriEnd = c.friEnd;
    	newSatStart = c.satStart;
    	newSatEnd = c.satEnd;
    	
    }
    
	
  //  private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
   // }
    
    public void launchEdit(int courseID, String courseSubj, String courseName, String semester,
            String startDate, String endDate, String startMon, String endMon,
            String startTue, String endTue, String startWed, String endWed, String startThu, String endThu, 
            String startFri, String endFri, String startSat, String endSat) 
    {
        setDefCourseID(courseID);
        defSub = courseSubj;
        defSemester = semester;
        defCourseName = courseName;
        defCourseStart = startDate;
        defCourseEnd = endDate;
        defMonStart = startMon;
        defMonEnd = endMon;
        defTueStart = startTue;
        defTueEnd = endTue;
        defWedStart = startWed;
        defWedEnd = endWed;
        defThuStart = startThu;
        defThuEnd = endThu;
        defFriStart = startFri;
        defFriEnd = endFri;
        defSatStart = startSat;
        defSatEnd = endSat;
       
    }
    
    public static String getNewCourseEnd() {
		return newCourseEnd;
	}
	public static void setNewCourseEnd(String newCourseEnd) {
		PrefilledScheduleForm.newCourseEnd = newCourseEnd;
	}

	public static int getDefCourseID() {
		return defCourseID;
	}
	public static void setDefCourseID(int defCourseID) {
		PrefilledScheduleForm.defCourseID = defCourseID;
	}

	public static String getNewCourseStart() {
		return newCourseStart;
	}
	public static void setNewCourseStart(String newCourseStart) {
		PrefilledScheduleForm.newCourseStart = newCourseStart;
	}

	public static String getNewThuStart() {
		return newThuStart;
	}
	public static void setNewThuStart(String newThuStart) {
		PrefilledScheduleForm.newThuStart = newThuStart;
	}

	public static String getNewMonStart() {
		return newMonStart;
	}
	public static void setNewMonStart(String newMonStart) {
		PrefilledScheduleForm.newMonStart = newMonStart;
	}

	public static String getNewMonEnd() {
		return newMonEnd;
	}
	public static void setNewMonEnd(String newMonEnd) {
		PrefilledScheduleForm.newMonEnd = newMonEnd;
	}

	public static String getNewTueStart() {
		return newTueStart;
	}
	public static void setNewTueStart(String newTueStart) {
		PrefilledScheduleForm.newTueStart = newTueStart;
	}

	public static String getNewTueEnd() {
		return newTueEnd;
	}
	public static void setNewTueEnd(String newTueEnd) {
		PrefilledScheduleForm.newTueEnd = newTueEnd;
	}

	public static String getNewWedStart() {
		return newWedStart;
	}
	public static void setNewWedStart(String newWedStart) {
		PrefilledScheduleForm.newWedStart = newWedStart;
	}

	public static String getNewWedEnd() {
		return newWedEnd;
	}
	public static void setNewWedEnd(String newWedEnd) {
		PrefilledScheduleForm.newWedEnd = newWedEnd;
	}

	public static String getNewFriStart() {
		return newFriStart;
	}
	public static void setNewFriStart(String newFriStart) {
		PrefilledScheduleForm.newFriStart = newFriStart;
	}

	public static String getNewFriEnd() {
		return newFriEnd;
	}
	public static void setNewFriEnd(String newFriEnd) {
		PrefilledScheduleForm.newFriEnd = newFriEnd;
	}

	public static String getNewSatStart() {
		return newSatStart;
	}
	public static void setNewSatStart(String newSatStart) {
		PrefilledScheduleForm.newSatStart = newSatStart;
	}

	public static String getNewThuEnd() {
		return newThuEnd;
	}
	public static void setNewThuEnd(String newThuEnd) {
		PrefilledScheduleForm.newThuEnd = newThuEnd;
	}

	public static String getNewSatEnd() {
		return newSatEnd;
	}
	public static void setNewSatEnd(String newSatEnd) {
		PrefilledScheduleForm.newSatEnd = newSatEnd;
	}


}
