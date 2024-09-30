package com.inventory.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.dto.UbicacionDto;
import com.inventory.entity.Ubicacion;
import com.inventory.repository.IUbicacionRepository;

@Service
public class UbicacionService implements IUbicacionService {

	@Autowired
	private IUbicacionRepository ubicacionRepository;

	// Consulta todos
	@Transactional(readOnly = true)
	public List<Ubicacion> findAll() {
		return (List<Ubicacion>) ubicacionRepository.findAll(Sort.by("idUbicacion"));
	}

	// consulta todos para paginación
	@Transactional(readOnly = true)
	public Page<Ubicacion> findAllPage(Pageable pageable) {
		return ubicacionRepository.findAll(pageable);
	}

	// consulta por id
	@Transactional(readOnly = true)
	public Ubicacion findById(Long idUbicacion) {
		return ubicacionRepository.findById(idUbicacion).orElse(null);
	}

	// Crear
	@Transactional
	public Ubicacion createUbicacion(UbicacionDto ubicacion) {
		Ubicacion ubicacionEntity = new Ubicacion();
		ubicacionEntity.setNombreUbicacion(ubicacion.nombreUbicacion());
		ubicacionEntity.setDireccionUbicacion(ubicacion.direccionUbicacion());
		return ubicacionRepository.save(ubicacionEntity);
	}

	// Eliminar
	public Ubicacion deleteUbicacion(Long idUbicacion) {
		ubicacionRepository.deleteById(idUbicacion);
		return null;
	}

	// Modificar
	@Transactional
	public Ubicacion updateUbicacion(Long idUbicacion, UbicacionDto ubicacion) {
		Ubicacion ubicacionEntity = ubicacionRepository.findById(idUbicacion)
				.orElseThrow(() -> new NoSuchElementException("Ubicacion no encontrada con el ID: " + idUbicacion));
		ubicacionEntity.setNombreUbicacion(ubicacion.nombreUbicacion());
		ubicacionEntity.setDireccionUbicacion(ubicacion.direccionUbicacion());
		return ubicacionRepository.save(ubicacionEntity);
	}
}
