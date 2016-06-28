package service.entryOfGoods;

import java.util.List;

import dao.hibernate.entryOfGoods.EntryOfGoodsDao;
import dto.entity.EntryOfGoods;

public class EntryOfGoodsService {

	public List<EntryOfGoods> getAll(){
		EntryOfGoodsDao dao = new EntryOfGoodsDao();
		return dao.getAll();
	}
	
}
