package zadatak2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Modifier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.TestUtil;
import zadatak2.Namirnica;

public class NamirnicaTest {

	private Namirnica instance;

	@Before
	public void setUp() throws Exception {
		instance = new Namirnica();
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
	}
	
	@Test
	public void atribut_naziv() {
		assertTrue("U klasi nije definisan atribut naziv", TestUtil.doesFieldExist(Namirnica.class, "naziv"));
	}
	
	@Test
	public void atribut_naziv_vidljivost() {
		assertTrue("Atribut naziv nije privatan", TestUtil.hasFieldModifier(Namirnica.class, "naziv", Modifier.PRIVATE));
	}
	
	@Test
	public void atribut_cena() {
		assertTrue("U klasi nije definisan atribut cena", TestUtil.doesFieldExist(Namirnica.class, "cena"));
	}
	
	@Test
	public void atribut_cena_vidljivost() {
		assertTrue("Atribut cena nije privatan", TestUtil.hasFieldModifier(Namirnica.class, "cena", Modifier.PRIVATE));
	}
	
	@Test
	public void metoda_setNaziv() {
		instance.setNaziv("hleb");
		String nazivValue = (String) TestUtil.getFieldValue(instance, "naziv");
		assertEquals("Nakon poziva metode setNaziv(String) sa prosledjenim argumentom \"hleb\", vrednost atributa naziv se nije promenila na tu vrednost", "hleb", nazivValue);
	}
	
	@Test
	public void metoda_getNaziv() {
		String nazivValue = (String) TestUtil.getFieldValue(instance, "naziv");

		assertEquals("Metoda getNaziv ne vraca vrednost atributa naziv", nazivValue, instance.getNaziv());
	}
	
	@Test
	public void metoda_setCena() {
		instance.setCena(50.3);
		double cenaValue = (double) TestUtil.getFieldValue(instance, "cena");
		assertEquals("Nakon poziva metode setCena(double) sa prosledjenim argumentom \"50.3\", vrednost atributa cena se nije promenila na tu vrednost", 50.3, cenaValue, 0.001);
	}
	
	@Test
	public void metoda_getCena() {
		double cenaValue = (double) TestUtil.getFieldValue(instance, "cena");
		
		assertEquals("Metoda getCena ne vraca vrednost atributa cena", cenaValue, instance.getCena(), 0.001);
	}
	
}
