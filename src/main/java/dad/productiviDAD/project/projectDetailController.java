package dad.productiviDAD.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.SegmentedBar;

import dad.productiviDAD.dataManager.TableProjects;
import dad.productiviDAD.dataManager.TableTasks;
import dad.productiviDAD.segmentedBarUtils.InfoLabel;
import dad.productiviDAD.segmentedBarUtils.StatusType;
import dad.productiviDAD.segmentedBarUtils.TypeSegment;
import dad.productiviDAD.segmentedBarUtils.TypeSegmentView;
import dad.productiviDAD.task.Task;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class projectDetailController implements Initializable {

	@FXML
	private VBox view;

	@FXML
	private SegmentedBar<TypeSegment> segmentedBar = new SegmentedBar<>();

	@FXML
	private FlowPane flowPane;

	private ObjectProperty<Project> project = new SimpleObjectProperty<>();

	private ListProperty<Task> projectTasks = new SimpleListProperty<>(FXCollections.observableArrayList());
	
	private IntegerProperty toDoTasks=new SimpleIntegerProperty();
	private IntegerProperty inProgressTasks=new SimpleIntegerProperty();
	private IntegerProperty doneTasks=new SimpleIntegerProperty();


	public projectDetailController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectDetailView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		project.addListener((o, ov, nv) -> {
			if (nv != null) {
				for (Task parentTask : TableTasks.readParentTasks(project.get())) {
					projectTasks.add(parentTask);
					TableTasks.readChildTasks(parentTask);
				}
				for (Task task : projectTasks) {
					System.out.println(projectTasks.get(0).getId());
					System.out.println(projectTasks.get(0).getChildTasks().get(0).getId());
					System.out.println(projectTasks.get(0).getChildTasks().get(1).getId());
				}
			}

		});
		segmentedBar.setSegmentViewFactory(TypeSegmentView::new);
		segmentedBar.setInfoNodeFactory(segment -> new InfoLabel((int) segment.getValue() + " Tareas"));
		
/*
 * 		TODO 
 * 		Cambiar los números de typeSegment por toDoTasks, inProgressTasks y doneTasks.
 * 		Que el valor de las property provengan de la cantidad de tareas con el status correspondiente
 * 		
 */
		segmentedBar.getSegments().addAll(
				new TypeSegment(5, StatusType.TODO),
				new TypeSegment(3, StatusType.IN_PROGRESS),
				new TypeSegment(9, StatusType.DONE)
		);

	}

	public VBox getView() {
		return this.view;
	}

	public final ObjectProperty<Project> projectProperty() {
		return this.project;
	}

	public final Project getProject() {
		return this.projectProperty().get();
	}

	public final void setProject(final Project project) {
		this.projectProperty().set(project);
	}

}
