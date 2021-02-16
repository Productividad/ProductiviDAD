package dad.productividad.note;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXMasonryPane;

import dad.productividad.dataManager.TableNotes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class NotesController implements Initializable{
	    


    @FXML
    private ScrollPane view;

    @FXML
    private JFXMasonryPane taskWrapper;

	private List<Note> noteList;
	 
	public NotesController() {  
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NotesView.fxml"));
			loader.setController(this);
			loader.load(); 
		} catch (IOException e) {   
			e.printStackTrace();   
		}  
	}   
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

		view.setFitToWidth(true);
		noteList = TableNotes.read(20);
    	for(Note i : noteList) {
    	NoteComponent postIt1=new NoteComponent();
    	Note note1=new Note(i.getContent(),"#708D81");
    	postIt1.setNote(note1);
	 
    	postIt1.getContentTA().focusedProperty().addListener((o,ov,nv)->{
    		
    		if (!nv) {
    			//cuando pierde el foco 
    			i.setContent(note1.getContent());
    			//postIt1.setNote(i);
    			TableNotes.update(i);
			}
    		    });
    	taskWrapper.getChildren().addAll(postIt1);
    	taskWrapper.setCellHeight(314.0);
    	taskWrapper.setCellWidth(306.0);

    	}
 
			
			
	} 


	public ScrollPane getView() {
		return this.view;
	}
	

}   