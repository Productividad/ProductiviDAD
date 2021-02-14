package dad.productiviDAD.project;

import dad.productiviDAD.dataManager.TableTasks;
import dad.productiviDAD.segmentedBarUtils.InfoLabel;
import dad.productiviDAD.segmentedBarUtils.StatusType;
import dad.productiviDAD.segmentedBarUtils.TypeSegment;
import dad.productiviDAD.segmentedBarUtils.TypeSegmentView;
import dad.productiviDAD.task.Task;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.controlsfx.control.SegmentedBar;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class projectDetailController implements Initializable {

	@FXML
	private VBox view;

	@FXML
	private SegmentedBar<TypeSegment> segmentedBar = new SegmentedBar<>();

	@FXML
	private HBox taskContainer;
	
	@FXML
	private ScrollPane scroll;
	
	@FXML
	private Label titleProject,descriptionProject;
	
    @FXML
    private Button addTaskButton;
    
	private ObjectProperty<Project> project = new SimpleObjectProperty<>();

	private ListProperty<Task> projectTasks = new SimpleListProperty<>(FXCollections.observableArrayList());
	
	private StringProperty title=new SimpleStringProperty();
	private StringProperty description=new SimpleStringProperty();
	
	private IntegerProperty toDoTasks=new SimpleIntegerProperty(0);
	private IntegerProperty inProgressTasks=new SimpleIntegerProperty(0);
	private IntegerProperty doneTasks=new SimpleIntegerProperty(0); 
	   
	public projectDetailController() {         
		try {   
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectDetailView.fxml"));
			loader.setResources(ResourceBundle.getBundle("i18n/projectdetail"));
			loader.setController(this);
			loader.load();    
		} catch (IOException e) {  
			e.printStackTrace();  
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) { 
 				
		titleProject.textProperty().bind(title);
		descriptionProject.textProperty().bind(description);
		
		segmentedBar.setSegmentViewFactory(TypeSegmentView::new);
		segmentedBar.setInfoNodeFactory(segment -> new InfoLabel((int) segment.getValue() + " Tareas"));
	
		project.addListener((o, ov, nv) -> {
			
			if (nv != null) { 
				
				view.getStylesheets().setAll(nv.getStyleSheet());
				title.set(nv.getTitle());
				description.set(nv.getDescription()); 
				 
				for (Task parentTask : TableTasks.readParentTasks(project.get())) {
					TableTasks.readChildTasks(parentTask);
					ProjectTaskComponent taskCard=new ProjectTaskComponent(); 
					taskCard.setTask(parentTask); 
					taskCard.styleTaskCard();   
					projectTasks.add(parentTask); 
					taskContainer.getChildren().add(taskCard);
				}
 
				for (Task task : projectTasks) { 
					if(task.getStatus().equals(StatusType.TODO)) 
						toDoTasks.set(toDoTasks.get()+1);
					if(task.getStatus().equals(StatusType.IN_PROGRESS)) 
						inProgressTasks.set(inProgressTasks.get()+1);   
					if(task.getStatus().equals(StatusType.DONE))     
						doneTasks.set(doneTasks.get()+1);       
				}       
			}            
			  
			segmentedBar.getSegments().addAll(   
					new TypeSegment(toDoTasks.get(), StatusType.TODO),
					new TypeSegment(inProgressTasks.get(), StatusType.IN_PROGRESS),
					new TypeSegment(doneTasks.get(), StatusType.DONE)
			);
		});  

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
