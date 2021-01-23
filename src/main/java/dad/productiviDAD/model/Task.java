package dad.productiviDAD.model;

<<<<<<< HEAD
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Task {

	private StringProperty title=new SimpleStringProperty();
	private StringProperty description=new SimpleStringProperty();
	private BooleanProperty completed=new SimpleBooleanProperty();
	
	public Task() {}
	
	public Task(String title, String description, boolean completed) {
		this.title.set(title);
		this.description.set(description);
		this.completed.set(completed);
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
	

	public final BooleanProperty completedProperty() {
		return this.completed;
	}
	

	public final boolean isCompleted() {
		return this.completedProperty().get();
	}
	

	public final void setCompleted(final boolean completed) {
		this.completedProperty().set(completed);
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

	@Override
	public String toString() {
		return this.titleProperty().get();
	}
	
	
	
	
=======
public class Task {

>>>>>>> 6352ca4e6267b87cf7d8a33e65b457111f80d608
}
