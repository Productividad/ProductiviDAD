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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class NoteComponent extends BorderPane implements Initializable{

	private StringProperty content=new SimpleStringProperty();
	private ObjectProperty<Note>note=new SimpleObjectProperty<>();
	

    @FXML
    private Button favouriteButton;

    @FXML
    private Button checkButton;
    
    @FXML
    private JFXTextArea contentTA;

	public NoteComponent() {
		super();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NoteComponentView.fxml"));
			loader.setController(this);
			loader.setRoot(this); 
			loader.load();
		} catch (IOException e) {e.printStackTrace();}
	}
	
//	public NoteComponent(StringProperty title, StringProperty content) {
//		super();
//		this.title = title;
//		this.content = content;
//	}
//	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		note.addListener((o,ov,nv)->{
			if(nv!=null) {
//				title.bindBidirectional(nv.titleProperty());
				content.bindBidirectional(nv.contentProperty());
				
//				styleNote();
			}
		});
		
		contentTA.textProperty().bindBidirectional(content);
		
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

	public final ObjectProperty<Note> noteProperty() {
		return this.note;
	}
	

	public final Note getNote() {
		return this.noteProperty().get();
	}
	

	public final void setNote(final Note note) {
		this.noteProperty().set(note);
	}
	
    @FXML
    void OnFavoritePressed(ActionEvent event) {

    }

    @FXML
    void onDeleteNote(ActionEvent event) {

    }

    @FXML
    void onOpenOptions(ActionEvent event) {

    }

	public final StringProperty contentProperty() {
		return this.content;
	}
	

	public final String getContent() {
		return this.contentProperty().get();
	}
	

	public final void setContent(final String content) {
		this.contentProperty().set(content);
	}
	
	
	
}
