package com.example.QUIZ.SERVICES;

import com.example.QUIZ.CONTROLLER.Newquizcontroller;
import com.example.QUIZ.DAO.Newquizcontrollerdao;
import com.example.QUIZ.DAO.Questiondao;
import com.example.QUIZ.ENTITY.QuestionWrapper;
import com.example.QUIZ.ENTITY.Questions;
import com.example.QUIZ.ENTITY.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Newquizcontrollerservice {

    @Autowired
    Newquizcontrollerdao newquizcontrollerdao;
    @Autowired
    Questiondao questiondao;

    public ResponseEntity<String> add(String difficultylevel, int num, String title) {

        List<Questions> questions = questiondao.findRnadonQuestions(difficultylevel,num);
        if (questions.isEmpty()) {
            return new ResponseEntity<>("No questions found for the given difficulty level", HttpStatus.BAD_REQUEST);
        }

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        newquizcontrollerdao.save(quiz);

        return new ResponseEntity<>("ok success on adding new quiz", HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getquiz(Integer quizid) {
        Optional<Quiz> quiz = newquizcontrollerdao.findById(quizid);
        List<Questions> questiondb = quiz.get().getQuestions();
        List<QuestionWrapper> questionuser = new ArrayList<>();

        for(Questions q : questiondb){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestion(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4(),q.getDifficultylevel());
            questionuser.add(qw);
        }

        return new ResponseEntity<>(questionuser,HttpStatus.OK);
    }
}
