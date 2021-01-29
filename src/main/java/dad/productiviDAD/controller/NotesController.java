package dad.productiviDAD.controller;

import java.util.Random;

import com.jfoenix.controls.JFXMasonryPane;

import javafx.application.Application;
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
    private FlowPane view=new FlowPane();
	 
    public NotesController() {
		Random random=new Random(); 
		
		for(int i=0;i<=29;i++) {
			Button button=new Button("Nota "+ i);
			button.setPrefSize(200,200);
			
			int red= random.nextInt(255); 
			int green= random.nextInt(255);
			int blue= random.nextInt(255);
			
			button.setStyle("-fx-background-color:rgb("+red+","+green+","+blue+")");
			
			view.getChildren().add(button);
		}
	}
	public FlowPane getView() {
		return this.view;
	}


}