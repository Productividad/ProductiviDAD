package dad.productividad.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import dad.productividad.app.MainController;
import dad.productividad.dataManager.TableProjects;
import dad.productividad.utils.ColorUtils;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
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
    private GridPane dialogProject,dialogDelete;

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
	private Button addProjectButton, acceptDialogProject;
	 
	private ListProperty<Project> projectsList = new SimpleListProperty<>(FXCollections.observableArrayList());
	
	private ObjectProperty<Project> projectToDelete=new SimpleObjectProperty<>();
	private ObjectProperty<Project> projectToModify=new SimpleObjectProperty<>();
	
	private BooleanProperty dialogCreating=new SimpleBooleanProperty();
	
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
		
		readProjects();
		dialogProject.setVisible(false);
		dialogDelete.setVisible(false);
		colorPicker.setValue(new Color(1, 1, 1, 1));		
	
		acceptDialogProject.disableProperty().bind((Bindings.isEmpty(titleTF.textProperty())
				.or(Bindings.isEmpty(descriptionTA.textProperty())
						.or(Bindings.isNull(datePicker.valueProperty())))));
	}

	/**
	 * Clears the projectCardContainer.
	 * Read the projects from the db.
	 * Insert the projects on projectCardContainer.
	 * If projectCardContainer have 5 childrens then addProjectButton is disabled
	 */
	public void readProjects() {
		projectCardContainer.getChildren().clear();
		for (Project project : TableProjects.read(5)) {
			getProjectsList().add(project);
			addProjectCard(project);
		}
		addProjectButton.setDisable(countChildrens()==5);
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
		dialogCreating.set(true);;
	}
	
    @FXML
    private void onAcceptDialog(ActionEvent event) {
    	
    	if(dialogCreating.get()) {
		    try {
		    	Project project=new Project();
		    	project.setTitle(titleTF.textProperty().get());
		    	project.setDescription(descriptionTA.textProperty().get());
		    	project.setColor(ColorUtils.getHexString(colorPicker.getValue()));
		    	project.setWhite(whiteText.isSelected());
		    	project.setDeadLine(datePicker.getValue());   		 
		    	TableProjects.create(project); 		
		    		
		    }catch(Exception e) {e.printStackTrace();}
		    	
    	}else {
	    	projectToModify.get().setTitle(titleTF.textProperty().get());
	    	projectToModify.get().setDescription(descriptionTA.textProperty().get());
	    	projectToModify.get().setColor(ColorUtils.getHexString(colorPicker.getValue()));
	    	projectToModify.get().setWhite(whiteText.isSelected());
	    	projectToModify.get().setDeadLine(datePicker.getValue());   
	    	TableProjects.update(projectToModify.get());
    	}
    	
	    resetDialogProject();
	    readProjects();
		hideDialogs();
    }

    @FXML
    private void onCancelDialog(ActionEvent event) {
    	resetDialogProject();
		hideDialogs();
    }
    
    private void resetDialogProject() {
    	titleTF.clear();
    	descriptionTA.clear();
    	whiteText.selectedProperty().set(false);
		colorPicker.setValue(new Color(1, 1, 1, 1));
		datePicker.setValue(null);
    }
    
    public void showModifyDialog(Project project) {
    	dialogCreating.set(false);
    	projectToModify.set(project);
    	titleTF.textProperty().set(project.getTitle());
    	descriptionTA.textProperty().set(project.getDescription());
    	whiteText.selectedProperty().set(project.isWhite());
    	datePicker.setValue(project.getDeadLine());
    	
    	dialogProject.setVisible(true);
    }
    
    public void showDeleteDialog(Project project) {
    	projectToDelete.set(project);
    	dialogDelete.setVisible(true);
    }
    
    @FXML
    private void onAcceptDeleteDialog(ActionEvent event) {
    	TableProjects.delete(projectToDelete.get());
    	MainController.mainController.getProjectManagerController().readProjects();
    	hideDialogs();
    }
    
    @FXML
    private void onCancelDeleteDialog(ActionEvent event) {
    	projectToDelete.set(null);
    	hideDialogs();
    }
    
    public void hideDialogs() {
    	dialogProject.setVisible(false);
    	dialogDelete.setVisible(false); 
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

	public final BooleanProperty dialogCreatingProperty() {
		return this.dialogCreating;
	}
	

	public final boolean isDialogCreating() {
		return this.dialogCreatingProperty().get();
	}
	

	public final void setDialogCreating(final boolean dialogIsCreating) {
		this.dialogCreatingProperty().set(dialogIsCreating);
	}
	
	
}
