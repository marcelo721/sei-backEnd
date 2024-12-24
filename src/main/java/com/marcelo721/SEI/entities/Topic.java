package com.marcelo721.SEI.entities;

import jakarta.persistence.*;
import lombok.*;

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
}
