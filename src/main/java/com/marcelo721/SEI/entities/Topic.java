package com.marcelo721.SEI.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name = "id")
    private Long id;

    @Column(nullable = false,length = 50, unique = true, name = "name")
    private String name;

    @Column(nullable = false, length = 100, name = "description")
    private String description;

    @ManyToOne()
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToOne(mappedBy = "topic", cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
    private Resume resume;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToMany(mappedBy = "topic", fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Video> videos = new HashSet<>();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToMany(mappedBy = "topic", fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Exercise> exercises = new HashSet<>();
}
