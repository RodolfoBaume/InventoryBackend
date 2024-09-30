package com.inventory.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.inventory.dto.RegistroResponseDto;
import com.inventory.dto.UsuarioDto;
import com.inventory.entity.Usuario;


public interface IUsuarioService {

	List<Usuario> findAll();
	
	Page<Usuario> findAllPage(Pageable pageable);
	
	Usuario findById(Long idUsuario);
	
	ResponseEntity<RegistroResponseDto> registrarUsuario(UsuarioDto dtoRegistro, String role);
	
	Usuario deleteUsuario(Long idUsuario);
	
	Usuario updateUsuario(Long idUsuario, UsuarioDto usuario);
	
	
}
