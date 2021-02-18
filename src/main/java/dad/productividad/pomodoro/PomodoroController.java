package dad.productividad.pomodoro;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.NumberStringConverter;

public class PomodoroController implements Initializable {

	@FXML
	private AnchorPane view;

	@FXML
	private JFXTextField pomoTextField;

	@FXML
	private JFXButton pomodoroSettings;

    @FXML
    private Label minuteLabel;

    @FXML
    private Label secondsLabel;

	@FXML
	private JFXButton pomodoroPlay;

	@FXML
	private JFXButton pomodoroPause;

	@FXML
	private JFXButton pomodoroCancel;

	
	public PomodoroController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PomodoroView.fxml"));
			loader.setResources(ResourceBundle.getBundle("i18n/strings"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
			pomodoroPlay.disableProperty().bind(Bindings.equal(minuteLabel.textProperty(), "00"));
			pomodoroPause.setDisable(true);
			pomodoroCancel.setDisable(true);

			
	}
	@FXML
	void onPomodoroCancelAction(ActionEvent event) {
		
	}

	@FXML
	void onPomodoroPauseAction(ActionEvent event) {

	}

	@FXML
	void onPomodoroPlayAction(ActionEvent event) {
		
		
		
	}

	@FXML
	void onPomodoroSettingsAction(ActionEvent event) {
		PomodoroEditorDialog dialog = new PomodoroEditorDialog();
		Optional<PomodoroSetup> result = dialog.showAndWait();
		
		if (result.isPresent()) {
			PomodoroSetup ps = result.get();
			minuteLabel.textProperty().bindBidirectional(ps.minutesProperty(),  new NumberStringConverter("00"));
	}
	}



	public AnchorPane getView() {
		return this.view;
	}

}
