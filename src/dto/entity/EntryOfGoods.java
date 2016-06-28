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

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity(name="entry_of_goods")
@DynamicInsert
@DynamicUpdate
public class EntryOfGoods {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="product_quantity", nullable=false)
	private Long productQuantity;
	
	//price from expense
	@Column(name="price_per_piece", nullable=false, precision=10, scale=2)
	private BigDecimal pricePerPiece;
	
	//value from expense
	@Column(name="vat_rate", nullable=false, precision=5, scale=2)
	private BigDecimal vatRate;
	
	@Column(name="with_vat", nullable=false)
	private Boolean withVat;
	
	@Column(name="added", nullable=false, insertable=false, columnDefinition="datetime default now()")
	private LocalDateTime added;
	
	@ManyToOne
	@JoinColumn(name="product_id", nullable=false, updatable=false)
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="expense_id", nullable=false)
	private Expense expense;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Long productQuantity) {
		this.productQuantity = productQuantity;
	}

	public BigDecimal getPricePerPiece() {
		return pricePerPiece;
	}

	public void setPricePerPiece(BigDecimal pricePerPiece) {
		this.pricePerPiece = pricePerPiece;
	}

	public BigDecimal getVatRate() {
		return vatRate;
	}

	public void setVatRate(BigDecimal vatRate) {
		this.vatRate = vatRate;
	}

	public Boolean getWithVat() {
		return withVat;
	}

	public void setWithVat(Boolean withVat) {
		this.withVat = withVat;
	}

	public LocalDateTime getAdded() {
		return added;
	}

	public void setAdded(LocalDateTime added) {
		this.added = added;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Expense getExpense() {
		return expense;
	}

	public void setExpense(Expense expense) {
		this.expense = expense;
	}

	public EntryOfGoods(Long id, Long productQuantity, BigDecimal pricePerPiece, BigDecimal vatRate, Boolean withVat,
			LocalDateTime added, Product product, Expense expense) {
		super();
		this.id = id;
		this.productQuantity = productQuantity;
		this.pricePerPiece = pricePerPiece;
		this.vatRate = vatRate;
		this.withVat = withVat;
		this.added = added;
		this.product = product;
		this.expense = expense;
	}

	public EntryOfGoods(Long productQuantity, BigDecimal pricePerPiece, BigDecimal vatRate, Boolean withVat,
			Product product, Expense expense) {
		super();
		this.productQuantity = productQuantity;
		this.pricePerPiece = pricePerPiece;
		this.vatRate = vatRate;
		this.withVat = withVat;
		this.product = product;
		this.expense = expense;
	}

	public EntryOfGoods(Long productQuantity, BigDecimal pricePerPiece, BigDecimal vatRate, Boolean withVat,
			Product product) {
		super();
		this.productQuantity = productQuantity;
		this.pricePerPiece = pricePerPiece;
		this.vatRate = vatRate;
		this.withVat = withVat;
		this.product = product;
	}

	@Override
	public String toString() {
		return "EntryOfGoods [id=" + id + ", productQuantity=" + productQuantity + ", pricePerPiece=" + pricePerPiece
				+ ", vatRate=" + vatRate + ", withVat=" + withVat + ", added=" + added + ", product=" + product
				+ ", expense=" + expense + "]";
	}

	public EntryOfGoods() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}