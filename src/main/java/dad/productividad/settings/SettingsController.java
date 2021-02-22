package dad.productividad.settings;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import dad.productividad.app.App;
import dad.productividad.app.MainController;
import dad.productividad.task.Task;
import dad.productividad.theme.Theme;
import dad.productividad.theme.ThemePicker;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class SettingsController implements Initializable {
	
	@FXML
	private StackPane view;
	
    @FXML
    private GridPane bottomPane, dialogPane;

    @FXML
    private VBox themeWrapper;
    
    @FXML
    private ScrollPane scroll;
    
    @FXML
    private Button saveButton, resetButton;

    @FXML
    private ComboBox<Locale> localePicker; 

    @FXML
    private ComboBox<String> currencyPicker;

    
    private ListProperty<Locale> languages = new SimpleListProperty<>(FXCollections.observableArrayList(Locale.ENGLISH, new Locale("es"), Locale.FRENCH));

    @Override 
    public void initialize(URL location, ResourceBundle resources) {
    	
        localePicker.setItems(languages);
        
        if(App.preferences.getLocale() == null)
            localePicker.getSelectionModel().select(0);
        else
            localePicker.getSelectionModel().select(App.preferences.getLocale());
         
        scroll.setFitToWidth(true);	 

        for(int i=0;i<=15;i++) {
	        Theme theme=new Theme();
	        theme.setTitle("Darkest night");
	        theme.setPalette("#B3B689", "#93C763", "#4E87BF", "#8CBBAD", "#EC7600", "#7CCADD");
	        ThemePicker picker=new ThemePicker();
	        picker.setTheme(theme);
	        themeWrapper.getChildren().add(picker); 
        }   
        
        bottomPane.disableProperty().bind(dialogPane.visibleProperty());
        dialogPane.setVisible(false);
    }

    public SettingsController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SettingsView.fxml"));
        loader.setResources(ResourceBundle.getBundle("i18n/strings"));
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
 
    @FXML 
    private void onResetAction(ActionEvent event) {
        localePicker.getSelectionModel().select(Locale.ENGLISH);
        if(App.preferences.localeProperty().get() != localePicker.getValue()) {
            App.preferences.localeProperty().set(localePicker.getValue());
            try {
                App.preferences.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    } 

    @FXML
    private void onSaveAction(ActionEvent event) {
        if(App.preferences.localeProperty().get() != localePicker.getValue()) {
            App.preferences.localeProperty().set(localePicker.getValue());
            try {
                App.preferences.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        dialogPane.setVisible(true);
    }

    @FXML
    private void onAcceptDialog() {
    	dialogPane.setVisible(false);
    	MainController.mainController.getMenuBarController().onHomeManagerSection(); 
    }
    
	public void hideDialog() {
		dialogPane.setVisible(false);
	}
    
    public StackPane getView() {
        return view;
    }
}
