package dad.productividad.menuBar;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import animatefx.animation.FadeIn;
import dad.productividad.app.App;
import dad.productividad.app.MainController;
import dad.productividad.settings.SettingsController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * MenuBar view controller
 */
public class MenuBarController implements Initializable {
    /**
     * MenuBar view
     */
    @FXML
    private VBox view;
    /**
     * View sections
     */
    @FXML
    private GridPane homeManagerSection, projectManagerSection,
            notesManagerSection, balanceManagerSection, pomodoroManagerSection, settingsManagerSection,
            githubSection;
    /**
     * View labels
     */
    @FXML
    private Label homeTag, projectManagerTag, ideasTag,
            balanceManagerTag, pomodoroTag, toolsTag, githubTag;
    /**
     * View shortcuts
     */
    @FXML
    private Label homeShortcut, projectManagerShortcut,
            ideasShortcut, balanceManagerShortcut, pomodoroShortcut, toolsShortcut;

    /**
     * Menu bar constructor
     */
    public MenuBarController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LeftBarView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialization of the view
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        homeManagerSection.setDisable(true);

        homeManagerSection.setOnMouseClicked(event -> onHomeManagerSection());
        projectManagerSection.setOnMouseClicked(event -> onProjectManagerSection());
        notesManagerSection.setOnMouseClicked(event -> onNotesManagerSection());
        balanceManagerSection.setOnMouseClicked(event -> onBalanceManagerSection());
        pomodoroManagerSection.setOnMouseClicked(event -> onPomodoroManagerSection());
        settingsManagerSection.setOnMouseClicked(event -> onSettingsManagerSection());
        githubSection.setOnMouseClicked(event -> onGithubSection());
  
    }
 
    /**     
     * Disables the home section button when it's the current section
     */
    public void onHomeManagerSection() {
        if (MainController.mainController.getView().getCenter() != MainController.mainController.getHomeController().getView()) {

            setSectionsDisableFalse();
            homeManagerSection.setDisable(true);

            MainController.mainController.getHomeController().hideDialog();

            new FadeIn(MainController.mainController.getHomeController().getView()).play();
            MainController.mainController.getView().setCenter(MainController.mainController.getHomeController().getView());
        }
    }
 
    public void onTimePlannerManagerSection() {

    }

    /**
     * Disables the project section button when it's the current section
     */
    public void onProjectManagerSection() {
        if (MainController.mainController.getView().getCenter() != MainController.mainController.getProjectManagerController().getView()) {

            setSectionsDisableFalse();
            projectManagerSection.setDisable(true);

            MainController.mainController.getProjectManagerController().hideDialogs();

            new FadeIn(MainController.mainController.getProjectManagerController().getView()).play();
            MainController.mainController.getView().setCenter(MainController.mainController.getProjectManagerController().getView());
        }
    }

    /**
     * Disables the notes section button when it's the current section
     */
    public void onNotesManagerSection() {
        if (MainController.mainController.getView().getCenter() != MainController.mainController.getNotesController().getView()) {

            setSectionsDisableFalse();
            notesManagerSection.setDisable(true);

            new FadeIn(MainController.mainController.getNotesController().getView()).play();
            MainController.mainController.getView().setCenter(MainController.mainController.getNotesController().getView());
        }
    }

    /**
     * Disables the balance section button when it's the current section
     */
    public void onBalanceManagerSection() {
        if (MainController.mainController.getView().getCenter() != MainController.mainController.getBalanceManagerController().getView()) {

            setSectionsDisableFalse();
            balanceManagerSection.setDisable(true);

            new FadeIn(MainController.mainController.getBalanceManagerController().getView()).play();
            MainController.mainController.getView().setCenter(MainController.mainController.getBalanceManagerController().getView());
        }
    }

    /**
     * Disables the pomodoro section button when it's the current section
     */
    public void onPomodoroManagerSection() {
        if (MainController.mainController.getView().getCenter() != MainController.mainController.getPomodoroController().getView()) {

            setSectionsDisableFalse();
            pomodoroManagerSection.setDisable(true);

            new FadeIn(MainController.mainController.getPomodoroController().getView()).play();
            MainController.mainController.getView().setCenter(MainController.mainController.getPomodoroController().getView());
        }
    }

    /**
     * Disables the settings section button when it's the current section
     */
    public void onSettingsManagerSection() {
        if (MainController.mainController.getView().getCenter() != MainController.mainController.getSettingsController().getView()) {

            setSectionsDisableFalse();
            settingsManagerSection.setDisable(true);

            MainController.mainController.getSettingsController().hideDialog();
            SettingsController.selectedTheme = App.preferences.getTheme();
            MainController.mainController.getSettingsController().setAllThemesDisableFalse();
            MainController.mainController.getSettingsController().setSelectedThemeFromJSON();

            new FadeIn(MainController.mainController.getSettingsController().getView()).play();
            MainController.mainController.getView().setCenter(MainController.mainController.getSettingsController().getView());
        }
    }

    /**
     * Disables the github section button when it's the current section
     */
    private void onGithubSection() {
        try {
            Desktop.getDesktop().browse(new URL("https://github.com/dam-dad/ProductiviDAD/blob/main/USERMANUAL.md").toURI());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the tag shortcut for accessibility purposes
     */
    public void showTagShortcut() {
        ResourceBundle rb = ResourceBundle.getBundle("i18n/strings");
        homeTag.textProperty().set(rb.getString("home"));
        projectManagerTag.textProperty().set(rb.getString("project"));
        ideasTag.textProperty().set(rb.getString("notes"));
        balanceManagerTag.textProperty().set(rb.getString("balance"));
        pomodoroTag.textProperty().set("Pomodoro");
        toolsTag.textProperty().set(rb.getString("settings"));
        githubTag.textProperty().set("Github");

        homeShortcut.textProperty().set("Alt+1");
        projectManagerShortcut.textProperty().set("Alt+2");
        ideasShortcut.textProperty().set("Alt+3");
        balanceManagerShortcut.textProperty().set("Alt+4");
        pomodoroShortcut.textProperty().set("Alt+5");
        toolsShortcut.textProperty().set("Alt+6");
    } 

    /**
     * Hides the tag shortcut
     */
    public void hideTagShortcut() {
        homeTag.textProperty().set("");
        projectManagerTag.textProperty().set("");
        ideasTag.textProperty().set("");
        balanceManagerTag.textProperty().set("");
        pomodoroTag.textProperty().set("");
        toolsTag.textProperty().set("");
        githubTag.textProperty().set("");

        homeShortcut.textProperty().set("");
        projectManagerShortcut.textProperty().set("");
        ideasShortcut.textProperty().set("");
        balanceManagerShortcut.textProperty().set("");
        pomodoroShortcut.textProperty().set("");
        toolsShortcut.textProperty().set("");
    }

    /**
     * Sets disable from sections to false.
     */
    private void setSectionsDisableFalse() {

        homeManagerSection.setDisable(false);
        projectManagerSection.setDisable(false);
        projectManagerSection.setDisable(false);
        notesManagerSection.setDisable(false);
        balanceManagerSection.setDisable(false);
        pomodoroManagerSection.setDisable(false);
        settingsManagerSection.setDisable(false);
    }

    /**
     * @return the view
     */
    public VBox getView() {
        return view;
    }
}
