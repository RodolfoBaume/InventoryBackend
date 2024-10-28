package com.inventory.service;

import java.security.Principal;

import org.springframework.http.ResponseEntity;

import com.inventory.dto.AuthRespuestaDto;
import com.inventory.dto.LoginDto;
import com.inventory.dto.PasswordDto;
import com.inventory.dto.RegistroResponseDto;
import com.inventory.dto.UsuarioActualDto;
import com.inventory.dto.UsuarioDto;

public interface IUsuarioAuthService {

	ResponseEntity<RegistroResponseDto> registrarUsuario(UsuarioDto registroDto, String role);
	
	ResponseEntity<AuthRespuestaDto> login(LoginDto dtoLogin);
	
	ResponseEntity<UsuarioActualDto> obtenerUsuarioActual(Principal principal);
	
	ResponseEntity<?> cambiarPassword(Principal principal, PasswordDto passwordDto);
	
	
}
