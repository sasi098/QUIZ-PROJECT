package com.example.QUIZ.CONTROLLER;

import com.example.QUIZ.ENTITY.Questions;
import com.example.QUIZ.SERVICES.Questionservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Quizcontroller {

    @Autowired
    Questionservice questionservice;

    @GetMapping("/questions")
    public List<Questions> questions(){
        List<Questions> questions = questionservice.getall();
//        System.out.println(questions);
        return questions;
    }

    @GetMapping("/category/{level}")
    public List<Questions> getlevel(@PathVariable String level ){
        List<Questions> questions = questionservice.getlevel(level);
        return questions;
    }

    @PostMapping("/questions")
    public String addquestions(@RequestBody Questions question){
        String out = questionservice.addquestion(question);
        return "question added successfully" + out;
    }

}
