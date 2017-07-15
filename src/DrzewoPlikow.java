import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

public class DrzewoPlikow {
	private List<Plik> pliki = new ArrayList<>();
	private MessageDigest md;
	public List<Plik> getPliki() {
		return pliki;
	}

	private String katalogBazowy;

	public DrzewoPlikow(String katalogBazowy) throws NoSuchAlgorithmException {
		this.katalogBazowy = katalogBazowy;
		md = MessageDigest.getInstance("MD5");
		System.out.println("Katalog bazowy: " + katalogBazowy);
	}

	private String getMD5(String wejscie) {
		return DatatypeConverter.printHexBinary((md.digest(wejscie.getBytes())));
	}

	private String getPlik(String sciezka) throws IOException {
//		System.err.println("Wczytuje plik: "+sciezka);
		return new String(Files.readAllBytes(Paths.get(sciezka)));
	}

	public void Przeszukaj() throws IOException {
		Przeszukaj(new File(katalogBazowy));
	}

	public void Przeszukaj(File katalog) throws IOException {
		Plik plik = new Plik();
		plik.setNazwa(katalog.getName());
		// plik.setLokalizacja(katalog.getParent().replace(katalogBazowy, ""));
//		 plik.setHash(getMD5(getPlik(katalog.getAbsolutePath())));

		if (katalog.isDirectory()) {
			plik.setTyp("D");
			plik.setRozmiar(0l);
			plik.setHash("");
			plik.setLokalizacja(katalog.getAbsolutePath().replace(katalogBazowy, ""));
			String[] subNote = katalog.list();
			for (String filename : subNote) {
				Przeszukaj(new File(katalog, filename));
			}
		} else {
			plik.setHash(getMD5(katalog.getName()));
			plik.setHash(getMD5(getPlik(katalog.getAbsolutePath())));
			plik.setLokalizacja(katalog.getParent().replace(katalogBazowy, ""));
			plik.setTyp("F");
			plik.setRozmiar(katalog.length());
		}
		if (!plik.getLokalizacja().equals(katalogBazowy)) {
			pliki.add(plik);
		}

	}

	public void wydruk() {

		pliki.stream().sorted().forEach(plik -> System.out.println(plik.toString()));
		// pliki.stream().sorted(Plik::getLokalizacja).forEach(plik ->
		// System.out.println(plik.toString()));
	}
}
