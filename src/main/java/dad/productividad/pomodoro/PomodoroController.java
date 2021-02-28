package dad.productividad.pomodoro;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.Timer;

import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.converter.NumberStringConverter;

/**
 * Pomodoro view Controller
 */
public class PomodoroController implements Initializable {

	/**
	 * Pomodoro view
	 */
	@FXML
	private GridPane view;
	/**
	 * Pomodoro Textfield
	 */
	@FXML
	private JFXTextField pomoTextField;
	/**
	 * Pomodoro buttons
	 */
	@FXML
	private Button pomodoroSettings, pomodoroCancel, pomodoroPlay, pomodoroPause;
	/**
	 * Pomodor spinner
	 */
	@FXML
	private JFXSpinner pomodoroSpinner;
	/**
	 * Pomodoro minute label
	 */
	@FXML
	private Label minuteLabel;
	/**
	 * Pomodoro seconds label
	 */
	@FXML
	private Label secondsLabel;
	/**
	 * Pomodoro timer
	 */
	private Timer pomodoro;
	/**
	 * Pomodoro short timer
	 */
	private Timer shortTimer;
	/**
	 * Pomodoro long timer
	 */
	private Timer longTimer;
	/**
	 * Integer seconds timer
	 */
	private Integer secondsTimer;
	/**
	 * Cancel seconds
	 */
	private static final int CANCEL_SECONDS = 0;
	/**
	 * Minutes timer
	 */
	private Integer minutesTimer;
	/**
	 * Minutes selected
	 */
	private Integer minutesSelected;
	/**
	 * Cancel minutes
	 */
	private static final int CANCEL_MINUTES = 0;
	/**
	 * Completed
	 */
	private Integer completed = 0;
	/**
	 * Total seconds
	 */
	private Integer totalSeconds;
	/**
	 * Pomodoro Setup
	 */
	private PomodoroSetup pomodoroSetup;
	/**
	 * Paused
	 */
	private boolean isPaused;
	/**
	 * Short timer
	 */
	private boolean isShortTimer;
	/**
	 * Long timer
	 */
	private boolean isLongTimer;
	/**
	 * End sound
	 */
	private Media soundPomodoroEnd = new Media(
			this.getClass().getResource("/sound/completed-pomodoro-sound.wav").toExternalForm());
	/**
	 * Start sound
	 */
	private Media soundTimerStart = new Media(
			this.getClass().getResource("/sound/started-pomodoro-break.wav").toExternalForm());
	/**
	 * Media player
	 */
	private MediaPlayer mediaPlayer;

	private int shortTimerSeconds;

	public static StringProperty pomodoroMinutesTimer = new SimpleStringProperty();
	public static StringProperty pomodoroSecondsTimer = new SimpleStringProperty();

	/**
	 * PomodoroController constructor
	 */
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

	/**
	 * Initialization of Pomodoro view
	 *
	 * @param location
	 * @param resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pomodoroPlay.setDisable(true);
		pomodoroPause.setDisable(true);
		pomodoroCancel.setDisable(true);
		totalSeconds = minutesToSeconds(Integer.valueOf(minuteLabel.textProperty().getValue()),
				Integer.valueOf(secondsLabel.textProperty().getValue()));
		pomodoroSpinner.setProgress(0);
		pomodoroSpinner.getStyleClass().add("pomodoroSpinner");
	}

	/**
	 * Cancel button action
	 *
	 * @param event
	 */
	@FXML
	private void onPomodoroCancelAction(ActionEvent event) {

		if (isShortTimer) {
			isShortTimer = false;
			shortTimer.stop();
		} else if (isLongTimer) {
			isLongTimer = false;
			longTimer.stop();
		} else {
			pomodoro.stop();

		}
		pomodoroSpinner.setProgress(0);
		minuteLabel.setText(String.format("%02d", CANCEL_MINUTES));
		secondsLabel.setText(String.format("%02d", CANCEL_SECONDS));
		setPomodoroMinutesTimer(minuteLabel.getText());
		setPomodoroSecondsTimer(secondsLabel.getText());
		pomodoroPlay.setVisible(true);
		pomodoroCancel.setDisable(true);
		pomodoroPause.setDisable(true);
		pomodoroPlay.setDisable(true);

		// Reset rounds
		completed = 0;
		totalSeconds = minutesToSeconds(Integer.valueOf(minuteLabel.textProperty().getValue()),
				Integer.valueOf(secondsLabel.textProperty().getValue()));

	}

	/**
	 * Pause button action
	 *
	 * @param event
	 */
	@FXML
	private void onPomodoroPauseAction(ActionEvent event) {

		// pomodoroSpinner.setProgress(value);

		isPaused = true;
		pomodoroPause.setDisable(true);
		pomodoroPlay.setDisable(false);
		pomodoroCancel.setDisable(false);
		pomodoro.stop();

	}

	/**
	 * Starts the timer, initializing the timer Thread
	 *
	 * @param event
	 */
	@FXML
	public void onPomodoroPlayAction(ActionEvent event) {

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
					pomodoroSpinner.setProgress(totalSeconds / (minutesTimer * 60F));
					Platform.runLater(() -> {
						secondsToMinutes(secondsTimer);
					});

					if (secondsTimer == 0) {
						completed++;

						if (completed > 4 && completed == pomodoroSetup.getPomoLength()) {
							startLongTimer();
						} else
							startShortTimer();

						pomodoro.stop();

					}
				}

			});

			pomodoro.start();
		}
	}

	/**
	 * Opens up the pomodoro settings dialog
	 *
	 * @param event
	 */

	@FXML
	private void onPomodoroSettingsAction(ActionEvent event) {
		PomodoroEditorDialog dialog = new PomodoroEditorDialog();
		Optional<PomodoroSetup> result = dialog.showAndWait();

		if (result.isPresent()) {
			pomodoroSetup = result.get();
			minuteLabel.textProperty().bindBidirectional(pomodoroSetup.minutesProperty(),
					new NumberStringConverter("00"));
			minutesSelected = pomodoroSetup.getMinutes();
			pomodoroPlay.setDisable(false);
		}
	}

	/**
	 * Starts a short timer
	 */
	private void startShortTimer() {
		shortTimerSeconds = 0;
		isShortTimer = true;
		pomodoroCancel.setDisable(false);
		pomodoroPause.setDisable(true);
		pomodoroPlay.setDisable(true);
		minutesTimer = pomodoroSetup.getShortBreak();
		secondsTimer = minutesToSeconds(minutesTimer, 0);
		mediaPlayer = new MediaPlayer(soundTimerStart);
		mediaPlayer.play();
		pomodoroSpinner.setProgress(0);

		shortTimer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				secondsTimer--;
				totalSeconds++;
				shortTimerSeconds++;
				pomodoroSpinner.setProgress(shortTimerSeconds / (minutesTimer * 60F));
				Platform.runLater(() -> {
					secondsToMinutes(secondsTimer);
				});

				if (secondsTimer == 0) {
					mediaPlayer = new MediaPlayer(soundPomodoroEnd);
					mediaPlayer.play();
					pomodoroPlay.setVisible(true);
					pomodoroPlay.setDisable(false);
					shortTimer.stop();

				}
			}

		});
		shortTimer.start();
	}

	/**
	 * Starts long timer based on Pomodoro Settings
	 */
	private void startLongTimer() {
		shortTimerSeconds = 0;
		isLongTimer = true;
		pomodoroCancel.setDisable(false);
		pomodoroPause.setDisable(true);
		pomodoroPlay.setDisable(true);
		pomodoro.stop();

		minutesTimer = pomodoroSetup.getLongBreak();
		secondsTimer = minutesToSeconds(minutesTimer, 0);
		mediaPlayer = new MediaPlayer(soundPomodoroEnd);
		mediaPlayer.play();
		longTimer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {

				secondsTimer--;
				totalSeconds++;
				shortTimerSeconds++;
				pomodoroSpinner.setProgress(shortTimerSeconds / (minutesTimer * 60F));
				Platform.runLater(() -> {
					secondsToMinutes(secondsTimer);
				});

				if (secondsTimer == 0) {

					longTimer.stop();
					minuteLabel.setText(String.format("%02d", CANCEL_MINUTES));
					secondsLabel.setText(String.format("%02d", CANCEL_SECONDS));
					pomodoroCancel.setDisable(true);
					pomodoroPause.setDisable(true);
					pomodoroPlay.setDisable(true);

					totalSeconds = minutesToSeconds(Integer.valueOf(minuteLabel.textProperty().getValue()),
							Integer.valueOf(secondsLabel.textProperty().getValue()));
				}
			}

		});

		longTimer.start();
	}

	/**
	 * Converts minutes to seconds
	 *
	 * @param minutes
	 * @param seconds
	 * @return total
	 */
	private Integer minutesToSeconds(Integer minutes, Integer seconds) {
		Integer minutesSeconds = minutes * 60;
		Integer total = minutesSeconds + seconds;
		return total;
	}

	/**
	 * Converts seconds to minutes
	 *
	 * @param seconds
	 */
	private void secondsToMinutes(Integer seconds) {
		Integer minutes = seconds / 60;
		Integer secondsRemaining = seconds % 60;

		minuteLabel.setText(String.format("%02d", minutes));
		secondsLabel.setText(String.format("%02d", secondsRemaining));

		setPomodoroSecondsTimer(secondsLabel.getText());
		setPomodoroMinutesTimer(minuteLabel.getText());

	}

	/**
	 * @return The pomodoro view.
	 */
	public GridPane getView() {
		return this.view;
	}

	public final StringProperty pomodoroMinutesTimerProperty() {
		return PomodoroController.pomodoroMinutesTimer;
	}

	public final String getPomodoroMinutesTimer() {
		return this.pomodoroMinutesTimerProperty().get();
	}

	public final void setPomodoroMinutesTimer(final String pomodoroMinutesTimer) {
		this.pomodoroMinutesTimerProperty().set(pomodoroMinutesTimer);
	}

	public final StringProperty pomodoroSecondsTimerProperty() {
		return PomodoroController.pomodoroSecondsTimer;
	}

	public final String getPomodoroSecondsTimer() {
		return this.pomodoroSecondsTimerProperty().get();
	}

	public final void setPomodoroSecondsTimer(final String pomodoroSecondsTimer) {
		this.pomodoroSecondsTimerProperty().set(pomodoroSecondsTimer);
	}

}
