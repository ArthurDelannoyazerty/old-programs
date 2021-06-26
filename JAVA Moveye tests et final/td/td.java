package td;
import java.util.Scanner;
public class td {

	public static void main(String[] args) {
		//création tableau ?*3
		String[][] T = new String[3][];
		T=creerrepertoire();
		afficherrepertoire(T);
		T=TriTableau(T);
		afficherrepertoire(T);
		recherchercontact(T);
	}
	
	//méthode créerrepertoire
	public static String[][] creerrepertoire(){
		Scanner clavier=new Scanner(System.in);
		int n;
		System.out.print("combien de numéro voulez-vous rentrer?");
		n=clavier.nextInt();
		//création tableau temporaire
		String[][] Ttemp = new String[3][n];
		//remplissage tableau
		for(int i=0;i<n;i++){
			System.out.print("donner le nom de la personne n°"+(i+1)+" : ");
			Ttemp[0][i]=clavier.next();
			System.out.print("donner le numéro de la personne n°"+(i+1)+" : ");
			Ttemp[1][i]=clavier.next();
			System.out.print("donner l'adresse de la personne n°"+(i+1)+" : ");
			Ttemp[2][i]=clavier.next();			
		}
		clavier.close();
		return Ttemp;
		
	}
	
	//méthode afficherrepertoire
	public static void afficherrepertoire(String[][] T){
			for(int i=0;i<T[0].length;i++){
				System.out.println("Contact n°"+(i+1));
				System.out.println("le nom:"+ T[0][i]);
				System.out.println("le numéro:"+ T[1][i]);
				System.out.println("l'adresse:"+ T[2][i]);
				System.out.println(" ");
			}
	}
	//méthode TriTableau
	public static String[][] TriTableau(String[][] T){
		int j,n;
		String nom,prenom,num;
		boolean echange;
		
		n=T.length;
		
		echange=false;
		do {
			echange=false;
			for(j=1;j<n-1;j++){
				if(T[0][j].compareTo(T[0][j+1])>0){
					nom=T[0][j];
					prenom=T[1][j];
					num=T[2][j];
					
					T[0][j]=T[0][j+1];
					T[1][j]=T[1][j+1];
					T[2][j]=T[2][j+1];
					
					T[0][j+1] = nom;
					T[1][j+1] = prenom;
					T[2][j+1] = num;
					
					echange=true;
				}
				
			}
		}while(echange==true);
		
		
		return T;
				
		
	}
	//méthode recherchercontact
	public static void recherchercontact(String[][] T){
		Scanner clavier=new Scanner(System.in);
		String nom = null,nomsolus = null,numsolus = null,adressesolus = null;
		int contactsolus = 0;
		System.out.println("quel est le nom du contact que vous recherchez?");
		nom=clavier.next();
		for(int i=0;i<T[0].length;i++){
			if(nom.compareTo(T[0][i])==0){
				contactsolus=(i+1);
				nomsolus=T[0][i];
				numsolus=T[1][i];
				adressesolus=T[2][i];
			}
		}
		System.out.println("Contact n°"+ contactsolus);
		System.out.println("le nom:"+ nomsolus);
		System.out.println("le numéro:"+ numsolus);
		System.out.println("l'adresse:"+ adressesolus);
		clavier.close();
	}

}
