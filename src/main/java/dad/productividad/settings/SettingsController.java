package dad.productividad.settings;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;


public class SettingsController implements Initializable {
    @FXML
    private GridPane view;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton resetButton;

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

        Theme theme=new Theme();
        theme.setTitle("Darkest night");
        theme.setPalette("#B3B689", "#93C763", "#4E87BF", "#8CBBAD", "#EC7600", "#7CCADD");
        ThemePicker picker=new ThemePicker();
        picker.setTheme(theme);
        view.add(picker, 0, 1);

        Theme theme1=new Theme();
        theme.setTitle("Darkest night");
        theme.setPalette("#B3B689", "#93C763", "#4E87BF", "#8CBBAD", "#EC7600", "#7CCADD");
        ThemePicker picker1=new ThemePicker();
        picker1.setTheme(theme);
        view.add(picker1, 1, 1);

        Theme theme2=new Theme();
        theme.setTitle("Darkest night");
        theme.setPalette("#B3B689", "#93C763", "#4E87BF", "#8CBBAD", "#EC7600", "#7CCADD");
        ThemePicker picker2=new ThemePicker();
        picker2.setTheme(theme);
        view.add(picker2, 0, 2);

        Theme theme3=new Theme();
        theme.setTitle("Darkest night");
        theme.setPalette("#B3B689", "#93C763", "#4E87BF", "#8CBBAD", "#EC7600", "#7CCADD");
        ThemePicker picker3=new ThemePicker();
        picker3.setTheme(theme);
        view.add(picker3, 1, 2);
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
