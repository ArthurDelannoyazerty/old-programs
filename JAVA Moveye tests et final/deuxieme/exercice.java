package deuxieme;
import java.util.Scanner;
public class exercice {

	public static void main(String[] args) {
		
		float a;
		float b;
		float c;
		
		Scanner clavier = new Scanner(System.in);
		
		System.out.print("Donner la valeur de a : ");
		a=clavier.nextFloat();
		
		System.out.print("Donner la valeur de b : ");
		b=clavier.nextFloat();
		
		System.out.print("Donner la valeur de c : ");
		c=clavier.nextFloat();
		
		if (a>b){
			if (a>c){
				System.out.print("le plus grand est a :" + a);
			}
		}
		else if (b>a){
			if(b>c){
				System.out.print("le plus grand est b :" + b);
			}
		}
		else if (c>a){
			if(c>b){
				System.out.print("le plus grand est c :" + c);
			}
		}
		clavier.close();

	}

}
