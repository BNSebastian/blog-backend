package freevoice.core.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200", "http://127.0.0.1:5500"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {

            auth.requestMatchers(AntPathRequestMatcher.antMatcher("/api/auth/**")).permitAll();
            auth.requestMatchers(AntPathRequestMatcher.antMatcher("/api/video/**")).permitAll();
            auth.requestMatchers(AntPathRequestMatcher.antMatcher("/api/videoComment/**")).authenticated();
            auth.requestMatchers(AntPathRequestMatcher.antMatcher("/api/forumPost/**")).permitAll();
            auth.requestMatchers(AntPathRequestMatcher.antMatcher("/api/forumComment/**")).authenticated();
            auth.requestMatchers(AntPathRequestMatcher.antMatcher("/api/chat/**")).authenticated();
            //auth.requestMatchers(AntPathRequestMatcher.antMatcher("/api/time/ADMIN")).hasRole("ADMIN");

            // websocket
            //auth.requestMatchers(AntPathRequestMatcher.antMatcher("/ws/**")).permitAll();
        });

        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(withDefaults()); // by default uses a bean named corsConfigurationSource
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authenticationProvider(authenticationProvider);
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

//        // Adding a custom security filter for WebSocket connections
//        http.headers(headers -> {
//            headers.cacheControl(withDefaults()).disable();
//            headers.frameOptions(withDefaults()).disable();
//        });

        return http.build();
    }
}