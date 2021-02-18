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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PomodoroEditorDialog extends Dialog<PomodoroSetup> implements Initializable {


	 @FXML
	    private BorderPane view;

	    @FXML
	    private GridPane pomodoroTopBar;

	    @FXML
	    private Button closeWindowButton;

	    @FXML
	    private Label pomoEditorTitle;

	    @FXML
	    private JFXTextField titleTF;

	    @FXML
	    private JFXCheckBox whiteText;

	    @FXML
	    private JFXColorPicker colorPicker;

	    @FXML
	    private JFXComboBox<Integer> pomodoroBox;

	    @FXML
	    private Label shortLabel;

	    @FXML
	    private JFXComboBox<Integer> shortBreakBox;

	    @FXML
	    private Label longLabel;

	    @FXML
	    private JFXComboBox<Integer> longBreakBox;

	    @FXML
	    private Label pomoTimes;

	    @FXML
	    private Slider pomogLengthSlider;
    
    private Button saveButton;
    
    private Stage stage;
    
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
		//Filling comboBox
		ObservableList<Integer> minutesSecondsList = FXCollections.observableArrayList();
		for (int i = 1; i <= 59; i++) {
			minutesSecondsList.add(i);
		}

		pomodoroBox.setItems(minutesSecondsList);
		pomodoroBox.setValue(0);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		getDialogPane().getStyleClass().add("customDialog");
		getDialogPane().setContent(view);
		getDialogPane().getButtonTypes().add(new ButtonType("Aceptar",ButtonData.OK_DONE));
		saveButton = (Button) getDialogPane().lookupButton(getDialogPane().getButtonTypes().get(0));
		setResultConverter(d -> onAccept(d));
	}

	@FXML
	void onCloseWindowAction(ActionEvent event) {
		stage=(Stage)view.getScene().getWindow();
    	stage.close();
	}

	
	private PomodoroSetup onAccept(ButtonType buttonType) {
		if (buttonType.getButtonData() == ButtonData.OK_DONE) {
			PomodoroSetup pomodoroSetup = new PomodoroSetup(pomodoroBox.getSelectionModel().getSelectedItem(), 3,2);
//			shortBreakBox.getSelectionModel().getSelectedItem(), longBreakBox.getSelectionModel().getSelectedItem()
			return pomodoroSetup;
		}
		
		return null;
	}
	
	public BorderPane getView() {
		return view;
	}

}
