package com.bountylink.v1.bountylink.repository;

import com.bountylink.v1.bountylink.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
User findByEmail(String email);
}
