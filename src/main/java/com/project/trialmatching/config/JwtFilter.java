package com.project.trialmatching.config;

import com.project.trialmatching.repository.UserRepository;
import com.project.trialmatching.util.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String token;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        token = authHeader.substring(7);
        userEmail = jwtUtil.extractUsername(token); // or extractEmail()

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var user = userRepository.findByEmail(userEmail).orElse(null);

            if (user != null && jwtUtil.validateToken(token)) {
                var authToken = new UsernamePasswordAuthenticationToken(
                        user,  // principal (can also be user.getEmail())
                        null,
                        user.getAuthorities() // or List.of() for empty authorities
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }

}
