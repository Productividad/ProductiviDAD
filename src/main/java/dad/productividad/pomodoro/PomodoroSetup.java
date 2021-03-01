package dad.productividad.pomodoro;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Pomodoro setup class
 */
public class PomodoroSetup {

	/**
	 * Minutes
	 */
	private IntegerProperty minutes = new SimpleIntegerProperty();
	/**
	 * Short Break
	 */
	private IntegerProperty shortBreak = new SimpleIntegerProperty();
	/**
	 * Long break
	 */
	private IntegerProperty longBreak = new SimpleIntegerProperty();
	/**
	 * Pomodoro length
	 */
	private IntegerProperty pomoLength = new SimpleIntegerProperty();
	/**
	 * Pomodoro id
	 */
	private IntegerProperty idPomodoro = new SimpleIntegerProperty();
	/**
	 * Pomodoro title
	 */
	private StringProperty titlePomodoro = new SimpleStringProperty();
	/**
	 * Pomodoro time spent
	 */
	private IntegerProperty timeSpent = new SimpleIntegerProperty();
	/**
	 * Pomodoro page id
	 */
	private IntegerProperty idPage = new SimpleIntegerProperty();
	/**
	 * Pomodoro task id
	 */
	private IntegerProperty idTask = new SimpleIntegerProperty();

	/**
	 * PomodoroSetup constructor
	 *
	 * @param minutes
	 * @param shortBreak
	 * @param longBreak
	 * @param pomoLength
	 */
	public PomodoroSetup(int minutes, int shortBreak, int longBreak, int pomoLength) {
		this.minutes.set(minutes);
		this.shortBreak.set(shortBreak);
		this.longBreak.set(longBreak);
		this.pomoLength.set(pomoLength);

	}

	/**
	 * @return IntegerProperty of minutes
	 */
	public final IntegerProperty minutesProperty() {
		return this.minutes;
	}

	/**
	 * @return int of minutes
	 */
	public final int getMinutes() {
		return this.minutesProperty().get();
	}

	/**
	 * Sets new minutes
	 *
	 * @param minutes
	 */
	public final void setMinutes(final int minutes) {
		this.minutesProperty().set(minutes);
	}

	/**
	 * @return IntegerProperty of shortBreak
	 */
	public final IntegerProperty shortBreakProperty() {
		return this.shortBreak;
	}

	/**
	 * @return int of shortBreak
	 */
	public final int getShortBreak() {
		return this.shortBreakProperty().get();
	}

	/**
	 * Sets a new shortBreak
	 *
	 * @param shortBreak
	 */
	public final void setShortBreak(final int shortBreak) {
		this.shortBreakProperty().set(shortBreak);
	}

	/**
	 * @return IntegerPrroperty of longBreak
	 */
	public final IntegerProperty longBreakProperty() {
		return this.longBreak;
	}

	/**
	 * @return int of longBreak
	 */
	public final int getLongBreak() {
		return this.longBreakProperty().get();
	}

	/**
	 * Sets a new longBreak
	 *
	 * @param longBreak
	 */
	public final void setLongBreak(final int longBreak) {
		this.longBreakProperty().set(longBreak);
	}

	/**
	 * @return IntegerProperty of pomoLength
	 */
	public final IntegerProperty pomoLengthProperty() {
		return this.pomoLength;
	}

	/**
	 * @return int of PomoLength
	 */
	public final int getPomoLength() {
		return this.pomoLengthProperty().get();
	}

	/**
	 * Sets a new pomoLength
	 *
	 * @param pomoLength
	 */
	public final void setPomoLength(final int pomoLength) {
		this.pomoLengthProperty().set(pomoLength);
	}

	/**
	 * @return Pomodoro id IntegerProperty
	 */
	public final IntegerProperty idPomodoroProperty() {
		return this.idPomodoro;
	}

	/**
	 * @return Pomodoro id int
	 */
	public final int getIdPomodoro() {
		return this.idPomodoroProperty().get();
	}

	/**
	 * Set a new Pomodoro id
	 *
	 * @param idPomodoro
	 */
	public final void setIdPomodoro(final int idPomodoro) {
		this.idPomodoroProperty().set(idPomodoro);
	}

	/**
	 * @return Pomodoro title StringProperty
	 */
	public final StringProperty titlePomodoroProperty() {
		return this.titlePomodoro;
	}

	/**
	 * @return Pomodoro title String
	 */
	public final String getTitlePomodoro() {
		return this.titlePomodoroProperty().get();
	}

	/**
	 * Set a new Pomodoro title
	 *
	 * @param titlePomodoro
	 */
	public final void setTitlePomodoro(final String titlePomodoro) {
		this.titlePomodoroProperty().set(titlePomodoro);
	}

	/**
	 * @return Time Spent IntegerProperty
	 */
	public final IntegerProperty timeSpentProperty() {
		return this.timeSpent;
	}

	/**
	 * @return Time Spent int
	 */
	public final int getTimeSpent() {
		return this.timeSpentProperty().get();
	}

	/**
	 * Set a new Time Spent
	 *
	 * @param timeSpent
	 */
	public final void setTimeSpent(final int timeSpent) {
		this.timeSpentProperty().set(timeSpent);
	}

	/**
	 * @return id Page IntegerProperty
	 */
	public final IntegerProperty idPageProperty() {
		return this.idPage;
	}

	/**
	 * @return id Page int
	 */
	public final int getIdPage() {
		return this.idPageProperty().get();
	}

	/**
	 * Set a new id page
	 *
	 * @param idPage
	 */
	public final void setIdPage(final int idPage) {
		this.idPageProperty().set(idPage);
	}

	/**
	 * @return id Task IntegerProperty
	 */
	public final IntegerProperty idTaskProperty() {
		return this.idTask;
	}

	/**
	 * @return id Task int
	 */
	public final int getIdTask() {
		return this.idTaskProperty().get();
	}

	/**
	 * Set a new id Task
	 *
	 * @param idTask
	 */
	public final void setIdTask(final int idTask) {
		this.idTaskProperty().set(idTask);
	}

	/**
	 * @return A String with Setup parameters
	 */
	@Override
	public String toString() {
		return "PomodoroSetup [minutes=" + minutes + ", shortBreak=" + shortBreak + ", longBreak=" + longBreak + "]";
	}

}
