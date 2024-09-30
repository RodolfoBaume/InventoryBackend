package com.inventory.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.dto.CategoriaDto;
import com.inventory.entity.Categoria;
import com.inventory.repository.ICategoriaRepository;

@Service
public class CategoriaService implements ICategoriaService{

	@Autowired
	private ICategoriaRepository categoriaRepository;

	// Consulta todos
	@Transactional(readOnly = true)
	public List<Categoria> findAll() {
		return (List<Categoria>) categoriaRepository.findAll(Sort.by("idCategoria"));
	}

	// consulta todos para paginación
	@Transactional(readOnly = true)
	public Page<Categoria> findAllPage(Pageable pageable) {
		return categoriaRepository.findAll(pageable);
	}

	// consulta por id
	@Transactional(readOnly = true)
	public Categoria findById(Long idCategoria) {
		return categoriaRepository.findById(idCategoria).orElse(null);
	}

	// Crear
	@Transactional
	public Categoria createCategoria(CategoriaDto categoria) {
		Categoria categoriaEntity = new Categoria();
		categoriaEntity.setNombreCategoria(categoria.nombreCategoria());
		categoriaEntity.setDescripcionCategoria(categoria.descripcionCategoria());
		return categoriaRepository.save(categoriaEntity);
	}

	// Eliminar
	public Categoria deleteCategoria(Long idCategoria) {
		categoriaRepository.deleteById(idCategoria);
		return null;
	}

	// Modificar
	@Transactional
	public Categoria updateCategoria(Long idCategoria, CategoriaDto categoria) {
		Categoria categoriaEntity = categoriaRepository.findById(idCategoria)
				.orElseThrow(() -> new NoSuchElementException("Categoria no encontrada con el ID: " + idCategoria));
		categoriaEntity.setNombreCategoria(categoria.nombreCategoria());
		categoriaEntity.setDescripcionCategoria(categoria.descripcionCategoria());
		return categoriaRepository.save(categoriaEntity);
	}
}
