package com.example.QUIZ.DAO;

import com.example.QUIZ.ENTITY.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Questiondao extends JpaRepository<Questions, Integer> {
    List<Questions> findByDifficultylevel(String level);
}
