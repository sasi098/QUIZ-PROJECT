package com.example.QUIZ.SERVICES;

import com.example.QUIZ.DAO.*;
import com.example.QUIZ.ENTITY.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Questionservice {

    @Autowired
     Questiondao questiondao;
//    @Autowired
//    Inboxdao inboxdao;
    @Autowired
    Allquestionsdao allquestionsdao;
    @Autowired
    Inboxalldao inboxalldao;


    public ResponseEntity<List<Questions>> getall() {
        try{
            return new ResponseEntity<>(questiondao.findAll() ,HttpStatus.OK);
        }
        catch(Exception e){
            e.getMessage();
        }
        return new ResponseEntity<>(new ArrayList<>() ,HttpStatus.BAD_REQUEST);
    }

    public List<Questions> getlevel(String level) {
        return questiondao.findByDifficultylevel(level);
    }

    public String addquestion(Questions question) {
        questiondao.save(question);
        return "success";
    }

    public List<Headingswrapper> gettables() {
        List<Headingswrapper> headings =  questiondao.getAlltables();
        return headings;
    }

    public List<Questions> getquestionfromtable(String name) {
        List<Questions> question = questiondao.getquestions(name);
        return question;
    }

    public List<Categorywrapper> getcategory() {
        List<Categorywrapper> category = questiondao.getcategory();
        return category;
    }

    public Inboxoutputwrappper createnewinbox(String title) {
        if(inboxalldao.existsByTitle(title)){
            Inboxoutputwrappper in1 = new Inboxoutputwrappper(1,"already exists");
            return in1;
        }

        Inboxall inbox = new Inboxall();
        inbox.setTitle(title);

        inboxalldao.save(inbox);
        Inboxoutputwrappper in2 = new Inboxoutputwrappper(0,"new inbox created");
        return  in2;

    }

    public String addquestionnintoinbox(String title, int quesid) {
        Optional<Allquestions> optionalquestions = allquestionsdao.findById(quesid);

        if(optionalquestions.isPresent()){
            Allquestions questions = optionalquestions.get();
//            System.out.println(questions);
            Inboxall in1 = inboxalldao.findByTitle(title);
//            System.out.println(in1);
            List<Allquestions> existing = in1.getAllquestions();
            existing.add(questions);
            in1.setAllquestions(existing);
            inboxalldao.save(in1);
            return "question added successfully";
        }
        else {
            return "question not added";
        }
    }

    public List<InboxWrapper> getallinboxes() {
        return inboxalldao.getallinboxes();
    }

    public List<Allquestions> getquestionfromname(int inboxid) {
        Optional<Inboxall> inbox1 = inboxalldao.findById(inboxid);

        List<Allquestions> questions = inbox1.get().getAllquestions();

        return questions;
    }

    public List<Allquestions> deletequesfrominbox(int inboxid, int quesid) {
        Optional<Inboxall> inbox1 = inboxalldao.findById(inboxid);
        Inboxall inbox = inbox1.get();
        List<Allquestions> allquestions  = inbox.getAllquestions();
        boolean removed = allquestions.removeIf(question -> question.getId() == quesid);

        if(removed){
            inboxalldao.save(inbox);
            return allquestions;
        }

        return allquestions;

    }
}
