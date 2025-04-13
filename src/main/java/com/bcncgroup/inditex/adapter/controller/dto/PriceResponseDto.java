package com.bcncgroup.inditex.adapter.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.bcncgroup.inditex.domain.model.Price;

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
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	public Integer getPriceList() {
		return priceList;
	}
	public void setPriceList(Integer priceList) {
		this.priceList = priceList;
	}
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
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

