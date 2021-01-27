package dad.productiviDAD.controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import dad.productiviDAD.model.Project;
import dad.productiviDAD.utils.CSSUtils;
import dad.productiviDAD.utils.ResourceUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
 
public class ProjectCard extends VBox implements Initializable {

	private StringProperty title=new SimpleStringProperty();
	private StringProperty progress=new SimpleStringProperty();
	
    @FXML
    private Label projectTitleLabel;

    @FXML
    private Label percentageLabel;
 

    public ProjectCard() {
    	super();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectCardComponent.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
		} catch (IOException e) {e.printStackTrace();}
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		projectTitleLabel.textProperty().bindBidirectional(title);
		percentageLabel.textProperty().bindBidirectional(progress);		
	}
	

    @FXML
    private void onAccessProject(ActionEvent event) {

    }

    @FXML
    private void onDeleteProject(ActionEvent event) {

    } 
 
    @FXML
    private void onEditProject(ActionEvent event) {

    }
   
	public final StringProperty titleProperty()  {
		return this.title;
	}
		
	//TODO Terminar método
	public void prepareProjectCard(Project project){
		
		title.set(project.getTitle());
		progress.set(project.getProgress()+"%");
		 
		String textColor="white";
		if(!project.isIsWhite())
			textColor="black";

		Map<String, String> params = new HashMap<>();
		params.put("cardColor", project.getColor());
		params.put("textColor", textColor);
		
		this.getStylesheets().setAll(CSSUtils.generateCss("/css/ProjectCardStyle.txt", params));
	}

	public final String getTitle() {
		return this.titleProperty().get();
	}
	

	public final void setTitle(final String title) {
		this.titleProperty().set(title);
	}
	

	public final StringProperty percentageProperty() {
		return this.progress;
	}
	

	public final String getPercentage() {
		return this.percentageProperty().get();
	}
	

	public final void setPercentage(final String percentage) {
		this.percentageProperty().set(percentage);
	}
	
    
    
    
}
