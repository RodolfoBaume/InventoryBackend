package com.inventory.dto;

import com.inventory.entity.Atributo;
import com.inventory.entity.Grupo;

public record AtributoDto(
		long idAtributo,
		String atributo,
		Grupo grupo
		) {
	public AtributoDto(Atributo atributo) {
		this(atributo.getIdAtributo(), atributo.getAtributo(), atributo.getGrupo());
	}
}
