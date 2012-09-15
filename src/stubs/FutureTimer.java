package stubs;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimerTask;
import java.util.Timer;


@SuppressWarnings("unused")
public class FutureTimer extends Timer {

    public void schedule(TimerTask task, Date myDate)

	  {/*
	       
	        Calendar cal = new GregorianCalendar();
	        cal.setTimeInMillis(System.currentTimeMillis());
	        Date currentDate = cal.getTime();
	        if (myDate.compareTo(currentDate) < 0)
	            return;
	        
	        super.schedule(task, myDate); 
	    */}
	}
