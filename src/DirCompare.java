import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class DirCompare {

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		DrzewoPlikow w1 = new DrzewoPlikow("D:\\dane\\projekty\\springboot\\DirCompare\\w1");
		w1.Przeszukaj();
		w1.wydruk();
		
		DrzewoPlikow w2 = new DrzewoPlikow("D:\\dane\\projekty\\springboot\\DirCompare\\w2");
		w2.Przeszukaj();
		w2.wydruk();
		
		Komparator komparator=new Komparator(w1, w2);
		komparator.porownaj();
		System.out.println("Raport roznic:");
		komparator.getRoznice().forEach(System.out::println);
	}

}
