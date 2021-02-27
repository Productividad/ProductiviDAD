package dad.productividad.home;

import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;

import dad.productividad.app.MainController;
import dad.productividad.dataManager.TableTasks;
import dad.productividad.task.Task;
import dad.productividad.task.TaskComponent;
import dad.productividad.task.TaskInserterController;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class HomeController implements Initializable {

    @FXML
    private StackPane view;

    @FXML
    private VBox topWrapper;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox taskWrapper;

    //Borrar

    @FXML
    private VBox bottomPane;

    @FXML
    private GridPane dialogPane;

    @FXML
    private Button acceptButton, cancelButton;

    @FXML
    private Label date;

    private ListProperty<Task> normalTask = new SimpleListProperty<>(FXCollections.observableArrayList());
    private ListProperty<Task> favouriteList = new SimpleListProperty<>(FXCollections.observableArrayList());
    private ListProperty<Task> doneList = new SimpleListProperty<>(FXCollections.observableArrayList());

    private ObjectProperty<Task> dialogTask = new SimpleObjectProperty<>();

    /**
     * Home Controller constructor
     */
    public HomeController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HomeView.fxml"));
            loader.setResources(ResourceBundle.getBundle("i18n/strings"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * View initialization
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        scrollPane.setFitToWidth(true);

        insertTaskFromDB();

        TaskInserterController inserter = new TaskInserterController();
        bottomPane.getChildren().add(inserter);
        bottomPane.setPadding(new Insets(10));

        //Borrar

        bottomPane.disableProperty().bind(dialogPane.visibleProperty());
        dialogPane.setVisible(false);
        setDay();
    }

    /**
     * Sets the current day to a Label, considering the various formats depending on the language.
     */
    private void setDay() {
        ResourceBundle rb = ResourceBundle.getBundle("i18n/strings");
        String str = rb.getString("dateformat");
        MessageFormat format = new MessageFormat(str);
        Object arguments[] = {rb.getString(LocalDate.now().getDayOfWeek().toString()), LocalDate.now().getDayOfMonth(),
                rb.getString(LocalDate.now().getMonth().toString()), String.valueOf(LocalDate.now().getYear())};
        format.applyPattern(str);

        date.textProperty().set(format.format(arguments));
    }

    /**
     * Accept button action, from the dialog when deleting a Task
     *
     * @param e
     */
    @FXML
    private void onAcceptDialog(ActionEvent e) {
        TableTasks.delete(dialogTask.get());
        MainController.mainController.updateTaskWrapper();
        dialogPane.setVisible(false);
    }

    /**
     * Cancel button action, from the dialog when deleting a Task
     *
     * @param e
     */
    @FXML
    private void onCancelDialog(ActionEvent e) {
        hideDialog();
    }

    /**
     * Shows the dialog for deleting a selected Task
     *
     * @param task
     */
    public void showDialog(Task task) {
        dialogPane.setVisible(true);
        dialogTask.set(task);
    }

    /**
     * Hides the dialog for deleting a Task
     */
    public void hideDialog() {
        dialogPane.setVisible(false);
    }

    /**
     * Insert tasks from DB in the wrapper
     */
    public void insertTaskFromDB() {

        favouriteList.clear();
        doneList.clear();
        normalTask.clear();
        taskWrapper.getChildren().clear();

        for (Task task : TableTasks.readParentTasks(null)) {
            if (!task.isDone() && task.isFavourite())
                favouriteList.add(task);
            if (task.isDone())
                doneList.add(task);
            if (!task.isDone() && !task.isFavourite())
                normalTask.add(task);
        }

        for (Task task : favouriteList) {
            TaskComponent taskComponent = new TaskComponent();
            taskComponent.setTask(task);
            taskWrapper.getChildren().add(taskComponent);
        }
        for (Task task : normalTask) {
            TaskComponent taskComponent = new TaskComponent();
            taskComponent.setTask(task);
            taskWrapper.getChildren().add(taskComponent);
        }
        for (Task task : doneList) {
            TaskComponent taskComponent = new TaskComponent();
            taskComponent.setTask(task);
            taskComponent.getStyleClass().add("completed-task");

            taskWrapper.getChildren().add(taskComponent);
        }
    }

	/**
	 *
	 * @return the Home View
	 */
    public StackPane getView() {
        return this.view;
    }

}