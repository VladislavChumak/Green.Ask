package com.boots.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userscod;
    private String fio;
    private String telephone_number;
    private String mail;
    private String comment;
    private String code;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Vacancy> vacancy;
    
    
    public User() {}

    public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public String getTelephone_number() {
		return telephone_number;
	}

	public void setTelephone_number(String telephone_number) {
		this.telephone_number = telephone_number;
	}

	public Long getId() {
        return userscod;
    }

    public void setId(Long userscod) {
        this.userscod = userscod;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail= mail;
    }

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Set<Role> getRoles() {
		for(Role role : roles) {
			System.out.println(role.getAuthority());
		}
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
    	return getRoles();
	}

	@Override
	public String getPassword() {
		return code;
	}
	public void setPassword(String password) {
        this.code = password;
    }
	
	@Override
	public String getUsername() {
		return mail;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
