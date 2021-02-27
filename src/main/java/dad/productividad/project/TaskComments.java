package dad.productividad.project;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TaskComments {
    private StringProperty content = new SimpleStringProperty();
    private IntegerProperty id = new SimpleIntegerProperty();
    private IntegerProperty idTask = new SimpleIntegerProperty();

    public StringProperty contentProperty() {return this.content;}

    public String getContent() {return this.contentProperty().get();}

    public void setContent(String content) {this.contentProperty().set(content);}

    public IntegerProperty idProperty() { return this.id;}

    public int getId() { return this.idProperty().get(); }

    public void setId(int id) { this.idProperty().set(id);}

    public IntegerProperty idTaskProperty() { return this.idTask; }

    public int getIdTask() { return this.idTaskProperty().get(); }

    public void setIdTask(final int idTask) { this.idTaskProperty().set(idTask);}
}
