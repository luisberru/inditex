package com.bcncgroup.inditex;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PriceServiceApplicationTests {

	@Test
	void contextLoads() {
		// Este método se deja vacío intencionadamente para verificar que el contexto de Spring Boot carga correctamente.
	}

	@Test
    void mainMethodTest() {
        PriceServiceApplication.main(new String[] {});
    }
}
