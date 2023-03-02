package com.may.MAYAirlines.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenManager tokenManager;
    private final CustomUserDetailService userDetailService;

    public JwtAuthenticationFilter(TokenManager tokenManager, CustomUserDetailService userDetailService) {
        this.tokenManager = tokenManager;
        this.userDetailService = userDetailService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader("Authorization");
        final String token;
        final String username;

        if (header != null && header.contains("Bearer ")){
             token = header.substring(7);
             username = tokenManager.getUsernameToken(token);
             if (username != null && SecurityContextHolder.getContext().getAuthentication()==null){
                 UserDetails userDetails = userDetailService.loadUserByUsername(username);
                 if (tokenManager.tokenControl(token,userDetails)){
                     UsernamePasswordAuthenticationToken authenticationToken =
                             new UsernamePasswordAuthenticationToken(userDetails,
                                     null,
                                     userDetails.getAuthorities());
                     authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                     SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                 }
             }
             filterChain.doFilter(request,response);
        }
        else {
            filterChain.doFilter(request,response);
        }
    }
}
