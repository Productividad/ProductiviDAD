package dad.productiviDAD.model;

import java.time.LocalDate;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Project {

	private IntegerProperty primaryKey=new SimpleIntegerProperty();
	private StringProperty title=new SimpleStringProperty();
	private IntegerProperty progress=new SimpleIntegerProperty();
	private BooleanProperty isCompleted=new SimpleBooleanProperty();
	private BooleanProperty isWhite=new SimpleBooleanProperty();
	private ObjectProperty<LocalDate>deadLine=new SimpleObjectProperty<>();
	private StringProperty color=new SimpleStringProperty();
	
	public Project(String title, int progress, String color, boolean isWhite) {
		this.title.set(title);
		this.progress.set(progress);
		this.color.set(color);
		this.isWhite.set(isWhite);
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
	

	public final IntegerProperty progressProperty() {
		return this.progress;
	}
	

	public final int getProgress() {
		return this.progressProperty().get();
	}
	

	public final void setProgress(final int progress) {
		this.progressProperty().set(progress);
	}
	

	public final BooleanProperty isCompletedProperty() {
		return this.isCompleted;
	}
	

	public final boolean isIsCompleted() {
		return this.isCompletedProperty().get();
	}
	

	public final void setIsCompleted(final boolean isCompleted) {
		this.isCompletedProperty().set(isCompleted);
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

	public final BooleanProperty isWhiteProperty() {
		return this.isWhite;
	}
	

	public final boolean isIsWhite() {
		return this.isWhiteProperty().get();
	}
	

	public final void setIsWhite(final boolean isWhite) {
		this.isWhiteProperty().set(isWhite);
	}

	public final IntegerProperty primaryKeyProperty() {
		return this.primaryKey;
	}
	

	public final int getPrimaryKey() {
		return this.primaryKeyProperty().get();
	}
	

	public final void setPrimaryKey(final int primaryKey) {
		this.primaryKeyProperty().set(primaryKey);
	}	
}
