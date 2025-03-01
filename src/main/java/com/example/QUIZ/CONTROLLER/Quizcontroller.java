package com.example.QUIZ.CONTROLLER;

import com.example.QUIZ.ENTITY.*;
import com.example.QUIZ.SERVICES.Questionservice;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class Quizcontroller {

    @Autowired
    Questionservice questionservice;

    @GetMapping("/hi")
    public String hi(){
        return "hi";
    }


    @GetMapping("/questions")
    public ResponseEntity<List<Questions>> questions(){
        return questionservice.getall();
//        System.out.println(questions);
//        return questions;
    }

    @GetMapping("/tables")
    public List<Headingswrapper> gettables(){
        return questionservice.gettables();
    }

    @GetMapping("/category")
    public List<Categorywrapper> getcategory(){
        return questionservice.getcategory();
    }

    @GetMapping("/tables/{name}")
    public List<Questions> getquestionfromtable(@PathVariable String name){
        return questionservice.getquestionfromtable(name);
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

    @GetMapping("/inbox/{title}")
    public Inboxoutputwrappper createinbox(@PathVariable String title){
        return questionservice.createnewinbox(title);
    }

    @GetMapping("/inbox/{title}/{quesid}")
    public String addquestoinintoinbox(@PathVariable String title,@PathVariable int quesid){
        return questionservice.addquestionnintoinbox(title,quesid);
    }

    @GetMapping("/inboxes")
    public List<InboxWrapper> getallinboxes(){
        return questionservice.getallinboxes();
    }

    @GetMapping("/inboxes/{inboxid}")
    public List<Allquestions> getqustionforminbox(@PathVariable int inboxid){
        return questionservice.getquestionfromname(inboxid);
    }

    @GetMapping("/inboxes/delete/{inboxid}/{quesid}")
    public List<Allquestions> dletequesfrominbox(@PathVariable int inboxid,@PathVariable int quesid){
        return questionservice.deletequesfrominbox(inboxid,quesid);
    }

//    @GetMapping("/create/{name}")
//    public String createtable(@RequestBody List<Questions>)

}
