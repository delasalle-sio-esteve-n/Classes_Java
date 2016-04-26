package reservation.test;

import static org.junit.Assert.*;
import java.text.ParseException;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import reservation.classes.Outils;
import reservation.classes.Reservation;


public class ReservationTest { 
private Reservation uneReservation; 
private String formatDateUS = "yyyy-MM-dd HH:mm:ss"; 

@Before 

public void setUp() throws Exception { 

	Date unTimeStamp = Outils.ConvertirEnDate("2014-12-09 10:30:40", formatDateUS); 
	Date unStartTime = Outils.ConvertirEnDate("2014-12-09 20:00:00", formatDateUS); 
	Date unEndTime = Outils.ConvertirEnDate("2014-12-09 22:00:00", formatDateUS); 
	uneReservation = new Reservation(10, unTimeStamp, unStartTime, unEndTime, "Salle info", 4, "123456"); 

} 

@Test 

public void testGetId() {
		assertEquals("Test getId", 10, uneReservation.getId()); 
	
		uneReservation.setId(20); 
	
		assertEquals("Test setId", 20, uneReservation.getId()); 
}
	@Test 

	public void testGetTimeStamp() throws ParseException { 

		String valeurLue = Outils.FormaterDateHeure(uneReservation.getTimeStamp(), formatDateUS); 
	
		assertEquals("Test getTimeStamp", "2014-12-09 10:30:40", valeurLue); 
	
		Date unNouveauTimeStamp = Outils.ConvertirEnDate("2015-12-09 11:30:00", formatDateUS); 
		uneReservation.setTimeStamp(unNouveauTimeStamp); 
		valeurLue = Outils.FormaterDateHeure(uneReservation.getTimeStamp(), formatDateUS); 
	
		assertEquals("Test getTimeStamp", "2015-12-09 11:30:00", valeurLue); 
	}
	@Test 

	public void testGetStartTime() throws ParseException { 

		String valeurLue = Outils.FormaterDateHeure(uneReservation.getStartTime(), formatDateUS); 
		
		assertEquals("Test getStartTime", "2014-12-09 20:00:00", valeurLue); 
		
		Date unNouveauStartTime = Outils.ConvertirEnDate("2015-12-09 11:30:00", formatDateUS); 
		uneReservation.setStartTime(unNouveauStartTime); 
		valeurLue = Outils.FormaterDateHeure(uneReservation.getStartTime(), formatDateUS); 
		
		assertEquals("Test getStartTime", "2015-12-09 11:30:00", valeurLue); 
	
	}
	@Test 

		public void testGetEndTime() throws ParseException { 
	
		String valeurLue = Outils.FormaterDateHeure(uneReservation.getEndTime(), formatDateUS); 
	
		assertEquals("Test getEndTime", "2014-12-09 22:00:00", valeurLue); 
	
		Date unNouveauEndTime = Outils.ConvertirEnDate("2015-12-09 11:30:00", formatDateUS); 
		uneReservation.setEndTime(unNouveauEndTime);
		valeurLue = Outils.FormaterDateHeure(uneReservation.getEndTime(), formatDateUS); 
	
		assertEquals("Test getEndTime", "2015-12-09 11:30:00", valeurLue); 
	}
	@Test 

		public void testGetRoomName() { 
	
		assertEquals("Test getRoomName", "Salle info", uneReservation.getRoomName()); 
		
		uneReservation.setRoomName("Salle informatique "); 
	
		assertEquals("Test setRoomName", "Salle informatique ", uneReservation.getRoomName()); 
	}
	@Test 
	
		public void testGetStatus() { 
	
		assertEquals("Test getStatus", 4, uneReservation.getStatus()); 
	
		uneReservation.setStatus(8); 
	
		assertEquals("Test setStatus", 8, uneReservation.getStatus()); 
	}
	@Test 

		public void testGetDigicode() { 
	
		assertEquals("Test getDigicode", "123456", uneReservation.getDigicode()); 
	
		uneReservation.setDigicode("abcdef"); 
	
		assertEquals("Test setDigicode", "abcdef", uneReservation.getDigicode()); 
	}
	@Test 

	public void testToString() { 

		String msg = ""; 
		msg += "id :\t\t" + "10" + "\n"; 
		msg += "timestamp :\t" + "2014-12-09 10:30:40" + "\n"; 
		msg += "start_time :\t" + "2014-12-09 20:00:00" + "\n"; 
		msg += "end_time :\t" + "2014-12-09 22:00:00" + "\n"; 
		msg += "room_name :\t" + "Salle info" + "\n";
		msg += "status :\t\t" + "4" + "\n"; 
		msg += "digicode :\t" + "123456" + "\n"; 
	
		assertEquals("Test toString", msg, uneReservation.toString());
  }
}
