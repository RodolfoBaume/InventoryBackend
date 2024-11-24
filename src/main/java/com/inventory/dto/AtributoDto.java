package com.inventory.dto;

import java.util.List;

import com.inventory.entity.Atributo;

public record AtributoDto(
	    long idAtributo,
	    String atributo,
	    List<ValorAtributoDto> valores
	) {
	    public AtributoDto(Atributo atributo) {
	        this(
	            atributo.getIdAtributo(),
	            atributo.getAtributo(),
	            atributo.getValores() == null ? List.of() : atributo.getValores().stream()
	                .map(ValorAtributoDto::new)
	                .toList()
	        );
	    }
	}