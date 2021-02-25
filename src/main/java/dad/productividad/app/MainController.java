package dad.productividad.app;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

import animatefx.animation.FadeIn;
import dad.productividad.balanceManager.BalanceManagerController;
import dad.productividad.dataManager.TablePages;
import dad.productividad.home.HomeController;
import dad.productividad.menuBar.MenuBarController;
import dad.productividad.note.NotesController;
import dad.productividad.page.Page;
import dad.productividad.pomodoro.PomodoroController;
import dad.productividad.project.Project;
import dad.productividad.project.ProjectDetailController;
import dad.productividad.project.ProjectManagerController;
import dad.productividad.settings.SettingsController;
import dad.productividad.task.Task;
import dad.productividad.task.TaskDetailController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController implements Initializable {

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

	@FXML
	private Button closeButton, maximizeButton, minimizeButton;
	
	static Page todaysPage = new Page();
	
	private double x;
	private double y; 
	private boolean lastValueYCoodIs0;
 
	private final KeyCombination homeShortcut=new KeyCodeCombination(KeyCode.DIGIT1, KeyCombination.ALT_DOWN);
	private final KeyCombination calendarShortcut=new KeyCodeCombination(KeyCode.DIGIT2, KeyCombination.ALT_DOWN);
	private final KeyCombination projectManagerShortcut=new KeyCodeCombination(KeyCode.DIGIT3, KeyCombination.ALT_DOWN);
	private final KeyCombination notesShortcut=new KeyCodeCombination(KeyCode.DIGIT4, KeyCombination.ALT_DOWN);
	private final KeyCombination balanceShortcut=new KeyCodeCombination(KeyCode.DIGIT5, KeyCombination.ALT_DOWN);
	private final KeyCombination pomodoroShortcut=new KeyCodeCombination(KeyCode.DIGIT6, KeyCombination.ALT_DOWN);
	private final KeyCombination settingsShortcut=new KeyCodeCombination(KeyCode.DIGIT7, KeyCombination.ALT_DOWN);
	
	private ProjectManagerController projectManagerController;
	private NotesController notasController;
	private BalanceManagerController balanceManagerController;
	private ProjectDetailController projectDetailController;	
	private HomeController homeController;	 
	private PomodoroController pomodoroController;
	private SettingsController settingsController;
	private MenuBarController menuBarController;

	public static MainController mainController; 
	 
	public MainController() { 
		MainController.mainController = this; 
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
			loader.setResources(ResourceBundle.getBundle("i18n/strings", Locale.getDefault()));
			loader.setController(this);
			loader.load();
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 
		view.getStylesheets().add(getClass().getResource(App.preferences.getTheme()).toExternalForm());		
				
		view.centerProperty().addListener((o,ov,nv)->{
			if(nv!=null) {
				if(view.getRight()!=null)  
					view.setRight(null);
			}
		}); 
		
		view.setOnKeyPressed(new EventHandler<KeyEvent>() { 
			@Override
			public void handle(KeyEvent e) {
				if (e.getCode() == KeyCode.ALT) 
	            	MainController.mainController.getMenuBarController().showTagShortcut();
	            if(homeShortcut.match(e)) 
	            	menuBarController.onHomeManagerSection();
	            if(calendarShortcut.match(e))
	            	menuBarController.onTimePlannerManagerSection();
	            if(projectManagerShortcut.match(e))
	            	menuBarController.onProjectManagerSection();
	            if(balanceShortcut.match(e)) 
	            	menuBarController.onBalanceManagerSection();
	            if(notesShortcut.match(e))
	            	menuBarController.onNotesManagerSection();
	            if(pomodoroShortcut.match(e))
	            	menuBarController.onPomodoroManagerSection();
	            if(settingsShortcut.match(e))
	            	menuBarController.onSettingsManagerSection();
			}
		});   
		 
		view.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				if (e.getCode() == KeyCode.ALT) 
		           	MainController.mainController.getMenuBarController().hideTagShortcut();
		        
			}
		}); 
		
		projectManagerController = new ProjectManagerController(); 
		notasController = new NotesController();
		balanceManagerController = new BalanceManagerController();
		homeController=new HomeController();
		pomodoroController = new PomodoroController();
		settingsController = new SettingsController();
		menuBarController=new MenuBarController();
		view.setLeft(menuBarController.getView());
		view.setCenter(homeController.getView());  
		  
		todaysPage.setDate(LocalDate.now());
		TablePages.insertPage(todaysPage);
	} 

	public static Page getTodaysPage() {
		return todaysPage;
	}
	 
	/**
	 * Set the center of view with projectDetailController.getView().
	 * The received project and stylesheet are assigned to the projectDetailController
	 * 
	 * @param project Project
	 * @param styleSheetPath String
	 */
	public void openProject(Project project, String styleSheetPath) {
		projectDetailController=new ProjectDetailController();
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
	
	public void updateTaskWrapper() {
		homeController.insertTaskFromDB(); 
	}
	
	
	public BorderPane getView() {
		return this.view;
	}

	public GridPane getTopBar() {
		return this.topBar;
	}
	
	public void initActions() {
		
		App.borderLessScene.setMoveControl(getTopBar());
		App.borderLessScene.removeDefaultCSS();
		closeButton.setOnAction(a -> App.primaryStage.close());
		minimizeButton.setOnAction(a -> App.primaryStage.setIconified(true));
		maximizeButton.setOnAction(a -> App.borderLessScene.maximizeStage());
	}
	
	public void changeTheme() {
		view.getStylesheets().clear();
		view.getStylesheets().add(getClass().getResource(App.preferences.getTheme()).toExternalForm());		

	}

	public ProjectManagerController getProjectManagerController() {
		return projectManagerController;
	}

	public void setProjectManagerController(ProjectManagerController projectManagerController) {
		this.projectManagerController = projectManagerController;
	}

	public NotesController getNotasController() {
		return notasController;
	}

	public void setNotasController(NotesController notasController) {
		this.notasController = notasController;
	}

	public BalanceManagerController getBalanceManagerController() {
		return balanceManagerController;
	}

	public void setBalanceManagerController(BalanceManagerController balanceManagerController) {
		this.balanceManagerController = balanceManagerController;
	}

	public ProjectDetailController getProjectDetailController() {
		return projectDetailController;
	}

	public void setProjectDetailController(ProjectDetailController projectDetailController) {
		this.projectDetailController = projectDetailController;
	}

	public HomeController getHomeController() {
		return homeController;
	}

	public void setHomeController(HomeController homeController) {
		this.homeController = homeController;
	}

	public PomodoroController getPomodoroController() {
		return pomodoroController;
	}

	public void setPomodoroController(PomodoroController pomodoroController) {
		this.pomodoroController = pomodoroController;
	}

	public SettingsController getSettingsController() {
		return settingsController;
	}

	public void setSettingsController(SettingsController settingsController) {
		this.settingsController = settingsController;
	}

	public MenuBarController getMenuBarController() {
		return menuBarController;
	}

	public void setMenuBarController(MenuBarController menuBarController) {
		this.menuBarController = menuBarController;
	}

	public Button getCloseButton() {
		return closeButton;
	}

	public void setCloseButton(Button closeButton) {
		this.closeButton = closeButton;
	}

	public Button getMaximizeButton() {
		return maximizeButton;
	}

	public void setMaximizeButton(Button maximizeButton) {
		this.maximizeButton = maximizeButton;
	}

	public Button getMinimizeButton() {
		return minimizeButton;
	}

	public void setMinimizeButton(Button minimizeButton) {
		this.minimizeButton = minimizeButton;
	}

}
