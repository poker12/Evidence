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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicUpdate;

@Entity(name="expenses")
@DynamicUpdate
public class Expense {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="description", nullable=false, length=150)
	private String description;
	
	@Column(name="expense_created", nullable=false)
	private LocalDate created; //day when bill was created
	
	@Column(name="date_of_taxable_supply", nullable=false)
	private LocalDate dateOfTaxableSupply; //day, when service/product was delivered
	
	@Column(name="due_date", nullable=false)
	private LocalDate due_date; //last day, when bill can be paid
	
	@Column(name="method_of_payment", nullable=false, length=30)
	private String methodOfPayment; // transaction, cash, credit card, zápoètem
	
	@Column(name="summary_price", nullable=false, precision=10, scale=2)
	private BigDecimal summaryPrice; //summarized price with VAT 
	
	//Expense can has more item-vat-categories. We must know about all their summaries
	@OneToMany(mappedBy="expense", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<VatRateSummary> vatRatesAndSummaries; 
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="supplier_id", nullable=true)
	private CompanyContact supplier;
	
	@OneToMany(mappedBy="expense", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<ExpenseItem> expenseItems; //when we need create sub-bill from original bill
	
	@ManyToOne
	@JoinColumn(name="expense_category_id", nullable=false)
	private ExpenseCategory category;
	
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

	public LocalDate getDue_date() {
		return due_date;
	}

	public void setDue_date(LocalDate due_date) {
		this.due_date = due_date;
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

	public ExpenseCategory getCategory() {
		return category;
	}

	public void setCategory(ExpenseCategory category) {
		this.category = category;
	}

	public List<EntryOfGoods> getEntriesOfGoods() {
		return entriesOfGoods;
	}

	public void setEntriesOfGoods(List<EntryOfGoods> entriesOfGoods) {
		this.entriesOfGoods = entriesOfGoods;
	}

	public Expense(Long id, String description, LocalDate created, LocalDate dateOfTaxableSupply, LocalDate due_date,
			String methodOfPayment, BigDecimal summaryPrice, List<VatRateSummary> vatRatesAndSummaries,
			CompanyContact supplier, List<ExpenseItem> expenseItems, ExpenseCategory category,
			List<EntryOfGoods> entriesOfGoods) {
		super();
		this.id = id;
		this.description = description;
		this.created = created;
		this.dateOfTaxableSupply = dateOfTaxableSupply;
		this.due_date = due_date;
		this.methodOfPayment = methodOfPayment;
		this.summaryPrice = summaryPrice;
		this.vatRatesAndSummaries = vatRatesAndSummaries;
		this.supplier = supplier;
		this.expenseItems = expenseItems;
		this.category = category;
		this.entriesOfGoods = entriesOfGoods;
	}

	public Expense(String description, LocalDate created, LocalDate dateOfTaxableSupply, LocalDate due_date,
			String methodOfPayment, BigDecimal summaryPrice, List<VatRateSummary> vatRatesAndSummaries,
			CompanyContact supplier, ExpenseCategory category, List<EntryOfGoods> entriesOfGoods) {
		super();
		this.description = description;
		this.created = created;
		this.dateOfTaxableSupply = dateOfTaxableSupply;
		this.due_date = due_date;
		this.methodOfPayment = methodOfPayment;
		this.summaryPrice = summaryPrice;
		this.vatRatesAndSummaries = vatRatesAndSummaries;
		this.supplier = supplier;
		this.category = category;
		this.entriesOfGoods = entriesOfGoods;
	} 

}