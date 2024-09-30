package com.inventory.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.dto.TipoMovimientoDto;
import com.inventory.entity.TipoMovimiento;
import com.inventory.repository.ITipoMovimientoRepository;

@Service
public class TipoMovimientoService implements ITipoMovimientoService {

	@Autowired
	private ITipoMovimientoRepository tipoMovimientoRepository;

	// Consulta todos
	@Transactional(readOnly = true)
	public List<TipoMovimiento> findAll() {
		return (List<TipoMovimiento>) tipoMovimientoRepository.findAll(Sort.by("idTipoMovimiento"));
	}

	// consulta todos para paginación
	@Transactional(readOnly = true)
	public Page<TipoMovimiento> findAllPage(Pageable pageable) {
		return tipoMovimientoRepository.findAll(pageable);
	}

	// consulta por id
	@Transactional(readOnly = true)
	public TipoMovimiento findById(Long idTipoMovimiento) {
		return tipoMovimientoRepository.findById(idTipoMovimiento).orElse(null);
	}

	// Crear
	@Transactional
	public TipoMovimiento createTipoMovimiento(TipoMovimientoDto tipoMovimiento) {
		TipoMovimiento tipoMovimientoEntity = new TipoMovimiento();
		tipoMovimientoEntity.setTipoMovimiento(tipoMovimiento.tipoMovimiento());
		return tipoMovimientoRepository.save(tipoMovimientoEntity);
	}

	// Eliminar
	public TipoMovimiento deleteTipoMovimiento(Long idTipoMovimiento) {
		tipoMovimientoRepository.deleteById(idTipoMovimiento);
		return null;
	}

	// Modificar
	@Transactional
	public TipoMovimiento updateTipoMovimiento(Long idTipoMovimiento, TipoMovimientoDto tipoMovimiento) {
		TipoMovimiento tipoMovimientoEntity = tipoMovimientoRepository.findById(idTipoMovimiento)
				.orElseThrow(() -> new NoSuchElementException(
						"Tipo de Movimiento no encontrado con el ID: " + idTipoMovimiento));
		tipoMovimientoEntity.setTipoMovimiento(tipoMovimiento.tipoMovimiento());
		return tipoMovimientoRepository.save(tipoMovimientoEntity);
	}
}
