
package stubs;

import testUtil.Course;


@SuppressWarnings({ "unused"})
public class ScheduleForm extends javax.swing.JFrame {
    
      /**
	 * 
	 */
	private static final long serialVersionUID = 2558662358067607L;
	/**
	 * 
	 */
	
	private static int newCourseID;
      private static String newSub;
      private static String newSemester;
      private static String newCourseName;
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

      public static boolean dataReceived;
     
    
    /** Creates new form ScheduleSetupUI2 */
    public ScheduleForm() {

    }
    
    /* Added Helper Method for Testing CEN5076 - DG */
    public void addCourse(Course c){
    	
    	newCourseID = c.crseid;
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
    
    public boolean dataRec()
    {
        return dataReceived;
    }
    public void setDataRec(boolean condition)
    {
        dataReceived = condition;       
    }
      
      
	private void initComponents() {


    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    }
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
    }
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
    }

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
    }

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
    }

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
    }

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
    }

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
    }

    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField12ActionPerformed
    }

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
    }
    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
    }
    private void jTextField15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField15ActionPerformed
    }
    
    /**
     * @param args the command line arguments
     */
    public void launchInitial() {
        
    }
    
    public static int getNewCourseID() {
		return newCourseID;
	}

	public static void setNewCourseID(int newCourseID) {
	}

	public static String getNewSub() {
		return newSub;
	}

	public static void setNewSub(String newSub) {
		ScheduleForm.newSub = newSub;
	}

	public static String getNewCourseStart() {
		return newCourseStart;
	}

	public static void setNewCourseStart(String newCourseStart) {
		ScheduleForm.newCourseStart = newCourseStart;
	}

	public static String getNewMonStart() {
		return newMonStart;
	}

	public static void setNewMonStart(String newMonStart) {
		ScheduleForm.newMonStart = newMonStart;
	}

	public static String getNewMonEnd() {
		return newMonEnd;
	}

	public static void setNewMonEnd(String newMonEnd) {
		ScheduleForm.newMonEnd = newMonEnd;
	}

	public static String getNewSemester() {
		return newSemester;
	}

	public static void setNewSemester(String newSemester) {
		ScheduleForm.newSemester = newSemester;
	}

	public static String getNewWedStart() {
		return newWedStart;
	}

	public static void setNewWedStart(String newWedStart) {
		ScheduleForm.newWedStart = newWedStart;
	}

	public static String getNewFriStart() {
		return newFriStart;
	}

	public static void setNewFriStart(String newFriStart) {
		ScheduleForm.newFriStart = newFriStart;
	}

	public static String getNewFriEnd() {
		return newFriEnd;
	}

	public static void setNewFriEnd(String newFriEnd) {
		ScheduleForm.newFriEnd = newFriEnd;
	}

	public static String getNewWedEnd() {
		return newWedEnd;
	}

	public static void setNewWedEnd(String newWedEnd) {
		ScheduleForm.newWedEnd = newWedEnd;
	}

	public static String getNewThuStart() {
		return newThuStart;
	}

	public static void setNewThuStart(String newThuStart) {
		ScheduleForm.newThuStart = newThuStart;
	}

	public static String getNewSatStart() {
		return newSatStart;
	}

	public static void setNewSatStart(String newSatStart) {
		ScheduleForm.newSatStart = newSatStart;
	}

	public static String getNewTueStart() {
		return newTueStart;
	}

	public static void setNewTueStart(String newTueStart) {
		ScheduleForm.newTueStart = newTueStart;
	}

	public static String getNewTueEnd() {
		return newTueEnd;
	}

	public static void setNewTueEnd(String newTueEnd) {
		ScheduleForm.newTueEnd = newTueEnd;
	}

	public static String getNewThuEnd() {
		return newThuEnd;
	}

	public static void setNewThuEnd(String newThuEnd) {
		ScheduleForm.newThuEnd = newThuEnd;
	}

	public static String getNewSatEnd() {
		return newSatEnd;
	}

	public static void setNewSatEnd(String newSatEnd) {
		ScheduleForm.newSatEnd = newSatEnd;
	}

	public static String getNewCourseName() {
		return newCourseName;
	}

	public static void setNewCourseName(String newCourseName) {
		ScheduleForm.newCourseName = newCourseName;
	}

	public static String getNewCourseEnd() {
		return newCourseEnd;
	}

	public static void setNewCourseEnd(String newCourseEnd) {
		ScheduleForm.newCourseEnd = newCourseEnd;
	}
}
