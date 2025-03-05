package com.marcelo721.SEI.repositories;

import com.marcelo721.SEI.entities.Video;
import com.marcelo721.SEI.web.dto.VideoDto.VideoResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findByTopicId(Long idTopic);

}
