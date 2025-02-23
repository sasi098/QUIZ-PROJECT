package com.example.QUIZ.SERVICES;

import com.example.QUIZ.DAO.Questiondao;
import com.example.QUIZ.ENTITY.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Questionservice {

    @Autowired
    Questiondao questiondao;

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
}
