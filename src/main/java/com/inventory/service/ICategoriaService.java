package com.inventory.service;

import java.util.List;

import com.inventory.dto.CategoriaDto;
import com.inventory.dto.CategoriaProductoDto;

public interface ICategoriaService {

	List<CategoriaDto> obtenerCategoriasEnArbol();
	
	CategoriaDto obtenerCategoriaPorId(Long id);
	
	CategoriaDto crearCategoria(CategoriaDto categoriaDto);
	
	CategoriaDto actualizarCategoria(Long id, CategoriaDto categoriaDto);
	
	void eliminarCategoria(Long id);
	
	//List<CategoriaProductoDTO> obtenerCategoriasConProductos();
	
	//Optional<CategoriaProductoDTO> obtenerCategoriaConProductosPorId(Long idCategoria);
	
	//List<Producto> obtenerProductosPorCategoriaId(Long idCategoria);
	CategoriaProductoDto obtenerProductosPorCategoria(Long idCategoria);
	
	//breadcrumb
	List<CategoriaDto> obtenerJerarquiaPorId(Long idCategoria);
}
