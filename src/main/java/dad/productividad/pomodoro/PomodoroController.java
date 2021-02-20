package dad.productividad.pomodoro;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.Timer;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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
	
	@FXML
    private HBox progressBox;

    @FXML
    private JFXProgressBar pomoProgressBar;

	private Timer pomodoro;

	private Timer shortTimer;

	private Timer longTimer;

	private Integer secondsTimer;

	private static final int CANCEL_SECONDS = 0;

	private Integer minutesTimer;

	private static final int CANCEL_MINUTES = 0;

	private Integer completed = 0;

	private Integer totalSeconds;

	private PomodoroSetup pomodoroSetup;

	private boolean isPaused;

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
		pomodoroPlay.setDisable(true);
		pomodoroPause.setDisable(true);
		pomodoroCancel.setDisable(true);
		totalSeconds = minutesToSeconds(Integer.valueOf(minuteLabel.textProperty().getValue()),
				Integer.valueOf(secondsLabel.textProperty().getValue()));

	}

	@FXML
	private void onPomodoroCancelAction(ActionEvent event) {

		pomodoro.stop();
		minuteLabel.setText(String.format("%02d", CANCEL_MINUTES));
		secondsLabel.setText(String.format("%02d", CANCEL_SECONDS));
		pomodoroCancel.setDisable(true);
		pomodoroPause.setDisable(true);
		pomodoroPlay.setDisable(true);
		// Reset rounds
		completed = 0;
		totalSeconds = minutesToSeconds(Integer.valueOf(minuteLabel.textProperty().getValue()),
				Integer.valueOf(secondsLabel.textProperty().getValue()));

	}

	@FXML
	private void onPomodoroPauseAction(ActionEvent event) {
		isPaused = true;
		pomodoroPause.setDisable(true);
		pomodoroPlay.setDisable(false);
		pomodoroCancel.setDisable(false);

		pomodoro.stop();

	}

	@FXML
	private void onPomodoroPlayAction(ActionEvent event) {

		pomodoroCancel.setDisable(false);
		pomodoroPause.setDisable(false);
		pomodoroPlay.setDisable(true);

		if (isPaused) {
			isPaused = false;
			pomodoro.start();
		} else {

			minutesTimer = pomodoroSetup.getMinutes();
			secondsTimer = minutesToSeconds(minutesTimer, 0);
			pomodoro = new Timer(1000, new ActionListener() {

				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {

					secondsTimer--;
					totalSeconds++;

					Platform.runLater(() -> {
						secondsToMinutes(secondsTimer);
					});

					if (secondsTimer == 0) {
						completed++;

						if (completed == pomodoroSetup.getPomoLength()) {
							startLongTimer();
						} else if (completed > 0 && completed % 2 == 0) {
							startShortTimer();
						}
						pomodoro.stop();
					}
				}

			});

			pomodoro.start();
		}
	}

	@FXML
	private void onPomodoroSettingsAction(ActionEvent event) {
		PomodoroEditorDialog dialog = new PomodoroEditorDialog();
		Optional<PomodoroSetup> result = dialog.showAndWait();

		if (result.isPresent()) {
			pomodoroSetup = result.get();
			minuteLabel.textProperty().bindBidirectional(pomodoroSetup.minutesProperty(),
					new NumberStringConverter("00"));
			pomodoroPlay.setDisable(false);
		}
	}

	private void startShortTimer() {

	}

	private void startLongTimer() {

	}

	private Integer minutesToSeconds(Integer minutes, Integer seconds) {
		Integer minutesSeconds = minutes * 60;
		Integer total = minutesSeconds + seconds;
		return total;
	}

	private void secondsToMinutes(Integer seconds) {
		Integer minutes = seconds / 60;
		Integer secondsRemaining = seconds % 60;

		minuteLabel.setText(String.format("%02d", minutes));
		secondsLabel.setText(String.format("%02d", secondsRemaining));

	}

	public AnchorPane getView() {
		return this.view;
	}

}
