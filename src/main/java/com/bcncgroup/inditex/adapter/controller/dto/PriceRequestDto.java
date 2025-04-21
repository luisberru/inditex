package com.bcncgroup.inditex.adapter.controller.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceRequestDto {

	@NotNull(message = "La fecha es obligatoria")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@Schema(description = "Fecha de la consulta en formato YYYY-MM-DDTHH:mm:ss", 
    example = "2023-01-01T10:00:00", 
    pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime date;

	@NotNull(message = "El productId es obligatorio")
	@PositiveOrZero(message = "El productId no puede ser negativo")
	@Schema(description = "ID del producto", example = "35455")
	private Long productId;

	@NotNull(message = "El brandId es obligatorio")
	@PositiveOrZero(message = "El brandId no puede ser negativo")
	@Schema(description = "ID de la marca", example = "1")
	private Long brandId;

}
