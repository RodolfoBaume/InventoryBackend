package com.inventory.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.inventory.dto.TipoMovimientoDto;
import com.inventory.entity.TipoMovimiento;

public interface ITipoMovimientoService {

	List<TipoMovimiento> findAll();
	
	Page<TipoMovimiento> findAllPage(Pageable pageable);
	
	TipoMovimiento findById(Long idTipoMovimiento);
	
	TipoMovimiento createTipoMovimiento(TipoMovimientoDto tipoMovimiento);
	
	TipoMovimiento deleteTipoMovimiento(Long idTipoMovimiento);
	
	TipoMovimiento updateTipoMovimiento(Long idTipoMovimiento, TipoMovimientoDto tipoMovimiento);

}
