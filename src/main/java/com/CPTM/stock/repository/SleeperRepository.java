package com.CPTM.stock.repository;

import com.CPTM.stock.domain.Sleeper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SleeperRepository extends JpaRepository<Sleeper, UUID> {
    boolean existsByIdentifier(String identifier);
    Optional<Sleeper> findByIdentifier(String identifier);
}
