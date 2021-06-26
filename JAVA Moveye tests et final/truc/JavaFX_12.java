/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package truc;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.TimelineBuilder;
import javafx.animation.TranslateTransitionBuilder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;


public class JavaFX_12 extends Application {
    final static Image DOG_1 =  new Image(JavaFX_12.class.getResource("1.png").toString());
    final static Image DOG_2 =  new Image(JavaFX_12.class.getResource("2.png").toString());
    final static Image DOG_3 =  new Image(JavaFX_12.class.getResource("3.png").toString());
    final static Image DOG_4 =  new Image(JavaFX_12.class.getResource("4.png").toString());
    final static Image DOG_5 =  new Image(JavaFX_12.class.getResource("5.png").toString());
    final static Image DOG_6 =  new Image(JavaFX_12.class.getResource("6.png").toString());
    final static Image DOG_7 =  new Image(JavaFX_12.class.getResource("7.png").toString());
    final static Image DOG_8 =  new Image(JavaFX_12.class.getResource("8.png").toString());
    final static Image DOG_9 =  new Image(JavaFX_12.class.getResource("9.png").toString());

    private Animation walk;
    private Group dog;
    @SuppressWarnings("deprecation")
	@Override
    public void start(Stage primaryStage) {     
        final ImageView dog1 = new ImageView(DOG_1);
        final ImageView dog2 = new ImageView(DOG_2);
        final ImageView dog3 = new ImageView(DOG_3);
        final ImageView dog4 = new ImageView(DOG_4);
        final ImageView dog5 = new ImageView(DOG_5);
        final ImageView dog6 = new ImageView(DOG_6);
        final ImageView dog7 = new ImageView(DOG_7);
        final ImageView dog8 = new ImageView(DOG_8);
        final ImageView dog9 = new ImageView(DOG_9);
        
        
        
        
        dog = new Group(dog1);
        dog.setTranslateX(0);
        dog.setTranslateY(0);

        TimelineBuilder.create()
                .cycleCount(Animation.INDEFINITE)
                .keyFrames(
                    new KeyFrame(Duration.millis(50), new EventHandler<ActionEvent>(){
                        @Override
                        public void handle(ActionEvent t) {
                            dog.getChildren().setAll(dog1);
                        }
                    }),
                    new KeyFrame(Duration.millis(200), new EventHandler<ActionEvent>(){
                        @Override
                        public void handle(ActionEvent t) {
                             dog.getChildren().setAll(dog2);
                        }
                    }),
                    new KeyFrame(Duration.millis(300), new EventHandler<ActionEvent>(){
                        @Override
                        public void handle(ActionEvent t) {
                             dog.getChildren().setAll(dog3);
                        }
                    }),
                    new KeyFrame(Duration.millis(400), new EventHandler<ActionEvent>(){
                        @Override
                        public void handle(ActionEvent t) {
                             dog.getChildren().setAll(dog4);
                        }
                    }),
                    new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>(){
                        @Override
                        public void handle(ActionEvent t) {
                             dog.getChildren().setAll(dog5);
                        }
                    }),
                    new KeyFrame(Duration.millis(600), new EventHandler<ActionEvent>(){
                        @Override
                        public void handle(ActionEvent t) {
                             dog.getChildren().setAll(dog6);
                        }
                    }),
                    new KeyFrame(Duration.millis(700), new EventHandler<ActionEvent>(){
                        @Override
                        public void handle(ActionEvent t) {
                             dog.getChildren().setAll(dog7);
                        }
                    }),
                    new KeyFrame(Duration.millis(800), new EventHandler<ActionEvent>(){
                        @Override
                        public void handle(ActionEvent t) {
                             dog.getChildren().setAll(dog8);
                        }
                    }),
                    new KeyFrame(Duration.millis(850), new EventHandler<ActionEvent>(){
                        @Override
                        public void handle(ActionEvent t) {
                             dog.getChildren().setAll(dog9);
                        }
                    })
                )
                .build().play();       
        Scene scene = new Scene(dog, 800, 150);        
        primaryStage.setTitle("Titre");
        primaryStage.setScene(scene);
        primaryStage.show();
        startWalking();
    }

    @SuppressWarnings("deprecation")
	private void startWalking() 
    {
        walk = TranslateTransitionBuilder.create()
                .node(dog)
                .fromX(-200)
                .toX(800)
                .duration(Duration.seconds(5))
                .onFinished(new EventHandler<ActionEvent>()
                        {
                            @Override
                            public void handle(ActionEvent t) {
                                startWalking();   
                            }
                        }).build();
        walk.play();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
