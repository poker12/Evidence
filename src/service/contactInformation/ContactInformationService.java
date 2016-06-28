package service.contactInformation;

import java.util.List;

import dao.hibernate.companyContact.CompanyContactDao;
import dto.entity.CompanyContact;

public class ContactInformationService {

	public List<CompanyContact> getSuppliers(){
		CompanyContactDao dao = new CompanyContactDao();
		return dao.getAllSuppliers();
	}
	
}
