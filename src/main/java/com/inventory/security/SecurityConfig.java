package com.inventory.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration // Le indica al contenedor de spring que esta es una clase de seguridad al
				// momento de arrancar la aplicación
@EnableWebSecurity // Indicamos que se activa la seguridad web en nuestra aplicación y además esta
					// será una clase la cuál contendrá toda la configuración referente a la
					// seguridad
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	public SecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
	}

	// Este bean va a encargarse de verificar la información de los usuarios que se
	// loguearán en nuestra api
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	// Con este bean nos encargaremos de encriptar todas nuestras contraseñas
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Este beab incorporará el filtro de seguridad de json web token que creamos en
	// nuestra clase anterior
	@Bean
	JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}

	// Vamos a crear un bean el cual va a establecer una cadena de filtros de
	// seguridad en nuestra aplicación. Y es aquí donde determinaremos los permisos
	// según los roles de los usuarios para acceder a nuestra aplicación.
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .cors(withDefaults())  // Activa la configuración de CORS
	        .csrf(csrf -> csrf.disable())
	        .exceptionHandling(handling -> handling.authenticationEntryPoint(jwtAuthenticationEntryPoint))
	        .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .authorizeHttpRequests(requests -> requests
	            .requestMatchers("/api/auth/**").permitAll()
	            .requestMatchers("/api/**").permitAll()
	            .requestMatchers("/**").permitAll()
	            .anyRequest().authenticated())
	        .httpBasic(withDefaults());

	    http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	    return http.build();
	}

	
	@Bean
	public CorsFilter corsFilter() {
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true);
	    config.addAllowedOrigin("http://localhost:4200");  // Permitir el origen de Angular
	    config.addAllowedHeader("*");  // Permitir todas las cabeceras
	    config.addAllowedMethod("*");  // Permitir todos los métodos (GET, POST, etc.)
	    source.registerCorsConfiguration("/api/**", config);  // Aplica a todas las rutas que empiezan con /api/
	    return new CorsFilter(source);
	}
}
