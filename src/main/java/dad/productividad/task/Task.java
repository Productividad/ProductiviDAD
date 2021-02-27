package dad.productividad.task;


import java.time.LocalDate;

import dad.productividad.page.Page;
import dad.productividad.project.Project;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Task {

    private IntegerProperty id = new SimpleIntegerProperty();

    private StringProperty title = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private StringProperty color = new SimpleStringProperty();

    private BooleanProperty favourite = new SimpleBooleanProperty();
    private BooleanProperty done = new SimpleBooleanProperty();
    private BooleanProperty white = new SimpleBooleanProperty();

    private ObjectProperty<LocalDate> deadLine = new SimpleObjectProperty<>();
    private ObjectProperty<Page> page = new SimpleObjectProperty<>();
    private IntegerProperty pageId = new SimpleIntegerProperty();
    private ObjectProperty<Task> parentTask = new SimpleObjectProperty<>();
    private ObjectProperty<Project> project = new SimpleObjectProperty<>();
    private ObjectProperty<StatusType> status = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> creationDate = new SimpleObjectProperty<>();
    private ListProperty<Task> childTasks = new SimpleListProperty<Task>(FXCollections.observableArrayList());
    private ObjectProperty<LocalDate> completedDate = new SimpleObjectProperty<>();

    public Task() {

    }

    /**
     * Task constructor
     *
     * @param title
     * @param description
     * @param completed
     */
    public Task(String title, String description, boolean completed) {
        this.title.set(title);
        this.description.set(description);
        this.done.set(completed);
    }

    /**
     * @return the String of title
     */
    @Override
    public String toString() {
        return getTitle();
    }

    /**
     * @return IntegerProperty of id
     */
    public final IntegerProperty idProperty() {
        return this.id;
    }

    /**
     * @return int of id
     */
    public final int getId() {
        return this.idProperty().get();
    }

    /**
     * Sets a new id
     *
     * @param id
     */
    public final void setId(final int id) {
        this.idProperty().set(id);
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
     * @return BooleanProperty of favourite
     */
    public final BooleanProperty favouriteProperty() {
        return this.favourite;
    }

    /**
     * @return boolean of favourite
     */
    public final boolean isFavourite() {
        return this.favouriteProperty().get();
    }

    /**
     * Sets a new favourite value
     *
     * @param favourite
     */
    public final void setFavourite(final boolean favourite) {
        this.favouriteProperty().set(favourite);
    }

    /**
     * @return BooleanProperty of done
     */
    public final BooleanProperty doneProperty() {
        return this.done;
    }

    /**
     * @return boolean of done
     */
    public final boolean isDone() {
        return this.doneProperty().get();
    }

    /**
     * Sets a new done value
     *
     * @param done
     */
    public final void setDone(final boolean done) {
        this.doneProperty().set(done);
    }

    /**
     * @return BooleanProperty of white
     */
    public final BooleanProperty whiteProperty() {
        return this.white;
    }

    /**
     * @return boolean of white
     */
    public final boolean isWhite() {
        return this.whiteProperty().get();
    }

    /**
     * Sets a new white value
     *
     * @param white
     */
    public final void setWhite(final boolean white) {
        this.whiteProperty().set(white);
    }

    /**
     * @return ObjectProperty of LocalDate of deadline
     */
    public final ObjectProperty<LocalDate> deadLineProperty() {
        return this.deadLine;
    }

    /**
     * @return Localdate of deadline
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
     * @return ObjectProperty of Page of page
     */
    public final ObjectProperty<Page> pageProperty() {
        return this.page;
    }

    /**
     * @return Page of page
     */
    public final Page getPage() {
        return this.pageProperty().get();
    }

    /**
     * Sets a new page
     *
     * @param page
     */
    public final void setPage(final Page page) {
        this.pageProperty().set(page);
    }

    /**
     * @return ObjectProperty of Task parentTask
     */
    public final ObjectProperty<Task> parentTaskProperty() {
        return this.parentTask;
    }

    /**
     * @return Task parentTask
     */
    public final Task getParentTask() {
        return this.parentTaskProperty().get();
    }

    /**
     * Sets a new parentTask
     *
     * @param parentTask
     */
    public final void setParentTask(final Task parentTask) {
        this.parentTaskProperty().set(parentTask);
    }

    /**
     * @return ObjectProperty of Project project
     */
    public final ObjectProperty<Project> projectProperty() {
        return this.project;
    }

    /**
     * @return Project project
     */
    public final Project getProject() {
        return this.projectProperty().get();
    }

    /**
     * Sets a new project to the task
     *
     * @param project
     */
    public final void setProject(final Project project) {
        this.projectProperty().set(project);
    }

    /**
     * @return ObjectProperty of StatusType status
     */
    public final ObjectProperty<StatusType> statusProperty() {
        return this.status;
    }

    /**
     * @return StatusType status
     */
    public final StatusType getStatus() {
        return this.statusProperty().get();
    }

    /**
     * Sets a new status to the task
     *
     * @param status
     */
    public final void setStatus(final StatusType status) {
        this.statusProperty().set(status);
    }

    /**
     * @return ListProperty of Task childTasks
     */
    public final ListProperty<Task> childTasksProperty() {
        return this.childTasks;
    }

    /**
     * @return ObservableList of Task childTasks
     */
    public final ObservableList<Task> getChildTasks() {
        return this.childTasksProperty().get();
    }

    /**
     * Sets a new List of child Tasks
     *
     * @param childTasks
     */
    public final void setChildTasks(final ObservableList<Task> childTasks) {
        this.childTasksProperty().set(childTasks);
    }

    /**
     * @return int pageId
     */
    public int getPageId() {
        return pageId.get();
    }

    /**
     * @return IntegerProperty of pageId
     */
    public IntegerProperty pageIdProperty() {
        return pageId;
    }

    /**
     * Sets a new pageId
     *
     * @param pageId
     */
    public void setPageId(int pageId) {
        this.pageId.set(pageId);
    }

    /**
     * @return LocalDate of creationDate
     */
    public LocalDate getCreationDate() {
        return creationDate.get();
    }

    /**
     * @return ObjectProperty of LocalDate creationDate
     */
    public ObjectProperty<LocalDate> creationDateProperty() {
        return creationDate;
    }

    /**
     * Sets a new creationDate
     *
     * @param date
     */
    public void setCreationDate(LocalDate date) {
        this.creationDate.set(date);
    }

    /**
     * @return LocalDate of completedDate
     */
    public LocalDate getCompletedDate() {
        return completedDate.get();
    }

    /**
     * @return ObjectProperty of LocalDate completedDate
     */
    public ObjectProperty<LocalDate> completedDateProperty() {
        return completedDate;
    }

    /**
     * Sets a new completedDate
     *
     * @param completedDate
     */
    public void setCompletedDate(LocalDate completedDate) {
        this.completedDate.set(completedDate);
    }
}
