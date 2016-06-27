package dto.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="expense_items")
public class ExpenseItem{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="name", nullable=true, length=100)
	private String name;
	
	@Column(name="price", nullable=true, precision=10, scale=2)
	private BigDecimal price;
	
	@Column(name="vat_rate", nullable=false, precision=5, scale=2)
	private BigDecimal vatRate;
	
	@Column(name="with_vat", nullable=false)
	private Boolean withVat;
	
	@ManyToOne
	@JoinColumn(name="expense_id", nullable=false)
	private Expense expense;
}