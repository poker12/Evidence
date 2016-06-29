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
	
	//@Column(name="start_date_time", nullable=false, columnDefinition="datetime default now()")
	@Column(name="start_date_time", nullable=true, columnDefinition="datetime default now()")
	private LocalDateTime startDateTime;
	
	@Column(name="end_date_time", nullable=true)
	private LocalDateTime endDateTime;
	
	@Column(name="selling_price", nullable=true, precision=10, scale=2)
	private BigDecimal sellingPrice;
	
	@Column(name="withVat", nullable=true, precision=10, scale=2)
	private Boolean withVat;
	
	@Column(name="vat_rate", precision=5, scale=2, nullable=false)
	private BigDecimal vatRate;

	public PriceHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PriceHistory(Long id, Product product, LocalDateTime startDateTime, LocalDateTime endDateTime,
			BigDecimal sellingPrice, Boolean withVat, BigDecimal vatRate) {
		super();
		this.id = id;
		this.product = product;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.sellingPrice = sellingPrice;
		this.withVat = withVat;
		this.vatRate = vatRate;
	}

	public PriceHistory(Product product, LocalDateTime endDateTime, BigDecimal sellingPrice, Boolean withVat,
			BigDecimal vatRate) {
		super();
		this.product = product;
		this.endDateTime = endDateTime;
		this.sellingPrice = sellingPrice;
		this.withVat = withVat;
		this.vatRate = vatRate;
	}

	public PriceHistory(Product product, LocalDateTime startDateTime, LocalDateTime endDateTime,
			BigDecimal sellingPrice, Boolean withVat, BigDecimal vatRate) {
		super();
		this.product = product;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.sellingPrice = sellingPrice;
		this.withVat = withVat;
		this.vatRate = vatRate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}

	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Boolean getWithVat() {
		return withVat;
	}

	public void setWithVat(Boolean withVat) {
		this.withVat = withVat;
	}

	public BigDecimal getVatRate() {
		return vatRate;
	}

	public void setVatRate(BigDecimal vatRate) {
		this.vatRate = vatRate;
	}
	
}