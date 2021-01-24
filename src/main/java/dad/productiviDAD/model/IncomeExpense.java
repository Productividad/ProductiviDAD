package dad.productiviDAD.model;

import java.time.LocalDate;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class IncomeExpense {

	private ObjectProperty<LocalDate> date=new SimpleObjectProperty<>();
	private StringProperty concept=new SimpleStringProperty();
	private DoubleProperty amount=new SimpleDoubleProperty();
	private BooleanProperty isPositive=new SimpleBooleanProperty();
	
	public IncomeExpense(LocalDate date, String concept, double amount, boolean isPositive) {
		this.date.set(date);
		this.concept.set(concept);
		this.amount.set(amount);
		this.isPositive.set(isPositive);
	}

	public final ObjectProperty<LocalDate> dateProperty() {
		return this.date;
	}
	

	public final LocalDate getDate() {
		return this.dateProperty().get();
	}
	

	public final void setDate(final LocalDate date) {
		this.dateProperty().set(date);
	}
	

	public final StringProperty conceptProperty() {
		return this.concept;
	}
	

	public final String getConcept() {
		return this.conceptProperty().get();
	}
	

	public final void setConcept(final String concept) {
		this.conceptProperty().set(concept);
	}
	

	public final DoubleProperty amountProperty() {
		return this.amount;
	}
	

	public final double getAmount() {
		return this.amountProperty().get();
	}
	

	public final void setAmount(final int amount) {
		this.amountProperty().set(amount);
	}
	

	public final BooleanProperty isPositiveProperty() {
		return this.isPositive;
	}
	

	public final boolean isIsPositive() {
		return this.isPositiveProperty().get();
	}
	

	public final void setIsPositive(final boolean isPositive) {
		this.isPositiveProperty().set(isPositive);
	}
	
	
	
	
}
