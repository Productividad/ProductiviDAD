package dad.productiviDAD.task;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class RightBarController implements Initializable{

    @FXML
    private AnchorPane view;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;
	
	private HamburgerNextArrowBasicTransition transiction;

    public RightBarController() {
    	try {
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/RightBarView.fxml"));
		loader.setController(this);
		loader.load();
		} catch (IOException e) {e.printStackTrace();} 
	}
     
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		transiction=new HamburgerNextArrowBasicTransition(hamburger);
		TaskManagerController taskManagerController=new TaskManagerController();
 
		
		drawer.setSidePane(taskManagerController.getView());  
		transiction.setRate(-1); 
		
		drawer.open();
		
		hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
			transiction.setRate(transiction.getRate() * -1);
			transiction.play(); 
  
			if (drawer.isOpened()) 
				drawer.close();
			else 
				drawer.open();
		});
	}

	public AnchorPane getView() {
		return this.view;
	}
	
}
