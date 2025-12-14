package zadatak1;

public class Garaza {

    private ParkingMesto[] mesta;

    public Garaza(int brojMesta) {
        if (brojMesta > 0) {
            mesta = new ParkingMesto[brojMesta];
        } else {
            System.out.println("GRESKA");
            mesta = new ParkingMesto[40];
        }

        for (int i = 0; i < mesta.length; i++) {
            mesta[i] = new ParkingMesto();
            mesta[i].setSlobodno(true);
        }
    }

    public Garaza(ParkingMesto[] mesta) {
        if (mesta != null) {
            this.mesta = mesta;
        } else {
            System.out.println("GRESKA");
        }
    }

    public void ispisi() {
        for (int i = 0; i < mesta.length; i++) {
            System.out.println((i+1) + ". " + mesta[i].toString());
        }
    }

    public void uvedi(Vozilo novoVozilo) {
        for (int i = 0; i < mesta.length; i++) {
            if (mesta[i].isSlobodno()) {
                mesta[i].setVozilo(novoVozilo);
                mesta[i].setSlobodno(false);
                return;
            }
        }
        System.out.println("NEMA MESTA");

    /*
    for (ParkingMesto parkingMesto : mesta) {
        if (parkingMesto.isSlobodno()) {
            parkingMesto.setVozilo(novoVozilo);
            parkingMesto.setSlobodno(false);
            return;
        }
    }
    System.out.println("NEMA MESTA");
    */
    }

    public void izvedi(Vozilo voziloZaIzlaz) {
        for (int i = 0; i < mesta.length; i++) {
            if (!mesta[i].isSlobodno() && mesta[i].getVozilo().equals(voziloZaIzlaz)) {
                mesta[i].setVozilo(null);
                mesta[i].setSlobodno(true);
                break;
            }
        }

    /*
    for (ParkingMesto parkingMesto : mesta) {
        if (!parkingMesto.isSlobodno() && parkingMesto.getVozilo().equals(voziloZaIzlaz)) {
            parkingMesto.setVozilo(null);
            parkingMesto.setSlobodno(true);
            break;
        }
    }
    */
    }

}
