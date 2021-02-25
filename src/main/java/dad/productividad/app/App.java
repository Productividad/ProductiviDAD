package dad.productividad.app;

import dad.productividad.utils.Preferences;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.Locale;

import com.goxr3plus.fxborderlessscene.borderless.BorderlessScene;
import com.goxr3plus.fxborderlessscene.borderless.CustomStage;

public class App extends Application{
	public static final String APP_NAME = "ProductiviDAD";
	public static Preferences preferences;
	private MainController controller;
	public static Stage primaryStage;
	
	static BorderlessScene borderLessScene;
	
	@Override
	public void init() throws Exception {
		preferences = Preferences.load();
		Locale.setDefault(preferences.getLocale());
		super.init();
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		controller=new MainController();

		CustomStage stage=new CustomStage(StageStyle.UNDECORATED);
		App.primaryStage = stage;
		borderLessScene=stage.craftBorderlessScene(controller.getView());
		
		controller.initActions();
		
		stage.setTitle("ProductiviDAD");
		stage.setScene(borderLessScene);
		stage.setMinWidth(750);
		stage.setMinHeight(650);
		stage.setWidth(950);
		stage.setHeight(850);
		stage.getIcons().add(new Image("/images/pdad_192px.png"));
		stage.showAndAdjust();
		
//		App.primaryStage = primaryStage;
//		controller=new MainController();
//		
//		primaryStage.setMinHeight(450);	
//		primaryStage.setMinWidth(550);
//		
////		primaryStage.setMinHeight(750);	
////		primaryStage.setMinWidth(850);
//		BorderlessScene scene=new BorderlessScene(primaryStage, StageStyle.UNDECORATED, controller.getView());
//
//		scene.setMoveControl(controller.getTopBar());
//		scene.removeDefaultCSS();
//		
//
//		
//		controller.getView().setTop(controller.getTopBar());
//		
//		primaryStage.setTitle("ProductiviDAD");
//		primaryStage.setScene(scene);
////		primaryStage.initStyle(StageStyle.UNDECORATED); 
//		primaryStage.show();
//		primaryStage.getIcons().add(new Image("/images/pdad_192px.png"));

	}
	
	@Override
	public void stop() throws Exception {
		super.stop();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
