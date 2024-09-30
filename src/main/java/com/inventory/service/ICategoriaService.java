package com.inventory.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.inventory.dto.CategoriaDto;
import com.inventory.entity.Categoria;

public interface ICategoriaService {

	List<Categoria> findAll();
	
	Page<Categoria> findAllPage(Pageable pageable);
	
	Categoria findById(Long idCategoria);
	
	Categoria createCategoria(CategoriaDto categoria);
	
	Categoria deleteCategoria(Long idCategoria);
	
	Categoria updateCategoria(Long idCategoria, CategoriaDto categoria);
}
