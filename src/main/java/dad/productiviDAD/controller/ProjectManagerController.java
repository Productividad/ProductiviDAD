package dad.productiviDAD.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class ProjectManagerController implements Initializable{

	@FXML
	private HBox view;
	
	public ProjectManagerController() {
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/ProjectManagerView.fxml"));
			loader.setController(this);
			loader.load();  
		} catch (IOException e) {e.printStackTrace();}  
	}
	
	@Override 
	public void initialize(URL location, ResourceBundle resources) {
	 
		
		for (int i=0;i<3;i++) {
			Random random=new Random();
			
			int red= random.nextInt(255); 
			int green= random.nextInt(255);
			int blue= random.nextInt(255);
			
			ProjectCard carta=new ProjectCard();
	
			
			carta.setStyle("-fx-background-color:rgb("+red+","+green+","+blue+")");
			view.getChildren().add(carta);
			view.setHgrow(carta, Priority.ALWAYS);
		}

		
	}
	
	public HBox getView() {
		return this.view;
	}
}
