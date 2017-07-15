
public class Plik implements Comparable {
	private String typ;
	private String nazwa;
	private String lokalizacja;
	private Long rozmiar;
	private String hash;
	private Boolean sprawdzony=false;

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public String toString() {
		return typ + " " + lokalizacja + " \t" + nazwa + " \t" + rozmiar + " " + hash;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getLokalizacja() {
		return lokalizacja;
	}

	public void setLokalizacja(String lokalizacja) {
		if (lokalizacja.startsWith("\\")) {
			lokalizacja = lokalizacja.substring(1);
		}
		this.lokalizacja = lokalizacja;
	}

	public Long getRozmiar() {
		return rozmiar;
	}

	public void setRozmiar(Long rozmiar) {
		this.rozmiar = rozmiar;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	@Override
	public int compareTo(Object arg0) {
		Plik inny = (Plik) arg0;
		return this.nazwa.compareTo(inny.nazwa);
	}

	public Boolean getSprawdzony() {
		return sprawdzony;
	}

	public void setSprawdzony(Boolean sprawdzony) {
		this.sprawdzony = sprawdzony;
	}

}
