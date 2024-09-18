package com.paymentchain.apigateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;


// Creación de un filtro a través de una clase que implementa la interfaz "GlobalFilter"
// Este modo de hacerlo tiene la desventaja de que sólo podemos implementar un filtro por clase

@Slf4j
@Component
public class GlobalPreFilter implements GlobalFilter {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		log.info("Global prefilter executed");
		
		return chain.filter(exchange); // Indicamos que se ejecute el siguiente filtro de la cadena de filtros
	}

}
