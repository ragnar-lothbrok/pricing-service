package com.xyz.assignment.config;

import com.xyz.assignment.properties.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Security configuration for actuator endpoints
 */
@Configuration
@Order(1)
public class ActuatorSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * no op password encoder prefix
     */
    private static final String NOOP_PASSWORD_PREFIX = "{noop}";

    /**
     * Application properties resolved by spring
     */
    @Autowired
    private ServiceConfig serviceConfig;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String password = NOOP_PASSWORD_PREFIX + serviceConfig.getActuatorEndpointsUserPassword();
        auth.inMemoryAuthentication()
                .withUser(serviceConfig.getActuatorEndpointsUser())
                .password(password)
                .authorities("ROLE_ACTUATOR");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // csrf is disable, session is never created
        // form login is not supported, only basic authentication is supported
        http.requestMatchers().antMatchers("/actuator/**")
                .and()
                .httpBasic().and()
                .authorizeRequests().antMatchers("/actuator/health").permitAll()
                .and().authorizeRequests().antMatchers("/actuator/info").permitAll()
                .and()
                .authorizeRequests().antMatchers("/actuator/**").fullyAuthenticated()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().formLogin().disable()
                .rememberMe().disable()
                .logout().disable()
                .x509().disable();
    }
}
