package com.bcncgroup.inditex.infrastructure.adapter.inbound.rest.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceRequestDto {

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@Schema(
	    type = "string",
	    pattern = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$",
	    example = "2020-06-14T01:00:00",
	    description = "Fecha de la consulta en formato yyyy-MM-dd'T'HH:mm:ss"
	)
	@NotNull(message = "La fecha es obligatoria")
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
