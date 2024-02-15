package freevoice.core.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
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
                // configuration.setAllowedOrigins(Arrays.asList(
                // "http://localhost:4200",
                // "https://localhost:4200",
                // "http://frontend-service:4200",
                // "http://frontend-service",
                // "http://bucovala-sebastian.go.ro:4200",
                // "https://bucovala-sebastian.go.ro:4200",
                // "https://bucovala-sebastian.go.ro:5001",
                // "https://bucovala-sebastian.go.ro",
                // "http://172.21.0.2:4200",
                // "https://172.21.0.2:4200",
                // "http://192.168.1.131:4200",
                // "https://192.168.1.131:4200",
                // "http://192.168.1.132:4200"));
                configuration.setAllowedOrigins(List.of("*"));
                configuration.setAllowedMethods(List.of("*"));
                configuration.setAllowedHeaders(List.of("*"));
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                return source;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http.authorizeHttpRequests(auth -> {
                        // auhentication
                        auth.requestMatchers(AntPathRequestMatcher
                                        .antMatcher("/api/auth/**"))
                                        .permitAll();
                        auth.requestMatchers(AntPathRequestMatcher
                                        .antMatcher("/api/auth/isAdmin"))
                                        .authenticated();
                        // user
                        auth.requestMatchers(AntPathRequestMatcher
                                        .antMatcher("/api/user/**"))
                                        .authenticated();

                        // profile pictures
                        auth.requestMatchers(AntPathRequestMatcher
                                        .antMatcher("/api/image/**"))
                                        .authenticated();
                        // videos
                        auth.requestMatchers(AntPathRequestMatcher
                                        .antMatcher("/api/video/**"))
                                        .permitAll();
                        auth.requestMatchers(AntPathRequestMatcher
                                        .antMatcher("/api/video/upload"))
                                        .hasRole("ADMIN");
                        auth.requestMatchers(AntPathRequestMatcher
                                        .antMatcher("/api/video/changeVideo/**"))
                                        .hasRole("ADMIN");
                        auth.requestMatchers(AntPathRequestMatcher
                                        .antMatcher("/api/video/changeName/**"))
                                        .hasRole("ADMIN");
                        auth.requestMatchers(AntPathRequestMatcher
                                        .antMatcher("/api/video/delete/**"))
                                        .hasRole("ADMIN");

                        // video comments
                        auth.requestMatchers(AntPathRequestMatcher
                                        .antMatcher("/api/videoComment/**"))
                                        .authenticated();

                        // forum posts
                        auth.requestMatchers(AntPathRequestMatcher
                                        .antMatcher("/api/forumPost/**"))
                                        .authenticated();
                        auth.requestMatchers(AntPathRequestMatcher
                                        .antMatcher("/api/forumPost/pinForumPost/**"))
                                        .hasRole("ADMIN");

                        // forum comments
                        auth.requestMatchers(AntPathRequestMatcher
                                        .antMatcher("/api/forumComment/**"))
                                        .authenticated();

                        // articles
                        auth.requestMatchers(AntPathRequestMatcher
                                        .antMatcher("/api/article/**"))
                                        .authenticated();
                        auth.requestMatchers(AntPathRequestMatcher
                                        .antMatcher("/api/article/create"))
                                        .hasRole("ADMIN");
                        auth.requestMatchers(AntPathRequestMatcher
                                        .antMatcher("/api/article/update/**"))
                                        .hasRole("ADMIN");
                        auth.requestMatchers(AntPathRequestMatcher
                                        .antMatcher("/api/article/delete/**"))
                                        .hasRole("ADMIN");

                        // paypal
                        auth.requestMatchers(AntPathRequestMatcher
                                        .antMatcher("/api/paypal/**"))
                                        .authenticated();
                });

                http.csrf(AbstractHttpConfigurer::disable);

                http.cors(withDefaults()); // by default uses a bean named corsConfigurationSource

                http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

                http.authenticationProvider(authenticationProvider);

                http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

                // // Adding a custom security filter for WebSocket connections
                // http.headers(headers -> {
                // headers.cacheControl(withDefaults()).disable();
                // headers.frameOptions(withDefaults()).disable();
                // });

                return http.build();
        }
}