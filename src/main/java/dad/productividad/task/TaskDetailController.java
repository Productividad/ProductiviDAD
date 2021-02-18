package dad.productividad.task;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import dad.productividad.app.MainController;
import dad.productividad.dataManager.TableTasks;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class TaskDetailController implements Initializable {

    @FXML
    private GridPane view;

    @FXML
    private CheckBox doneTaskDetailCB;
    
    @FXML
    private CheckBox favouriteTaskDetailCB;
    
    @FXML
    private JFXTextField titleTaskDetailTF;

    @FXML
    private JFXTextArea descriptionTaskDetailTA;
    
    @FXML 
    private Button arrowButton;

	private ObjectProperty<Task> task = new SimpleObjectProperty<>();

	private BooleanProperty done=new SimpleBooleanProperty();
	private BooleanProperty favourite=new SimpleBooleanProperty();
	private StringProperty title=new SimpleStringProperty();
	private StringProperty description=new SimpleStringProperty();

	private Media sound=new Media(this.getClass().getResource("/sound/cartoon_wink_magic_sparkle.wav").toExternalForm());
	private MediaPlayer mediaPlayer;
	
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
    
	@FXML
    void onArrowButton(ActionEvent event) {
   	    MainController.mainController.setRightSideNull();
    }
    
    @FXML
    private void onDoneClicked(ActionEvent event) {

    	if(doneTaskDetailCB.selectedProperty().get()) {
	    	mediaPlayer=new MediaPlayer(sound);
	        mediaPlayer.play();
    	}
    	task.get().setDone(doneTaskDetailCB.selectedProperty().get());
    	TableTasks.updateHomeTask(task.get());
    	MainController.mainController.updateTaskWrapper();

    }

    @FXML
    private void onFavouriteClicked(ActionEvent event) {

    	task.get().setFavourite(favouriteTaskDetailCB.selectedProperty().get());
    	TableTasks.updateHomeTask(task.get());
    	MainController.mainController.updateTaskWrapper();
    	
    }
	
	public GridPane getView() { 
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
