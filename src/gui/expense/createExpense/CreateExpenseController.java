package gui.expense.createExpense;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;

import javafx.scene.control.ToggleGroup;

import javafx.scene.control.ComboBox;

import javafx.scene.control.TextArea;

import javafx.scene.control.RadioButton;

import javafx.scene.control.CheckBox;

import javafx.scene.control.DatePicker;

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

	
	
}
