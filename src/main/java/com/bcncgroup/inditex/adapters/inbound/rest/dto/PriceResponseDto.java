package com.bcncgroup.inditex.adapters.inbound.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import com.bcncgroup.inditex.adapters.outbound.persistence.entity.PriceEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceResponseDto {
    private Long productId;
    private Long brandId;
    private Integer priceList;
    @Schema(
	  type = "string",
	  pattern = "yyyy-MM-dd'T'HH:mm:ss",
	  example = "2025-04-22T02:14:35",
	  description = "Fecha de la consulta en formato yyyy-MM-dd'T'HH:mm:ss"
	)
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startDate;
    @Schema(
	  type = "string",
	  pattern = "yyyy-MM-dd'T'HH:mm:ss",
	  example = "2025-04-22T02:14:35",
	  description = "Fecha de la consulta en formato yyyy-MM-dd'T'HH:mm:ss"
	)
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endDate;
    private BigDecimal price;
    private String currency;
    
	public PriceResponseDto(Long productId, Long brandId, Integer priceList, LocalDateTime startDate,
			LocalDateTime endDate, BigDecimal price, String currency) {
		super();
		this.productId = productId;
		this.brandId = brandId;
		this.priceList = priceList;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
		this.currency = currency;
	}

	public static PriceResponseDto from(PriceEntity price) {
	    return new PriceResponseDto(
	        price.getProductId(),
	        price.getBrandId(),
	        price.getPriceList(),
	        price.getStartDate(),
	        price.getEndDate(),
	        price.getPrice(),
	        price.getCurr()
	    );
	}

}

