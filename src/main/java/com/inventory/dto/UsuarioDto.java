package com.inventory.dto;

import java.util.List;

import com.inventory.entity.Rol;
import com.inventory.entity.Usuario;

public record UsuarioDto(long idUsuario, String username, String password, List<Rol>rol) {
	public UsuarioDto(Usuario usuario) {
		this(usuario.getIdUsuario(), usuario.getUsername(), usuario.getPassword(), usuario.getRol());
	}
}
