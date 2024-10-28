package com.inventory.dto;

public record UnidadMedidaDto(
		long idUnidadMedida,
		String unidadMedida) {
	public UnidadMedidaDto(com.inventory.entity.UnidadMedida unidadMedida) {
		this(unidadMedida.getIdUnidadMedida(), unidadMedida.getUnidadMedida());
	}
}
