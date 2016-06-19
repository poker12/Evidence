package dto.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="vat_rates")
public class VatRate {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="name", nullable=false, length=30)
	private String name;
	
	@OneToMany(mappedBy="vatRate", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<ProductVatRate> products;
	
	@OneToMany(mappedBy="vatRate", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<VatRateHistory> values;
}
