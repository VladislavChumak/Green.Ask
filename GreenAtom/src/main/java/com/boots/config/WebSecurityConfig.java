package com.boots.config;

import com.boots.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/resources/**")
            .antMatchers("/assets/**")
            .antMatchers("assets/**")
            .antMatchers("/css/**")
            .antMatchers("/dist/**")
            .antMatchers("/publics/**");
    }
    
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
    	//httpSecurity.authorizeRequests().antMatchers("/resources/**").permitAll();
    	httpSecurity
        .csrf()
            .disable()
            .authorizeRequests()
            //Доступ только для не зарегистрированных пользователей
            .antMatchers("/").not().fullyAuthenticated()
            //Доступ только для пользователей с ролью Администратор
            //.antMatchers("/admin/**").hasRole("ADMIN")
            //.antMatchers("/news").hasRole("USER")
            //Доступ разрешен всем пользователей
            .antMatchers("/", "/resources/**").permitAll()
        //Все остальные страницы требуют аутентификации
        .anyRequest().authenticated()
        .and()
            //Настройка для входа в систему
            .formLogin()
            .loginPage("/login")
            //Перенарпавление на главную страницу после успешного входа
            .defaultSuccessUrl("/")
            .permitAll()
        .and()
            .logout()
            .permitAll()
            .logoutSuccessUrl("/");//*/

    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
