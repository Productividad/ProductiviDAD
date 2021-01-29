package dad.productiviDAD.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Note {

	private StringProperty title = new SimpleStringProperty();
	private StringProperty content = new SimpleStringProperty();
	private IntegerProperty id = new SimpleIntegerProperty();
	private IntegerProperty idPage = new SimpleIntegerProperty();

	public final StringProperty titleProperty() {
		return this.title;
	}

	public final String getTitle() {
		return this.titleProperty().get();
	}

	public final void setTitle(final String title) {
		this.titleProperty().set(title);
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

}
