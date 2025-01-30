package com.marcelo721.SEI.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ForgotPassword {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "otp", nullable = false)
    private Integer otp;

    @Column(name = "expire_date", nullable = false)
    private Date expireDate;

    @OneToOne
    private User user;
}
