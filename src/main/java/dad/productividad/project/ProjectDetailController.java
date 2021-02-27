package dad.productividad.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXSpinner;

import dad.productividad.dataManager.TableTasks;
import dad.productividad.task.Task;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ProjectDetailController implements Initializable {

    @FXML
    private GridPane view;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox taskContainer;

    @FXML
    private Label titleProject;

    @FXML
    private JFXSpinner spinner;

    private StringProperty title = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();

    private ObjectProperty<Project> project = new SimpleObjectProperty<>();

    /**
     * ProjectDetailController constructor
     */
    public ProjectDetailController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectDetailView.fxml"));
            loader.setResources(ResourceBundle.getBundle("i18n/strings"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Project detail view initialization
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        scrollPane.setFitToWidth(true);
        titleProject.textProperty().bind(title);

        project.addListener((o, ov, nv) -> {
            if (nv != null) {
                view.getStylesheets().setAll(nv.getStyleSheet());
                title.set(nv.getTitle());
                description.set(nv.getDescription());
                setTasksOnTaskContainer();
            }
        });
    }

    /**
     * Add tasks to the container
     */
    public void setTasksOnTaskContainer() {
        taskContainer.getChildren().clear();

        double tasksOfProject = 0.0;
        double completedTasks = 0.0;

        for (Task task : TableTasks.readParentTasks(project.get())) {
            ProjectTaskComponent taskCard = new ProjectTaskComponent();
            taskCard.setTask(task);
            taskContainer.getChildren().add(taskCard);
            if (task.isDone())
                completedTasks += 1.0;
            tasksOfProject += 1.0;
        }
        spinner.progressProperty().set(((completedTasks * 100.0) / tasksOfProject) / 100.0);
    }

    /**
     * @return The ProjectDetail view
     */
    public GridPane getView() {
        return this.view;
    }

    /**
     * @return ObjectProperty of Project of project variable
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
