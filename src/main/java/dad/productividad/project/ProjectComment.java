package dad.productividad.project;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProjectComment {
    
	private StringProperty content = new SimpleStringProperty();
    private IntegerProperty id = new SimpleIntegerProperty();
    private IntegerProperty idProject = new SimpleIntegerProperty();

    public ProjectComment() {
    	
    }
    
    public StringProperty contentProperty() {
    	return this.content;
    }

    public String getContent() {
    	return this.contentProperty().get();
    }

    public void setContent(String content) {
    	this.contentProperty().set(content);
    }

    public IntegerProperty idProperty() { 
    	return this.id;
    }

    public int getId() { 
    	return this.idProperty().get(); 
    }

    public void setId(int id) { 
    	this.idProperty().set(id);
    }

    public IntegerProperty idProjectProperty() { 
    	return this.idProject; 
    }

    public int getIdProject() { 
    	return this.idProjectProperty().get(); 
    }

    public void setIdProject(final int idProject) {
    	this.idProjectProperty().set(idProject);
    }
}
