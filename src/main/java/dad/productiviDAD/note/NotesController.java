package dad.productiviDAD.note;

import java.util.List;

import dad.productiviDAD.dataManager.TableNotes;
import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;

public class NotesController {

//	Button button1= new Button("1");
//	Button button2= new Button("2");
//	Button button4= new Button("3");
//	Button button3= new Button("4");
//	
//	view.getChildren().addAll(button1,button2,button3,button4);

	    
	private List<Note> noteList;

	
//	private JFXMasonryPane view=new JFXMasonryPane();
	private FlowPane view = new FlowPane();
//
    public NotesController() {
    	noteList = TableNotes.read(20);
    	for(Note i : noteList) {
    	NoteComponent postIt1=new NoteComponent();
    	Note note1=new Note(i.getTitle(),i.getContent(),"#708D81",false);
    	postIt1.setNote(note1);
//    	
//    	postIt1.getNoteTF().focusedProperty().addListener((o,ov,nv)->{
//    		
//    		if (!nv) { //cuando pierde el foco 
//				i.setTitle(note1.getTitle());
//				TableNotes.update(i);
//			}
//    		
//    	});
//    	
//    	postIt1.getNoteTA().focusedProperty().addListener((o,ov,nv)->{
//    		
//    		if (!nv) { //cuando pierde el foco 
//				i.setContent(note1.getContent());
//				TableNotes.update(i);
//			}
//    		
//    	});
    	
    /*	NoteComponent postIt2=new NoteComponent();
    	Note note2=new Note("Titulo2","Descripcion2","#708D81",false);
    	postIt2.setNote(note2);
    	
    	NoteComponent postIt3=new NoteComponent();
    	Note note3=new Note("Titulo3","Descripcion3","#001427",true);
    	postIt3.setNote(note3);
    	
    	NoteComponent postIt4=new NoteComponent();
    	Note note4=new Note("Titulo4","Descripcion4","#DDA15E",false);
    	postIt4.setNote(note4);*/
//
//    	view.getChildren().addAll(postIt1);
//    	}
//    	view.setHgap(5);
//    	view.setVgap(5);
//    	view.setPadding(new Insets(5));
 
	}
}
/*	public FlowPane getView() {
		return this.view; 
		Random random=new Random(); 
		noteList = TableNotes.read(20);
		for (Note i : noteList) {
			JFXTextField textContent=new JFXTextField(i.getContent());
			textContent.setPrefSize(400,400);
			
			int red= random.nextInt(255); 
			int green= random.nextInt(255);
			int blue= random.nextInt(255);
			
			textContent.setStyle("-fx-background-color:rgb("+red+","+green+","+blue+")");
			
			textContent.focusedProperty().addListener((obs, oldVal,newVal)->{  //https://stackoverflow.com/questions/16549296/how-perform-task-on-javafx-textfield-at-onfocus-and-outfocus
				if (!newVal) { //cuando pierde el foco 
					i.setContent(textContent.getText());
					TableNotes.update(i);
				}
			});
			
			view.getChildren().add(textContent);
		}
	}
*/
	public FlowPane getView() {
		return this.view;
	}
	

}   