package dad.productiviDAD.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import dad.productiviDAD.model.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ProjectEditorDialog extends Dialog<Project> implements Initializable{

    @FXML
    private BorderPane view;

    @FXML
    private GridPane topBar;

    @FXML 
    private Label titleTopBar;
    
    @FXML
    private JFXTextField titleTF;

    @FXML
    private JFXTextArea descriptionTA;

    @FXML
    private JFXDatePicker datePicker;

    @FXML
    private JFXCheckBox whiteText;

    @FXML
    private JFXColorPicker colorPicker;
         
    public ProjectEditorDialog() {
    	super();
    	initStyle(StageStyle.UNDECORATED);
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/ProjectEditorDialogView.fxml"));
		loader.setController(this); 
		try {
			loader.load();
		} catch (IOException e) {e.printStackTrace();} 
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//TODO mmm muestrate un color ya sea en hex o en rgb titán
		colorPicker.valueProperty().addListener((o,ov,nv)->{
			
//			System.out.println(colorPicker.getCH);
			
		});
		
		
		getDialogPane().getStyleClass().add("customDialog");
		getDialogPane().setContent(view);
		ButtonBar buttonBar=(ButtonBar)getDialogPane().lookup(".button-bar");
		buttonBar.setStyle("-fx-background-color:red");
		
	}
    
    @FXML
    private void onAcceptAction(ActionEvent event) {

    }
	
	
    @FXML 
    private void onCloseWindow(ActionEvent event) { 
    	Stage stage=(Stage)view.getScene().getWindow();
    	stage.close();
    }
}