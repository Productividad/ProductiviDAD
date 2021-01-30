package dad.productiviDAD.controller;

import dad.productiviDAD.model.Note;
import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;

public class NotesController {

//	Button button1= new Button("1");
//	Button button2= new Button("2");
//	Button button4= new Button("3");
//	Button button3= new Button("4");
//	
//	view.getChildren().addAll(button1,button2,button3,button4);

	//get titulo y fecha de la nota
    
//	private List<Note> noteList;

	
//	private JFXMasonryPane view=new JFXMasonryPane();
	private FlowPane view = new FlowPane();

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
    	
    	view.setHgap(5);
    	view.setVgap(5);
    	view.setPadding(new Insets(5));

    	view.getChildren().addAll(postIt1,postIt2,postIt3,postIt4);
 
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