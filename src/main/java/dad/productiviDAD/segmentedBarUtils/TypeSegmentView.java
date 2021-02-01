package dad.productiviDAD.segmentedBarUtils;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.layout.StackPane;

public class TypeSegmentView extends StackPane {
	
	private Label label;
	
	public TypeSegmentView(final TypeSegment segment) {
		
		label=new Label();
		label.setStyle("-fx-font-weight: bold; -fx-text-fill: white; -fx-font-size: 1.2em;");
        label.setTextOverrun(OverrunStyle.CLIP);
        label.textProperty().bind(segment.textProperty());
        StackPane.setAlignment(label, Pos.CENTER_LEFT);
        
        getChildren().add(label);

		
		switch(segment.getType()) {
		case TODO:
			setStyle("-fx-background-color:orange;");
			break;
		case IN_PROGRESS:
			setStyle("-fx-background-color:steelblue;");
			break;
		case DONE:
			setStyle("-fx-background-color:green;");
			break;
		}
        setPadding(new Insets(5));
		setPrefHeight(40);
		setPrefWidth(40);

		setOnMouseClicked(evt->System.out.println("holo "+segment.getText()));
	}
}
