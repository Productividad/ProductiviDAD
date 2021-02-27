package dad.productividad.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import dad.productividad.app.MainController;
import dad.productividad.dataManager.TableProjectComments;
import dad.productividad.task.Task;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

public class ProjectCommentInserter extends HBox implements Initializable{
    @FXML
    private JFXTextField titleTF;

    private StringProperty title=new SimpleStringProperty();
    private ObjectProperty<Task>task=new SimpleObjectProperty<>();
    
    /**
     * Default constructor of ProjectCommentInserter
     */
    public ProjectCommentInserter() {
        super();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectCommentInserter.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * ProjectCommentInserter initialization
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		titleTF.textProperty().bindBidirectional(title);
	} 

	/**
	 * Use insertComment method
	 * @param event
	 */
    @FXML
    private void onInsertTaskButton(ActionEvent event) { 	
    	insertComment();
    }
    
	/**
	 * Use insertComment method
	 * @param event
	 */
    @FXML
    private void onEnter(ActionEvent event) {
    	insertComment();
    }
    /**
     * Inserts a comment into the database and resets the comments wrapper
     * on projectDetail
     */
    private void insertComment() {
    	ProjectComment projectComment=new ProjectComment();
    	projectComment.setIdTask(task.get().getId());
    	projectComment.setContent(title.get());
    	
    	TableProjectComments.insertProjectComments(projectComment);
    	titleTF.clear();
    	MainController.mainController.getProjectDetailController().setProjectComments();
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
