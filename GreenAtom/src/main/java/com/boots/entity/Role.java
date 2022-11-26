package com.boots.entity;

import java.util.Set;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "user_role")
public class Role implements GrantedAuthority {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long rolescod;
    private String role_cod;
    private String role_name;
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
    

    public Role() {
    	
    }
    public Role(Long rolescod) {
        this.rolescod = rolescod;
    }

    public Role(Long rolescod, String role_cod) {
        this.rolescod = rolescod;
        this.role_cod = role_cod;
    }

	public String getRole_code() {
		return role_cod;
	}

	public void setRole_code(String role_cod) {
		this.role_cod = role_cod;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	@Override
	public String getAuthority() {
		return "ROLE_"+getRole_code();
	}
}
