package dad.productividad.task;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class TaskComponent extends VBox implements Initializable {

	@FXML
    private Label taskTitleLabel;

    @FXML
    private CheckBox doneCheckBox,favouriteCheckBox;
    
    @FXML
    private GridPane grid;

    private ObjectProperty<Task>task=new SimpleObjectProperty<>();
    private StringProperty title=new SimpleStringProperty();
    private BooleanProperty done=new SimpleBooleanProperty();
    private BooleanProperty favourite=new SimpleBooleanProperty();
    
	private Media sound=new Media(this.getClass().getResource("/sound/cartoon_wink_magic_sparkle.wav").toExternalForm());
	private MediaPlayer mediaPlayer;
    
    public TaskComponent() {
		super();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TaskComponent.fxml"));
			loader.setController(this);
			loader.setRoot(this); 
			loader.load();
		} catch (IOException e) {e.printStackTrace();}
    } 
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		taskTitleLabel.textProperty().bindBidirectional(title);
		doneCheckBox.selectedProperty().bindBidirectional(done);
		favouriteCheckBox.selectedProperty().bindBidirectional(favourite);
		
		task.addListener((o,ov,nv)->{
			title.set(task.get().getTitle());
			done.set(task.get().isDone());
			favourite.set(task.get().isFavourite());
		});
		
		setOnMouseClicked(event->onMouseClicked()); 
	}

	private void onMouseClicked() {
    	MainController.mainController.setTaskOnRightSide(task.get());
	}
	
    @FXML
    private void onDoneClicked(ActionEvent event) {   	
    	if(doneCheckBox.selectedProperty().get()) {
	    	mediaPlayer=new MediaPlayer(sound);
	        mediaPlayer.play();
    	}
    	
    	task.get().setDone(doneCheckBox.selectedProperty().get());
    	MainController.mainController.updateRightSide(task.get());
    	TableTasks.update(task.get());
    	MainController.mainController.updateTaskWrapper();
    }

    @FXML
    private void onFavouriteClicked(ActionEvent event) {
    	task.get().setFavourite(favouriteCheckBox.selectedProperty().get());
    	MainController.mainController.updateRightSide(task.get());
    	TableTasks.update(task.get());
    	MainController.mainController.updateTaskWrapper();
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
