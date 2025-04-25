package com.bcncgroup.inditex.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

public record Price(
        Long id,
        Long brandId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Integer priceList,
        Long productId,
        Integer priority,
        BigDecimal amount,
        Currency currency) {}

