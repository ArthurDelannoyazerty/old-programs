package testthreadwin;
import java.util.concurrent.atomic.AtomicBoolean;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public  class test extends Application{

	public static void main(String[] args) throws Exception{
		launch(args);
		}

	public void start(Stage stage) throws Exception {
		int i = 1;
		
		show(stage,i);
	}

	private void show(Stage stage, int i) {
		Thread t1 = new Thread(new testclass(i));
		t1.start();
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
	
	public class testclass implements Runnable{
			
			private Thread worker;
			private final AtomicBoolean running = new AtomicBoolean(false);
			private int interval;
			
			public testclass(int sleepInterval) {
				interval = sleepInterval;
			}
			
			public void start() {
				worker = new Thread(this);
				worker.start();
			}
			
			public void stop() {
				running.set(false);
			}
			public void interrupt() {
		        running.set(false);
		        worker.interrupt();
		    }
		 
		    boolean isRunning() {
		        return running.get();
		    }
			
			public void run() {
				running.set(true);
				while(running.get()) {
					try {
						Thread.sleep(1000);
					}catch(InterruptedException e){
						Thread.currentThread().interrupt();
						System.out.println("erreur");
					}
					System.out.println("hello");
				}
			}
			
	}

	

}
