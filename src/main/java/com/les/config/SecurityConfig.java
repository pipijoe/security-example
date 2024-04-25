package com.les.config;

import com.les.config.security.*;
import com.les.config.security.codeauth.CodeAuthenticationFilter;
import com.les.config.security.codeauth.CodeAuthenticationProvider;
import com.les.service.interfaces.CodeUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author: joetao
 * @Date: 2024/4/18 9:39
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private final UserDetailsService customUserDetailsService;
    private final CodeUserDetailService codeUserDetailService;
    private final AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;
    private final MyAuthenticationSuccessHandler authenticationSuccessHandler;
    private final MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    public SecurityConfig(UserDetailsService customUserDetailsService, CodeUserDetailService codeUserDetailService, AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler, MyAuthenticationSuccessHandler authenticationSuccessHandler, MyAuthenticationFailureHandler myAuthenticationFailureHandler, JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter, CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.customUserDetailsService = customUserDetailsService;
        this.codeUserDetailService = codeUserDetailService;
        this.authenticationAccessDeniedHandler = authenticationAccessDeniedHandler;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.myAuthenticationFailureHandler = myAuthenticationFailureHandler;
        this.jwtAuthenticationTokenFilter = jwtAuthenticationTokenFilter;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedHandler(authenticationAccessDeniedHandler)
                .and().exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin()
                .disable()
                .csrf()
                .disable();
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public CustomUsernamePasswordAuthFilter getCustomUsernamePasswordAuthFilter() {
        CustomUsernamePasswordAuthFilter customUsernamePasswordAuthFilter = new CustomUsernamePasswordAuthFilter();
        customUsernamePasswordAuthFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        customUsernamePasswordAuthFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        return customUsernamePasswordAuthFilter;
    }

    @Bean
    public CodeAuthenticationFilter getCodeAuthenticationFilter() {
        CodeAuthenticationFilter codeAuthenticationFilter = new CodeAuthenticationFilter();
        codeAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        return codeAuthenticationFilter;
    }

    public CodeAuthenticationProvider getCodeAuthenticationProvider() {
        return new CodeAuthenticationProvider(codeUserDetailService);
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers("/login", "/login/code", "/api/v1/getPublicKey");
    }
    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                // 设置UserDetailsService
                .userDetailsService(this.customUserDetailsService)
                // 使用BCrypt进行密码的hash
                .passwordEncoder(passwordEncoder());
        // 添加授权码认证器，默认只有DaoAuthenticationProvider
        authenticationManagerBuilder.authenticationProvider(getCodeAuthenticationProvider());
    }


    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
