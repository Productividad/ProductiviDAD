package dad.productividad.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;

import dad.productividad.app.MainController;
import dad.productividad.dataManager.TableProjectComments;
import dad.productividad.dataManager.TableTasks;
import dad.productividad.task.Task;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *Class controller of projectDetailView 
 *
 */
public class ProjectDetailController implements Initializable {

	@FXML
    private StackPane view;

    @FXML
    private JFXSpinner spinner;

    @FXML
    private Label titleProject,descriptionLabel,titleTaskDetail;
    
    @FXML
    private ScrollPane scrollPane,scrollPaneTaskDetail;

    @FXML
    private VBox taskContainer,taskCommentsWrapper;

    @FXML
    private GridPane dialogAdd,dialogTaskDetail;

    @FXML
    private JFXTextField titleTF;
    
    @FXML
    private HBox commentInserterWrapper;
    
    @FXML
    private Button cancelAddButton;
    	
	private StringProperty title=new SimpleStringProperty(); 
	private StringProperty description=new SimpleStringProperty();
	
	private ObjectProperty<Project> project = new SimpleObjectProperty<>(); 
	private ObjectProperty<Task> taskOnDetailDialog=new SimpleObjectProperty<>();
 
	
    /**
     * ProjectDetailController constructor
     */
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
 
    /**
     * Project detail view initialization
     *
     * @param location
     * @param resources
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) { 
 				
		titleProject.textProperty().bind(title); 
		descriptionLabel.textProperty().bindBidirectional(description);

		
		ProjectCommentInserter inserter =new ProjectCommentInserter();
		commentInserterWrapper.getChildren().add(inserter);
		HBox.setHgrow(inserter, Priority.ALWAYS);
		
		taskOnDetailDialog.addListener((o,ov,nv)->{ 
			inserter.setTask(taskOnDetailDialog.get());
		});
		
		
		project.addListener((o, ov, nv) -> {	
			if (nv != null) {  
				view.getStylesheets().setAll(nv.getStyleSheet());
				title.set(nv.getTitle());
				description.set(nv.getDescription()); 
				setTasksOnTaskContainer();
			}             
		});  


	}   
	
    /**
     * Add tasks to the container
     */
	public void setTasksOnTaskContainer(){
		taskContainer.getChildren().clear();
	
		double tasksOfProject=0.0;
		double completedTasks=0.0;
		
		for(Task task:TableTasks.readParentTasks(project.get())) {
			ProjectTaskComponent taskCard=new ProjectTaskComponent(); 
			taskCard.setTask(task); 
			taskContainer.getChildren().add(taskCard);
			if(task.isDone())
				completedTasks+=1.0;
			tasksOfProject+=1.0;
		}
		spinner.progressProperty().set(((completedTasks*100.0)/tasksOfProject)/100.0);
		
		if(tasksOfProject==0.0) {
				dialogAdd.setVisible(true);
				cancelAddButton.setDisable(true);
		}else
			hideDialog(); 
	}
	
	/**
	 * Set dialogAdd visible
	 * @param event
	 */
	@FXML
	public void onAddTask(ActionEvent event) {
		cancelAddButton.setDisable(false);
		dialogAdd.setVisible(true);
	}
	
	
	/**
	 * Creates a task new task and inserts it in the db.
	 * hide dialogAdd.
	 * 
	 * @param event
	 */
    @FXML
    private void onAcceptDialogAdd(ActionEvent event) {
    	
    	Task task=new Task();
    	task.setTitle(titleTF.textProperty().get());
    	task.setProject(project.get());
    	task.setPage(MainController.getTodaysPage());
    	TableTasks.insert(task);
    	
    	hideDialog();
    	setTasksOnTaskContainer();
    	titleTF.clear();
    }

    /**
     * Action of cancel button on dialogAdd. Hides the dialog
     * @param event
     */
    @FXML
    private void onCancelDialogAdd(ActionEvent event) {
    	hideDialog();
    }
    
    /**
     * Action of close button on dialogTaskDetail. Hides the dialog
     * @param event
     */
    @FXML
    private void onCloseDialogTaskDetail(ActionEvent event) {
    	hideDialog();
    }

    /**
     * Deletes the task on dialogTaskDetail and resets projectTaskDetail
     * @param event
     */
    @FXML
    private void onDeleteDialogTaskDetail(ActionEvent event) {
    	
    	TableTasks.delete(taskOnDetailDialog.get());
    	setTasksOnTaskContainer();
    }
    
    /**
     * Insert the values of the task on the parameter into the taskDetailDialog.
     * Set the task to taskToDelete ObjectProperty
     * @param task
     */
    public void showDialogTaskDetail(Task task) {
    	taskOnDetailDialog.set(task);
    	setProjectComments();
    	titleTaskDetail.textProperty().set(task.getTitle());
    	dialogTaskDetail.setVisible(true);
    }
    
    /**
     * Resets the taskCommentsWrapper and inserts all the projectComment
     * of the task in it
     */
    public void setProjectComments() {
    	taskCommentsWrapper.getChildren().clear();
    	
    	for(ProjectComment comment:TableProjectComments.read(taskOnDetailDialog.get().getId())) {
    		ProjectCommentComponent component=new ProjectCommentComponent();
        	component.setProjectComment(comment);
        	
        	taskCommentsWrapper.getChildren().add(component);
    	} 	
    }
    
    /**
     * Hides dialogAdd and dialogTaskDetail
     */
    private void hideDialog() {
		dialogAdd.setVisible(false);
		dialogTaskDetail.setVisible(false);
    }
    
    /**
     * @return The ProjectDetail view 
     */  
	public StackPane getView() {
		return this.view;
	}

    /**
     * @return ObjectProperty of Project of project variable
     */
	public final ObjectProperty<Project> projectProperty() {
		return this.project;    
	} 
	
    /**
     * @return Project of project variable
     */
	public final Project getProject() { 
		return this.projectProperty().get();
	}
 
    /**
     * Sets a new project
     *
     * @param project
     */
	public final void setProject(final Project project) { 
		this.projectProperty().set(project);
	}
}
