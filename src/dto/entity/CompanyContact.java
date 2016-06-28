package dto.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.annotations.DynamicUpdate;

@Entity(name="company_contacts")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("COMPANY")
@DynamicUpdate
public class CompanyContact extends ContactInformation{

	@Column(name="company_name", nullable=true, length=120)
	private String companyName;
	
	@Column(name="company_tin", nullable=true, length=30)
	private String companyTin; //iè
	
	@Column(name="company_vatin", nullable=true, length=20)
	private String companyVatin; //diè

	@Column(name="saved_supplier", nullable=true, columnDefinition="bit(1) default 0")
	private Boolean savedSupplier;
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyTin() {
		return companyTin;
	}

	public void setCompanyTin(String companyTin) {
		this.companyTin = companyTin;
	}

	public String getCompanyVatin() {
		return companyVatin;
	}

	public void setCompanyVatin(String companyVatin) {
		this.companyVatin = companyVatin;
	}

	public Boolean getSavedSupplier() {
		return savedSupplier;
	}

	public void setSavedSupplier(Boolean savedSupplier) {
		this.savedSupplier = savedSupplier;
	}

	public CompanyContact(String country, String zipCode, String city, String street, String contactPhoneNumber,
			String contactEmail, String contactPerson, String companyName, String companyTin, String companyVatin) {
		super(country, zipCode, city, street, contactPhoneNumber, contactEmail, contactPerson);
		this.companyName = companyName;
		this.companyTin = companyTin;
		this.companyVatin = companyVatin;
	}

	public CompanyContact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompanyContact(String country, String zipCode, String city, String street, String contactPhoneNumber,
			String contactEmail, String contactPerson, String companyName, String companyTin, String companyVatin,
			Boolean savedSupplier) {
		super(country, zipCode, city, street, contactPhoneNumber, contactEmail, contactPerson);
		this.companyName = companyName;
		this.companyTin = companyTin;
		this.companyVatin = companyVatin;
		this.savedSupplier = savedSupplier;
	}

	@Override
	public String toString() {
		if(companyName == null)
			return null;
		return companyName + " (" + companyTin + ")";
	}

	
}
