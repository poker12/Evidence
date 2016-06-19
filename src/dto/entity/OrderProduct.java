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
	private Integer productQuantity;

	@Column(name="price_per_unit_without_vat", nullable=true, precision=10, scale=2)
	private BigDecimal pricePerUnitWithoutVat;
	
	@Column(name="price_per_unit_with_vat", nullable=true, precision=10, scale=2)
	private BigDecimal pricePerUnitWithVat;
	
	@Column(name="vat_rate", nullable=false, precision=5, scale=2)
	private BigDecimal vatRate;
}
