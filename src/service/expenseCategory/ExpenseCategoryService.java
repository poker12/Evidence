package service.expenseCategory;

import java.util.List;

import dao.hibernate.expenseCategory.ExpenseCategoryDao;
import dto.entity.ExpenseCategory;

public class ExpenseCategoryService {

	public void persist(ExpenseCategory expenseCategory){
		ExpenseCategoryDao dao = new ExpenseCategoryDao();
		dao.persist(expenseCategory);
	}

	public List<ExpenseCategory> getAll(){
		ExpenseCategoryDao dao = new ExpenseCategoryDao();
		return dao.getAll();
	}
	
}
