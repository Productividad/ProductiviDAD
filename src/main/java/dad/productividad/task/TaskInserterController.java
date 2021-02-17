package dad.productividad.task;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

public class TaskInserterController extends GridPane implements Initializable{


    @FXML
    private JFXTextField TaskTitleTF;
    
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

		setStyle("-fx-background-radius:7px;-fx-background-color:white;");

	}
	
    @FXML
    void onEnter(ActionEvent event) {
    	System.out.println(TaskTitleTF.textProperty().get());
    }

    @FXML
    void onInsertTaskButton(ActionEvent event) {

    }

}
