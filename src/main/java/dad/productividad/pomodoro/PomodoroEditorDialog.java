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
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PomodoroEditorDialog extends Dialog<Pomodoro> implements Initializable {

	@FXML
	private BorderPane view;

	@FXML
	private JFXTextField titleTF;

	@FXML
	private JFXCheckBox whiteText;

	@FXML
	private JFXColorPicker colorPicker;

	@FXML
	private JFXComboBox<Integer> pomodoroBox;

	@FXML
	private JFXComboBox<Integer> shortBreakBox;

	@FXML
	private JFXComboBox<Integer> longBreakBox;

	@FXML
	private GridPane pomodoroTopBar;

	@FXML
	private Label titleTopBar;

	public PomodoroEditorDialog() {
		initStyle(StageStyle.UNDECORATED);
		initModality(Modality.WINDOW_MODAL);
		initOwner(App.primaryStage);
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PomodoroEditorDialogView.fxml"));
			// loader.setResources(ResourceBundle.getBundle("i18n/pomodoro"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Filling comboBox
		ObservableList<Integer> minutesSecondsList = FXCollections.observableArrayList();
		for (int i = 0; i <= 60; i++) {
			minutesSecondsList.add(i);
		}

		pomodoroBox.setItems(minutesSecondsList);
		pomodoroBox.setValue(0);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		getDialogPane().getStyleClass().add("customDialog");
		getDialogPane().setContent(view);

		
	}

	@FXML
	void onCloseWindow(ActionEvent event) {
		Stage stage=(Stage)view.getScene().getWindow();
    	stage.close();
	}

	@FXML
	void onSaveAction(ActionEvent event) {
		
		PomodoroController pc = new PomodoroController();
		
		
		
	}

	public BorderPane getView() {
		return view;
	}

}
