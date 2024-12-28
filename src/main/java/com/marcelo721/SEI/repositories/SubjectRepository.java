package com.marcelo721.SEI.repositories;

import com.marcelo721.SEI.entities.Subject;
import com.marcelo721.SEI.entities.enums.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query("SELECT a.subjects FROM User a WHERE a.id = :idUser")
    List<Subject> findAllSubjectsByUserId(Long idUser);

    @Query("SELECT s FROM Subject s WHERE s.semester = :semester")
    List<Subject> findSubjectsBySemester(@Param("semester") Semester semester);

}
