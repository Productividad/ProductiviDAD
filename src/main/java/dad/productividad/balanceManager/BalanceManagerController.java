package dad.productividad.balanceManager;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import dad.productividad.app.App;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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
    private Button addButton, deleteButton;

    @FXML
    private Label total, totalLabel, typeCoinLabel, yearLabel, monthLabel;

    @FXML
    private JFXToggleButton totalToggle;

    @FXML
    private VBox previousMonthWrapper, nextMonthWrapper;

    private ListProperty<IncomeExpense> movementsList = new SimpleListProperty<>(FXCollections.observableArrayList());
    private DoubleProperty totalAmount = new SimpleDoubleProperty();
    private ObjectProperty<LocalDate> index = new SimpleObjectProperty<>(LocalDate.now()); //My actual index
    private ObjectProperty<LocalDate> previousIndex = new SimpleObjectProperty<>(null); //My previous index
    private ObjectProperty<LocalDate> nextIndex = new SimpleObjectProperty<>(); //My next index
    private int year = LocalDate.now().getYear(), month = LocalDate.now().getMonthValue();

    /**
     * Class constructor
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

    /**
     * Initialization of the components, and the logic of this view.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        datePicker.setValue(getIndex());
        typeCoinLabel.textProperty().set(App.preferences.getCurrency().getSymbol());
        movementsList.addAll(TableIncomeExpenses.read(getIndex(), 0));

        previousMonthWrapper.setOnMouseClicked(e -> onPreviousMonth());
        nextMonthWrapper.setOnMouseClicked(e -> onNextMonth());

        nextMonthWrapper.setDisable(true);

        if (!movementsList.isEmpty()) {
            setNextIndex(TableIncomeExpenses.find(movementsList.get(0).getDate(), 0));
            setPreviousIndex(TableIncomeExpenses.find(movementsList.get(0).getDate(), 1));
        } else {
            setNextIndex(TableIncomeExpenses.find(getIndex(), 0));
            setPreviousIndex(TableIncomeExpenses.find(getIndex(), 1));
        }

        if (getPreviousIndex() == null)
            previousMonthWrapper.setDisable(true);

        index.addListener((observable, oldValue, newValue) -> {
            if (newValue.getMonthValue() != getIndex().getMonthValue())
                nextMonthWrapper.setDisable(true);
            else
                nextMonthWrapper.setDisable(false);
        });

        setYearAndMonth();

        movementsList.addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                setIndex(newValue.get(0).getDate());
                setNextIndex(TableIncomeExpenses.find(newValue.get(0).getDate(), 0));
                setPreviousIndex(TableIncomeExpenses.find(newValue.get(0).getDate(), 1));

                if (getPreviousIndex() == null)
                    previousMonthWrapper.setDisable(true);
                else
                    previousMonthWrapper.setDisable(false);
                if (getNextIndex() == null)
                    nextMonthWrapper.setDisable(true);
                else
                    nextMonthWrapper.setDisable(false);
                filter();
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

        totalAmount.set(TableIncomeExpenses.getTotal(getIndex(), 0));

        amountTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*(\\.\\d{0,2})?")) {
                    amountTF.setText(oldValue);
                }
            }
        });

        totalLabel.textProperty().bindBidirectional(totalAmount, new NumberStringConverter("0.##"));

        totalToggle.setSelected(true);
        totalToggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
            filter();
        });
        balanceTableView.getSelectionModel().clearSelection();
        addButton.disableProperty().bind(Bindings.isEmpty(amountTF.textProperty()).or(Bindings.isNull(datePicker.valueProperty())).or(Bindings.isEmpty(conceptTF.textProperty())));
        deleteButton.disableProperty().bind(balanceTableView.getSelectionModel().selectedItemProperty().isNull());

    }

    /**
     * Delete button action.
     * Deletes a registry from the TableView and the DB.
     *
     * @param event
     */
    @FXML
    private void onDeleteMovement(ActionEvent event) {

        TableIncomeExpenses.delete(balanceTableView.getSelectionModel().getSelectedItem());
        balanceTableView.getItems().remove(balanceTableView.getSelectionModel().getSelectedItem());
        balanceTableView.refresh();
    }

    /**
     * Insert button action
     * Inserts a registry in the TableView and the DB.
     * When a movement is inserted, table is updated with the month of that inserted movement.
     *
     * @param event
     */
    @FXML
    private void onInsertMovement(ActionEvent event) {
        IncomeExpense incomeExpense = new IncomeExpense();
        incomeExpense.setAmount((positiveRB.selectedProperty().get()) ? Double.parseDouble(amountTF.getText())
                : Double.parseDouble("-" + amountTF.getText()));
        incomeExpense.setConcept(conceptTF.getText());
        incomeExpense.setDate(datePicker.getValue());
        TableIncomeExpenses.create(incomeExpense);
        if (!movementsList.isEmpty()) {
            if (incomeExpense.getDate().getMonth() == movementsList.get(0).getDate().getMonth()
                    && incomeExpense.getDate().getYear() == movementsList.get(0).getDate().getYear())
                movementsList.add(incomeExpense);
            else {
                movementsList.clear();
                movementsList.addAll(TableIncomeExpenses.read(incomeExpense.getDate(), 0));
                setYearAndMonth();
                balanceTableView.getSelectionModel().clearSelection();
            }
        } else {
            movementsList.addAll(TableIncomeExpenses.read(incomeExpense.getDate(), 0));
            setYearAndMonth();
            balanceTableView.getSelectionModel().clearSelection();
        }

        amountTF.clear();
        conceptTF.clear();
    }

    /**
     * Negative RadioButton action.
     * Filters all negative movements.
     *
     * @param event
     */
    @FXML
    private void onFilterNegativeCheck(ActionEvent event) {
        if (!movementsList.isEmpty())
            movementsList.clear();
        List<IncomeExpense> arrayList = TableIncomeExpenses.read(getIndex(), 2);
        if (!arrayList.isEmpty()) {
            movementsList.addAll(arrayList);
            balanceTableView.getSelectionModel().clearSelection();
        }
    }

    /**
     * Positive RadioButton action.
     * Filters all positive movements.
     *
     * @param event
     */
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

    /**
     * All RadioButton action.
     * Deletes all filters, shows all movements.
     *
     * @param event
     */
    @FXML
    private void onNoFilterCheck(ActionEvent event) {
        movementsList.clear();
        movementsList.addAll(TableIncomeExpenses.read(getIndex(), 0));
        balanceTableView.getSelectionModel().clearSelection();
    }

    /**
     * Moves to the closest previous month with movements.
     */
    private void onPreviousMonth() {
        List<IncomeExpense> arrayList;
        arrayList = TableIncomeExpenses.read(getPreviousIndex(), 0);
        movementsList.clear();
        movementsList.addAll(arrayList);

        setYearAndMonth();
        balanceTableView.getSelectionModel().clearSelection();
    }

    /**
     * Moves to the closest next month with movements.
     */
    private void onNextMonth() {
        List<IncomeExpense> arrayList;
        arrayList = TableIncomeExpenses.read(getNextIndex(), 0);
        movementsList.clear();
        movementsList.addAll(arrayList);

        allFilter.setSelected(true);
        setYearAndMonth();
        balanceTableView.getSelectionModel().clearSelection();
    }

    /**
     * Method used to calculate the Balance, considering applied filters (Positive, Negative, All; Overall, Month)
     */
    private void filter() {
        if (allFilter.isSelected() && totalToggle.isSelected()) {
            totalAmount.set(TableIncomeExpenses.getTotal(getIndex(), 0));
        } else if (negativeFilter.isSelected() && totalToggle.isSelected()) {
            totalAmount.set(TableIncomeExpenses.getTotal(getIndex(), 2));
        } else if (positiveFilter.isSelected() && totalToggle.isSelected()) {
            totalAmount.set(TableIncomeExpenses.getTotal(getIndex(), 1));
        } else if (allFilter.isSelected() && !totalToggle.isSelected()) {
            totalAmount.set(TableIncomeExpenses.getTotal(0));
        } else if (negativeFilter.isSelected() && !totalToggle.isSelected()) {
            totalAmount.set(TableIncomeExpenses.getTotal(2));
        } else if (positiveFilter.isSelected() && !totalToggle.isSelected()) {
            totalAmount.set(TableIncomeExpenses.getTotal(1));
        }
    }

    /**
     * @return Date used as Index
     */
    public LocalDate getIndex() {
        return index.get();
    }

    /**
     * @return Date as ObjectProperty used as Index
     */
    public ObjectProperty<LocalDate> indexProperty() {
        return index;
    }

    /**
     * Set a new index
     *
     * @param index The date to be used as Index
     */
    public void setIndex(LocalDate index) {
        this.index.set(index);
    }

    /**
     * @return Index year
     */
    public int getYear() {
        return year;
    }

    /**
     * Set a new Year
     *
     * @param year The year to be used as Index
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return Index month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Set a new Month
     *
     * @param month The month to be used as Index
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * Set the month and year Label.
     */
    private void setYearAndMonth() {
        monthLabel.textProperty().set(ResourceBundle.getBundle("i18n/strings").getString(String.valueOf(getIndex().getMonth())));
        yearLabel.textProperty().set((String.valueOf(getIndex().getYear())));
    }

    /**
     * Get the previous Index.
     *
     * @return the previous Index.
     */
    public LocalDate getPreviousIndex() {
        return previousIndex.get();
    }

    /**
     * Get the previous Index as ObjectProperty.
     *
     * @return the previous index Property.
     */
    public ObjectProperty<LocalDate> previousIndexProperty() {
        return previousIndex;
    }

    /**
     * Set a new previous Index
     *
     * @param previousIndex
     */
    public void setPreviousIndex(LocalDate previousIndex) {
        this.previousIndex.set(previousIndex);
    }

    /**
     * Get next Index
     *
     * @return the next index.
     */
    public LocalDate getNextIndex() {
        return nextIndex.get();
    }

    /**
     * Get next Index ObjectProperty
     *
     * @return the next index Property.
     */
    public ObjectProperty<LocalDate> nextIndexProperty() {
        return nextIndex;
    }

    /**
     * Set a new Next Index
     *
     * @param nextIndex
     */
    public void setNextIndex(LocalDate nextIndex) {
        this.nextIndex.set(nextIndex);
    }

    /**
     * @return View
     */
    public GridPane getView() {
        return this.view;
    }

}
