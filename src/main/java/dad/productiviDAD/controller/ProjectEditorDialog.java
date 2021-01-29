package dad.productiviDAD.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.productiviDAD.model.Project;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ProjectEditorDialog extends Dialog<Project> implements Initializable{

    @FXML
    private BorderPane view;

    @FXML
    private GridPane topBar;

    @FXML
    private Label titleTopBar;
         
    public ProjectEditorDialog() {
    	super();
    	initStyle(StageStyle.UNDECORATED);
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/ProjectEditorView.fxml"));
		loader.setController(this); 
		try {
			loader.load();
		} catch (IOException e) {e.printStackTrace();} 
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		getDialogPane().setContent(view);
		getDialogPane().getStyleClass().add("customDialog");
	}
    
    @FXML 
    void onCloseWindow(ActionEvent event) {
        Platform.exit();
    }

}
