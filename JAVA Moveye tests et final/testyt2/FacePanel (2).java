package testyt2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class FacePanel extends JPanel {//extends = hérite des attributs de la class mère
	private BufferedImage image;//appelle la classe BufferedImage=création buffer image
	int count = 0;
	public FacePanel() {
		super();//Creates a new JPanel with a double buffer
	}
	
	public void setFace(BufferedImage img) {
		image = img;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (this.image==null) {
			System.out.println(" Jpanel nul");
			return;
		}
		g.drawImage(this.image, 10, 10, this.image.getWidth(),this.image.getHeight(),null);
		//écris en arial
		g.setFont(new Font("arial",2/*type*/,20/*taille*/));
		//écris en blanc
		g.setColor(Color.WHITE);
		//ce qui est écris
		g.drawString("La caméra tourne [Frame:" + (count++) + " ]",50, 50 );
	}
}
