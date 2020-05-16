package com.example.PayMyBuddy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/*@Configuration
@EnableWebSecurity*/
public class Security extends WebSecurityConfigurerAdapter {


    /*    @Bean
    public UserDetailsService userDetailsService() {
        return new BuddyUserDetailsService(); // (1)
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/user/").permitAll() // pas d'authentification sur /home, accès a tous
                .anyRequest().authenticated() // autres demandes : Authentification necessaire
                .and()
                .formLogin() // formulaire de Login autorisé
                .loginPage("/login") // formulaire de Login custom (ne redirige pas vers le formulaire built in de Spring
                .permitAll()
                .and()
                .logout() // (6)
                .permitAll()
                .and()
                .httpBasic(); // authentification de base, envoi d'un en-tête d'authentification de base HTTP pour l'authentification.
    }*/


}
