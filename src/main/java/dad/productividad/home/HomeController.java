package dad.productividad.home;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.productividad.task.Task;
import dad.productividad.task.TaskComponent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class HomeController implements Initializable {
 
	@FXML 
	private VBox view;
	 
	@FXML 
	private ScrollPane scrollPane;
	
	@FXML 
	private VBox taskWrapper;  
	 
	public HomeController() {  
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HomeView.fxml"));
			loader.setController(this);
			loader.load(); 
		} catch (IOException e) {   
			e.printStackTrace();   
		}  
	}   
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		scrollPane.setFitToWidth(true);
 
			Task task0=new Task();
			task0.setTitle("Eso es ponerse");
			task0.setDescription("No se hace nada porque no hay nada que hacer");
			task0.setFavourite(true);
			task0.setDone(false);
			TaskComponent taskComponent0=new TaskComponent();
			taskComponent0.setTask(task0);
			taskWrapper.getChildren().add(taskComponent0); 
			
			Task task1=new Task();
			task1.setTitle("Este E1000io eso que dices es ofensivo");
			task1.setDescription("ES IRÓNICO");
			task1.setFavourite(true);
			task1.setDone(false);
			TaskComponent taskComponent1=new TaskComponent();
			taskComponent1.setTask(task1);
			taskWrapper.getChildren().add(taskComponent1); 
			
			Task task2=new Task();
			task2.setTitle("Se han saltado el protocolo");
			task2.setDescription("Me enfado y no respiro");
			task2.setFavourite(true);
			task2.setDone(false);
			TaskComponent taskComponent2=new TaskComponent();
			taskComponent2.setTask(task2);
			taskWrapper.getChildren().add(taskComponent2); 
			
			Task task3=new Task();
			task3.setTitle("A ver si dejo de trabajar ya en esta mierda de práctica");
			task3.setDescription("Esa es tu opinión Jesús =,C");
			task3.setFavourite(true);
			task3.setDone(false);
			TaskComponent taskComponent3=new TaskComponent();
			taskComponent3.setTask(task3);
			taskWrapper.getChildren().add(taskComponent3); 
			
			Task task4=new Task();
			task4.setTitle("Bueno aqui puedes leer las características si quieres");
			task4.setDescription("Eso lo tienes que explicar tu Hulio xd");
			task4.setFavourite(true);
			task4.setDone(false);
			TaskComponent taskComponent4=new TaskComponent();
			taskComponent4.setTask(task4);
			taskWrapper.getChildren().add(taskComponent4); 
			
			Task task5=new Task();
			task5.setTitle("Chesbos");
			task5.setDescription("Chesbos");
			task5.setFavourite(true);
			task5.setDone(false);
			TaskComponent taskComponent5=new TaskComponent();
			taskComponent5.setTask(task5);
			taskWrapper.getChildren().add(taskComponent5); 
			
			Task task6=new Task();
			task6.setTitle("IES Domingo Pérez Minik");
			task6.setDescription("Preferiria ir a un vertedero antes que volver a estudiar aquí");
			task6.setFavourite(true);
			task6.setDone(false);
			TaskComponent taskComponent6=new TaskComponent();
			taskComponent6.setTask(task6);
			taskWrapper.getChildren().add(taskComponent6); 
			
	} 
 
	public VBox getView() {
		return this.view;
	}
	
}
