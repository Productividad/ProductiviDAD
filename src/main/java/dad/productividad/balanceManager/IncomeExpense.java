package dad.productividad.balanceManager;

import javafx.beans.property.*;

import java.time.LocalDate;

/**
 * IncomeExpense model
 */
public class IncomeExpense {
    /**
     * Movement id
     */
    private IntegerProperty id = new SimpleIntegerProperty();
    /**
     * Movement Date
     */
    private ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();
    /**
     * Movement concept
     */
    private StringProperty concept = new SimpleStringProperty();
    /**
     * Movement amount
     */
    private DoubleProperty amount = new SimpleDoubleProperty();

    /**
     * IncomeExpense Constructor with parameters
     *
     * @param date    Date of the movement
     * @param concept Concept of the movement
     * @param amount  Amount of the movement
     */
    public IncomeExpense(LocalDate date, String concept, double amount) {
        this.date.set(date);
        this.concept.set(concept);
        this.amount.set(amount);
    }

    /**
     * IncomeExpense empty constructor
     */
    public IncomeExpense() {

    }

    /**
     * @return ObjectProperty of date
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
     * Set a new date
     *
     * @param date
     */
    public final void setDate(final LocalDate date) {
        this.dateProperty().set(date);
    }

    /**
     * @return StringProperty of concept
     */
    public final StringProperty conceptProperty() {
        return this.concept;
    }

    /**
     * @return String of concept
     */
    public final String getConcept() {
        return this.conceptProperty().get();
    }

    /**
     * Set a new concept
     *
     * @param concept
     */
    public final void setConcept(final String concept) {
        this.conceptProperty().set(concept);
    }

    /**
     * @return DoubleProperty of amount
     */
    public final DoubleProperty amountProperty() {
        return this.amount;
    }

    /**
     * @return double of amount
     */
    public final double getAmount() {
        return this.amountProperty().get();
    }

    /**
     * Set a new amount
     *
     * @param amount
     */
    public final void setAmount(final double amount) {
        this.amountProperty().set(amount);
    }

    /**
     * @return IntegerProperty of id
     */
    public final IntegerProperty idProperty() {
        return this.id;
    }

    /**
     * @return integer of id
     */
    public final int getId() {
        return this.idProperty().get();
    }

    /**
     * Set a new id
     *
     * @param id
     */
    public final void setId(final int id) {
        this.idProperty().set(id);
    }


}


