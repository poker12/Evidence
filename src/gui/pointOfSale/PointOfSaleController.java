package gui.pointOfSale;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.Main;
import dto.entity.OrderProduct;
import dto.entity.Product;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import service.PriceManager;
import service.pointOfSale.PointOfSale;
import service.pointOfSale.ProductFindingParameter;
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
	
	private PointOfSale pointOfSale = new PointOfSale();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pointOfSale.refreshListOfAllProducts();

		productListCombobox.setItems(pointOfSale.getAllProducts());
		
		itemsColumnPlu.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Long>(column.getValue().getProduct().getId()));
		itemsColumnName.setCellValueFactory(column -> new ReadOnlyStringWrapper(column.getValue().getProduct().getName()));
		itemsColumnQuantity.setCellValueFactory(new PropertyValueFactory<OrderProduct, Long>("productQuantity"));
		itemsColumnPriceWithVat.setCellValueFactory(column -> new ReadOnlyObjectWrapper<BigDecimal>(pointOfSale.getPricePerUniWithVat(column.getValue())));
		itemsColumnVatRate.setCellValueFactory(new PropertyValueFactory<OrderProduct, BigDecimal>("vatRate"));
		itemsColumnSummary.setCellValueFactory(column -> new ReadOnlyObjectWrapper<BigDecimal>(pointOfSale.getOrderProductSummaryPriceWithVat(column.getValue())));
		
		itemsTable.setItems(pointOfSale.getItemList());
		
		productListCombobox.setOnAction(event -> searchBySelectFromList());
	}

	public void productSelected(){
		if(pointOfSale.getSelectedProduct() == null){
			selectedProductItemQuantity.setText("0");
			selectedProductName.setText("-");
			selectedProductNumWaitingOnExpedition.setText("-");
			selectedProductPricePerUnitWithVat.setText("-");
			selectedProductPricePerUnitWithoutVat.setText("-");
			selectedProductQuantityInWarehouse.setText("-");
			selectedProductVatRate.setText("-");
			return;
		}
		selectedProductItemQuantity.setText(pointOfSale.getSelectedProduct().getQuantity().toString());
		selectedProductName.setText(pointOfSale.getSelectedProduct().getName());
		//TODO dodìlat poèítání poètu produktu v nevyexpedovaných, nezrušených objednávkách
		selectedProductNumWaitingOnExpedition.setText(String.valueOf(0));
		if(pointOfSale.getSelectedProduct().getPriceHistory().get(0).getWithVat()){
			selectedProductPricePerUnitWithoutVat.setText(
					new PriceManager().calculatePriceWithoutVat(
							pointOfSale.getSelectedProduct().getPriceHistory().get(0).getSellingPrice(),
							pointOfSale.getSelectedProduct().getPriceHistory().get(0).getVatRate(),
							2
							).toString());
			selectedProductPricePerUnitWithVat.setText(pointOfSale.getSelectedProduct().getPriceHistory().get(0).getSellingPrice().toString());
		}
		else{
			selectedProductPricePerUnitWithVat.setText(
					new PriceManager().calculatePriceWithVat(
							pointOfSale.getSelectedProduct().getPriceHistory().get(0).getSellingPrice(),
							pointOfSale.getSelectedProduct().getPriceHistory().get(0).getVatRate(),
							2
							).toString());
			selectedProductPricePerUnitWithoutVat.setText(pointOfSale.getSelectedProduct().getPriceHistory().get(0).getSellingPrice().toString());
		}
		selectedProductQuantityInWarehouse.setText(pointOfSale.getSelectedProduct().getQuantity().toString());
		selectedProductVatRate.setText(pointOfSale.getSelectedProduct().getPriceHistory().get(0).getVatRate().toString());
		selectedProductItemQuantity.setText("1");
		selectedProductItemQuantity.requestFocus();
	}
	
	public void searchBySelectFromList(){
		pointOfSale.findProduct(productListCombobox.getSelectionModel().getSelectedItem());
		productSelected();
	}
	
	public void searchByPlu(KeyEvent event){
		if(event.getCode() == KeyCode.ENTER){
			pointOfSale.findProduct(searchByPLUTextField.getText(), ProductFindingParameter.FIND_BY_PLU);
			productSelected();
		}
	}
	
	public void searchByBarcode(KeyEvent event){
		if(event.getCode() == KeyCode.ENTER){
			pointOfSale.findProduct(searchByBarcodeTextField.getText(), ProductFindingParameter.FIND_BY_BARCODE);
			productSelected();
		}
	}
	
	public void handlePressedEnterOnSelectedProductItemQuantity(KeyEvent event){
		if(event.getCode() == KeyCode.ENTER){
			pointOfSale.addItemToList(Long.valueOf(selectedProductItemQuantity.getText()));
			billSummizedPriceWithoutVat.setText(pointOfSale.getSummarizedBillPriceWithoutVat().toString() + " Kè");
			billSummizedPriceWithVat.setText(pointOfSale.getSummarizedBillPriceWithVat().toString() + " Kè");
			selectedProductItemQuantity.setText(String.valueOf(1));
		}
	}
	
	public void handleClickOnAddButton(ActionEvent event){
		pointOfSale.addItemToList(Long.valueOf(selectedProductItemQuantity.getText()));
		billSummizedPriceWithoutVat.setText(pointOfSale.getSummarizedBillPriceWithoutVat().toString() + " Kè");
		billSummizedPriceWithVat.setText(pointOfSale.getSummarizedBillPriceWithVat().toString() + " Kè");
		selectedProductItemQuantity.setText(String.valueOf(1));
	}
	
	public void createOrder(ActionEvent event){
		if(company.isSelected()){
			pointOfSale.setCompany(companyCountry.getText(), companyZipCode.getText(), companyCity.getText(),
					companyStreet.getText(), companyName.getText(), companyTin.getText(), companyVatin.getText());
			pointOfSale.setSellingToCompany(true);
		}
		pointOfSale.createOrder();

		clear();
	}
	
	public void handleClickOnCompany(ActionEvent event){
		if(company.isSelected()){
			setDisableCompanyTextField(false);
			
			pointOfSale.setSellingToCompany(true);
		}
		else{
			setDisableCompanyTextField(true);
			
			clearCompanyTextFields();
			
			pointOfSale.setSellingToCompany(false);
		}
	}
	
	public void handleClickOnCancel(ActionEvent event){
		
	}
	
	private void clear(){
		pointOfSale.clear();
		
		billSummizedPriceWithoutVat.setText("0 Kè");
		billSummizedPriceWithVat.setText("0 Kè");
		
		clearCompanyTextFields();
		
		setDisableCompanyTextField(true);
		
		company.setSelected(false);
	}
	
	private void clearCompanyTextFields(){
		companyCity.setText("");
		companyCountry.setText("");
		companyName.setText("");
		companyStreet.setText("");
		companyTin.setText("");
		companyVatin.setText("");
		companyZipCode.setText("");
	}
	
	private void setDisableCompanyTextField(boolean value){
		companyCity.setDisable(value);
		companyCountry.setDisable(value);
		companyName.setDisable(value);
		companyStreet.setDisable(value);
		companyTin.setDisable(value);
		companyVatin.setDisable(value);
		companyZipCode.setDisable(value);
	}
}
