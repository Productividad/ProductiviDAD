package dad.productividad.pomodoro;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.Timer;

import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;

import dad.productividad.dataManager.TablePomodoro;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.converter.NumberStringConverter;

/**
 * Pomodoro view Controller
 */
public class PomodoroController implements Initializable {

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

	private ResourceBundle rb;

	@FXML
	private StackPane view;

	@FXML
	private Label pomodoroTitleLabel;

	@FXML
	private Button pomodoroSettings;

	@FXML
	private JFXSpinner pomodoroSpinner;

	@FXML
	private Label minuteLabel;

	@FXML
	private Label secondsLabel;

	@FXML
	private Button pomodoroCancel;

	@FXML
	private Button pomodoroPlay;

	@FXML
	private Button pomodoroPause;

	@FXML
	private GridPane dialogPomo;

	@FXML
	private Button acceptDialogProject;

	@FXML
	private Label shortLabel;

	@FXML
	private Label longLabel;

	@FXML
	private ComboBox<Integer> pomodoroBox;

	@FXML
	private ComboBox<Integer> shortBreakBox;

	@FXML
	private ComboBox<Integer> longBreakBox;

	@FXML
	private ComboBox<Integer> pomoLength;

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
		pomodoroTitleLabel.setVisible(false);
		totalSeconds = minutesToSeconds(Integer.valueOf(minuteLabel.textProperty().getValue()),
				Integer.valueOf(secondsLabel.textProperty().getValue()));
		pomodoroSpinner.setProgress(0);
		pomodoroSpinner.getStyleClass().add("pomodoroSpinner");
		dialogPomo.setVisible(false);
		/**
		 * Filling comboBox
		 */
		ObservableList<Integer> minutesSecondsList = FXCollections.observableArrayList();
		for (int i = 1; i <= 60; i++) {
			minutesSecondsList.add(i);

		}
		/**
		 * Filling comboBox rounds
		 */
		ObservableList<Integer> pomoRounds = FXCollections.observableArrayList();
		for (int i = 1; i <= 10; i++) {
			pomoRounds.add(i);

		}

		pomodoroBox.setItems(minutesSecondsList);
		pomodoroBox.setValue(25);
		shortBreakBox.setValue(5);
		longBreakBox.setValue(20);

		shortBreakBox.getItems().addAll(5, 10, 15);
		longBreakBox.getItems().addAll(20, 25);
		pomoLength.setItems(pomoRounds);
		pomoLength.setValue(3);
	}

	@FXML
	private void onAcceptDialog(ActionEvent event) {

		pomodoroSetup = new PomodoroSetup(pomodoroBox.getSelectionModel().getSelectedItem(),
				shortBreakBox.getSelectionModel().getSelectedItem(), longBreakBox.getSelectionModel().getSelectedItem(),
				pomoLength.getSelectionModel().getSelectedItem());
		minuteLabel.setText(String.format("%02d", pomodoroSetup.getMinutes()));
		minutesSelected = pomodoroSetup.getMinutes();
		pomodoroPlay.setDisable(false);

		// TODO
		hideDialog();
	}

	@FXML
	private void onCancelDialog(ActionEvent event) {
		hideDialog();
	}

	private void hideDialog() {
		dialogPomo.setVisible(false);
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
		pomodoroTitleLabel.setText("");
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

		dialogPomo.setVisible(true);

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
	 * Starts long timer based on Pomodoro Settings Inserts into the database the
	 * pomodoro object
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
					pomodoroSetup.setTimeSpent(totalSeconds);
					TablePomodoro.insertPomodoro(pomodoroSetup);
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

	}

	/**
	 * @return The pomodoro view.
	 */
	public StackPane getView() {
		return this.view;
	}

}
