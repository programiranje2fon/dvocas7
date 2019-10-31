package zadatak1;

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
		
		for (ParkingMesto parkingMesto : mestaValue) {
			if (parkingMesto == null) {
				fail("Za pozvani konstruktor sa argumentom 5, nisu inicijalizovani svi elementi atributa mesta");
			} else if (!parkingMesto.isSlobodno()) {
				fail("Za pozvani konstruktor sa argumentom 5, u nizu mesta nemaju svi elementi u atributu slobodan vrednost \"true\"");
			}
		}
	}
	
	@Test
	public void konstruktor_GarazaInt_brojMestaManjiOd1() {
		Garaza g1 = new Garaza(0);
		
		assertTrue("NE ispisuje se rec GRESKA u slucaju da je prosledjeno 0 konstruktoru Garaza(int)", outContent.toString().toLowerCase().contains("GRESKA".toLowerCase()));
		
		ParkingMesto[] mestaValue = (ParkingMesto[]) TestUtil.getFieldValue(g1, "mesta");
		
		assertTrue("Za pozvani konstruktor sa argumentom 0, atribut mesta nije inicijalizovan", mestaValue != null);
		assertEquals("Za pozvani konstruktor sa argumentom 0, atribut mesta nema kapacitet 40, vec "+mestaValue.length, 40, mestaValue.length);
		
		for (ParkingMesto parkingMesto : mestaValue) {
			if (parkingMesto == null) {
				fail("Za pozvani konstruktor sa argumentom 0, nisu inicijalizovani svi elementi atributa mesta");
			} else if (!parkingMesto.isSlobodno()) {
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
		
		assertTrue("NE ispisuje se rec GRESKA u slucaju da je prosledjeno null konstruktoru Garaza()", outContent.toString().toLowerCase().contains("GRESKA".toLowerCase()));
	}
	
	@Test
	public void metoda_ispisi() {
		Vozilo v1 = new Vozilo();
		v1.setRegistarskiBroj("AB123XY");
		instance.uvedi(v1);
		
		instance.ispisi();
		
		String[] linijeIspisa = outContent.toString().split(System.lineSeparator());
		
		assertTrue("Ako je garaza kreirana sa pet parking mesta, a samo prvo je zauzeto, prva linija ispisa treba da sadrzi broj \"1.\"", linijeIspisa[0].contains("1."));
		assertTrue("Ako je garaza kreirana sa pet parking mesta, a samo prvo je zauzeto, prva linija ispisa treba da sadrzi rec \"ZAUZETO\"", linijeIspisa[0].toLowerCase().contains("zauzeto"));
		assertTrue("Ako je garaza kreirana sa pet parking mesta, a samo prvo je zauzeto, prva linija ispisa treba da sadrzi registarski broj vozila", linijeIspisa[0].contains("AB123XY"));
		assertTrue("Ako je garaza kreirana sa pet parking mesta, a samo prvo je zauzeto, druga linija ispisa treba da sadrzi broj \"2.\"", linijeIspisa[1].contains("2."));
		assertTrue("Ako je garaza kreirana sa pet parking mesta, a samo prvo je zauzeto, druga linija ispisa treba da sadrzi rec \"SLOBODNO\"", linijeIspisa[1].toLowerCase().contains("slobodno"));
		assertTrue("Ako je garaza kreirana sa pet parking mesta, a samo prvo je zauzeto, treca linija ispisa treba da sadrzi broj \"3.\"", linijeIspisa[2].contains("3."));
		assertTrue("Ako je garaza kreirana sa pet parking mesta, a samo prvo je zauzeto, treca linija ispisa treba da sadrzi rec \"SLOBODNO\"", linijeIspisa[3].toLowerCase().contains("slobodno"));
		assertTrue("Ako je garaza kreirana sa pet parking mesta, a samo prvo je zauzeto, cetvrta linija ispisa treba da sadrzi broj \"4.\"", linijeIspisa[3].contains("4."));
		assertTrue("Ako je garaza kreirana sa pet parking mesta, a samo prvo je zauzeto, cetvrta linija ispisa treba da sadrzi rec \"SLOBODNO\"", linijeIspisa[3].toLowerCase().contains("slobodno"));
		assertTrue("Ako je garaza kreirana sa pet parking mesta, a samo prvo je zauzeto, peta linija ispisa treba da sadrzi broj \"5.\"", linijeIspisa[4].contains("5."));
		assertTrue("Ako je garaza kreirana sa pet parking mesta, a samo prvo je zauzeto, peta linija ispisa treba da sadrzi rec \"SLOBODNO\"", linijeIspisa[4].toLowerCase().contains("slobodno"));


		String ocekivaniIspis =
				"1. ZAUZETO, Registarski broj: AB123XY" + System.lineSeparator() +
				"2. SLOBODNO" + System.lineSeparator() +
				"3. SLOBODNO" + System.lineSeparator() +
				"4. SLOBODNO" + System.lineSeparator() +
				"5. SLOBODNO";
		
		ocekivaniIspis = ocekivaniIspis.replaceAll("\\s","");
		String ispis = outContent.toString().replaceAll("\\s","");
		
		assertTrue("Metoda ne ispisuje dobro sve podatke o parking mestima", ispis.equalsIgnoreCase(ocekivaniIspis));
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
		
		assertTrue("NE ispisuje se rec NEMA MESTA u slucaju da je uvedeno novo vozilo u garazu u kojoj nema slobodnih mesta", outContent.toString().toLowerCase().contains("NEMA MESTA".toLowerCase()));
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
