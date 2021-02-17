package dad.productividad.home;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.productividad.dataManager.TableTasks;
import dad.productividad.task.Task;
import dad.productividad.task.TaskComponent;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class HomeController implements Initializable {
 
	@FXML 
	private VBox view;
	 
	@FXML 
	private ScrollPane scrollPane;
	
	@FXML 
	private VBox taskWrapper;  
	 
	private ListProperty<Task> taskList=new SimpleListProperty<>(FXCollections.observableArrayList());

	
	public HomeController() {  
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HomeView.fxml"));
			loader.setController(this);
			loader.load(); 
		} catch (IOException e) {   
			e.printStackTrace();   
		}  
	}   
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		scrollPane.setFitToWidth(true);	
		

		
		insertTaskFromDB();
				
	} 
 
	public void insertTaskFromDB() {
		
		
		taskList.clear();
		taskWrapper.getChildren().clear();
		
		taskList.addAll(TableTasks.readParentTasks(null));
		
		for(Task task:taskList) {
			  
			TaskComponent taskComponent=new TaskComponent();
			taskComponent.setTask(task);
			taskWrapper.getChildren().add(taskComponent);
		}
		
		
	}

	
	public VBox getView() {
		return this.view;
	}
	
}
