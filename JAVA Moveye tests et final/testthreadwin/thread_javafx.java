package testthreadwin;

import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class thread_javafx extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		int i = 1;
		FirstLineService service = new FirstLineService();
		Stage st = stage;
		i=service.show(i);
		service.show2(st);
		//inutile
		//service.createTask();
		
		//service.start();
		
		//pas de reset après restart/start
		//service.reset();
		while(true) {
		service.restart();
		}
		//System.out.println("hello?");

	}


		public class FirstLineService extends Service<Void> {
		public int a;
		public Stage stage;
        
		
        public int show(int i) {
        	a = i+1;
			return a;
        }
        public Stage show2(Stage st) {
        	stage = st;
			return stage;
        }
        
        
        public Task<Void> createTask() {
        	
            return new Task<Void>() {

                @Override
                public Void call() throws Exception {
                	System.out.println("i="+a);
                	System.out.println("stage="+stage);
                    
                	Text text = new Text();
            		String string = Integer.toString(a);
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
                	
                	return null;
                }
            };
        }
       
    };
}