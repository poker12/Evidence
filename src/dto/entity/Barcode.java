package dto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="barcodes")
public class Barcode {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="product_id", nullable=false, updatable=false)
	//@Column(name="product_id", nullable=false, updatable=false)
	private Product product;
	
	@Column(name="barcode", nullable=false, length=13)
	private String barcode; // EAN13, EAN8, ISBN, UPC_A, UPC_E
	
}