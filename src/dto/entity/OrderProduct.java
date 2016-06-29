package dto.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="orders_products")
public class OrderProduct {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="product_id", nullable=false, updatable=false)
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="order_id", nullable=false, updatable=false)
	private Order order;
	
	@Column(name="product_quantity", nullable=false)
	private Long productQuantity;

	@Column(name="price_per_unit", nullable=true, precision=10, scale=2)
	private BigDecimal pricePerUnit;
	
	@Column(name="with_vat", nullable=false)
	private Boolean withVat;
	
	@Column(name="vat_rate", nullable=false, precision=5, scale=2)
	private BigDecimal vatRate;

	public OrderProduct(Long id, Product product, Order order, Long productQuantity, BigDecimal pricePerUnit,
			Boolean withVat, BigDecimal vatRate) {
		super();
		this.id = id;
		this.product = product;
		this.order = order;
		this.productQuantity = productQuantity;
		this.pricePerUnit = pricePerUnit;
		this.withVat = withVat;
		this.vatRate = vatRate;
	}

	public OrderProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderProduct(Product product, Order order, Long productQuantity, BigDecimal pricePerUnit, Boolean withVat,
			BigDecimal vatRate) {
		super();
		this.product = product;
		this.order = order;
		this.productQuantity = productQuantity;
		this.pricePerUnit = pricePerUnit;
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Long getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Long productQuantity) {
		this.productQuantity = productQuantity;
	}

	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(BigDecimal pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
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
