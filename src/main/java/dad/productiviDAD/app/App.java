package dad.productiviDAD.app;

import dad.productiviDAD.controller.MainController;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application{

	private double x;
	private double y;
	private boolean lastValueYCoodIs0;
	
	private MainController controller;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		controller=new MainController();
		
		Scene scene=new Scene(controller.getView());
		
		controller.getView().setTop(controller.getTopBar());
		
		
		controller.getTopBar().setOnMouseClicked(e -> {
			// doble clic con bot�n primario -> maximimizar/restaurar ventana
			if (e.getClickCount() == 2 && e.getButton().equals(MouseButton.PRIMARY)) {
				primaryStage.setMaximized(!primaryStage.isMaximized());
				if(primaryStage.isMaximized())
					lastValueYCoodIs0=true;
			}
		});
		controller.getTopBar().setOnMousePressed(e -> { 
			// clic con botón primario -> guarda coordenadas del clic
			if (e.getButton().equals(MouseButton.PRIMARY)) {
			    x = primaryStage.getX() - e.getScreenX();
			    y = primaryStage.getY() - e.getScreenY();
			}
		});
		controller.getTopBar().setOnMouseDragged(e -> {
			// arrastra panel con botón primario -> se mueve ventana a nuevas coordenadas
			if (e.getButton().equals(MouseButton.PRIMARY)) {
				primaryStage.setX(e.getScreenX() + x);
				primaryStage.setY(e.getScreenY() + y);
			}
		});
		controller.getTopBar().setOnMouseReleased(e->{
			// Si al soltar el boton del raton se encuentra en la parte de arriba de la pantalla
			// maximiza la ventana
			lastValueYCoodIs0=primaryStage.isMaximized();
			if(e.getScreenY()==(double)0) {
				primaryStage.setMaximized(true);
				lastValueYCoodIs0=primaryStage.isMaximized();
				primaryStage.setX((double)0);
				primaryStage.setY((double)0);
			}
			//Si la ventana estaba maximizada al arrastrarla hacia abajo la minimiza
			else if(lastValueYCoodIs0) {
				primaryStage.setMaximized(!primaryStage.isMaximized());
				lastValueYCoodIs0=primaryStage.isMaximized();
				primaryStage.setX(e.getScreenX() + x);
				primaryStage.setY(e.getScreenY() + y);
			}
		});
		
		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.UNDECORATED); 
		primaryStage.setTitle("Bullet Journal, gestor de productiviDAD");
		primaryStage.getIcons().add(new Image("images/eclipse-icon-32px.png"));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
