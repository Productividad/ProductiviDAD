package dad.productividad.settings;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import dad.productividad.app.App;
import dad.productividad.app.MainController;
import dad.productividad.balanceManager.CurrencyType;
import dad.productividad.theme.Theme;
import dad.productividad.theme.ThemePicker;
import javafx.beans.binding.Bindings;
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
    private GridPane bottomPane, dialogAccept, dialogReset;

    @FXML
    private VBox themeWrapper;

    @FXML
    private ScrollPane scroll;

    @FXML
    private Button saveButton, resetButton;

    @FXML
    private ComboBox<Locale> localePicker;

    @FXML
    private ComboBox<CurrencyType> currencyPicker;

    private ListProperty<CurrencyType> currencies = new SimpleListProperty<>(FXCollections.observableArrayList(CurrencyType.values()));
    private ListProperty<Locale> languages = new SimpleListProperty<>(FXCollections.observableArrayList(Locale.ENGLISH, new Locale("es"), Locale.FRENCH));

    public static String selectedTheme;
    
    private ThemePicker pickerBW = new ThemePicker();
    private ThemePicker pickerPB = new ThemePicker();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    	selectedTheme=App.preferences.getTheme();
    	
        setThemes();

        setSelectedThemeFromJSON();
    	
        localePicker.setItems(languages);

        scroll.setFitToWidth(true);	 

        currencyPicker.setItems(currencies);

        localePicker.getSelectionModel().select(App.preferences.getLocale());

        currencyPicker.getSelectionModel().select(App.preferences.getCurrency());

        hideDialog();

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

    /**
     * Reset button of bottomPane. Set dialogReset visible
     * @param event
     */
    @FXML
    private void onResetAction(ActionEvent event) {
        dialogReset.setVisible(true);
    } 
    
    /**
     * Accept button of bottomPane. Set dialogAccept visible
     * @param event
     */
    @FXML
    private void onSaveAction(ActionEvent event) {
        dialogAccept.setVisible(true);
    }
    
    /**
     * Accept button on acceptDialog. 
     * Checks if any preferences are updated and change the JSON preferences file and
     * change the center of mainController borderPane to homeController view
     */
    @FXML
    private void onAcceptDialog() {
    	
    	if (!App.preferences.localeProperty().get().equals(localePicker.getValue())) {
            App.preferences.localeProperty().set(localePicker.getValue());
            try {
                App.preferences.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    	
        if(!App.preferences.themeProperty().get().equals(selectedTheme)) {
        	App.preferences.themeProperty().set(selectedTheme);
            try {
                App.preferences.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        if(!App.preferences.currencyProperty().get().equals(currencyPicker.getValue())) {
        	App.preferences.currencyProperty().set(currencyPicker.getValue());
        	try {
        		App.preferences.save();
        	}catch (IOException e) {
                e.printStackTrace();
			}
        }
    	dialogAccept.setVisible(false);
    	MainController.mainController.changeTheme();
    	MainController.mainController.getMenuBarController().onHomeManagerSection();
    }
    
    /**
     * Accept button in reset dialog
     */
    @FXML
    private void onAcceptDialogReset() {
    	//TODO Resetear las cosas de resetear
        dialogReset.setVisible(false);
    }

    /**
     * Cancel button in reset dialog
     */
    @FXML
    private void onCancelDialogReset() {
    	hideDialog();
    }
    
    /**
     * Hide dialogAccept and DialogReset
     */
	public void hideDialog() {
		dialogAccept.setVisible(false);
        dialogReset.setVisible(false);
	}
    
	/**
	 * Set the themes availables on themeWrapper
	 */
	private void setThemes() {
		
		//Black and White
        Theme blackAndWhite = new Theme();
        blackAndWhite.setTitle("Black and white");
        blackAndWhite.setPalette("transparent", "transparent", "transparent", "#FFFFFF", "#E0DBDF", "#4C4C4C");
        blackAndWhite.setPath("/css/Themes/BlackAndWhite.css");
        pickerBW.setTheme(blackAndWhite);
        pickerBW.getStyleClass().addAll("theme-component","black-and-white-theme");
        
        //Princess Bubblegum
        Theme princessBubblegum = new Theme();
        princessBubblegum.setTitle("Princess Bubblegum");
        princessBubblegum.setPalette("transparent", "transparent", "transparent", "#E4E0E4", "#F5D8DA", "#EBB3B4");
        princessBubblegum.setPath("/css/Themes/PrincessBubblegum.css");
        pickerPB.setTheme(princessBubblegum);
        pickerPB.getStyleClass().addAll("theme-component","princess-bubblegum-theme");
        
        themeWrapper.getChildren().addAll(pickerBW,pickerPB);
	}
	
	/**
	 * Set the selected themePicker on themeWrapper from the preferences JSON
	 */
	public void setSelectedThemeFromJSON() {
			
		if(pickerBW.getTheme().getPath().equals(App.preferences.getTheme())) 
			pickerBW.setDisable(true);
		
		if(pickerPB.getTheme().getPath().equals(App.preferences.getTheme()))
			pickerPB.setDisable(true);
		
	}
	
	/**
	 * Set all the themePickers on themeWrapper disable(false)
	 */
	public void setAllThemesDisableFalse() {
		pickerBW.setDisable(false);
		pickerPB.setDisable(false);
	}
	
	/**
	 * Return the view of this controller
	 * @return StackPane
	 */
    public StackPane getView() {
        return view;
    }
}
