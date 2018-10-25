package zadatak1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Modifier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.TestUtil;
import zadatak1.ParkingMesto;
import zadatak1.Vozilo;

public class ParkingMestoTest {

	private ParkingMesto instance;

	@Before
	public void setUp() throws Exception {
		instance = new ParkingMesto();
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
	}
	
	@Test
	public void atribut_slobodno() {
		assertTrue("U klasi nije definisan atribut slobodno", TestUtil.doesFieldExist(ParkingMesto.class, "slobodno"));
	}
	
	@Test
	public void atribut_slobodno_vidljivost() {
		assertTrue("Atribut slobodno nije privatan", TestUtil.hasFieldModifier(ParkingMesto.class, "slobodno", Modifier.PRIVATE));
	}
	
	@Test
	public void atribut_vozilo() {
		assertTrue("U klasi nije definisan atribut vozilo", TestUtil.doesFieldExist(ParkingMesto.class, "vozilo"));
	}
	
	@Test
	public void atribut_vozilo_vidljivost() {
		assertTrue("Atribut vozilo nije privatan", TestUtil.hasFieldModifier(ParkingMesto.class, "vozilo", Modifier.PRIVATE));
	}
	
	@Test
	public void metoda_setSlobodno_true() {
		instance.setSlobodno(true);
		boolean slobodnoValue = (boolean) TestUtil.getFieldValue(instance, "slobodno");
		assertEquals("Nakon poziva metode setSlobodno(boolean) sa prosledjenim argumentom \"true\", atribut slobodno ima vrednost \""+slobodnoValue+"\"", true, slobodnoValue);
	}
	
	@Test
	public void metoda_setSlobodno_false() {
		instance.setSlobodno(false);
		boolean slobodnoValue = (boolean) TestUtil.getFieldValue(instance, "slobodno");
		assertEquals("Nakon poziva metode setSlobodno(boolean) sa prosledjenim argumentom \"false\", atribut slobodno ima vrednost \""+slobodnoValue+"\"", false, slobodnoValue);
	}
	
	@Test
	public void metoda_isSlobodno_false() {
		instance.setSlobodno(false);
		assertEquals("Metoda isSlobodno() vraca \"true\", a vrednost atributa slobodno je \"false\"", false, instance.isSlobodno());
	}
	
	@Test
	public void metoda_isSlobodno_true() {
		instance.setSlobodno(true);
		assertEquals("Metoda isSlobodno() vraca \"false\", a vrednost atributa slobodno je \"true\"", true, instance.isSlobodno());
	}
	
	@Test
	public void metoda_setVozilo() {
		Vozilo v = new Vozilo();
		instance.setVozilo(v);
		
		Vozilo voziloValue = (Vozilo) TestUtil.getFieldValue(instance, "vozilo");

		assertTrue("Metoda setVozilo() ne postavlja odgovarajucu vrednost u atribut vozilo", v == voziloValue);
	}
	
	@Test
	public void metoda_getVozilo() {
		Vozilo voziloValue = (Vozilo) TestUtil.getFieldValue(instance, "vozilo");
		
		assertTrue("Metoda getVozilo ne vraca vrednost atributa vozilo", voziloValue == instance.getVozilo());
	}
	
	@Test
	public void metoda_toString_slobodno() {
		instance.setSlobodno(true);
		assertEquals("Metoda toString ne vraca String u odgovarajucem formatu", "SLOBODNO", instance.toString());
	}
	
	@Test
	public void metoda_toString_zauzeto() {
		Vozilo v1 = new Vozilo();
		v1.setRegistarskiBroj("AA123BB");
		
		instance.setVozilo(v1);
		instance.setSlobodno(false);
		assertEquals("Metoda toString ne vraca String u odgovarajucem formatu", "ZAUZETO, "+instance.getVozilo().toString(), instance.toString());
	}
	
}
