package dad.productividad.project;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import dad.productividad.dataManager.TableProjects;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * Controller class of ProjectManagerView
 */
public class ProjectManagerController implements Initializable {

	@FXML
	private StackPane view;

    @FXML
    private GridPane dialogProject;

    @FXML
    private JFXTextField titleTF;

    @FXML
    private HBox projectCardContainer;
    
    @FXML
    private JFXTextArea descriptionTA;

    @FXML 
    private JFXCheckBox whiteText;

    @FXML
    private JFXColorPicker colorPicker;

    @FXML
    private JFXDatePicker datePicker;

	@FXML
	private Button addProjectButton;
	 
	private ListProperty<Project> projectsList = new SimpleListProperty<>(FXCollections.observableArrayList());

	public ProjectManagerController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectManagerView.fxml"));
			loader.setResources(ResourceBundle.getBundle("i18n/strings"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for (Project project : TableProjects.read(5)) {
			getProjectsList().add(project);
			addProjectCard(project);
		}
		dialogProject.setVisible(false);
		colorPicker.setValue(new Color(0, 0, 0, 0));

	}

	/**
	 * Counts the number of childrens that projectCardContainer have
	 * 
	 * @return Int number of children
	 */
	private int countChildrens() {
		int counter = 0;

		for (Node project : projectCardContainer.getChildren())
			counter++;

		return counter;
	} 

	/**
	 * Combines a Project and a ProjectCard and set the result in the
	 * projectCardContainer
	 * 
	 * @param card
	 * @param project
	 */ 
	private void addProjectCard(Project project) {
		ProjectCardComponent card = new ProjectCardComponent();
		card.setProject(project);
		projectCardContainer.getChildren().add(card);
		HBox.setHgrow(card, Priority.ALWAYS);
	} 

	@FXML
	void onAddProject(ActionEvent event) {
		dialogProject.setVisible(true);
	}
	
    @FXML
    private void onAcceptDialog(ActionEvent event) {
//  TODO  	
//		getProjectsList().add(result.get());
//		addProjectCard(result.get());
    	dialogProject.setVisible(false);
    }

    @FXML
    private void onCancelDialog(ActionEvent event) {
    	titleTF.clear();
    	descriptionTA.clear();
    	whiteText.selectedProperty().set(false);
		colorPicker.setValue(new Color(0, 0, 0, 0));
		datePicker.setValue(null);
    	dialogProject.setVisible(false);
    }
	public StackPane getView() {
		return this.view;
	}

	public final ListProperty<Project> projectsListProperty() {
		return this.projectsList;
	}

	public final ObservableList<Project> getProjectsList() {
		return this.projectsListProperty().get();
	}

	
	
}
