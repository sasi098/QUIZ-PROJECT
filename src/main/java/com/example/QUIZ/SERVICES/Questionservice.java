package com.example.QUIZ.SERVICES;

import com.example.QUIZ.DAO.Questiondao;
import com.example.QUIZ.ENTITY.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Questionservice {

    @Autowired
    Questiondao questiondao;

    public List<Questions> getall() {
        return questiondao.findAll();
    }

    public List<Questions> getlevel(String level) {
        return questiondao.findByDifficultylevel(level);
    }

    public String addquestion(Questions question) {
        questiondao.save(question);
        return "success";
    }
}
