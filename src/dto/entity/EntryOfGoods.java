package dto.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="entry_of_goods")
public class EntryOfGoods {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="product_quantity", nullable=false)
	private Long productQuantity;
	
	//price from expense
	@Column(name="price_per_piece_with_vat", nullable=true, precision=10, scale=2)
	private BigDecimal pricePerPieceWithVat;
	
	//price from expense
	@Column(name="price_per_piece_without_vat", nullable=true, precision=10, scale=2)
	private BigDecimal pricePerPieceWithoutVat;
	
	//value from expense
	@Column(name="vat_rate", nullable=true, precision=5, scale=2)
	private BigDecimal vatRate;
	
	@Column(name="added", nullable=false, insertable=false, columnDefinition="datetime default now()")
	private LocalDateTime added;
	
	@ManyToOne
	@JoinColumn(name="product_id", nullable=false, updatable=false)
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="expense_id", nullable=false)
	private Expense expense;

}