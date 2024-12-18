package com.inventory.service;

import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.dto.AuthRespuestaDto;
import com.inventory.dto.LoginDto;
import com.inventory.dto.PasswordDto;
import com.inventory.dto.RegistroResponseDto;
import com.inventory.dto.UsuarioActualDto;
import com.inventory.dto.UsuarioDto;
import com.inventory.entity.Rol;
import com.inventory.entity.Usuario;
import com.inventory.repository.IRolRepository;
import com.inventory.repository.IUsuarioRepository;
import com.inventory.security.JwtGenerador;


@Service
public class UsuarioAuthService {

	private AuthenticationManager authenticationManager;
	private PasswordEncoder passwordEncoder;
	private IRolRepository rolesRepository;
	private IUsuarioRepository usuariosRepository;
	private JwtGenerador jwtGenerador;

	@Autowired
	public UsuarioAuthService(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
			IRolRepository rolesRepository, IUsuarioRepository usuariosRepository, JwtGenerador jwtGenerador) {
		this.authenticationManager = authenticationManager;
		this.passwordEncoder = passwordEncoder;
		this.rolesRepository = rolesRepository;
		this.usuariosRepository = usuariosRepository;
		this.jwtGenerador = jwtGenerador;
	}

	// register
	public ResponseEntity<RegistroResponseDto> registrarUsuario(UsuarioDto registroDto, String role) {
		if(registroDto==null){
	        return new ResponseEntity<>(new RegistroResponseDto("El registro esta vacio", null), HttpStatus.BAD_REQUEST);
		}

		if (usuariosRepository.existsByUsername(registroDto.username())) {
	        return new ResponseEntity<>(new RegistroResponseDto("El usuario ya existe, intenta con otro", null), HttpStatus.BAD_REQUEST);
		}

		Usuario usuarios = new Usuario();
		usuarios.setUsername(registroDto.username());
		usuarios.setPassword(passwordEncoder.encode(registroDto.password()));
		System.out.println("<<usuario nuevo>>>>"+usuarios.toString());

		Rol roles = rolesRepository.findByNombre(role).orElse(null);

		if (roles == null) {
	        return new ResponseEntity<>(new RegistroResponseDto("Rol no válido", null), HttpStatus.BAD_REQUEST);
		}

		System.out.println(">>>>>>ROLES:" +roles.toString());
		usuarios.setRol(Collections.singletonList(roles));
		usuariosRepository.save(usuarios);

		RegistroResponseDto responseDto = new RegistroResponseDto("Registro exitoso", usuarios.getIdUsuario());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	// login
	public ResponseEntity<AuthRespuestaDto> login(LoginDto dtoLogin) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(dtoLogin.getUsername(), dtoLogin.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtGenerador.generarToken(authentication);
		return new ResponseEntity<>(new AuthRespuestaDto(token), HttpStatus.OK);
	}

	// usuarioActual
	public ResponseEntity<UsuarioActualDto> obtenerUsuarioActual(Principal principal) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario user = usuariosRepository.findCustomerByUsername(userDetails.getUsername());

		UsuarioActualDto response = new UsuarioActualDto();
		response.setUsername(userDetails.getUsername());
		response.setPassword(userDetails.getPassword()); // No se recomienda exponer la contraseña, solo se utiliza aquí
															// para ilustrar
		response.setRole(userDetails.getAuthorities().toArray()[0].toString());
		//response.setIdCliente(user.getCliente() !=null ? user.getCliente().getIdCliente() : 0L);
		//response.setIdEmpleado(user.getEmpleado() != null? user.getEmpleado().getIdEmpleado() : 0L);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// Cambiar Password
	@Transactional
	public ResponseEntity<?> cambiarPassword(Principal principal, PasswordDto passwordDto) {
		Map<String, Object> resp = new HashMap<>();

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
						passwordDto.getCurrentPassword()));


		try {
			resp.put("mensaje", "La Contraseña ha sido cambiada");
			this.usuariosRepository.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()), userDetails.getUsername());
		} catch (Exception e) {
			resp.put("mensaje", "No es posible cambiar el password");

			return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.FORBIDDEN);
		}

		System.out.println("usuario valido: " + authentication.isAuthenticated());
		return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.OK);

	}
}
