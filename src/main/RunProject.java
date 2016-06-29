package main;

import java.util.List;

import dao.hibernate.PersistenceManager;
import dao.hibernate.databaseVendor.MySqlFunctionsDao;
import dao.hibernate.order.OrderDao;
import dao.hibernate.productCategory.ProductCategoryDao;
import dao.hibernate.productCategory.ProductCategoryLevelContainer;
import dto.entity.ProductCategory;

public class RunProject {

	public static void main(String[] args) {
		System.out.println("Running...");
	
		OrderDao dao = new OrderDao();
		System.out.println(dao.getCountOfInvoicesInYear(2016));
		
		if(PersistenceManager.getInstance().isOpen())
			PersistenceManager.getInstance().close();
		System.out.println("End");
	}
}
