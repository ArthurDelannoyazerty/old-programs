package note;
import java.util.Scanner;
public class mauvaiseNote {

	//début méthode principale
	public static void main(String[] args) {
		//creation clavier
		Scanner clavier=new Scanner(System.in);
		
		//déclaration de variables
		int v;
		
		System.out.print("combien de notes voulez-vous rentrer? ");
		v=clavier.nextInt();
		//création du tableau
		float notes[] = new float[v];
		
		//appel méthode remplir
		notes = remplir(notes);
		//appel méthode afficher
		afficher(notes);
		//appel méthode moyenne
		moyenne(notes);

		clavier.close();
	}//fin méthode public void
	
	//méthode remplir
	public static float[] remplir(float[] tableau){
		Scanner clavier=new Scanner(System.in);
		int taille;
		taille=tableau.length;
		for(int j=0;j<taille;j++){
			System.out.println("donner la note n°"+ (j+1) +" : ");
			tableau[j]=clavier.nextFloat();
			
		}
		clavier.close();
		return tableau;
		
	}//fin méthode remplir
	
	//début méthode afficher
	public static void afficher(float[] tableau){
		int t,i;
		t=tableau.length;
		for(i=0;i<t;i++){
			System.out.println("la note n°"+ (i+1) +" est : "+ tableau[i]);
		}
		
	}//fin méthode afficher
	
	//début méthode moyenne
	public static void moyenne(float[] tableau){
		float moyenne;
		int t;
		t=tableau.length;
		moyenne=0;
		for(int i=0;i<t;i++){
			moyenne=moyenne + tableau[i];
		}
		moyenne=moyenne/t;
		System.out.println(" ");
		System.out.println("la moyenne est : "+moyenne);
	}//fin méthode moyenne

}
