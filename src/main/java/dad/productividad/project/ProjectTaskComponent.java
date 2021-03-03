package dad.productividad.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.productividad.app.MainController;
import dad.productividad.dataManager.TableProjectComments;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * ProjectTaskComponent view controller
 */
public class ProjectTaskComponent extends GridPane implements Initializable {
	/**
	 * Title label
	 */
    @FXML
    private Label titleLabel;
    
    /**
     * Label which contains the number of comments of a project
     */
    @FXML
    private Label numComentLabel;
    
	/**
	 * Done checkbox
	 */
    @FXML
    private CheckBox doneCB;
	/**
	 * Title
	 */
    private StringProperty title=new SimpleStringProperty();
	/**
	 * Done
	 */
	private BooleanProperty done=new SimpleBooleanProperty();
	/**
	 * Task
	 */
	private ObjectProperty<Task> task=new SimpleObjectProperty<>();
	/**
	 * Media
	 */
	private Media sound=new Media(this.getClass().getResource("/sound/cartoon_wink_magic_sparkle.wav").toExternalForm());
	/**
	 * Mediaplayer
	 */
	private MediaPlayer mediaPlayer;

    /**
     * ProjectTaskComponent constructor
     */
    public ProjectTaskComponent() {
		super();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectTaskComponent.fxml"));
			loader.setController(this);
			loader.setRoot(this);    
			loader.load(); 
		} catch (IOException e) {e.printStackTrace();} 
    }
 

    /**
     * ProjectTaskComponent view initialization
     *
     * @param location
     * @param resources
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {  
				  
		titleLabel.textProperty().bindBidirectional(title);     
		doneCB.selectedProperty().bindBidirectional(done); 
		
		task.addListener((o,ov,nv)->{    
			if(nv!=null) {
				title.set(nv.getTitle());  
				done.set(nv.isDone());   
				int commentsCounter=TableProjectComments.read(task.get().getId()).size();
				if(commentsCounter!=0) {
					numComentLabel.textProperty().set(String.valueOf(commentsCounter));
				}
			} 
		}); 
		 
		setOnMouseClicked(event->projectTaskComponentClicked());
		   
	}  

    /**
     * Done CheckBox checked action
     *
     * @param event
     */
	@FXML
	private void taskChecked(ActionEvent event) {
    	if(done.get()) {
	    	mediaPlayer=new MediaPlayer(sound);
	        mediaPlayer.play();
    	}
		
		task.get().setDone(done.get());
		TableTasks.update(task.get());
		MainController.mainController.getProjectDetailController().setTasksOnTaskContainer();
	}
	
	/**
	 * Opens a dialog in projectDetailController using this getTask()
	 */
	private void projectTaskComponentClicked() {
		MainController.mainController.getProjectDetailController().showDialogTaskDetail(getTask());
	}  
	
    /**
     * @return ObjectProperty of Task task
     */
	public final ObjectProperty<Task> taskProperty() { 
		return this.task;
	}
	
    /**
     * @return Task task
     */
	public final Task getTask() {
		return this.taskProperty().get();
	}
	  
    /**
     * Sets a new task
     *
     * @param task
     */
	public final void setTask(final Task task) {
		this.taskProperty().set(task);
	}
}
