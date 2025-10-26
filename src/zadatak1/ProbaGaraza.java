package zadatak1;

public class ProbaGaraza {

	public static void main(String[] args) {
		Vozilo v1 = new Vozilo();
		v1.setRegistarskiBroj("BG123-AB");
		
		Vozilo v2 = new Vozilo();
		v2.setRegistarskiBroj("NS456-CD");
		
		
		Garaza garaza1 = new Garaza(10);
		garaza1.uvedi(v1);
		garaza1.uvedi(v2);
		
		garaza1.ispisi();
		
		garaza1.izvedi(v1);
		garaza1.ispisi();
	}
}
