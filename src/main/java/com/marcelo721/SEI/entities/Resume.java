package com.marcelo721.SEI.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "id")
    private Long id;

    private String title;

    @Column(name = "text", length = 5000)
    @Lob
    private String text;

    @OneToOne
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    @JsonIgnore
    private Topic topic;
}
