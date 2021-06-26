import java.util.Scanner;
public class ex7_table_de_multiplication {

	public static void main(String[] args) {
		Scanner clavier = new Scanner(System.in);
		int valeur,reponse;
		System.out.print("Donner la valeur souhaitée : ");
		valeur=clavier.nextInt();
		for(int n=0;n<11;n++){
			reponse = n*valeur;
			System.out.print(valeur + "*" + n +"=" + reponse +"\n");
		}	
		
		
		clavier.close();

	}

}
