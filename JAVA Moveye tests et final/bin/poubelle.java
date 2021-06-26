package bin;

public class poubelle {

	public static void main(String[] args) {
		int a=0;
		for(int i=99; i>=0; i--) {
			a = a+(i*i);
			System.out.println(a);
		}
		System.out.println(a);
	}

}
