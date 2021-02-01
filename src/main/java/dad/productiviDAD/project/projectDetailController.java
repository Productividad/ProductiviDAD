package dad.productiviDAD.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.SegmentedBar;

import dad.productiviDAD.segmentedBarUtils.InfoLabel;
import dad.productiviDAD.segmentedBarUtils.StatusType;
import dad.productiviDAD.segmentedBarUtils.TypeSegment;
import dad.productiviDAD.segmentedBarUtils.TypeSegmentView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class projectDetailController implements Initializable{

	@FXML
	private VBox view;
	
	@FXML
    private SegmentedBar<TypeSegment> segmentedBar = new SegmentedBar<>();
	
	@FXML
	private FlowPane flowPane;	
 
    
    public projectDetailController() {
    	try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/ProjectDetailView.fxml"));
			loader.setController(this);
			loader.load();  
		} catch (IOException e) {e.printStackTrace();}  
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		segmentedBar.setSegmentViewFactory(TypeSegmentView::new);
		segmentedBar.setInfoNodeFactory(segment->new InfoLabel((int)segment.getValue()+" Tareas"));
		segmentedBar.getSegments().addAll( 
				new TypeSegment(5,StatusType.TODO),
				new TypeSegment(3,StatusType.IN_PROGRESS),
				new TypeSegment(9, StatusType.DONE)
		);

	}

	public VBox getView() {
		return this.view;
	}
}
