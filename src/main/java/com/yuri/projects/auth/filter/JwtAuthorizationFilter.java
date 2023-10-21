package com.yuri.projects.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuri.projects.auth.service.JwtTokenService;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthorizationFilter extends OncePerRequestFilter {
    public static final String AUTH_HEADER = "Authorization";
    public static final String PREFIX_HEADER = "Bearer ";

    private final JwtTokenService jwtTokenService;

    public JwtAuthorizationFilter(final JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader(AUTH_HEADER);

        if (authHeader == null || !authHeader.startsWith(PREFIX_HEADER)) {
            filterChain.doFilter(request, response);
            return ;
        }

        authHeader = authHeader.replace(PREFIX_HEADER, "");

        final String userId = this.jwtTokenService.validateToken(authHeader);

        if (userId == null) {
            Map<String, String> tokens = new HashMap<>();
            tokens.put("error", "Token Invalido ou Expirado!");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(401);
            new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            return ;
        }

        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userId, null, Collections.emptyList()
        );

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }

}
