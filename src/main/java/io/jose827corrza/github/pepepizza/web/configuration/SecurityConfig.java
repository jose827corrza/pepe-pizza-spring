package io.jose827corrza.github.pepepizza.web.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors().and()
                .csrf().disable() // Is disabled because this API was meant bo be stateless and protected by JWT
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/api/pizzas/**").hasAnyRole("ADMIN", "CUSTOMER")
                .requestMatchers(HttpMethod.GET, "/api/orders/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();


        return http.build();
    }

    @Bean
    public UserDetailsService userService() {
        UserDetails user = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("123456"))
                .roles("ADMIN")
                .build();

        UserDetails customer = User.builder()
                .username("customer")
                .password(passwordEncoder().encode("customer123"))
                .roles("CUSTOMER")
                .build();

        return new InMemoryUserDetailsManager(user, customer);
    }

    // To avoid the Error for "having" a plaintext password
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
