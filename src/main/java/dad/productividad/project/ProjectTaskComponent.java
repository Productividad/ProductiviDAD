package dad.productividad.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.productividad.app.MainController;
import dad.productividad.dataManager.TableTasks;
import dad.productividad.task.Task;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ProjectTaskComponent extends GridPane implements Initializable{

    @FXML
    private Label titleLabel;  

    @FXML
    private CheckBox doneTaskDetailCB;

    private StringProperty title=new SimpleStringProperty();
    private BooleanProperty done=new SimpleBooleanProperty();  
    
    private ObjectProperty<Task> task=new SimpleObjectProperty<>();
    
    public ProjectTaskComponent() {
		super();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectTaskComponent.fxml"));
			loader.setController(this);
			loader.setRoot(this);   
			loader.load(); 
		} catch (IOException e) {e.printStackTrace();} 
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {  
				
		titleLabel.textProperty().bindBidirectional(title);     
		doneTaskDetailCB.selectedProperty().bindBidirectional(done); 
		
		task.addListener((o,ov,nv)->{ 
			title.set(nv.getTitle());
			done.set(nv.isDone()); 
		});
		
		setOnMouseClicked(event->projectTaskComponentClicked());
		   
	} 

	
	@FXML
	private void taskChecked(ActionEvent event) {
		task.get().setDone(done.get());
		TableTasks.update(task.get());
		MainController.mainController.getProjectDetailController().setTasksOnTaskContainer();
	}
	
	private void projectTaskComponentClicked() {
		System.out.println("ey");
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
