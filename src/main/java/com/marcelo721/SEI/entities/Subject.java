package com.marcelo721.SEI.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.marcelo721.SEI.entities.enums.Semester;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "subjects")
    private Set<User> users = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Semester semester;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToMany(mappedBy = "subject", fetch = FetchType.EAGER, cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Topic> topics = new ArrayList<>();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToMany(mappedBy = "subject", fetch = FetchType.EAGER, cascade = CascadeType.ALL,orphanRemoval = true)
    private List<PastExam> pastExams = new ArrayList<>();

}