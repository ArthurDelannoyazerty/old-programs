package seconddegre;

import java.util.Scanner;

public class classs {

	public static void main(String[] args) {
		float a,b,c,reponse;
		float delta;
		float x1 = 0;
		float x2 = 0;
		
		Scanner clavier = new Scanner(System.in);
		
		System.out.print("Donner la valeur de a : ");
		a=clavier.nextInt();
		
		System.out.print("Donner la valeur de b : ");
		b=clavier.nextInt();
		
		System.out.print("Donner la valeur de c : ");
		c=clavier.nextInt();

		delta= b*b-4*a*c;
		if(delta==0){
			if(a==0){
				System.out.println("division par o!");
			}
			 reponse= -b/(2*a);
			 System.out.println("la fonction a comme solution : "+ reponse);
		}
		else if (delta<0){
			System.out.println("la fonction n'a pas de solution");
		}
		else {
			if (delta>0){
				x1=(float) ((-b-Math.sqrt(delta))/(2*a));
				x2=(float) ((-b+Math.sqrt(delta))/(2*a));
			}
			System.out.println("la fonction a comme solution : "+ x1 +" et "+ x2);
			
		}
		clavier.close();	
	}

}
