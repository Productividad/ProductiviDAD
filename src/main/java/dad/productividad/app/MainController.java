package dad.productividad.app;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import com.dlsc.formsfx.model.util.ResourceBundleService;
import com.dlsc.preferencesfx.PreferencesFx;
import com.dlsc.preferencesfx.model.Category;
import com.dlsc.preferencesfx.model.Group;
import com.dlsc.preferencesfx.model.Setting;

import animatefx.animation.FadeIn;
import animatefx.animation.Shake;
import dad.productividad.balanceManager.BalanceManagerController;
import dad.productividad.dataManager.TablePages;
import dad.productividad.home.HomeController;
import dad.productividad.note.NotesController;
import dad.productividad.page.Page;
import dad.productividad.pomodoro.PomodoroController;
import dad.productividad.project.Project;
import dad.productividad.project.ProjectManagerController;
import dad.productividad.project.projectDetailController;
import dad.productividad.task.Task;
import dad.productividad.task.TaskDetailController;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController implements Initializable {
	static Page todaysPage = new Page();
	// View

	public static Page getTodaysPage() {
		return todaysPage;
	}

	@FXML
	private BorderPane view;

	@FXML
	private AnchorPane centerPane;

	@FXML
	private GridPane topBar;

	@FXML
	private VBox center;

	@FXML
	private ListView<String> listView;
	
	// Controllers

	private ProjectManagerController projectManagerController;
	private NotesController notasController;
	private BalanceManagerController balanceManagerController;
	private projectDetailController projectDetailController;	
	private HomeController homeController;	 
	private PomodoroController pomodoroController;

	public static MainController mainController;
 
	public MainController() {
		MainController.mainController = this;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
			loader.setResources(ResourceBundle.getBundle("i18n/main"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}  
  
	@Override
	public void initialize(URL location, ResourceBundle resources) { 
		
		view.centerProperty().addListener((o,ov,nv)->{
			if(nv!=null) {
				if(view.getRight()!=null) 
					view.setRight(null);
			}
		});
		
		todaysPage.setDate(LocalDate.now());
 
		projectManagerController = new ProjectManagerController();
		notasController = new NotesController();
		balanceManagerController = new BalanceManagerController();
		homeController=new HomeController();
		pomodoroController = new PomodoroController();
		
		view.setCenter(homeController.getView()); 

		if (TablePages.todaysPage())
			TablePages.setID(todaysPage);
		else
			TablePages.insertPage(todaysPage);
	} 

	/**
	 * Set the center of view with projectDetailController.getView().
	 * The received project and stylesheet are assigned to the projectDetailController
	 * 
	 * @param project Project
	 * @param styleSheetPath String
	 */
	public void openProject(Project project, String styleSheetPath) {
		projectDetailController=new projectDetailController();
		projectDetailController.setProject(project);
		
		new FadeIn(projectDetailController.getView()).play();
			
		view.setCenter(projectDetailController.getView());
	}
	
	

	public void setTaskOnRightSide(Task task) {
	
		view.setRight(null);
		TaskDetailController taskDetailController=new TaskDetailController();
		taskDetailController.setTask(task);
		view.setRight(taskDetailController.getView());
	}	
	
	public void updateRightSide(Task task) {
		
		if(view.getRight()!=null) {
			TaskDetailController taskDetailController=new TaskDetailController();
			taskDetailController.setTask(task);
			
			view.setRight(null);
			view.setRight(taskDetailController.getView());
		}
	}
	public void setRightSideNull() {
		view.setRight(null);
	}
	public void updateTaskComponent(Task task) {
		//TODO
	}
	
	
	public BorderPane getView() {
		return this.view;
	}

	public GridPane getTopBar() {
		return this.topBar;
	}

	// TopBar
	@FXML
	private void onCloseWindow(ActionEvent event) {
		Stage stage=(Stage)view.getScene().getWindow();
    	stage.close();
	}

	@FXML
	private void onMaximizeButton(ActionEvent event) {
		Stage stage = (Stage) view.getScene().getWindow();
		stage.setMaximized(!stage.isMaximized());
	}

	@FXML
	private void onMinimizeWindow(ActionEvent event) {
		Stage stage = (Stage) view.getScene().getWindow();
		stage.setIconified(true);
	}

	// LeftMenuBar

	@FXML
	private void onHomeButton(ActionEvent event) {

		if (view.getCenter() == homeController.getView()) 
			new Shake(view.getCenter()).play();
		else {
			new FadeIn(homeController.getView()).play();
			
			view.setCenter(homeController.getView());
		}
	}

	@FXML
	private void onCalendarButton(ActionEvent event) {

	}

	@FXML
	private void onEntryReaderButton(ActionEvent event) {

	}

	@FXML
	private void onProyectManagerButton(ActionEvent event) {

		if(view.getCenter()==projectManagerController.getView())
			new Shake(view.getCenter()).play();
		else {
			new FadeIn(projectManagerController.getView()).play();
			view.setCenter(projectManagerController.getView());
		}
	}

	@FXML
	private void onIdeasButton(ActionEvent event) {

		if (view.getCenter() == notasController.getView())
			new Shake(view.getCenter()).play();
		else {
			new FadeIn(notasController.getView()).play();
			view.setCenter(notasController.getView());
		}
	}

	@FXML
	private void onBalanceManagerButton(ActionEvent event) {

		if (view.getCenter() == balanceManagerController.getView())
			new Shake(view.getCenter()).play();
		else {
			new FadeIn(balanceManagerController.getView()).play();
			view.setCenter(balanceManagerController.getView());
		}
	}

	@FXML
	private void onTimePlannerButton(ActionEvent event) {

		if(view.getCenter()==pomodoroController.getView())
			new Shake(view.getCenter()).play();
		else {
			new FadeIn(pomodoroController.getView()).play();
			view.setCenter(pomodoroController.getView());
		}
	}

	@FXML
	private void onToolsButton(ActionEvent event) throws BackingStoreException {

		//The preferences we want to config
		ResourceBundle rbES = ResourceBundle.getBundle("i18n/preferences", new Locale("es"));
		ResourceBundle rbEN = ResourceBundle.getBundle("i18n/preferences", new Locale("en"));
		ResourceBundle rbFR = ResourceBundle.getBundle("i18n/preferences", new Locale("fr"));
		ResourceBundleService rbs = new ResourceBundleService(rbEN);

		ObservableList themeItems = FXCollections.observableArrayList(Arrays.asList(
				"Dark", "Clear", "Canary"));
		ObservableList<Locale> localeList = FXCollections.observableArrayList(Locale.ENGLISH, new Locale("es"), Locale.FRENCH);
		BooleanProperty booleanProperty = new SimpleBooleanProperty();
		IntegerProperty integerProperty = new SimpleIntegerProperty();
		DoubleProperty doubleProperty = new SimpleDoubleProperty();
		//Creation of the dialog
		PreferencesFx preferencesFx = PreferencesFx.of(App.class,
				Category.of("customization", Setting.of("theme", themeItems, App.themeProperty()),
						Setting.of("language", localeList, App.localeSelectionProperty()), // creates a group
						// automatically
						Setting.of("activate", booleanProperty),
						Setting.of("font_size", App.fontSizeProperty(), 10, 36) // which contains both settings
				)).i18n(rbs).saveSettings(true);
		preferencesFx.dialogIcon(App.primaryStage.getIcons().get(0));
		preferencesFx.show(true);

//		Preferences preferences = Preferences.userNodeForPackage(App.class);
//		preferences.clear();
	}

	@FXML
	private void onGithubButton(ActionEvent event) {
		try {
		    Desktop.getDesktop().browse(new URL("https://github.com/dam-dad/ProductiviDAD").toURI());
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (URISyntaxException e) {
		    e.printStackTrace();
		}
	}
}
