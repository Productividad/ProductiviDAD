package dad.productividad.note;

import dad.productividad.dataManager.TableNotes;
import dad.productividad.dataManager.TableProjects;
import dad.productividad.project.Project;
import dad.productividad.project.ProjectCardComponent;
import dad.productividad.project.ProjectEditorDialog;
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

public class NoteManagerController implements Initializable {
    @FXML
    private VBox view;

    @FXML
    private HBox projectCardContainer;

    @FXML
    private Button addProjectButton, SeeOldProjectButton;
    private ListProperty<Note> notelist = new SimpleListProperty<>(FXCollections.observableArrayList());

    public NoteManagerController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NoteManagerView.fxml"));
            loader.setResources(ResourceBundle.getBundle("i18n/strings"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Note notes : TableNotes.read(20)) {
            getNotesList().add(notes);
            addProjectCard(notes);
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
     */
    private void addProjectCard(Note notes) {
        NoteComponent card = new NoteComponent();
        card.setNote(notes);
        projectCardContainer.getChildren().add(card);
        HBox.setHgrow(card, Priority.ALWAYS);
    }

    /*@FXML
    void onAddProject(ActionEvent event) {
        ProjectEditorDialog dialog = new ProjectEditorDialog();
        dialog.setTitleDialog("Añadir proyecto");
        Optional<Project> result = dialog.showAndWait();
        if (result.isPresent()) {
            getNotesList().add(result.get());
            addProjectCard(result.get());
        }

    }*/

    @FXML
    void onSeeOldProjects(ActionEvent event) {

    }

    public VBox getView() {
        return this.view;
    }

    public final ListProperty<Note> notesListProperty() {
        return this.notelist;
    }

    public final ObservableList<Note> getNotesList() {
        return this.notesListProperty().get();
    }

}
