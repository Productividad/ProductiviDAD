package dad.productiviDAD.controller;

import java.util.List;
import java.util.Random;

import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXTextField;

import dad.productiviDAD.data.TableNotes;
import dad.productiviDAD.model.IncomeExpense;
import dad.productiviDAD.model.Note;
import javafx.application.Application;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

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
    
	private List<Note> noteList;

	
	private JFXMasonryPane view=new JFXMasonryPane();
	 
    public NotesController() {
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
	public JFXMasonryPane getView() {
		return this.view;
	}
	

}