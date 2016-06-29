package gui.expense.createExpense;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.hibernate.product.ProductDao;
import dto.entity.CompanyContact;
import dto.entity.EntryOfGoods;
import dto.entity.Expense;
import dto.entity.Product;
import dto.entity.VatRateSummary;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import service.contactInformation.ContactInformationService;
import service.expense.ExpenseService;
import service.product.ProductService;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ComboBox;

import javafx.scene.control.TextArea;

import javafx.scene.control.RadioButton;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;

import javafx.scene.control.DatePicker;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class CreateExpenseController implements Initializable{
	
	@FXML
	private ComboBox<CompanyContact> selectedCompany;
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
	private ComboBox<VatRateSummary> vatRatePerSummary;
	@FXML
	private TextField pricePerVatRateSummary;
	@FXML
	private CheckBox pricePerVatRateSummaryWithVatCheckbox;
	@FXML
	private DatePicker billCreated;
	@FXML
	private DatePicker orderDelivered;
	@FXML
	private DatePicker dateOfTaxableSupply;
	@FXML
	private DatePicker dueDate;
	@FXML
	private ComboBox<String> paymentMethod;
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
	private Label foundProductLabel;
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
	private ComboBox<BigDecimal> vatRate;
	@FXML
	private TextField selectedItem;
	@FXML
	private TableView<EntryOfGoods> itemsTable;
	@FXML
	private TableColumn<EntryOfGoods, Integer> itemsTableRowId;
	@FXML
	private TableColumn<EntryOfGoods, Long> itemsTablePlu;
	@FXML
	private TableColumn<EntryOfGoods, String> itemsTableName;
	@FXML
	private TableColumn<EntryOfGoods, Long> itemsTableQuantity;
	@FXML
	private TableColumn<EntryOfGoods, BigDecimal> itemsTablePricePerUnit;
	@FXML
	private TableColumn<EntryOfGoods, Boolean> itemsTableWithVat;
	@FXML
	private TableColumn<EntryOfGoods, BigDecimal> itemsTableVatRate;
	@FXML
	private TableColumn<EntryOfGoods, BigDecimal> itemsTableSummaryPrice;
	@FXML
	private TableColumn<EntryOfGoods, BigDecimal> itemsTableVatSum;
	@FXML
	private CheckBox saveSupplier;
	@FXML
	private ComboBox<Product> productList;
	@FXML
	private RadioButton inListSelected;
	@FXML
	private TableView<VatRateSummary> summaryTable;	
	@FXML
	private TableColumn<VatRateSummary, BigDecimal> summaryTablePriceWithoutVat;
	@FXML
	private TableColumn<VatRateSummary, BigDecimal> summaryTableVatRate;
	@FXML
	private TableColumn<VatRateSummary, BigDecimal> summaryTableVatValue;
	@FXML
	private TableColumn<VatRateSummary, BigDecimal> summaryTableWithVat;
		
	private ObservableList<EntryOfGoods> entryOfGoods = FXCollections.observableArrayList();
	private ObservableList<VatRateSummary> vatRateSummaries = FXCollections.observableArrayList();
	private Product foundProduct = null;
	
	private ObservableList<CompanyContact> suppliers = FXCollections.observableArrayList();
	
	private Expense expense = null;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ProductService productService = new ProductService();
		productList.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {
			
			@Override
			public ListCell<Product> call(ListView<Product> param) {
				return new ListCell<Product>(){

					@Override
					protected void updateItem(Product arg0, boolean arg1) {
						super.updateItem(arg0, arg1);
						if(arg0 != null)
							setText(arg0.getId() + " | " + arg0.getName() + " | " + arg0.getCategory().getName());
						else
							setText(null);
					}
			
				};
			}
		});
		productList.getItems().addAll(productService.getAll());
		
		itemsTableRowId.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Integer>(itemsTable.getItems().indexOf(column.getValue())));
		itemsTablePlu.setCellValueFactory(new PropertyValueFactory<EntryOfGoods, Long>("id"));
		itemsTableName.setCellValueFactory(column -> new ReadOnlyStringWrapper(column.getValue().getProduct().getName()));
		itemsTableQuantity.setCellValueFactory(new PropertyValueFactory<EntryOfGoods, Long>("productQuantity"));
		itemsTablePricePerUnit.setCellValueFactory(new PropertyValueFactory<EntryOfGoods, BigDecimal>("pricePerPiece"));
		itemsTableWithVat.setCellValueFactory(new PropertyValueFactory<EntryOfGoods, Boolean>("withVat"));
		itemsTableVatRate.setCellValueFactory(new PropertyValueFactory<EntryOfGoods, BigDecimal>("vatRate"));
		itemsTableSummaryPrice.setCellValueFactory(
				column -> {
					EntryOfGoods eog = column.getValue();
					if(eog.getWithVat()){
						return new ReadOnlyObjectWrapper<BigDecimal>((eog.getPricePerPiece()
								.divide((eog.getVatRate().add(new BigDecimal(100)).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP)), 2, RoundingMode.HALF_UP))
								.multiply(new BigDecimal(eog.getProductQuantity())).setScale(2, RoundingMode.HALF_UP));
					}
					return new ReadOnlyObjectWrapper<BigDecimal>(eog.getPricePerPiece()
							.multiply(new BigDecimal(eog.getProductQuantity())).setScale(2, RoundingMode.HALF_UP));
				});
		itemsTableVatSum.setCellValueFactory(
				column -> {
					EntryOfGoods eog = column.getValue();
					if(eog.getWithVat()){
						return new ReadOnlyObjectWrapper<BigDecimal>((eog.getPricePerPiece()
								.divide((eog.getVatRate().add(new BigDecimal(100)).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP)), 2, RoundingMode.HALF_UP))
								.multiply(new BigDecimal(eog.getProductQuantity())).multiply(eog.getVatRate().divide(new BigDecimal(100), 2, RoundingMode.HALF_UP))
								.setScale(2, RoundingMode.HALF_UP));
					}
					return new ReadOnlyObjectWrapper<BigDecimal>(eog.getPricePerPiece()
							.multiply(new BigDecimal(eog.getProductQuantity())).multiply(eog.getVatRate().divide(new BigDecimal(100), 2, RoundingMode.HALF_UP))
							.setScale(2, RoundingMode.HALF_UP));
				});
		
		itemsTable.setItems(entryOfGoods);
		
		summaryTablePriceWithoutVat.setCellValueFactory(new PropertyValueFactory<VatRateSummary, BigDecimal>("summaryWithoutVat"));
		summaryTableVatRate.setCellValueFactory(new PropertyValueFactory<VatRateSummary, BigDecimal>("vatRate"));
		summaryTableVatValue.setCellValueFactory(new PropertyValueFactory<VatRateSummary, BigDecimal>("vatValue"));
		summaryTableWithVat.setCellValueFactory(new PropertyValueFactory<VatRateSummary, BigDecimal>("summaryWithVat"));
		
		summaryTable.setItems(vatRateSummaries);
		
		ContactInformationService contactInformationService = new ContactInformationService();
		CompanyContact voidContact = new CompanyContact();
		suppliers.add(voidContact);
		suppliers.addAll(contactInformationService.getSuppliers());
		
		selectedCompany.setItems(suppliers);
	}
	
	public void createNewProduct(ActionEvent event){
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/gui/product/create/CreateProduct.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initOwner(this.foundProductLabel.getScene().getWindow());
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveExpense(ActionEvent event){
		if(expense != null){
			return;
		}
		ExpenseService expenseService = new ExpenseService();
		CompanyContact companyContact = new CompanyContact(companyCountry.getText(),
				companyZipCode.getText(), companyCity.getText(), companyStreet.getText(),
				null, null, null, companyName.getText(), companyTin.getText(), companyVatin.getText(), saveSupplier.isSelected());
		expense = new Expense(billNumber.getText(), billDescription.getText(), billCreated.getValue(), dateOfTaxableSupply.getValue(), dueDate.getValue(),
				paymentMethod.getEditor().getText(), new BigDecimal(priceSummary.getText()), orderDelivered.getValue(), "zbozi", vatRateSummaries,
				companyContact, entryOfGoods);
		for(VatRateSummary v : vatRateSummaries)
			v.setExpense(expense);
		for(EntryOfGoods e : entryOfGoods)
			e.setExpense(expense);
		expenseService.persist(expense);
		
		ProductDao productDao = new ProductDao();
		for(EntryOfGoods e : entryOfGoods){
			Product product = e.getProduct();
			product.setQuantity(product.getQuantity() + e.getProductQuantity());
			productDao.updateIfExists(product, product.getId());
		}
	}

	public void findProduct(ActionEvent event){
		ProductService productService = new ProductService();
		if(inListSelected.isSelected()){
			foundProduct = productList.getValue();
			if(foundProduct != null)
				foundProductLabel.setText(foundProduct.getId() + " | " + foundProduct.getName() + " | " + foundProduct.getCategory().getName());
			else
				foundProductLabel.setText("Nebyl vybrán produkt");
		}
		else if(barcodeSelected.isSelected()){
			List<Product> products = productService.findProductsByBarcode(barcode.getText());
			if(!products.isEmpty()){
				foundProduct = products.get(0);
				foundProductLabel.setText(foundProduct.getId() + " | " + foundProduct.getName() + " | " + foundProduct.getCategory().getName());
			}
			else
				foundProductLabel.setText("Nenalezeno");
		}
		else if(pluSelected.isSelected()){
			foundProduct = productService.findByPlu(Long.valueOf(plu.getText()));
			if(foundProduct != null){
				foundProductLabel.setText(foundProduct.getId() + " | " + foundProduct.getName() + " | " + foundProduct.getCategory().getName());
			}
			else
				foundProductLabel.setText("Nenalezeno");
		}
		else{
			foundProductLabel.setText("-");
		}
		if(foundProduct != null)
			quantity.requestFocus();
	}
	
	public void changeFindingCriteria(ActionEvent event){
		if(inListSelected.isSelected()){
			productList.setDisable(false);
			productList.requestFocus();
			barcode.setDisable(true);
			plu.setDisable(true);
			productList.setValue(null);
			barcode.setText("");
			plu.setText("");
		}
		else if(barcodeSelected.isSelected()){
			productList.setDisable(true);
			barcode.setDisable(false);
			barcode.requestFocus();
			plu.setDisable(true);
			productList.setValue(null);
			barcode.setText("");
			plu.setText("");
		}
		else{
			productList.setDisable(true);
			barcode.setDisable(true);
			plu.setDisable(false);
			plu.requestFocus();
			productList.setValue(null);
			barcode.setText("");
			plu.setText("");
		}
	}
	
	public void changePriceType(ActionEvent event){
		if(pricePerUnitWithVatSelected.isSelected()){
			pricePerUnitWithVat.setDisable(false);
			pricePerUnitWithVat.requestFocus();
			pricePerUnitWithVat.setText("");
			pricePerUnitWithoutVat.setDisable(true);
			pricePerUnitWithoutVat.setText("");
		}
		else{
			pricePerUnitWithVat.setDisable(true);
			pricePerUnitWithVat.setText("");
			pricePerUnitWithoutVat.setDisable(false);
			pricePerUnitWithoutVat.requestFocus();
			pricePerUnitWithoutVat.setText("");
		}
	}
	
	public void addProductToExpenseList(ActionEvent event){
		if(foundProduct == null)
			return;
		BigDecimal price;
		Boolean withVat;
		if(pricePerUnitWithoutVatSelected.isSelected()){
			price = new BigDecimal(pricePerUnitWithoutVat.getText());
			withVat = false;
		}
		else{
			price = new BigDecimal(pricePerUnitWithVat.getText());
			withVat = true;
		}
		EntryOfGoods eog = new EntryOfGoods(Long.valueOf(quantity.getText()),
				price, new BigDecimal(vatRate.getEditor().getText()), withVat, foundProduct);
		entryOfGoods.add(eog);
		quantity.setText("1");
		pricePerUnitWithoutVat.setText("");
		pricePerUnitWithVat.setText("");
		vatRate.setValue(null);
		vatRate.getEditor().setText("");
		
		recalculateVatRateSummaries();
		
	}
	
	private void recalculateVatRateSummaries(){
		vatRateSummaries.clear();
		if(itemsTable.getItems().isEmpty())
			return;
		for(int i = 0; i < itemsTable.getItems().size(); i++){
			boolean exists = false;
			for(VatRateSummary v : vatRateSummaries){
				if(v.getVatRate().equals(itemsTable.getColumns().get(6).getCellData(i))){
					v.setSummaryWithoutVat(v.getSummaryWithoutVat().add((BigDecimal)itemsTable.getColumns().get(7).getCellData(i)));
					v.setVatValue(v.getVatValue().add((BigDecimal)itemsTable.getColumns().get(8).getCellData(i)));
					v.setSummaryWithVat(v.getSummaryWithoutVat().add(v.getVatValue()));
					exists = true;
					break;
				}
			}
			if(exists == false){
				VatRateSummary v = new VatRateSummary((BigDecimal)itemsTable.getColumns().get(7).getCellData(i),
						(BigDecimal)itemsTable.getColumns().get(6).getCellData(i), (BigDecimal)itemsTable.getColumns().get(8).getCellData(i),
						((BigDecimal)itemsTable.getColumns().get(7).getCellData(i)).add((BigDecimal)itemsTable.getColumns().get(8).getCellData(i)));
				vatRateSummaries.add(v);
			}
		}
		BigDecimal sumForBill = new BigDecimal(0);
		for(VatRateSummary v : vatRateSummaries){
			sumForBill = sumForBill.add(v.getSummaryWithVat());
		}
		priceSummary.setText(sumForBill.toEngineeringString());
	}
	
	public void supplierSelected(ActionEvent event){
		CompanyContact contact = selectedCompany.getSelectionModel().getSelectedItem();
		if(contact == null){
			companyName.setText("");
			companyTin.setText("");
			companyVatin.setText("");
			companyStreet.setText("");
			companyCity.setText("");
			companyZipCode.setText("");
			companyCountry.setText("");
			saveSupplier.setSelected(false);
		}
		else{
			companyName.setText(contact.getCompanyName());
			companyTin.setText(contact.getCompanyTin());
			companyVatin.setText(contact.getCompanyVatin());
			companyStreet.setText(contact.getStreet());
			companyCity.setText(contact.getCity());
			companyZipCode.setText(contact.getZipCode());
			companyCountry.setText(contact.getCountry());
			saveSupplier.setSelected(false);
		}
	}

}
