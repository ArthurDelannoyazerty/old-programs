package projetfinal;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

public class projet extends Application{

	public static void main(String[] args) throws Exception {
		 launch(args);
	}
	

	public void start(Stage stage) {
		
		long chrono, chrono0, chronod;
		long chronodiv, chronodivd;
		chronodivd=0;
		chrono0 = java.lang.System.currentTimeMillis();
		while(true) {
			
			chrono = java.lang.System.currentTimeMillis();
			chronod = chrono - chrono0;
			chronodiv = chronod/1000;
			//System.out.println("chronodiv = "+chronodiv);
			//System.out.println("chronodivd = "+chronodivd);
			//System.out.println("chronodiv-chronodivd+2 = "+ (chronodiv-chronodivd+2));
			if(chronodiv > chronodivd-20) {
				System.out.println(chronodiv);
				chronodivd=chronodiv;
				//là où on lance la méthode
				main(stage);
				
			}			
		}
		  
	}
	
	
	public void main(Stage stage) {
		// chargement librairies opencv
	      System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
	      //création variables Mat contenant les images
	      //image tout
	      Mat img_tt = new Mat();
	      //image resize visage
	      Mat img_vis = new Mat();
	      //image resize oeil1
	      Mat img_y1 = new Mat();
	      //image resize oeil2
	      Mat img_y2 = new Mat();
	      //image resize pupille 1
	      Mat img_p1 = new Mat();
	      //image resize pupille 2
	      Mat img_p2 = new Mat();
	      
	      //méthode prendre photo originale
	      img_tt = prendrePhoto();
	      
	      //je sais pas
	      projet obj = new projet();
	      
	      //création writableImage img_tt
	      WritableImage img_tt_wri = obj.MatAWritableimage(img_tt);
	      //méthode showImage qui affiche la writableImage
	      showImage(img_tt_wri, stage);
	      
		  //création img_vis = image visage seule(resize)
		  img_vis = visage(img_tt);
		  //création writableImage img_vis
	      WritableImage img_vis_wri = obj.MatAWritableimage(img_vis);
	      //méthode showImage qui affiche la writableImage
	      showImage(img_vis_wri, stage);
	}
	
	//méthode pour prendre photo
	public static Mat prendrePhoto() {
		Mat Mat = new Mat();
		
		VideoCapture capture = new VideoCapture(0);
		capture.read(Mat);
		capture.release();
		return Mat;
	}
	
	//transformer 
	public WritableImage MatAWritableimage(Mat matrix) {
		WritableImage WritableImage = null;
		
	            // création de BufferedImage a l'aide d'une matrice
	            BufferedImage image = new BufferedImage(matrix.width(), matrix.height(), BufferedImage.TYPE_3BYTE_BGR);
	            
	            WritableRaster raster = image.getRaster();
	            DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
	            byte[] data = dataBuffer.getData();
	            matrix.get(0, 0, data);
	            //this.matrix = matrix;
	            
	            //création de WritableImage
	            WritableImage = SwingFXUtils.toFXImage(image, null);
	         
		return WritableImage;
	}
	
	public void showImage(WritableImage writableImage, Stage stage) {
			
		
		
			//permet de gerer les paramètres de l'image writableImage
			ImageView imageView = new ImageView(writableImage);
		 
			
			//	imageView.setImage(null);
			
			
			//hauteur = 400px et largeur = 600px
			imageView.setFitHeight(400);
			imageView.setFitWidth(600);
	     
			// pas de déformation de l'image
			imageView.setPreserveRatio(true);
	     
			//class abstraite contenant tous les éléments graphiques permettant la mise en place de Scene
			//le groupe root contient l'image writableImage
			Group root = new Group(imageView);
	     
			// création de Scene(conteneur d'éléments sur la fenêtre
			Scene scene = new Scene(root, 400, 600);
	     
	     	//stage = frame dans JPanel mais dans javaFX
	     	//titre de la fenetre
	     	stage.setTitle("Capturing an image");
	     
	     	//stage est le conteneur d'objets dans javaFX
	     	//ajout de Scene dans Stage (et donc de writable image dans Stage)
	     	stage.setScene(scene);
	     
	     	// visibilité = OK
	     	stage.show();
			
	}
	
	
	public static Mat visage(Mat img_tt) {
		//image resize visage
	     Mat img_vis = new Mat();
	     
	     //instantiation de cascade classifier
	     String xml = "xml/haarcascade_frontalface_alt.xml";
	     CascadeClassifier classifier = new CascadeClassifier(xml);
	     
	     //detection visage
	     MatOfRect detectionVisage = new MatOfRect();
	     classifier.detectMultiScale(img_tt, detectionVisage);
	     System.out.println(String.format("Detected %s faces", detectionVisage.toArray().length));
	     
	     
	     //dessinage carrés
	     for(Rect rect : detectionVisage.toArray()) {
	    	 Imgproc.rectangle(
	    			 img_tt,       													//où les boites sont dessinées
	    			 new Point(rect.x, rect.y),										//bas gauche
	    			 new Point(rect.x + rect.width, rect.y + rect.height),			//haut droite
	    			 new Scalar(0,0,255),											//lignes
	    			 3																//RGB couleur lignes et points
	    			 );	    	 
	     }
	     //écriture de l'image visage
	    img_vis = img_tt;
	    //retour image visage
		return img_vis;
	}
}



















































/*//lire l'image de la vidéo
Mat matrix = new Mat(); 
capture.read(matrix);

//si la caméra est allumée
if(capture.isOpened()) {
	//si il y a une image lue
	if(capture.read(matrix)) {
		//création de Bufferedimage(variable conteneur d'image) a partir de matrix
		BufferedImage image = new BufferedImage(matrix.width(),matrix.height(), BufferedImage.TYPE_3BYTE_BGR);
		
		WritableRaster raster = image.getRaster();
		DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
        byte[] data = dataBuffer.getData();
        matrix.get(0, 0, data);
        this.matrix = matrix;
        
        //création WritableImage
        WritableImage = SwingFXUtils.toFXImage(image, null);*/