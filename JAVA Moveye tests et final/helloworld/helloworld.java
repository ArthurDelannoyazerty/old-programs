package helloworld;

import java.util.Scanner;

public class helloworld {

	public static void main(String[] args) {
		System.out.println("hello world");
		//déclaration des variables
			int j,n ;
			float u;
			
			//création des objets et lecture clavier
			Scanner clavier = new Scanner(System.in);
			
			//initialisation
			u=0;
			
			//saisie des valeurs
			System.out.println("Donner la valeur de n : ");
			n=clavier.nextInt();
			
			//boucle "pour"
			for (j=1;j<=n;j++) {
				u=u+(float)1/j;	}
			
			//Afichage
			System.out.println("La valeur de u est : "+u);
			clavier.close();
			
			
			//boucle infini
			int a=0;
			int b=4;
			while (a<100) {
				b=b+1;
				System.out.println("b= "+b);
				a=a+1;
			}
			System.out.println(System.currentTimeMillis());
			
			
	}

}
