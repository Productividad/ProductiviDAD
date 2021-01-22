package dad.productiviDAD.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class TaskManagerController implements Initializable{

	@FXML
	private VBox view;
		
	@FXML
    private ListView<String> listView;

	
	public TaskManagerController() {
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/TaskManagerView.fxml"));
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		} 
	} 
	 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Pruebas
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
	public VBox getView() {
		return this.view;
	}

}
