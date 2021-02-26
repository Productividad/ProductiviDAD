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

public class NotesController implements Initializable{

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
    private Pane colorPick1,colorPick2,colorPick3,colorPick4,colorPick5,colorPick6,colorPick7;
	
	private StringProperty selectedColor=new SimpleStringProperty();
	
	private ObjectProperty<Note> note=new SimpleObjectProperty<>();
	 
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

		scrollPane.setFitToWidth(true);
		readNotes();
		notesWrapper.clearLayout();
		notesWrapper.setPadding(new Insets(15));
		
		colorPick1.setOnMouseClicked(event->colorPicked1());
		colorPick2.setOnMouseClicked(event->colorPicked2());
		colorPick3.setOnMouseClicked(event->colorPicked3());
		colorPick4.setOnMouseClicked(event->colorPicked4());
		colorPick5.setOnMouseClicked(event->colorPicked5());
		colorPick6.setOnMouseClicked(event->colorPicked6());
		colorPick7.setOnMouseClicked(event->colorPicked7());
		
		dialogColor.setVisible(false);

	}

	private void colorPicked1() {
		setColorPickersDisableFalse();
		colorPick1.setDisable(true);
		selectedColor.set("/css/notes/noteComponent1.css");
	}
	
	private void colorPicked2() {
		setColorPickersDisableFalse();
		colorPick2.setDisable(true);
		selectedColor.set("/css/notes/noteComponent2.css");
	}
	
	private void colorPicked3() {
		setColorPickersDisableFalse();
		colorPick3.setDisable(true);
		selectedColor.set("/css/notes/noteComponent3.css");
	}
	
	private void colorPicked4() {
		setColorPickersDisableFalse();
		colorPick4.setDisable(true);
		selectedColor.set("/css/notes/noteComponent4.css");
	}
	
	private void colorPicked5() {
		setColorPickersDisableFalse();
		colorPick5.setDisable(true);
		selectedColor.set("/css/notes/noteComponent5.css");
	} 
	
	private void colorPicked6() {
		setColorPickersDisableFalse();
		colorPick6.setDisable(true);
		selectedColor.set("/css/notes/noteComponent6.css");
	}
	
	private void colorPicked7() {
		setColorPickersDisableFalse();
		colorPick7.setDisable(true);
		selectedColor.set("/css/notes/noteComponent7.css");
	}
	
	private void setColorPickersDisableFalse() {
		colorPick1.setDisable(false);
		colorPick2.setDisable(false);
		colorPick3.setDisable(false);
		colorPick4.setDisable(false);
		colorPick5.setDisable(false);
		colorPick6.setDisable(false);
		colorPick7.setDisable(false);
	}
	
	public void showDialogPane() {
		dialogColor.setVisible(true);
	}
	
	public void showDialogPane(Note note) {
		dialogColor.setVisible(true);
		this.note.set(note);
	}
	
	public void readNotes() {
		notesWrapper.getChildren().clear();
    	for(Note note : TableNotes.read(20)) {
	    	NoteComponent component=new NoteComponent();
	    	component.setNote(note); 
	
	    	notesWrapper.getChildren().addAll(component);
	    	notesWrapper.setCellHeight(314.0); 
	    	notesWrapper.setCellWidth(306.0);
    	}	
	}

    @FXML
    private void onAddNoteAction(ActionEvent event) {

		NoteComponent postIt=new NoteComponent(); 
		Note note=new Note();
		note.setContent("");
		notesWrapper.getChildren().add(postIt); 
		TableNotes.insertNote(note);
		note.setColor(TableNotes.returnColor(note.getId()));
		postIt.setNote(note);
		HBox.setHgrow(postIt, Priority.ALWAYS);

	} 

    @FXML
    private void onAcceptDialog(ActionEvent event) {
    	note.get().setColor(selectedColor.get());
    	TableNotes.updateColor(note.get());
    	dialogColor.setVisible(false);
    	readNotes();
    }

    @FXML
    private void onCancelDialog(ActionEvent event) {
    	dialogColor.setVisible(false);
    }

	public StackPane getView() {
		return this.view;
	}

}

