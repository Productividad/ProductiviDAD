package dad.productiviDAD.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
  
public class MainController implements Initializable  { 
    
	//View   
	 
    @FXML  
    private BorderPane view;   
    
    @FXML
    private AnchorPane centerPane;
    
    @FXML
    private GridPane topBar;
     
    @FXML 
    private VBox center; 
    
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;

	//Transición
	private HamburgerNextArrowBasicTransition transiction;
     
    @FXML 
    private ListView<String> listView;  
    
	public MainController(){
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/View.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	 
	@Override 
	public void initialize(URL location, ResourceBundle resources) {
		 
		TaskManagerController taskManager=new TaskManagerController();
 
		//Desplegable
		transiction=new HamburgerNextArrowBasicTransition(hamburger);
		
		drawer.setSidePane(taskManager.getView()); 
		drawer.open(); 
		transiction.setRate(-1); 
		
		hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
			transiction.setRate(transiction.getRate() * -1);
			transiction.play(); 
 
			if (drawer.isOpened()) 
				drawer.close();
			else 
				drawer.open();
		});
	}



	
	public BorderPane getView() {
		return this.view;
	}
	public GridPane getTopBar() {
		return this.topBar;
	}
	public JFXDrawer getDrawer() {
		return this.drawer;
	}
	
	//TopBar
    @FXML
    void onCloseWindow(ActionEvent event) {
        Platform.exit();
    }
    
    @FXML
    void onMaximizeButton(ActionEvent event) {
    	Stage stage=(Stage)view.getScene().getWindow();
		stage.setMaximized(!stage.isMaximized());
    }
    
    @FXML
    void onMinimizeWindow(ActionEvent event) {
    	Stage stage=(Stage)view.getScene().getWindow();
    	stage.setIconified(true);
    }
	
    //SideBar
    @FXML 
    void onCalendarAction(ActionEvent event) {
    }
    
    @FXML
    void onEntryReaderAction(ActionEvent event) {
    
    }
    
    @FXML
    void onExpenseHistory(ActionEvent event) {
    
    }
    
    @FXML
    void onHomeAction(ActionEvent event) {
    
    }
    
    @FXML
    void onIdeasAction(ActionEvent event) {
    
    }
    
    @FXML
    void onProyectManagerAction(ActionEvent event) {
    
    }
    
    @FXML
    void onTaskHistoryAction(ActionEvent event) {
    
    }
    
    @FXML
    void onToolsAction(ActionEvent event) {
    
    }
}
