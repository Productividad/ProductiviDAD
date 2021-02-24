package dad.productividad.theme;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Theme {

	private StringProperty title=new SimpleStringProperty();
	
	private StringProperty color0=new SimpleStringProperty();
	private StringProperty color1=new SimpleStringProperty();
	private StringProperty color2=new SimpleStringProperty();
	private StringProperty color3=new SimpleStringProperty();
	private StringProperty color4=new SimpleStringProperty();
	private StringProperty color5=new SimpleStringProperty();

	private StringProperty path=new SimpleStringProperty();
	
	public Theme() {
		
	}

	public final StringProperty titleProperty() {
		return this.title;
	}
	

	public final String getTitle() {
		return this.titleProperty().get();
	}
	

	public final void setTitle(final String title) {
		this.titleProperty().set(title);
	}
	

	public final StringProperty color0Property() {
		return this.color0;
	}
	

	public final String getColor0() {
		return this.color0Property().get();
	}
	

	public final void setColor0(final String color0) {
		this.color0Property().set(color0);
	}
	

	public final StringProperty color1Property() {
		return this.color1;
	}
	

	public final String getColor1() {
		return this.color1Property().get();
	}
	

	public final void setColor1(final String color1) {
		this.color1Property().set(color1);
	}
	

	public final StringProperty color2Property() {
		return this.color2;
	}
	

	public final String getColor2() {
		return this.color2Property().get();
	}
	

	public final void setColor2(final String color2) {
		this.color2Property().set(color2);
	}
	

	public final StringProperty color3Property() {
		return this.color3;
	}
	

	public final String getColor3() {
		return this.color3Property().get();
	}
	

	public final void setColor3(final String color3) {
		this.color3Property().set(color3);
	}
	

	public final StringProperty color4Property() {
		return this.color4;
	}
	

	public final String getColor4() {
		return this.color4Property().get();
	}
	

	public final void setColor4(final String color4) {
		this.color4Property().set(color4);
	}
	

	public final StringProperty color5Property() {
		return this.color5;
	}
	

	public final String getColor5() {
		return this.color5Property().get();
	}
	

	public final void setColor5(final String color5) {
		this.color5Property().set(color5);
	}
	
	public final StringProperty pathProperty() {
		return this.path;
	}
	

	public final String getPath() {
		return this.pathProperty().get();
	}
	

	public final void setPath(final String path) {
		this.pathProperty().set(path);
	}
	
	public void setPalette(String color0,String color1,String color2, String color3, String color4, String color5) {
		
		this.color0.set(color0);
		this.color1.set(color1);
		this.color2.set(color2);
		this.color3.set(color3);
		this.color4.set(color4);
		this.color5.set(color5);
	}

}
