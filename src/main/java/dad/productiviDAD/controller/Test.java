package dad.productiviDAD.controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Test extends Application {
    @Override
    public void start(Stage stage) {
        VBox vbox = new VBox();
        Scene scene = new Scene(vbox);
        stage.setScene(scene);

        Button button1 = new Button("New Tool Window");
        button1.setOnAction((e) -> {
            Stage toolStage = new Stage();
            Scene toolScene = new Scene(new Label("Am I on top?"), 300, 250);
            toolStage.setScene(toolScene);
            toolStage.initOwner(stage);
            toolStage.setAlwaysOnTop(true);
            toolStage.show();
        });

        Button button2 = new Button("Close");
        button2.setOnAction((e) -> System.exit(0));

        vbox.getChildren().addAll(button1, button2);
        stage.show();
        stage.setFullScreen(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}