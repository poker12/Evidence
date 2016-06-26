package service.product;

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
	
}
