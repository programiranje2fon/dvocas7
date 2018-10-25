package zadatak1;

public class ParkingMesto {

	private boolean slobodno;
	private Vozilo vozilo;

	public boolean isSlobodno() {
		return slobodno;
	}

	public void setSlobodno(boolean slobodno) {
		this.slobodno = slobodno;
	}

	public Vozilo getVozilo() {
		return vozilo;
	}

	public void setVozilo(Vozilo vozilo) {
		this.vozilo = vozilo;
	}
	
	@Override
	public String toString() {
		if (slobodno) {
			return "SLOBODNO";
		} else {
			return "ZAUZETO, " + vozilo.toString();
		}
	}

}
