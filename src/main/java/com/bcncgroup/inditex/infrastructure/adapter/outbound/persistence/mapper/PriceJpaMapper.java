package com.bcncgroup.inditex.infrastructure.adapter.outbound.persistence.mapper;

import com.bcncgroup.inditex.infrastructure.adapter.outbound.persistence.entity.PriceEntity;
import com.bcncgroup.inditex.domain.model.Price;

import java.util.Currency;

public class PriceJpaMapper {

    public static Price toDomain(PriceEntity e) {
        return new Price(
                e.getId(),
                e.getBrandId(),
                e.getStartDate(),
                e.getEndDate(),
                e.getPriceList(),
                e.getProductId(),
                e.getPriority(),
                e.getPrice(),
                Currency.getInstance(e.getCurr()));
    }
}
