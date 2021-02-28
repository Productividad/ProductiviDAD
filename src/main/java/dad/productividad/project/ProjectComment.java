package dad.productividad.project;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Project Comment model
 */
public class ProjectComment {

    private StringProperty content = new SimpleStringProperty();
    private IntegerProperty id = new SimpleIntegerProperty();
    private IntegerProperty idTask = new SimpleIntegerProperty();

    /**
     * ProjectComment default constructor
     */
    public ProjectComment() {
    	
    }

    /**
     * @return StringProperty of content
     */
    public final StringProperty contentProperty() {
        return this.content;
    }

    /**
     * @return String of content
     */
    public final String getContent() {
        return this.contentProperty().get();
    }

    /**
     * Sets new content
     *
     * @param content
     */
    public final void setContent(final String content) {
        this.contentProperty().set(content);
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
     * @return IntegerProperty of idTask
     */
    public final IntegerProperty idTaskProperty() {
        return this.idTask;
    }

    /**
     * @return int of idTask
     */
    public final int getIdTask() {
        return this.idTaskProperty().get();
    }

    /**
     * Sets a new idTask
     *
     * @param idTask
     */
    public final void setIdTask(final int idTask) {
        this.idTaskProperty().set(idTask);
    }
}
