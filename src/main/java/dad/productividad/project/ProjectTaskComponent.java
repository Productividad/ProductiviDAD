package dad.productividad.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXSpinner;

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
import javafx.scene.layout.GridPane;

public class ProjectTaskComponent extends GridPane implements Initializable{

    @FXML
    private Label titleLabel,subTaskRemainLabel;

    @FXML
    private JFXSpinner spinner;

    private StringProperty title=new SimpleStringProperty();
    private StringProperty subTaskTotal=new SimpleStringProperty();
    
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
		
		setStyle("-fx-background-radius:20;"); 
		
		titleLabel.textProperty().bindBidirectional(title);     
		subTaskRemainLabel.textProperty().bindBidirectional(subTaskTotal); 
		 
		task.addListener((o,ov,nv)->{ 
			title.set(nv.getTitle());
			subTaskTotal.set(nv.getChildTasks().size()+" tareas");
			
			int numDoneTasks=0;
			
			for(Task task:nv.getChildTasks()) {
				if(task.getStatus().equals(StatusType.DONE))
					numDoneTasks+=1;
			}
						
			spinner.progressProperty().set((numDoneTasks*100)/nv.getChildTasks().size());

		});
		
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
