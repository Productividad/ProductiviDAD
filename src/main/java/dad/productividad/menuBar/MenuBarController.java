package dad.productividad.menuBar;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import animatefx.animation.FadeIn;
import dad.productividad.app.MainController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class MenuBarController implements Initializable {
	
	@FXML
    private VBox view;

	@FXML
	private ToggleButton homeButton,calendarButton,entryReaderButton,projectManagerButton,ideasButton,
						balanceManagerButton,timePlannerButton,toolsButton;
	private ToggleGroup toggleGroup;

	@FXML
	private Label homeLabel;
	private StringProperty homeTag=new SimpleStringProperty();
	
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
		
		homeLabel.textProperty().bind(homeTag);
		
		toggleGroup = new ToggleGroup();
		homeButton.setToggleGroup(toggleGroup);
		calendarButton.setToggleGroup(toggleGroup);
		entryReaderButton.setToggleGroup(toggleGroup);
		projectManagerButton.setToggleGroup(toggleGroup);
		ideasButton.setToggleGroup(toggleGroup);
		balanceManagerButton.setToggleGroup(toggleGroup);
		timePlannerButton.setToggleGroup(toggleGroup);
		toolsButton.setToggleGroup(toggleGroup);
		
		homeButton.selectedProperty().set(true);		
	}
	
    @FXML
    void onBalanceManagerButton(ActionEvent event) {
		if (MainController.mainController.getView().getCenter() == MainController.mainController.getBalanceManagerController().getView())
			balanceManagerButton.selectedProperty().set(true);
		else {
			new FadeIn(MainController.mainController.getBalanceManagerController().getView()).play();
			MainController.mainController.getView().setCenter(MainController.mainController.getBalanceManagerController().getView());
		}
    }

    @FXML
    void onCalendarButton(ActionEvent event) {

    }

    @FXML
    void onEntryReaderButton(ActionEvent event) {

    }

    @FXML
    void onGithubButton(ActionEvent event) {
		try {
		    Desktop.getDesktop().browse(new URL("https://github.com/dam-dad/ProductiviDAD").toURI());
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (URISyntaxException e) {
		    e.printStackTrace();
		}
    }

    @FXML
    void onHomeButton(ActionEvent event) {
		if (MainController.mainController.getView().getCenter() == MainController.mainController.getHomeController().getView()) 
			homeButton.selectedProperty().set(true);
		else { 
			new FadeIn(MainController.mainController.getHomeController().getView()).play();
			
			MainController.mainController.getView().setCenter(MainController.mainController.getHomeController().getView());
		}
    }

    @FXML
    void onIdeasButton(ActionEvent event) {
		if (MainController.mainController.getView().getCenter() == MainController.mainController.getNotasController().getView())
			ideasButton.selectedProperty().set(true);
		else {
			new FadeIn(MainController.mainController.getNotasController().getView()).play();
			MainController.mainController.getView().setCenter(MainController.mainController.getNotasController().getView());
		}
    }

    @FXML
    void onProjectManagerButton(ActionEvent event) {
		if(MainController.mainController.getView().getCenter()==MainController.mainController.getProjectManagerController().getView())
			projectManagerButton.selectedProperty().set(true);
		else {
			new FadeIn(MainController.mainController.getProjectManagerController().getView()).play();
			MainController.mainController.getView().setCenter(MainController.mainController.getProjectManagerController().getView());
		}
    }

    @FXML
    void onTimePlannerButton(ActionEvent event) {
		if(MainController.mainController.getView().getCenter()==MainController.mainController.getPomodoroController().getView())
			timePlannerButton.selectedProperty().set(true);
		else {
			new FadeIn(MainController.mainController.getPomodoroController().getView()).play();
			MainController.mainController.getView().setCenter(MainController.mainController.getPomodoroController().getView());
		}
    }

    @FXML
    void onToolsButton(ActionEvent event) {
		if(MainController.mainController.getView().getCenter()==MainController.mainController.getSettingsController().getView())
			toolsButton.selectedProperty().set(true);
		else {
			new FadeIn(MainController.mainController.getSettingsController().getView()).play();
			MainController.mainController.getView().setCenter(MainController.mainController.getSettingsController().getView());
		}
    }

	public VBox getView() {
		return view;
	}

	public final StringProperty homeTagProperty() {
		return this.homeTag;
	}
	

	public final String getHomeTag() {
		return this.homeTagProperty().get();
	}
	

	public final void setHomeTag(final String homeTag) {
		this.homeTagProperty().set(homeTag);
	}
	

}
