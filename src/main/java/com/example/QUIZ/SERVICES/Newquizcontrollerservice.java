package com.example.QUIZ.SERVICES;

import com.example.QUIZ.CONTROLLER.Newquizcontroller;
import com.example.QUIZ.DAO.Newquizcontrollerdao;
import com.example.QUIZ.DAO.Questiondao;
import com.example.QUIZ.ENTITY.QuestionWrapper;
import com.example.QUIZ.ENTITY.Questions;
import com.example.QUIZ.ENTITY.Quiz;
import com.example.QUIZ.ENTITY.Response;
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
        Optional<Quiz> quiz = newquizcontrollerdao.findById(quizid);//FIRST GET QUIZ BY USING QUIZ NUM
        List<Questions> questiondb = quiz.get().getQuestions();//ANG THE QUESTIONS IN THAT QUIZ
        List<QuestionWrapper> questionuser = new ArrayList<>();//STORE THAT QUESTION IN QUESTION USER

        for(Questions q : questiondb){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestion(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4(),q.getDifficultylevel());
            questionuser.add(qw);
        }

        return new ResponseEntity<>(questionuser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculcte(Integer id, List<Response> response) {
        Quiz quiz = newquizcontrollerdao.findById(id).get();
        int result = 0;
        int i =0;
        List<Questions> question = quiz.getQuestions();

        for(Response res : response){
            if(res.getResult().equals(question.get(i).getRightanswer())){
                result++;
            }
            i++;
        }

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
