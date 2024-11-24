package com.inventory.dto;

import java.util.List;

import com.inventory.entity.Categoria;

public record CategoriaProductoDto(
	    long idCategoria,
	    String nombreCategoria,
	    List<ProductosDto> productos
	) {
	    public CategoriaProductoDto(Categoria categoria) {
	        this(
	            categoria.getIdCategoria(),
	            categoria.getNombreCategoria(),
	            categoria.getProductos().stream()
	                .map(ProductosDto::new)
	                .toList()
	        );
	    }
	}