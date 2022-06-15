package br.com.desafio2.ilab.usuarios.providers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import reactor.core.publisher.Mono;

public class AdminApiProvider {
    private static WebClient client = WebClient.create("http://localhost:8085");

    public static HttpStatus isValidToken(String token) {

        try {
            Mono<ResponseEntity<Object>> result = client.get()
                    .uri("/validateToken")
                    .accept(MediaType.APPLICATION_JSON)
                    .header("Authorization", token)
                    .retrieve()
                    .toEntity(Object.class);

            return result.block().getStatusCode();
        } catch (WebClientResponseException e) {
            return e.getStatusCode();
        }
    }
}
