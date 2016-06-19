package dto.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.DynamicUpdate;

@Entity(name="permissions")
@DynamicUpdate
public class Permission {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="name", nullable=false, unique=true, length=70)
	private String name;

	@ManyToMany(mappedBy="permissions")
	private List<Role> roles;

}