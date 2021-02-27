package dad.productividad.app;

import dad.productividad.utils.Preferences;
import dad.productividad.utils.ResourceUtils;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.util.Locale;

import com.goxr3plus.fxborderlessscene.borderless.BorderlessScene;
import com.goxr3plus.fxborderlessscene.borderless.CustomStage;

public class App extends Application {
    public static final String APP_NAME = "ProductiviDAD";
    public static Preferences preferences;
    private MainController controller;
    public static Stage primaryStage;

    static BorderlessScene borderLessScene;

    @Override
    public void init() throws Exception {
/**
 * TODO use this when DB structure is definitive.
 File file = new File(System.getProperty("user.home"), "." + App.APP_NAME + "\\productiviDAD.db");
 System.out.println(file.getPath());
 if(!file.exists())
 ResourceUtils.copyResourceToFile("/database/productiviDAD.db", file);
 */
        preferences = Preferences.load();
        Locale.setDefault(preferences.getLocale());
        super.init();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        controller = new MainController();

        CustomStage stage = new CustomStage(StageStyle.UNDECORATED);
        App.primaryStage = stage;
        borderLessScene = stage.craftBorderlessScene(controller.getView());

        controller.initActions();

        stage.setTitle("ProductiviDAD");
        stage.setScene(borderLessScene);
        stage.setMinWidth(750);
        stage.setMinHeight(650);
        stage.setWidth(950);
        stage.setHeight(850);
        stage.getIcons().add(new Image("/images/pdad_192px.png"));
        stage.showAndAdjust();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}
