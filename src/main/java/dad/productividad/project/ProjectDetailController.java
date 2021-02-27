package dad.productividad.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;

import dad.productividad.app.MainController;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

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
    private Button cancelAddButton;
    	
	private StringProperty title=new SimpleStringProperty(); 
	private StringProperty description=new SimpleStringProperty();
	
	private ObjectProperty<Project> project = new SimpleObjectProperty<>(); 
	   
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
 				
		titleProject.textProperty().bind(title); 
		descriptionLabel.textProperty().bindBidirectional(description);

		project.addListener((o, ov, nv) -> {	
			if (nv != null) {  
				view.getStylesheets().setAll(nv.getStyleSheet());
				title.set(nv.getTitle());
				description.set(nv.getDescription()); 
				setTasksOnTaskContainer();
			}             
		});  
		
	}     
	 
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
	
	@FXML
	public void onAddTask(ActionEvent event) {
		cancelAddButton.setDisable(false);
		dialogAdd.setVisible(true);
	}
	
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

    @FXML
    private void onCancelDialogAdd(ActionEvent event) {
    	hideDialog();
    }
    
    @FXML
    private void onCloseDialogTaskDetail(ActionEvent event) {

    }

    @FXML
    private void onDeleteDialogTaskDetail(ActionEvent event) {

    }
    
    public void showDialogTaskDetail(Task task) {
    	//TODO componente y meterlo
    	
    	dialogTaskDetail.setVisible(true);
    }
    
    private void hideDialog() {
		dialogAdd.setVisible(false);
		dialogTaskDetail.setVisible(false);
    }
	public StackPane getView() {
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
