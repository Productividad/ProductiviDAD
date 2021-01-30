package dad.productiviDAD.controller;

import dad.productiviDAD.model.Note;
import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;

public class NotesController {

//	
//	private FlowPane view = new FlowPane();
//	
//	Button button1= new Button("1");
//	Button button2= new Button("2");
//	Button button4= new Button("3");
//	Button button3= new Button("4");
//	
//	view.getChildren().addAll(button1,button2,button3,button4);

	//get titulo y fecha de la nota
    private FlowPane view=new FlowPane();
	 
    public NotesController() {

    	NoteComponent postIt1=new NoteComponent();
    	Note note1=new Note("Titulo1","Descripcion1","#DBC2CF",false);
    	postIt1.setNote(note1);
    	
    	NoteComponent postIt2=new NoteComponent();
    	Note note2=new Note("Titulo2","Descripcion2","#708D81",false);
    	postIt2.setNote(note2);
    	
    	NoteComponent postIt3=new NoteComponent();
    	Note note3=new Note("Titulo3","Descripcion3","#001427",true);
    	postIt3.setNote(note3);
    	
    	NoteComponent postIt4=new NoteComponent();
    	Note note4=new Note("Titulo4","Descripcion4","#DDA15E",false);
    	postIt4.setNote(note4);
    	
    	view.setPadding(new Insets(5));
    	view.setHgap(5);
    	view.setVgap(5);
    	view.getChildren().addAll(postIt1,postIt2,postIt3,postIt4);
 
	}
	public FlowPane getView() {
		return this.view; 
	}


}   