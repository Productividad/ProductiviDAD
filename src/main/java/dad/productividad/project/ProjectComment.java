package dad.productividad.project;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProjectComment {
    
	private StringProperty content = new SimpleStringProperty();
    private IntegerProperty id = new SimpleIntegerProperty();
    private IntegerProperty idTask = new SimpleIntegerProperty();

    public ProjectComment() {
    	
    }

	public final StringProperty contentProperty() {
		return this.content;
	}
	

	public final String getContent() {
		return this.contentProperty().get();
	}
	

	public final void setContent(final String content) {
		this.contentProperty().set(content);
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
	

	public final IntegerProperty idTaskProperty() {
		return this.idTask;
	}
	

	public final int getIdTask() {
		return this.idTaskProperty().get();
	}
	

	public final void setIdTask(final int idTask) {
		this.idTaskProperty().set(idTask);
	}
	

}
