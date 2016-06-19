package dto.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicUpdate;

@Entity(name="products")
@DynamicUpdate
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="name", nullable=false, length=100)
	private String name;
	
	@Column(name="archived", nullable=false, columnDefinition="bit(1) default 0") 
	private Boolean archived;
	
	@Column(name="description", nullable=true, length=1024)
	private String description;
	
	@Column(name="visibility_in_eshop", nullable=false, columnDefinition="bit(1) default 1")
	private Boolean visibilityInEshop;
	
	@Column(name="quantity", nullable=false, columnDefinition="integer default 0")
	private Long quantity;
	
	@ManyToOne
	@JoinColumn(name="product_category_id", nullable=false)
	private ProductCategory category;

	@OneToMany(mappedBy="product", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<OrderProduct> orderProductList;
	
	@OneToMany(mappedBy="product", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Barcode> barcodes;
	
	@OneToMany(mappedBy="product", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<PriceHistory> priceHistory;
	
	@OneToMany(mappedBy="product", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<ProductPicture> pictures;
	
	@OneToMany(mappedBy="product", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<EntryOfGoods> entriesOfGoods;
	
	@OneToMany(mappedBy="product", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<ProductVatRate> vatRates;
	
	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}