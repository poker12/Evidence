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

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity(name="products")
@DynamicUpdate
@DynamicInsert
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
	
	@OneToMany(mappedBy="product", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Barcode> barcodes;
	
	@OneToMany(mappedBy="product", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<PriceHistory> priceHistory;
	
	@OneToMany(mappedBy="product", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<ProductPicture> pictures;
	
	@OneToMany(mappedBy="product", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<EntryOfGoods> entriesOfGoods;
	
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

	public Product(String name, Boolean archived, String description, Boolean visibilityInEshop, Long quantity,
			ProductCategory category) {
		super();
		this.name = name;
		this.archived = archived;
		this.description = description;
		this.visibilityInEshop = visibilityInEshop;
		this.quantity = quantity;
		this.category = category;
	}
	
	public Product(Long id, String name, Boolean archived, String description, Boolean visibilityInEshop, Long quantity,
			ProductCategory category, List<OrderProduct> orderProductList, List<Barcode> barcodes,
			List<PriceHistory> priceHistory, List<ProductPicture> pictures, List<EntryOfGoods> entriesOfGoods) {
		super();
		this.id = id;
		this.name = name;
		this.archived = archived;
		this.description = description;
		this.visibilityInEshop = visibilityInEshop;
		this.quantity = quantity;
		this.category = category;
		this.orderProductList = orderProductList;
		this.barcodes = barcodes;
		this.priceHistory = priceHistory;
		this.pictures = pictures;
		this.entriesOfGoods = entriesOfGoods;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getArchived() {
		return archived;
	}

	public void setArchived(Boolean archived) {
		this.archived = archived;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getVisibilityInEshop() {
		return visibilityInEshop;
	}

	public void setVisibilityInEshop(Boolean visibilityInEshop) {
		this.visibilityInEshop = visibilityInEshop;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public List<OrderProduct> getOrderProductList() {
		return orderProductList;
	}

	public void setOrderProductList(List<OrderProduct> orderProductList) {
		this.orderProductList = orderProductList;
	}

	public List<Barcode> getBarcodes() {
		return barcodes;
	}

	public void setBarcodes(List<Barcode> barcodes) {
		this.barcodes = barcodes;
	}

	public List<PriceHistory> getPriceHistory() {
		return priceHistory;
	}

	public void setPriceHistory(List<PriceHistory> priceHistory) {
		this.priceHistory = priceHistory;
	}

	public List<ProductPicture> getPictures() {
		return pictures;
	}

	public void setPictures(List<ProductPicture> pictures) {
		this.pictures = pictures;
	}

	public List<EntryOfGoods> getEntriesOfGoods() {
		return entriesOfGoods;
	}

	public void setEntriesOfGoods(List<EntryOfGoods> entriesOfGoods) {
		this.entriesOfGoods = entriesOfGoods;
	}

	@Override
	public String toString() {
		return id + " | " + name;
	}
	
}
