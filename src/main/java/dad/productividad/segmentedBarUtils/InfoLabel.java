package dad.productiviDAD.segmentedBarUtils;

import javafx.geometry.Insets;
import javafx.scene.control.Label;

public class InfoLabel extends Label {

	public InfoLabel(String text) {
		super(text);
		setPadding(new Insets(4));
		setStyle("-fx-font-weight: bold; -fx-font-size: 1.2em;");
	}
	
}
