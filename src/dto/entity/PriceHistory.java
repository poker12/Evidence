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

@Entity(name="product_price_histories")
public class PriceHistory {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="product_id", nullable=false, updatable=false)
	private Product product;
	
	@Column(name="start_date_time", nullable=false)
	private LocalDateTime startDateTime;
	
	@Column(name="end_date_time", nullable=true)
	private LocalDateTime endDateTime;
	
	@Column(name="selling_price_with_vat", nullable=true, precision=10, scale=2)
	private BigDecimal oldSellingPriceWithVat;
	
	@Column(name="selling_price_without_vat", nullable=true, precision=10, scale=2)
	private BigDecimal oldSellingPriceWithoutVat;

}