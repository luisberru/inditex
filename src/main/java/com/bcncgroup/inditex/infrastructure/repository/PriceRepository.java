package com.bcncgroup.inditex.infrastructure.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bcncgroup.inditex.domain.model.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("""
        SELECT p FROM Price p
        WHERE p.brandId = :brandId
          AND p.productId = :productId
          AND :applicationDate BETWEEN p.startDate AND p.endDate
        ORDER BY p.priority DESC
        LIMIT 1
    """)
    Optional<Price> findApplicablePrice(
        @Param("applicationDate") LocalDateTime applicationDate,
        @Param("productId") Long productId,
        @Param("brandId") Long brandId
    );
}
