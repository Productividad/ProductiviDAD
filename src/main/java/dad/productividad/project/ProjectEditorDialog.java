package dad.productividad.project;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import dad.productividad.app.App;
import dad.productividad.dataManager.TableProjects;
import dad.productividad.utils.ColorUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
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
    
    private Button acceptButton;
      
    private StringProperty titleDialog=new SimpleStringProperty();
    
    public ProjectEditorDialog() {
    	super();
    	initStyle(StageStyle.UNDECORATED);
    	initModality(Modality.WINDOW_MODAL);
    	initOwner(App.primaryStage);
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/ProjectEditorDialogView.fxml"));
		loader.setResources(ResourceBundle.getBundle("i18n/strings"));
		loader.setController(this); 
		try {
			loader.load();
		} catch (IOException e) {e.printStackTrace();} 
    }

	@Override 
	public void initialize(URL location, ResourceBundle resources) {
		datePicker = new JFXDatePicker();
		datePicker.setValue(LocalDate.now());
		titleTopBar.textProperty().bindBidirectional(titleDialog);
		
		getDialogPane().getStyleClass().add("customDialog");
		getDialogPane().setContent(view);

		getDialogPane().getButtonTypes().add(new ButtonType("Aceptar",ButtonData.OK_DONE));
		acceptButton = (Button) getDialogPane().lookupButton(getDialogPane().getButtonTypes().get(0));
		styleButton("white", "#000000");
		setResultConverter(d -> onAccept(d));
		 
		ButtonBar buttonBar=(ButtonBar)getDialogPane().lookup(".button-bar");
		buttonBar.setStyle("-fx-background-color:derive(white, 20.00%)");
		buttonBar.setPadding(new Insets(0,10,0,0));
		  
		colorPicker.setValue(new Color(0, 0, 0, 1)); 
		
		whiteText.selectedProperty().set(true);
		
		whiteText.selectedProperty().addListener((o,ov,nv)->{
			
			if(whiteText.selectedProperty().get()) {
				styleButton("white", ColorUtils.getHexString(colorPicker.getValue()));
				titleTopBar.setStyle("-fx-text-fill:white;");
			}
			else {
				styleButton("black", ColorUtils.getHexString(colorPicker.getValue()));
				titleTopBar.setStyle("-fx-text-fill:black;");
			}
		});
		
		colorPicker.valueProperty().addListener((o,ov,nv)->{
			  
			if(whiteText.selectedProperty().get()) 
				styleButton("white", ColorUtils.getHexString(colorPicker.getValue()));
			else 
				styleButton("black", ColorUtils.getHexString(colorPicker.getValue()));
			
			projectTopBar.setStyle("-fx-background-color:"+ColorUtils.getHexString(colorPicker.getValue()));
		});
		
	} 
	private Project onAccept(ButtonType buttonType) {
		if (buttonType.getButtonData() == ButtonData.OK_DONE) {
	
	    	Project project = new Project();
	    	project.setTitle(titleTF.textProperty().get());
	    	project.setDescription(descriptionTA.textProperty().get());
	    	project.setColor(ColorUtils.getHexString(colorPicker.getValue()));
	    	project.setWhite(whiteText.isSelected());
	    	project.setDeadLine(datePicker.getValue());
	    	System.out.println("HOLA" + datePicker.getValue());
	    	TableProjects.create(project);

	    	return project;
	    }
		return null;
	}
    @FXML  
    private void onCloseWindow(ActionEvent event) {  
    	Stage stage=(Stage)view.getScene().getWindow();
    	stage.close();
    }

    /**
     * Styles the ButtonData.OK_DONE button of the dialog
     * @param colorText String color of the text
     * @param backgroundColor String color of the background
     */
    private void styleButton(String colorText, String backgroundColor) {
		acceptButton
			.setStyle("-fx-background-radius:20; "
					+ "-fx-background-color:"+backgroundColor+"; "
					+ "-fx-text-fill:"+colorText+"; "
					+ "-fx-min-width:130;"
					+ "-fx-min-height:30;");
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