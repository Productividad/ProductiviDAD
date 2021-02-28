package dad.productividad.page;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;

/**
 * Page model
 */
public class Page {
    /**
     * Page id
     */
    private IntegerProperty id = new SimpleIntegerProperty();
    /**
     * Page date
     */
    private ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();

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
     * @return ObjectProperty of date (LocalDate)
     */
    public final ObjectProperty<LocalDate> dateProperty() {
        return this.date;
    }

    /**
     * @return LocalDate of date
     */
    public final LocalDate getDate() {
        return this.dateProperty().get();
    }

    /**
     * Sets a new date
     *
     * @param date
     */
    public final void setDate(final LocalDate date) {
        this.dateProperty().set(date);
    }

}
