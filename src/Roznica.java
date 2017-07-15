
public class Roznica {
	private String lokalizacja1 = "";
	private String lokalizacja2 = "";
	private String nazwa;
	private String hash;
	private String status;
	private String typ;

	public String toString() {
		if ("USUNIETY".equals(this.status)) {
			return typ + " " + lokalizacja1 + "/" + nazwa + " -\t" + " \t" + hash + " \t" + status;
		}
		if ("NOWY".equals(this.status)) {
			return typ + " -"  + " \t" + lokalizacja2 + "/" + nazwa + " \t" + hash + " \t"
					+ status;
		}
		return typ + " " + lokalizacja1 + "/" + nazwa + " \t" + lokalizacja2 + "/" + nazwa + " \t" + hash + " \t"
				+ status;
	}

	public String getLokalizacja1() {
		return lokalizacja1;
	}

	public void setLokalizacja1(String lokalizacja1) {
		this.lokalizacja1 = lokalizacja1;
	}

	public String getLokalizacja2() {
		return lokalizacja2;
	}

	public void setLokalizacja2(String lokalizacja2) {
		this.lokalizacja2 = lokalizacja2;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}
}
