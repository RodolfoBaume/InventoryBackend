package com.inventory.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.inventory.dto.UnidadMedidaDto;
import com.inventory.entity.UnidadMedida;

public interface IUnidadMedidaService {

	List<UnidadMedida>findAll();
	
	Page<UnidadMedida> findAllPage(Pageable pageable);
	
	UnidadMedida findById(Long idUnidadMedida);
	
	UnidadMedida createUnidadMedida(UnidadMedidaDto unidadMedida);
	
	UnidadMedida deleteUnidadMedida(Long idUnidadMedida);
	
	UnidadMedida updateUnidadMedida(Long idUnidadMedida, UnidadMedidaDto unidadMedida);
}
