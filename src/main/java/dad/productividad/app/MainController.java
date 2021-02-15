package dad.productividad.app;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ResourceBundle;

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
	private TaskDetailController taskDetailController;

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
		
		todaysPage.setDate(LocalDate.now());
 
		projectManagerController = new ProjectManagerController();
		notasController = new NotesController();
		balanceManagerController = new BalanceManagerController();
		homeController=new HomeController();
		taskDetailController=new TaskDetailController();
		
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
	
	
	/**
	 * Set the right side of the view with taskDetailController.getView().
	 * The received task is assigned to the taskDetailController
	 * 
	 * If the right side of the view is not null checks if the previus task of taskDetailController
	 * is the same as the received. If true the right side of the view is setted null
	 * 
	 * @param task Task
	 */
	public void openTask(Task task) {
		
		if(view.getRight()==null) {
			taskDetailController.setTask(task);
			view.setRight(taskDetailController.getView());
		}
		else if(view.getRight()==taskDetailController.getView()) {
				if(taskDetailController.getTask()==task)
					view.setRight(null);
				else
					taskDetailController.setTask(task);
			}
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

	}

	@FXML
	private void onToolsButton(ActionEvent event) {

		ObservableList themeItems = FXCollections.observableArrayList(Arrays.asList(
				"Dark", "Clear", "Canary"));
		ObjectProperty themeSelection = new SimpleObjectProperty<>("Canary");
		BooleanProperty booleanProperty = new SimpleBooleanProperty(true);
		IntegerProperty integerProperty = new SimpleIntegerProperty(12);
		DoubleProperty doubleProperty = new SimpleDoubleProperty(6.5);
		IntegerProperty fontSize = new SimpleIntegerProperty(12);

		PreferencesFx preferencesFx = PreferencesFx.of(App.class, // Save class (will be used to reference saved values of
																	// Settings to)
				Category.of("Customization", Setting.of("Theme", themeItems, themeSelection), // creates a group
																								// automatically
						Setting.of("Activate me", booleanProperty), Setting.of("Font Size", fontSize, 10, 36) // which contains both settings
				), Category.of("Miscellaneous").expand() // Expand the parent category in the tree-view
						.subCategories( // adds a subcategory to "Category title 2"
								Category.of("Category title 3",
										Group.of("Group title 1", Setting.of("Setting title 3", integerProperty)), Group.of( // group
																																// without
																																// title
												Setting.of("Setting title 3", doubleProperty)))));
		preferencesFx.dialogIcon(App.primaryStage.getIcons().get(0));
		preferencesFx.show(true);
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
