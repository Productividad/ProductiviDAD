package dad.productividad.project;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import dad.productividad.app.MainController;
import dad.productividad.dataManager.TableTasks;
import dad.productividad.task.Task;
import dad.productividad.utils.CSSUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Class of projectCardComponent used on projectManagerController
 */
public class ProjectCardComponent extends VBox implements Initializable {
    /**
     * Project card title
     */
    private StringProperty title = new SimpleStringProperty();
    /**
     * Project card progress
     */
    private StringProperty progress = new SimpleStringProperty();
    /**
     * Project card Project
     */
    private ObjectProperty<Project> project = new SimpleObjectProperty<>();
    /**
     * Project card stylesheet path
     */
    private StringProperty styleSheetPath = new SimpleStringProperty();
    /**
     * Title and percentage labels
     */
    @FXML
    private Label projectTitleLabel, percentageLabel;

    /**
     * ProjectCardComponent constructor
     */
    public ProjectCardComponent() {
        super();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectCardComponent.fxml"));
            loader.setResources(ResourceBundle.getBundle("i18n/strings"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialization of ProjectCard view
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        project.addListener((o, ov, nv) -> {
            if (nv != null) {
                title.bindBidirectional(nv.titleProperty());
                styleProjectCard();
                
                double completedTasks=0.0;
                double tasksOfProject=0.0;
                
        		for(Task task:TableTasks.readParentTasks(project.get())) {
        			if(task.isDone())
        				completedTasks+=1.0;
        			tasksOfProject+=1.0;
        		}
        		
        		int progressDouble=(int) ((completedTasks*100.0)/tasksOfProject);
        		progress.set(progressDouble+"%");
            }
        });
        
        projectTitleLabel.textProperty().bindBidirectional(title);
        percentageLabel.textProperty().bind(progress);
    }

    /**
     * Open the projectDetailController with this project and his styleSheet
     *
     * @param event
     */
    @FXML
    private void onAccessProject(ActionEvent event) {
        MainController.mainController.openProject(project.get(), styleSheetPath.get());
    }

    /**
     * Opens dialogDelete with this project.get()
     *
     * @param event
     */
    @FXML
    private void onDeleteProject(ActionEvent event) {
        MainController.mainController.getProjectManagerController().showDeleteDialog(project.get());
    }

    /**
     * Opens dialogProject with this project.get()
     *
     * @param event
     */
    @FXML
    private void onEditProject(ActionEvent event) {
        MainController.mainController.getProjectManagerController().showModifyDialog(project.get());
    }

    /**
     * Check the color values from a project and applies it to a projectCard
     */
    private void styleProjectCard() {

        String textColor = "white";
        if (!getProject().isWhite())
            textColor = "black";

        Map<String, String> params = new HashMap<>();
        params.put("cardColor", getProject().getColor());
        params.put("textColor", textColor);

        styleSheetPath.set(CSSUtils.generateCss("/css/ProjectCardStyle.txt", params));

        project.get().setStyleSheet(styleSheetPath.get());
        this.getStylesheets().setAll(styleSheetPath.get());
    }

    /**
     * @return ObjectProperty of Project project variable
     */
    public final ObjectProperty<Project> projectProperty() {
        return this.project;
    }

    /**
     * @return Project of project variable
     */
    public final Project getProject() {
        return this.projectProperty().get();
    }

    /**
     * Sets a new project
     *
     * @param project
     */
    public final void setProject(final Project project) {
        this.projectProperty().set(project);
    }
}