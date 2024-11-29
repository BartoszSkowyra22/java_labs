package com.pollub.lab.repository.lab8;

import com.pollub.lab.model.lab8.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
