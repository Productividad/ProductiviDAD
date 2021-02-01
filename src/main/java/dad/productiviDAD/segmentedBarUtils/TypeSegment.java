package dad.productiviDAD.segmentedBarUtils;

import org.controlsfx.control.SegmentedBar;

public class TypeSegment extends SegmentedBar.Segment {
	
	private StatusType type;

	public TypeSegment(double value, StatusType type) {
		super(value);
		this.type=type;

		switch(type) {
			case TODO:
				setText("Pendiente");
				break;
			case IN_PROGRESS:
				setText("En progreso");
				break;
			case DONE:
				setText("Finalizado");
				break;
		}
	}
	
	public StatusType getType(){
		return this.type;
	}
	

}
