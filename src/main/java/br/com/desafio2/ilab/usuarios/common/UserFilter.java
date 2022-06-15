package br.com.desafio2.ilab.usuarios.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import br.com.desafio2.ilab.usuarios.providers.AdminApiProvider;

@Component
public class UserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String token = httpRequest.getHeader("Authorization");
        String method = httpRequest.getMethod();

        if (method.equals("OPTIONS")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (token == null) {
            httpResponse.sendError(401, "Token ausente.");
            return;
        }

        HttpStatus status = AdminApiProvider.isValidToken(token);

        if (status == HttpStatus.UNAUTHORIZED) {
            httpResponse.sendError(401, "Token inválido.");
            return;
        }

        filterChain.doFilter(request, response);

    }

}
