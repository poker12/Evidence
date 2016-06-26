package service.product;

import java.util.List;

import dao.hibernate.product.ProductDao;
import dto.entity.Product;

public class ProductService {

	public ProductService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void persist(Product product){
		ProductDao pd = new ProductDao();
		pd.persist(product);
	}
	
	public List<Product> getAll(){
		ProductDao pd = new ProductDao();
		return pd.getAll();
	}
}
