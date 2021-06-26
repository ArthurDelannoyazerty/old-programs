package methode;

import java.util.Scanner;

public class classpourmethode {

	public static void main(String[] args) {
		double x,P;
		int n;
		
		Scanner clavier=new Scanner(System.in);
		
		System.out.println("Donner le x puis le n");
		x=clavier.nextDouble();
		n=clavier.nextInt();
		
		P=somme(x,n);
		System.out.println("somme géométrique vaut : " + P);
		clavier.close();
	}
	public static double puissance(double x, int n){
		
		//initialisation
		int k;
		double puiss;
		puiss = 1;
		//traitement
		
		for(k=1;k<=n;k++){
			puiss=puiss*x;
		}		
		return puiss;		
	}
	public static double somme(double x, int n){
		//variable locale
		double S;
		int i;
		S=0;
		//initialisation de la somme :
		for(i=0;i<=n;i++){
			S=S+puissance(x,i);
		}
		
		return S;		
	}

}
