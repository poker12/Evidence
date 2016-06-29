package gui.invoice.manageInvoice;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import dto.entity.ContactInformation;
import dto.entity.Order;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;

import javafx.scene.control.RadioButton;

import javafx.scene.layout.VBox;
import service.order.InvoiceManagement;
import javafx.scene.control.TableView;

import javafx.scene.control.ChoiceBox;

import javafx.scene.control.TableColumn;

public class ManageInvoiceController implements Initializable{
	@FXML
	private VBox paneProduct;
	@FXML
	private RadioButton setOfInvoiceParameterWaitingForPayment;
	@FXML
	private ToggleGroup invoiceListSelectChoices;
	@FXML
	private RadioButton setOfInvoiceParameterPaid;
	@FXML
	private RadioButton setOfInvoiceCanceled;
	@FXML
	private RadioButton setOfInvoiceParameterAll;
	@FXML
	private ChoiceBox searchParameter;
	@FXML
	private TextField searchTextField;
	@FXML
	private TableView<Order> invoiceTable;
	@FXML
	private TableColumn<Order, String> invoiceNumber;
	@FXML
	private TableColumn<Order, String> invoiceCreated;
	@FXML
	private TableColumn<Order, LocalDate> dateOfTaxableSupply;
	@FXML
	private TableColumn<Order, LocalDate> invoiceDueDate;
	@FXML
	private TableColumn<Order, String> invoicePaid;
	@FXML
	private TableColumn<Order, String> invoicePaymentMethod;
	@FXML
	private TableColumn<Order, BigDecimal> invoiceSummaryPrice;
	@FXML
	private TableColumn<Order, Boolean> invoiceCanceled;
	@FXML
	private TableColumn<Order, String> invoiceBillAddress;
	
	private InvoiceManagement invoiceManagement = new InvoiceManagement();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		invoiceNumber.setCellValueFactory(new PropertyValueFactory<Order, String>("invoiceNumber"));
		invoiceCreated.setCellValueFactory(column -> 
			new ReadOnlyObjectWrapper<String>(column.getValue().getInvoiceCreated().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
		dateOfTaxableSupply.setCellValueFactory(new PropertyValueFactory<Order, LocalDate>("dateOfTaxableSupply"));
		invoiceDueDate.setCellValueFactory(new PropertyValueFactory<Order, LocalDate>("dueDate"));
		invoicePaid.setCellValueFactory(column -> 
			new ReadOnlyObjectWrapper<String>(column.getValue().getPaid().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
		invoicePaymentMethod.setCellValueFactory(new PropertyValueFactory<Order, String>("methodOfPayment"));
		invoiceSummaryPrice.setCellValueFactory(new PropertyValueFactory<Order, BigDecimal>("summaryPrice"));
		invoiceCanceled.setCellValueFactory(new PropertyValueFactory<Order, Boolean>("canceled"));
		invoiceBillAddress.setCellValueFactory(column -> {
			ContactInformation contactInformation = column.getValue().getBillingAddress();
			if(contactInformation == null)
				return null;
			return new ReadOnlyStringWrapper(contactInformation.toString());
		});
		
		invoiceManagement.refreshInvoiceList();
		invoiceTable.setItems(invoiceManagement.getAllInvoices());
	}

	
}
