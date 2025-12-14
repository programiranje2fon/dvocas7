package zadatak1;

public class Vozilo {

    private String registarskiBroj;

    public String getRegistarskiBroj() {
        return registarskiBroj;
    }

    public void setRegistarskiBroj(String registarskiBroj) {
        this.registarskiBroj = registarskiBroj;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vozilo)) {
            return false;
        }

        Vozilo v1 = (Vozilo) obj;

        return this.registarskiBroj.equals(v1.registarskiBroj);
    }

    @Override
    public String toString() {
        return "Registarski broj: " + registarskiBroj;
    }

}
