package com.apiproduct.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class ResourceServerConfig {

    @Bean
    public SecurityFilterChain resourceServerFilterChain(HttpSecurity http) throws Exception {
        http
            .oauth2ResourceServer(oauth2 -> oauth2.opaqueToken(Customizer.withDefaults()))
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> {
                    auth
                        .anyRequest().authenticated() /*ALWAYS COME FIRST TO CHECK PRE AUTORIZE*/
                        /* WHEN NOT USING PRE YOU CAN CONFIGURE HERE */
                        /*.requestMatchers("/product/**").hasAuthority("SCOPE_WRITE")*/;


            });

        return http.build();
    }

}
