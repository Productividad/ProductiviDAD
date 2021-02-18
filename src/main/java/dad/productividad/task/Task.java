package dad.productividad.task;


import java.time.LocalDate;

import dad.productividad.page.Page;
import dad.productividad.project.Project;
import dad.productividad.segmentedBarUtils.StatusType;
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

    private ListProperty<Task> childTasks = new SimpleListProperty<Task>(FXCollections.observableArrayList());

    public Task() {
    	
    }

    public Task(String title, String description, boolean completed) {
        this.title.set(title);
        this.description.set(description);
        this.done.set(completed);
    }


    @Override
    public String toString() {
        return getTitle();
    }

    public final IntegerProperty idProperty() {
        return this.id;
    }


    public final int getId() {
        return this.idProperty().get();
    }


    public final void setId(final int id) {
        this.idProperty().set(id);
    }


    public final StringProperty titleProperty() {
        return this.title;
    }


    public final String getTitle() {
        return this.titleProperty().get();
    }


    public final void setTitle(final String title) {
        this.titleProperty().set(title);
    }


    public final StringProperty descriptionProperty() {
        return this.description;
    }


    public final String getDescription() {
        return this.descriptionProperty().get();
    }


    public final void setDescription(final String description) {
        this.descriptionProperty().set(description);
    }


    public final StringProperty colorProperty() {
        return this.color;
    }


    public final String getColor() {
        return this.colorProperty().get();
    }


    public final void setColor(final String color) {
        this.colorProperty().set(color);
    }


    public final BooleanProperty favouriteProperty() {
        return this.favourite;
    }


    public final boolean isFavourite() {
        return this.favouriteProperty().get();
    }


    public final void setFavourite(final boolean favourite) {
        this.favouriteProperty().set(favourite);
    }


    public final BooleanProperty doneProperty() {
        return this.done;
    }


    public final boolean isDone() {
        return this.doneProperty().get();
    }


    public final void setDone(final boolean done) {
        this.doneProperty().set(done);
    }


    public final BooleanProperty whiteProperty() {
        return this.white;
    }


    public final boolean isWhite() {
        return this.whiteProperty().get();
    }


    public final void setWhite(final boolean white) {
        this.whiteProperty().set(white);
    }


    public final ObjectProperty<LocalDate> deadLineProperty() {
        return this.deadLine;
    }


    public final LocalDate getDeadLine() {
        return this.deadLineProperty().get();
    }


    public final void setDeadLine(final LocalDate deadLine) {
        this.deadLineProperty().set(deadLine);
    }


    public final ObjectProperty<Page> pageProperty() {
        return this.page;
    }


    public final Page getPage() {
        return this.pageProperty().get();
    }


    public final void setPage(final Page page) {
        this.pageProperty().set(page);
    }


    public final ObjectProperty<Task> parentTaskProperty() {
        return this.parentTask;
    }


    public final Task getParentTask() {
        return this.parentTaskProperty().get();
    }


    public final void setParentTask(final Task parentTask) {
        this.parentTaskProperty().set(parentTask);
    }


    public final ObjectProperty<Project> projectProperty() {
        return this.project;
    }


    public final Project getProject() {
        return this.projectProperty().get();
    }


    public final void setProject(final Project project) {
        this.projectProperty().set(project);
    }


    public final ObjectProperty<StatusType> statusProperty() {
        return this.status;
    }


    public final StatusType getStatus() {
        return this.statusProperty().get();
    }


    public final void setStatus(final StatusType status) {
        this.statusProperty().set(status);
    }


    public final ListProperty<Task> childTasksProperty() {
        return this.childTasks;
    }


    public final ObservableList<Task> getChildTasks() {
        return this.childTasksProperty().get();
    }


    public final void setChildTasks(final ObservableList<Task> childTasks) {
        this.childTasksProperty().set(childTasks);
    }

    public int getPageId() {
        return pageId.get();
    }

    public IntegerProperty pageIdProperty() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId.set(pageId);
    }
}
