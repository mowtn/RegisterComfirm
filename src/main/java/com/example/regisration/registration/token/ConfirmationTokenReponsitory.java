package com.example.regisration.registration.token;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConfirmationTokenReponsitory
        extends JpaRepository<ConfirmationToken,Long> {
    Optional<ConfirmationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query(value = "UPDATE confirmation_token SET confirm_at = :confirmedAt WHERE token = :token",nativeQuery = true)
    int updateConfirmedAt(String token,
                          LocalDateTime confirmedAt);
}
