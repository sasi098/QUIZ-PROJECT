package com.example.QUIZ.DAO;

import com.example.QUIZ.ENTITY.Inbox;
import com.example.QUIZ.ENTITY.InboxWrapper;
import com.example.QUIZ.ENTITY.Inboxall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Inboxalldao extends JpaRepository<Inboxall,Integer> {

    boolean existsByTitle(String title);

    Inboxall findByTitle(String title);

    @Query(value = "select title from inboxall group by title",nativeQuery = true)
    List<InboxWrapper> getallinboxes();
}
