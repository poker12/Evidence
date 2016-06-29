package gui.pointOfSale;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import dto.entity.OrderProduct;
import dto.entity.Product;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import javafx.scene.control.Label;

import javafx.scene.control.ComboBox;

import javafx.scene.control.CheckBox;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class PointOfSaleController implements Initializable{
	@FXML
	private Label billSummizedPriceWithVat;
	@FXML
	private Label billSummizedPriceWithoutVat;
	@FXML
	private TableView<OrderProduct> itemsTable;
	@FXML
	private TableColumn<OrderProduct, Long> itemsColumnPlu;
	@FXML
	private TableColumn<OrderProduct, String> itemsColumnName;
	@FXML
	private TableColumn<OrderProduct, Long> itemsColumnQuantity;
	@FXML
	private TableColumn<OrderProduct, BigDecimal> itemsColumnPriceWithVat;
	@FXML
	private TableColumn<OrderProduct, BigDecimal> itemsColumnVatRate;
	@FXML
	private TableColumn<OrderProduct, BigDecimal> itemsColumnSummary;
	@FXML
	private ComboBox<Product> productListCombobox;
	@FXML
	private TextField searchByPLUTextField;
	@FXML
	private TextField searchByBarcodeTextField;
	@FXML
	private Label selectedProductName;
	@FXML
	private Label selectedProductPricePerUnitWithVat;
	@FXML
	private Label selectedProductPricePerUnitWithoutVat;
	@FXML
	private Label selectedProductVatRate;
	@FXML
	private TextField selectedProductItemQuantity;
	@FXML
	private Label selectedProductQuantityInWarehouse;
	@FXML
	private Label selectedProductNumWaitingOnExpedition;
	@FXML
	private CheckBox company;
	@FXML
	private TextField companyTin;
	@FXML
	private TextField companyVatin;
	@FXML
	private TextField companyName;
	@FXML
	private TextField companyStreet;
	@FXML
	private TextField companyCity;
	@FXML
	private TextField companyZipCode;
	@FXML
	private TextField companyCountry;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	
	
}
