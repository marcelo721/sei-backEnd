package com.marcelo721.SEI.repositories;

import com.marcelo721.SEI.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
}
