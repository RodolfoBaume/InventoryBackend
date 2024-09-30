package com.inventory.dto;

import com.inventory.entity.Rol;

public record RolDto(Long idRol, String nombre) {
	public RolDto(Rol rol) {
		this(rol.getIdRol(), rol.getNombre());
	}
}
