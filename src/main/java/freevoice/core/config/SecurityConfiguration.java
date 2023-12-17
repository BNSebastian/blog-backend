package freevoice.core.config;

import freevoice.core.util.JwtAuthenticationFilter;
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

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // authorize requests
        http.authorizeHttpRequests(auth -> {
            //auth.requestMatchers(AntPathRequestMatcher.antMatcher("/api/pokemon")).hasRole("ADMIN");
            auth.requestMatchers(AntPathRequestMatcher.antMatcher("/api/auth/**")).permitAll();
            auth.requestMatchers(AntPathRequestMatcher.antMatcher("/api/activity/**")).authenticated();
            auth.requestMatchers(AntPathRequestMatcher.antMatcher("/api/type/**")).authenticated();
//            auth.requestMatchers(AntPathRequestMatcher.antMatcher("/api/time/ADMIN")).hasRole("ADMIN");
            auth.requestMatchers(AntPathRequestMatcher.antMatcher("/api/time/**")).authenticated();
            //auth.requestMatchers(AntPathRequestMatcher.antMatcher("/api/**")).permitAll();
            //auth.requestMatchers(AntPathRequestMatcher.antMatcher("/api/pokemon")).authenticated();
            //auth.anyRequest().authenticated();
            //auth.anyRequest().permitAll();
        });

        // disable CSRF
        http.csrf(AbstractHttpConfigurer::disable);

        // by default uses a bean named corsConfigurationSource
        http.cors(Customizer.withDefaults());
        //http.cors(cors -> cors.disable());

        // don't persist log in
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authenticationProvider(authenticationProvider);
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}