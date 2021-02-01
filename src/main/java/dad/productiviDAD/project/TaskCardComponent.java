package dad.productiviDAD.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.productiviDAD.task.Task;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TaskCardComponent extends VBox implements Initializable{

	private StringProperty title=new SimpleStringProperty();
	private StringProperty taskRemaining=new SimpleStringProperty();
	private ObjectProperty<Task>task=new SimpleObjectProperty<>();
	//TODO Tema subTareas contar tareas restantes como lo manejamos
    @FXML
    private Label titleLabel,taskRemainingLabel;
	
    public TaskCardComponent() {
        super();
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TaskCardComponent.fxml"));
    		loader.setController(this);
    		loader.setRoot(this); 
    		loader.load();
    	} catch (IOException e) {e.printStackTrace();}
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		task.addListener((o,ov,nv)->{
			if(nv!=null) {
				title.bindBidirectional(nv.titleProperty());
//				task
			}
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
