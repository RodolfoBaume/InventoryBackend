package com.inventory.dto;

import java.util.List;

import com.inventory.entity.Categoria;

public record CategoriaDto(
	    Long idCategoria,
	    String nombreCategoria,
	    String descripcionCategoria,
	    Boolean folder,
	    Long parentId,
	    List<CategoriaDto> subcategorias
	) {
	    /**
	     * Constructor que inicializa un CategoriaDto a partir de un objeto Categoria.
	     *
	     * @param categoria entidad Categoria para crear el DTO
	     */
	    public CategoriaDto(Categoria categoria) {
	        this(
	            categoria.getIdCategoria(),
	            categoria.getNombreCategoria(),
	            categoria.getDescripcionCategoria(),
	            categoria.getFolder(),
	            categoria.getParent() != null ? categoria.getParent().getIdCategoria() : null,
	            categoria.getSubcategorias() != null ? 
	                categoria.getSubcategorias().stream()
	                    .map(CategoriaDto::new)
	                    .toList() 
	                : List.of()
	        );
	    }
	}
