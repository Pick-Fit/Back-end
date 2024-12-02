package com.pickfit.pickfit.config;

import com.pickfit.pickfit.oauth2.handler.OAuth2AuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    private final OAuth2AuthenticationSuccessHandler successHandler;

    public SecurityConfig(OAuth2AuthenticationSuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors() // CORS 설정 활성화
                .and()
                .csrf().disable() // CSRF 비활성화 (REST API 환경에서 적합)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // OPTIONS 요청 허용 (CORS preflight 요청)
                        .requestMatchers("/", "/login", "/oauth2/**").permitAll() // 로그인 관련 엔드포인트 허용
                        .requestMatchers("/api/**").permitAll() // 위시리스트 API 인증 없이 허용
                        .anyRequest().authenticated() // 그 외 모든 요청 인증 필요
                )
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(successHandler)
                );
        return http.build();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // Frontend 도메인 허용
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메서드
                .allowedHeaders("*") // 모든 헤더 허용
                .allowCredentials(true); // 쿠키 및 인증 정보 허용
    }
}
