package com.example.QUIZ.DAO;

import com.example.QUIZ.ENTITY.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Questiondao extends JpaRepository<Questions, Integer> {
    List<Questions> findByDifficultylevel(String level);

    @Query(value = "SELECT * FROM Questions WHERE difficultylevel = :difficultylevel ORDER BY RAND() LIMIT :num", nativeQuery = true)
    List<Questions> findRnadonQuestions(@Param("difficultylevel") String difficultylevel, @Param("num") int num);

}
