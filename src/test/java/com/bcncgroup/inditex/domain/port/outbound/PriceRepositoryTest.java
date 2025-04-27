package com.bcncgroup.inditex.domain.port.outbound;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import com.bcncgroup.inditex.domain.model.Price;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;

class PriceRepositoryTest {

    @Test
    void shouldReturnApplicablePrices() {
        // Arrange
        PriceRepository priceRepository = mock(PriceRepository.class);
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        Price price1 = new Price(
                1L,
                brandId,
                LocalDateTime.of(2020, 6, 14, 0, 0),
                LocalDateTime.of(2020, 6, 14, 23, 59),
                1,
                productId,
                0,
                BigDecimal.valueOf(35.50),
                Currency.getInstance("EUR")
        );

        Price price2 = new Price(
                2L,
                brandId,
                LocalDateTime.of(2020, 6, 14, 10, 0),
                LocalDateTime.of(2020, 6, 14, 20, 0),
                2,
                productId,
                1,
                BigDecimal.valueOf(25.45),
                Currency.getInstance("EUR")
        );

        when(priceRepository.findApplicable(date, productId, brandId)).thenReturn(List.of(price1, price2));

        // Act
        List<Price> applicablePrices = priceRepository.findApplicable(date, productId, brandId);

        // Assert
        assertEquals(2, applicablePrices.size());
        assertEquals(price1, applicablePrices.get(0));
        assertEquals(price2, applicablePrices.get(1));
        verify(priceRepository, times(1)).findApplicable(date, productId, brandId);
    }

    @Test
    void shouldReturnEmptyListWhenNoPricesAreApplicable() {
        // Arrange
        PriceRepository priceRepository = mock(PriceRepository.class);
        LocalDateTime date = LocalDateTime.of(2020, 6, 13, 10, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        when(priceRepository.findApplicable(date, productId, brandId)).thenReturn(List.of());

        // Act
        List<Price> applicablePrices = priceRepository.findApplicable(date, productId, brandId);

        // Assert
        assertEquals(0, applicablePrices.size());
        verify(priceRepository, times(1)).findApplicable(date, productId, brandId);
    }
}