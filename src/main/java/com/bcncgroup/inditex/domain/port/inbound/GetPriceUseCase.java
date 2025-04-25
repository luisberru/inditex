package com.bcncgroup.inditex.domain.port.inbound;

import com.bcncgroup.inditex.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface GetPriceUseCase {
    Optional<Price> findApplicable(LocalDateTime date, Long productId, Long brandId);
}
