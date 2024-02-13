package com.sumerge.tmdb.movie.component;


import com.sumerge.tmdb.movie.client.AuthClient;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;


@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    @Value("${auth-url}")
    private  String authUrl;


    private final AuthClient authClient;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull    HttpServletResponse response,
        @NonNull    FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        final String jwtToken;

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            response.setStatus(403);
            return;
        }

        jwtToken = authHeader.substring(7);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(jwtToken);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try{

            ResponseEntity<Void> authResponse = authClient.getValidation("Bearer " + jwtToken);

            if (authResponse.getStatusCode().is2xxSuccessful()) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        jwtToken,
                        null,
                        List.of(new SimpleGrantedAuthority("USER"))
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
                filterChain.doFilter(request, response);
            }
        } catch (HttpClientErrorException e) {
            response.setStatus(403);
            return;
        }
    }
}