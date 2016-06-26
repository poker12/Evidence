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

import org.hibernate.annotations.DynamicInsert;

@Entity(name="product_price_histories")
@DynamicInsert
public class PriceHistory {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="product_id", nullable=false, updatable=false)
	private Product product;
	
	@Column(name="start_date_time", nullable=false, columnDefinition="datetime default now()")
	private LocalDateTime startDateTime;
	
	@Column(name="end_date_time", nullable=true)
	private LocalDateTime endDateTime;
	
	@Column(name="selling_price_with_vat", nullable=true, precision=10, scale=2)
	private BigDecimal sellingPriceWithVat;
	
	@Column(name="selling_price_without_vat", nullable=true, precision=10, scale=2)
	private BigDecimal sellingPriceWithoutVat;
	
	@Column(name="vat_rate", precision=5, scale=2, nullable=false)
	private BigDecimal vatRate;

	public PriceHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PriceHistory(Long id, Product product, LocalDateTime startDateTime, LocalDateTime endDateTime,
			BigDecimal sellingPriceWithVat, BigDecimal sellingPriceWithoutVat, BigDecimal vatRate) {
		super();
		this.id = id;
		this.product = product;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.sellingPriceWithVat = sellingPriceWithVat;
		this.sellingPriceWithoutVat = sellingPriceWithoutVat;
		this.vatRate = vatRate;
	}

	public PriceHistory(Product product, LocalDateTime startDateTime, LocalDateTime endDateTime,
			BigDecimal sellingPriceWithVat, BigDecimal vatRate) {
		super();
		this.product = product;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.sellingPriceWithVat = sellingPriceWithVat;
		this.vatRate = vatRate;
	}

	public PriceHistory(Product product, LocalDateTime endDateTime, BigDecimal sellingPriceWithVat,
			BigDecimal vatRate) {
		super();
		this.product = product;
		this.endDateTime = endDateTime;
		this.sellingPriceWithVat = sellingPriceWithVat;
		this.vatRate = vatRate;
	}

	
}