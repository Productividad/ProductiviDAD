package dad.productividad.settings;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;

import dad.productividad.app.App;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class SettingsController implements Initializable {
    @FXML
    private GridPane view;

    @FXML
    private VBox themeWrapper;
    
    @FXML
    private ScrollPane scroll;
    
    @FXML
    private Button saveButton;

    @FXML
    private Button resetButton;

    @FXML
    private JFXComboBox<Locale> localePicker;

    ListProperty<Locale> languages = new SimpleListProperty<>(FXCollections.observableArrayList(Locale.ENGLISH, new Locale("es"), Locale.FRENCH));

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        localePicker.setItems(languages);
        if(App.preferences.getLocale() == null)
            localePicker.getSelectionModel().select(0);
        else
            localePicker.getSelectionModel().select(App.preferences.getLocale());
        
        scroll.setFitToWidth(true);	 

        Theme theme=new Theme();
        theme.setTitle("Darkest night");
        theme.setPalette("#B3B689", "#93C763", "#4E87BF", "#8CBBAD", "#EC7600", "#7CCADD");
        ThemePicker picker=new ThemePicker();
        picker.setTheme(theme);
        themeWrapper.getChildren().add(picker);
        
        
        Theme theme1=new Theme();
        theme.setTitle("Darkest night");
        theme.setPalette("#B3B689", "#93C763", "#4E87BF", "#8CBBAD", "#EC7600", "#7CCADD");
        ThemePicker picker1=new ThemePicker();
        picker1.setTheme(theme);
        themeWrapper.getChildren().add(picker1);

        Theme theme2=new Theme();
        theme.setTitle("Darkest night");
        theme.setPalette("#B3B689", "#93C763", "#4E87BF", "#8CBBAD", "#EC7600", "#7CCADD");
        ThemePicker picker2=new ThemePicker();
        picker2.setTheme(theme);
        themeWrapper.getChildren().add(picker2);

        Theme theme3=new Theme();
        theme.setTitle("Darkest night");
        theme.setPalette("#B3B689", "#93C763", "#4E87BF", "#8CBBAD", "#EC7600", "#7CCADD");
        ThemePicker picker3=new ThemePicker();
        picker3.setTheme(theme);
        themeWrapper.getChildren().add(picker3); 
        
        Theme theme4=new Theme();
        theme4.setTitle("Darkest night");
        theme4.setPalette("#B3B689", "#93C763", "#4E87BF", "#8CBBAD", "#EC7600", "#7CCADD");
        ThemePicker picker4=new ThemePicker();
        picker4.setTheme(theme4);
        themeWrapper.getChildren().add(picker4);
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
    void onResetAction(ActionEvent event) {
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
    void onSaveAction(ActionEvent event) {
        if(App.preferences.localeProperty().get() != localePicker.getValue()) {
            App.preferences.localeProperty().set(localePicker.getValue());
            try {
                App.preferences.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public GridPane getView() {
        return view;
    }
}
