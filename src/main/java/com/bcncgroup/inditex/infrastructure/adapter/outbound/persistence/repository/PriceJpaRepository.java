package com.bcncgroup.inditex.infrastructure.adapter.outbound.persistence.repository;

import com.bcncgroup.inditex.infrastructure.adapter.outbound.persistence.mapper.PriceJpaMapper;
import com.bcncgroup.inditex.domain.model.Price;
import com.bcncgroup.inditex.domain.port.outbound.PriceRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PriceJpaRepository implements PriceRepository {

    private final SpringDataPriceRepo dataRepo;

    public PriceJpaRepository(SpringDataPriceRepo dataRepo) {
        this.dataRepo = dataRepo;
    }

    @Override
    public List<Price> findApplicable(LocalDateTime date, Long productId, Long brandId) {
        return dataRepo.findApplicable(date, productId, brandId)
                       .stream()
                       .map(PriceJpaMapper::toDomain)
                       .toList();
    }
}
