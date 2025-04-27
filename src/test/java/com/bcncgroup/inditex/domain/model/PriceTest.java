package com.bcncgroup.inditex.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import org.junit.jupiter.api.Test;

class PriceTest {

    @Test
    void shouldCreatePriceWithValidData() {
        Long id = 1L;
        Long brandId = 1L;
        LocalDateTime startDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        LocalDateTime endDate = LocalDateTime.of(2020, 6, 14, 20, 0);
        Integer priceList = 1;
        Long productId = 35455L;
        Integer priority = 0;
        BigDecimal amount = BigDecimal.valueOf(35.50);
        Currency currency = Currency.getInstance("EUR");

        Price price = new Price(id, brandId, startDate, endDate, priceList, productId, priority, amount, currency);

        assertNotNull(price);
        assertEquals(id, price.id());
        assertEquals(brandId, price.brandId());
        assertEquals(startDate, price.startDate());
        assertEquals(endDate, price.endDate());
        assertEquals(priceList, price.priceList());
        assertEquals(productId, price.productId());
        assertEquals(priority, price.priority());
        assertEquals(amount, price.amount());
        assertEquals(currency, price.currency());
    }
}