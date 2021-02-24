package dad.productividad.theme;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.productividad.app.MainController;
import dad.productividad.settings.SettingsController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ThemePicker extends GridPane implements Initializable {

    @FXML
    private Label themeTitle;

    @FXML
    private Pane colorSquare0,colorSquare1,colorSquare2,colorSquare3,colorSquare4,colorSquare5;

    private StringProperty title=new SimpleStringProperty();
    private ObjectProperty<Theme>theme=new SimpleObjectProperty<>();
    
	public ThemePicker() {
		super();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ThemePickerComponent.fxml"));
			loader.setController(this);
			loader.setRoot(this); 
			loader.load();
		} catch (IOException e) {e.printStackTrace();}
	} 
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		themeTitle.textProperty().bindBidirectional(title);		
		 
		theme.addListener((o,ov,nv)->{
			
			setPalette(theme.get()); 
			title.set(theme.get().getTitle());
			
		});
		
		setOnMouseClicked(evt->onMouseClicked());

	}

	private void onMouseClicked() {
		MainController.mainController.getSettingsController().setAllThemesDisableFalse();
		setDisable(true);
		SettingsController.selectedTheme=getTheme().getPath();
	} 

	private void setPalette(Theme theme) {
		
		colorSquare0.setStyle("-fx-background-color:"+theme.getColor0()+";-fx-background-radius:5px;");
		colorSquare1.setStyle("-fx-background-color:"+theme.getColor1()+";-fx-background-radius:5px;");
		colorSquare2.setStyle("-fx-background-color:"+theme.getColor2()+";-fx-background-radius:5px;");
		colorSquare3.setStyle("-fx-background-color:"+theme.getColor3()+";-fx-background-radius:5px;");
		colorSquare4.setStyle("-fx-background-color:"+theme.getColor4()+";-fx-background-radius:5px;");
		colorSquare5.setStyle("-fx-background-color:"+theme.getColor5()+";-fx-background-radius:5px;");
	}

	public final ObjectProperty<Theme> themeProperty() { 
		return this.theme; 
	}
	

	public final Theme getTheme() {
		return this.themeProperty().get();
	}
	

	public final void setTheme(final Theme theme) {
		this.themeProperty().set(theme);
	}
	


	
	
}
