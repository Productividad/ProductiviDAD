package dad.productividad.note;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;

import dad.productividad.dataManager.TableNotes;
import dad.productividad.utils.CSSUtils;
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
    private Button favouriteButton;
    
    @FXML
    private Button checkButton;
    
    @FXML
    private JFXTextArea contentTA;
    
//    private NoteComponentColorPicker colorPicker;
    
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
		
//		colorPicker=new NoteComponentColorPicker();
//		border.setTop(colorPicker);
		
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

	private void styleNote() {
		
		String textColor="white";
//		if(!getNote().isWhite())
//			textColor="black";
		
		Map<String, String> params = new HashMap<>();
		params.put("noteColor", getNote().getColor());
		params.put("textColor", textColor);
		
		this.getStylesheets().setAll(CSSUtils.generateCss("/css/noteComponent.txt", params));
	}

    @FXML
    void OnFavoritePressed(ActionEvent event) {

    }

    @FXML
    void onDeleteNote(ActionEvent event) {
//    	TableNotes.delete(getNote());
    	System.out.println("Borrado");
    	System.out.println(getNote().getId());

    }

    @FXML
    void onOpenOptions(ActionEvent event) {

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
