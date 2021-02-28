package dad.productividad.note;

import javafx.beans.property.*;

/**
 * Note model
 */
public class Note {
    /**
     * Note content
     */
    private StringProperty content = new SimpleStringProperty();
    /**
     * Note id
     */
    private IntegerProperty id = new SimpleIntegerProperty();
    /**
     * Note page id
     */
    private IntegerProperty idPage = new SimpleIntegerProperty();
    /**
     * Note color
     */
    private StringProperty color = new SimpleStringProperty();

    /**
     * Note default constructor
     */
    public Note() {

    }

    /**
     * @return StringProperty of the content
     */
    public final StringProperty contentProperty() {
        return this.content;
    }

    /**
     * @return String of the content
     */
    public final String getContent() {
        return this.contentProperty().get();
    }

    /**
     * Sets the content of the note
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
     * @return int of the id
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
     * @return IntegerProperty of page
     */
    public final IntegerProperty idPageProperty() {
        return this.idPage;
    }

    /**
     * @return int of the page
     */
    public final int getIdPage() {
        return this.idPageProperty().get();
    }

    /**
     * Sets a new id
     */
    public final void setIdPage(final int idPage) {
        this.idPageProperty().set(idPage);
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


}
