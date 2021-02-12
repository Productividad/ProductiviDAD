package dad.productiviDAD.home;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

	@FXML
	private AnchorPane view;
	
	public HomeController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HomeView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	public AnchorPane getView() {
		return this.view;
	}
	
}
