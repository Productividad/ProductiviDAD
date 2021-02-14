package dad.productiviDAD.home;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.productiviDAD.task.Task;
import dad.productiviDAD.task.TaskComponent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class HomeController implements Initializable {

	@FXML
	private VBox view;
	
	@FXML
	private ScrollPane scrollPane;
	
	@FXML
	private VBox taskWrapper;
	
	public HomeController() { 
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HomeView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace(); 
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		scrollPane.setFitToWidth(true);

		for(int i=0;i<=2;i++) {
			Task task=new Task();
			task.setTitle("Titulo de la tarea "+i);
			task.setFavourite(true);
			task.setDone(false);
			TaskComponent taskComponent=new TaskComponent();
			taskComponent.setTask(task);
			taskWrapper.getChildren().add(taskComponent); 
		}
		
		view.setSpacing(5);  
		view.setPadding(new Insets(10,10,10,10));
	}

	public VBox getView() {
		return this.view;
	}
	
}
