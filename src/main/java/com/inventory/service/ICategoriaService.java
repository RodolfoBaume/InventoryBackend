package com.inventory.service;

import java.util.List;
import java.util.Optional;

import com.inventory.dto.CategoriaDto;
import com.inventory.projection.CategoriaProductoDTO;

public interface ICategoriaService {

	List<CategoriaDto> obtenerCategoriasEnArbol();
	
	CategoriaDto obtenerCategoriaPorId(Long id);
	
	CategoriaDto crearCategoria(CategoriaDto categoriaDto);
	
	CategoriaDto actualizarCategoria(Long id, CategoriaDto categoriaDto);
	
	void eliminarCategoria(Long id);
	
	List<CategoriaProductoDTO> obtenerCategoriasConProductos();
	
	Optional<CategoriaProductoDTO> obtenerCategoriaConProductosPorId(Long idCategoria);
}
