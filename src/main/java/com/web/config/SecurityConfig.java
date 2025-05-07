package com.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.web.serviceimplement.DetalleUsuarioImplement;


@Configuration
@EnableWebSecurity
public class SecurityConfig  {
	
	@Autowired
	private DetalleUsuarioImplement userDetailsService;
	
	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.authorizeHttpRequests(authorizeHttpRequests ->
	        authorizeHttpRequests
	            .requestMatchers("/index").authenticated() // Rutas autenticadas
	            .requestMatchers("/resources/**", "/nuevousuario", "/registrarusuario", "/frmeditar/**", "/css/**", "/js/**", "/img/**").permitAll() // Rutas públicas
	            //.requestMatchers("/login").permitAll() // Permitir acceso a la página de login sin autenticación
	            .anyRequest().authenticated()) // Cualquier otra solicitud requiere autenticación
	        .formLogin(formLogin ->
	            formLogin
	                .loginPage("/login") // Página de inicio de sesión personalizada
	                .permitAll() // Permitir acceso a la página de login para todos
	                .defaultSuccessUrl("/index", true)) // Redirigir después del inicio de sesión exitoso
	        .logout(logout ->
	            logout
	                .logoutUrl("/logout") // URL para cerrar sesión
	                .logoutSuccessUrl("/login") // Redirigir después de cerrar sesión
	                .permitAll()) // Permitir acceso a la página de logout para todos
	        .csrf(csrf -> csrf.disable());  // Deshabilitar CSRF (puedes habilitarlo correctamente en producción)
	    return http.build();
	}
	
	
	
	@Bean
	AuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	    provider.setUserDetailsService(userDetailsService);
	    provider.setPasswordEncoder(passwordEncoder());
	    return provider;
	}

    
}
