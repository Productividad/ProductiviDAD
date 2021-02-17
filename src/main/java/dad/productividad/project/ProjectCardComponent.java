package dad.productividad.project;

import dad.productividad.app.MainController;
import dad.productividad.dataManager.TableProjects;
import dad.productividad.utils.CSSUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


public class ProjectCardComponent extends VBox implements Initializable {

	private StringProperty title=new SimpleStringProperty();
	private StringProperty progress=new SimpleStringProperty();
	private ObjectProperty<Project>project=new SimpleObjectProperty<>();
	private StringProperty styleSheetPath=new SimpleStringProperty();
	 
    @FXML
    private Label projectTitleLabel;

    @FXML
    private Label percentageLabel;  
  
    public ProjectCardComponent() {
    	super();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectCardComponent.fxml"));
			loader.setResources(ResourceBundle.getBundle("i18n/strings"));
			loader.setController(this);
			loader.setRoot(this); 
			loader.load();
		} catch (IOException e) {e.printStackTrace();}
    } 
	 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		project.addListener((o,ov,nv)->{
			if (nv!=null) {
				title.bindBidirectional(nv.titleProperty());
				progress.bind(nv.progressProperty().asString());
				styleProjectCard();
			}
			
		}); 
		 
		projectTitleLabel.textProperty().bindBidirectional(title); 
		percentageLabel.textProperty().bind(progress);		
	}
	
    @FXML
    private void onAccessProject(ActionEvent event) {
    	MainController.mainController.openProject(project.get(),styleSheetPath.get());
    }

    @FXML
    private void onDeleteProject(ActionEvent event) {
    	TableProjects.delete(getProject());
    	
    } 
 
    @FXML
    private void onEditProject(ActionEvent event) {

    }
	/**
	 * Check the color values from a project and applies it to a projectCard
	 * @param project 
	 */
	private void styleProjectCard(){

		String textColor="white";
		if(!getProject().isWhite())
			textColor="black";

		Map<String, String> params = new HashMap<>();
		params.put("cardColor", getProject().getColor());
		params.put("textColor", textColor);
		
		styleSheetPath.set(CSSUtils.generateCss("/css/ProjectCardStyle.txt", params));
		
		project.get().setStyleSheet(styleSheetPath.get());
		this.getStylesheets().setAll(styleSheetPath.get());
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