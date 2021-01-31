package dad.productiviDAD.task;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class TaskManagerController implements Initializable {

	@FXML
	private VBox view;

	@FXML
	private ListView<Task> listView;

	@FXML
	private JFXTextField titleFTF;

	@FXML
	private JFXTextArea descriptionFTA;

	private ListProperty<Task> taskObservableList = new SimpleListProperty<>(FXCollections.observableArrayList());

	private ObjectProperty<Task> selectedTask = new SimpleObjectProperty<>();

	public TaskManagerController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TaskManagerView.fxml"));
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		taskObservableList.addAll(new Task("Tarea 1", "Descripcion 1", true),
				new Task("Tarea 2", "Descripcion 2", true), new Task("Tarea 3", "Descripcion 3", true),
				new Task("Tarea 4", "Descripcion 4", true), new Task("Tarea 5", "Descripcion 5", true),
				new Task("Tarea 6", "Descripcion 6", true), new Task("Tarea 7", "Descripcion 7", true),
				new Task("Tarea 8", "Descripcion 8", true), new Task("Tarea 9", "Descripcion 9", true),
				new Task("Tarea 10", "Descripcion 10", true), new Task("Tarea 11", "Descripcion 11", true));

		listView.setItems(taskObservableList.get());
		selectedTask.bind(listView.getSelectionModel().selectedItemProperty());
		selectedTask.addListener((o, ov, nv) -> {
			titleFTF.textProperty().set(selectedTask.get().getTitle());
			descriptionFTA.textProperty().set(selectedTask.get().getDescription());
		});
	}

	public VBox getView() {
		return this.view;
	}

	@FXML
	void cosa(ActionEvent event) {
	}
}
