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

public class Project {

	private IntegerProperty id=new SimpleIntegerProperty();
	private StringProperty title=new SimpleStringProperty();
	private DoubleProperty progress=new SimpleDoubleProperty();
	private BooleanProperty completed=new SimpleBooleanProperty();
	private BooleanProperty white=new SimpleBooleanProperty();
	private ObjectProperty<LocalDate>deadLine=new SimpleObjectProperty<>();
	private StringProperty color=new SimpleStringProperty();
	private StringProperty description = new SimpleStringProperty();
	
	public Project(String title, double progress, String color, boolean white) {
		this.title.set(title);
		this.progress.set(progress);
		this.color.set(color);
		this.white.set(white);
	}
	
	public Project() {
		
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
	

	public final DoubleProperty progressProperty() {
		return this.progress;
	}
	

	public final double getProgress() {
		return this.progressProperty().get();
	}
	

	public final void setProgress(final double progress) {
		this.progressProperty().set(progress);
	}
	

	public final BooleanProperty CompletedProperty() {
		return this.completed;
	}
	

	public final boolean isCompleted() {
		return this.CompletedProperty().get();
	}
	

	public final void setCompleted(final boolean Completed) {
		this.CompletedProperty().set(Completed);
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
	

	public final StringProperty colorProperty() {
		return this.color;
	}
	

	public final String getColor() {
		return this.colorProperty().get();
	}
	

	public final void setColor(final String color) {
		this.colorProperty().set(color);
	}

	public final IntegerProperty IDProperty() {
		return this.id;
	}
	

	public final int getId() {
		return this.IDProperty().get();
	}
	

	public final void setId(final int id) {
		this.IDProperty().set(id);
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

	public final BooleanProperty WhiteProperty() {
		return this.white;
	}
	

	public final boolean isWhite() {
		return this.WhiteProperty().get();
	}
	

	public final void setWhite(final boolean white) {
		this.WhiteProperty().set(white);
	}
	
		
	
}
