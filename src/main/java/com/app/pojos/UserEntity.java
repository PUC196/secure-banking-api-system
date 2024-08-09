package com.app.pojos;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
@Table(name="sec_users")
public class UserEntity extends BaseEntity {

	    @Column(length = 30,unique = true)
	    private String userName;
	    @Column(length = 30,unique = true)
	    private String email;
	    @Column(length = 300)
	    private String password;
	    private boolean active;
	    @ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(name = "user_roles",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
	    private Set<Role>roles=new HashSet<>();
	    
}
	 
		