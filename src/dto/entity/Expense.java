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
	
}