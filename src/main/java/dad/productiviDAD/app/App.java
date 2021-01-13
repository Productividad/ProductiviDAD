package dad.productiviDAD.app;

import dad.productiviDAD.controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application{

	private Controller controller;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		controller=new Controller();
		
		Scene scene=new Scene(controller.getView());
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Bullet Journal, gestor de productiviDAD");
		primaryStage.getIcons().add(new Image("images/eclipse-icon-32px.png"));
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
