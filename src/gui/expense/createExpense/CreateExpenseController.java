package gui.expense.createExpense;

import java.io.IOException;

import dto.entity.CompanyContact;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.expense.ExpenseService;
import javafx.scene.control.Label;

import javafx.scene.control.ComboBox;

import javafx.scene.control.TextArea;

import javafx.scene.control.RadioButton;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;

import javafx.scene.control.DatePicker;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class CreateExpenseController {
	@FXML
	private ComboBox selectedCompany;
	@FXML
	private TextField companyName;
	@FXML
	private TextField companyTin;
	@FXML
	private TextField companyVatin;
	@FXML
	private TextField companyStreet;
	@FXML
	private TextField companyCity;
	@FXML
	private TextField companyZipCode;
	@FXML
	private TextField companyCountry;
	@FXML
	private TextField billNumber;
	@FXML
	private TextField priceSummary;
	@FXML
	private TextField priceWithoutVatSummary;
	@FXML
	private ComboBox vatRateSummary;
	@FXML
	private TextField pricePerVatRateSummary;
	@FXML
	private CheckBox pricePerVatRateSummaryWithVatCheckbox;
	@FXML
	private TableColumn summaryTableVatRate;
	@FXML
	private TableColumn summaryTablePrice;
	@FXML
	private DatePicker billCreated;
	@FXML
	private DatePicker orderDelivered;
	@FXML
	private DatePicker duzp;
	@FXML
	private DatePicker dueDate;
	@FXML
	private ComboBox paymentMethod;
	@FXML
	private TextArea billDescription;
	@FXML
	private TextField barcode;
	@FXML
	private RadioButton barcodeSelected;
	@FXML
	private ToggleGroup searchParamRad;
	@FXML
	private TextField plu;
	@FXML
	private RadioButton pluSelected;
	@FXML
	private Label foundProduct;
	@FXML
	private TextField quantity;
	@FXML
	private TextField pricePerUnitWithVat;
	@FXML
	private RadioButton pricePerUnitWithVatSelected;
	@FXML
	private ToggleGroup priceTypeSelectRad;
	@FXML
	private TextField pricePerUnitWithoutVat;
	@FXML
	private RadioButton pricePerUnitWithoutVatSelected;
	@FXML
	private ComboBox vatRate;
	@FXML
	private TextField selectedItem;
	@FXML
	private TableView itemsTable;
	@FXML
	private TableColumn itemsTableRowId;
	@FXML
	private TableColumn itemsTablePlu;
	@FXML
	private TableColumn itemsTableName;
	@FXML
	private TableColumn itemsTableQuantity;
	@FXML
	private TableColumn itemsTablePricePerUnit;
	@FXML
	private TableColumn itemsTableWithVat;
	@FXML
	private TableColumn itemsTableVatRate;
	@FXML
	private TableColumn itemsTableSummaryPrice;
	@FXML
	private TableColumn itemsTableVatSum;
	@FXML
	private CheckBox saveSupplier;
	
	
	public void createNewProduct(ActionEvent event){
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/gui/product/create/CreateProduct.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initOwner(this.foundProduct.getScene().getWindow());
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveExpense(ActionEvent event){
		ExpenseService expenseService = new ExpenseService();
		CompanyContact cc = new CompanyContact(companyCountry.getText(),
				companyZipCode.getText(), companyCity.getText(), companyStreet.getText(),
				null, null, null, companyName.getText(), companyTin.getText(), companyVatin.getText());
		
	}

}
