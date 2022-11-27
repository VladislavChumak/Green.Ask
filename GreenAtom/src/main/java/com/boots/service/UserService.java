package com.boots.service;

import com.boots.entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
	@PersistenceContext
    private EntityManager em;
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private User user;
	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		System.out.println(mail);
		try {
			user = em.createQuery("SELECT u FROM User u WHERE u.mail = :mail", User.class)
                .setParameter("mail", mail).getSingleResult();
		}
		catch(Exception e) {
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
    	System.out.println(user.getFio());
    	user.setRoles(Collections.singleton(em.find(Role.class, 1L)));
    	String code = "1234";
    	//Mail.sendMessage(user.getMail(), code);
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
    	System.out.print(user != null);
    	if(user != null) {
    		return 
    			em.createQuery("Select u From User u, Users_vacancy uv, Vacancy v "
						+ "Where u.userscod = uv.userscod "
						+ "and u.userscod = :userscod "
						+ "and uv.vacancyscod = v.vacancyscod "
						+ "and v.vacancy_name = :vacancy_name")
				.setParameter("userscod", user.getId())
				.setParameter("vacancy_name", vacancy_name)
				.getResultList().size()==0;
    		
    		//System.out.println(user.getFio());
    		
    	}
    	else return false;
    }
    
    @SuppressWarnings("unchecked")
	public ArrayList<String> getQuestion(String vacancy, int number){
    	ArrayList<String> list = new ArrayList<String>();
    	Questions q = (Questions) em.createQuery("Select q From Questions q, Vacancy_questions vq, Vacancy v "
    			+ "Where v.vacancy_name = :vacancy "
    			+ "and v.vacancyscod = vq.vacancyscod "
    			+ "and vq.questionscod = :number").getResultList().get(0);
    	list.add(q.getQuestionName());
    	ArrayList<Answers> alist = new ArrayList<Answers> (em.createQuery("Select q From Questions q, Vacancy_questions vq, Vacancy v "
    			+ "Where v.vacancy_name = :vacancy "
    			+ "and v.vacancyscod = vq.vacancyscod "
    			+ "and vq.questionscod = :number").getResultList());
    	for(Answers a : alist) {
    		list.add(a.getAnswerName());
    	}
    	
    	return list;
    }
}
