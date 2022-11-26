package com.boots.controller;

import com.boots.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SelectControler {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/isVacancyActive/{name}")
    public ResponseEntity<?> isVacancyActive(@PathVariable(name = "name", required = true) String vacancy_name) {
    	if(userService.user == null)return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    	else {
    		return userService.isVacancyActive(vacancy_name) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.FOUND);
    	}
    }
}
