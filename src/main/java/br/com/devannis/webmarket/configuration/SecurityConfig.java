package br.com.devannis.webmarket.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.header.StaticServerHttpHeadersWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Autorização de requisições
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/h2/**", "/favicon.ico", "/error", "/css/**", "/js/**", "/images/**").permitAll()
                        .anyRequest().authenticated()  // Exigir autenticação para todas as outras requisições
                )
                // Configuração de CSRF
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2/**")  // Ignorar CSRF para as requisições do H2
                )
                // Configuração de cabeçalhos HTTP
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.sameOrigin()) // Permitir frames da mesma origem
                )
                // Configuração de login
                .formLogin(form -> form
                        .loginPage("/login")  // Página de login personalizada (caso tenha)
                        .permitAll()
                )
                // Configuração de logout
                .logout(logout -> logout
                        .permitAll()
                );

        return http.build();
    }
}
