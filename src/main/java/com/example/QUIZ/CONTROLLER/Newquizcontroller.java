package com.example.QUIZ.CONTROLLER;


import com.example.QUIZ.ENTITY.QuestionWrapper;
import com.example.QUIZ.ENTITY.Questions;
import com.example.QUIZ.ENTITY.Quiz;
import com.example.QUIZ.SERVICES.Newquizcontrollerservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class Newquizcontroller {

    @Autowired
    Newquizcontrollerservice newquizcontrollerservice;

    @GetMapping("/create")
//    POST MAN LINK FOR CREATING QUIZ = http://localhost:8080/quiz/create?difficultylevel=easy&num=5&title=quiz1
    public ResponseEntity<String> createquiz(@RequestParam String difficultylevel, @RequestParam int num, @RequestParam String title) {
        return newquizcontrollerservice.add(difficultylevel, num, title);
    }

    @GetMapping("/quiz/{quizid}")
    public ResponseEntity<List<QuestionWrapper>> getquiz(@PathVariable Integer quizid){
        return newquizcontrollerservice.getquiz(quizid);
    }

}
