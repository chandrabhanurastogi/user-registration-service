package com.gamesys.userregistrationservice.repository;

import com.gamesys.userregistrationservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRegistrationRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
}
