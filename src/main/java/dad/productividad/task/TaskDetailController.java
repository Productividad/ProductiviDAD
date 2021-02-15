package dad.productiviDAD.task;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

public class TaskDetailController implements Initializable {

    @FXML
    private VBox view;

    @FXML
    private CheckBox doneTaskDetailCB;
    
    @FXML
    private CheckBox favouriteTaskDetailCB;
    
    @FXML
    private JFXTextField titleTaskDetailTF;

    @FXML
    private JFXTextArea descriptionTaskDetailTA;

	private ObjectProperty<Task> task = new SimpleObjectProperty<>();

	private BooleanProperty done=new SimpleBooleanProperty();
	private BooleanProperty favourite=new SimpleBooleanProperty();
	private StringProperty title=new SimpleStringProperty();
	private StringProperty description=new SimpleStringProperty();

	public TaskDetailController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TaskDetail.fxml"));
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		doneTaskDetailCB.selectedProperty().bindBidirectional(done);
		favouriteTaskDetailCB.selectedProperty().bindBidirectional(favourite);
		titleTaskDetailTF.textProperty().bindBidirectional(title);
		descriptionTaskDetailTA.textProperty().bindBidirectional(description);
		
		task.addListener((o,oc,nv)->{
			done.set(task.get().isDone());
			favourite.set(task.get().isFavourite());
			title.set(task.get().getTitle());
			description.set(task.get().getDescription());
		});

	}    
  
	public VBox getView() { 
		return this.view; 
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
