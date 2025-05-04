package com.bcncgroup.inditex.application.port.outbound;

import java.time.LocalDateTime;
import java.util.List;
import com.bcncgroup.inditex.domain.model.Price;

public interface PriceRepository {

	List<Price> findApplicable(LocalDateTime date, Long productId, Long brandId);
	
}
