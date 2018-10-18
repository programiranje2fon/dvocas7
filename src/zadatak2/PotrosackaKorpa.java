package zadatak2;

public class PotrosackaKorpa {

	private Namirnica[] namirnice;

	public PotrosackaKorpa(int kapacitet) {
		if (kapacitet > 0) {
			namirnice = new Namirnica[kapacitet];
		} else {
			System.out.println("GRESKA");
			namirnice = new Namirnica[10];
		}
	}
	
	public void dodajUKorpu(Namirnica namirnica) {
		for (int i = 0; i < namirnice.length; i++) {
			if (namirnice[i] == null) {
				namirnice[i] = namirnica;
				return;
			}
		}
		
		System.out.println("NEMA MESTA");
	}
	
	public double izracunajUkupnuCenu() {
		double ukupnaCena = 0;
		
		for (int i = 0; i < namirnice.length; i++) {
			if (namirnice[i] != null) {
				ukupnaCena += namirnice[i].getCena();
			}
		}
		
		return ukupnaCena;
	}
	
	public Namirnica pronadjiNajskuplju() {
		Namirnica najskuplja = null;
		
		for (int i = 0; i < namirnice.length; i++) {
			if (namirnice[i] != null) {
				if (najskuplja == null || namirnice[i].getCena() > najskuplja.getCena()) {
					najskuplja = namirnice[i];
				}
			}
		}
		
		return najskuplja;
	}
}
