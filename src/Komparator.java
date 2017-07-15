import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Komparator {
	private DrzewoPlikow drzewo1;
	private DrzewoPlikow drzewo2;
	private List<Roznica> roznice;

	public Komparator(DrzewoPlikow drzewo1, DrzewoPlikow drzewo2) {
		this.drzewo1 = drzewo1;
		this.drzewo2 = drzewo2;
		roznice = new ArrayList<>();
	}

	public void porownaj() {
		System.out.println("Wyszukuje roznice");
		drzewo1.getPliki().stream().filter(plik -> "F".equals(plik.getTyp())).forEach(plik -> {
			// System.out.println(plik);
			// plik o takiej samej nazwie, lokalizacji i sumie w drugim drzewie
			// -- bez zmiany
			Optional<Plik> bz = drzewo2.getPliki().stream().filter(plik2 -> plik2.getNazwa().equals(plik.getNazwa()))
					.filter(plik2 -> plik2.getLokalizacja().equals(plik.getLokalizacja()))
					.filter(plik2 -> plik2.getHash().equals(plik.getHash())).findFirst();
			if (bz.isPresent()) {
				plik.setSprawdzony(true);
				bz.get().setSprawdzony(true);
				Roznica r = new Roznica();
				r.setLokalizacja1(plik.getLokalizacja());
				r.setLokalizacja2(plik.getLokalizacja());
				r.setHash(plik.getHash());
				r.setNazwa(plik.getNazwa());
				r.setTyp(plik.getTyp());
				r.setStatus("BEZ_ZMIANY");
				roznice.add(r);
				return;
			}

			// plik o takiej samej nazwie, lokalizacji ale innej sumie w drugim
			// drzewie -- zmieniony
			Optional<Plik> zm = drzewo2.getPliki().stream().filter(plik2 -> plik2.getNazwa().equals(plik.getNazwa()))
					.filter(plik2 -> plik2.getLokalizacja().equals(plik.getLokalizacja()))
					.filter(plik2 -> !plik2.getHash().equals(plik.getHash())).findFirst();
			if (zm.isPresent()) {
				plik.setSprawdzony(true);
				zm.get().setSprawdzony(true);
				Roznica r = new Roznica();
				r.setLokalizacja1(plik.getLokalizacja());
				r.setLokalizacja2(plik.getLokalizacja());
				r.setHash(plik.getHash());
				r.setNazwa(plik.getNazwa());
				r.setTyp(plik.getTyp());
				r.setStatus("ZMIENIONY");
				roznice.add(r);
				return;
			}

			// plik o takiej samej nazwie i sumie, ale innej lokalizacji w
			// drugim drzewie -- przeniesiony
			Optional<Plik> prz = drzewo2.getPliki().stream().filter(plik2 -> plik2.getNazwa().equals(plik.getNazwa()))
					.filter(plik2 -> !plik2.getLokalizacja().equals(plik.getLokalizacja()))
					.filter(plik2 -> plik2.getHash().equals(plik.getHash())).findFirst();
			if (prz.isPresent()) {
				plik.setSprawdzony(true);
				prz.get().setSprawdzony(true);
				Roznica r = new Roznica();
				r.setLokalizacja1(plik.getLokalizacja());
				r.setLokalizacja2(prz.get().getLokalizacja());
				r.setHash(plik.getHash());
				r.setNazwa(plik.getNazwa());
				r.setTyp(plik.getTyp());
				r.setStatus("PRZENIESIONY");
				roznice.add(r);
				return;
			}

			// plik o takiej samej nazwie, ale innej lokalizacji i sumie w
			// drugim drzewie -- przeniesiony i zmieniony
			Optional<Plik> pz = drzewo2.getPliki().stream().filter(plik2 -> plik2.getNazwa().equals(plik.getNazwa()))
					.filter(plik2 -> !plik2.getLokalizacja().equals(plik.getLokalizacja()))
					.filter(plik2 -> !plik2.getHash().equals(plik.getHash())).findFirst();
			if (pz.isPresent()) {
				plik.setSprawdzony(true);
				pz.get().setSprawdzony(true);
				Roznica r = new Roznica();
				r.setLokalizacja1(plik.getLokalizacja());
				r.setLokalizacja2(pz.get().getLokalizacja());
				r.setHash(plik.getHash());
				r.setNazwa(plik.getNazwa());
				r.setTyp(plik.getTyp());
				r.setStatus("PRZENIESIONY_ZMIENIONY");
				roznice.add(r);
				return;
			}

		});
		// -- usuniety w drugim drzewie
		drzewo1.getPliki().stream().filter(plik1 -> "F".equals(plik1.getTyp())).filter(plik1 -> !plik1.getSprawdzony())
				.forEach(plik1 -> {
					plik1.setSprawdzony(true);
					Roznica r = new Roznica();
					r.setLokalizacja1(plik1.getLokalizacja());
					r.setLokalizacja2("");
					r.setHash(plik1.getHash());
					r.setNazwa(plik1.getNazwa());
					r.setTyp(plik1.getTyp());
					r.setStatus("USUNIETY");
					roznice.add(r);
					// return;
				});

		// -- nowy w drugim drzewie
		drzewo2.getPliki().stream().filter(plik2 -> "F".equals(plik2.getTyp())).filter(plik2 -> !plik2.getSprawdzony())
				.forEach(plik2 -> {
					plik2.setSprawdzony(true);
					Roznica r = new Roznica();
					r.setLokalizacja1("");
					r.setLokalizacja2(plik2.getLokalizacja());
					r.setHash(plik2.getHash());
					r.setNazwa(plik2.getNazwa());
					r.setTyp(plik2.getTyp());
					r.setStatus("NOWY");
					roznice.add(r);
					// return;
				});

	}

	public List<Roznica> getRoznice() {
		System.out.println("Znaleziono roznic: " + roznice.size());
		return roznice;
	}

}
