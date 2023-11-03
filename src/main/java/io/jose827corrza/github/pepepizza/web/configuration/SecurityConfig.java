package io.jose827corrza.github.pepepizza.web.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(securedEnabled = true) // Allow us to protect not only the controller, but the business logic, in order service an annotation secured
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Autowired
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors().and()
                .csrf().disable() // Is disabled because this API was meant bo be stateless and protected by JWT
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() // To be stateless API
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/pizzas/**").hasAnyRole("ADMIN", "CUSTOMER")
                .requestMatchers(HttpMethod.GET, "/api/orders/summary/*").hasAuthority("SUMMARY_ORDER") // The order affects
                .requestMatchers(HttpMethod.GET, "/api/orders/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // This is the common practice in industry
//                .httpBasic(); // Used with Basic?


        return http.build();
    }

    // NO LONGER REQUIRED, IMPLEMENTED WITH DB
//    @Bean
//    public UserDetailsService userService() {
//        UserDetails user = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("123456"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails customer = User.builder()
//                .username("customer")
//                .password(passwordEncoder().encode("customer123"))
//                .roles("CUSTOMER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, customer);
//    }

    // To avoid the Error for "having" a plaintext password

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
