package truc;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class bird extends Application {
	int index=0;
	@Override
	public void start(Stage primaryStage) {
	    try {

	        ImageView bgV = new ImageView();
	        Image img_BG = new Image(bird.class.getResourceAsStream("img1.jpg"));
	        bgV.setImage(img_BG);
	        bgV.setEffect(new BoxBlur());
	        bgV.setOpacity(0.5);

	        ImageView t1V = new ImageView();
	        Image img_t1 = new Image(bird.class.getResourceAsStream(
	                "img2.jpg"
	                ));
	        t1V.setImage(img_t1);

	        ImageView t2V = new ImageView();
	        Image img_t2 = new Image(bird.class.getResourceAsStream(
	                "img3.jpg"
	                ));
	        t2V.setImage(img_t2);

	        ImageView t3V = new ImageView();
	        Image img_t3 = new Image(bird.class.getResourceAsStream(
	                "img2.jpg"
	                ));
	        t3V.setImage(img_t3);

	        Group foreground = new Group(t1V,t2V,t3V);

	        t1V.setTranslateX(20);
	        t1V.setTranslateY(200);

	        t2V.setTranslateX(300);
	        t2V.setTranslateY(200);

	        t3V.setTranslateX(550);
	        t3V.setTranslateY(200);
	        foreground.setEffect(new DropShadow());

	        
	        t3V.setFitHeight(400);
	        t3V.setFitWidth(600);
	        
	        t2V.setFitHeight(400);
	        t2V.setFitWidth(600);
	        
			t1V.setFitHeight(400);
			t1V.setFitWidth(600);
	        
			bgV.setFitHeight(600);
			bgV.setFitWidth(800);
			
			
			//imageView.setFitHeight(400);
			//imageView.setFitWidth(600);
	        
	        
	        
	        
	        
	        
	        
	        String[]
	                birdFiles = {"img1.jpg", "img2.jpg", "img3.jpg", "img1.jpg", "img2.jpg", "img3.jpg"};
	        double[] ds = { 300,            600,                900,            1200,   1500,   1800};

	        ImageView birdV = new ImageView(new Image(bird.class.getResourceAsStream(birdFiles[0])));
	        Group birds = new Group(birdV);
	        birds.setTranslateX(img_BG.getWidth()-100);

	        Timeline timeline  = new Timeline(); 
	        timeline.setCycleCount(
	                Animation.INDEFINITE
	                ); 


	        KeyFrame[] kframs = new KeyFrame[birdFiles.length];

	        for( index=0; index<birdFiles.length; index++)
	        {
	        	final int birdIndex = index;
	            EventHandler<ActionEvent> onFishined = new EventHandler<ActionEvent>() {

	                @Override
	                public void handle(ActionEvent arg0)
	                {
	                	birds.getChildren().setAll(new ImageView(new Image(bird.class.getResourceAsStream(birdFiles[birdIndex]))));

	                }

	            };
	            Duration duration = Duration.millis((ds[index])*2);
	            KeyFrame kf = new KeyFrame(duration, onFishined,null,null);
	             timeline.getKeyFrames().add(kf);

	        }//End for i
	        index = 0;







	        timeline.play();





	        Group root = new Group(bgV,foreground,birds);

	        Scene scene = new Scene(root,img_BG.getWidth(), img_BG.getHeight());
	        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}

	public static void main(String[] args) {
	    launch(args);
	}
}