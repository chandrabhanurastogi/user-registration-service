package com.gamesys.userregistrationservice.repository;

import com.gamesys.userregistrationservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegistrationRepository extends JpaRepository<User, String> {}
