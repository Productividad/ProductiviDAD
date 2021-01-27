package dad.productiviDAD.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import dad.productiviDAD.model.Project;
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
	 
		
		ProjectCard card1=new ProjectCard();
		Project project1=new Project("Project 1", 27, "#9EE493", false);
		
		card1.prepareProjectCard(project1);
		view.getChildren().add(card1);
		HBox.setHgrow(card1, Priority.ALWAYS);
			
	}
	
	public HBox getView() {
		return this.view;
	}
}
