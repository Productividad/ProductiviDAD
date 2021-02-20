package dad.productividad.task;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import dad.productividad.app.MainController;
import dad.productividad.dataManager.TableTasks;
import dad.productividad.segmentedBarUtils.StatusType;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

public class TaskInserterController extends HBox implements Initializable{

    @FXML
    private JFXTextField titleTF;
    
    private StringProperty title=new SimpleStringProperty();
    
    public TaskInserterController() {
    	
	    try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TaskInserter.fxml"));
			loader.setController(this);
			loader.setRoot(this); 
			loader.load();
		} catch (IOException e) {e.printStackTrace();}
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		titleTF.textProperty().bindBidirectional(title);
	}  
	 
    @FXML
    private void onEnter(ActionEvent event) {
    	System.out.println("bnij");
    	insertTask();
    }

    @FXML
    private void onInsertTaskButton(ActionEvent event) {
    	insertTask();
    }
    
    /**
     * Inserts a task into the database, set that task into the right 
     * side of mainView and reset the focusProperty
     */
    private void insertTask() {
    	
    	if(title.get()!=null) {
    		if(!title.get().isEmpty()) {
		    	Task task=new Task();
		    	task.setTitle(title.get());
		    	task.setCreationDate(LocalDate.now());
		    	task.setStatus(StatusType.TODO);
		    	TableTasks.insert(task);
		    	MainController.mainController.updateTaskWrapper();
		    	MainController.mainController.setTaskOnRightSide(task);
		    	title.set("");
		    	requestFocus();
    		}
    	}
    }
}
