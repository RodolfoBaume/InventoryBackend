package com.inventory.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.inventory.dto.UbicacionDto;
import com.inventory.entity.Ubicacion;

public interface IUbicacionService {
	
	List<Ubicacion> findAll();
	
	Page<Ubicacion> findAllPage(Pageable pageable);
	
	Ubicacion findById(Long idUbicacion);
	
	Ubicacion createUbicacion(UbicacionDto ubicacion);
	
	Ubicacion deleteUbicacion(Long idUbicacion);
	
	Ubicacion updateUbicacion(Long idUbicacion, UbicacionDto ubicacion);
	
}
