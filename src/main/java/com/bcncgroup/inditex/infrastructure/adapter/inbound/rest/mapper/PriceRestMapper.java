package com.bcncgroup.inditex.infrastructure.adapter.inbound.rest.mapper;

import com.bcncgroup.inditex.infrastructure.adapter.inbound.rest.dto.PriceResponseDto;
import com.bcncgroup.inditex.domain.model.Price;

public class PriceRestMapper {
    public static PriceResponseDto toDto(Price price) {
        return new PriceResponseDto(
                price.productId(),
                price.brandId(),
                price.priceList(),
                price.startDate(),
                price.endDate(),
                price.amount(),
                price.currency().getCurrencyCode());
    }
}
