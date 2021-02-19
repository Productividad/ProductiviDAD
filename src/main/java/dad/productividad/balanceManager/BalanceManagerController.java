package dad.productividad.balanceManager;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import dad.productividad.dataManager.TableIncomeExpenses;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.util.converter.NumberStringConverter;

public class BalanceManagerController implements Initializable {

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
    private JFXTextField conceptTF,amountTF;

    @FXML
    private JFXDatePicker datePicker;

    @FXML
	private JFXRadioButton positiveRB,negativeRB;

    @FXML
    private Button addButton,deleteButton;

    @FXML
    private Label total,totalLabel,previusMonthButton,yearLabel,monthLabel,nextMonthButton;

	private ListProperty<IncomeExpense> movementsList = new SimpleListProperty<>(FXCollections.observableArrayList());
	private DoubleProperty totalAmount = new SimpleDoubleProperty();

	/**TODO button right -> index + 1 | if read List is null or Size < 10, disable
	 * TODO button left -> index - 1 | if index = 1, disable left button
	 */


	public BalanceManagerController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BalanceManagerView.fxml"));
			loader.setResources(ResourceBundle.getBundle("i18n/strings"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override 
	public void initialize(URL location, ResourceBundle resources) {
		for (IncomeExpense i : TableIncomeExpenses.read(LocalDate.now()))
			movementsList.add(i);

		balanceTableView.itemsProperty().bindBidirectional(movementsList); 

		dateColumn.setCellValueFactory(v -> v.getValue().dateProperty());
		conceptColumn.setCellValueFactory(v -> v.getValue().conceptProperty());
		amountColumn.setCellValueFactory(v -> v.getValue().amountProperty());

		final ToggleGroup toggleGroup = new ToggleGroup();
		positiveRB.setToggleGroup(toggleGroup);
		positiveRB.setSelected(true);
		negativeRB.setToggleGroup(toggleGroup);

		totalAmount.set(TableIncomeExpenses.getTotal());

		amountTF.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*(\\.\\d{0,2})?")) {
					amountTF.setText(oldValue);
				}
			}
		}); 

		totalLabel.textProperty().bindBidirectional(totalAmount, new NumberStringConverter("0.##"));

		balanceTableView.getSelectionModel().clearSelection();
		addButton.disableProperty().bind(Bindings.isEmpty(amountTF.textProperty()));
		deleteButton.disableProperty().bind(balanceTableView.getSelectionModel().selectedItemProperty().isNull());

	}

	public GridPane getView() {
		return this.view;
	}

	@FXML
	private void onDeleteMovement(ActionEvent event) {

		TableIncomeExpenses.delete(balanceTableView.getSelectionModel().getSelectedItem());
		balanceTableView.getItems().remove(balanceTableView.getSelectionModel().getSelectedItem());
		balanceTableView.refresh();
		totalAmount.set(TableIncomeExpenses.getTotal());
	}

	@FXML
	private void onInsertMovement(ActionEvent event) {
		IncomeExpense incomeExpense = new IncomeExpense();
		incomeExpense.setAmount((positiveRB.selectedProperty().get()) ? Double.parseDouble(amountTF.getText())
				: Double.parseDouble("-" + amountTF.getText()));
		incomeExpense.setConcept(conceptTF.getText());
		incomeExpense.setDate(datePicker.getValue());
		TableIncomeExpenses.create(incomeExpense);
		movementsList.add(incomeExpense);
		totalAmount.set(TableIncomeExpenses.getTotal());
		amountTF.clear();
	}

    @FXML
    private void onFilterNegativeCheck(ActionEvent event) {

    }

    @FXML
    private void onFilterPositiveCheck(ActionEvent event) {

    }
    
    @FXML
    private void onNextMonth(ActionEvent event) {

    }

    @FXML
    private void onPreviusMonth(ActionEvent event) {

    }

}
