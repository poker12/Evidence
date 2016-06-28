package dto.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity(name="vat_rate_summaries")
@DynamicInsert
@DynamicUpdate
public class VatRateSummary {
		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="summary_without_vat", nullable=false, precision=9, scale=2)
	private BigDecimal summaryWithoutVat;
	
	@Column(name="vat_rate", nullable=false, precision=5, scale=2)
	private BigDecimal vatRate;
	
	@Column(name="vat_value", nullable=false, precision=9, scale=2)
	private BigDecimal vatValue;
	
	@Column(name="summary_with_vat", nullable=false, precision=9, scale=2)
	private BigDecimal summaryWithVat;
	
	@ManyToOne
	@JoinColumn(name="expense_id", nullable=true)
	private Expense expense;
	
	@ManyToOne
	@JoinColumn(name="order_id", nullable=true)
	private Order order;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getSummaryWithoutVat() {
		return summaryWithoutVat;
	}

	public void setSummaryWithoutVat(BigDecimal summaryWithoutVat) {
		this.summaryWithoutVat = summaryWithoutVat;
	}

	public BigDecimal getVatRate() {
		return vatRate;
	}

	public void setVatRate(BigDecimal vatRate) {
		this.vatRate = vatRate;
	}

	public BigDecimal getVatValue() {
		return vatValue;
	}

	public void setVatValue(BigDecimal vatValue) {
		this.vatValue = vatValue;
	}

	public BigDecimal getSummaryWithVat() {
		return summaryWithVat;
	}

	public void setSummaryWithVat(BigDecimal summaryWithVat) {
		this.summaryWithVat = summaryWithVat;
	}

	public Expense getExpense() {
		return expense;
	}

	public void setExpense(Expense expense) {
		this.expense = expense;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public VatRateSummary(Long id, BigDecimal summaryWithoutVat, BigDecimal vatRate, BigDecimal vatValue,
			BigDecimal summaryWithVat, Expense expense, Order order) {
		super();
		this.id = id;
		this.summaryWithoutVat = summaryWithoutVat;
		this.vatRate = vatRate;
		this.vatValue = vatValue;
		this.summaryWithVat = summaryWithVat;
		this.expense = expense;
		this.order = order;
	}

	public VatRateSummary(BigDecimal summaryWithoutVat, BigDecimal vatRate, BigDecimal vatValue,
			BigDecimal summaryWithVat) {
		super();
		this.summaryWithoutVat = summaryWithoutVat;
		this.vatRate = vatRate;
		this.vatValue = vatValue;
		this.summaryWithVat = summaryWithVat;
	}

	
}
