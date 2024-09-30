package com.inventory.dto;

import com.inventory.entity.Categoria;

public record CategoriaDto(
		long idCategoria,
		String nombreCategoria,
		String descripcionCategoria
		) {
	
	public CategoriaDto(Categoria categoria) {
		this(categoria.getIdCategoria(), categoria.getNombreCategoria(), categoria.getDescripcionCategoria());
	}

}
