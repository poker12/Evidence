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

@Entity(name="vat_rate_histories")
public class VatRateHistory {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="start", nullable=false)
	private LocalDateTime start;
	
	@Column(name="end", nullable=true)
	private LocalDateTime end;
	
	@Column(name="rate", nullable=false, precision=5, scale=2)
	private BigDecimal rate;
	
	@ManyToOne
	@JoinColumn(name="vat_rate_id", nullable=false)
	private VatRate vatRate;
}
