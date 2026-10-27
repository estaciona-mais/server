package com.estacionamais.server.core;

import com.estacionamais.server.security.JwtAuthenticationFilter;
import java.util.List;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class ConfiguracaoSeguranca {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(configuracaoCors()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        regras -> regras.requestMatchers("/api/auth/**", "/api/webhook/**", "/api/veiculos")
                                .permitAll()
                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/api/planos",
                                        "/api/veiculos/cores",
                                        "/api/veiculos/tipos",
                                        "/api/veiculos")
                                .permitAll()
                                .requestMatchers(
                                        HttpMethod.POST,
                                        "/api/usuarios/google/consulta")
                                .permitAll()
                                .requestMatchers(
                                        "/api/clientes/consulta",
                                        "/api/enderecos/**",
                                        "/api/movimentacoes/**",
                                        "/api/veiculos/**",
                                        "/api/vagas/**",
                                        "/api/movimentacoes/**",
                                        "/api/financeiro/**",
                                        "/api/usuarios/**")
                                .hasRole("ADMIN")
                                .anyRequest()
                                .authenticated())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    public CorsConfigurationSource configuracaoCors() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:5173"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
