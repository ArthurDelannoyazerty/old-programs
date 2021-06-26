package hashmaptest;

import java.util.HashMap;

public class hashmap {

	public static void main(String[] args) {
		HashMap<String, Integer> moyenneListe = new HashMap<String, Integer>();
		int a = 100;
		int b = 100;
		moyenneListe.put("String:"+Integer.toString(a), b);
		System.out.println(moyenneListe);
		System.out.println(moyenneListe.get("String:"+Integer.toString(a)));
	}

}
