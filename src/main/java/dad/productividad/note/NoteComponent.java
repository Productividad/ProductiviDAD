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

public class NoteComponent extends BorderPane implements Initializable{
    
    @FXML
    private Button checkButton;
    
    @FXML
    private JFXTextArea contentTA;
        
	private StringProperty content=new SimpleStringProperty();
	private ObjectProperty<Note>note=new SimpleObjectProperty<>();
 
	public NoteComponent() {
		super();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NoteComponentView.fxml"));
			loader.setController(this);
			loader.setRoot(this);  
			loader.load();
		} catch (IOException e) {e.printStackTrace();}
	}
	 
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		note.addListener((o,ov,nv)->{
			getStylesheets().clear();
			getStylesheets().add(getClass().getResource(note.get().getColor()).toExternalForm());		

		});
		
		contentTA.textProperty().bindBidirectional(content);
		
		note.addListener((o,ov,nv)->{
			if(nv!=null) {
				content.set(nv.contentProperty().get());
			}
		});

		contentTA.focusedProperty().addListener((o,ov,nv)->{
			if(contentTA.textProperty().get()!=null) {
				note.get().setContent(content.get());
				TableNotes.update(note.get());
			}
		}); 
		 
	}

    @FXML
    void onDeleteNote(ActionEvent event) {
    	
    	TableNotes.delete(note.get());
    	MainController.mainController.getNotesController().readNotes();

    }

    @FXML
    void onOpenOptions(ActionEvent event) {
    	MainController.mainController.getNotesController().showDialogPane(note.get());
    }

	public final ObjectProperty<Note> noteProperty() {
		return this.note;
	}
	

	public final Note getNote() {
		return this.noteProperty().get();
	}
	

	public final void setNote(final Note note) {
		this.noteProperty().set(note);
	}

	public JFXTextArea getContentTA() {
		return contentTA;
	}

	public void setContentTA(JFXTextArea contentTA) {
		this.contentTA = contentTA;
	}
	

	
}
