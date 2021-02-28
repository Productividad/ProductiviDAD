package dad.productividad.project;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Task Comments model
 */
public class TaskComments {
    private StringProperty content = new SimpleStringProperty();
    private IntegerProperty id = new SimpleIntegerProperty();
    private IntegerProperty idTask = new SimpleIntegerProperty();

    /**
     * @return StringProperty of content
     */
    public StringProperty contentProperty() {
        return this.content;
    }

    /**
     * @return String of content
     */
    public String getContent() {
        return this.contentProperty().get();
    }

    /**
     * Sets a new content
     *
     * @param content
     */
    public void setContent(String content) {
        this.contentProperty().set(content);
    }

    /**
     * @return IntegerProperty of id
     */
    public IntegerProperty idProperty() {
        return this.id;
    }

    /**
     * @return int of id
     */
    public int getId() {
        return this.idProperty().get();
    }

    /**
     * Sets a new id
     *
     * @param id
     */
    public void setId(int id) {
        this.idProperty().set(id);
    }

    /**
     * @return IntegerProperty of idTask
     */
    public IntegerProperty idTaskProperty() {
        return this.idTask;
    }

    /**
     * @return int of idTask
     */
    public int getIdTask() {
        return this.idTaskProperty().get();
    }

    /**
     * Sets a new idTask
     *
     * @param idTask
     */
    public void setIdTask(final int idTask) {
        this.idTaskProperty().set(idTask);
    }
}
