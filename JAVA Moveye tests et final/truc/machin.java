package truc;

import java.io.File;
import java.io.IOException;

import javafx.animation.*;
import javafx.event.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class machin extends Application {
  public static void main(String[] args) { launch(args); }
  @Override public void start(Stage stage) throws IOException {
    
     	stage.setTitle("Capturing an image");
     
     	stage.setScene(new Scene(new DigitalClock(), 400, 600));
     
     	stage.show();
	
 	
	
    
  }



class DigitalClock extends Label{
  public DigitalClock() {
	
	bindToTime();
    
  }

  // update chaque seconde
  private void bindToTime() {
    Timeline timeline = new Timeline(
      new KeyFrame(Duration.seconds(0),
        new EventHandler<ActionEvent>() {
    	  int a = 0;
    	  ImageView imageView = new ImageView();
          @Override public void handle(ActionEvent actionEvent) {
        	
        	  File file = new File("C:\\0.jpeg");
        	   
        	  
        	  String localUrl = file.toURI().toString();
        	   
        	  Image image = new Image(localUrl);
        	  
        	  //Image img2 = new Image(Main.class.getResourceAsStream("img/2.jpg"));
        	  //Image img3 = new Image(Main.class.getResourceAsStream("img/3.jpg"));
        	  
        	  //if(a==0) {
        		  imageView.setImage(image);
        	//	  a=a+1;
        	  /*}
        	  if(a==1) {
        		  imageView.setImage(img2);
        		  a=a+1;
        	  }
        	  if(a==2) {
        		  imageView.setImage(img3);
        		  a=0;
        	  }*/
        		  imageView.setFitHeight(400);
      			imageView.setFitWidth(600);
        	  
          }
        }
      ),
      new KeyFrame(Duration.seconds(5))
    );
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
  }
}
}