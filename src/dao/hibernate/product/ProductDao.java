package dao.hibernate.product;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.hibernate.HibernateGenericDao;
import dao.hibernate.PersistenceManager;
import dto.entity.Barcode;
import dto.entity.Product;

public class ProductDao extends HibernateGenericDao<Product, Long>{

	public ProductDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Product> findProductsByBarcode(String barcode){
		List<Product> products = new ArrayList<>();
		EntityManager em = PersistenceManager.getInstance().createEntityManager();
		List<Barcode> barcodeList = null;
		Query barcodeQuery = em.createQuery("SELECT b FROM dto.entity.Barcode b WHERE b.barcode = :barcode", Barcode.class);
		barcodeQuery.setParameter("barcode", barcode);
		barcodeList = barcodeQuery.getResultList();
		if(barcodeList != null){
			for(Barcode b : barcodeList)
				products.add(b.getProduct());
		}
		em.close();
		return products;
	}
}