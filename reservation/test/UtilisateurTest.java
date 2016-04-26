package reservation.test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import reservation.classes.Outils;
import reservation.classes.Reservation;
import reservation.classes.Utilisateur;

public class UtilisateurTest { 
	
	private Utilisateur unUtilisateur; 
	private Reservation reservation1, reservation2; 
	private String formatDateUS = "yyyy-MM-dd HH:mm:ss"; 
	
	@Before 
	public void setUp() throws Exception { 
	
		unUtilisateur = new Utilisateur(10, 1, "Tess Tuniter", "123456", "tess.tuniter@gmail.com"); 
		Date unTimeStamp = Outils.ConvertirEnDate("2014-12-09 10:30:40", formatDateUS); 
		Date unStartTime = Outils.ConvertirEnDate("2014-12-09 20:00:00", formatDateUS); 
		Date unEndTime = Outils.ConvertirEnDate("2014-12-09 22:00:00", formatDateUS); 
		reservation1 = new Reservation(10, unTimeStamp, unStartTime, unEndTime, "Salle 1", 4, "111111"); 
		reservation2 = new Reservation(10, unTimeStamp, unStartTime, unEndTime, "Salle 2", 4, "222222"); 
	
	} 
	
	@Test 
	public void testGetId() { 
		
		assertEquals("Test getId", 10, unUtilisateur.getId()); 
		
		unUtilisateur.setId(20); 
		
		assertEquals("Test setId", 20, unUtilisateur.getId()); 
	
	} 
	
	@Test 
	
	public void testGetLevel() { 
	
		assertEquals("Test getLevel", 1, unUtilisateur.getLevel()); 
		
		unUtilisateur.setLevel(2);
		
		assertEquals("Test setLevel", 2, unUtilisateur.getLevel()); 
	
	} 
	
	@Test 
	public void testGetName() { 
	
		assertEquals("Test getName", "Tess Tuniter", unUtilisateur.getName()); 
		
		unUtilisateur.setName("Amédée Bogueur"); 
		
		assertEquals("Test setName", "Amédée Bogueur", unUtilisateur.getName()); 
	
	} 
	
	@Test 
	public void testGetPassword() { 
	
		assertEquals("Test getPassword", "123456", unUtilisateur.getPassword());
		
		unUtilisateur.setPassword("abcdef"); 
		
		assertEquals("Test setPassword", "abcdef", unUtilisateur.getPassword()); 
	}
	
	@Test
	public void testGetEmail() { 
	
		assertEquals("Test getEmail", "tess.tuniter@gmail.com", unUtilisateur.getEmail()); 
		
		unUtilisateur.setEmail("amedee.bogueur@gmail.com"); 
		
		assertEquals("Test setEmail", "amedee.bogueur@gmail.com", unUtilisateur.getEmail()); 
	}
	
	@Test 
	public void testGetLesReservations() { 
	
		unUtilisateur.ajouteReservation(reservation1);
		unUtilisateur.ajouteReservation(reservation2); 
		ArrayList<Reservation> lesReservations = unUtilisateur.getLesReservations(); 
		Reservation laReservation = lesReservations.get(0); 
		
		assertEquals("Test getLesReservations", "Salle 1", laReservation.getRoomName()); 
		
		laReservation = lesReservations.get(1); 
		
		assertEquals("Test getLesReservations", "Salle 2", laReservation.getRoomName()); 
	}
	
	@Test 
	public void testAjouteReservation() { 
	
		assertEquals("Test getNbReservations", 0, unUtilisateur.getNbReservations()); 
		
		unUtilisateur.ajouteReservation(reservation1);
		
		assertEquals("Test getNbReservations", 1, unUtilisateur.getNbReservations()); 
		
		assertEquals("Test getLaReservation", "Salle 1", unUtilisateur.getLaReservation(0).getRoomName()); 
		
		unUtilisateur.ajouteReservation(reservation2); 
		
		assertEquals("Test getNbReservations", 2, unUtilisateur.getNbReservations()); 
		
		assertEquals("Test getLaReservation", "Salle 2", unUtilisateur.getLaReservation(1).getRoomName()); 
	}
	
	@Test 
	public void testGetLaReservation() { 
	
		unUtilisateur.ajouteReservation(reservation1); 
		unUtilisateur.ajouteReservation(reservation2); 
		
		assertEquals("Test getLaReservation", "Salle 1", unUtilisateur.getLaReservation(0).getRoomName()); 
		
		assertEquals("Test getLaReservation", "Salle 2", unUtilisateur.getLaReservation(1).getRoomName()); 
	}
	
	@Test 
	
	public void testGetNbReservations() { 
	
		assertEquals("Test getNbReservations", 0, unUtilisateur.getNbReservations()); 
		
		unUtilisateur.ajouteReservation(reservation1); 
		
		assertEquals("Test getNbReservations", 1, unUtilisateur.getNbReservations()); 
	}
	
	@Test 
	public void testViderListeReservations() { 
	
		unUtilisateur.ajouteReservation(reservation1);
		unUtilisateur.ajouteReservation(reservation2); 
		
		assertEquals("Test getNbReservations", 2, unUtilisateur.getNbReservations()); 
		
		unUtilisateur.viderListeReservations(); 
		
		assertEquals("Test getNbReservations", 0, unUtilisateur.getNbReservations());
	}
}