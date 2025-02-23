package com.example.QUIZ.DAO;

import com.example.QUIZ.ENTITY.Questions;
import com.example.QUIZ.ENTITY.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Newquizcontrollerdao extends JpaRepository<Quiz, Integer> {

}
