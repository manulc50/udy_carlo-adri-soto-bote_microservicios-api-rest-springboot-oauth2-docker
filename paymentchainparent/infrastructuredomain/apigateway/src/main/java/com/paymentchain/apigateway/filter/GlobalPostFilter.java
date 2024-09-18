package com.paymentchain.apigateway.filter;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;


// Creación de un filtro a través de un bean de Spring que implementa la interfaz "GlobalFilter"
// La ventaja de hacerlo de esta forma es que podemos implementar más de un filtro en una misma clase

@Slf4j
@Configuration
public class GlobalPostFilter {
	
	@Bean
	public GlobalFilter postGlobalFilter() {
		// Esta función implementa el método "filter" de la interfaz "GlobalFilter"
		return (excahnge, chain) -> chain.filter(excahnge)
				.then(Mono.fromRunnable(() -> log.info("Global Post filter executed")));
	}

}
