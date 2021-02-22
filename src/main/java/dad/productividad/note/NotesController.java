package dad.productividad.note;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXMasonryPane;

import dad.productividad.dataManager.TableNotes;
import javafx.beans.property.ListProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class NotesController implements Initializable{



	@FXML
	private VBox view;

	@FXML
	private Button addNotebutton;

	@FXML
	private ScrollPane scrollpane;

	@FXML
	private JFXMasonryPane notesWrapper;


	private List<Note> noteList;
	 
	public NotesController() {  
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NotesManagerView.fxml"));
			loader.setController(this);
			loader.load(); 
		} catch (IOException e) {   
			e.printStackTrace();   
		}  
	}   
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		scrollpane.setFitToWidth(true);
		readNotes();
		//notesWrapper.centerShapeProperty();
		notesWrapper.clearLayout();
//		notesWrapper.setPadding(new Insets(0, 30, 30, 140));
	}

	private void readNotes() {
    	for(Note note : TableNotes.read(20)) {
	    	NoteComponent component=new NoteComponent();
	    	component.setNote(note); 
	
	    	notesWrapper.getChildren().addAll(component);
	    	notesWrapper.setCellHeight(314.0);
	    	notesWrapper.setCellWidth(306.0);
    	}	
	}

    @FXML
    void onAddNoteAction(ActionEvent event) {

		NoteComponent postIt1=new NoteComponent();
		Note note1=new Note("","#708D81");
		postIt1.setNote(note1);
		notesWrapper.getChildren().add(postIt1);
		TableNotes.insertNote(note1);
		HBox.setHgrow(postIt1, Priority.ALWAYS);

	} 


	public VBox getView() {
		return this.view;
	}



}

