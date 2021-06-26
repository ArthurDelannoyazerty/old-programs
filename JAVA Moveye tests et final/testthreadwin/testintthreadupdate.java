package testthreadwin;
import java.util.concurrent.atomic.AtomicBoolean;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public  class testintthreadupdate extends Application{

	public static void main(String[] args) throws Exception{
		launch(args);
		}

	public void start(Stage stage) throws Exception {
		int i = 1;
		
		show(stage,i);
		while(true) {
		System.out.println("hello!");
		}}

	private void show(Stage stage, int i) {
		//Thread t1 = new Thread(new testclass(i, i, stage));
		//t1.start();
		/*Text text = new Text();
		String string = Integer.toString(i);
		text.setText(string);
		text.setX(50);
		text.setY(50);
		Group root = new Group();
	    
		// création de Scene(conteneur d'éléments sur la fenêtre
		Scene scene = new Scene(root, 400, 600);
     
     	//stage = frame dans JPanel mais dans javaFX
     	//titre de la fenetre
     	stage.setTitle("Capturing an image");
     	root.getChildren().add(text);
     	//stage est le conteneur d'objets dans javaFX
     	//ajout de Scene dans Stage (et donc de writable image dans Stage)
     	stage.setScene(scene);
     	
     
     	// visibilité = OK
     	stage.show();
		*/
	}
	
	

	

}
