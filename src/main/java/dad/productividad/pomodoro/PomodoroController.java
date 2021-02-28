package dad.productividad.pomodoro;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.Timer;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.converter.NumberStringConverter;

public class PomodoroController implements Initializable {

    @FXML
    private GridPane view; 

    @FXML
    private JFXTextField pomoTextField;

    @FXML
    private Button pomodoroSettings,pomodoroCancel,pomodoroPlay,pomodoroPause;

    @FXML
    private JFXSpinner pomodoroSpinner;

    @FXML
    private Label minuteLabel;

    @FXML 
    private Label secondsLabel;

    private Timer pomodoro;

    private Timer shortTimer;

    private Timer longTimer;

    private Integer secondsTimer;

    private static final int CANCEL_SECONDS = 0;

    private Integer minutesTimer;

    private Integer minutesSelected;

    private static final int CANCEL_MINUTES = 0;

    private Integer completed = 0;

    private Integer totalSeconds;

    private PomodoroSetup pomodoroSetup;

    private boolean isPaused;

    private boolean isShortTimer;

    private boolean isLongTimer;

    private Media soundPomodoroEnd = new Media(
            this.getClass().getResource("/sound/completed-pomodoro-sound.wav").toExternalForm());
    private Media soundTimerStart = new Media(
            this.getClass().getResource("/sound/started-pomodoro-break.wav").toExternalForm());

    private MediaPlayer mediaPlayer;

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
        //pomodoroSpinner.setProgress(value);

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
                    pomodoroSpinner.setProgress(totalSeconds / (minutesTimer * 60F));
                    System.out.println(totalSeconds / (minutesTimer * 60F));
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

        isShortTimer = true;
        pomodoroCancel.setDisable(false);
        pomodoroPause.setDisable(true);
        pomodoroPlay.setDisable(true);
        minutesTimer = pomodoroSetup.getShortBreak();
        secondsTimer = minutesToSeconds(minutesTimer, 0);
        mediaPlayer = new MediaPlayer(soundTimerStart);
        mediaPlayer.play();

        shortTimer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                secondsTimer--;
                totalSeconds++;

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

        isLongTimer = true;
        pomodoroCancel.setDisable(false);
        pomodoroPause.setDisable(true);
        pomodoroPlay.setDisable(true);
        pomodoro.stop();

        minutesTimer = pomodoroSetup.getLongBreak();
        secondsTimer = minutesToSeconds(0, 10);
        mediaPlayer = new MediaPlayer(soundPomodoroEnd);
        mediaPlayer.play();
        longTimer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                secondsTimer--;
                totalSeconds++;

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

    }

    /**
     * @return The pomodoro view.
     */
    public GridPane getView() { 
        return this.view; 
    }

}
