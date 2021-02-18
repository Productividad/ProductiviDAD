package dad.productividad.task;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import dad.productividad.app.MainController;
import dad.productividad.dataManager.TableTasks;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

public class TaskInserterController extends GridPane implements Initializable{


    @FXML
    private JFXTextField TaskTitleTF;
    
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

		TaskTitleTF.textProperty().bindBidirectional(title);
		
		setStyle("-fx-background-radius:7px;-fx-background-color:white;");

	}
	
    @FXML
    void onEnter(ActionEvent event) {
    	
    	Task task=new Task();
    	task.setTitle(title.get());
    	TableTasks.insertTitleTask(task);
    	MainController.mainController.updateTaskWrapper();
//    	MainController.mainController.updateRightSide(task);
    }

    @FXML
    void onInsertTaskButton(ActionEvent event) {

    }

}
