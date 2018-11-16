package zadatak1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Modifier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.TestUtil;
import zadatak1.Vozilo;

public class VoziloTest {

	private Vozilo instance;

	@Before
	public void setUp() throws Exception {
		instance = new Vozilo();
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
	}
	
	@Test
	public void atribut_registarskiBroj() {
		assertTrue("U klasi nije definisan atribut registarskiBroj", TestUtil.doesFieldExist(Vozilo.class, "registarskiBroj"));
	}
	
	@Test
	public void atribut_registarskiBroj_vidljivost() {
		assertTrue("Atribut registarskiBroj nije privatan", TestUtil.hasFieldModifier(Vozilo.class, "registarskiBroj", Modifier.PRIVATE));
	}
	
	@Test
	public void metoda_setRegistarskiBroj() {
		instance.setRegistarskiBroj("AA112BB");
		String registarskiBrojValue = (String) TestUtil.getFieldValue(instance, "registarskiBroj");
		assertEquals("Nakon poziva metode setRegistarskiBroj(String) sa prosledjenim argumentom \"AA112BB\", vrednost atributa registarskiBroj se nije promenila na \"AA112BB\"", "AA112BB", registarskiBrojValue);
	}
	
	@Test
	public void metoda_getRegistarskiBroj() {
		String registarskiBrojValue = (String) TestUtil.getFieldValue(instance, "registarskiBroj");

		assertEquals("Metoda getRegistarskiBroj ne vraca vrednost atributa registarskiBroj", registarskiBrojValue, instance.getRegistarskiBroj());
	}
	
	@Test
	public void metoda_equals_pogresanTip() {
		assertEquals("Metoda equals ne vraca false ako je prosledjen objekat koji nije klase Vozilo", false, instance.equals(new Object()));
	}
	
	@Test
	public void metoda_equals_isti() {
		Vozilo v1 = new Vozilo();
		v1.setRegistarskiBroj("AA123BB");
		Vozilo v2 = new Vozilo();
		v2.setRegistarskiBroj("AA123BB");
		
		assertEquals("Metoda equals ne vraca \"true\" kada je pozvana nad vozilom sa registracijom \"AA123BB\", a prosledjeno je vozilo sa registarskim brojem \"AA123BB\"", true, v1.equals(v2));
	}
	
	@Test
	public void metoda_equals_razliciti() {
		Vozilo v1 = new Vozilo();
		v1.setRegistarskiBroj("AA123BB");
		Vozilo v2 = new Vozilo();
		v2.setRegistarskiBroj("cc456DD");
		
		assertEquals("Metoda equals ne vraca \"false\" kada je pozvana nad vozilom sa registracijom \"AA123BB\", a prosledjeno je vozilo sa registarskim brojem \"cc456DD\"", false, v1.equals(v2));
	}
	
	@Test
	public void metoda_toString() {
		instance.setRegistarskiBroj("AA123BB");
		assertTrue("Metoda toString ne vraca vrednost atributa registarski broj", instance.toString().contains("AA123BB"));
	}
	
}
