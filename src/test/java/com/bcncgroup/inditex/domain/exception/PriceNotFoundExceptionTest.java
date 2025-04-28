package com.bcncgroup.inditex.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class PriceNotFoundExceptionTest {

    @Test
    void shouldCreateExceptionWithMessage() {
        String errorMessage = "Precio no encontrado para los criterios dados";
        PriceNotFoundException exception = new PriceNotFoundException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void shouldThrowPriceNotFoundException() {
        String errorMessage = "Precio no encontrado para los criterios dados";

        Exception exception = assertThrows(PriceNotFoundException.class, () -> {
            throw new PriceNotFoundException(errorMessage);
        });

        assertEquals(errorMessage, exception.getMessage());
    }
}