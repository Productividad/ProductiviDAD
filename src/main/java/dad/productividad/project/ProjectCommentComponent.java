package dad.productividad.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.productividad.app.MainController;
import dad.productividad.dataManager.TableProjectComments;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * Project comment component
 */
public class ProjectCommentComponent extends GridPane implements Initializable {
    /**
     * Project Comment title label
     */
    @FXML
    private Label titleComment;
    /**
     * Project Comment title
     */
    private StringProperty title = new SimpleStringProperty();
    /**
     * ProjectComment objectProperty
     */
    private ObjectProperty<ProjectComment> projectComment = new SimpleObjectProperty<>();

    /**
     * ProjectComment constructor
     */
    public ProjectCommentComponent() {
        super();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectComment.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ProjectComment initialization
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        titleComment.textProperty().bind(title);

        projectComment.addListener((o, ov, nv) -> {
            if (nv != null) {
                title.set(nv.getContent());
            }
        });
    }

    /**
     * Deletes the projectComment from the database and resets the list of projectComments
     * on projectDetailController
     *
     * @param event
     */
    @FXML
    void onDeleteComment(ActionEvent event) {

        TableProjectComments.delete(getProjectComment());
        MainController.mainController.getProjectDetailController().setProjectComments();
    }

    /**
     * @return ObjectProperty of ProjectComment
     */
    public final ObjectProperty<ProjectComment> projectCommentProperty() {
        return this.projectComment;
    }

    /**
     * @return ProjectComment
     */
    public final ProjectComment getProjectComment() {
        return this.projectCommentProperty().get();
    }

    /**
     * Set a new projectComment
     * @param projectComment
     */
    public final void setProjectComment(final ProjectComment projectComment) {
        this.projectCommentProperty().set(projectComment);
    }


}
