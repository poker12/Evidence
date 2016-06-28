package dto.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity(name="expenses")
@DynamicUpdate
@DynamicInsert
public class Expense {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="bill_number", length=20, nullable=false)
	private String billNumber;
	
	@Column(name="description", nullable=false, length=150)
	private String description;
	
	@Column(name="expense_created", nullable=false)
	private LocalDate created; //day when bill was created
	
	@Column(name="date_of_taxable_supply", nullable=false)
	private LocalDate dateOfTaxableSupply; //day of taxable supply (mostly same as delivered date)
	
	@Column(name="due_date", nullable=false)
	private LocalDate dueDate; //last day, when bill can be paid
	
	@Column(name="method_of_payment", nullable=false, length=30)
	private String methodOfPayment; // transaction, cash, credit card, zápoètem
	
	@Column(name="summary_price", nullable=false, precision=10, scale=2)
	private BigDecimal summaryPrice; //summarized price with VAT 
	
	@Column(name="delivered", nullable=true)
	private LocalDate delivered;//day, when goods were delivered
	
	@Column(name="kind_of_expense", length=30, nullable=false)
	private String kindOfExpense;
	
	//Expense can has more item-vat-categories. We must know about all their summaries
	@OneToMany(mappedBy="expense", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<VatRateSummary> vatRatesAndSummaries; 
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="supplier_id", nullable=true)
	private CompanyContact supplier;
	
	@OneToMany(mappedBy="expense", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<ExpenseItem> expenseItems; //when we need create sub-bill from original bill
		
	//in moment of creating expense, we can/have to joint it with entry of products
	@OneToMany(mappedBy="expense", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<EntryOfGoods> entriesOfGoods;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getCreated() {
		return created;
	}

	public void setCreated(LocalDate created) {
		this.created = created;
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

	public CompanyContact getSupplier() {
		return supplier;
	}

	public void setSupplier(CompanyContact supplier) {
		this.supplier = supplier;
	}

	public List<ExpenseItem> getExpenseItems() {
		return expenseItems;
	}

	public void setExpenseItems(List<ExpenseItem> expenseItems) {
		this.expenseItems = expenseItems;
	}

	public List<EntryOfGoods> getEntriesOfGoods() {
		return entriesOfGoods;
	}

	public void setEntriesOfGoods(List<EntryOfGoods> entriesOfGoods) {
		this.entriesOfGoods = entriesOfGoods;
	}

	public LocalDate getDelivered() {
		return delivered;
	}

	public void setDelivered(LocalDate delivered) {
		this.delivered = delivered;
	}

	public String getKindOfExpense() {
		return kindOfExpense;
	}

	public void setKindOfExpense(String kindOfExpense) {
		this.kindOfExpense = kindOfExpense;
	}

	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public Expense(String billNumber, String description, LocalDate created, LocalDate dateOfTaxableSupply,
			LocalDate due_date, String methodOfPayment, BigDecimal summaryPrice, LocalDate delivered,
			String kindOfExpense, List<VatRateSummary> vatRatesAndSummaries, CompanyContact supplier,
			List<EntryOfGoods> entriesOfGoods) {
		super();
		this.billNumber = billNumber;
		this.description = description;
		this.created = created;
		this.dateOfTaxableSupply = dateOfTaxableSupply;
		this.dueDate = due_date;
		this.methodOfPayment = methodOfPayment;
		this.summaryPrice = summaryPrice;
		this.delivered = delivered;
		this.kindOfExpense = kindOfExpense;
		this.vatRatesAndSummaries = vatRatesAndSummaries;
		this.supplier = supplier;
		this.entriesOfGoods = entriesOfGoods;
	}

	
	public Expense(Long id, String billNumber, String description, LocalDate created, LocalDate dateOfTaxableSupply,
			LocalDate due_date, String methodOfPayment, BigDecimal summaryPrice, LocalDate delivered,
			String kindOfExpense, List<VatRateSummary> vatRatesAndSummaries, CompanyContact supplier,
			List<ExpenseItem> expenseItems, List<EntryOfGoods> entriesOfGoods) {
		super();
		this.id = id;
		this.billNumber = billNumber;
		this.description = description;
		this.created = created;
		this.dateOfTaxableSupply = dateOfTaxableSupply;
		this.dueDate = due_date;
		this.methodOfPayment = methodOfPayment;
		this.summaryPrice = summaryPrice;
		this.delivered = delivered;
		this.kindOfExpense = kindOfExpense;
		this.vatRatesAndSummaries = vatRatesAndSummaries;
		this.supplier = supplier;
		this.expenseItems = expenseItems;
		this.entriesOfGoods = entriesOfGoods;
	}

	public Expense() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	
	
}