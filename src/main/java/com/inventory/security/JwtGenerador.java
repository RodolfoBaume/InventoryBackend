package com.inventory.security;

import java.util.Date;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerador {
	// Método para crear un token por medio de la autentificación
	public String generarToken(Authentication authentication) {
		String username = authentication.getName();
		Date tiempoActual = new Date();
		Date expiracionToken = new Date(tiempoActual.getTime() + ConstantesSeguridad.JWT_EXPIRATION_TOKEN);
		
		// Línea para generar el token
		String token = Jwts.builder() //Construimos un token JWT llamado token
				.setSubject(username) //Aca establecemos el nombre de usuario que está iniciando sesión
				.setIssuedAt(new Date()) //Establecemos la fecha de emisión del token en el momento actual
				.setExpiration(expiracionToken) //Establecemos la fecha de caducidad del token
				.signWith(SignatureAlgorithm.HS512, ConstantesSeguridad.JWT_FIRMA) /* Utilizamos este método para firmar
				nuestro token y de esta manera evitar la manipulación o modificación de este*/
				.compact(); //Este método finaliza la construcción del token y lo convierte en una cadena compacta
		
		return token;
	}
	
	// Método para extraer un username a partir de un token
	public String obtenerUsernameDeJwt(String token) {
		Claims claims = Jwts.parser() //El método parser se utiliza con el fin de analizar el token
				.setSigningKey(ConstantesSeguridad.JWT_FIRMA) //Establece la clave de firma, que se utiliza para verificar la firma del token
				.parseClaimsJws(token) //Se utiliza para verificar la firma del token, apartir del String "token"
				.getBody(); /*Obtenemos el claims(cuerpo) ya verificado del token el cual contendrá la información de
				nombre de usuario, fecha de expiración y firma del token*/
		return claims.getSubject(); //Devolvemos el nombre de usuario
	}
	
	// Método para validar el token
	public Boolean validarToken(String token) {
		try {
			//Validación del token por medio de la firma que contiene el String token (token)
			//Si son idénticas validará el token o caso contrario saltará la excepción de abajo
			Jwts.parser().setSigningKey(ConstantesSeguridad.JWT_FIRMA).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			throw new AuthenticationCredentialsNotFoundException("401");
		}
	}
}
