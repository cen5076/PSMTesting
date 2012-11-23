package testUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * This class encapsulates all the properties  of a course as stored in the database.
 * Used for convenience of moving course properties. Mimics necessary database accesses
 * and has convenience methods for use in classes that call this object.
 * 
 * @author David
 *
 */
public class Course {
	
	public int crseid;
	public String crseSub;
	public String crseNam;
	public String semester;
	public String startdt;
	public String enddt;
	public String monStart;
	public String monEnd;
	public String tueStart;
	public String tueEnd;
	public String wedStart;
	public String wedEnd;
	public String thuStart;
	public String thuEnd;
	public String friStart;
	public String friEnd;
	public String satStart;
	public String satEnd;
	
	/* Default static values to use for start and end dates */
	public static final String STARTDATE = "01/01/13";
	public static final String ENDDATE = "01/02/13";
	
	/* List of the values for the days */
	public static final List<String> DAYS = Arrays.asList("mon", "tue", "wed", "thu", "fri", "sat");
	public static final List<String> DEFAULT_TIMES = Arrays.asList("12:00","13:00","12:00","13:00","12:00","13:00",
		"12:00","13:00","12:00","13:00","12:00","13:00");
	public static final List<String> NO_TIMES = Arrays.asList("","","","","","","","","","","","");
	
	/**
	 *Constructor for Course.
	 */
	public Course(){
		
	}
	
	/**
	 * Constructor with values for the id and other properties but days uses the default stored values.
	 * 
	 * @param id
	 * @param sub
	 * @param nam
	 * @param sem
	 * @param start
	 * @param end
	 */
	public Course(int id, String sub, String nam, String sem, String start, String end){
		
		//calls default to init days
		this();
		this.crseid = id;
		this.crseNam = nam;
		this.crseSub = sub;
		this.semester = sem;
		this.startdt = start;
		this.enddt = end;
		this.monStart = DEFAULT_TIMES.get(0);
		this.monEnd = DEFAULT_TIMES.get(1);
		this.tueStart = DEFAULT_TIMES.get(2);
		this.tueEnd = DEFAULT_TIMES.get(3);
		this.wedStart = DEFAULT_TIMES.get(4);
		this.wedEnd = DEFAULT_TIMES.get(5);
		this.thuStart = DEFAULT_TIMES.get(6);
		this.thuEnd = DEFAULT_TIMES.get(7);
		this.friStart = DEFAULT_TIMES.get(8);
		this.friEnd = DEFAULT_TIMES.get(9);
		this.satStart = DEFAULT_TIMES.get(10);
		this.satEnd = DEFAULT_TIMES.get(11);

		
	}
	
	/**
	 * Constructor to not use the default values for the days.
	 * @author - David Garcia
	 * 
	 * @param id
	 * @param sub
	 * @param nam
	 * @param sem
	 * @param start
	 * @param end
	 * @param mons
	 * @param mone
	 * @param tues
	 * @param tuee
	 * @param weds
	 * @param wede
	 * @param thus
	 * @param thue
	 * @param fris
	 * @param frie
	 * @param sats
	 * @param sate
	 */
	public Course(int id, String sub, String nam, String sem, String start, String end, String mons, String mone, String tues, String tuee, String weds, String wede,String thus, String thue, String fris, String frie, String sats, String sate){
		
		this(id,sub,nam,sem,start,end);
		
		this.monStart = mons;
		this.monEnd = mone;
		this.tueEnd = tuee;
		this.tueStart = tues;
		this.wedEnd = wede;
		this.wedStart = weds;
		this.thuEnd = thue;
		this.thuStart = thus;
		this.friEnd = frie;
		this.friStart = fris;
		this.satEnd = sate;
		this.satStart = sats;
	}
	
	/**
	 * Returns the field names, used to facilitate sql statements.
	 * 
	 * @return - a string array with one value of the field name for each field
	 * that would be in the database.
	 */
	public List<String> getFieldNames() {
		
		ArrayList<String> fields = new ArrayList<String>();
		
		fields.add("course_id");
		fields.add("course_subject");
		fields.add("course_name");
		fields.add("semester");
		fields.add("start_date");
		fields.add("end_date");
		
		for (String day : DAYS) {
			fields.add("start_" + day);
			fields.add("end_" + day);
		}
		
		return fields;
		
	}
	
	/**
	 * returns the values of the course as an ArrayList.
	 * 
	 * @return ArrayList<String> values
	 */
	public ArrayList<String> getValues(){
		
		ArrayList<String> vals = new ArrayList<String>();
		vals.add(String.valueOf(this.crseid));
		vals.add(this.crseSub);
		vals.add(this.crseNam);
		vals.add(this.semester);
		vals.add(this.startdt);
		vals.add(this.enddt);
		vals.add(this.monStart);
		vals.add(this.monEnd);
		vals.add(this.tueStart);
		vals.add(this.tueEnd);
		vals.add(this.wedStart);
		vals.add(this.wedEnd);
		vals.add(this.thuStart);
		vals.add(this.thuEnd);
		vals.add(this.friStart);
		vals.add(this.friEnd);
		vals.add(this.satStart);
		vals.add(this.satEnd);
		
		return vals;
		
	} 
	
	/**
	 * Set the values for the dates based on the array
	 * 
	 * @param times - list with sequence of times monStart, monEnd, tueStart, etc.
	 * @return - true if added successfully
	 */
	public boolean fillTimes(List<String> times) {
		
		if(times.size() != 12)
			return false;
		this.monStart = times.get(0);
		this.monEnd = times.get(1);
		this.tueStart = times.get(2);
		this.tueEnd = times.get(3);
		this.wedStart = times.get(4);
		this.wedEnd = times.get(5);
		this.thuStart = times.get(6);
		this.thuEnd = times.get(7);
		this.friStart = times.get(8);
		this.friEnd = times.get(9);
		this.satStart = times.get(10);
		this.satEnd = times.get(11);
		
		return true;
	}
	
	/**
	 * returns an array of only the endDates for each day.
	 * 
	 * @return - ArrayList<String> ends
	 */
	public ArrayList<String> getEndTimes(){
		
		ArrayList<String> ends = new ArrayList<String>();
		
		ends.add(this.monEnd);
		ends.add(this.tueEnd);
		ends.add(this.wedEnd);
		ends.add(this.thuEnd);
		ends.add(this.friEnd);
		ends.add(this.satEnd);
		
		return ends;
		
	}
	
	/**
	 * Clear all values.
	 * 
	 * @return
	 */
	public boolean clear(){

		this.crseid = 0;
		this.crseNam = "";
		this.crseSub = "";
		this.enddt = "";
		this.startdt = "";
		this.monEnd = "";
		this.monStart = "";
		this.tueEnd = "";
		this.tueStart = "";
		this.wedEnd = "";
		this.wedStart= "";
		this.thuEnd = "";
		this.thuStart = "";
		this.friEnd = "";
		this.friStart = "";
		this.satEnd = "";
		this.satStart = "";
		
		return true;
	}

	/**
	 * @return the crseid
	 */
	public int getCrseid() {
		return crseid;
	}

	/**
	 * @param crseid the crseid to set
	 */
	public void setCrseid(int crseid) {
		this.crseid = crseid;
	}

	/**
	 * @return the crseSub
	 */
	public String getCrseSub() {
		return crseSub;
	}

	/**
	 * @param crseSub the crseSub to set
	 */
	public void setCrseSub(String crseSub) {
		this.crseSub = crseSub;
	}

	/**
	 * @return the crseNam
	 */
	public String getCrseNam() {
		return crseNam;
	}

	/**
	 * @param crseNam the crseNam to set
	 */
	public void setCrseNam(String crseNam) {
		this.crseNam = crseNam;
	}

	/**
	 * @return the semester
	 */
	public String getSemester() {
		return semester;
	}

	/**
	 * @param semester the semester to set
	 */
	public void setSemester(String semester) {
		this.semester = semester;
	}

	/**
	 * @return the startdt
	 */
	public String getStartdt() {
		return startdt;
	}

	/**
	 * @param startdt the startdt to set
	 */
	public void setStartdt(String startdt) {
		this.startdt = startdt;
	}

	/**
	 * @return the enddt
	 */
	public String getEnddt() {
		return enddt;
	}

	/**
	 * @param enddt the enddt to set
	 */
	public void setEnddt(String enddt) {
		this.enddt = enddt;
	}

	/**
	 * @return the monStart
	 */
	public String getMonStart() {
		return monStart;
	}

	/**
	 * @param monStart the monStart to set
	 */
	public void setMonStart(String monStart) {
		this.monStart = monStart;
	}

	/**
	 * @return the monEnd
	 */
	public String getMonEnd() {
		return monEnd;
	}

	/**
	 * @param monEnd the monEnd to set
	 */
	public void setMonEnd(String monEnd) {
		this.monEnd = monEnd;
	}

	/**
	 * @return the tueStart
	 */
	public String getTueStart() {
		return tueStart;
	}

	/**
	 * @param tueStart the tueStart to set
	 */
	public void setTueStart(String tueStart) {
		this.tueStart = tueStart;
	}

	/**
	 * @return the tueEnd
	 */
	public String getTueEnd() {
		return tueEnd;
	}

	/**
	 * @param tueEnd the tueEnd to set
	 */
	public void setTueEnd(String tueEnd) {
		this.tueEnd = tueEnd;
	}

	/**
	 * @return the wedStart
	 */
	public String getWedStart() {
		return wedStart;
	}

	/**
	 * @param wedStart the wedStart to set
	 */
	public void setWedStart(String wedStart) {
		this.wedStart = wedStart;
	}

	/**
	 * @return the wedEnd
	 */
	public String getWedEnd() {
		return wedEnd;
	}

	/**
	 * @param wedEnd the wedEnd to set
	 */
	public void setWedEnd(String wedEnd) {
		this.wedEnd = wedEnd;
	}

	/**
	 * @return the thuStart
	 */
	public String getThuStart() {
		return thuStart;
	}

	/**
	 * @param thuStart the thuStart to set
	 */
	public void setThuStart(String thuStart) {
		this.thuStart = thuStart;
	}

	/**
	 * @return the thuEnd
	 */
	public String getThuEnd() {
		return thuEnd;
	}

	/**
	 * @param thuEnd the thuEnd to set
	 */
	public void setThuEnd(String thuEnd) {
		this.thuEnd = thuEnd;
	}

	/**
	 * @return the friStart
	 */
	public String getFriStart() {
		return friStart;
	}

	/**
	 * @param friStart the friStart to set
	 */
	public void setFriStart(String friStart) {
		this.friStart = friStart;
	}

	/**
	 * @return the friEnd
	 */
	public String getFriEnd() {
		return friEnd;
	}

	/**
	 * @param friEnd the friEnd to set
	 */
	public void setFriEnd(String friEnd) {
		this.friEnd = friEnd;
	}

	/**
	 * @return the satStart
	 */
	public String getSatStart() {
		return satStart;
	}

	/**
	 * @param satStart the satStart to set
	 */
	public void setSatStart(String satStart) {
		this.satStart = satStart;
	}

	/**
	 * @return the satEnd
	 */
	public String getSatEnd() {
		return satEnd;
	}

	/**
	 * @param satEnd the satEnd to set
	 */
	public void setSatEnd(String satEnd) {
		this.satEnd = satEnd;
	}
	
	public String toString(){
		
		String course = "Id=" + this.crseid + ", Name=" + this.crseNam + ", Subj=" + this.crseSub + ", Semester=" + this.semester + ", Starts=[" + this.monStart + "," + this.tueStart + "," + this.wedStart + "," + this.thuStart + "," + this.friStart + "," + this.satStart + "], Ends=[" + this.monEnd + "," + this.tueEnd + "," + this.wedEnd + ","+ this.thuEnd + ","+ this.friEnd + ","+ this.satEnd + "]";
		
		return course;
		
	}

}
