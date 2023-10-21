package com.bountylink.v1.bountylink.repository;

import com.bountylink.v1.bountylink.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
User findByEmail(String email);

    User findOneByEmailAndPassword(String email, String encodedPassword);

   /* @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.flag = 1 WHERE a.email = ?1")
    int enableAppUser(String email);*/
}
