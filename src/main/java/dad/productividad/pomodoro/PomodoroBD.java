package dad.productividad.pomodoro;

import dad.productividad.page.Page;
import javafx.beans.property.*;

public class PomodoroBD {

	private IntegerProperty id = new SimpleIntegerProperty();
	private ObjectProperty<Page> page = new SimpleObjectProperty<>();
	private StringProperty typeTask = new SimpleStringProperty();
	private IntegerProperty timeSpent = new SimpleIntegerProperty();

	public PomodoroBD() {
	}

	public PomodoroBD(String typeTask, Integer timeSpent) {
		this.typeTask.set(typeTask);
		this.timeSpent.set(timeSpent);
	}

	public final IntegerProperty idProperty() {
		return this.id;
	}

	public final int getId() {
		return this.idProperty().get();
	}

	public final ObjectProperty<Page> pageProperty() {
		return this.page;
	}

	public final Page getPage() {
		return this.pageProperty().get();
	}

	public final void setPage(final Page page) {
		this.pageProperty().set(page);
	}

	public final StringProperty typeTaskProperty() {
		return this.typeTask;
	}

	public final String getTypeTask() {
		return this.typeTaskProperty().get();
	}

	public final void setTypeTask(final String typeTask) {
		this.typeTaskProperty().set(typeTask);
	}

	public final IntegerProperty timeSpentProperty() {
		return this.timeSpent;
	}

	public final int getTimeSpent() {
		return this.timeSpentProperty().get();
	}

	public final void setTimeSpent(final int timeSpent) {
		this.timeSpentProperty().set(timeSpent);
	}

}
