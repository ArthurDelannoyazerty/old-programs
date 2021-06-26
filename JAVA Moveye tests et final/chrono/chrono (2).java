package chrono;

public class chrono {

	public static void main(String[] args) {
		long chrono, chrono0, chronod;
		long chronodiv, chronodivd;
		chronodivd=0;
		chrono0 = java.lang.System.currentTimeMillis();
		while(true) {
			
			chrono = java.lang.System.currentTimeMillis();
			chronod = chrono - chrono0;
			chronodiv = chronod/1000;
			if(chronodiv != chronodivd) {
				System.out.println(chronodiv);
				chronodivd=chronodiv;
				//là où on lance la méthode
			}			
		}

	}

}
