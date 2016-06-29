package dto.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

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
	
	@Column(name="contact_phone_number", nullable=true, length=20)
	private String contactPhoneNumber;
	
	@Column(name="contact_email", nullable=true, length=100)
	private String contactEmail;
	
	@Column(name="contact_person", nullable=true, length=120)
	private String contactPerson;

	@ManyToOne
	@JoinColumn(name="user_secondary_contact", nullable=true)
	private User userSecondaryContact;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getContactPhoneNumber() {
		return contactPhoneNumber;
	}

	public void setContactPhoneNumber(String contactPhoneNumber) {
		this.contactPhoneNumber = contactPhoneNumber;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public User getUserSecondaryContact() {
		return userSecondaryContact;
	}

	public void setUserSecondaryContact(User userSecondaryContact) {
		this.userSecondaryContact = userSecondaryContact;
	}

	public ContactInformation(Long id, String country, String zipCode, String city, String street,
			String contactPhoneNumber, String contactEmail, String contactPerson, User userSecondaryContact) {
		super();
		this.id = id;
		this.country = country;
		this.zipCode = zipCode;
		this.city = city;
		this.street = street;
		this.contactPhoneNumber = contactPhoneNumber;
		this.contactEmail = contactEmail;
		this.contactPerson = contactPerson;
		this.userSecondaryContact = userSecondaryContact;
	}

	public ContactInformation(String country, String zipCode, String city, String street, String contactPhoneNumber,
			String contactEmail, String contactPerson) {
		super();
		this.country = country;
		this.zipCode = zipCode;
		this.city = city;
		this.street = street;
		this.contactPhoneNumber = contactPhoneNumber;
		this.contactEmail = contactEmail;
		this.contactPerson = contactPerson;
	}

	public ContactInformation(String country, String zipCode, String city, String street, String contactPhoneNumber,
			String contactEmail, String contactPerson, User userSecondaryContact) {
		super();
		this.country = country;
		this.zipCode = zipCode;
		this.city = city;
		this.street = street;
		this.contactPhoneNumber = contactPhoneNumber;
		this.contactEmail = contactEmail;
		this.contactPerson = contactPerson;
		this.userSecondaryContact = userSecondaryContact;
	}

	public ContactInformation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ContactInformation(String country, String zipCode, String city, String street) {
		super();
		this.country = country;
		this.zipCode = zipCode;
		this.city = city;
		this.street = street;
	}

	
	
}