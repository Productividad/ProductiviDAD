package dad.productividad.pomodoro;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

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

}
