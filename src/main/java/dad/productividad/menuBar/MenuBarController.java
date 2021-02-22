package dad.productividad.menuBar;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import animatefx.animation.FadeIn;
import dad.productividad.app.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MenuBarController implements Initializable {
	
	@FXML
    private VBox view;

    @FXML
    private GridPane homeManagerSection,calendarManagerSection,entryManagerSection,projectManagerSection,
    				 notesManagerSection,balanceManagerSection,pomodoroManagerSection,settingsManagerSection,
    				 githubSection;

    @FXML
    private Label homeTag, calendarTag, entryReaderTag, projectManagerTag, ideasTag,
    			  balanceManagerTag, timePlannerTag, toolsTag, githubTag;
    @FXML
    private Label homeShortcut,calendarShortCut,entryReaderShortCut,projectManagerShortcut,
    			  ideasShortcut,balanceManagerShortcut, timePlannerShortcut,toolsShortcut;

	public MenuBarController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LeftBarView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		} 	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		homeManagerSection.setDisable(true);  
		
		homeManagerSection.setOnMouseClicked(event->onHomeManagerSection());
		calendarManagerSection.setOnMouseClicked(event->onCalendarManagerSection());
		entryManagerSection.setOnMouseClicked(event->onEntryManagerSection());
		projectManagerSection.setOnMouseClicked(event->onProjectManagerSection());
		notesManagerSection.setOnMouseClicked(event->onNotesManagerSection());
		balanceManagerSection.setOnMouseClicked(event->onBalanceManagerSection());
		pomodoroManagerSection.setOnMouseClicked(event->onPomodoroManagerSection());
		settingsManagerSection.setOnMouseClicked(event->onSettingsManagerSection());
		githubSection.setOnMouseClicked(event->onGithubSection());
	 
	} 
	
	public void onHomeManagerSection() {
		if (MainController.mainController.getView().getCenter() != MainController.mainController.getHomeController().getView()) {
			
			setSectionsDisableFalse();
			homeManagerSection.setDisable(true);

			MainController.mainController.getHomeController().hideDialog();
			
			new FadeIn(MainController.mainController.getHomeController().getView()).play();
			MainController.mainController.getView().setCenter(MainController.mainController.getHomeController().getView());
		}
    } 
	
	public void onCalendarManagerSection() {

    }
    
	public void onEntryManagerSection() {

    } 
    
	public void onProjectManagerSection() {
		if(MainController.mainController.getView().getCenter() !=MainController.mainController.getProjectManagerController().getView()) {
			
			setSectionsDisableFalse();
			projectManagerSection.setDisable(true);
			
			new FadeIn(MainController.mainController.getProjectManagerController().getView()).play();
			MainController.mainController.getView().setCenter(MainController.mainController.getProjectManagerController().getView());
		}
    }
    
	public void onNotesManagerSection() {
		if (MainController.mainController.getView().getCenter() != MainController.mainController.getNotasController().getView()) {
			
			setSectionsDisableFalse();
			notesManagerSection.setDisable(true);	
			
			new FadeIn(MainController.mainController.getNotasController().getView()).play();
			MainController.mainController.getView().setCenter(MainController.mainController.getNotasController().getView());
		}
    }
    
	public void onBalanceManagerSection() {
		if (MainController.mainController.getView().getCenter() != MainController.mainController.getBalanceManagerController().getView()) {
			
			setSectionsDisableFalse();
			balanceManagerSection.setDisable(true);
			
			new FadeIn(MainController.mainController.getBalanceManagerController().getView()).play();
			MainController.mainController.getView().setCenter(MainController.mainController.getBalanceManagerController().getView());
		}
    }

	public void onPomodoroManagerSection() {
		if(MainController.mainController.getView().getCenter() !=MainController.mainController.getPomodoroController().getView()) {
			
			setSectionsDisableFalse();
			pomodoroManagerSection.setDisable(true);

			new FadeIn(MainController.mainController.getPomodoroController().getView()).play();
			MainController.mainController.getView().setCenter(MainController.mainController.getPomodoroController().getView());
		}
    }

	public void onSettingsManagerSection() {
		if(MainController.mainController.getView().getCenter() !=MainController.mainController.getSettingsController().getView()) {
			
			setSectionsDisableFalse();
			settingsManagerSection.setDisable(true);
			
			MainController.mainController.getSettingsController().hideDialog();

			new FadeIn(MainController.mainController.getSettingsController().getView()).play();
			MainController.mainController.getView().setCenter(MainController.mainController.getSettingsController().getView());
		}
    }
  
    private void onGithubSection() {
		try {
		    Desktop.getDesktop().browse(new URL("https://github.com/dam-dad/ProductiviDAD").toURI());
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (URISyntaxException e) {
		    e.printStackTrace();
		}
    }
  
    
    public void showTagShortcut() {
		homeTag.textProperty().set("Inicio");
		calendarTag.textProperty().set("Calendario");
		entryReaderTag.textProperty().set("Entradas");
		projectManagerTag.textProperty().set("Proyectos");
		ideasTag.textProperty().set("Notas");
		balanceManagerTag.textProperty().set("Balance");
		timePlannerTag.textProperty().set("Pomodoro");
		toolsTag.textProperty().set("Opciones");
		githubTag.textProperty().set("Github");
    	
    	homeShortcut.textProperty().set("Alt+1");
    	calendarShortCut.textProperty().set("Alt+2");
    	entryReaderShortCut.textProperty().set("Alt+3");
    	projectManagerShortcut.textProperty().set("Alt+4");
    	ideasShortcut.textProperty().set("Alt+5");
    	balanceManagerShortcut.textProperty().set("Alt+6");
    	timePlannerShortcut.textProperty().set("Alt+7");
    	toolsShortcut.textProperty().set("Alt+8");
    }
    public void hideTagShortcut() {
		homeTag.textProperty().set("");
		calendarTag.textProperty().set("");
		entryReaderTag.textProperty().set("");
		projectManagerTag.textProperty().set("");
		ideasTag.textProperty().set("");
		balanceManagerTag.textProperty().set("");
		timePlannerTag.textProperty().set("");
		toolsTag.textProperty().set("");
		githubTag.textProperty().set("");
    	
		homeShortcut.textProperty().set(""); 
		calendarShortCut.textProperty().set(""); 
		entryReaderShortCut.textProperty().set("");
		projectManagerShortcut.textProperty().set(""); 
		ideasShortcut.textProperty().set("");
		balanceManagerShortcut.textProperty().set("");
		timePlannerShortcut.textProperty().set("");
		toolsShortcut.textProperty().set("");
    }
     
	private void setSectionsDisableFalse() {
		
		homeManagerSection.setDisable(false);  
		calendarManagerSection.setDisable(false);  
		entryManagerSection.setDisable(false);  
		projectManagerSection.setDisable(false);  
		projectManagerSection.setDisable(false);  
		notesManagerSection.setDisable(false);   
		balanceManagerSection.setDisable(false);    
		pomodoroManagerSection.setDisable(false);  
		settingsManagerSection.setDisable(false);  
	}
    
	public VBox getView() { 
		return view;
	}
}
