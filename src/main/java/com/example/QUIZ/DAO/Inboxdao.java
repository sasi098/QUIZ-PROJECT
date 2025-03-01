package com.example.QUIZ.DAO;

import com.example.QUIZ.ENTITY.Inbox;
import com.example.QUIZ.ENTITY.Inboxoutputwrappper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Inboxdao extends JpaRepository<Inbox, Integer> {
    boolean existsByTitle(String title);

    Inbox findByTitle(String title);
}
