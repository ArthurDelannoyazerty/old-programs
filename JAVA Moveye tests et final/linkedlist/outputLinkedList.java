package linkedlist;

import java.util.HashMap;
import java.util.LinkedList;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.Rect;

public class outputLinkedList {

	public static void main(String[] args) {
		
		float decoupage = 10; //découpe des image en 10*10 -> équivault à la précision de la pupille
    	
    	
    	
    	int x;
    	int y;
    	int width;
    	int height;
    	int boucle = 0;
    	HashMap<String, Integer> moyenneListe = new HashMap<String, Integer>();
    	LinkedList<String> numCarres = new LinkedList<String>();
    	int totalx = 0;
    	int totaly = 0;
    	int intListe = 0;
    	
    	
    	

    	String stringNumbMoyenneListe = new String();
    	
    	
    	
    	for(float i = 0; i<decoupage ; i++) {
    		for(float o = 0; o<decoupage; o++) {
    			
    			
        		int d = boucle;
        		
        		if(d != 255) {
        			moyenneListe.put(Integer.toString((boucle)), d);
        			//System.out.println("valeur rectangle " + boucle + " = "+moyenneListe.get(Integer.toString(boucle)));
        			numCarres.add(Integer.toString(boucle));
        		}
        		boucle = boucle+1;
    		}
    	}
    	// coordonnées de 0 à 9
    	// pour x et y
    	// x:0-> 1er carré à gauche
    	
    	String nbCarreX = new String();
    	int intNbCarre=0;
    	String nbCarreY = new String();
    	int nbCarre=0;
    	
		for(String a: numCarres) {
    		intListe = moyenneListe.get(a); 
    		stringNumbMoyenneListe = Integer.toString(intListe);
    		nbCarre = moyenneListe.get(stringNumbMoyenneListe);
    		
    		if(intListe<10) {
    			nbCarreY = "0";
    			nbCarreX = stringNumbMoyenneListe;
    		}
    		else {
    			nbCarreY = stringNumbMoyenneListe.substring(0, 1);
        		nbCarreX = stringNumbMoyenneListe.substring(1);
    		}
    		
    		
    		totalx =totalx + ( nbCarre * Integer.parseInt(nbCarreX) );
    		totaly =totaly + ( nbCarre * Integer.parseInt(nbCarreY) );
    		
    		
    		System.out.println("tt ="+stringNumbMoyenneListe);
    		System.out.println("y  ="+nbCarreY);
    		System.out.println("x  ="+nbCarreX);
    		System.out.println("");
		
		}
    	
		
			System.out.println("totalx = "+totalx);
			System.out.println("totaly = "+totaly);
			
			totalx = totalx/moyenneListe.size();
			totaly = totaly/moyenneListe.size();
			
			System.out.println("moyenneListe.size  ="+moyenneListe.size());
			System.out.println("moyenne x  ="+totalx);
			System.out.println("moyenne y  ="+totaly);
			
		
	}

}
