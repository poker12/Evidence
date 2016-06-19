package dto.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicUpdate;

@Entity(name="contact_information")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="contact_type", discriminatorType=DiscriminatorType.STRING, length=20)
@DynamicUpdate
public class ContactInformation {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="country", nullable=false, length=50)
	private String country;
	
	@Column(name="zip_code", nullable=false, length=20)
	private String zipCode;
	
	@Column(name="city", nullable=false, length=70)
	private String city;
	
	@Column(name="street", nullable=false, length=150)
	private String street;
	
	@Column(name="contact_phone_number", nullable=false, length=20)
	private String contactPhoneNumber;
	
	@Column(name="contact_email", nullable=false, length=100)
	private String contactEmail;
	
	@Column(name="contact_person", nullable=true, length=120)
	private String contactPerson;

	@ManyToOne
	@JoinColumn(name="user_secondary_contact", nullable=true)
	private User userSecondaryContact;

}