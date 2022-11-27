package com.boots.controller;

import com.boots.service.UserService;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuizControler {
    @Autowired
    private UserService userService;

    @GetMapping(value = "quiz/{task}/{question}")
    public ResponseEntity<ArrayList<String>> getQuestion(@PathVariable(name = "task", required = true) String task, 
    		@PathVariable(name = "question", required = true)String question) {
    	return new ResponseEntity<ArrayList<String>>(userService.getQuestion(task, Integer.valueOf(question)), HttpStatus.OK);
    }
    @GetMapping(value = "/quiz/{name}/{score}")
    public String getCongratulation(@PathVariable(name = "name", required = true) String name, 
    		@PathVariable(name = "score", required = true)String score) {
    		//userService.addUserVacancy(vacancy_name);
    	return "redirect:/finally?name="+name+"&score = "+score;
    }
    
    @GetMapping(value = "checkans/{task}/{question}/{answer}")
    public ResponseEntity<ArrayList<String>> GetRightAnswer(@PathVariable(name = "task", required = true) String task, 
    		@PathVariable(name = "question", required = true)String question,
    		@PathVariable(name = "answer", required = true)String answer) {
    	return new ResponseEntity<ArrayList<String>>(userService.getRightAnswer(task, Integer.valueOf(question), answer), HttpStatus.OK);
    }
    
    @GetMapping("/quiz/{name}")
    public String selectVacancy(@PathVariable(name = "name", required = true) String vacancy_name, Model model) {
    	//userService.addUserVacancy(vacancy_name);
    	return "redirect:/finally?name="+vacancy_name;
    }
}
