package dad.productividad.balanceManager;

import javafx.beans.property.*;

import java.time.LocalDate;

public class IncomeExpense {
	private IntegerProperty id = new SimpleIntegerProperty(); 
	private ObjectProperty<LocalDate> date=new SimpleObjectProperty<>();
	private StringProperty concept=new SimpleStringProperty();
	private DoubleProperty amount=new SimpleDoubleProperty();
	
	public IncomeExpense(LocalDate date, String concept, double amount) {
		this.date.set(date);
		this.concept.set(concept);
		this.amount.set(amount);
	}
	public IncomeExpense() {
		
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
	

	public final void setAmount(final double amount) {
		this.amountProperty().set(amount);
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
		
	
}


