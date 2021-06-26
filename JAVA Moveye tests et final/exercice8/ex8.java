package exercice8;
import java.util.Scanner;
public class ex8 {

	public static void main(String[] args) {
		Scanner clavier = new Scanner(System.in);
		int valeur,nbvaleurs,sp,sn;
		valeur=0;
		sp=0;
		sn=0;
		System.out.print("Donner la valeur souhaitée : ");
		nbvaleurs=clavier.nextInt();
		for(int n=0;n<nbvaleurs;n++){
			System.out.print("Entrer la valeur souhaitée : "+ "\n");
			valeur=clavier.nextInt();
			if(valeur<0){
				sn=sn+valeur;
			}
			else{
				sp=sp+valeur;
			}
			System.out.print("sp=  "+ sp+ "\n");
			System.out.print("sn=  "+ sn+ "\n");
		}
		System.out.print("sp=  "+ sp+ "\n");
		System.out.print("sn=  "+ sn+ "\n");
		clavier.close();
	}

}
