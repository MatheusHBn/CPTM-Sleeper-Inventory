package com.CPTM.stock.repository;

import com.CPTM.stock.domain.WoodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WoodTypeRepository extends JpaRepository<WoodType, UUID> {
    boolean existsByNameIgnoreCase(String name);
    List<WoodType> findByActiveTrue();

}
