package reservation.test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import reservation.classes.Passerelle;
import reservation.classes.Reservation;
import reservation.classes.Utilisateur;

public class PasserelleTest {

	@Test
	public void testConnecter() {
		String msg = Passerelle.connecter("admin", "adminnnnnnnn");
		assertEquals("Test Passerelle.connecter", "Erreur : authentification incorrecte.", msg);
		
		msg = Passerelle.connecter("admin", "azerty");
		assertEquals("Test Passerelle.connecter", "Administrateur authentifié.", msg);
		
		msg = Passerelle.connecter("giboired", "passe");
		assertEquals("Test Passerelle.connecter", "Utilisateur authentifié.", msg);	
	}

	@Test
	public void testCreerUtilisateur() {
		Utilisateur unUtilisateur = new Utilisateur(0, 4, "yvesz", "", "yves.zenels@gmail.com");
		String msg = Passerelle.creerUtilisateur("admin", "adminnnnnnnn", unUtilisateur);
		assertEquals("Test Passerelle.creerUtilisateur", "Erreur : le niveau doit être 0, 1 ou 2.", msg);
		
		unUtilisateur = new Utilisateur(0, 1, "yvesz", "", "yves.zenels@gmail.com");
		msg = Passerelle.creerUtilisateur("admin", "adminnnnnnnn", unUtilisateur);
		assertEquals("Test Passerelle.creerUtilisateur", "Erreur : authentification incorrecte.", msg);
		
		unUtilisateur = new Utilisateur(0, 1, "yvesz", "", "yves.zenels@gmail.com");
		msg = Passerelle.creerUtilisateur("admin", "azerty", unUtilisateur);
		assertEquals("Test Passerelle.creerUtilisateur", "Erreur : nom d'utilisateur déjà existant.", msg);
		
		unUtilisateur = new Utilisateur(0, 1, "yvesz", "", "yves.zenels@gmail.com");
		msg = Passerelle.creerUtilisateur("admin", "azerty", unUtilisateur);
		assertEquals("Test Passerelle.creerUtilisateur", "Erreur : nom d'utilisateur déjà existant.", msg);	
	}

	private static String FormaterDateHeure(Date uneDate, String unFormat) {
		SimpleDateFormat leFormat = new SimpleDateFormat(unFormat);
		return leFormat.format(uneDate);
	}
	
	@Test
	public void testConsulterReservations() {
		Utilisateur unUtilisateur = new Utilisateur(0, 0, "giboired", "passeeeeeeeeeee", "");
		String msg = Passerelle.consulterReservations(unUtilisateur);
		assertEquals("Erreur : authentification incorrecte.", msg);
		
		unUtilisateur = new Utilisateur(0, 0, "garniert", "passe", "");
		msg = Passerelle.consulterReservations(unUtilisateur);
		assertEquals("Erreur : vous n'avez aucune réservation.", msg);
		
		unUtilisateur = new Utilisateur(0, 0, "admin", "azerty", "");
		msg = Passerelle.consulterReservations(unUtilisateur);
		assertEquals("Vous avez effectué 2 réservation(s).", msg);
		assertEquals(2, unUtilisateur.getNbReservations());
		
		String formatUS = "yyyy-MM-dd HH:mm:ss";
		Reservation laReservation = unUtilisateur.getLaReservation(1);
		assertEquals("Multimédia", laReservation.getRoomName());		
		assertEquals(0, laReservation.getStatus());	
		assertEquals("2016-04-23 07:00:00", FormaterDateHeure(laReservation.getStartTime(), formatUS));
		assertEquals("2016-04-23 20:00:00", FormaterDateHeure(laReservation.getEndTime(), formatUS));
		
		laReservation = unUtilisateur.getLaReservation(0);
		assertEquals("Hall d'accueil", laReservation.getRoomName());		
		assertEquals(0, laReservation.getStatus());	
		assertEquals("2016-04-22 07:00:00", FormaterDateHeure(laReservation.getStartTime(), formatUS));
		assertEquals("2016-04-22 20:00:00", FormaterDateHeure(laReservation.getEndTime(), formatUS));
	}
	
	@Test
	public void testConfirmerReservation(){
		
		Utilisateur unUtilisateur = new Utilisateur(0, 0, "", "", "");
		Reservation uneReservation = new Reservation(0, null, null, null, null, 0, null);
		String msg = Passerelle.confirmerReservation(unUtilisateur, uneReservation);
		assertEquals("Erreur : données incomplètes.", msg);
		
		unUtilisateur = new Utilisateur(0, 0, "zenesl", "passe", "");
		uneReservation = new Reservation(1, null, null, null, "", -1, "");
		msg = Passerelle.confirmerReservation(unUtilisateur, uneReservation);
		assertEquals("Erreur : authentification incorrecte.", msg);
				
		unUtilisateur = new Utilisateur(15, 2, "admin", "azerty", "admin@gmail.com");
		uneReservation = new Reservation(1, null, null, null, "", -1, "");
		msg = Passerelle.confirmerReservation(unUtilisateur, uneReservation);
		assertEquals("Erreur : numéro de réservation inexistant.", msg);
		
		unUtilisateur = new Utilisateur(15, 2, "admin", "azerty", "admin@gmail.com");
		uneReservation = new Reservation(7, null, null, null, "", -1, "");
		msg = Passerelle.confirmerReservation(unUtilisateur, uneReservation);
		assertEquals("Erreur : vous n'êtes pas l'auteur de cette réservation.", msg);
	
		unUtilisateur = new Utilisateur(15, 2, "admin", "azerty", "");
		uneReservation = new Reservation(3, null, null, null, "", -1, "");
		msg = Passerelle.confirmerReservation(unUtilisateur, uneReservation);
		assertEquals("Erreur : cette réservation est déjà confirmée.", msg);
				
		unUtilisateur = new Utilisateur(15, 2, "admin", "azerty", "admin@gmail.com");
		uneReservation = new Reservation(3, null, null, null, "", -1, "");
		msg = Passerelle.confirmerReservation(unUtilisateur, uneReservation);
		assertEquals("Erreur : cette réservation est déjà confirmée.", msg);
		
		unUtilisateur = new Utilisateur(15, 2, "admin", "azerty", "admin@gmail.com");
		uneReservation = new Reservation(11, null, null, null, "", -1, "");
		msg = Passerelle.confirmerReservation(unUtilisateur, uneReservation);
		assertEquals("Erreur : cette réservation est déjà passée.", msg);
		
		// Si ce test ne marche pas modifier BDD status 4 idReservation = 4
		// unUtilisateur = new Utilisateur(15, 2, "admin", "azerty", "admin@gmail.com");
		// uneReservation = new Reservation(4, null, null, null, "", -1, "");
		// msg = Passerelle.confirmerReservation(unUtilisateur, uneReservation);
		// assertEquals("Enregistrement effectué ; vous allez recevoir un mail de confirmation.", msg);
		
	}
	
	@Test
	public void testAnnulerReservation() {
		
		Utilisateur unUtilisateur = new Utilisateur(0, 0, "", "", "");
		Reservation uneReservation = new Reservation(0, null, null, null, null, 0, null);
		String msg = Passerelle.annulerReservation(unUtilisateur, uneReservation);
		assertEquals("Erreur : données incomplètes.", msg);
		
		unUtilisateur = new Utilisateur(0, 0, "zenesl", "passe", "");
		uneReservation = new Reservation(1, null, null, null, "", -1, "");
		msg = Passerelle.annulerReservation(unUtilisateur, uneReservation);
		assertEquals("Erreur : authentification incorrecte.", msg);
		
		unUtilisateur = new Utilisateur(15, 2, "admin", "azerty", "admin@gmail.com");
		uneReservation = new Reservation(1, null, null, null, "", -1, "");
		msg = Passerelle.confirmerReservation(unUtilisateur, uneReservation);
		assertEquals("Erreur : numéro de réservation inexistant.", msg);
		
		unUtilisateur = new Utilisateur(15, 2, "admin", "azerty", "admin@gmail.com");
		uneReservation = new Reservation(7, null, null, null, "", -1, "");
		msg = Passerelle.confirmerReservation(unUtilisateur, uneReservation);
		assertEquals("Erreur : vous n'êtes pas l'auteur de cette réservation.", msg);
				
		unUtilisateur = new Utilisateur(15, 2, "admin", "azerty", "admin@gmail.com");
		uneReservation = new Reservation(11, null, null, null, "", -1, "");
		msg = Passerelle.confirmerReservation(unUtilisateur, uneReservation);
		assertEquals("Erreur : cette réservation est déjà passée.", msg);
		
//		unUtilisateur = new Utilisateur(15, 2, "admin", "azerty", "admin@gmail.com");
//		uneReservation = new Reservation(4, null, null, null, "", -1, "");
//		msg = Passerelle.confirmerReservation(unUtilisateur, uneReservation);
//		assertEquals("Enregistrement effectué ; vous allez recevoir un mail de confirmation."", msg);		
	}

	@Test
	public void testChangerMdp(){
		String msg = Passerelle.changerDeMdp("", "", "", "");
		assertEquals("Erreur : données incomplètes.", msg);
		
		msg = Passerelle.changerDeMdp("antoineq", "admin", "123", "1234");
		assertEquals("Erreur : le nouveau mot de passe et sa confirmation sont différents.", msg);
		
		
		msg = Passerelle.changerDeMdp("antoineq", "adminnnn", "123", "123");
		assertEquals("Erreur : authentification incorrecte.", msg);
		
	}
	
	@Test
	public void testDemanderMdp(){
		String msg = Passerelle.demanderMdp("");
		assertEquals("Erreur : données incomplètes.", msg);
		
		msg = Passerelle.demanderMdp("utilisateurInexistant");
		assertEquals( "Erreur : nom d'utilisateur inexistant.", msg);
		
		msg = Passerelle.demanderMdp("glavork");
		assertEquals("Vous allez recevoir un mail avec votre nouveau mot de passe.", msg);
	}
	
	@Test
	public void testSupprimerUtilisateur(){		
		String msg = Passerelle.supprimerUtilisateur("", "", "");
		assertEquals("Erreur : données incomplètes.", msg);
		
		msg = Passerelle.supprimerUtilisateur("admin", "motdepasseeee", "monnetm");
		assertEquals( "Erreur : authentification incorrecte.", msg);
		
		msg = Passerelle.supprimerUtilisateur("admin", "azerty", "monnetmmm");
		assertEquals( "Erreur : nom d'utilisateur inexistant.", msg);
		
		msg = Passerelle.supprimerUtilisateur("admin", "azerty", "antoineq");
				assertEquals( "Erreur : cet utilisateur a passé des réservations à venir.", msg);
		
//		Crée l'utilisateur monnetm avec de lancer le test
//		msg = Passerelle.supprimerUtilisateur("admin", "azerty", "monnetm");
//		assertEquals( "Enregistrement effectué.", msg);
	}
	
	
	@Test
	public void testTesterDigicodeBatiment(){		
		String msg = Passerelle.testerDigicodeBatiment("");
		assertEquals("0", msg);
		
		msg = Passerelle.testerDigicodeBatiment("123456");
		assertEquals("0", msg);
		
		msg = Passerelle.testerDigicodeBatiment("232323");
		assertEquals("1", msg);
	}

	@Test
	public void testTesterDigicodeSalle(){
		String msg = Passerelle.testerDigicodeSalle("", "");
		assertEquals("0", msg);
		
		msg = Passerelle.testerDigicodeSalle("7", "123456");
		assertEquals("0", msg);	
		
		msg = Passerelle.testerDigicodeSalle("13", "123456");
		assertEquals("0", msg);
		
		msg = Passerelle.testerDigicodeSalle("13", "232323");
		assertEquals("1", msg);
	}
}
