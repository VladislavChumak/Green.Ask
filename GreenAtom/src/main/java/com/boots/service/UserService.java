package com.boots.service;

import com.boots.entity.Role;
import com.boots.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
	@PersistenceContext
    private EntityManager em;
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		System.out.println(mail);
		User user = em.createQuery("SELECT u FROM User u WHERE u.mail = :mail", User.class)
                .setParameter("mail", mail).getSingleResult();
        if (user == null) {
        	System.out.println("Упали((");
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println(user.getFio());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return user;
    }

    public List<User> allUsers() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Transactional
    public boolean saveUser(User user) {
    	if (em.createQuery("SELECT u FROM User u "
    			+ "Where u.telephone_number = :number or u.mail= :mail", User.class)
    			.setParameter("number", user.getTelephone_number())
    			.setParameter("mail", user.getMail()).getResultList().size()!=0) {
            return false;
        }
    	user.setRoles(Collections.singleton(em.find(Role.class, 1L)));
    	String code = "1234";
    	Mail.sendMessage(user.getMail(), code);
    	user.setPassword(code);
    	em.persist(user);
    	return true;
    }

    public boolean deleteUser(Long userId) {
        if (em.createQuery("SELECT u FROM User u WHERE u.userscod = :paramId", User.class)
                .setParameter("paramId", userId).getMaxResults()==1) {
        	int code = em.createQuery("Delete FROM User u WHERE u.userscod= :paramId", User.class)
            .setParameter("paramId", userId).executeUpdate();
        	return code == 1 ? true : false;
        }
        
        return false;
    }

    public List<User> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
    
    public boolean isVacancyActive(String vacancy_name) {
    	
    	return true;
    }
    
}
