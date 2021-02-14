package dad.productividad.project;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller class of ProjectManagerView
 */
public class ProjectManagerController implements Initializable {

	@FXML
	private VBox view;

	@FXML
	private HBox projectCardContainer;

	@FXML
	private Button addProjectButton, SeeOldProjectButton;
	private ListProperty<Project> projectsList = new SimpleListProperty<>(FXCollections.observableArrayList());

	public ProjectManagerController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectManagerView.fxml"));
			loader.setResources(ResourceBundle.getBundle("i18n/project"));
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
		ProjectEditorDialog dialog = new ProjectEditorDialog();
		dialog.setTitleDialog("Añadir proyecto");
		Optional<Project> result = dialog.showAndWait();
		if (result.isPresent()) {
			getProjectsList().add(result.get());
			addProjectCard(result.get());
		}

	}

	@FXML
	void onSeeOldProjects(ActionEvent event) {

	}

	public VBox getView() {
		return this.view;
	}

	public final ListProperty<Project> projectsListProperty() {
		return this.projectsList;
	}

	public final ObservableList<Project> getProjectsList() {
		return this.projectsListProperty().get();
	}

}
