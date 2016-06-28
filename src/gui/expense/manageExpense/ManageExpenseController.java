package gui.expense.manageExpense;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import dto.entity.EntryOfGoods;
import dto.entity.Expense;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.expense.ExpenseService;
import javafx.scene.control.TableView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;

public class ManageExpenseController implements Initializable{
	@FXML
	private VBox paneProduct;
	@FXML
	private ToggleGroup expenseListSelectChoice;
	@FXML
	private TableView<Expense> expenseTable;
	@FXML
	private TableColumn<Expense, String> billNumber;
	@FXML
	private TableColumn<Expense, LocalDate> billCreated;
	@FXML
	private TableColumn<Expense, LocalDate> delivered;
	@FXML
	private TableColumn<Expense, LocalDate> dateOfTaxableSupply;
	@FXML
	private TableColumn<Expense, LocalDate> dueDate;
	@FXML
	private TableColumn<Expense, String> methodOfPayment;
	@FXML
	private TableColumn<Expense, BigDecimal> summaryPrice;
	@FXML
	private TableColumn<Expense, String> supplierName;
	@FXML
	private TableColumn<Expense, String> expenseCategory;
	@FXML
	private TableColumn<Expense, String> expenseDescription;

	private ObservableList<Expense> expenses = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ExpenseService expenseService = new ExpenseService();
		expenses.addAll(expenseService.getAll());
		
		billNumber.setCellValueFactory(new PropertyValueFactory<Expense, String>("billNumber"));
		billCreated.setCellValueFactory(new PropertyValueFactory<Expense, LocalDate>("created"));
		delivered.setCellValueFactory(new PropertyValueFactory<Expense, LocalDate>("delivered"));
		dateOfTaxableSupply.setCellValueFactory(new PropertyValueFactory<Expense, LocalDate>("dateOfTaxableSupply"));
		dueDate.setCellValueFactory(new PropertyValueFactory<Expense, LocalDate>("dueDate"));
		methodOfPayment.setCellValueFactory(new PropertyValueFactory<Expense, String>("methodOfPayment"));
		summaryPrice.setCellValueFactory(new PropertyValueFactory<Expense, BigDecimal>("summaryPrice"));
		supplierName.setCellValueFactory(column -> new ReadOnlyStringWrapper(column.getValue().getSupplier().getCompanyName()));
		expenseCategory.setCellValueFactory(new PropertyValueFactory<Expense, String>("kindOfExpense"));
		expenseDescription.setCellValueFactory(new PropertyValueFactory<Expense, String>("description"));
		
		expenseTable.setItems(expenses);
	}
	
	// Event Listener on Button.onAction
	public void addExpense(ActionEvent event){
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/gui/expense/createExpense/CreateExpense.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initOwner(this.paneProduct.getScene().getWindow());
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
