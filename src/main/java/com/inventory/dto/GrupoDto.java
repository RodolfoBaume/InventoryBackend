package com.inventory.dto;

import com.inventory.entity.Grupo;

public record GrupoDto(
		long idGrupo,
		String nombreGrupo,
		Boolean status
		) {
	
	public GrupoDto(Grupo grupo) {
		this(grupo.getIdGrupo(), grupo.getNombreGrupo(), grupo.getStatus());
	}
}
