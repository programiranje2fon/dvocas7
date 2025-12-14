package zadatak2;

public class ProbaPotrosackaKorpa {

    public static void main(String[] args) {
        Namirnica n1 = new Namirnica();
        n1.setNaziv("hleb");
        n1.setCena(50);

        Namirnica n2 = new Namirnica();
        n2.setNaziv("mleko");
        n2.setCena(80);

        Namirnica n3 = new Namirnica();
        n3.setNaziv("cokolada");
        n3.setCena(100);

        PotrosackaKorpa korpa1 = new PotrosackaKorpa(15);
        korpa1.dodajUKorpu(n1);
        korpa1.dodajUKorpu(n2);
        korpa1.dodajUKorpu(n3);

        System.out.println(korpa1.izracunajUkupnuCenu());

        Namirnica najskuplja = korpa1.pronadjiNajskuplju();

        if (najskuplja != null) {
            System.out.println(najskuplja.getNaziv());
        }
    }

}
