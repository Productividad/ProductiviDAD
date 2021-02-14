package dad.productiviDAD.segmentedBarUtils;

import org.controlsfx.control.SegmentedBar;

import java.util.ResourceBundle;

public class TypeSegment extends SegmentedBar.Segment {
	
	private StatusType type;

	public TypeSegment(double value, StatusType type) {
		super(value);
		this.type=type;

		switch(type) {
			case TODO:
				setText(ResourceBundle.getBundle("i18n/segmentedbar").getString("todo"));
				break;
			case IN_PROGRESS:
				setText(ResourceBundle.getBundle("i18n/segmentedbar").getString("inprogress"));
				break;
			case DONE:
				setText(ResourceBundle.getBundle("i18n/segmentedbar").getString("done"));
				break;
		}
	}
	
	public StatusType getType(){
		return this.type;
	}
	

}
