package com.bcncgroup.inditex.adapter.controller.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceRequestDto {

    @NotNull(message = "La fecha es obligatoria")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;

    @NotNull(message = "El productId es obligatorio")
	@PositiveOrZero(message = "El productId no puede ser negativo")
    private Long productId;

    @NotNull(message = "El brandId es obligatorio")
	@PositiveOrZero(message = "El brandId no puede ser negativo")
    private Long brandId;
    
    
    
}
