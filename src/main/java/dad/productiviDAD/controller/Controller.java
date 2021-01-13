package dad.productiviDAD.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class Controller implements Initializable {

	//View
	 @FXML
	 private BorderPane view;

	 @FXML
	 private HBox topBar;
	 
	 @FXML
	 private TitledPane calendario,historialTareas,buJoReader, gestorProyectos,
	    				ideas,gestorGastos,tareasDia;

	public Controller() throws IOException{
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
}
