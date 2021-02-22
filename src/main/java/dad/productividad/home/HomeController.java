package dad.productividad.home;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.productividad.app.MainController;
import dad.productividad.dataManager.TableTasks;
import dad.productividad.task.Task;
import dad.productividad.task.TaskComponent;
import dad.productividad.task.TaskInserterController;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class HomeController implements Initializable {
 
	@FXML 
	private StackPane view;
	 
	@FXML
	private VBox topWrapper;
	
	@FXML 
	private ScrollPane scrollPane;
	
	@FXML 
	private VBox taskWrapper;  
	
	//Borrar
	
	@FXML
	private VBox bottomPane;
	
	@FXML 
	private GridPane dialogPane;
	
	@FXML
	private Button acceptButton, cancelButton;
	
	private ListProperty<Task> normalTask=new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<Task> favouriteList=new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<Task> doneList=new SimpleListProperty<>(FXCollections.observableArrayList());

	private ObjectProperty<Task> dialogTask=new SimpleObjectProperty<>();
	
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
		bottomPane.getChildren().add(inserter);
		bottomPane.setPadding(new Insets(10));

		//Borrar
		
		bottomPane.disableProperty().bind(dialogPane.visibleProperty());
		dialogPane.setVisible(false);
		
	}  
 
	@FXML
	private void onAcceptDialog(ActionEvent e) {
    	TableTasks.delete(dialogTask.get());
    	MainController.mainController.updateTaskWrapper();
		dialogPane.setVisible(false);
	}
	@FXML
	private void onCancelDialog(ActionEvent e) {
		hideDialog();
	}
	
	public void showDialog(Task task) {
		dialogPane.setVisible(true);
		dialogTask.set(task);
	}
	
	public void hideDialog() {
		dialogPane.setVisible(false);
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

	
	
	public StackPane getView() {
		return this.view;
	}
	
}