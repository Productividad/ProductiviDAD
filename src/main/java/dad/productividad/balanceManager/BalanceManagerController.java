package dad.productividad.balanceManager;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import dad.productividad.dataManager.TableIncomeExpenses;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.control.ToggleButton;
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
    private JFXTextField conceptTF, amountTF;

    @FXML
    private JFXDatePicker datePicker;

    @FXML
    private JFXRadioButton positiveRB, negativeRB, positiveFilter, negativeFilter, allFilter;

    @FXML
    private Button addButton, deleteButton, previousMonthButton, nextMonthButton;

    @FXML
    private Label total, totalLabel, typeCoinLabel, yearLabel, monthLabel;

    @FXML
    private ToggleButton totalToggle,monthTotalToggle;
    
    private ListProperty<IncomeExpense> movementsList = new SimpleListProperty<>(FXCollections.observableArrayList());
    private DoubleProperty totalAmount = new SimpleDoubleProperty();
    private ObjectProperty<LocalDate> index = new SimpleObjectProperty<>(LocalDate.now()); //My actual index
    private ObjectProperty<LocalDate> previousIndex = new SimpleObjectProperty<>(null); //My previous index
    private ObjectProperty<LocalDate> nextIndex = new SimpleObjectProperty<>(); //My next index
    private int year = LocalDate.now().getYear(), month = LocalDate.now().getMonthValue();

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

        //TODO Set typeCoinLabel from enum and settings
        typeCoinLabel.textProperty().set("€");
        movementsList.addAll(TableIncomeExpenses.read(getIndex(), 0));

        previousMonthButton.setDisable(true);
        setNextIndex(TableIncomeExpenses.findNext(movementsList.get(0), 0));
        index.addListener((observable, oldValue, newValue) -> {
            if (newValue.getMonthValue() != getIndex().getMonthValue())
                previousMonthButton.setDisable(true);
            else
                previousMonthButton.setDisable(false);
        });

        setYearAndMonth();

        movementsList.addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                setIndex(newValue.get(0).getDate());
                setNextIndex(TableIncomeExpenses.findNext(newValue.get(0), 0));
                setPreviousIndex(TableIncomeExpenses.findNext(newValue.get(0), 1));

                if (getPreviousIndex() == null)
                    previousMonthButton.setDisable(true);
                else
                    previousMonthButton.setDisable(false);
                if (getNextIndex() == null)
                    nextMonthButton.setDisable(true);
                else
                    nextMonthButton.setDisable(false);
            }
        });

        balanceTableView.itemsProperty().bindBidirectional(movementsList);

        dateColumn.setCellValueFactory(v -> v.getValue().dateProperty());
        conceptColumn.setCellValueFactory(v -> v.getValue().conceptProperty());
        amountColumn.setCellValueFactory(v -> v.getValue().amountProperty());

        final ToggleGroup toggleGroupTop = new ToggleGroup();
        positiveRB.setToggleGroup(toggleGroupTop);
        positiveRB.setSelected(true);
        negativeRB.setToggleGroup(toggleGroupTop);

        final ToggleGroup toggleGroupBottom = new ToggleGroup();
        allFilter.setToggleGroup(toggleGroupBottom);
        allFilter.setSelected(true);
        negativeFilter.setToggleGroup(toggleGroupBottom);
        positiveFilter.setToggleGroup(toggleGroupBottom);

        totalAmount.set(TableIncomeExpenses.getTotal(getIndex()));

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
        addButton.disableProperty().bind(Bindings.isEmpty(amountTF.textProperty()).or(Bindings.isNull(datePicker.valueProperty())).or(Bindings.isEmpty(conceptTF.textProperty())));
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
    }

    @FXML
    private void onInsertMovement(ActionEvent event) {
        IncomeExpense incomeExpense = new IncomeExpense();
        incomeExpense.setAmount((positiveRB.selectedProperty().get()) ? Double.parseDouble(amountTF.getText())
                : Double.parseDouble("-" + amountTF.getText()));
        incomeExpense.setConcept(conceptTF.getText());
        incomeExpense.setDate(datePicker.getValue());
        TableIncomeExpenses.create(incomeExpense);
        if (incomeExpense.getDate().getMonth() == movementsList.get(0).getDate().getMonth()
                && incomeExpense.getDate().getYear() == movementsList.get(0).getDate().getYear())
            movementsList.add(incomeExpense);
        else {
            movementsList.clear();
            movementsList.addAll(TableIncomeExpenses.read(incomeExpense.getDate(), 0));
            setYearAndMonth();
            balanceTableView.getSelectionModel().clearSelection();
        }
        amountTF.clear();
        conceptTF.clear();
    }

    @FXML
    private void onFilterNegativeCheck(ActionEvent event) {
        if (!movementsList.isEmpty())
            movementsList.clear();
        List<IncomeExpense> arrayList = TableIncomeExpenses.read(getIndex(), 2);
        movementsList.addAll(arrayList);
        balanceTableView.getSelectionModel().clearSelection();
    }


    @FXML
    private void onFilterPositiveCheck(ActionEvent event) {
        if (!movementsList.isEmpty())
            movementsList.clear();
        List<IncomeExpense> arrayList = TableIncomeExpenses.read(getIndex(), 1);
        if (!arrayList.isEmpty()) {
            movementsList.addAll(arrayList);
            balanceTableView.getSelectionModel().clearSelection();
        }
    }

    @FXML
    private void onNoFilterCheck(ActionEvent event) {
        movementsList.clear();
        movementsList.addAll(TableIncomeExpenses.read(getIndex(), 0));
        balanceTableView.getSelectionModel().clearSelection();
    }

    @FXML
    private void onNextMonth(ActionEvent event) {
        List<IncomeExpense> arrayList;
        arrayList = TableIncomeExpenses.read(getNextIndex(), 0);
        movementsList.clear();
        movementsList.addAll(arrayList);

        setYearAndMonth();
        allFilter.setSelected(true);
        totalAmount.set(TableIncomeExpenses.getTotal(getIndex()));
        balanceTableView.getSelectionModel().clearSelection();
    }

    @FXML
    private void onPreviousMonth(ActionEvent event) {
        List<IncomeExpense> arrayList;
        arrayList = TableIncomeExpenses.read(getPreviousIndex(), 0);
        movementsList.clear();
        movementsList.addAll(arrayList);

        allFilter.setSelected(true);
        setYearAndMonth();
        totalAmount.set(TableIncomeExpenses.getTotal(getIndex()));
        balanceTableView.getSelectionModel().clearSelection();
    }
    
    @FXML
    private void onMonthTotal(ActionEvent event) {
    	
    }
    
    @FXML
    private void onTotal(ActionEvent event) { 
    	
    }

    public LocalDate getIndex() {
        return index.get();
    }

    public ObjectProperty<LocalDate> indexProperty() {
        return index;
    }

    public void setIndex(LocalDate index) {
        this.index.set(index);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    private void setYearAndMonth() {
        monthLabel.textProperty().set(ResourceBundle.getBundle("i18n/strings").getString(String.valueOf(getIndex().getMonth())));
        yearLabel.textProperty().set((String.valueOf(getIndex().getYear())));
    }

    public LocalDate getPreviousIndex() {
        return previousIndex.get();
    }

    public ObjectProperty<LocalDate> previousIndexProperty() {
        return previousIndex;
    }

    public void setPreviousIndex(LocalDate previousIndex) {
        this.previousIndex.set(previousIndex);
    }

    public LocalDate getNextIndex() {
        return nextIndex.get();
    }

    public ObjectProperty<LocalDate> nextIndexProperty() {
        return nextIndex;
    }

    public void setNextIndex(LocalDate nextIndex) {
        this.nextIndex.set(nextIndex);
    }
}
