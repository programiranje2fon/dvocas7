package test.zadatak1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.TestUtil;
import zadatak1.Garaza;
import zadatak1.ParkingMesto;
import zadatak1.Vozilo;

public class GarazaTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	private Garaza instance;

	@Before
	public void setUp() throws Exception {
		instance = new Garaza(5);
		System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
		System.setOut(originalOut);
	    System.setErr(originalErr);
	}
	
	@Test
	public void atribut_mesta() {
		assertTrue("U klasi nije definisan atribut mesta", TestUtil.doesFieldExist(Garaza.class, "mesta"));
	}
	
	@Test
	public void atribut_mesta_vidljivost() {
		assertTrue("Atribut mesta nije privatan", TestUtil.hasFieldModifier(Garaza.class, "mesta", Modifier.PRIVATE));
	}
	
	@Test
	public void konstruktor_GarazaInt() {
		ParkingMesto[] mestaValue = (ParkingMesto[]) TestUtil.getFieldValue(instance, "mesta");
		
		assertTrue("Za pozvani konstruktor sa argumentom 5, atribut mesta nije inicijalizovan", mestaValue != null);
		assertEquals("Za pozvani konstruktor sa argumentom 5, atribut namirnice nije kapaciteta 5, vec "+mestaValue.length, 5, mestaValue.length);
		
		for (int i = 0; i < mestaValue.length; i++) {
			if (mestaValue[i] == null) {
				fail("Za pozvani konstruktor sa argumentom 5, nisu inicijalizovani svi elementi atributa mesta");
			} else if (!mestaValue[i].isSlobodno()) {
				fail("Za pozvani konstruktor sa argumentom 5, u nizu mesta nemaju svi elementi u atributu slobodan vrednost \"true\"");
			}
		}
	}
	
	@Test
	public void konstruktor_GarazaInt_brojMestaManjiOd1() {
		Garaza g1 = new Garaza(0);
		
		assertTrue("NE ispisuje se rec GRESKA u slucaju da je prosledjeno 0 konstruktoru Garaza(int)", outContent.toString().trim().equalsIgnoreCase("GRESKA"));
		
		ParkingMesto[] mestaValue = (ParkingMesto[]) TestUtil.getFieldValue(g1, "mesta");
		
		assertTrue("Za pozvani konstruktor sa argumentom 0, atribut mesta nije inicijalizovan", mestaValue != null);
		assertEquals("Za pozvani konstruktor sa argumentom 0, atribut mesta nema kapacitet 40, vec "+mestaValue.length, 40, mestaValue.length);
		
		for (int i = 0; i < mestaValue.length; i++) {
			if (mestaValue[i] == null) {
				fail("Za pozvani konstruktor sa argumentom 0, nisu inicijalizovani svi elementi atributa mesta");
			} else if (!mestaValue[i].isSlobodno()) {
				fail("Za pozvani konstruktor sa argumentom 0, u nizu mesta nemaju svi elementi u atributu slobodan vrednost \"true\"");
			}
		}
	}
	
	@Test
	public void konstruktor_Garaza() {
		ParkingMesto[] mesta = new ParkingMesto[1];
		
		Garaza g1 = new Garaza(mesta);
		
		ParkingMesto[] mestaValue = (ParkingMesto[]) TestUtil.getFieldValue(g1, "mesta");
		
		assertTrue("Za pozvani konstruktor Garaza() sa prosledjenim nizom objekata ParkingMesto, atribut mesta ne sadrzi prosledjeni niz", mesta == mestaValue);
	}
	
	@Test
	public void konstruktor_Garaza_null() {
		new Garaza(null);
		
		assertTrue("NE ispisuje se rec GRESKA u slucaju da je prosledjeno null konstruktoru Garaza()", outContent.toString().trim().equalsIgnoreCase("GRESKA"));
	}
	
	@Test
	public void metoda_daLiImaSlobodnih_True() {
		Garaza g1 = new Garaza(1);
		
		assertEquals("U slucaju kada nije uvedeno nijedno vozilo, metoda ne vraca true", true, g1.daLiImaSlobodnih());
	}
	
	@Test
	public void metoda_daLiImaSlobodnih_False() {
		Vozilo v1 = new Vozilo();
		
		Garaza g1 = new Garaza(1);
		g1.uvedi(v1);
		
		assertEquals("U slucaju kada je uvedeno jedno vozilo, a kapacitet garaze je 1, metoda ne vraca false", false, g1.daLiImaSlobodnih());
	}
	
	@Test
	public void metoda_uvedi() {
		Vozilo v1 = new Vozilo();
		v1.setRegistarskiBroj("AB123XY");
		instance.uvedi(v1);
		
		ParkingMesto[] mestaValue = (ParkingMesto[]) TestUtil.getFieldValue(instance, "mesta");
		
		assertTrue("Nakon sto se uvede novo vozilo, a u nizu mesta ima slobodnih mesta, to vozilo se ne nalazi u nizu mesta", Arrays.stream(mestaValue).anyMatch(m -> m.getVozilo() != null && m.getVozilo() == v1));
	}
	
	@Test
	public void metoda_uvedi_nemaMesta() {
		Vozilo v1 = new Vozilo();
		Vozilo v2 = new Vozilo();
		
		Garaza g1 = new Garaza(1);
		g1.uvedi(v1);
		g1.uvedi(v2);
		
		assertTrue("NE ispisuje se rec NEMA MESTA u slucaju da je uvedeno novo vozilo u garazu u kojoj nema slobodnih mesta", outContent.toString().trim().equalsIgnoreCase("NEMA MESTA"));
	}
	
	@Test
	public void metoda_izvedi() {
		Vozilo v1 = new Vozilo();
		v1.setRegistarskiBroj("AB123XY");
		Vozilo v2 = new Vozilo();
		v2.setRegistarskiBroj("YU763KJ");
		Vozilo v3 = new Vozilo();
		v3.setRegistarskiBroj("OK312SA");
		
		instance.uvedi(v1);
		instance.uvedi(v2);
		instance.uvedi(v3);
		
		instance.izvedi(v3);
		
		ParkingMesto[] mestaValue = (ParkingMesto[]) TestUtil.getFieldValue(instance, "mesta");
		
		assertTrue("U slucaju da se pozove metoda sa prosledjenim vozilom sa registracijom koju vec poseduje vozilo koje je na parkingu, to vozilo se ne izbaci iz niza", !Arrays.stream(mestaValue).anyMatch(m -> m.getVozilo() != null && m.getVozilo().equals(v3)));

	}
}
