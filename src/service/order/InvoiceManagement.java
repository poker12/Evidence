package service.order;

import dao.hibernate.order.OrderDao;
import dto.entity.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InvoiceManagement {

	private ObservableList<Order> allInvoices = FXCollections.observableArrayList();
	
	public void refreshInvoiceList(){
		OrderDao dao = new OrderDao();
		allInvoices.addAll(dao.getAllInvoices());
	}

	public ObservableList<Order> getAllInvoices() {
		return allInvoices;
	}

	public void setAllInvoices(ObservableList<Order> allInvoices) {
		this.allInvoices = allInvoices;
	}
	
	
}
