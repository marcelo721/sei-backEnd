package com.marcelo721.SEI.repositories;

import com.marcelo721.SEI.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
