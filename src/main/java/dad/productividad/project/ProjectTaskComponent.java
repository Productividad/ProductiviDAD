package dad.productividad.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.productividad.segmentedBarUtils.StatusType;
import dad.productividad.task.Task;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ProjectTaskComponent extends VBox implements Initializable{

	private StringProperty title=new SimpleStringProperty();
	private StringProperty taskRemaining=new SimpleStringProperty();
	private ObjectProperty<Task>task=new SimpleObjectProperty<>();

	@FXML
    private Label titleLabel,taskRemainingLabel;
	
    public ProjectTaskComponent() {
        super();
    	try { 
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectTaskComponent.fxml"));
    		loader.setResources(ResourceBundle.getBundle("i18n/taskcard"));
    		loader.setController(this);
    		loader.setRoot(this); 
    		loader.load();
    	} catch (IOException e) {e.printStackTrace();}
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		titleLabel.textProperty().bind(title);
		taskRemainingLabel.textProperty().bind(taskRemaining);
		
		task.addListener((o,ov,nv)->{
			if(nv!=null) {				
				int subTaskNotDone=0; 
				for(Task subtask:task.get().getChildTasks()) {
					if(!subtask.getStatus().equals(StatusType.DONE))
						subTaskNotDone=+1;
				}
				title.set(nv.titleProperty().get());
				taskRemaining.set((String.valueOf(subTaskNotDone)));
			} 
		});
		
		setOnMouseClicked(evt->onMouseClicked());
		
	}

	public void onMouseClicked() {
		System.out.println("Cambiar comportamiento en TaskCardComponent.java -> onMouseClicked()");
	}
	
	public void styleTaskCard() {
		
		String pathStyleSheet="";
		
		if(task.get().getStatus().equals(StatusType.TODO))
			pathStyleSheet="/css/ToDoTask.css";
		if(task.get().getStatus().equals(StatusType.IN_PROGRESS))
			pathStyleSheet="/css/InProgressTask.css";
		if(task.get().getStatus().equals(StatusType.DONE))
			pathStyleSheet="/css/DoneTask.css";
		
		getStylesheets().setAll(pathStyleSheet);
	}
	
	public final ObjectProperty<Task> taskProperty() {
		return this.task;
	}
	

	public final Task getTask() {
		return this.taskProperty().get();
	}
	

	public final void setTask(final Task task) {
		this.taskProperty().set(task);
	}
	

}
