package com.bcncgroup.inditex.domain.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.bcncgroup.inditex.domain.exception.PriceNotFoundException;
import com.bcncgroup.inditex.domain.model.Price;
import com.bcncgroup.inditex.domain.port.outbound.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;
import java.util.Optional;

class PriceServiceTest {

    private PriceRepository priceRepository;
    private PriceService priceService;

    @BeforeEach
    void setUp() {
        priceRepository = mock(PriceRepository.class);
        priceService = new PriceService(priceRepository);
    }

    @Test
    void shouldReturnHighestPriorityPrice() {
        // Arrange
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
        Optional<Price> result = priceService.findApplicable(date, productId, brandId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(price2, result.get()); // El precio con mayor prioridad
        verify(priceRepository, times(1)).findApplicable(date, productId, brandId);
    }

    @Test
    void shouldThrowExceptionWhenNoPriceIsFound() {
        // Arrange
        LocalDateTime date = LocalDateTime.of(2020, 6, 13, 10, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        when(priceRepository.findApplicable(date, productId, brandId)).thenReturn(List.of());

        // Act & Assert
        PriceNotFoundException exception = assertThrows(PriceNotFoundException.class, () -> {
            priceService.getOrThrow(date, productId, brandId);
        });

        assertEquals("No se encontró un precio para el producto 35455 de la marca 1 en la fecha 2020-06-13T10:00.", exception.getMessage());
        verify(priceRepository, times(1)).findApplicable(date, productId, brandId);
    }

    @Test
    void shouldReturnEmptyOptionalWhenNoPriceIsApplicable() {
        // Arrange
        LocalDateTime date = LocalDateTime.of(2020, 6, 13, 10, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        when(priceRepository.findApplicable(date, productId, brandId)).thenReturn(List.of());

        // Act
        Optional<Price> result = priceService.findApplicable(date, productId, brandId);

        // Assert
        assertTrue(result.isEmpty());
        verify(priceRepository, times(1)).findApplicable(date, productId, brandId);
    }
}