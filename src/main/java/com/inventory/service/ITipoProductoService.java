package com.inventory.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.inventory.dto.TipoProductoDto;
import com.inventory.entity.TipoMovimiento;
import com.inventory.entity.TipoProducto;

public interface ITipoProductoService {

	List<TipoProducto> findAll();
	
	Page<TipoProducto> findAllPage(Pageable pageable);
	
	TipoProducto findById(Long idTipoProducto);
	
	TipoProducto createTipoProducto(TipoProductoDto tipoProducto);
	
	TipoMovimiento deleteTipoMovimiento(Long idTipoMovimiento);
	
	TipoProducto updateTipoProducto(Long idTipoProducto, TipoProductoDto tipoProducto);
}
