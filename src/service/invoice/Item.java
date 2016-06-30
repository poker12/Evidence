package service.invoice;

import java.math.BigDecimal;

public class Item {

	private String name;
	private BigDecimal pricePerUnit;
	private Long quantity;
	private BigDecimal vatRate;
	
	public Item(String name, BigDecimal pricePerUnit, Long quantity, BigDecimal vatRate) {
		super();
		this.name = name;
		this.pricePerUnit = pricePerUnit;
		this.quantity = quantity;
		this.vatRate = vatRate;
	}

	public Item() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(BigDecimal pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getVatRate() {
		return vatRate;
	}

	public void setVatRate(BigDecimal vatRate) {
		this.vatRate = vatRate;
	}
	
}
