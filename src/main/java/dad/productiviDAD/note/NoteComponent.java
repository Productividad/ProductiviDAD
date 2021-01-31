package dad.productiviDAD.note;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import dad.productiviDAD.utils.CSSUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class NoteComponent extends GridPane	implements Initializable{

	private StringProperty title=new SimpleStringProperty();	
	private StringProperty content=new SimpleStringProperty();
	private ObjectProperty<Note>note=new SimpleObjectProperty<>();
	
	@FXML
    private JFXTextField noteTF;

    @FXML
    private JFXTextArea noteTA;
    
    @FXML
    private Button favButton;

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
			if(nv!=null) {
				title.bindBidirectional(nv.titleProperty());
				content.bindBidirectional(nv.contentProperty());
				
				styleNote();
			}
		});
		
		noteTF.textProperty().bindBidirectional(title);
		noteTA.textProperty().bindBidirectional(content);
		
	}

	private void styleNote() {
		
		String textColor="white";
		if(!getNote().isWhite())
			textColor="black";
		
		Map<String, String> params = new HashMap<>();
		params.put("noteColor", getNote().getColor());
		params.put("textColor", textColor);
		
		this.getStylesheets().setAll(CSSUtils.generateCss("/css/noteComponent.txt", params));
	}

    @FXML
    void onColorSelectorButton(ActionEvent event) {

    }

    @FXML
    void onDeleteNote(ActionEvent event) {

    }

    @FXML
    void onFavButton(ActionEvent event) {

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

	public NoteComponent(StringProperty title, StringProperty content) {
		super();
		this.title = title;
		this.content = content;
	}

	public StringProperty getTitle() {
		return title;
	}

	public void setTitle(StringProperty title) {
		this.title = title;
	}

	public StringProperty getContent() {
		return content;
	}

	public void setContent(StringProperty content) {
		this.content = content;
	}

	public JFXTextField getNoteTF() {
		return noteTF;
	}

	public void setNoteTF(JFXTextField noteTF) {
		this.noteTF = noteTF;
	}

	public JFXTextArea getNoteTA() {
		return noteTA;
	}

	public void setNoteTA(JFXTextArea noteTA) {
		this.noteTA = noteTA;
	}
	
	
}
