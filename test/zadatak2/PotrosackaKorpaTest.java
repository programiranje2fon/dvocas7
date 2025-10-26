package zadatak2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.TestUtil;
import zadatak2.Namirnica;
import zadatak2.PotrosackaKorpa;

public class PotrosackaKorpaTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	private PotrosackaKorpa instance;

	@Before
	public void setUp() throws Exception {
		instance = new PotrosackaKorpa(5);
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
	public void atribut_namirnice() {
		assertTrue("U klasi nije definisan atribut namirnice", TestUtil.doesFieldExist(PotrosackaKorpa.class, "namirnice"));
	}
	
	@Test
	public void atribut_namirnice_vidljivost() {
		assertTrue("Atribut namirnice nije privatan", TestUtil.hasFieldModifier(PotrosackaKorpa.class, "namirnice", Modifier.PRIVATE));
	}
	
	@Test
	public void konstruktor_PotrosackaKorpa() {
		Namirnica[] namirniceValue = (Namirnica[]) TestUtil.getFieldValue(instance, "namirnice");
		
		assertTrue("Za pozvani konstruktor sa argumentom 5, atribut namirnice nije inicijalizovan", namirniceValue != null);
		assertEquals("Za pozvani konstruktor sa argumentom 5, atribut namirnice nije kapaciteta 5, vec "+namirniceValue.length, 5, namirniceValue.length);
	}
	
	@Test
	public void konstruktor_PotrosackaKorpa_kapacitetManjiOd1() {
		PotrosackaKorpa g1 = new PotrosackaKorpa(0);
		
		assertTrue("NE ispisuje se rec GRESKA u slucaju da je prosledjeno 0 konstruktoru PotrosackaKorpa()", outContent.toString().trim().equalsIgnoreCase("GRESKA"));
		
		Namirnica[] namirniceValue = (Namirnica[]) TestUtil.getFieldValue(g1, "namirnice");
		
		assertTrue("Za pozvani konstruktor sa argumentom 0, atribut namirnice nije inicijalizovan", namirniceValue != null);
		assertEquals("Za pozvani konstruktor sa argumentom 0, atribut namirnice nema kapacitet 10, vec "+namirniceValue.length, 10, namirniceValue.length);
	}
	
	@Test
	public void metoda_dodajUKorpu_imaMesta() {
		Namirnica n1 = new Namirnica();
		n1.setNaziv("sok");
		instance.dodajUKorpu(n1);
		
		Namirnica[] namirniceValue = (Namirnica[]) TestUtil.getFieldValue(instance, "namirnice");
		
		assertTrue("Nakon sto se doda nova namirnica u korpu, a u nizu namirnice ima slobodnih mesta, ta namirnica se ne nalazi u nizu namirnice", Arrays.stream(namirniceValue).anyMatch(m -> m == n1));
	}
	
	@Test
	public void metoda_dodajUKorpu_nemaMesta() {
		Namirnica n1 = new Namirnica();
		Namirnica n2 = new Namirnica();
		
		PotrosackaKorpa pk1 = new PotrosackaKorpa(1);
		pk1.dodajUKorpu(n1);
		pk1.dodajUKorpu(n2);
		
		assertTrue("NE ispisuje se rec NEMA MESTA u slucaju da je dodata nova namirnica u korpu u kojoj nema slobodnih mesta", outContent.toString().trim().equalsIgnoreCase("NEMA MESTA"));
	}
	
	@Test
	public void metoda_izracunajUkupnuCenu() {
		Namirnica n1 = new Namirnica();
		n1.setCena(10);
		Namirnica n2 = new Namirnica();
		n2.setCena(15);
		Namirnica n3 = new Namirnica();
		n3.setCena(20);
		
		instance.dodajUKorpu(n1);
		instance.dodajUKorpu(n2);
		instance.dodajUKorpu(n3);
		
		assertEquals("Kada su u korpu dodate namirnice sa cenama 10, 15 i 20 dinara, metoda ne vraca ukupnu cenu 45", 45, instance.izracunajUkupnuCenu(), 0.001);
	}
	
	@Test
	public void metoda_pronadjiNajskuplju() {
		Namirnica n1 = new Namirnica();
		n1.setCena(10);
		Namirnica n2 = new Namirnica();
		n2.setCena(15);
		Namirnica n3 = new Namirnica();
		n3.setCena(20);
		
		instance.dodajUKorpu(n1);
		instance.dodajUKorpu(n2);
		instance.dodajUKorpu(n3);
		
		assertTrue("Kada su u korpu dodate namirnice sa cenama 10, 15 i 20 dinara, metoda ne vraca kao najskuplju trecu namirnicu", instance.pronadjiNajskuplju() == n3);
	}
}
