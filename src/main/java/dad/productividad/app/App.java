package dad.productividad.app;

import javafx.application.Application;
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
	public static Stage primaryStage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		App.primaryStage = primaryStage;
		controller=new MainController();
		
		Scene scene=new Scene(controller.getView());
		
		controller.getView().setTop(controller.getTopBar());
		
		controller.getTopBar().setOnMouseClicked(e -> {
			//Double click with primary mouse button->Maximize/restore window
			if (e.getClickCount() == 2 && e.getButton().equals(MouseButton.PRIMARY)) {
				primaryStage.setMaximized(!primaryStage.isMaximized());
				if(primaryStage.isMaximized())
					lastValueYCoodIs0=true;
			}
		});
		controller.getTopBar().setOnMousePressed(e -> { 
			//Click with primary mouse button -> Saves x and y coordenates of mouse position
			if (e.getButton().equals(MouseButton.PRIMARY)) {
			    x = primaryStage.getX() - e.getScreenX();
			    y = primaryStage.getY() - e.getScreenY();
			}
		});
		controller.getTopBar().setOnMouseDragged(e -> {
			//Drag with primary mose button -> moves window to the new coordinates
			if (e.getButton().equals(MouseButton.PRIMARY)) {
				primaryStage.setX(e.getScreenX() + x);
				primaryStage.setY(e.getScreenY() + y);
			}
		});
		controller.getTopBar().setOnMouseReleased(e->{
			//If mouse is released when the window is on top then is maximized
			lastValueYCoodIs0=primaryStage.isMaximized();
			if(e.getScreenY()==(double)0) {
				primaryStage.setMaximized(true);
				lastValueYCoodIs0=primaryStage.isMaximized();
				primaryStage.centerOnScreen();
			}
			//If the window was maximized when dragged down then is restored
			else if(lastValueYCoodIs0) {
				primaryStage.setMaximized(!primaryStage.isMaximized());
				lastValueYCoodIs0=primaryStage.isMaximized();
				primaryStage.setX(e.getScreenX() + x);
				primaryStage.setY(e.getScreenY() + y);
			}
		});
		primaryStage.setTitle("ProductiviDAD");
		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.UNDECORATED); 
		primaryStage.show();
		primaryStage.getIcons().add(new Image("/images/icon-64x64.png"));
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
