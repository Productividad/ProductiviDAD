package dad.productividad.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.SegmentedBar;

import dad.productividad.dataManager.TableTasks;
import dad.productividad.segmentedBarUtils.InfoLabel;
import dad.productividad.segmentedBarUtils.StatusType;
import dad.productividad.segmentedBarUtils.TypeSegment;
import dad.productividad.segmentedBarUtils.TypeSegmentView;
import dad.productividad.task.Task;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ProjectDetailController implements Initializable {

	@FXML
	private GridPane view;

	@FXML
	private SegmentedBar<TypeSegment> segmentedBar = new SegmentedBar<>();

	@FXML 
	private VBox taskContainer;
	 
	@FXML
	private HBox addProjectSection;
	
	@FXML 
	private ScrollPane scrollPane;
	
	@FXML
	private Label titleProject; 
    
	private ObjectProperty<Project> project = new SimpleObjectProperty<>();

	private ListProperty<Task> toDoTasksLP = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<Task> inProgressTasksLP = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<Task> doneTasksLP = new SimpleListProperty<>(FXCollections.observableArrayList());

	
	private StringProperty title=new SimpleStringProperty();
	private StringProperty description=new SimpleStringProperty();
	
	private IntegerProperty toDoTaskCounter=new SimpleIntegerProperty(0);
	private IntegerProperty inProgressTaskCounter=new SimpleIntegerProperty(0);
	private IntegerProperty doneTaskCounter=new SimpleIntegerProperty(0); 
	   
	public ProjectDetailController() { 
		try {    
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectDetailView.fxml"));
			loader.setResources(ResourceBundle.getBundle("i18n/strings"));
			loader.setController(this);
			loader.load();    
		} catch (IOException e) {  
			e.printStackTrace();   
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) { 
 				
		scrollPane.setFitToWidth(true);
 
		titleProject.textProperty().bind(title);
		
		segmentedBar.setSegmentViewFactory(TypeSegmentView::new);
		segmentedBar.setInfoNodeFactory(segment -> new InfoLabel((int) segment.getValue() + " Tareas"));
	
		project.addListener((o, ov, nv) -> {	
			if (nv != null) {  
				view.getStylesheets().setAll(nv.getStyleSheet());
				title.set(nv.getTitle());
				description.set(nv.getDescription()); 
				setTasksOnTaskContainer();
			}            
			  
			segmentedBar.getSegments().addAll(   
					new TypeSegment(toDoTaskCounter.get(), StatusType.TODO),
					new TypeSegment(inProgressTaskCounter.get(), StatusType.IN_PROGRESS),
					new TypeSegment(doneTaskCounter.get(), StatusType.DONE)
			);
		});  

		addProjectSection.setOnMouseClicked(event->onAddProjectSectionClicked());
		
	}    

	private void onAddProjectSectionClicked() {
		System.out.println("clicked");
	}
	
	public void setTasksOnTaskContainer(){
		taskContainer.getChildren().clear();
		
		for (Task parentTask : TableTasks.readParentTasks(project.get())) {
			TableTasks.readChildTasks(parentTask);    
			     
			if (parentTask.getStatus().equals(StatusType.TODO)) {  
				toDoTasksLP.add(parentTask);
				toDoTaskCounter.set(toDoTaskCounter.get()+1);
			}
			if(parentTask.getStatus().equals(StatusType.IN_PROGRESS)) {
				inProgressTasksLP.add(parentTask);
				inProgressTaskCounter.set(inProgressTaskCounter.get()+1);   
			}
			if(parentTask.getStatus().equals(StatusType.DONE)) {
				doneTasksLP.add(parentTask);
				doneTaskCounter.set(doneTaskCounter.get()+1);  
			} 
		}   
		
		for(Task toDoTask:toDoTasksLP) {
			ProjectTaskComponent taskCard=new ProjectTaskComponent(); 
			taskCard.setTask(toDoTask); 
			taskContainer.getChildren().add(taskCard);
		}
//		
//		for(Task inProgressTask:inProgressTasksLP) {
//			ProjectTaskComponent taskCard=new ProjectTaskComponent(); 
//			taskCard.setTask(inProgressTask); 
//			taskCard.styleTaskCard(); 
//			taskContainer.getChildren().add(taskCard);
//		}
//		
//		for(Task doneTask:doneTasksLP) {
//			ProjectTaskComponent taskCard=new ProjectTaskComponent(); 
//			taskCard.setTask(doneTask); 
//			taskCard.styleTaskCard(); 
//			taskContainer.getChildren().add(taskCard);
//		}
	}
	
	public GridPane getView() {
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
