package dad.productividad.note;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXMasonryPane;

import dad.productividad.dataManager.TableNotes;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

public class NotesController implements Initializable {

    @FXML
    private StackPane view;

    @FXML
    private Button addNotebutton;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private GridPane dialogColor;

    @FXML
    private JFXMasonryPane notesWrapper;

    @FXML
    private Pane colorPick1, colorPick2, colorPick3, colorPick4, colorPick5, colorPick6, colorPick7;

    private StringProperty selectedColor = new SimpleStringProperty();

    private ObjectProperty<Note> note = new SimpleObjectProperty<>();

    /**
     * Notes Controller constructor
     */
    public NotesController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NotesManagerView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Notes view initialization
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        scrollPane.setFitToWidth(true);
        readNotes();
        notesWrapper.clearLayout();
        notesWrapper.setPadding(new Insets(15));

        colorPick1.setOnMouseClicked(event -> colorPicked1());
        colorPick2.setOnMouseClicked(event -> colorPicked2());
        colorPick3.setOnMouseClicked(event -> colorPicked3());
        colorPick4.setOnMouseClicked(event -> colorPicked4());
        colorPick5.setOnMouseClicked(event -> colorPicked5());
        colorPick6.setOnMouseClicked(event -> colorPicked6());
        colorPick7.setOnMouseClicked(event -> colorPicked7());

        dialogColor.setVisible(false);

    }

    /**
     * Color 1 picked
     */
    private void colorPicked1() {
        setColorPickersDisableFalse();
        colorPick1.setDisable(true);
        selectedColor.set("/css/notes/noteComponent1.css");
    }

    /**
     * Color 2 picked
     */
    private void colorPicked2() {
        setColorPickersDisableFalse();
        colorPick2.setDisable(true);
        selectedColor.set("/css/notes/noteComponent2.css");
    }

    /**
     * Color 3 picked
     */
    private void colorPicked3() {
        setColorPickersDisableFalse();
        colorPick3.setDisable(true);
        selectedColor.set("/css/notes/noteComponent3.css");
    }

    /**
     * Color 4 picked
     */
    private void colorPicked4() {
        setColorPickersDisableFalse();
        colorPick4.setDisable(true);
        selectedColor.set("/css/notes/noteComponent4.css");
    }

    /**
     * Color 5 picked
     */
    private void colorPicked5() {
        setColorPickersDisableFalse();
        colorPick5.setDisable(true);
        selectedColor.set("/css/notes/noteComponent5.css");
    }

    /**
     * Color 6 picked
     */
    private void colorPicked6() {
        setColorPickersDisableFalse();
        colorPick6.setDisable(true);
        selectedColor.set("/css/notes/noteComponent6.css");
    }

    /**
     * Color 7 picked
     */
    private void colorPicked7() {
        setColorPickersDisableFalse();
        colorPick7.setDisable(true);
        selectedColor.set("/css/notes/noteComponent7.css");
    }

    /**
     * Color pickers disable
     */
    private void setColorPickersDisableFalse() {
        colorPick1.setDisable(false);
        colorPick2.setDisable(false);
        colorPick3.setDisable(false);
        colorPick4.setDisable(false);
        colorPick5.setDisable(false);
        colorPick6.setDisable(false);
        colorPick7.setDisable(false);
    }

    /**
     * Shows color picker dialog
     */
    public void showDialogPane() {
        dialogColor.setVisible(true);
    }

    /**
     * Shows dialog of note
     *
     * @param note
     */
    public void showDialogPane(Note note) {
        dialogColor.setVisible(true);
        this.note.set(note);
    }

    /**
     * Read notes from DB
     */
    public void readNotes() {
        notesWrapper.getChildren().clear();
        for (Note note : TableNotes.read(20)) {
            NoteComponent component = new NoteComponent();
            component.setNote(note);

            notesWrapper.getChildren().addAll(component);
            notesWrapper.setCellHeight(314.0);
            notesWrapper.setCellWidth(306.0);
        }
    }

    /**
     * Add note button action
     *
     * @param event
     */
    @FXML
    private void onAddNoteAction(ActionEvent event) {

        NoteComponent postIt = new NoteComponent();
        Note note = new Note();
        note.setContent("");
        notesWrapper.getChildren().add(postIt);
        TableNotes.insertNote(note);
        note.setColor(TableNotes.returnColor(note.getId()));
        postIt.setNote(note);
        HBox.setHgrow(postIt, Priority.ALWAYS);

    }

    /**
     * Accept dialog action
     *
     * @param event
     */
    @FXML
    private void onAcceptDialog(ActionEvent event) {
        note.get().setColor(selectedColor.get());
        TableNotes.updateColor(note.get());
        dialogColor.setVisible(false);
        readNotes();
    }

    /**
     * Cancel dialog action
     *
     * @param event
     */
    @FXML
    private void onCancelDialog(ActionEvent event) {
        dialogColor.setVisible(false);
    }

    /**
     * @return Notes view
     */
    public StackPane getView() {
        return this.view;
    }

}

