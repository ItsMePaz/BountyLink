package com.bountylink.v1.bountylink.repository;

import com.bountylink.v1.bountylink.entity.ConfirmationToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Integer> {
    Optional<ConfirmationToken> findByToken(String token);


    /*@Modifying
    @Query("UPDATE confirmation_token c SET c.confirmed_at = :confirmedAt WHERE c.token=:token")
    int updateConfirmedAt(@Param("token") String token,
                          @Param("confirmedAt") LocalDateTime confirmedAt);*/
}
/*"UPDATE confirmation_token c " +
            "SET c.confirmed_at = :confirmedAt " +
            "WHERE c.token = :token"*/
