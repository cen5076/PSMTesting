package stubs;

import java.util.ArrayList;

import testUtil.DBUtil;

//import javax.swing.JButton;
//import javax.swing.JComboBox;

//import Logic.appController;
@SuppressWarnings("unused")

public class courseSelect {
   private static final long serialVersionUID = 1L;
	/** Creates new form courseSelect2 */
   //DBConnection Data = new DBConnection();
   
   public static int selection;
   private static boolean courseSelected;
   public String [] listCourses;
   private javax.swing.JButton jButton1;
   private javax.swing.JButton jButton2;
   @SuppressWarnings("rawtypes")
   private javax.swing.JComboBox jComboBox1;
   private javax.swing.JPanel jPanel1;
   
   

   
@SuppressWarnings("rawtypes")
public courseSelect() {
       /* TODO Initialize Course list */
	   courseSelect.selection = DBUtil.defaultCourseId;
	   courseSelect.courseSelected = true;
	   /*this.jButton1 = new javax.swing.JButton();
	   this.jButton2 = new javax.swing.JButton();
	   this.jComboBox1 = new javax.swing.JComboBox();
	   this.jPanel1 = new javax.swing.JPanel();
	   for (int i=0;i<5;i++){
		   this.listCourses[i]="Course"+i;
	   }
	   */
    }
    
   /**
 * @return the listCourses
 */
public String[] getListCourses() {
	return listCourses;
}

/**
 * @param listCourses the listCourses to set
 */
public void setListCourses(String[] listCourses) {
	this.listCourses = listCourses;
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
 * @return the jButton2
 */
public javax.swing.JButton getjButton2() {
	return jButton2;
}

/**
 * @param jButton2 the jButton2 to set
 */
public void setjButton2(javax.swing.JButton jButton2) {
	this.jButton2 = jButton2;
}

/**
 * @return the jComboBox1
 */
@SuppressWarnings("rawtypes")
public javax.swing.JComboBox getjComboBox1() {
	return jComboBox1;
}

/**
 * @param jComboBox1 the jComboBox1 to set
 */

public void setjComboBox1(@SuppressWarnings("rawtypes") javax.swing.JComboBox jComboBox1) {
	this.jComboBox1 = jComboBox1;
}

/**
 * @return the jPanel1
 */
public javax.swing.JPanel getjPanel1() {
	return jPanel1;
}

/**
 * @param jPanel1 the jPanel1 to set
 */
public void setjPanel1(javax.swing.JPanel jPanel1) {
	this.jPanel1 = jPanel1;
}

/**
 * @return the serialversionuid
 */
public static long getSerialversionuid() {
	return serialVersionUID;
}

/**
 * @return the courseSelected
 */
public static boolean isCourseSelected() {
	return courseSelected;
}

/**
 * @param selection the selection to set
 */
public static void setSelection(int s) {
	courseSelect.selection = s;
	System.out.println("Selection=" + courseSelect.selection);
}

public int getSelection()
    {
        return courseSelect.selection;
    }

   
   public boolean courseSelected()
    {
        return courseSelected;
    }
    
    public void setCourseSelected(boolean condition)
    {
    	courseSelected = condition;
    }
    
	private void initComponents() {

            }

	private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

    }

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
    }  
    
    public void launchCourse() {
      
    }
    
 
}
