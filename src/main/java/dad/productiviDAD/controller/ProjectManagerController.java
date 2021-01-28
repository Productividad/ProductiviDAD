package dad.productiviDAD.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.NotificationPane;

import dad.productiviDAD.model.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
/**
 * Controller class of ProjectManagerView
 */
public class ProjectManagerController implements Initializable{

	@FXML 
	private VBox view;
	
	@FXML 
	private HBox projectCardContainer;
	
	@FXML
	private Button addProjectButton,SeeOldProjectButton;
	 
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
		Project project1=new Project("Project 1", 27, "#DAD6D6", false);
		setProjectCard(card1,project1);
		 
		ProjectCard card2=new ProjectCard();
		Project project2=new Project("Project 2", 14, "#92BFB1", false); 
		setProjectCard(card2,project2);

		ProjectCard card3=new ProjectCard();
		Project project3=new Project("Project 3", 52, "#F4AC45", false);
		setProjectCard(card3,project3);
 
		ProjectCard card4=new ProjectCard();
		Project project4=new Project("Project 4", 70, "#A61C3C", true);
		setProjectCard(card4,project4); 
		
		ProjectCard card5=new ProjectCard();
		Project project5=new Project("Project 5", 70, "#A42C3C", true);
		setProjectCard(card5,project5);
		
	}  
	
	/**
	 * Counts the number of childrens that projectCardContainer have
	 * @return Int number of children
	 */
	private int countChildrens() {
		int counter=0;
		
		for(Node project: projectCardContainer.getChildren())
			counter++; 
	
		return counter;
	}
	/**
	 * Combines a Project and a ProjectCard and set the result in the 
	 * projectCardContainer
	 * @param card
	 * @param project
	 */
	private void setProjectCard(ProjectCard card, Project project) {
		card.styleProjectCard(project);
		projectCardContainer.getChildren().add(card);
		HBox.setHgrow(card, Priority.ALWAYS);
	} 
	
	public VBox getView() {
		return this.view;
	}
	
    @FXML
    void onAddProject(ActionEvent event) {
        
    }

    @FXML
    void onSeeOldProjects(ActionEvent event) {

    }
}
 