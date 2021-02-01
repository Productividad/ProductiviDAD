package dad.productiviDAD.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import dad.productiviDAD.utils.ColorUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private GridPane projectTopBar;

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
    
    @FXML 
    private Button acceptButton;
      
    private StringProperty titleDialog=new SimpleStringProperty();
    
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
		
		titleTopBar.textProperty().bindBidirectional(titleDialog);
		
		getDialogPane().getStyleClass().add("customDialog");
		getDialogPane().setContent(view);
		
		ButtonBar buttonBar=(ButtonBar)getDialogPane().lookup(".button-bar");
		buttonBar.setStyle("-fx-background-color:derive(white, 20.00%)");
		
		colorPicker.setValue(new Color(0, 0, 0, 1));
		
		whiteText.selectedProperty().set(true);
		whiteText.selectedProperty().addListener((o,ov,nv)->{
			if(whiteText.selectedProperty().get()) {
				acceptButton.setStyle("-fx-background-color:"+ColorUtils.getHexString(colorPicker.getValue())+
						"; -fx-text-fill:white;");
				titleTopBar.setStyle("-fx-text-fill:white;");
			}
			else {
				acceptButton.setStyle("-fx-background-color:"+ColorUtils.getHexString(colorPicker.getValue())+
						"; -fx-text-fill:black;");
				titleTopBar.setStyle("-fx-text-fill:black;");
			}
		});
		
		colorPicker.valueProperty().addListener((o,ov,nv)->{
			acceptButton.setStyle("-fx-background-color:"+ColorUtils.getHexString(colorPicker.getValue()));
			projectTopBar.setStyle("-fx-background-color:"+ColorUtils.getHexString(colorPicker.getValue()));
		});
		
	} 

    @FXML 
    private void onAcceptAction(ActionEvent event) {
    	//TODO crear proyecto en la base de datos e insertar projectCard en la interfaz
    	System.out.println("Implementar");
    } 
	
    @FXML  
    private void onCloseWindow(ActionEvent event) {  
    	Stage stage=(Stage)view.getScene().getWindow();
    	stage.close();
    }

	public final StringProperty titleDialogProperty() {
		return this.titleDialog;
	}
	

	public final String getTitleDialog() {
		return this.titleDialogProperty().get();
	}
	

	public final void setTitleDialog(final String titleDialog) {
		this.titleDialogProperty().set(titleDialog);
	}
}