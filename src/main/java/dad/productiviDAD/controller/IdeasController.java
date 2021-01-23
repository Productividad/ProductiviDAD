package dad.productiviDAD.controller;

import java.util.Random;

import com.jfoenix.controls.JFXMasonryPane;

import javafx.scene.control.Label;

public class IdeasController {

    private JFXMasonryPane view=new JFXMasonryPane();
	 
    public IdeasController() {
		Random random=new Random(); 
		
		for(int i=0;i<=10;i++) {
			Label label=new Label("Nota "+i);
			label.setPrefSize(random.nextInt(180), random.nextInt(180));
			
			int red= random.nextInt(255); 
			int green= random.nextInt(255);
			int blue= random.nextInt(255);
			
			label.setStyle("-fx-background-color:rgb("+red+","+green+","+blue+")");
			
			view.getChildren().add(label);
		}
	}
	public JFXMasonryPane getView() {
		return this.view;
	}

}
