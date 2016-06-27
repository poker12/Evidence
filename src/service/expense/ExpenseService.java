package service.expense;

import java.util.List;

import dao.hibernate.expense.ExpenseDao;
import dto.entity.Expense;

public class ExpenseService {

	public void persist(Expense expense){
		ExpenseDao dao = new ExpenseDao();
		dao.persist(expense);
	}

	public List<Expense> getAll(){
		ExpenseDao dao = new ExpenseDao();
		return dao.getAll();
	}
}
