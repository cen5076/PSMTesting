package testing.logicSubsystemTest;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Logic.appController;

public class appControllerTimers_SS {

	private appController app1;
	private Calendar temp;
	private Calendar result;
	public static final int SECONDS = 1;
	
	
	@Before
	public void setUp() throws Exception {
		app1 = new appController();
		temp = new GregorianCalendar();
		result = new GregorianCalendar();
	}

	@After
	public void tearDown() throws Exception {
		app1 = null;
		temp = null;
		result = null;
	}
	
	/** Test Case ID: PSM_008-Message_PopUp-SubsystemTest-D01
	 * Purpose: test onpoint values for getEndTime.
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testGetEndTime_1() {
		
		//set hour and minutes of day 
		//h=0 => 00:, h=1 => 01:, h=-1 => 23:
		//m=0 => (h < 1 ? d-1 : d) (h-1):59:01, m=1 => (h == -1 ? d-1 : d) h:00:01, m=-1 => (h < 1 ? d-1 : d) (h-1):58:01
		int h=0;
		int m=0;
		
		result.setTime(app1.getEndTime(h, m));

		temp.set(Calendar.DATE, temp.get(Calendar.DATE)-1);
		
		//Assert
		assertEquals("Year", temp.get(Calendar.YEAR), result.get(Calendar.YEAR));
		assertEquals("Month", temp.get(Calendar.MONTH), result.get(Calendar.MONTH));
		assertEquals("Date", temp.get(Calendar.DATE), result.get(Calendar.DATE));
		assertEquals("Hour", 23, result.get(Calendar.HOUR_OF_DAY));
		assertEquals("Minute", 59, result.get(Calendar.MINUTE));
		assertEquals("Second", SECONDS, result.get(Calendar.SECOND));
	}

	/** Test Case ID: PSM_008-Message_PopUp-SubsystemTest-D02
	 * Purpose: test onpoint, and offpoint/inpoint values for getEndTime
	 * Date Created: 09/15/12
	 * Author: Matthew Brown 
	 */
	@Test
	public void testGetEndTime_2() {
		
		//set hour and minutes of day 
		//h=0 => 00:, h=1 => 01:, h=-1 => 23:
		//m=0 => (h < 1 ? d-1 : d) (h-1):59:01, m=1 => (h == -1 ? d-1 : d) h:00:01, m=-1 => (h < 1 ? d-1 : d) (h-1):58:01
		int h=0;
		int m=1;

		result.setTime(app1.getEndTime(h, m));
		
		//Assert
		assertEquals("Year", temp.get(Calendar.YEAR), result.get(Calendar.YEAR));
		assertEquals("Month", temp.get(Calendar.MONTH), result.get(Calendar.MONTH));
		assertEquals("Date", temp.get(Calendar.DATE), result.get(Calendar.DATE));
		assertEquals("Hour", 0, result.get(Calendar.HOUR_OF_DAY));
		assertEquals("Minute", 0, result.get(Calendar.MINUTE));
		assertEquals("Second", SECONDS, result.get(Calendar.SECOND));

	}
	
	/** Test Case ID: PSM_008-Message_PopUp-SubsystemTest-D03
	 * Purpose: test onpoint, and offpoint/outpoint values for getEndTime
	 * Date Created: 09/15/12
	 * Author: Matthew Brown 
	 */
	@Test
	public void testGetEndTime_3() {
		
		//set hour and minutes of day 
		//h=0 => 00:, h=1 => 01:, h=-1 => 23:
		//m=0 => (h < 1 ? d-1 : d) (h-1):59:01, m=1 => (h == -1 ? d-1 : d) h:00:01, m=-1 => (h < 1 ? d-1 : d) (h-1):58:01
		int h=0;
		int m=-1;
		
		result.setTime(app1.getEndTime(h, m));

		temp.set(Calendar.DATE, temp.get(Calendar.DATE)-1);
		
		//Assert
		assertEquals("Year", temp.get(Calendar.YEAR), result.get(Calendar.YEAR));
		assertEquals("Month", temp.get(Calendar.MONTH), result.get(Calendar.MONTH));
		assertEquals("Date", temp.get(Calendar.DATE), result.get(Calendar.DATE));
		assertEquals("Hour", 23, result.get(Calendar.HOUR_OF_DAY));
		assertEquals("Minute", 58, result.get(Calendar.MINUTE));
		assertEquals("Second", SECONDS, result.get(Calendar.SECOND));

	}

	/** Test Case ID: PSM_008-Message_PopUp-SubsystemTest-D04
	 * Purpose: test offpoint/inpoint, and onpoint values for getEndTime
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testGetEndTime_4() {
		
		//set hour and minutes of day 
		//h=0 => 00:, h=1 => 01:, h=-1 => 23:
		//m=0 => (h < 1 ? d-1 : d) (h-1):59:01, m=1 => (h == -1 ? d-1 : d) h:00:01, m=-1 => (h < 1 ? d-1 : d) (h-1):58:01
		int h=1;
		int m=0;

		result.setTime(app1.getEndTime(h, m));
		
		//Assert
		assertEquals("Year", temp.get(Calendar.YEAR), result.get(Calendar.YEAR));
		assertEquals("Month", temp.get(Calendar.MONTH), result.get(Calendar.MONTH));
		assertEquals("Date", temp.get(Calendar.DATE), result.get(Calendar.DATE));
		assertEquals("Hour", 0, result.get(Calendar.HOUR_OF_DAY));
		assertEquals("Minute", 59, result.get(Calendar.MINUTE));
		assertEquals("Second", SECONDS, result.get(Calendar.SECOND));

	}

	/** Test Case ID: PSM_008-Message_PopUp-SubsystemTest-D05
	 * Purpose: test offpoint/inpoints values for getEndTime
	 * Date Created: 09/15/12
	 * Author: Matthew Brown 
	 */
	@Test
	public void testGetEndTime_5() {
		
		//set hour and minutes of day 
		//h=0 => 00:, h=1 => 01:, h=-1 => 23:
		//m=0 => (h < 1 ? d-1 : d) (h-1):59:01, m=1 => (h == -1 ? d-1 : d) h:00:01, m=-1 => (h < 1 ? d-1 : d) (h-1):58:01
		int h=1;
		int m=1;

		result.setTime(app1.getEndTime(h, m));
		
		//Assert
		assertEquals("Year", temp.get(Calendar.YEAR), result.get(Calendar.YEAR));
		assertEquals("Month", temp.get(Calendar.MONTH), result.get(Calendar.MONTH));
		assertEquals("Date", temp.get(Calendar.DATE), result.get(Calendar.DATE));
		assertEquals("Hour", 1, result.get(Calendar.HOUR_OF_DAY));
		assertEquals("Minute", 0, result.get(Calendar.MINUTE));
		assertEquals("Second", SECONDS, result.get(Calendar.SECOND));

	}

	/** Test Case ID: PSM_008-Message_PopUp-SubsystemTest-D06
	 * Purpose: test offpoint/inpoint, and offpoint/outpoint values for getEndTime
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testGetEndTime_6() {
		
		//set hour and minutes of day 
		//h=0 => 00:, h=1 => 01:, h=-1 => 23:
		//m=0 => (h < 1 ? d-1 : d) (h-1):59:01, m=1 => (h == -1 ? d-1 : d) h:00:01, m=-1 => (h < 1 ? d-1 : d) (h-1):58:01
		int h=1;
		int m=-1;

		result.setTime(app1.getEndTime(h, m));
		
		//Assert
		assertEquals("Year", temp.get(Calendar.YEAR), result.get(Calendar.YEAR));
		assertEquals("Month", temp.get(Calendar.MONTH), result.get(Calendar.MONTH));
		assertEquals("Date", temp.get(Calendar.DATE), result.get(Calendar.DATE));
		assertEquals("Hour", 0, result.get(Calendar.HOUR_OF_DAY));
		assertEquals("Minute", 58, result.get(Calendar.MINUTE));
		assertEquals("Second", SECONDS, result.get(Calendar.SECOND));

	}

	/** Test Case ID: PSM_008-Message_PopUp-SubsystemTest-D07
	 * Purpose: test offpoint/outpoint, and onpoint values for getEndTime
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testGetEndTime_7() {
		
		//set hour and minutes of day 
		//h=0 => 00:, h=1 => 01:, h=-1 => 23:
		//m=0 => (h < 1 ? d-1 : d) (h-1):59:01, m=1 => (h == -1 ? d-1 : d) h:00:01, m=-1 => (h < 1 ? d-1 : d) (h-1):58:01
		int h=-1;
		int m=0;

		result.setTime(app1.getEndTime(h, m));

		temp.set(Calendar.DATE, temp.get(Calendar.DATE)-1);
		
		//Assert
		assertEquals("Year", temp.get(Calendar.YEAR), result.get(Calendar.YEAR));
		assertEquals("Month", temp.get(Calendar.MONTH), result.get(Calendar.MONTH));
		assertEquals("Date", temp.get(Calendar.DATE), result.get(Calendar.DATE));
		assertEquals("Hour", 22, result.get(Calendar.HOUR_OF_DAY));
		assertEquals("Minute", 59, result.get(Calendar.MINUTE));
		assertEquals("Second", SECONDS, result.get(Calendar.SECOND));

	}

	/** Test Case ID: PSM_008-Message_PopUp-SubsystemTest-D08
	 * Purpose: test offpoint/outpoint, and offpoint/inpoint values for getEndTime
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testGetEndTime_8() {
		
		//set hour and minutes of day 
		//h=0 => 00:, h=1 => 01:, h=-1 => 23:
		//m=0 => (h < 1 ? d-1 : d) (h-1):59:01, m=1 => (h == -1 ? d-1 : d) h:00:01, m=-1 => (h < 1 ? d-1 : d) (h-1):58:01
		int h=-1;
		int m=1;

		result.setTime(app1.getEndTime(h, m));

		temp.set(Calendar.DATE, temp.get(Calendar.DATE)-1);
		
		//Assert
		assertEquals("Year", temp.get(Calendar.YEAR), result.get(Calendar.YEAR));
		assertEquals("Month", temp.get(Calendar.MONTH), result.get(Calendar.MONTH));
		assertEquals("Date", temp.get(Calendar.DATE), result.get(Calendar.DATE));
		assertEquals("Hour", 23, result.get(Calendar.HOUR_OF_DAY));
		assertEquals("Minute", 0, result.get(Calendar.MINUTE));
		assertEquals("Second", SECONDS, result.get(Calendar.SECOND));

	}

	/** Test Case ID: PSM_008-Message_PopUp-SubsystemTest-D09
	 * Purpose: test offpoint/outpoint values for getEndTime
	 * Date Created: 09/15/12
	 * Author: Matthew Brown 
	 */
	@Test
	public void testGetEndTime_9() {
		
		//set hour and minutes of day 
		//h=0 => 00:, h=1 => 01:, h=-1 => 23:
		//m=0 => (h < 1 ? d-1 : d) (h-1):59:01, m=1 => (h == -1 ? d-1 : d) h:00:01, m=-1 => (h < 1 ? d-1 : d) (h-1):58:01
		int h=-1;
		int m=-1;

		result.setTime(app1.getEndTime(h, m));

		temp.set(Calendar.DATE, temp.get(Calendar.DATE)-1);
		
		//Assert
		assertEquals("Year", temp.get(Calendar.YEAR), result.get(Calendar.YEAR));
		assertEquals("Month", temp.get(Calendar.MONTH), result.get(Calendar.MONTH));
		assertEquals("Date", temp.get(Calendar.DATE), result.get(Calendar.DATE));
		assertEquals("Hour", 22, result.get(Calendar.HOUR_OF_DAY));
		assertEquals("Minute", 58, result.get(Calendar.MINUTE));
		assertEquals("Second", SECONDS, result.get(Calendar.SECOND));

	}
	
	/** Test Case ID: PSM_008-Message_PopUp-SubsystemTest-D10
	 * Purpose: test onpoint values for get15BeforeEnd
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testGetPopup15min_1() {
		
		//set hour and minutes of day 
		//h=0 => 00:, h=1 => 01:, h=-1 => 23:
		//m=0 => (h < 1 ? d-1 : d) (h-1):45:01, m=1 => (h < 1 ? d-1 : d) (h-1):46:01, m=-1 => (h < 1 ? d-1 : d) (h-1):44:01
		int h=0;
		int m=0;

		result.setTime(app1.get15BeforeEnd(h, m));

		temp.set(Calendar.DATE, temp.get(Calendar.DATE)-1);
		
		//Assert
		assertEquals("Year", temp.get(Calendar.YEAR), result.get(Calendar.YEAR));
		assertEquals("Month", temp.get(Calendar.MONTH), result.get(Calendar.MONTH));
		assertEquals("Date", temp.get(Calendar.DATE), result.get(Calendar.DATE));
		assertEquals("Hour", 23, result.get(Calendar.HOUR_OF_DAY));
		assertEquals("Minute", 45, result.get(Calendar.MINUTE));
		assertEquals("Second", SECONDS, result.get(Calendar.SECOND));
	}
	
	/** Test Case ID: PSM_008-Message_PopUp-SubsystemTest-D11
	 * Purpose: test onpoint, and offpoint/inpoint values for get15BeforeEnd
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testGetPopup15min_2() {
		
		//set hour and minutes of day 
		//h=0 => 00:, h=1 => 01:, h=-1 => 23:
		//m=0 => (h < 1 ? d-1 : d) (h-1):45:01, m=1 => (h < 1 ? d-1 : d) (h-1):46:01, m=-1 => (h < 1 ? d-1 : d) (h-1):44:01
		int h=0;
		int m=1;

		result.setTime(app1.get15BeforeEnd(h, m));

		temp.set(Calendar.DATE, temp.get(Calendar.DATE)-1);
		
		//Assert
		assertEquals("Year", temp.get(Calendar.YEAR), result.get(Calendar.YEAR));
		assertEquals("Month", temp.get(Calendar.MONTH), result.get(Calendar.MONTH));
		assertEquals("Date", temp.get(Calendar.DATE), result.get(Calendar.DATE));
		assertEquals("Hour", 23, result.get(Calendar.HOUR_OF_DAY));
		assertEquals("Minute", 46, result.get(Calendar.MINUTE));
		assertEquals("Second", SECONDS, result.get(Calendar.SECOND));
	}
	
	/** Test Case ID: PSM_008-Message_PopUp-SubsystemTest-D12
	 * Purpose: test onpoint, and offpoint/outpoint values for get15BeforeEnd
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testGetPopup15min_3() {
		
		//set hour and minutes of day 
		//h=0 => 00:, h=1 => 01:, h=-1 => 23:
		//m=0 => (h < 1 ? d-1 : d) (h-1):45:01, m=1 => (h < 1 ? d-1 : d) (h-1):46:01, m=-1 => (h < 1 ? d-1 : d) (h-1):44:01
		int h=0;
		int m=-1;

		result.setTime(app1.get15BeforeEnd(h, m));

		temp.set(Calendar.DATE, temp.get(Calendar.DATE)-1);
		
		//Assert
		assertEquals("Year", temp.get(Calendar.YEAR), result.get(Calendar.YEAR));
		assertEquals("Month", temp.get(Calendar.MONTH), result.get(Calendar.MONTH));
		assertEquals("Date", temp.get(Calendar.DATE), result.get(Calendar.DATE));
		assertEquals("Hour", 23, result.get(Calendar.HOUR_OF_DAY));
		assertEquals("Minute", 44, result.get(Calendar.MINUTE));
		assertEquals("Second", SECONDS, result.get(Calendar.SECOND));

	}
	
	/** Test Case ID: PSM_008-Message_PopUp-SubsystemTest-D13
	 * Purpose: test offpoint/inpoint, and onpoint values for get15BeforeEnd
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testGetPopup15min_4() {
		
		//set hour and minutes of day 
		//h=0 => 00:, h=1 => 01:, h=-1 => 23:
		//m=0 => (h < 1 ? d-1 : d) (h-1):45:01, m=1 => (h < 1 ? d-1 : d) (h-1):46:01, m=-1 => (h < 1 ? d-1 : d) (h-1):44:01
		int h=1;
		int m=0;

		result.setTime(app1.get15BeforeEnd(h, m));
		
		//Assert
		assertEquals("Year", temp.get(Calendar.YEAR), result.get(Calendar.YEAR));
		assertEquals("Month", temp.get(Calendar.MONTH), result.get(Calendar.MONTH));
		assertEquals("Date", temp.get(Calendar.DATE), result.get(Calendar.DATE));
		assertEquals("Hour", 0, result.get(Calendar.HOUR_OF_DAY));
		assertEquals("Minute", 45, result.get(Calendar.MINUTE));
		assertEquals("Second", SECONDS, result.get(Calendar.SECOND));

	}
	
	/** Test Case ID: PSM_008-Message_PopUp-SubsystemTest-D14
	 * Purpose: test offpoint/inpoint values for get15BeforeEnd
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testGetPopup15min_5() {
		
		//set hour and minutes of day 
		//h=0 => 00:, h=1 => 01:, h=-1 => 23:
		//m=0 => (h < 1 ? d-1 : d) (h-1):45:01, m=1 => (h < 1 ? d-1 : d) (h-1):46:01, m=-1 => (h < 1 ? d-1 : d) (h-1):44:01
		int h=1;
		int m=1;
			
		result.setTime(app1.get15BeforeEnd(h, m));
		
		//Assert
		assertEquals("Year", temp.get(Calendar.YEAR), result.get(Calendar.YEAR));
		assertEquals("Month", temp.get(Calendar.MONTH), result.get(Calendar.MONTH));
		assertEquals("Date", temp.get(Calendar.DATE), result.get(Calendar.DATE));
		assertEquals("Hour", 0, result.get(Calendar.HOUR_OF_DAY));
		assertEquals("Minute", 46, result.get(Calendar.MINUTE));
		assertEquals("Second", SECONDS, result.get(Calendar.SECOND));

	}
	
	/** Test Case ID: PSM_008-Message_PopUp-SubsystemTest-D15
	 * Purpose: test offpoint/inpoint, and offpoint/outpoint values for get15BeforeEnd
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testGetPopup15min_6() {
		
		//set hour and minutes of day 
		//h=0 => 00:, h=1 => 01:, h=-1 => 23:
		//m=0 => (h < 1 ? d-1 : d) (h-1):45:01, m=1 => (h < 1 ? d-1 : d) (h-1):46:01, m=-1 => (h < 1 ? d-1 : d) (h-1):44:01
		int h=1;
		int m=-1;
		
		result.setTime(app1.get15BeforeEnd(h, m));
		
		//Assert
		assertEquals("Year", temp.get(Calendar.YEAR), result.get(Calendar.YEAR));
		assertEquals("Month", temp.get(Calendar.MONTH), result.get(Calendar.MONTH));
		assertEquals("Date", temp.get(Calendar.DATE), result.get(Calendar.DATE));
		assertEquals("Hour", 0, result.get(Calendar.HOUR_OF_DAY));
		assertEquals("Minute", 44, result.get(Calendar.MINUTE));
		assertEquals("Second", SECONDS, result.get(Calendar.SECOND));
	}
	
	/** Test Case ID: PSM_008-Message_PopUp-SubsystemTest-D16
	 * Purpose: test offpoint/outpoint, and onpoint values for get15BeforeEnd
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testGetPopup15min_7() {
		
		//set hour and minutes of day 
		//h=0 => 00:, h=1 => 01:, h=-1 => 23:
		//m=0 => (h < 1 ? d-1 : d) (h-1):45:01, m=1 => (h < 1 ? d-1 : d) (h-1):46:01, m=-1 => (h < 1 ? d-1 : d) (h-1):44:01
		int h=-1;
		int m=0;
		
		result.setTime(app1.get15BeforeEnd(h, m));

		temp.set(Calendar.DATE, temp.get(Calendar.DATE)-1);
		
		//Assert
		assertEquals("Year", temp.get(Calendar.YEAR), result.get(Calendar.YEAR));
		assertEquals("Month", temp.get(Calendar.MONTH), result.get(Calendar.MONTH));
		assertEquals("Date", temp.get(Calendar.DATE), result.get(Calendar.DATE));
		assertEquals("Hour", 22, result.get(Calendar.HOUR_OF_DAY));
		assertEquals("Minute", 45, result.get(Calendar.MINUTE));
		assertEquals("Second", SECONDS, result.get(Calendar.SECOND));

	}
	
	/** Test Case ID: PSM_008-Message_PopUp-SubsystemTest-D17
	 * Purpose: test offpoint/outpoint, and offpoint/inpoint values for get15BeforeEnd
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testGetPopup15min_8() {
		
		//set hour and minutes of day 
		//h=0 => 00:, h=1 => 01:, h=-1 => 23:
		//m=0 => (h < 1 ? d-1 : d) (h-1):45:01, m=1 => (h < 1 ? d-1 : d) (h-1):46:01, m=-1 => (h < 1 ? d-1 : d) (h-1):44:01
		int h=-1;
		int m=1;
		
		result.setTime(app1.get15BeforeEnd(h, m));

		temp.set(Calendar.DATE, temp.get(Calendar.DATE)-1);
		
		//Assert
		assertEquals("Year", temp.get(Calendar.YEAR), result.get(Calendar.YEAR));
		assertEquals("Month", temp.get(Calendar.MONTH), result.get(Calendar.MONTH));
		assertEquals("Date", temp.get(Calendar.DATE), result.get(Calendar.DATE));
		assertEquals("Hour", 22, result.get(Calendar.HOUR_OF_DAY));
		assertEquals("Minute", 46, result.get(Calendar.MINUTE));
		assertEquals("Second", SECONDS, result.get(Calendar.SECOND));

	}
	
	/** Test Case ID: PSM_008-Message_PopUp-SubsystemTest-D18
	 * Purpose: test offpoint/outpoint values for get15BeforeEnd
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testGetPopup15min_9() {
		
		//set hour and minutes of day 
		//h=0 => 00:, h=1 => 01:, h=-1 => 23:
		//m=0 => (h < 1 ? d-1 : d) (h-1):45:01, m=1 => (h < 1 ? d-1 : d) (h-1):46:01, m=-1 => (h < 1 ? d-1 : d) (h-1):44:01
		int h=-1;
		int m=-1;
		
		result.setTime(app1.get15BeforeEnd(h, m));

		temp.set(Calendar.DATE, temp.get(Calendar.DATE)-1);
		
		//Assert
		assertEquals("Year", temp.get(Calendar.YEAR), result.get(Calendar.YEAR));
		assertEquals("Month", temp.get(Calendar.MONTH), result.get(Calendar.MONTH));
		assertEquals("Date", temp.get(Calendar.DATE), result.get(Calendar.DATE));
		assertEquals("Hour", 22, result.get(Calendar.HOUR_OF_DAY));
		assertEquals("Minute", 44, result.get(Calendar.MINUTE));
		assertEquals("Second", SECONDS, result.get(Calendar.SECOND));

	}
	
	/** Test Case ID: PSM_008-Message_PopUp-SubsystemTest-D19
	 * Purpose: test onpoint values for get5BeforeEnd
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testGetPopup5min_1() {
		
		//set hour and minutes of day 
		//h=0 => 00:, h=1 => 01:, h=-1 => 23:
		//m=0 => (h < 1 ? d-1 : d) (h-1):55:01, m=1 => (h < 1 ? d-1 : d) (h-1):56:01, m=-1 => (h < 1 ? d-1 : d) (h-1):54:01
		int h=0;
		int m=0;
		
		result.setTime(app1.get5BeforeEnd(h, m));

		temp.set(Calendar.DATE, temp.get(Calendar.DATE)-1);
		
		//Assert
		assertEquals("Year", temp.get(Calendar.YEAR), result.get(Calendar.YEAR));
		assertEquals("Month", temp.get(Calendar.MONTH), result.get(Calendar.MONTH));
		assertEquals("Date", temp.get(Calendar.DATE), result.get(Calendar.DATE));
		assertEquals("Hour", 23, result.get(Calendar.HOUR_OF_DAY));
		assertEquals("Minute", 55, result.get(Calendar.MINUTE));
		assertEquals("Second", SECONDS, result.get(Calendar.SECOND));

	}
	
	/** Test Case ID: PSM_008-Message_PopUp-SubsystemTest-D20
	 * Purpose: test onpoint, and offpoint/inpoint values for get5BeforeEnd
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testGetPopup5min_2() {
		
		//set hour and minutes of day 
		//h=0 => 00:, h=1 => 01:, h=-1 => 23:
		//m=0 => (h < 1 ? d-1 : d) (h-1):55:01, m=1 => (h < 1 ? d-1 : d) (h-1):56:01, m=-1 => (h < 1 ? d-1 : d) (h-1):54:01
		int h=0;
		int m=1;
		
		result.setTime(app1.get5BeforeEnd(h, m));

		temp.set(Calendar.DATE, temp.get(Calendar.DATE)-1);
		
		//Assert
		assertEquals("Year", temp.get(Calendar.YEAR), result.get(Calendar.YEAR));
		assertEquals("Month", temp.get(Calendar.MONTH), result.get(Calendar.MONTH));
		assertEquals("Date", temp.get(Calendar.DATE), result.get(Calendar.DATE));
		assertEquals("Hour", 23, result.get(Calendar.HOUR_OF_DAY));
		assertEquals("Minute", 56, result.get(Calendar.MINUTE));
		assertEquals("Second", SECONDS, result.get(Calendar.SECOND));

	}
	
	/** Test Case ID: PSM_008-Message_PopUp-SubsystemTest-D21
	 * Purpose: test onpoint, and offpoint/outpoint values for get5BeforeEnd
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testGetPopup5min_3() {
		
		//set hour and minutes of day 
		//h=0 => 00:, h=1 => 01:, h=-1 => 23:
		//m=0 => (h < 1 ? d-1 : d) (h-1):55:01, m=1 => (h < 1 ? d-1 : d) (h-1):56:01, m=-1 => (h < 1 ? d-1 : d) (h-1):54:01
		int h=0;
		int m=-1;
		
		result.setTime(app1.get5BeforeEnd(h, m));

		temp.set(Calendar.DATE, temp.get(Calendar.DATE)-1);
		
		//Assert
		assertEquals("Year", temp.get(Calendar.YEAR), result.get(Calendar.YEAR));
		assertEquals("Month", temp.get(Calendar.MONTH), result.get(Calendar.MONTH));
		assertEquals("Date", temp.get(Calendar.DATE), result.get(Calendar.DATE));
		assertEquals("Hour", 23, result.get(Calendar.HOUR_OF_DAY));
		assertEquals("Minute", 54, result.get(Calendar.MINUTE));
		assertEquals("Second", SECONDS, result.get(Calendar.SECOND));

	}
	
	/** Test Case ID: PSM_008-Message_PopUp-SubsystemTest-D22
	 * Purpose: test offpoint/inpoint, and onpoint values for get5BeforeEnd
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testGetPopup5min_4() {
		
		//set hour and minutes of day 
		//h=0 => 00:, h=1 => 01:, h=-1 => 23:
		//m=0 => (h < 1 ? d-1 : d) (h-1):55:01, m=1 => (h < 1 ? d-1 : d) (h-1):56:01, m=-1 => (h < 1 ? d-1 : d) (h-1):54:01
		int h=1;
		int m=0;
		
		result.setTime(app1.get5BeforeEnd(h, m));
		
		//Assert
		assertEquals("Year", temp.get(Calendar.YEAR), result.get(Calendar.YEAR));
		assertEquals("Month", temp.get(Calendar.MONTH), result.get(Calendar.MONTH));
		assertEquals("Date", temp.get(Calendar.DATE), result.get(Calendar.DATE));
		assertEquals("Hour", 0, result.get(Calendar.HOUR_OF_DAY));
		assertEquals("Minute", 55, result.get(Calendar.MINUTE));
		assertEquals("Second", SECONDS, result.get(Calendar.SECOND));
	}
	
	/** Test Case ID: PSM_008-Message_PopUp-SubsystemTest-D23
	 * Purpose: test offpoint/inpoint values for get5BeforeEnd
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testGetPopup5min_5() {
		
		//set hour and minutes of day 
		//h=0 => 00:, h=1 => 01:, h=-1 => 23:
		//m=0 => (h < 1 ? d-1 : d) (h-1):55:01, m=1 => (h < 1 ? d-1 : d) (h-1):56:01, m=-1 => (h < 1 ? d-1 : d) (h-1):54:01
		int h=1;
		int m=1;
		
		result.setTime(app1.get5BeforeEnd(h, m));
		
		//Assert
		assertEquals("Year", temp.get(Calendar.YEAR), result.get(Calendar.YEAR));
		assertEquals("Month", temp.get(Calendar.MONTH), result.get(Calendar.MONTH));
		assertEquals("Date", temp.get(Calendar.DATE), result.get(Calendar.DATE));
		assertEquals("Hour", 0, result.get(Calendar.HOUR_OF_DAY));
		assertEquals("Minute", 56, result.get(Calendar.MINUTE));
		assertEquals("Second", SECONDS, result.get(Calendar.SECOND));
	}
	
	/** Test Case ID: PSM_008-Message_PopUp-SubsystemTest-D24
	 * Purpose: test offpoint/inpoint, and offpoint/outpoint values for get5BeforeEnd
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testGetPopup5min_6() {
		
		//set hour and minutes of day 
		//h=0 => 00:, h=1 => 01:, h=-1 => 23:
		//m=0 => (h < 1 ? d-1 : d) (h-1):55:01, m=1 => (h < 1 ? d-1 : d) (h-1):56:01, m=-1 => (h < 1 ? d-1 : d) (h-1):54:01
		int h=1;
		int m=-1;
		
		result.setTime(app1.get5BeforeEnd(h, m));
		
		//Assert
		assertEquals("Year", temp.get(Calendar.YEAR), result.get(Calendar.YEAR));
		assertEquals("Month", temp.get(Calendar.MONTH), result.get(Calendar.MONTH));
		assertEquals("Date", temp.get(Calendar.DATE), result.get(Calendar.DATE));
		assertEquals("Hour", 0, result.get(Calendar.HOUR_OF_DAY));
		assertEquals("Minute", 54, result.get(Calendar.MINUTE));
		assertEquals("Second", SECONDS, result.get(Calendar.SECOND));

	}
	
	/** Test Case ID: PSM_008-Message_PopUp-SubsystemTest-D25
	 * Purpose: test offpoint/outpoint, and onpoint values for get5BeforeEnd
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testGetPopup5min_7() {
		
		//set hour and minutes of day 
		//h=0 => 00:, h=1 => 01:, h=-1 => 23:
		//m=0 => (h < 1 ? d-1 : d) (h-1):55:01, m=1 => (h < 1 ? d-1 : d) (h-1):56:01, m=-1 => (h < 1 ? d-1 : d) (h-1):54:01
		int h=-1;
		int m=0;
		
		result.setTime(app1.get5BeforeEnd(h, m));

		temp.set(Calendar.DATE, temp.get(Calendar.DATE)-1);
		
		//Assert
		assertEquals("Year", temp.get(Calendar.YEAR), result.get(Calendar.YEAR));
		assertEquals("Month", temp.get(Calendar.MONTH), result.get(Calendar.MONTH));
		assertEquals("Date", temp.get(Calendar.DATE), result.get(Calendar.DATE));
		assertEquals("Hour", 22, result.get(Calendar.HOUR_OF_DAY));
		assertEquals("Minute", 55, result.get(Calendar.MINUTE));
		assertEquals("Second", SECONDS, result.get(Calendar.SECOND));

	}
	
	/** Test Case ID: PSM_008-Message_PopUp-SubsystemTest-D26
	 * Purpose: test offpoint/outpoint, and offpoint/inpoint values for get5BeforeEnd
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testGetPopup5min_8() {
		
		//set hour and minutes of day 
		//h=0 => 00:, h=1 => 01:, h=-1 => 23:
		//m=0 => (h < 1 ? d-1 : d) (h-1):55:01, m=1 => (h < 1 ? d-1 : d) (h-1):56:01, m=-1 => (h < 1 ? d-1 : d) (h-1):54:01
		int h=-1;
		int m=1;
		
		result.setTime(app1.get5BeforeEnd(h, m));

		temp.set(Calendar.DATE, temp.get(Calendar.DATE)-1);
		
		//Assert
		assertEquals("Year", temp.get(Calendar.YEAR), result.get(Calendar.YEAR));
		assertEquals("Month", temp.get(Calendar.MONTH), result.get(Calendar.MONTH));
		assertEquals("Date", temp.get(Calendar.DATE), result.get(Calendar.DATE));
		assertEquals("Hour", 22, result.get(Calendar.HOUR_OF_DAY));
		assertEquals("Minute", 56, result.get(Calendar.MINUTE));
		assertEquals("Second", SECONDS, result.get(Calendar.SECOND));

	}
	
	/** Test Case ID: PSM_008-Message_PopUp-SubsystemTest-D27
	 * Purpose: test offpoint/outpoint values for get5BeforeEnd
	 * Date Created: 09/15/12
	 * Author: Matthew Brown
	 */
	@Test
	public void testGetPopup5min_9() {
		
		//set hour and minutes of day 
		//h=0 => 00:, h=1 => 01:, h=-1 => 23:
		//m=0 => (h < 1 ? d-1 : d) (h-1):55:01, m=1 => (h < 1 ? d-1 : d) (h-1):56:01, m=-1 => (h < 1 ? d-1 : d) (h-1):54:01
		int h=-1;
		int m=-1;
		
		result.setTime(app1.get5BeforeEnd(h, m));

		temp.set(Calendar.DATE, temp.get(Calendar.DATE)-1);
		
		//Assert
		assertEquals("Year", temp.get(Calendar.YEAR), result.get(Calendar.YEAR));
		assertEquals("Month", temp.get(Calendar.MONTH), result.get(Calendar.MONTH));
		assertEquals("Date", temp.get(Calendar.DATE), result.get(Calendar.DATE));
		assertEquals("Hour", 22, result.get(Calendar.HOUR_OF_DAY));
		assertEquals("Minute", 54, result.get(Calendar.MINUTE));
		assertEquals("Second", SECONDS, result.get(Calendar.SECOND));

	}
}
