package testthreadwin;

import java.io.IOException;
import javafx.animation.*;
import javafx.event.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import sun.applet.Main;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class clock_win_cam extends Application {
  public static void main(String[] args) { launch(args); }
  @Override public void start(Stage stage) throws IOException {
    
 	
		
		Group root = new Group(new DigitalClock());
     
		Scene scene = new Scene(root, 400, 600);
     
     	stage.setTitle("Capturing an image");
     
     	stage.setScene(scene);
     
     	stage.show();
	
 	
	
    
  }




  public void DigitalClock() {
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
        	  Image img = new Image(Main.class.getResourceAsStream("img/1.jpeg"));
        	  //Image img2 = new Image(Main.class.getResourceAsStream("img/2.jpg"));
        	  //Image img3 = new Image(Main.class.getResourceAsStream("img/3.jpg"));
        	  
        	  //if(a==0) {
        		  imageView.setImage(img);
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
        	  
        	  
          }
        }
      ),
      new KeyFrame(Duration.seconds(5))
    );
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
  }
}

