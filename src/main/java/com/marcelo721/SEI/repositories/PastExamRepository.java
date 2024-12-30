package com.marcelo721.SEI.repositories;

import com.marcelo721.SEI.entities.PastExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PastExamRepository extends JpaRepository<PastExam, Long> {
}
