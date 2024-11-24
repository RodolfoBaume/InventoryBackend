package com.inventory.dto;

import java.util.List;

import com.inventory.entity.Grupo;

public record GrupoDto(
	    long idGrupo,
	    String nombreGrupo,
	    Boolean status,
	    List<AtributoDto> atributos
	) {
	    public GrupoDto(Grupo grupo) {
	        this(
	            grupo.getIdGrupo(),
	            grupo.getNombreGrupo(),
	            grupo.getStatus(),
	            grupo.getAtributos() == null ? List.of() : grupo.getAtributos().stream()
	                .map(AtributoDto::new)
	                .toList()
	        );
	    }
	}
