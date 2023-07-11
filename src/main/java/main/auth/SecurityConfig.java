package main.auth;

import main.auth.CorsFilter;
import main.auth.JWTAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    JWTAuthFilter jwtAuthFilter;

    @Autowired
    CorsFilter corsFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.cors(c -> c.disable());

        http.csrf(c -> c.disable());

        http.authorizeHttpRequests(auth -> auth.requestMatchers("/ristoranti/**").permitAll());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/ristoranti/cerca/**").permitAll());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/cliente/").permitAll());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/luogo/**").permitAll());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/ristoranti/tipo-cucina/**").permitAll());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/ristoranti/luogo/citta/{citta}").permitAll());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/ristoranti/luogo/**").permitAll());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("ristoranti/luogo/citta/**").permitAll());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/ristoranti/nome/{nomeRistorante}").permitAll());//testato,senza questo non funziona
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/ristoranti/tipoCucina/**").permitAll());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll());
//        AUTORIZZAZIONE+++++++++++
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/cliente/**").authenticated());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/prenotazione/**").authenticated());
//       http.authorizeHttpRequests(auth -> auth.requestMatchers("/ristoranti/**").authenticated());


        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(corsFilter, JWTAuthFilter.class);

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    PasswordEncoder pwEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}