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

import org.hibernate.annotations.DynamicUpdate;

@Entity(name="roles")
@DynamicUpdate
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="name", nullable=false, unique=true, length=70)
	private String name;
	
	@ManyToMany(mappedBy="roles")
	private List<User> users;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="roles_permissions", 
		joinColumns={@JoinColumn(name="role_id", table="roles")},
		inverseJoinColumns={@JoinColumn(name="permission_id", table="permissons")})
	private List<Permission> permissions;
}