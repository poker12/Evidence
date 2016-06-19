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

@Entity(name="product_categories")
@DynamicUpdate
public class ProductCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="name", length=30, nullable=false)
	private String name;
	
	@ManyToOne
	@JoinColumn(name="supercategory_id", nullable=true)
	//@Column(name="supercategory_id", nullable=true)
	private ProductCategory superCategory;
	
	@OneToMany(mappedBy="category", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Product> products;
	
	@OneToMany(mappedBy="superCategory", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<ProductCategory> subcategories;
	
	public ProductCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductCategory(Long id, String name, ProductCategory superCategory) {
		super();
		this.id = id;
		this.name = name;
		this.superCategory = superCategory;
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

	public ProductCategory getSuperCategory() {
		return superCategory;
	}

	public void setSuperCategory(ProductCategory superCategory) {
		this.superCategory = superCategory;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<ProductCategory> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<ProductCategory> subcategories) {
		this.subcategories = subcategories;
	}
	
	
}