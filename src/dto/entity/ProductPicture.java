package dto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicUpdate;

@Entity(name="product_pictures")
@DynamicUpdate
public class ProductPicture {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="image", nullable=false)
	private byte[] image;
	
	@ManyToOne
	@JoinColumn(name="product_id", nullable=false)
	private Product product;
}
