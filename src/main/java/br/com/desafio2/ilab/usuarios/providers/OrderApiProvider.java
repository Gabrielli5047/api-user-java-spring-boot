package br.com.desafio2.ilab.usuarios.providers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServerRequest;

public class OrderApiProvider {
    private static WebClient client = WebClient.create("http://localhost:8087");

    public static Boolean canDeleteUser(Integer id, String token) {

        Mono<Boolean> result = client.get()
                .uri("/orders/user/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(Boolean.class);

        return result.block();
    }

}
