package io.jose827corrza.github.pepepizza.web.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService; // Is the same "UserSecurityService", thanks to polymorphism..
    // Much better to use  the intherface rather its implementation

    @Autowired
    public JwtFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. Validate the Authorization Header
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }
        // 2. Validate JWT valid
        String jwt = authHeader.split(" ")[1].trim();

        if(!this.jwtUtil.isValid(jwt)){
            filterChain.doFilter(request,response);
            return;
        }
        // 3. Load the user to UserDetailsService
        String username = this.jwtUtil.getUserName(jwt);

        User user = (User) this.userDetailsService.loadUserByUsername(username);

        // 4. Load the user in the security context
        UsernamePasswordAuthenticationToken authorizationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities()
        );
        // Security context of Spring

        // Load details to  the context
        authorizationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        System.out.println(authorizationToken);
        SecurityContextHolder.getContext().setAuthentication(authorizationToken); // <- here is where the magic takes place
        filterChain.doFilter(request,response);
    }
}
