package com.example.QUIZ.DAO;

import com.example.QUIZ.ENTITY.Allquestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Allquestionsdao extends JpaRepository<Allquestions , Integer> {
}
