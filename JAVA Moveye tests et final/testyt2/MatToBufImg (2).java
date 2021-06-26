package testyt2;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;

public class MatToBufImg {
	Mat matrix;
	MatOfByte mob;
	String fileExten;
	
	public MatToBufImg() {
	}
	
	public MatToBufImg(Mat amatrix, String fileExt) {
		matrix = amatrix;
		fileExten = fileExt;
	}
	
	public void setMAtrix(Mat amatrix, String fileExt) {
		matrix = amatrix;
		fileExten = fileExt;
		mob = new MatOfByte();
	}

	public BufferedImage getBuffreredImage() {
		Imgcodecs.imencode(fileExten, matrix, mob);
		byte[] byteArray = mob.toArray();
		BufferedImage bufImage = null;
		try {
			InputStream in = new ByteArrayInputStream(byteArray);
			bufImage = ImageIO.read(in);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return bufImage;
	}

}
