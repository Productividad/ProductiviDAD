package dad.productiviDAD.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;

import animatefx.animation.FadeIn;
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
    private ListView<String> listView;  
    
    
 //Controllers
	private RightBarController rightBarController;

	private HomeController homeController;
	private IdeasController ideasController;
    
	public MainController(){
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {e.printStackTrace();} 
	}
	 
	@Override 
	public void initialize(URL location, ResourceBundle resources) {
		
		rightBarController=new RightBarController(); 
		
		homeController=new HomeController();
		ideasController=new IdeasController();
			
		view.setCenter(homeController.getView());
		view.setRight(rightBarController.getView());
	}

	public BorderPane getView() {
		return this.view;
	}
	public GridPane getTopBar() {
		return this.topBar;
	} 

	
 //TopBar
    @FXML
    private void onCloseWindow(ActionEvent event) {
        Platform.exit();
    }
    
    @FXML
    private void onMaximizeButton(ActionEvent event) {
    	Stage stage=(Stage)view.getScene().getWindow();
		stage.setMaximized(!stage.isMaximized());
    }
    
    @FXML
    private void onMinimizeWindow(ActionEvent event) {
    	Stage stage=(Stage)view.getScene().getWindow();
    	stage.setIconified(true);
    }
	
 //LeftMenuBar
    
    
    @FXML
    private void onHomeButton(ActionEvent event) {
    	new FadeIn(homeController.getView()).play();;
    	view.setCenter(homeController.getView());
    	resetRightBar();
    }
    
    @FXML 
    private void onCalendarButton(ActionEvent event) {

    }
    
    @FXML
    private void onEntryReaderButton(ActionEvent event) {

    
    }
    @FXML
    private void onProyectManagerButton(ActionEvent event) {
    
    }
    @FXML 
    private void onIdeasButton(ActionEvent event) {
    	new FadeIn(ideasController.getView()).play();
    	view.setCenter(ideasController.getView());
    	resetRightBar();
    }
    
    @FXML
    private void onBalanceManagerButton(ActionEvent event) {
    
    }
    @FXML
    private void onTimePlannerButton(ActionEvent event) {
    	
    }

    @FXML
    private void onToolsButton(ActionEvent event) {
    
    }
    @FXML
    private void onGithubButton(ActionEvent event) {
    	
    }
    
    /**
     * Resets right side of the view so the
     * JFXDrawer doesn't get unreachable under the 
     * new center node
     */
    private void resetRightBar() { 
		view.setRight(null);
		view.setRight(rightBarController.getView());
    }
}
