package dad.productiviDAD.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
		
		ProjectCardComponent card1=new ProjectCardComponent();
		Project project1=new Project("Project 1", 27, "#DAD6D6", false);
		setProjectCard(card1,project1);
		 
		ProjectCardComponent card2=new ProjectCardComponent();
		Project project2=new Project("Project 2", 14, "#92BFB1", false); 
		setProjectCard(card2,project2);

		ProjectCardComponent card3=new ProjectCardComponent();
		Project project3=new Project("Project 3", 52, "#F4AC45", false);
		setProjectCard(card3,project3);
 
		ProjectCardComponent card4=new ProjectCardComponent();
		Project project4=new Project("Project 4", 70, "#A61C3C", true);
		setProjectCard(card4,project4); 
		
		ProjectCardComponent card5=new ProjectCardComponent();
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
	private void setProjectCard(ProjectCardComponent card, Project project) {
		card.setProject(project);
		projectCardContainer.getChildren().add(card);
		HBox.setHgrow(card, Priority.ALWAYS);
	} 

    @FXML
    void onAddProject(ActionEvent event) {
    	ProjectEditorDialog dialog=new ProjectEditorDialog();
    	dialog.setTitleDialog("Añadir proyecto");
        dialog.show();
        //TOOD recogida de datos a traves del controlador del dialogo
    }

    @FXML
    void onSeeOldProjects(ActionEvent event) {

    }
	
	public VBox getView() {
		return this.view;
	}
}
 