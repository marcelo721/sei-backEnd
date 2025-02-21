package com.marcelo721.SEI.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "id")
    private Long id;

    @Column(nullable = false, name = "url_video")
    private String url;

    @ManyToOne()
    @JoinColumn(name = "topic_id")
    @JsonIgnore
    private Topic topic;
}
