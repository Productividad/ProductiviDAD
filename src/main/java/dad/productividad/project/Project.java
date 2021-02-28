package dad.productividad.project;

import javafx.beans.property.*;

import java.time.LocalDate;

/**
 * Project Model
 */
public class Project {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty title = new SimpleStringProperty();
    private DoubleProperty progress = new SimpleDoubleProperty();
    private BooleanProperty completed = new SimpleBooleanProperty();
    private BooleanProperty white = new SimpleBooleanProperty();
    private ObjectProperty<LocalDate> deadLine = new SimpleObjectProperty<>();
    private StringProperty color = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private StringProperty styleSheet = new SimpleStringProperty();

    /**
     * Project constructor
     *
     * @param title
     * @param progress
     * @param color
     * @param white
     */
    public Project(String title, double progress, String color, boolean white) {
        this.title.set(title);
        this.progress.set(progress);
        this.color.set(color);
        this.white.set(white);
    }

    /**
     * Project default constructor
     */
    public Project() {

    }

    /**
     * @return StringProperty of title
     */
    public final StringProperty titleProperty() {
        return this.title;
    }

    /**
     * @return String of title
     */
    public final String getTitle() {
        return this.titleProperty().get();
    }

    /**
     * Sets a new title
     *
     * @param title
     */
    public final void setTitle(final String title) {
        this.titleProperty().set(title);
    }

    /**
     * @return DoubleProperty of progress
     */
    public final DoubleProperty progressProperty() {
        return this.progress;
    }

    /**
     * @return double of progress
     */
    public final double getProgress() {
        return this.progressProperty().get();
    }

    /**
     * Sets a new progress
     *
     * @param progress
     */
    public final void setProgress(final double progress) {
        this.progressProperty().set(progress);
    }

    /**
     * @return BooleanProperty of completed
     */
    public final BooleanProperty CompletedProperty() {
        return this.completed;
    }

    /**
     * @return boolean of completed
     */
    public final boolean isCompleted() {
        return this.CompletedProperty().get();
    }

    /**
     * Sets a new value for completed
     *
     * @param Completed
     */
    public final void setCompleted(final boolean Completed) {
        this.CompletedProperty().set(Completed);
    }

    /**
     * @return ObjectPropery of LocalDate deadline
     */
    public final ObjectProperty<LocalDate> deadLineProperty() {
        return this.deadLine;
    }

    /**
     * @return LocalDate of deadline
     */
    public final LocalDate getDeadLine() {
        return this.deadLineProperty().get();
    }

    /**
     * Sets a new deadline
     *
     * @param deadLine
     */
    public final void setDeadLine(final LocalDate deadLine) {
        this.deadLineProperty().set(deadLine);
    }

    /**
     * @return StringProperty of color
     */
    public final StringProperty colorProperty() {
        return this.color;
    }

    /**
     * @return String of color
     */
    public final String getColor() {
        return this.colorProperty().get();
    }

    /**
     * Sets a new color
     *
     * @param color
     */
    public final void setColor(final String color) {
        this.colorProperty().set(color);
    }

    /**
     * @return IntegerProperty of id
     */
    public final IntegerProperty IDProperty() {
        return this.id;
    }

    /**
     * @return int of id
     */
    public final int getId() {
        return this.IDProperty().get();
    }

    /**
     * Sets a new id
     *
     * @param id
     */
    public final void setId(final int id) {
        this.IDProperty().set(id);
    }

    /**
     * @return StringProperty of description
     */
    public final StringProperty descriptionProperty() {
        return this.description;
    }

    /**
     * @return String of description
     */
    public final String getDescription() {
        return this.descriptionProperty().get();
    }

    /**
     * Sets a new description
     *
     * @param description
     */
    public final void setDescription(final String description) {
        this.descriptionProperty().set(description);
    }

    /**
     * @return BooleanProperty of white
     */
    public final BooleanProperty WhiteProperty() {
        return this.white;
    }

    /**
     * @return boolean of white
     */
    public final boolean isWhite() {
        return this.WhiteProperty().get();
    }

    /**
     * Sets a new value for white
     *
     * @param white
     */
    public final void setWhite(final boolean white) {
        this.WhiteProperty().set(white);
    }

    /**
     * @return StringProperty of styleSheet
     */
    public final StringProperty styleSheetProperty() {
        return this.styleSheet;
    }

    /**
     * @return String of styleSheet
     */

    public final String getStyleSheet() {
        return this.styleSheetProperty().get();
    }

    /**
     * Sets a new styleSheet
     *
     * @param styleSheet
     */
    public final void setStyleSheet(final String styleSheet) {
        this.styleSheetProperty().set(styleSheet);
    }


}
