package exercice6;
import java.util.Scanner;
public class ex6 {

	public static void main(String[] args) {
		int valeur;
		int somme;
		valeur=5;
		somme=0;
		Scanner clavier = new Scanner(System.in);
		while (valeur!=0){
			System.out.print("Donner la valeur souhaitée : ");
			valeur=clavier.nextInt();
			somme=somme+valeur;
		}
		System.out.print("Somme =" + somme);
		clavier.close();
	}

}
