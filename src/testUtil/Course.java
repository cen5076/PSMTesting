package testUtil;

import java.util.ArrayList;
import java.util.Iterator;

public class Course {
	
	public int crseid;
	public String crseSub;
	public String crseNam;
	public String semester;
	public  String startdt;
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
	
	public final ArrayList<String> days = new ArrayList<String>();
	
	public Course(){
		
		days.add("mon");
		days.add("tue");
		days.add("wed");
		days.add("thu");
		days.add("fri");
		days.add("sat");
	}
	
	public Course(int id,String sub,String nam,String sem, String start,String end){
		
		this();
		this.crseid = id;
		this.crseNam = nam;
		this.crseSub = sub;
		this.semester = sem;
		this.startdt = start;
		this.enddt = end;
		
		
	}
	
	public Course(int id,String sub,String nam,String sem, String start,String end,String mons,String mone, String tues, String tuee, String weds, String wede,String thus, String thue, String fris, String frie, String sats, String sate){
		
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
	
	public  ArrayList<String> getFieldNames(){
		
		ArrayList<String> fields = new ArrayList<String>();
		
		fields.add("course_id");
		fields.add("course_subject");
		fields.add("course_name");
		fields.add("semester");
		fields.add("start_date");
		fields.add("end_date");
		
		Iterator<String> days = this.days.iterator();
		
		while(days.hasNext()){
			
			String day = days.next();
			fields.add("start_" + day);
			fields.add("end_" + day);
		}
		
		return fields;
		
	}
	
	public ArrayList<String> getValues(){
		
		ArrayList<String> vals = new ArrayList<String>();
		vals.add(String.valueOf(this.crseid));
		vals.add(this.crseSub);
		vals.add(this.crseNam);
		vals.add(this.semester);
		vals.add(this.startdt);
		vals.add(this.enddt);
		vals.add(this.monStart);
		vals.add(this.monStart);
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
	
	public boolean fillDates(String[] arr){
		
		if(arr.length != 12)
			return false;
		this.monStart = arr[1];
		this.monEnd = arr[2];
		this.tueStart = arr[3];
		this.tueEnd = arr[4];
		this.wedStart = arr[5];
		this.wedEnd = arr[6];
		this.thuStart = arr[7];
		this.thuEnd = arr[8];
		this.friStart = arr[9];
		this.friEnd = arr[10];
		this.satStart = arr[11];
		this.satEnd = arr[12];
		
		
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

}
