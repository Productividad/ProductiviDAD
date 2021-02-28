package dad.productividad.task;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import dad.productividad.app.MainController;
import dad.productividad.dataManager.TableTasks;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Task Detail controller
 */
public class TaskDetailController implements Initializable {
    /**
     * Task Detail view
     */
    @FXML
    private GridPane view;
    /**
     * Done Checkbox
     */
    @FXML
    private CheckBox doneTaskDetailCB;
    /**
     * Favourite checkbox
     */
    @FXML
    private CheckBox favouriteTaskDetailCB;
    /**
     * Title Task textfield
     */
    @FXML
    private JFXTextField titleTaskDetailTF;
    /**
     * Task description textfield
     */
    @FXML
    private JFXTextArea descriptionTaskDetailTA;
    /**
     * ArrowButton button
     */
    @FXML
    private Button arrowButton;
    /**
     * Creation date label
     */
    @FXML
    private Label creationDateLabel;
    /**
     * Task ObjectProperty
     */
    private ObjectProperty<Task> task = new SimpleObjectProperty<>();
    /**
     * Done
     */
    private BooleanProperty done = new SimpleBooleanProperty();
    /**
     * Favourite
     */
    private BooleanProperty favourite = new SimpleBooleanProperty();
    /**
     * Title
     */
    private StringProperty title = new SimpleStringProperty();
    /**
     * Description
     */
    private StringProperty description = new SimpleStringProperty();
    /**
     * Creation date
     */
    private StringProperty creationDate = new SimpleStringProperty();
    /**
     * Media sound
     */
    private Media sound = new Media(this.getClass().getResource("/sound/cartoon_wink_magic_sparkle.wav").toExternalForm());
    /**
     * Media player
     */
    private MediaPlayer mediaPlayer;

    /**
     * TaskDetailController constructor
     */
    public TaskDetailController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TaskDetail.fxml"));
        loader.setResources(ResourceBundle.getBundle("i18n/strings"));
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * TaskDetail view initialization
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        doneTaskDetailCB.selectedProperty().bindBidirectional(done);
        favouriteTaskDetailCB.selectedProperty().bindBidirectional(favourite);
        titleTaskDetailTF.textProperty().bindBidirectional(title);
        descriptionTaskDetailTA.textProperty().bindBidirectional(description);
        creationDateLabel.textProperty().bindBidirectional(creationDate);

        doneTaskDetailCB.selectedProperty().addListener((o, ov, nv) -> {
            if (doneTaskDetailCB.selectedProperty().get() == true)
                titleTaskDetailTF.getStyleClass().add("completed-task");
            else {
                titleTaskDetailTF.getStyleClass().add("uncompleted-task");
            }
        });

        task.addListener((o, oc, nv) -> {
            done.set(task.get().isDone());
            favourite.set(task.get().isFavourite());
            title.set(task.get().getTitle());
            description.set(task.get().getDescription());

            if (task.get().getCreationDate() != null)
                creationDate.set(task.get().getCreationDate().toString());
        });

        titleTaskDetailTF.focusedProperty().addListener((o, ov, nv) -> {
            if (title.get().isEmpty())
                title.set(task.get().getTitle());
            else {
                if (task.get().getTitle() != title.get()) {
                    task.get().setTitle(title.get());
                    updateTaskAndWrapper();
                }
            }
        });
        descriptionTaskDetailTA.focusedProperty().addListener((o, ov, nv) -> {
            if (description.get() != null) {
                task.get().setDescription(description.get());
                TableTasks.update(task.get());
            }
        });
    }


    /**
     * Arrow Button action
     *
     * @param event
     */
    @FXML
    private void onArrowButton(ActionEvent event) {
        MainController.mainController.setRightSideNull();
    }

    /**
     * TitleTaskDetailTF action
     *
     * @param event
     */
    @FXML
    private void onTitleTaskDetailTF(ActionEvent event) {
        view.requestFocus();
    }

    /**
     * Done CheckBox click action
     *
     * @param event
     */
    @FXML
    private void onDoneClicked(ActionEvent event) {

        if (doneTaskDetailCB.selectedProperty().get()) {
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
            getTask().setCompletedDate(LocalDate.now());
        } else {
            getTask().setCompletedDate(null);
        }
        task.get().setDone(doneTaskDetailCB.selectedProperty().get());
        updateTaskAndWrapper();
    }

    /**
     * Favourite CheckBox click action
     *
     * @param event
     */
    @FXML
    private void onFavouriteClicked(ActionEvent event) {

        task.get().setFavourite(favouriteTaskDetailCB.selectedProperty().get());
        updateTaskAndWrapper();
    }

    /**
     * Updates task and wrapper
     */
    private void updateTaskAndWrapper() {
        TableTasks.update(task.get());
        MainController.mainController.updateTaskWrapper();
        MainController.mainController.updateRightSide(task.get());
    }

    /**
     * Delete Button action
     *
     * @param event
     */
    @FXML
    private void onDeleteTask(ActionEvent event) {

        MainController.mainController.getHomeController().showDialog(task.get());
        MainController.mainController.setRightSideNull();


    }

    /**
     * @return TaskDetail view
     */
    public GridPane getView() {
        return this.view;
    }

    /**
     * @return ObjectProperty of Task task
     */
    public final ObjectProperty<Task> taskProperty() {
        return this.task;
    }

    /**
     * @return Task task
     */
    public final Task getTask() {
        return this.taskProperty().get();
    }

    /**
     * Sets a new task
     *
     * @param task
     */
    public final void setTask(final Task task) {
        this.taskProperty().set(task);
    }


}
