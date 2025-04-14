package com.bcncgroup.inditex.adapter.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.bcncgroup.inditex.domain.model.Price;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceResponseDto {
    private Long productId;
    private Long brandId;
    private Integer priceList;
    private LocalDateTime startDate;
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

	public static PriceResponseDto from(Price price) {
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

