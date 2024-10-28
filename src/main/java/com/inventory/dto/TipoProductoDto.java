package com.inventory.dto;

import com.inventory.entity.TipoProducto;

public record TipoProductoDto(
		long idTipoProducto,
		String tipoProducto,
		Boolean status
		) {
	
	public TipoProductoDto(TipoProducto tipoProducto) {
		this(tipoProducto.getIdTipoProducto(), tipoProducto.getTipoProducto(), tipoProducto.isStatus());
	}
}
