package dad.productiviDAD.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController implements Initializable {

	//View
	 
    @FXML
    private BorderPane view; 
    
    @FXML
    private GridPane topBar;
    
    @FXML
    private VBox center;
    
    @FXML 
    private JFXListView<String> listView;
 
	public MainController() throws IOException{
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/View.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	public BorderPane getView() {
		return this.view;
	}
	public GridPane getTopBar() {
		return this.topBar;
	}
	
	//TopBar
    @FXML
    void onCloseWindow(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void onMaximizeButton(ActionEvent event) {
    	Stage stage=(Stage)view.getScene().getWindow();
		stage.setMaximized(!stage.isMaximized());
    }

    @FXML
    void onMinimizeWindow(ActionEvent event) {
    	Stage stage=(Stage)view.getScene().getWindow();
    	stage.setIconified(true);
    }
	
    //SideBar
    @FXML 
    void onCalendarAction(ActionEvent event) {
    }

    @FXML
    void onEntryReaderAction(ActionEvent event) {

    }

    @FXML
    void onExpenseHistory(ActionEvent event) {

    }

    @FXML
    void onHomeAction(ActionEvent event) {

    }

    @FXML
    void onIdeasAction(ActionEvent event) {

    }

    @FXML
    void onProyectManagerAction(ActionEvent event) {

    }

    @FXML
    void onTaskHistoryAction(ActionEvent event) {

    }

    @FXML
    void onToolsAction(ActionEvent event) {

    }
}
