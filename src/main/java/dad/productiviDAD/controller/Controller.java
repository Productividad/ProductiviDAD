package dad.productiviDAD.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Controller implements Initializable {

	//View
	 
	@FXML
	private BorderPane view;

	@FXML
	private TitledPane historialTareas;

	@FXML
	private TitledPane buJoReader;

	@FXML
	private TitledPane gestorProyectos;

	@FXML
	private TitledPane ideas;

	@FXML
	private TitledPane gestorGastos;

	@FXML
	private TitledPane calendario;

	@FXML
	private VBox calendarVBox;

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
