package com.boots.controller;

import com.boots.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuizControler {
    @Autowired
    private UserService userService;

    @GetMapping(value = "quiz/{task}/{qestion}")
    public ResponseEntity<?> isVacancyActive(@PathVariable(name = "task", required = true) String vacancy_name) {
    	return userService.isVacancyActive(vacancy_name) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.FOUND);
    }
}
