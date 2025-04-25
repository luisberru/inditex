package com.bcncgroup.inditex.adapters.inbound.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcncgroup.inditex.adapters.inbound.rest.dto.PriceRequestDto;
import com.bcncgroup.inditex.adapters.inbound.rest.dto.PriceResponseDto;
import com.bcncgroup.inditex.adapters.inbound.rest.mapper.PriceRestMapper;
import com.bcncgroup.inditex.adapters.outbound.persistence.entity.PriceEntity;
import com.bcncgroup.inditex.domain.service.PriceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/prices")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }
    /**
     * Obtiene el precio aplicable para un producto y marca en una fecha específica.
     *
     * @param request Objeto que contiene la fecha, ID de producto y ID de marca.
     * @return Precio aplicable en formato JSON.
     */
	@Operation(summary = "Obtener precio aplicable", description = "Obtiene el precio aplicable para un producto y marca en una fecha específica.", responses = {
			@ApiResponse(responseCode = "200", description = "Precio encontrado", content = @Content(schema = @Schema(implementation = PriceResponseDto.class))),
			@ApiResponse(responseCode = "404", description = "Precio no encontrado") })
	 @GetMapping
	    public ResponseEntity<PriceResponseDto> getPrice(@Valid @ModelAttribute PriceRequestDto req) {
	        var price = priceService.getOrThrow(req.getDate(), req.getProductId(), req.getBrandId());
	        return ResponseEntity.ok(PriceRestMapper.toDto(price));
	    }    

}
