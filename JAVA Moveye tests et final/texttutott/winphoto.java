package texttutott;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

public class winphoto {


	public static void main(String[] args) {
		//Loading the OpenCV core library  
	      System.loadLibrary(Core.NATIVE_LIBRARY_NAME); 
	      
	      //Instantiating the imagecodecs class 
	      Imgcodecs imagecodecs = new Imgcodecs(); 

	      //Reading the Image from the file and storing it in to a Matrix object 
	      String file ="Y:/ISN/T°S4/java/projet_ISN/texttutott/img/img1.jpg";   
	      Mat matrix = imagecodecs.imread(file); 

	      System.out.println("Image Loaded ..........");
	      String file2 = "Y:/ISN/T°S4/java/projet_ISN/texttutott/img/img2.jpg"; 

	      //Writing the image 
	      imagecodecs.imwrite(file2, matrix); 
	      System.out.println("Image Saved ............");      
	      
	    //Reading the image 
	      Mat image = imagecodecs.imread(file);

	      //instantiating an empty MatOfByte class 
	      MatOfByte matOfByte = new MatOfByte();

	      //Converting the Mat object to MatOfByte 
	      Imgcodecs.imencode(".jpg", image, matOfByte);
	}

}//Y:/ISN/T°S4/java/projet_ISN/texttutott/img/img.jpg
