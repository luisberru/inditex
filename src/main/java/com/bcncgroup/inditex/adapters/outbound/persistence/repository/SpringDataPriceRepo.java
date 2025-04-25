package com.bcncgroup.inditex.adapters.outbound.persistence.repository;

import com.bcncgroup.inditex.adapters.outbound.persistence.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface SpringDataPriceRepo extends JpaRepository<PriceEntity, Long> {

    @Query("""
        select p from PriceEntity p
        where p.productId = :productId
          and p.brandId = :brandId
          and :date between p.startDate and p.endDate
    """)
    List<PriceEntity> findApplicable(LocalDateTime date, Long productId, Long brandId);
}
