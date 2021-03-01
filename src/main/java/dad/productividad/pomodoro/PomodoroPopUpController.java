package dad.productividad.pomodoro;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Observable;
import java.util.ResourceBundle;

import dad.productividad.app.MainController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PomodoroPopUpController implements Initializable {

	@FXML
	private GridPane pomoPopUpView;

	@FXML
	private HBox timerPopUpBox;

	@FXML
	private Label minutesPopUpLabel;

	@FXML
	private Label secondsPopUpLabel;

	@FXML
	private HBox buttonPopUpBox;

	@FXML
	private Label titlePomodoroLabel;
	@FXML
	private Button closePomoPopupButton;

	private Stage stage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

//		PomodoroController.pomodoroMinutesTimer.addListener(new ChangeListener<String>() {
//			@Override
//			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//
//				minutesPopUpLabel.setText(newValue);
//			}
//		});
//
//		PomodoroController.pomodoroSecondsTimer.addListener(new ChangeListener<String>() {
//			@Override
//			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//
//				secondsPopUpLabel.setText(newValue);
//			}
//		});

	}

	public PomodoroPopUpController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PomodoroPopUpComponent.fxml"));
			loader.setResources(ResourceBundle.getBundle("i18n/strings", Locale.getDefault()));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onClosePomoPopUpAction(ActionEvent event) {
		MainController.mainController.getView().setBottom(null);
	}

	public GridPane getView() {
		return this.pomoPopUpView;
	}

}
