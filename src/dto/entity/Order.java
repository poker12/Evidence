package dto.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicUpdate;

@Entity(name="orders")
@DynamicUpdate
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="order_created", nullable=false)
	private LocalDateTime orderCreated; //kdy byla objednavka vytvorena
	
	@Column(name="due_date", nullable=true)
	private LocalDate dueDate; //datum splatnosti
	
	@Column(name="paid", nullable=true)
	private LocalDateTime paid; //kdy byla objednavka zaplacena
	
	@Column(name="invoice_created", nullable=true)
	private LocalDateTime invoiceCreated; //kdy byla vystavena faktura/ucet
	
	@Column(name="expedited", nullable=true)
	private LocalDateTime expedited; // kdy byla objednavka expedovana
	
	@Column(name="invoice_number", nullable=true, length=20, unique=true)
	private String invoiceNumber; // èíslo faktury
	
	@Column(name="canceled", nullable=false, columnDefinition="bit(1) default 0")
	private Boolean canceled; //byla-li objednávka stornována
	
	@Column(name="date_of_taxable_supply", nullable=true)
	private LocalDate dateOfTaxableSupply; //day, when service/product was delivered
	
	@Column(name="method_of_payment", nullable=true, length=30)
	private String methodOfPayment; // transaction, cash, credit card, zápoètem
	
	@Column(name="summary_price", nullable=false, precision=10, scale=2)
	private BigDecimal summaryPrice; //summarized price with VAT 
	
	//Order can has more item-vat-categories. We must know about all their summaries
	@OneToMany(mappedBy="order", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<VatRateSummary> vatRatesAndSummaries;
	
	@ManyToOne
	@JoinColumn(name="ordering_customer_id", nullable=true)
	private User orderingCustomer; //byla-li objednavka vytvorena pres eshop
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="delivering_address_id", nullable=true, unique=true)
	private ContactInformation deliveringAddress; //dorucovaci adresa
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="billing_address_id", nullable=true, unique=true)
	private ContactInformation billingAddress; //fakturacni adresa (udaje) v pripade eshop objednavky nebo nakupu na firmu
	
	@OneToMany(mappedBy="order", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<OrderProduct> orderProductList;
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(LocalDateTime orderCreated, LocalDate dueDate, LocalDateTime paid, LocalDateTime invoiceCreated,
			LocalDateTime expedited, String invoiceNumber, Boolean canceled, LocalDate dateOfTaxableSupply,
			String methodOfPayment, BigDecimal summaryPrice, List<VatRateSummary> vatRatesAndSummaries,
			ContactInformation billingAddress, List<OrderProduct> orderProductList) {
		super();
		this.orderCreated = orderCreated;
		this.dueDate = dueDate;
		this.paid = paid;
		this.invoiceCreated = invoiceCreated;
		this.expedited = expedited;
		this.invoiceNumber = invoiceNumber;
		this.canceled = canceled;
		this.dateOfTaxableSupply = dateOfTaxableSupply;
		this.methodOfPayment = methodOfPayment;
		this.summaryPrice = summaryPrice;
		this.vatRatesAndSummaries = vatRatesAndSummaries;
		this.billingAddress = billingAddress;
		this.orderProductList = orderProductList;
	}

	public Order(Long id, LocalDateTime orderCreated, LocalDate dueDate, LocalDateTime paid,
			LocalDateTime invoiceCreated, LocalDateTime expedited, String invoiceNumber, Boolean canceled,
			LocalDate dateOfTaxableSupply, String methodOfPayment, BigDecimal summaryPrice,
			List<VatRateSummary> vatRatesAndSummaries, User orderingCustomer, ContactInformation deliveringAddress,
			ContactInformation billingAddress, List<OrderProduct> orderProductList) {
		super();
		this.id = id;
		this.orderCreated = orderCreated;
		this.dueDate = dueDate;
		this.paid = paid;
		this.invoiceCreated = invoiceCreated;
		this.expedited = expedited;
		this.invoiceNumber = invoiceNumber;
		this.canceled = canceled;
		this.dateOfTaxableSupply = dateOfTaxableSupply;
		this.methodOfPayment = methodOfPayment;
		this.summaryPrice = summaryPrice;
		this.vatRatesAndSummaries = vatRatesAndSummaries;
		this.orderingCustomer = orderingCustomer;
		this.deliveringAddress = deliveringAddress;
		this.billingAddress = billingAddress;
		this.orderProductList = orderProductList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getOrderCreated() {
		return orderCreated;
	}

	public void setOrderCreated(LocalDateTime orderCreated) {
		this.orderCreated = orderCreated;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDateTime getPaid() {
		return paid;
	}

	public void setPaid(LocalDateTime paid) {
		this.paid = paid;
	}

	public LocalDateTime getInvoiceCreated() {
		return invoiceCreated;
	}

	public void setInvoiceCreated(LocalDateTime invoiceCreated) {
		this.invoiceCreated = invoiceCreated;
	}

	public LocalDateTime getExpedited() {
		return expedited;
	}

	public void setExpedited(LocalDateTime expedited) {
		this.expedited = expedited;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Boolean getCanceled() {
		return canceled;
	}

	public void setCanceled(Boolean canceled) {
		this.canceled = canceled;
	}

	public LocalDate getDateOfTaxableSupply() {
		return dateOfTaxableSupply;
	}

	public void setDateOfTaxableSupply(LocalDate dateOfTaxableSupply) {
		this.dateOfTaxableSupply = dateOfTaxableSupply;
	}

	public String getMethodOfPayment() {
		return methodOfPayment;
	}

	public void setMethodOfPayment(String methodOfPayment) {
		this.methodOfPayment = methodOfPayment;
	}

	public BigDecimal getSummaryPrice() {
		return summaryPrice;
	}

	public void setSummaryPrice(BigDecimal summaryPrice) {
		this.summaryPrice = summaryPrice;
	}

	public List<VatRateSummary> getVatRatesAndSummaries() {
		return vatRatesAndSummaries;
	}

	public void setVatRatesAndSummaries(List<VatRateSummary> vatRatesAndSummaries) {
		this.vatRatesAndSummaries = vatRatesAndSummaries;
	}

	public User getOrderingCustomer() {
		return orderingCustomer;
	}

	public void setOrderingCustomer(User orderingCustomer) {
		this.orderingCustomer = orderingCustomer;
	}

	public ContactInformation getDeliveringAddress() {
		return deliveringAddress;
	}

	public void setDeliveringAddress(ContactInformation deliveringAddress) {
		this.deliveringAddress = deliveringAddress;
	}

	public ContactInformation getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(ContactInformation billingAddress) {
		this.billingAddress = billingAddress;
	}

	public List<OrderProduct> getOrderProductList() {
		return orderProductList;
	}

	public void setOrderProductList(List<OrderProduct> orderProductList) {
		this.orderProductList = orderProductList;
	}
	
}