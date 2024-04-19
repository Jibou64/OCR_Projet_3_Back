package com.example.projet3chatop.security;

import com.example.projet3chatop.controller.RentalController;
import com.example.projet3chatop.security.jwt.AuthEntryPointJwt;
import com.example.projet3chatop.security.jwt.AuthTokenFilter;
import com.example.projet3chatop.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  UserDetailsServiceImpl userDetailsService;


  @Autowired
  private AuthEntryPointJwt unauthorizedHandler;

  // Bean to provide authentication token filter
  @Bean
  public AuthTokenFilter authenticationJwtTokenFilter() {
    return new AuthTokenFilter();
  }

  // Configuration for authentication using UserDetailsService
  @Override
  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  // Bean to provide authentication manager
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  // Bean to provide a password encoder
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // Configuration of HTTP security rules
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http.cors().and().csrf().disable()
//            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//            .authorizeRequests().antMatchers("/api/auth/**",
//                    "/v2/api-docs",
//                    "/v3/api-docs",
//                    "/v3/api-docs/**",
//                    "/swagger-resources",
//                    "/swagger-resources/**",
//                    "/configuration/ui",
//                    "/configuration/security",
//                    "/swagger-ui/**",
//                    "/webjars/**",
//                    "/swagger-ui.html"
//            ).permitAll()
//            .and()
//            .formLogin().disable() // Disable default login form
//            .logout().disable() // Disable default logout page
//            .httpBasic().disable() // Disable basic authentication
//            .authorizeRequests()
//            .and()
//            .headers().frameOptions().disable(); // Disable X-Frame-Options for Swagger UI in iframe
//  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests()
            .antMatchers("/v3/api-docs/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/api/auth/**",
                    "/files/**"
            ).permitAll()
            .antMatchers("/api/**").authenticated()
            .antMatchers(HttpMethod.GET, "/files/**").permitAll()
            .anyRequest().authenticated();

    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
  }
}
