package dto.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="vat_rate_summaries")
public class VatRateSummary {
		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="vat_rate", nullable=false, precision=5, scale=2)
	private BigDecimal vatRate;
	
	@Column(name="summary", nullable=false, precision=9, scale=2)
	private BigDecimal summary;
	
	@ManyToOne
	@JoinColumn(name="expense_id", nullable=true)
	private Expense expense;
	
	@ManyToOne
	@JoinColumn(name="order_id", nullable=true)
	private Order order;
}
