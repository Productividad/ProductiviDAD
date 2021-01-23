package dad.productiviDAD.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import dad.productiviDAD.model.IncomeExpense;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

public class BalanceManagerController implements Initializable{

	@FXML
    private GridPane view;

    @FXML
    private TableView<IncomeExpense> balanceTableView;

    @FXML
    private TableColumn<IncomeExpense, LocalDate> dateColumn;

    @FXML
    private TableColumn<IncomeExpense, String> conceptColumn;

    @FXML
    private TableColumn<IncomeExpense, Number> amountColumn;
 
    @FXML
	private JFXTextField conceptTF;

    @FXML 
    private JFXTextField amountTF;

    @FXML  
    private Label totalLabel;
 
    private ListProperty<IncomeExpense>movementsList=new SimpleListProperty<>(FXCollections.observableArrayList());
  
    
    public BalanceManagerController() {
    	try { 
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/balanceManagerView.fxml"));
		loader.setController(this); 
		loader.load();
		} catch (IOException e) {e.printStackTrace();} 
    } 
 
	@Override
	public void initialize(URL location, ResourceBundle resources) { 
				
		for(int i=0;i<=25;i++) { 
			 
			LocalDate date=LocalDate.now();
			String string=new String("String "+i);
			
			IncomeExpense income=new IncomeExpense(date, string, i, true); 
			movementsList.add(income);
		} 
		
		balanceTableView.itemsProperty().bind(movementsList);
		
		dateColumn.setCellValueFactory(v->v.getValue().dateProperty());
		conceptColumn.setCellValueFactory(v->v.getValue().conceptProperty());
		amountColumn.setCellValueFactory(v->v.getValue().amountProperty());
	}
	 
	public GridPane getView() {
		return this.view;
	}

    @FXML
    private void onDeleteMovement(ActionEvent event) {
    	
    }

    @FXML
    private void onInsertMovement(ActionEvent event) {
    	 
    }	
}
