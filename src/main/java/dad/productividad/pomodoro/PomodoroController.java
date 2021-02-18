package dad.productividad.pomodoro;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import dad.productividad.project.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

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
		// TODO Auto-generated method stub

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
			result.get();
		}
	}



	public AnchorPane getView() {
		return this.view;
	}

}
