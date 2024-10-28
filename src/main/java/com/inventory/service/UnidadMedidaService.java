package com.inventory.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.dto.UnidadMedidaDto;
import com.inventory.entity.UnidadMedida;
import com.inventory.repository.IUnidadMedidaRepository;

@Service
public class UnidadMedidaService implements IUnidadMedidaService {

	@Autowired
	private IUnidadMedidaRepository unidadMedidaRepository;

	// Consulta todos
	@Transactional(readOnly = true)
	public List<UnidadMedida> findAll() {
		return (List<UnidadMedida>) unidadMedidaRepository.findAll(Sort.by("idUnidadMedida"));
	}

	// consulta todos para paginación
	@Transactional(readOnly = true)
	public Page<UnidadMedida> findAllPage(Pageable pageable) {
		return unidadMedidaRepository.findAll(pageable);
	}

	// consulta por id
	@Transactional(readOnly = true)
	public UnidadMedida findById(Long idUnidadMedida) {
		return unidadMedidaRepository.findById(idUnidadMedida).orElse(null);
	}

	// Crear
	@Transactional
	public UnidadMedida createUnidadMedida(UnidadMedidaDto unidadMedida) {
		UnidadMedida unidadMedidaEntity = new UnidadMedida();
		unidadMedidaEntity.setUnidadMedida(unidadMedida.unidadMedida());
		return unidadMedidaRepository.save(unidadMedidaEntity);
	}

	// Eliminar
	public UnidadMedida deleteUnidadMedida(Long idUnidadMedida) {
		unidadMedidaRepository.deleteById(idUnidadMedida);
		return null;
	}

	// Modificar
	@Transactional
	public UnidadMedida updateUnidadMedida(Long idUnidadMedida, UnidadMedidaDto unidadMedida) {
		UnidadMedida unidadMedidaEntity = unidadMedidaRepository.findById(idUnidadMedida).orElseThrow(
				() -> new NoSuchElementException("Unidad de Medida no encontrada con el ID: " + idUnidadMedida));
		unidadMedidaEntity.setUnidadMedida(unidadMedida.unidadMedida());
		return unidadMedidaRepository.save(unidadMedidaEntity);
	}
}
