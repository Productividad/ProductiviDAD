package dad.productividad.pomodoro;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PomodoroSetup {

<<<<<<< HEAD
	private IntegerProperty idPomodoro = new SimpleIntegerProperty();
	private StringProperty titlePomodoro = new SimpleStringProperty();
	private IntegerProperty timeSpent = new SimpleIntegerProperty();
	private IntegerProperty idPage = new SimpleIntegerProperty();
	private IntegerProperty idTask = new SimpleIntegerProperty();
	private IntegerProperty minutes = new SimpleIntegerProperty();
	private IntegerProperty shortBreak = new SimpleIntegerProperty();
	private IntegerProperty longBreak = new SimpleIntegerProperty();
	private IntegerProperty pomoLength = new SimpleIntegerProperty();

	public PomodoroSetup(int minutes, int shortBreak, int longBreak, int pomoLength) {
		this.minutes.set(minutes);
		this.shortBreak.set(shortBreak);
		this.longBreak.set(longBreak);
		this.pomoLength.set(pomoLength);
	}

	public final IntegerProperty idPomodoroProperty() {
		return this.idPomodoro;
	}

	public final int getIdPomodoro() {
		return this.idPomodoroProperty().get();
	}

	public final void setIdPomodoro(final int idPomodoro) {
		this.idPomodoroProperty().set(idPomodoro);
	}

	public final StringProperty titlePomodoroProperty() {
		return this.titlePomodoro;
	}

	public final String getTitlePomodoro() {
		return this.titlePomodoroProperty().get();
	}

	public final void setTitlePomodoro(final String titlePomodoro) {
		this.titlePomodoroProperty().set(titlePomodoro);
	}

	public final IntegerProperty minutesProperty() {
		return this.minutes;
	}

	public final int getMinutes() {
		return this.minutesProperty().get();
	}

	public final void setMinutes(final int minutes) {
		this.minutesProperty().set(minutes);
	}

	public final IntegerProperty shortBreakProperty() {
		return this.shortBreak;
	}

	public final int getShortBreak() {
		return this.shortBreakProperty().get();
	}

	public final void setShortBreak(final int shortBreak) {
		this.shortBreakProperty().set(shortBreak);
	}

	public final IntegerProperty longBreakProperty() {
		return this.longBreak;
	}

	public final int getLongBreak() {
		return this.longBreakProperty().get();
	}

	public final void setLongBreak(final int longBreak) {
		this.longBreakProperty().set(longBreak);
	}

	public final IntegerProperty pomoLengthProperty() {
		return this.pomoLength;
	}

	public final int getPomoLength() {
		return this.pomoLengthProperty().get();
	}

	public final void setPomoLength(final int pomoLength) {
		this.pomoLengthProperty().set(pomoLength);
	}

	public final IntegerProperty timeSpentProperty() {
		return this.timeSpent;
	}

	public final int getTimeSpent() {
		return this.timeSpentProperty().get();
	}

	public final void setTimeSpent(final int timeSpent) {
		this.timeSpentProperty().set(timeSpent);
	}

	public final IntegerProperty idPageProperty() {
		return this.idPage;
	}

	public final int getIdPage() {
		return this.idPageProperty().get();
	}

	public final void setIdPage(final int idPage) {
		this.idPageProperty().set(idPage);
	}

	public final IntegerProperty idTaskProperty() {
		return this.idTask;
	}

	public final int getIdTask() {
		return this.idTaskProperty().get();
	}

	public final void setIdTask(final int idTask) {
		this.idTaskProperty().set(idTask);
	}

	@Override
	public String toString() {
		return "PomodoroSetup [minutes=" + minutes + ", shortBreak=" + shortBreak + ", longBreak=" + longBreak + "]";
	}
=======
    private IntegerProperty minutes = new SimpleIntegerProperty();
    private IntegerProperty shortBreak = new SimpleIntegerProperty();
    private IntegerProperty longBreak = new SimpleIntegerProperty();
    private IntegerProperty pomoLength = new SimpleIntegerProperty();

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
     * @return A String with Setup parameters
     */
    @Override
    public String toString() {
        return "PomodoroSetup [minutes=" + minutes + ", shortBreak=" + shortBreak + ", longBreak=" + longBreak + "]";
    }
>>>>>>> 48ede53af6f1aec6b85daaf06b374feb07f0a0bf

}
