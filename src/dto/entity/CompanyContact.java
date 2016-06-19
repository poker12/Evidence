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

	@Column(name="company_name", /*nullable=false*/ nullable=true, length=120)
	private String companyName;
	
	@Column(name="company_tin", /*nullable=false*/ nullable=true, length=30)
	private String companyTin; //iè
	
	@Column(name="company_vatin", /*nullable=false*/ nullable=true, length=20)
	private String companyVatin; //diè (max 20 znakù)

}
