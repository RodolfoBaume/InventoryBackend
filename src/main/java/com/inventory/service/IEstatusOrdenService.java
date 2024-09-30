package com.inventory.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.inventory.dto.EstatusOrdenDto;
import com.inventory.entity.EstatusOrden;

public interface IEstatusOrdenService {

	List<EstatusOrden> findAll();
	
	Page<EstatusOrden> findAllPage(Pageable pageable);
	
	EstatusOrden findById(Long idEstatusOrden);
	
	EstatusOrden createEstatusOrden(EstatusOrdenDto estatusOrden);
	
	EstatusOrden deleteEstatusOrden(Long idEstatusOrden);
	
	EstatusOrden updateEstatusOrden(Long idEstatusOrden, EstatusOrdenDto estatusOrden);
	
}
