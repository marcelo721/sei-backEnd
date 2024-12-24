package com.marcelo721.SEI.repositories;

import com.marcelo721.SEI.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query("SELECT a.subjects FROM User a WHERE a.id = :idUser")
    List<Subject> findAllSubjectsByUserId(Long idUser);
}
