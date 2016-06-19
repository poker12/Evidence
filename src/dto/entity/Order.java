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
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="billing_address_id", nullable=true, unique=true)
	private ContactInformation billingAddress; //fakturacni adresa (udaje) v pripade eshop objednavky nebo nakupu na firmu
	
	@OneToMany(mappedBy="order", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<OrderProduct> orderProductList;

}