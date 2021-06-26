package cerlcetest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class cercle extends Application{
	ImageView img = new ImageView();
	public static void main(String[] args){
		System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
		//lancement de la méthode javaFX = lancement de la class start
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		String filename = "xml/fish.jpg";
        Mat src = Imgcodecs.imread(filename);
		Mat output = new Mat();
		if (src.empty()) {
            System.err.println("Cannot read image: " + filename);
            System.exit(0);
        }
        
        
        
        
        
        
        
        
        Image image = SwingFXUtils.toFXImage(MatABufferedImage(src), null);
	    img.setImage(image);
        Image image_vis = SwingFXUtils.toFXImage(MatABufferedImage(output), null);
	    img.setImage(image_vis);        
        Group root = new Group(img);
        Scene scene = new Scene(root,1000, 1000);        
        primaryStage.setTitle("Titre");
        primaryStage.setMaximized(true);
        //insertion de la scene scene dans la fenêtre
        primaryStage.setScene(scene);
        //fenêtre visible
        primaryStage.show();
	}

	private BufferedImage MatABufferedImage(Mat src) {
		MatOfByte bytemat = new MatOfByte();
    	//transformation de mat_tt en MatOfByte
		Imgcodecs.imencode(".PNG", src, bytemat);
		
		
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

}
