package dad.productividad.note;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class NoteComponentColorPicker extends HBox implements Initializable{

    @FXML
    private VBox yellowSection;

    @FXML
    private VBox greenSection;

    @FXML
    private VBox pinkSection;

    @FXML
    private VBox purpleSection;

    @FXML
    private VBox blueSection;

    @FXML
    private VBox greySection;

    @FXML
    private VBox blackSection;

	public NoteComponentColorPicker() {
		super(); 
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NoteComponentColorPicker.fxml"));
			loader.setController(this);
			loader.setRoot(this); 
			loader.load();
		} catch (IOException e) {e.printStackTrace();}
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}
