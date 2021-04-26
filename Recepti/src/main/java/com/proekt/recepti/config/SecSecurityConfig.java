package com.proekt.recepti.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final CustomLoginAuthenticationProvider customLoginAuthenticationProvider;

    public SecSecurityConfig(PasswordEncoder passwordEncoder,
                             CustomLoginAuthenticationProvider customLoginAuthenticationProvider) {
        this.passwordEncoder = passwordEncoder;
        this.customLoginAuthenticationProvider = customLoginAuthenticationProvider;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        // authentication manager (see below)
//        auth.inMemoryAuthentication()
//                .withUser("user1").password(passwordEncoder.encode("123")).roles("USER")
//                .and()
//                .withUser("user2").password(passwordEncoder.encode("user2Pass")).roles("USER")
//                .and()
//                .withUser("admin").password(passwordEncoder.encode("adminPass")).roles("ADMIN");
        auth.authenticationProvider(customLoginAuthenticationProvider);
    }

//                    .antMatchers("/", "/r/**", "/profile/**", "/profile*", "/login*", "/register*").permitAll()
//            .anyRequest().authenticated()

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/create").authenticated()
            .anyRequest().permitAll()
            .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/", true)
            .failureUrl("/login?error=true")
            .and()
            .logout()
            .logoutUrl("/logout")
            .deleteCookies("JSESSIONID")
            .clearAuthentication(true)
            .invalidateHttpSession(true)
            .logoutSuccessUrl("/");
    }

}
