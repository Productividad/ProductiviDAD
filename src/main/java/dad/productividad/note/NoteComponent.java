package dad.productividad.note;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;

import dad.productividad.app.MainController;
import dad.productividad.dataManager.TableNotes;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * Note Component controller
 */
public class NoteComponent extends BorderPane implements Initializable {
    /**
     * checkButton
     */
    @FXML
    private Button checkButton;
    /**
     * Note textArea
     */
    @FXML
    private JFXTextArea contentTA;
    /**
     * Note content
     */
    private StringProperty content = new SimpleStringProperty();
    /**
     * Note object
     */
    private ObjectProperty<Note> note = new SimpleObjectProperty<>();

    /**
     * Note component constructor
     */
    public NoteComponent() {
        super();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NoteComponentView.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Note component view initialization
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        note.addListener((o, ov, nv) -> {
            getStylesheets().clear();
            getStylesheets().add(getClass().getResource(note.get().getColor()).toExternalForm());

        });

        contentTA.textProperty().bindBidirectional(content);

        note.addListener((o, ov, nv) -> {
            if (nv != null) {
                content.set(nv.contentProperty().get());
            }
        });

        contentTA.focusedProperty().addListener((o, ov, nv) -> {
            if (contentTA.textProperty().get() != null) {
                note.get().setContent(content.get());
                TableNotes.update(note.get());
            }
        });

    }

    /**
     * Delete button action
     *
     * @param event
     */
    @FXML
    void onDeleteNote(ActionEvent event) {

        TableNotes.delete(note.get());
        MainController.mainController.getNotesController().readNotes();

    }

    /**
     * Options button action
     *
     * @param event
     */
    @FXML
    void onOpenOptions(ActionEvent event) {
        MainController.mainController.getNotesController().showDialogPane(note.get());
    }

    /**
     * @return ObjectProperty of Note
     */
    public final ObjectProperty<Note> noteProperty() {
        return this.note;
    }

    /**
     * @return Note
     */
    public final Note getNote() {
        return this.noteProperty().get();
    }

    /**
     * Sets a new Note
     *
     * @param note
     */
    public final void setNote(final Note note) {
        this.noteProperty().set(note);
    }

    /**
     * @return TextArea content
     */
    public JFXTextArea getContentTA() {
        return contentTA;
    }

    /**
     * Set content in TextArea
     *
     * @param contentTA
     */
    public void setContentTA(JFXTextArea contentTA) {
        this.contentTA = contentTA;
    }


}
