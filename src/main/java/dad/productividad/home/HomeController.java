package dad.productividad.home;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.productividad.dataManager.TableTasks;
import dad.productividad.task.Task;
import dad.productividad.task.TaskComponent;
import dad.productividad.task.TaskInserterController;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class HomeController implements Initializable {
 
	@FXML 
	private VBox view;
	 
	@FXML
	private VBox topWrapper;
	
	@FXML 
	private ScrollPane scrollPane;
	
	@FXML 
	private VBox taskWrapper;  
	 
	private ListProperty<Task> normalTask=new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<Task> favouriteList=new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<Task> doneList=new SimpleListProperty<>(FXCollections.observableArrayList());

	
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
		
		TaskInserterController inserter=new TaskInserterController();
		view.getChildren().add(inserter);
		view.setPadding(new Insets(10));

 
	}  
 
	public void insertTaskFromDB() { 
		
		favouriteList.clear();
		doneList.clear();
		normalTask.clear();
		taskWrapper.getChildren().clear(); 
				
		for(Task task:TableTasks.readParentTasks(null)) { 
			if(!task.isDone() && task.isFavourite()) 
				favouriteList.add(task);
			if(task.isDone()) 
				doneList.add(task);
			if(!task.isDone() && !task.isFavourite()) 
				normalTask.add(task); 
		} 
  
		for(Task task:favouriteList) {
			TaskComponent taskComponent=new TaskComponent();
			taskComponent.setTask(task);
			taskWrapper.getChildren().add(taskComponent);
		}
		for(Task task:normalTask) { 
			TaskComponent taskComponent=new TaskComponent();
			taskComponent.setTask(task);
			taskWrapper.getChildren().add(taskComponent);
		}
		for(Task task:doneList) {
			TaskComponent taskComponent=new TaskComponent();
			taskComponent.setTask(task);
			taskComponent.getStyleClass().add("completed-task");
			
			taskWrapper.getChildren().add(taskComponent);
		}
	}

	
	public VBox getView() {
		return this.view;
	}
	
}