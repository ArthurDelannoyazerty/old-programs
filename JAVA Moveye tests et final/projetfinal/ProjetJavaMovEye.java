package projetfinal;

import java.awt.AWTException;

/*Notes
 truc a faire : 
	
	- Demander diagonale ecran en pouce avec text Field
	(-résolution cam) 
	- Donner distance ecran/visage en cm pour Nico => taille tête final=constante 
	
	-coef :
		x = tel angle
		pareil pour les y
		taille pixel image oeil

*/


import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDouble;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

//"extends Application" = importer javaFX
public class ProjetJavaMovEye extends Application {
	//class main
	public static void main(String[] args) {
		//importation des librairies opencv
		System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
		//lancement de la méthode javaFX = lancement de la class start
		launch(args);
	}
	
	
	//mesure des dimensions de l'écran
	Dimension Ecran = Toolkit.getDefaultToolkit().getScreenSize();
	
	
	//création d'images
	//l'objet Mat est une variable d'opencv qui stocke, en autre, des photos
	//image tout
    Mat mat_tt = new Mat();
    //image resize visage
    Mat mat_vis = new Mat();
    Mat mat_vis_resize = new Mat();
    Mat mat_vis_ok = new Mat();
    //image resize oeil1
    Mat mat_yd = new Mat();
    //image resize oeil2
    Mat mat_yg = new Mat();
    //image resize pupille 1
    Mat mat_p_d = new Mat();
    //image resize pupille 2
    Mat mat_pg = new Mat();
    
    //l'objet imageView est une variable de javaFX qui stocke des photos
    //création des imageview affichés dans la fenêtre finale de javaFX
    ImageView img_tt = new ImageView();
    ImageView img_vis = new ImageView();
    ImageView img_vis_resize = new ImageView();
    ImageView img_vis_ok = new ImageView();
    ImageView img_yd = new ImageView();
    ImageView img_yg = new ImageView();
    ImageView img_p_d = new ImageView();
    ImageView img_pg = new ImageView();
    
    //création des objets Text qui affichent un texte sur une fenêtre de JavaFX
    Text text_tt = new Text("img_tt");
    Text text_vis = new Text("img_vis");
    Text text_vis_resize = new Text("img_vis_resize");
    Text text_vis_ok = new Text("img_vis_ok");
    Text text_yd = new Text("img_yd");
    Text text_yg = new Text("img_yg");
    Text text_p_d = new Text("img_p_d");
    Text text_pg = new Text("img_pg");
    
    
    //création de l'objet Mat buffer(objet tampon)
    Mat mat_buf = new Mat();
    //variable compteur de boucle éffectuées
    int i = 0;
    int test;
    int diagonalePerm;
    //suppression des avertissement
	@SuppressWarnings("deprecation")
	@Override
	//class start = début du programme en chargeant JavaFX
	public void start(Stage primaryStage) throws Exception {
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		
		String xml = "xml/haarcascade_eye.xml";
	    CascadeClassifier classifierEye = new CascadeClassifier(xml);
	    
	    //instantiation de cascade classifier=dossier qui permet de faire une détection faciale
	    String xml1 = "xml/haarcascade_frontalface_alt.xml";
	    CascadeClassifier classifier = new CascadeClassifier(xml1);
		//création de l'objet TimeBuilder qui permet de faire des animations
        TimelineBuilder.create()
        		//boucle infinie
                .cycleCount(Animation.INDEFINITE)
                //création de l'objet keyFrames qui execute l'animation
                .keyFrames(
                	//execution de l'évenement EventHandler toutes les 1500 millisecondes(1.5sec)
                	new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>(){
                		//création du thread service(thread est une méthode qui s'éxecute indépendamment du reste du programme et ne va donc pas bloquer l'interface qraphique pendant son éxecution)
                    	FirstLineService service = new FirstLineService();
                        @Override
                        //class exécutée toutes les 1.5sec
                        public void handle(ActionEvent t) {
                        	//execution de la méthode show dans le thread service et qui renvoit l'itération i
                    		i=service.itération(i);
                    		//execution du thread:
                    		//execution de Task uniquement car itération() est une méthode a part
                    		//.restart signifie : commencer le thread, le terminer, le fermer. restart permet donc d'ouvrir et de refermer le thread afin de ne pas le laisser ouvert.
                    		service.restart();
                        }

						//class du thread service qui ne retourne rien car il stocke directement dans les variables imageView
						class FirstLineService extends Service<Void> {
							
							//variable d'itération "a" et non pas "i" = passe de variable dans un thread
							public int a;
							
							//class itération() (compte le nombre d'itération du thread)
					        public int itération(int i) {
					        	a = i+1;
					        	//return a dans le thread
								return a;
					        }
					        
					        //class Task qui ne retourne rien car il stocke directement dans les variable en dehors du thread
					        public Task<Void> createTask() {
					        	
					            return new Task<Void>() {

					                @Override
					                //class principale de Task
					                public Void call() throws Exception {
					                	//écriture de léitération sur la console
					                	System.out.println("i="+a);
					                	//l'objet Mat mat_tt prendle retour de la méthode prendrePhoto qui prend une photo
					                	//on envoit l'objet Mat tampon
					                	mat_tt = prendrePhoto(mat_buf);
					                	//on transforme d'abbord l'objet Mat mat_tt en BufferedImage avec la méthode MatABufferedImage()
					                	//puis on transforme la BufferedImage en objet Image image_tt
			                  	      	Image image_tt = SwingFXUtils.toFXImage(MatABufferedImage(mat_tt), null);
			                  	      	//on stocke l'objet Image image_tt dans l'objet imageView img_tt en dehors du Thread
			                  	      	img_tt.setImage(image_tt);
			                            
			                  	      	//appel de la class visage qui permet de détecter le visage sur une image dans la forme Mat
			                  	      	//on obtient un objet qui contient 2 objets Mat: l'image mat_tt et l'image mat_tt_resize
			                  	      	ResultatMatVis result = visage(mat_tt);
			                  	      	//on stocke l'image mat_tt provenant de la class visage
			                  	      	mat_vis=result.getSecond();
			                  	      	//on transforme l'image mat_vis en Image image_vis de la même manière que tout à l'heure
			                  	      	Image image_vis = SwingFXUtils.toFXImage(MatABufferedImage(mat_vis), null);
			                  	      	//on stocke l'Image image_vis dans L'imageView img_vis
			                  	      	img_vis.setImage(image_vis);
			                  	      	
			                  	      	//on stocke le second résultat de la class visage dans mat_vis_resize
			                  	      	//resize veut dire en français redimmensionné
			                  	      	mat_vis_resize=result.getFirst();
			                  	      	//on transforme l'image mat_vis_resize en Image image_vis_resize de la même manière que tout à l'heure
			                  	      	Image image_vis_resize = SwingFXUtils.toFXImage(MatABufferedImage(mat_vis_resize), null);
			                  	      	//on stocke l'Image image_vis_resize dans L'imageView img_vis_resize
			                  	      	img_vis_resize.setImage(image_vis_resize);
			                  	      	
			                  	      	//bloc traitement yeux
			                  	      	ResultatMatYeux resultatYeux = yeux(mat_vis_resize);
			                  	      	mat_vis_ok = resultatYeux.getFirst();
			                  	      	Image image_vis_ok = SwingFXUtils.toFXImage(MatABufferedImage(mat_vis_ok), null);
			                  	      	img_vis_ok.setImage(image_vis_ok);
			                  	      	
			                  	      	mat_yd = resultatYeux.getSecond();
			                  	      	Image image_yd = SwingFXUtils.toFXImage(MatABufferedImage(mat_yd), null);
			                  	      	img_yd.setImage(image_yd);
			                  	      	
			                  	      	mat_yg = resultatYeux.getThird();
			                  	      	Image image_yg = SwingFXUtils.toFXImage(MatABufferedImage(mat_yg), null);
			                  	      	img_yg.setImage(image_yg);
			                  	      	
			                  	      	//bloc traitement pupille
			                  	      	ResultatMatPupille resulatPupille = pupille(mat_yd, mat_yg);
			                  	      	mat_p_d = resulatPupille.getFirst();
			                  	      	Image image_p_d = SwingFXUtils.toFXImage(MatABufferedImage(mat_p_d), null);
			                  	      	img_p_d.setImage(image_p_d);
			                  	      	
			                  	      	mat_pg = resulatPupille.getSecond();
			                  	      	Image image_pg = SwingFXUtils.toFXImage(MatABufferedImage(mat_pg), null);
			                  	      	img_pg.setImage(image_pg);
			                  	      	
			                  	      	//coordonnées pupilles
			                  	      	
			                  	      	float x_yg = resulatPupille.getCinq();
			                  	      	float y_yg = resulatPupille.getSix();
			                  	      	
			                  	      	//méthode calculs
			                  	      	calculs(x_yg, y_yg);
										return null;
					                }
					                
					                private void calculs( float x_yg, float y_yg) throws AWTException {
					                	int m = 1;
					            		//calcul par rapport à oeil gauche 

					            		//import des variables
					            		double ax; //angle en x
					            		double ay; //angle en y
					            		//valeur coordoné pour la pupille
					            		int xp =(int) x_yg;  
					            		int yp = (int) y_yg;
					            		//format image pupille
					            		int height_p = mat_pg.height();
					            		int width_p = mat_pg.width();
					            		System.out.println(mat_pg.width());
					            		System.out.println(y_yg);
					            		
					            		
					            		//pour les x

					            		if(xp > width_p/2){
					            		//le regard est orienté vers la gauche
					            			ax= (45*(xp-width_p/2))/(44.23-width_p/2);			//ici
					            			ax= -ax;
					            		}else{
					            		//regard orienté vers la droite 
					            			ax=(45*(width_p/2 - xp))/(width_p/2-5.6);		//ici
					            			}

					            		
					            		// pour les y 

					            		if (yp > height_p/2 ){
					            		//regard vers le bas 
					            			ay= (20*(yp-height_p/2))/(8-height_p/2);		//ici
					            			ay=-ay;
					            		}else{
					            		//regarde vers le haut
					            			ay=(20*(height_p/2 - yp))/(height_p/2-6.68);		//ici
					            		}

					            		// fin traitement angle en rapport à la position pupille

					            		
					            		// alogo position souris 
					            		
					            		//info screen (pense à sortir cà de la boucle
					            		
					            				
					            		//création du robot (pense à sortir ca du thread)
					            		
					            		Robot robot = new Robot();
					            		
					            		
					            		
					            		//début traitement
					            			
					            			//pour x
					            			int x;
					            			x=0;
					            			double dx;
					            			dx=0;
					            			if(ax>=0) {
					            				ax = Math.toRadians(ax);
					            				dx = Math.tan(ax)*60;
					            				dx = dx*33.596+(width/2);
					            				x=(int)dx;
					            			}else {
					            				ax = Math.toRadians(ax*-1);
					            				dx = Math.tan(ax)*60;
					            				dx = (width/2)-dx*33.596;
					            				x = (int)dx;
					            			}
					            			
					            			//pour y
					            			int y;
					            			y=0;
					            			double dy;
					            			dy = 0;
					            			if(ay>=0) {
					            				ay = Math.toRadians(ay);
					            				dy = Math.tan(ay)*60;
					            				dy = (height/2)-dy*33.596;
					            				y = (int)dy;
					            			}else {
					            				ay = Math.toRadians(ay*-1);
					            				dy = Math.tan(ay)*60;
					            				dy = (height/2)+dy*33.596;
					            				y = (int)dy;				
					            			}
					            			
					            			//traitement pour éviter le out of bound
					            			if(x>width) {
					            				if(y>height) {
					            					x = width;
					            					y = height;	
					            				}else if(y<0) {
					            					x = width;
					            					y = 0;
					            				}else {
					            					x = width;
					            				}
					            			}else if(x<0){
					            				if(y>height) {
					            					x = 0;
					            					y = height;	
					            				}else if(y<0) {
					            					x = 0;
					            					y = 0;
					            				}else {
					            					x = 0;
					            				}
					            			}else {
					            				if(y>height) {
					            					y = height;	
					            				}else if(y<0) {
					            					y = 0;
					            				}else {
					            					//ne rien faire
					            				}
					            			}
					            			
					            		
					            			
					            			
					            			//robot.mouseMove(x,y);  // positionnement du curseur//////////////////////////////////////////////////////////////////////souris
									}

									private ResultatMatPupille pupille(Mat mat_yd, Mat mat_yg) {
					                	//on met les deux image Mat en niveaux de gris
					                	Imgproc.cvtColor(mat_yd, mat_yd, Imgproc.COLOR_BGR2GRAY);
					                	Imgproc.cvtColor(mat_yg, mat_yg, Imgproc.COLOR_BGR2GRAY);
					                	//on les floute pour supprimer les imperfections de l'image
					                	Imgproc.medianBlur(mat_yd,  mat_yd,  3);
					                	Imgproc.medianBlur(mat_yg,  mat_yg,  3);
					                	//on fait un seuillage des deux images Mat
					                	Imgproc.adaptiveThreshold(mat_yd, mat_yd, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C,Imgproc.THRESH_BINARY, 101, 20);
					                	Imgproc.adaptiveThreshold(mat_yg, mat_yg, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C,Imgproc.THRESH_BINARY, 101, 20);
					                	
					                	float decoupage = 10; 		//découpe des image en 10*10 -> équivault à la précision de la pupille
					                	
					                	
					                	//création des variables
					                	int x;
					                	int y;
					                	int width;
					                	int height;
					                	int numeroCarre = 0;
					                	//création d'une HashMap -> une sorte de tableau avec deux variables connectées
					                	HashMap<String, Integer> moyenneListe = new HashMap<String, Integer>();
					                	//création d'une LinkedList qui est utilisée comme un tableau mais plus pratique car elle est rapide à se remplir
					                	//Nous l'avons utilisée car les tableau conventionnels mettaient trop longtemps à se remplir
					                	LinkedList<String> numCarres = new LinkedList<String>();
					                	float totalx = 0;
					                	float totaly = 0;
					                	int intIntensite = 0;
					                	String stringIntensite = new String();
					                	
					                	//double boucle for de 10 itérations chacun pour découper une image Mat en 10 par 10
					                	for(float i = 0; i<decoupage ; i++) {
					                		for(float o = 0; o<decoupage; o++) {
					                			
					                			
					                			//variables pour la position et la grandeur du carré
					                			x=Math.round(mat_yd.width()/decoupage*i);
					                			y=Math.round(mat_yd.height()/decoupage*o);
					                			width=Math.round(mat_yd.width()/decoupage);
					                			height=Math.round(mat_yd.height()/decoupage);
					                			
					                			//création du carré
					                			Rect rect=new Rect(x,y,width,height);
					                			//Imgproc.rectangle(mat_yd, new Point(x, y), new Point(x+ rect.width, y+rect.height), new Scalar(0, 0, 255), 1);
					                			//5*5 ou 6*6 ou 7*7 px
					                			
					                			//création de plusieurs variables MatOfDouble 
					                			MatOfDouble moyenne = new MatOfDouble();
					                			MatOfDouble ecart_type = new MatOfDouble();
					                			//Création d'un objet Mat qui fera office de petite image découpée
					                			Mat smallMat = new Mat();
					                			
					                			//découpage de l'image Mat de l'oeil
						                		smallMat = mat_yd.submat(rect);
						                		//calcul de la moyenne de cette petite image Mat = découpage de cette grande image Mat
						                		Core.meanStdDev(smallMat, moyenne, ecart_type);
						                		//intensité lumineuse de la petite image
						                		int intensite = (int) moyenne.get(0, 0)[0];
						                		
						                		//si l'image n'est pas totalement blanche = si une partie de la pupille est dedans
						                		if(intensite != 255) {
						                			//on rempli le HashMap avec le numéro du carré en son instensité
						                			moyenneListe.put(Integer.toString((numeroCarre)), intensite);
						                			//on rempli la LinkedList permettant de retrouver les valeurs de la HashMap après
						                			numCarres.add(Integer.toString(numeroCarre));
						                		}
						                		//compteur de numéro de carré
						                		numeroCarre = numeroCarre+1;
					                		}
					                	}
					                	
					                	
					                	
					                	
					                	String nbCarreX = new String();
					                	String nbCarreY = new String();
					                	int totalMoyenne = 0;
					                	
					            		for(String numeroCarreXY: numCarres) {					// numéro du carré 0->99
					                		intIntensite = moyenneListe.get(numeroCarreXY); 	//int intensité lumineuse du carré selectionné
					                		stringIntensite = Integer.toString(intIntensite);	//string intensité lumineuse de carré selectionné
					                		
					                		//if else pour avoir le X et le Y de chaque carré en découpant le numéro du carré
					                		//exemple : carré 69 -> y=6 et X=9
					                		if(Integer.parseInt(numeroCarreXY)<10) {
					                			nbCarreY = "0";
					                			nbCarreX = numeroCarreXY;
					                		}
					                		else {
					                			nbCarreY = numeroCarreXY.substring(0, 1);
					                    		nbCarreX = numeroCarreXY.substring(1);
					                		}
					                		
					                		
					                		totalx =totalx + (Integer.parseInt(stringIntensite) * Integer.parseInt(nbCarreX));
					                		totaly =totaly + (Integer.parseInt(stringIntensite) * Integer.parseInt(nbCarreY));
					                		totalMoyenne = totalMoyenne + Integer.parseInt(stringIntensite);

					            		
					            		}
					            		//calcul des coordonnées en X et Y du centre de la pupille
					            		totalx = totalx/totalMoyenne;
					            		totaly = totaly/totalMoyenne;

					            		System.out.println("moyenne x  ="+totalx);
					            		System.out.println("moyenne Y  ="+totaly);
										float x_yd = totalx*Math.round(mat_yd.width()/decoupage);
										float y_yd = totaly*Math.round(mat_yd.width()/decoupage);
					                	
					                	
					            		
					            		
					            		
					            		
					            		//de même pour l'image Mat contenant l'oeil gauche
					            		int x2;
					                	int y2;
					                	int width2;
					                	int height2;
					                	int numeroCarre2 = 0;
					                	HashMap<String, Integer> moyenneListe2 = new HashMap<String, Integer>();
					                	LinkedList<String> numCarres2 = new LinkedList<String>();
					                	float totalx2 = 0;
					                	float totaly2 = 0;
					                	int intIntensite2 = 0;
					                	String stringIntensite2 = new String();
					                	
					                	
					                	for(float i = 0; i<decoupage ; i++) {
					                		for(float o = 0; o<decoupage; o++) {
					                			
					                			
					                			
					                			x2=Math.round(mat_yg.width()/decoupage*i);
					                			y2=Math.round(mat_yg.height()/decoupage*o);
					                			width2=Math.round(mat_yg.width()/decoupage);
					                			height2=Math.round(mat_yg.height()/decoupage);
					                			
					                			Rect rect2=new Rect(x2,y2,width2,height2);
					                			//Imgproc.rectangle(mat_yd, new Point(x, y), new Point(x+ rect.width, y+rect.height), new Scalar(0, 0, 255), 1);
					                			//5*5 ou 6*6 ou 7*7 px
					                			
					                			MatOfDouble moyenne2 = new MatOfDouble();
					                			MatOfDouble ecart_type2 = new MatOfDouble();
					                			Mat smallMat2 = new Mat();
					                			
						                		smallMat2 = mat_yg.submat(rect2);
						                		Core.meanStdDev(smallMat2, moyenne2, ecart_type2);
						                		int intensite2 = (int) moyenne2.get(0, 0)[0];
						                		
						                		if(intensite2 != 255) {
						                			moyenneListe2.put(Integer.toString((numeroCarre2)), intensite2);
						                			//System.out.println("valeur rectangle " + boucle + " = "+moyenneListe.get(Integer.toString(boucle)));
						                			numCarres2.add(Integer.toString(numeroCarre2));
						                		}
						                		numeroCarre2 = numeroCarre2+1;
					                		}
					                	}
					                	String nbCarreX2 = new String();
					                	String nbCarreY2 = new String();
					                	int totalMoyenne2 = 0;
					            		for(String numeroCarreXY2: numCarres2) {
					                		
					            			intIntensite2 = moyenneListe2.get(numeroCarreXY2);
					                		stringIntensite2 = Integer.toString(intIntensite2);
					                		
					                		if(Integer.parseInt(numeroCarreXY2)<10) {
					                			nbCarreY2 = "0";
					                			nbCarreX2 = numeroCarreXY2;
					                		}
					                		else {
					                			nbCarreY2 = numeroCarreXY2.substring(0, 1);
					                    		nbCarreX2 = numeroCarreXY2.substring(1);
					                		}
					                		    		
					                		totalx2 =totalx2 + (Integer.parseInt(stringIntensite2) * Integer.parseInt(nbCarreX2));
					                		totaly2 =totaly2 + (Integer.parseInt(stringIntensite2) * Integer.parseInt(nbCarreY2));
					                		totalMoyenne2 = totalMoyenne2 + Integer.parseInt(stringIntensite2);
					            		
					            		}
					            		totalx2 = totalx2/totalMoyenne2;
					            		totaly2 = totaly2/totalMoyenne2;
					            		
					            		
					            		

					            		System.out.println("moyenne x 2 ="+totalx2);
					            		System.out.println("moyenne Y 2 ="+totaly2);
					            		float x_yg = totalx*Math.round(mat_yg.width()/decoupage);
										float y_yg = totaly*Math.round(mat_yg.width()/decoupage);
					            		
					            		
					            		
					            		
					            		
										//on remet les image en couleur
					                	Imgproc.cvtColor(mat_yd, mat_yd, Imgproc.COLOR_GRAY2BGR);
					                	Imgproc.cvtColor(mat_yg, mat_yg, Imgproc.COLOR_GRAY2BGR);
					                	
					                	//on dessine des cercles ayant les coordonnées du centre des pupilles
					                	Imgproc.circle(
				                				mat_yd,
				                				new Point(totalx*Math.round(mat_yd.width()/decoupage) , totaly*Math.round(mat_yd.width()/decoupage) ),
				                				1,
				                				new Scalar(0, 0, 255),
				                				3
				                			);
					                	Imgproc.circle(
				                				mat_yg,
				                				new Point(totalx2*Math.round(mat_yg.width()/decoupage) , totaly2*Math.round(mat_yg.width()/decoupage) ),
				                				1,
				                				new Scalar(0, 0, 255),
				                				3
				                			);
					                	//exemple de découpage des image Mat en 10*10
					                	/*
					                	for(float i = 1; i<decoupage ; i++) {
					                		Imgproc.line (
					                				mat_yd,                  						 		//input image
					                				new Point((mat_yd.width()/decoupage)*i, 0),      				//point 1
					                				new Point((mat_yd.width()/decoupage)*i, mat_yd.height()),       	//point 2
					                				new Scalar(0, 0, 255),     								//Scalar object for color
					                				1                          								//épaisseur
					                				);
					                		Imgproc.line (
					                				mat_yg,                    							//input image
					                				new Point((mat_yg.width()/decoupage)*i, 0),        			//point 1
					                				new Point((mat_yg.width()/decoupage)*i, mat_yg.height()),		//point 2
					                				new Scalar(0, 0, 255),     							//Scalar object for color
					                				1                          							//épaisseur
					                				);
					                		Imgproc.line (
					                				mat_yd,                  						 		//input image
					                				new Point(0, (mat_yd.height()/decoupage)*i),      				//point 1
					                				new Point(mat_yd.width(), (mat_yd.height()/decoupage)*i),       	//point 2
					                				new Scalar(0, 0, 255),     								//Scalar object for color
					                				1                          								//épaisseur
					                				);
					                		Imgproc.line (
					                				mat_yg,                    							//input image
					                				new Point(0, (mat_yg.height()/decoupage)*i),        			//point 1
					                				new Point(mat_yg.width(), (mat_yg.height()/decoupage)*i),		//point 2
					                				new Scalar(0, 0, 255),     							//Scalar object for color
					                				1                          							//épaisseur
					                				);
					                	}*/
					                	return new ResultatMatPupille(mat_yd, mat_yg, x_yg, y_yg);
									}
					                
					                final class ResultatMatPupille {
					                	
					                    private Mat premier;
					                    private Mat deuxième;
					                    private float cinq;
					                    private float six;

					                    public ResultatMatPupille(Mat mat_yd, Mat img_yg, float x_yg, float y_yg) {
					                        this.premier = mat_yd;
					                        this.deuxième = img_yg;
					                        this.cinq = x_yg;
					                        this.six = y_yg;
					                    }

					                    public Mat getFirst() {
					                        return premier;
					                    }

					                    public Mat getSecond() {
					                        return deuxième;
					                    }

					                    public float getCinq() {
					                        return cinq;
					                    }

					                    public float getSix() {
					                        return six;
					                    }
					                }
					                
									private ResultatMatYeux yeux(Mat mat_vis_resize) {
					                	//instantiation de cascade classifier=dossier qui permet de faire une détection faciale
					           	     	
					           	     
					           	     	//detection visage
					           	     	MatOfRect detectionEye = new MatOfRect();
					           	     	classifierEye.detectMultiScale(mat_vis_resize, detectionEye);
					           	     	//message disant combient de visage on étés détectés
					           	     	System.out.println(String.format("%s yeux détecté", detectionEye.toArray().length));
					           	     	//création d'un nouveau rectangle
					           	     	Rect delimitation = new Rect();
					           	     	Rect delimitation2 = new Rect();
					           	     	
					           	     	int c=0;
					           	     	int deuxyeux=0;
					           	     	//dessinage carrés
					           	     	//pour chaque visage détecté:
					           	     	for(Rect rect : detectionEye.toArray()) {
					           	     		if((35<rect.height) && (70>rect.height) && (70>rect.width) && (35<rect.height)) {
					           	     			if(deuxyeux<2) {
					           	     				//dessine des rectangle sur l'image mat_tt
					           	     				Imgproc.rectangle(
					           	     						mat_vis_resize, 
					           	     						new Point(rect.x, rect.y),
					           	     						new Point(rect.x + rect.width, rect.y + rect.width),
					           	     						new Scalar(0, 255, 0)
					           	     						);
					           	     				//dimensionne le rectangle delimitation
					           	     				if(c==0) {
					           	     					delimitation = new Rect(rect.x+1, rect.y+1, rect.width-1, rect.height-1);
					           	     				}
					           	     				else {
					           	     					delimitation2 = new Rect(rect.x+1, rect.y+1, rect.width-1, rect.height-1);
					           	     				}
					           	     				c=c+1;
					           	     				deuxyeux=deuxyeux+1;
					           	     			}
					           	     		}
					           	     }
					           	     int delimitationx = delimitation.x;
					           	     int delimitation2x = delimitation2.x;
					           	     Mat mat_yd = null;
					           	     Mat mat_yg = null;
					           	     //créer les images mat_vis_resize
					           	     if(delimitationx < delimitation2x) {
					           	    	mat_yg = new Mat(mat_vis_resize,delimitation);
					           	    	mat_yd = new Mat(mat_vis_resize,delimitation2);
					           	     }
					           	     else {
					           	    	mat_yd = new Mat(mat_vis_resize,delimitation);
					           	    	mat_yg = new Mat(mat_vis_resize,delimitation2);
					           	     }
					           	     //on stocke l'image mat_tt modifiée dans mat_vis
					           	     mat_vis_ok = mat_vis_resize;
					           	     //on retourne les deux images grâce a la méthode MyResult
					           	     return new ResultatMatYeux(mat_vis_ok, mat_yd, mat_yg);
									}
					                
					                final class ResultatMatYeux {
					                	
					                    private final Mat premier;
					                    private final Mat deuxième;
					                    private final Mat troisieme;
					                    public ResultatMatYeux(Mat mat_vis_ok, Mat mat_yd, Mat mat_yg) {
					                    	this.premier = mat_vis_ok;
					                        this.deuxième = mat_yd;
					                        this.troisieme = mat_yg;
										}

					                    public Mat getFirst() {
					                        return premier;
					                    }

					                    public Mat getSecond() {
					                        return deuxième;
					                    }
					                    public Mat getThird() {
					                        return troisieme;
					                    }
					                    
					                }
					                
					                
									//méthode visage() qui permet de détecter un visage dans un photo Mat
					                //on importe mat_tt dans lequel on veut repérer un visage
					                public ResultatMatVis visage(Mat mat_tt) {
					                	
					           	     
					           	     	//detection visage
					           	     	MatOfRect detectionVisage = new MatOfRect();
					           	     	classifier.detectMultiScale(mat_tt, detectionVisage);
					           	     	//message disant combient de visage on étés détectés
					           	     	System.out.println(String.format("%s Visage détecté", detectionVisage.toArray().length));
					           	     	//création d'un nouveau rectangle
					           	     	Rect delimitation = new Rect();
					           	     	int un_visage = 0;
					           	     	//dessinage carrés
					           	     	//pour chaque visage détecté:
					           	     	for(Rect rect : detectionVisage.toArray()) {
					           	     		if(un_visage == 0) {
					           	     			//dessine des rectangle sur l'image mat_tt
					           	     			
					           	     			Imgproc.rectangle(
					           	     						mat_tt, 
					           	     						new Point(rect.x, rect.y),
					           	     						new Point(rect.x + rect.width, rect.y + rect.height),
					           	     						new Scalar(0, 255, 0)
					           	     						);
					           	     			//dimensionne le rectangle delimitation
					           	     			delimitation = new Rect(rect.x+1, rect.y+1, rect.width-1, rect.height-1);
					           	     			un_visage = un_visage++;
					           	     		}
					           	     	}
					           	     
					           	     //créerl'image mat_vis_resize
					           	     Mat mat_vis_resize=new Mat(mat_tt,delimitation);
					           	     //on stocke l'image mat_tt modifiée dans mat_vis
					           	     mat_vis = mat_tt;
					           	     //on retourne les deux images grâce a la méthode MyResult
					           	     return new ResultatMatVis(mat_vis_resize, mat_vis);
									}
					                
					                //méthode MyResult afin de redistribuer des variable Mat
					                final class ResultatMatVis {
					                	
					                    private final Mat premier;
					                    private final Mat deuxième;

					                    public ResultatMatVis(Mat mat_vis_resize, Mat img_vis) {
					                        this.premier = mat_vis_resize;
					                        this.deuxième = img_vis;
					                    }

					                    public Mat getFirst() {
					                        return premier;
					                    }

					                    public Mat getSecond() {
					                        return deuxième;
					                    }
					                }
					                //méthode MatABufferedImage qui permet de transformer un Mat en BufferedImage
									private BufferedImage MatABufferedImage(Mat mat_tt) {
					                	MatOfByte bytemat = new MatOfByte();
					                	//transformation de mat_tt en MatOfByte
			                    		Imgcodecs.imencode(".jpg", mat_tt, bytemat);
			                    		//transformation de MatOfByte en bytes
			                    		byte[] bytes = bytemat.toArray();
			                    		//insertion de Byte dans InputStream
			                    		InputStream in = new ByteArrayInputStream(bytes);
			                    		//initialisation de BufferedImage
			                    		BufferedImage img = null;
			                    		//bloc try/catch permettant d'essayer la transformation de l'image sans interrompre le programme
											try {
												//transformation de byte en BufferedImage par l'intermediaire de InputStream
												img = ImageIO.read(in);
											} catch (IOException e) {
												e.printStackTrace();
											}
											//retour de la BufferedImage
										return img;
									}
									//class prendrePhoto()
									private Mat prendrePhoto(Mat Mat) {
										//on prend une photo
										VideoCapture capture = new VideoCapture(0);
										//on la stocke dans l'objet Mat tampon
										capture.read(Mat);
										//on relache la caméra
										capture.release();
										//on retourne l'objet Mat tampon
										return Mat;
									}
					            };
					        }
					       
					    }
                    })
                )
                //lecture de keyframe une fois sa construction terminée
                .build().play(); 
		
        //position x de l'image img_vis_resize
        img_tt.setTranslateX(30);
        //position y de l'image img_vis_resize
        img_tt.setTranslateY(30);
        //largeur de l'image
        img_tt.setFitWidth((Ecran.getWidth())/4);
        //hauteur de l'image
        img_tt.setFitHeight((Ecran.getHeight())/4);
        //preservation du ratio de l'image=pas de déformation
        img_tt.setPreserveRatio(true);
        
        text_tt.setTranslateX(30);
        text_tt.setTranslateY(15);
        
        
        //de même
        img_vis.setTranslateX(30);
        img_vis.setTranslateY(img_tt.getFitHeight()+60);
        img_vis.setFitWidth((Ecran.getWidth())/4);
        img_vis.setFitHeight((Ecran.getHeight())/4);
        img_vis.setPreserveRatio(true);
        
        text_vis.setTranslateX(30);
        text_vis.setTranslateY(img_tt.getFitHeight()+45);
        
        //de même
        img_vis_resize.setTranslateX(img_tt.getFitWidth()+60);
        img_vis_resize.setTranslateY(30);
        img_vis_resize.setFitWidth((Ecran.getWidth())/4);
        img_vis_resize.setFitHeight((Ecran.getHeight())/4);
        img_vis_resize.setPreserveRatio(true);
        
        text_vis_resize.setTranslateX(img_tt.getFitWidth()+45);
        text_vis_resize.setTranslateY(15);
        
        //de même
        img_vis_ok.setTranslateX(img_tt.getFitWidth()+60);
        img_vis_ok.setTranslateY(img_tt.getFitHeight()+60);
        img_vis_ok.setFitWidth((Ecran.getWidth())/4);
        img_vis_ok.setFitHeight((Ecran.getHeight())/4);
        img_vis_ok.setPreserveRatio(true);
        
        text_vis_ok.setTranslateX(img_tt.getFitWidth()+45);
        text_vis_ok.setTranslateY(img_tt.getFitHeight()+45);
        
        //de même
        img_yd.setTranslateX((img_tt.getFitWidth()*2)+60);
        img_yd.setTranslateY(30);
        img_yd.setFitWidth((Ecran.getWidth())/4);
        img_yd.setFitHeight((Ecran.getHeight())/4);
        img_yd.setPreserveRatio(true);
        
        text_yd.setTranslateX((img_tt.getFitWidth()*2)+45);
        text_yd.setTranslateY(15);
        
        //de même
        img_yg.setTranslateX((img_tt.getFitWidth()*2)+60);
        img_yg.setTranslateY(img_tt.getFitHeight()+60);
        img_yg.setFitWidth((Ecran.getWidth())/4);
        img_yg.setFitHeight((Ecran.getHeight())/4);
        img_yg.setPreserveRatio(true);
        
        text_yg.setTranslateX((img_tt.getFitWidth()*2)+45);
        text_yg.setTranslateY(img_tt.getFitHeight()+45);
        
        //de même
        img_p_d.setTranslateX((img_tt.getFitWidth()*3)+60);
        img_p_d.setTranslateY(30);
        img_p_d.setFitWidth((Ecran.getWidth())/4);
        img_p_d.setFitHeight((Ecran.getHeight())/4);
        img_p_d.setPreserveRatio(true);
        
        text_p_d.setTranslateX((img_tt.getFitWidth()*3)+45);
        text_p_d.setTranslateY(15);
        
        //de même
        img_pg.setTranslateX((img_tt.getFitWidth()*3)+60);
        img_pg.setTranslateY(img_tt.getFitHeight()+60);
        img_pg.setFitWidth((Ecran.getWidth())/4);
        img_pg.setFitHeight((Ecran.getHeight())/4);
        img_pg.setPreserveRatio(true);
        
        text_pg.setTranslateX((img_tt.getFitWidth()*3)+45);
        text_pg.setTranslateY(img_tt.getFitHeight()+45);
        
        
        
        //création du groupe final root avec toutes les images imageView dedans
        final Group root= new Group(img_vis_resize,img_tt, img_vis, img_vis_ok, img_yd, img_yg, img_p_d, img_pg, text_tt, text_vis, text_vis_resize, text_vis_ok, text_yd, text_yg, text_p_d, text_pg);
        //création de la scène finale scene avec root dedans et avec ses dimensions X et Y
        final Scene scene = new Scene(root, Ecran.getWidth(), Ecran.getHeight()-50);        
        //pose du titre à la fenêtre
        primaryStage.setTitle("Titre");
        primaryStage.setMaximized(true);
        //insertion de la scene scene dans la fenêtre
        primaryStage.setScene(scene);
        //fenêtre visible
        primaryStage.show();
	}
}
