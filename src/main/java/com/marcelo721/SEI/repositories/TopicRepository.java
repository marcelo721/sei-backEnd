package com.marcelo721.SEI.repositories;

import com.marcelo721.SEI.entities.Subject;
import com.marcelo721.SEI.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    @Query("SELECT a.topics FROM Subject a WHERE a.id = :idSubject")
    List<Topic> findTopicsBySubject(Long idSubject);
}
