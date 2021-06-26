package testthreadwin;

import java.io.IOException;
import javafx.animation.*;
import javafx.event.*;
import javafx.scene.control.Label;
import javafx.util.Duration;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class clock extends Application {
  public static void main(String[] args) { launch(args); }
  @Override public void start(Stage stage) throws IOException {
    stage.setScene(new Scene(new DigitalClock(), 1000, 50));
    stage.show();
    
    Stage stage2 = new Stage();
    stage2.setScene(new Scene(new DigitalClock(), 100, 500));
    stage2.show();
  }
}

class DigitalClock extends Label {
  public DigitalClock() {
    bindToTime();
  }

  // update chaque seconde
  private void bindToTime() {
	  
    Timeline timeline = new Timeline(
      new KeyFrame(Duration.seconds(0),
        new EventHandler<ActionEvent>() {
    	  int a = 0;
          @Override public void handle(ActionEvent actionEvent) {
            a=a+1;
            String string = Integer.toString(a);
            setText(string);
          }
        }
      ),
      new KeyFrame(Duration.seconds(0.0001))
    );
    timeline.setCycleCount(Animation.INDEFINITE);
    timeline.play();
  }
}

