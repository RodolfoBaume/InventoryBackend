package com.inventory.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.dto.EstatusOrdenDto;
import com.inventory.entity.EstatusOrden;
import com.inventory.repository.IEstatusOrdenRepository;

@Service
public class EstatusOrdenService implements IEstatusOrdenService {

	@Autowired
	private IEstatusOrdenRepository estatusOrdenRepository;

	// Consulta todos
	@Transactional(readOnly = true)
	public List<EstatusOrden> findAll() {
		return (List<EstatusOrden>) estatusOrdenRepository.findAll(Sort.by("idEstatusOrden"));
	}

	// consulta todos para paginación
	@Transactional(readOnly = true)
	public Page<EstatusOrden> findAllPage(Pageable pageable) {
		return estatusOrdenRepository.findAll(pageable);
	}

	// consulta por id
	@Transactional(readOnly = true)
	public EstatusOrden findById(Long idEstatusOrden) {
		return estatusOrdenRepository.findById(idEstatusOrden).orElse(null);
	}

	// Crear
	@Transactional
	public EstatusOrden createEstatusOrden(EstatusOrdenDto estatusOrden) {
		EstatusOrden estatusOrdenEntity = new EstatusOrden();
		estatusOrdenEntity.setEstatusOrden(estatusOrden.estatusOrden());
		return estatusOrdenRepository.save(estatusOrdenEntity);
	}

	// Eliminar
	public EstatusOrden deleteEstatusOrden(Long idEstatusOrden) {
		estatusOrdenRepository.deleteById(idEstatusOrden);
		return null;
	}

	// Modificar
	@Transactional
	public EstatusOrden updateEstatusOrden(Long idEstatusOrden, EstatusOrdenDto estatusOrden) {
		EstatusOrden estatusOrdenEntity = estatusOrdenRepository.findById(idEstatusOrden)
				.orElseThrow(() -> new NoSuchElementException("Estatus de Orden de Compra no encontrado con el ID: " + idEstatusOrden));
		estatusOrdenEntity.setEstatusOrden(estatusOrden.estatusOrden());
		return estatusOrdenRepository.save(estatusOrdenEntity);
	}
}
