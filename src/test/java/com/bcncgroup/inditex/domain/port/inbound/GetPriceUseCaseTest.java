package com.bcncgroup.inditex.domain.port.inbound;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.bcncgroup.inditex.domain.model.Price;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Optional;

class GetPriceUseCaseTest {

    @Test
    void shouldReturnApplicablePrice() {
        // Arrange
        GetPriceUseCase getPriceUseCase = mock(GetPriceUseCase.class);
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        Price expectedPrice = new Price(
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

        when(getPriceUseCase.findApplicable(date, productId, brandId)).thenReturn(Optional.of(expectedPrice));

        // Act
        Optional<Price> actualPrice = getPriceUseCase.findApplicable(date, productId, brandId);

        // Assert
        assertEquals(Optional.of(expectedPrice), actualPrice);
        verify(getPriceUseCase, times(1)).findApplicable(date, productId, brandId);
    }

    @Test
    void shouldReturnEmptyWhenNoPriceIsApplicable() {
        // Arrange
        GetPriceUseCase getPriceUseCase = mock(GetPriceUseCase.class);
        LocalDateTime date = LocalDateTime.of(2020, 6, 13, 10, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        when(getPriceUseCase.findApplicable(date, productId, brandId)).thenReturn(Optional.empty());

        // Act
        Optional<Price> actualPrice = getPriceUseCase.findApplicable(date, productId, brandId);

        // Assert
        assertEquals(Optional.empty(), actualPrice);
        verify(getPriceUseCase, times(1)).findApplicable(date, productId, brandId);
    }
}