package com.Ecom.Gateway.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class TokenFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        System.out.println("TokenFilter invoked");

        return exchange.getPrincipal()
                .cast(Authentication.class)
                .flatMap(auth -> {
                    if (auth instanceof JwtAuthenticationToken jwtAuth) {
                        System.out.println("JWT token found");
                        Jwt jwt = jwtAuth.getToken();

                        // Extract claims directly
                        String userId = jwt.getClaimAsString("sub");
                        String firstName = jwt.getClaimAsString("given_name");
                        String lastName = jwt.getClaimAsString("family_name");
                        String email = jwt.getClaimAsString("email");

                        // Log the extracted claims
                        System.out.println("User ID: " + userId);
                        System.out.println("First Name: " + firstName);
                        System.out.println("Last Name: " + lastName);
                        System.out.println("Email: " + email);

                        // Log request headers before mutating
                        exchange.getRequest().getHeaders().forEach((key, value) -> {
                            System.out.println("Request Header: " + key + " Value: " + value);
                        });

                        ServerWebExchange mutatedExchange = exchange.mutate()
                                .request(r -> r.headers(headers -> {
                                    headers.add("User-ID", userId);
                                    headers.add("First-Name", firstName);
                                    headers.add("Last-Name", lastName);
                                    headers.add("Email", email);
                                }))
                                .build();

                        // Log mutated request headers
                        mutatedExchange.getRequest().getHeaders().forEach((key, value) -> {
                            System.out.println("Mutated Request Header: " + key + " Value: " + value);
                        });

                        return chain.filter(mutatedExchange);
                    } else {
                        System.out.println("JwtAuthenticationToken not found");
                        return chain.filter(exchange);
                    }
                }).switchIfEmpty(chain.filter(exchange));
    }
}
