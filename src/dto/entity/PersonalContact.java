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
	
}