package dad.productividad.note;

import javafx.beans.property.*;

public class Note {

	private StringProperty content = new SimpleStringProperty();
	private IntegerProperty id = new SimpleIntegerProperty();
	private IntegerProperty idPage = new SimpleIntegerProperty();
	private StringProperty color=new SimpleStringProperty();

	public Note() {
		
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
	

	public final IntegerProperty idPageProperty() {
		return this.idPage;
	}
	

	public final int getIdPage() {
		return this.idPageProperty().get();
	}
	

	public final void setIdPage(final int idPage) {
		this.idPageProperty().set(idPage);
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

	

	

}
