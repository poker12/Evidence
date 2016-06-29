package dto.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.annotations.DynamicUpdate;

@Entity(name="personal_contacts")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("PERSON")
@DynamicUpdate
public class PersonalContact extends ContactInformation{

	@Column(name="person_academic_degree", nullable=true, length=20)
	private String personAcademicDegree;
	
	@Column(name="person_name", /*nullable=false*/ nullable=true, length=60)
	private String personName;
	
	@Column(name="person_surename", /*nullable=false*/ nullable=true, length=70)
	private String personSurename;
	
	@Override
	public String toString() {
		return personName + " " + personSurename;
	}

	public String getPersonAcademicDegree() {
		return personAcademicDegree;
	}

	public void setPersonAcademicDegree(String personAcademicDegree) {
		this.personAcademicDegree = personAcademicDegree;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonSurename() {
		return personSurename;
	}

	public void setPersonSurename(String personSurename) {
		this.personSurename = personSurename;
	}

	
}