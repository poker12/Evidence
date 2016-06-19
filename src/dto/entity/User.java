package dto.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicUpdate;

@Entity(name="users")
@DynamicUpdate
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="login", nullable=false, unique=true, length=30)
	private String login;
	
	@Column(name="password", nullable=false, length=50)
	private String password;
	
	@Column(name="user_type", nullable=false, length=20)
	private String userType; //customer or employee
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="contact_id", nullable=false, unique=true)
	private ContactInformation contact; //domovska adresa, a jine udaje zakaznika/zamestance

	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="users_roles",
			joinColumns={@JoinColumn(name="user_id", table="users")},
			inverseJoinColumns={@JoinColumn(name="role_id", table="roles")})
	private List<Role> roles;
	
	@OneToMany(mappedBy="userSecondaryContact", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<ContactInformation> secondaryContacts;
	
	@OneToMany(mappedBy="orderingCustomer", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Order> orders;

}