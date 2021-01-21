package dad.productiviDAD.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;

import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
  
public class MainController implements Initializable  { 
    
	//View   
	 
    @FXML  
    private BorderPane view;   
    
    @FXML
    private GridPane topBar;
     
    @FXML 
    private VBox center; 
    
    @FXML 
    private ListView<String> listView;
    
	public MainController() throws IOException{
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/View.fxml"));
		loader.setController(this);
		loader.load(); 
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		ListProperty<String>lista=new SimpleListProperty<>(FXCollections.observableArrayList());
		int contador=0;
		
		for(contador=0;contador<=20;contador++) {
			String string=""+contador;
			lista.add(string);
		}
		String tarea1="Sacar al perro";
		String tarea2="Hacer quereseres";
		String tarea3="Hacer cosa de bases de datos";
		String tarea4="Hacer la comida";
		String tarea5="Vida antes que muerte";
		
		lista.addAll(tarea1,tarea2,tarea3,tarea4,tarea5);
		
		listView.itemsProperty().bindBidirectional(lista);
		 
		
		
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
