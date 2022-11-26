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
    private String role_code;
    private String role_name;
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
    

    public Role() {
    	
    }
    public Role(Long rolescod) {
        this.rolescod = rolescod;
    }

    public Role(Long rolescod, String role_code) {
        this.rolescod = rolescod;
        this.role_code = role_code;
    }

	public String getRole_code() {
		return role_code;
	}

	public void setRole_code(String role_code) {
		this.role_code = role_code;
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
