package dad.productividad.pomodoro;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import dad.productividad.app.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Pomodoro dialog class
 */
public class PomodoroEditorDialog extends Dialog<PomodoroSetup> implements Initializable {
    /**
     * Pomodoro editor view
     */
    @FXML
    private BorderPane view;
    /**
     * Editor top bar
     */
    @FXML
    private GridPane pomodoroTopBar;
    /**
     * Close dialog button
     */
    @FXML
    private Button closeWindowButton;
    /**
     * Editor title
     */
    @FXML
    private Label pomoEditorTitle;
    /**
     * Title textfield
     */
    @FXML
    private JFXTextField titleTF;
    /**
     * White checkbox
     */
    @FXML
    private JFXCheckBox whiteText;
    /**
     * Color picker
     */
    @FXML
    private JFXColorPicker colorPicker;
    /**
     * Pomodoro Box combobox
     */
    @FXML
    private JFXComboBox<Integer> pomodoroBox;
    /**
     * Short label
     */
    @FXML
    private Label shortLabel;
    /**
     * short BreakBox
     */
    @FXML
    private JFXComboBox<Integer> shortBreakBox;
    /**
     * Long label
     */
    @FXML
    private Label longLabel;
    /**
     * Long break combobox
     */
    @FXML
    private JFXComboBox<Integer> longBreakBox;
    /**
     * Pomodoro times
     */
    @FXML
    private Label pomoTimes;
    /**
     * Slider
     */
    @FXML
    private Slider pomogLengthSlider;
    /**
     * Save button
     */
    private Button saveButton;
    /**
     * Stage
     */
    private Stage stage;

    /**
     * Dialog constructor
     */
    public PomodoroEditorDialog() {
        initStyle(StageStyle.UNDECORATED);
        initModality(Modality.WINDOW_MODAL);
        initOwner(App.primaryStage);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PomodoroEditorDialogView.fxml"));
            loader.setResources(ResourceBundle.getBundle("i18n/strings"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Filling comboBox
         */
        ObservableList<Integer> minutesSecondsList = FXCollections.observableArrayList();
        for (int i = 1; i <= 60; i++) {
            minutesSecondsList.add(i);

        }

        pomodoroBox.setItems(minutesSecondsList);
        pomodoroBox.setValue(0);
        shortBreakBox.getItems().addAll(5, 10, 15);
        longBreakBox.getItems().addAll(20, 25);
    }

    /**
     * Dialog intialization
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        getDialogPane().getStyleClass().add("customDialog");
        getDialogPane().setContent(view);
        getDialogPane().getButtonTypes().add(new ButtonType("Aceptar", ButtonData.OK_DONE));
        saveButton = (Button) getDialogPane().lookupButton(getDialogPane().getButtonTypes().get(0));
        setResultConverter(d -> onAccept(d));

    }

    /**
     * Close action
     *
     * @param event
     */
    @FXML
    void onCloseWindowAction(ActionEvent event) {
        stage = (Stage) view.getScene().getWindow();
        stage.close();
    }

    /**
     * Instance of PomodoroSetup with comboBox values
     *
     * @param buttonType
     * @return
     */
    private PomodoroSetup onAccept(ButtonType buttonType) {
        if (buttonType.getButtonData() == ButtonData.OK_DONE) {
            PomodoroSetup pomodoroSetup = new PomodoroSetup(pomodoroBox.getSelectionModel().getSelectedItem(),
                    shortBreakBox.getSelectionModel().getSelectedItem(),
                    longBreakBox.getSelectionModel().getSelectedItem(), (int) pomogLengthSlider.getValue());
            return pomodoroSetup;
        }

        return null;
    }

    /**
     * Drag on length
     */
    @FXML
    void onPomoLengthDrag(MouseEvent event) {

    }

    /**
     * @return The pomodoro editor dialog view
     */
    public BorderPane getView() {
        return view;
    }

}
