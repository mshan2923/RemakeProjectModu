package com.example.modu.security;

import com.example.modu.service.UserDetailService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Slf4j(topic = "SecurityConfig")
@Configuration
//@EnableWebSecurity//spring Security 적용
@RequiredArgsConstructor
public class SecurityConfig {

    //private final JwtUtil jwtUtil;
    private final UserDetailService userDetailService;
    //private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer configure()//모든 기능 사용 안함
    {
        return (web) -> web.ignoring()
                .requestMatchers("/static/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        try {
            http.csrf(t -> t.disable());
            http.authorizeHttpRequests(auth ->
                    auth
                            .requestMatchers("/").permitAll()
                            .requestMatchers("/api/**").permitAll()
                            .anyRequest().authenticated()
                    );

            http.formLogin(formLogin ->
                    formLogin.loginPage("/api/user/loginForm")
                            .usernameParameter("username")
                            .passwordParameter("password")
                            .loginProcessingUrl("/api/user/login")
                            .successHandler(new AuthenticationSuccessHandler() {
                                @Override
                                public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                                    log.info("login Success");
                                }
                            })
                            .failureHandler(new AuthenticationFailureHandler() {
                                @Override
                                public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                                    log.info("login Fail");
                                }
                            })

            );
        }catch (Exception e)
        {
            log.warn("Security Error : " + e.getMessage());
        }

        return http.build();
    }

    @Bean//인증 관리자 관련 설정
    public DaoAuthenticationProvider daoAuthenticationProvider() throws Exception
    {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

}
