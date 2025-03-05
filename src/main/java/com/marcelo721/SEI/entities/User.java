package com.marcelo721.SEI.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.marcelo721.SEI.entities.enums.Course;
import com.marcelo721.SEI.entities.enums.Role;
import com.marcelo721.SEI.entities.enums.StatusAccount;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Lob
    @Column(name = "profile_picture")
    private byte[] profilePicture;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "email",nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "verification_code")
    private String verificationCode;

    @Column(name = "status_account")
    @Enumerated(EnumType.STRING)
    private StatusAccount statusAccount;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role = Role.STUDENT;

    @Column(name = "course")
    @Enumerated(EnumType.STRING)
    private Course course;

    @OneToOne(mappedBy = "user")
    private ForgotPassword forgotPassword;

    @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_subjects",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> subjects = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == Role.ADMIN) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_STUDENT")
            );
        }
        return List.of(
                new SimpleGrantedAuthority("ROLE_STUDENT")
        );
    }

    @Override
    public String getUsername() {
        return getEmail();
    }
}
