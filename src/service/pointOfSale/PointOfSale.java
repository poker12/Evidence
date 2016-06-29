package service.pointOfSale;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dao.hibernate.databaseVendor.MySqlFunctionsDao;
import dao.hibernate.order.OrderDao;
import dao.hibernate.product.ProductDao;
import dto.entity.CompanyContact;
import dto.entity.Order;
import dto.entity.OrderProduct;
import dto.entity.Product;
import dto.entity.VatRateSummary;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import service.PriceManager;

public class PointOfSale {
	
	private ObservableList<OrderProduct> itemList = FXCollections.observableArrayList();
	private CompanyContact company = null;
	private Boolean sellingToCompany = false;
	private Product selectedProduct = null;
	private ObservableList<Product> allProducts = FXCollections.observableArrayList();
	private String methodOfPayment = "hotovì";
	
	public BigDecimal getSummarizedBillPriceWithVat(){
		BigDecimal summary = new BigDecimal(0);
		for(OrderProduct i : itemList){
			if(i.getWithVat()){
				summary = summary.add(i.getPricePerUnit().multiply(new BigDecimal(i.getProductQuantity())));
			}
			else
				summary = summary.add(new PriceManager().calculatePriceWithVat(i.getPricePerUnit(), i.getVatRate(), 2).multiply(new BigDecimal(i.getProductQuantity())));
		}
		return summary;
	}
	
	public BigDecimal getSummarizedBillPriceWithoutVat(){
		BigDecimal summary = new BigDecimal(0);
		for(OrderProduct i : itemList){
			if(!i.getWithVat()){
				summary = summary.add(i.getPricePerUnit().multiply(new BigDecimal(i.getProductQuantity())));
			}
			else
				summary = summary.add(new PriceManager().calculatePriceWithoutVat(i.getPricePerUnit(), i.getVatRate(), 2).multiply(new BigDecimal(i.getProductQuantity())));
		}
		return summary;
	}
	
	public BigDecimal getPricePerUniWithVat(OrderProduct o){
		if(o.getWithVat()){
			return o.getPricePerUnit();
		}
		return new PriceManager().calculatePriceWithVat(o.getPricePerUnit(), o.getVatRate(), 2);
	}
	
	private BigDecimal getOrderProductSummaryPriceWithoutVat(OrderProduct orderProduct){
		if(orderProduct.getWithVat())
			return new PriceManager().calculatePriceWithoutVat(orderProduct.getPricePerUnit(), orderProduct.getVatRate(), 2)
					.multiply(new BigDecimal(orderProduct.getProductQuantity()));
		return orderProduct.getPricePerUnit()
				.multiply(new BigDecimal(orderProduct.getProductQuantity()));
	}
	
	public BigDecimal getOrderProductSummaryPriceWithVat(OrderProduct orderProduct){
		if(!orderProduct.getWithVat())
			return new PriceManager().calculatePriceWithVat(orderProduct.getPricePerUnit(), orderProduct.getVatRate(), 2)
					.multiply(new BigDecimal(orderProduct.getProductQuantity()));
		return orderProduct.getPricePerUnit()
				.multiply(new BigDecimal(orderProduct.getProductQuantity()));
	}
	
	public List<VatRateSummary> getBillVatRateSummaries(){
		List<VatRateSummary> list = new ArrayList<>();
		for(OrderProduct p : itemList){
			boolean exists = false;
			for(VatRateSummary v : list){
				if(v.getVatRate().equals(p.getVatRate())){
					v.setSummaryWithoutVat(v.getSummaryWithoutVat().add(getOrderProductSummaryPriceWithoutVat(p)));
					v.setVatValue(v.getVatValue().add(new PriceManager().calculateVatPriceFromPriceWithoutVat(getOrderProductSummaryPriceWithoutVat(p), p.getVatRate(), 2)));
					v.setSummaryWithVat(v.getSummaryWithVat().add(getOrderProductSummaryPriceWithVat(p)));
					exists = true;
					break;
				}
			}
			if(exists == false){
				VatRateSummary v = new VatRateSummary(
						getOrderProductSummaryPriceWithoutVat(p),
						p.getVatRate(), new PriceManager().calculateVatPriceFromPriceWithoutVat(getOrderProductSummaryPriceWithoutVat(p), p.getVatRate(), 2),
						getOrderProductSummaryPriceWithVat(p));
				list.add(v);
			}
		}
		return list;
	}
	
	public void createOrder(){
		Order order;
		MySqlFunctionsDao mySqlFunctionsDao = new MySqlFunctionsDao();
		OrderDao orderDao = new OrderDao();
		LocalDateTime currentDbDateTime = mySqlFunctionsDao.getCurrentDateTime();
		Long countOfInvoiceInYear = orderDao.getCountOfInvoicesInYear(currentDbDateTime.getYear());
		List<VatRateSummary> vatRateSummaries = getBillVatRateSummaries();
		order = new Order(currentDbDateTime, currentDbDateTime.toLocalDate(), currentDbDateTime, currentDbDateTime,
				currentDbDateTime, (String.valueOf(currentDbDateTime.getYear()) + "/" + String.valueOf(countOfInvoiceInYear + 1)), Boolean.valueOf(false), currentDbDateTime.toLocalDate(),
				methodOfPayment, getSummarizedBillPriceWithVat(), vatRateSummaries,
				company, itemList);
		for(VatRateSummary v : vatRateSummaries){
			v.setOrder(order);
		}
		for(OrderProduct o : itemList){
			o.setOrder(order);
		}
		orderDao.persist(order);
		ProductDao productDao = new ProductDao();
		for(OrderProduct orderProduct : itemList){
			Product product = orderProduct.getProduct();
			product.setQuantity(product.getQuantity() - orderProduct.getProductQuantity());
			productDao.updateIfExists(product, product.getId());
		}
		clear();
	}
	
	public void clear(){
		itemList.clear();
		company = null;
		sellingToCompany = false;
		selectedProduct = null;
		refreshListOfAllProducts();
	}
	
	public ObservableList<Product> getAllProducts() {
		return allProducts;
	}

	public void setAllProducts(ObservableList<Product> allProducts) {
		this.allProducts = allProducts;
	}

	public void refreshListOfAllProducts(){
		ProductDao dao = new ProductDao();
		List<Product> list = dao.getAll();
		if(list == null){
			allProducts.clear();
			return;
		}
		allProducts.clear();
		allProducts.addAll(list);
	}
	
	public void findProduct(Product product){
		if(product == null){
			selectedProduct = null;
			return;
		}
		ProductDao dao = new ProductDao();
		Product updatedProduct = dao.findById(product.getId());
		if(updatedProduct == null){
			selectedProduct = null;
		}
		else{
			selectedProduct = updatedProduct;
		}
	}
	
	public void addItemToList(Long quantity){
		if(selectedProduct == null)
			return;
		OrderProduct orderProduct = new OrderProduct();
		orderProduct.setProduct(selectedProduct);
		orderProduct.setPricePerUnit(selectedProduct.getPriceHistory().get(0).getSellingPrice());
		orderProduct.setProductQuantity(quantity);
		orderProduct.setVatRate(selectedProduct.getPriceHistory().get(0).getVatRate());
		orderProduct.setWithVat(selectedProduct.getPriceHistory().get(0).getWithVat());
		itemList.add(orderProduct);
	}
	
	public void findProduct(String variable, ProductFindingParameter param){
		if(param == null)
			return;
		ProductDao dao = new ProductDao();
		if(param == ProductFindingParameter.FIND_BY_PLU){
			Product product = dao.findById(Long.valueOf(variable));
			if(product == null)
				selectedProduct = null;
			else
				selectedProduct = product;
		}
		else if(param == ProductFindingParameter.FIND_BY_BARCODE){
			List<Product> products = dao.findProductsByBarcode(variable);
			Product product = null;
			if(products != null){
				if(!products.isEmpty()){
					product = products.get(0);
					selectedProduct = product;
				}
				else
					selectedProduct = null;
			}
			else
				selectedProduct = null;
		}
		else{
			selectedProduct = null;
		}
	}
	
	public void setCompany(String country, String zipCode, String city, String street, String companyName, String companyTin, String companyVatin) {
		if(sellingToCompany == false)
			return;
		CompanyContact companyContact = new CompanyContact(country, zipCode, city,
				street, null, null, null, companyName, companyTin, companyVatin);
		this.company = companyContact;
	}
	
	public CompanyContact getCompany() {
		return company;
	}
	
	public Boolean getSellingToCompany() {
		return sellingToCompany;
	}
	public void setSellingToCompany(Boolean sellingToCompany) {
		this.sellingToCompany = sellingToCompany;
	}

	public Product getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(Product selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public void setCompany(CompanyContact company) {
		this.company = company;
	}

	public ObservableList<OrderProduct> getItemList() {
		return itemList;
	}

	public void setItemList(ObservableList<OrderProduct> itemList) {
		this.itemList = itemList;
	}
		
}