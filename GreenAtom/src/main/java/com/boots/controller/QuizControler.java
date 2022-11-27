package com.boots.controller;

import com.boots.service.UserService;

import java.util.ArrayList;

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

    @GetMapping(value = "/quiz/{task}/{qestion}")
    public ResponseEntity<ArrayList<String>> isVacancyActive(@PathVariable(name = "task", required = true) String task, 
    		@PathVariable(name = "question", required = true)String qestion) {
    	return new ResponseEntity<ArrayList<String>>(userService.getQuestion(qestion, Integer.valueOf(task)), HttpStatus.OK);
    }
}
